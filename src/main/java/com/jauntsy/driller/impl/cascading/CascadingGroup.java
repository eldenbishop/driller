package com.jauntsy.driller.impl.cascading;

import cascading.flow.FlowProcess;
import cascading.operation.*;
import cascading.pipe.Every;
import cascading.pipe.GroupBy;
import cascading.pipe.Pipe;
import cascading.tuple.*;
import com.jauntsy.driller.api.*;
import com.jauntsy.driller.api.Tuple;
import com.jauntsy.driller.api.Tuples;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * User: ebishop
 * Date: 6/19/12
 * Time: 8:19 PM
 */
public class CascadingGroup extends CascadingDrillerBasePipeline implements UntypedGrouped {

    private Integer depth = null;
    private String[] columnNames = null;

    @Deprecated
    public CascadingGroup() {
        super();
    }

    public CascadingGroup(FlowContext flow, Schema inSchema, Pipe assembly, int depth) {
        super(flow, inSchema, assembly, inSchema);
        this.depth = depth;
    }

    public CascadingGroup(FlowContext flow, Schema inSchema, Pipe assembly, String[] columnNames) {
        super(flow, inSchema, assembly, inSchema);
        this.columnNames = columnNames;
    }

    private GroupBy buildGroupBy(Pipe pipe, Schema schema) {
        if (depth != null)
            return buildGroupBy(pipe, schema, depth);
        else
            return buildGroupBy(pipe, schema, columnNames);
    }

    static GroupBy buildGroupBy(Pipe pipe, Schema schema, int depth) {
        List<String> columnNamesList = schema.getColumnNames().subList(0, depth);
        String[] columnNamesArr = columnNamesList.toArray(new String[columnNamesList.size()]);
        return buildGroupBy(pipe, schema, columnNamesArr);
    }

    static GroupBy buildGroupBy(Pipe pipe, Schema schema, String[] columnNames) {
        Fields fields = new Fields(columnNames);
        return new GroupBy(pipe, fields);
    }

    private GroupBy buildGroupBy() {
        return buildGroupBy(pipeIn, schemaOut);
    }

    @Override
    public UntypedTable ungroup() {
        return new CascadingTable(flow, schemaOut, buildGroupBy());
    }

    @Override
    public UntypedGrouped combine(Combiner combiner) {
        return new CascadingDepthCombinedGroup(flow, schemaIn, pipeIn, depth, combiner);
    }

    @Override
    public UntypedTable reduce(Schema schemaOut, Reducer reducer) {
        return new CascadingTable(flow, schemaOut, new Every(buildGroupBy(pipeIn, schemaIn), Fields.ALL, new GenericReducer(schemaIn, depth, schemaOut, reducer), Fields.RESULTS));
    }

    public static class GenericReducer extends cascading.operation.BaseOperation<GenericReducer.Context> implements Buffer<GenericReducer.Context> {

        private Schema keySchema;
        private Schema valSchema;
        private Schema inputSchema;
        private Schema outputSchema;

        private int depth;
        private Reducer reducer;

        public GenericReducer(Schema schemaIn, int depth, Schema schemaOut, Reducer reducer) {
            this.inputSchema = schemaIn;
            // *** build the key schema
            this.keySchema = Schema.of0();
            for (int i = 1; i <= depth; i++)
                keySchema = keySchema.add(schemaIn.getColumnName(i), schemaIn.getColumnType(i));
            // *** build the value schema
            this.valSchema = Schema.of0();
            for (int i = depth + 1; i <= schemaIn.size(); i++)
                valSchema = valSchema.add(schemaIn.getColumnName(i), schemaIn.getColumnType(i));
            this.outputSchema = schemaOut;

            this.depth = depth;
            this.reducer = reducer;

            reducer.setInputSchema(inputSchema);
            reducer.setOutputSchema(outputSchema);

            reducer.setKeySchema(keySchema);
        }

        @Override
        public void prepare(FlowProcess flowProcess, OperationCall<Context> operationCall) {
            super.prepare(flowProcess, operationCall);    //To change body of overridden methods use File | Settings | File Templates.
            if (operationCall.getContext() == null) {
                operationCall.setContext(new Context(keySchema, valSchema, outputSchema));
            }
        }

        @Override
        public void operate(FlowProcess flowProcess, BufferCall<Context> bufferCall) {

            Context context = bufferCall.getContext();

            final Tuple keyTuple = context.keyTuple;
            final Tuple valTuple = context.valTuple;
            final cascading.tuple.Tuple outputTuple = context.outputTuple;

            final TupleEntry group = bufferCall.getGroup();
            final Iterator<TupleEntry> argumentsIterator = bufferCall.getArgumentsIterator();
            final TupleEntryCollector outputCollector = bufferCall.getOutputCollector();

            for (int i = 1; i <= keyTuple.size(); i++)
                keyTuple.set(i, group.get(i - 1));

            reducer.reduce(
                    keyTuple,
                    new Tuples() {
                        @Override
                        public boolean hasNext() {
                            return argumentsIterator.hasNext();
                        }

                        @Override
                        public Tuple next() {
                            TupleEntry next = argumentsIterator.next();
                            for (int i = 1; i <= valTuple.size(); i++) {
                                valTuple.set(i, next.get(keyTuple.size() + i - 1));
                            }
                            return valTuple;
                        }

                        @Override
                        public void remove() {
                            throw new UnsupportedOperationException();
                        }
                    },
                    new Emitter<Tuple>() {
                        @Override
                        public void emit(Tuple tuple) {
                            for (int i = 1; i <= tuple.size(); i++)
                                outputTuple.set(i - 1, tuple.get(i));
                            outputCollector.add(outputTuple);
                        }
                    }
            );
        }


        protected static class Context {

            public Tuple keyTuple;
            public Tuple valTuple;
            public cascading.tuple.Tuple outputTuple;

            public Context(Schema keySchema, Schema valSchema, Schema outputSchema) {
                this.keyTuple = keySchema.newTuple();
                this.valTuple = valSchema.newTuple();
                this.outputTuple = cascading.tuple.Tuple.size(outputSchema.size());
            }

        }

    }

    public static class GenericDepthCombiner extends cascading.operation.BaseOperation<GenericDepthCombiner.Context> implements Buffer<GenericDepthCombiner.Context> {

        private Schema schemaIn;
        private Schema keySchema;
        private Schema valSchema;

        private int depth;
        private Combiner combiner;

        public GenericDepthCombiner(Schema schemaIn, int depth, Combiner combiner) {
            this.schemaIn = schemaIn;
            // *** build the key schema
            this.keySchema = Schema.of0();
            for (int i = 1; i <= depth; i++)
                keySchema = keySchema.add(schemaIn.getColumnName(i), schemaIn.getColumnType(i));
            // *** build the value schema
            this.valSchema = Schema.of0();
            for (int i = depth + 1; i <= schemaIn.size(); i++)
                valSchema = valSchema.add(schemaIn.getColumnName(i), schemaIn.getColumnType(i));

            this.depth = depth;
            this.combiner = combiner;

            combiner.setKeySchema(keySchema);
            combiner.setInputSchema(valSchema);
            combiner.setOutputSchema(valSchema);

        }

        @Override
        public void prepare(FlowProcess flowProcess, OperationCall<Context> operationCall) {
            super.prepare(flowProcess, operationCall);    //To change body of overridden methods use File | Settings | File Templates.
            if (operationCall.getContext() == null) {
                operationCall.setContext(new Context(keySchema, valSchema, schemaIn));
            }
        }

        @Override
        public void operate(FlowProcess flowProcess, BufferCall<Context> bufferCall) {
            Context context = bufferCall.getContext();

            final Tuple keyTuple = context.keyTuple;
            final int keySize = keyTuple.size();

            final Tuple valTuple = context.valTuple;
            final int valSize = valTuple.size();

//            final cascading.tuple.Tuple outputValTuple = context.outputValTuple;
            final cascading.tuple.Tuple outputTuple = context.outputTuple;

            final TupleEntry group = bufferCall.getGroup();
            final Iterator<TupleEntry> argumentsIterator = bufferCall.getArgumentsIterator();
            final TupleEntryCollector outputCollector = bufferCall.getOutputCollector();

            for (int i = 1; i <= keyTuple.size(); i++)
                keyTuple.set(i, group.get(i - 1));


            final Tuple intermediateValue = combiner.combine(
                    keyTuple,
                    new Tuples() {
                        @Override
                        public boolean hasNext() {
                            return argumentsIterator.hasNext();
                        }

                        @Override
                        public Tuple next() {
                            TupleEntry next = argumentsIterator.next();
                            for (int i = 1; i <= valSize; i++) {
                                valTuple.set(i, next.get(keySize + i - 1));
                            }
                            return valTuple;
                        }

                        @Override
                        public void remove() {
                            throw new UnsupportedOperationException();
                        }
                    }
            );
            for (int i = 0; i < keySize; i++)
                outputTuple.set(i, keyTuple.get(i + 1));
            for (int i = 0; i < intermediateValue.size(); i++)
                outputTuple.set(i + keySize, intermediateValue.get(i + 1));
            outputCollector.add(outputTuple);
        }


        protected static class Context {

            public Tuple keyTuple;
            public Tuple valTuple;
            public cascading.tuple.Tuple outputValTuple;
            public cascading.tuple.Tuple outputTuple;

            public Context(Schema keySchema, Schema valSchema, Schema outputSchema) {
                this.keyTuple = keySchema.newTuple();
                this.valTuple = valSchema.newTuple();
                this.outputTuple = cascading.tuple.Tuple.size(outputSchema.size());
            }

        }

    }

    public static class GenericColumnCombiner extends cascading.operation.BaseOperation<GenericColumnCombiner.Context> implements Buffer<GenericColumnCombiner.Context> {

        private Schema schemaIn;
        private Schema keySchema;
        private Schema valSchema;

        private String[] columnNames;
        private Combiner combiner;

        public GenericColumnCombiner(Schema schemaIn, String[] columnNames, Combiner combiner) {
            this.schemaIn = schemaIn;
            // *** build the key schema
            this.keySchema = Schema.of0();
            for (int i = 0; i < columnNames.length; i++)
                keySchema = keySchema.add(columnNames[i], schemaIn.getColumnType(columnNames[i]));
            // *** build the value schema
            this.valSchema = Schema.of0();
            for (int i = 0; i < schemaIn.size(); i++) {
                String name = schemaIn.getColumnName(i + 1);
                if (keySchema.getColumn(name) != null) {
                    valSchema = valSchema.add(name, schemaIn.getColumnType(i + 1));
                }
            }

            this.combiner = combiner;

            combiner.setKeySchema(keySchema);
            combiner.setInputSchema(valSchema);
            combiner.setOutputSchema(valSchema);

        }

        @Override
        public void prepare(FlowProcess flowProcess, OperationCall<Context> operationCall) {
            super.prepare(flowProcess, operationCall);    //To change body of overridden methods use File | Settings | File Templates.
            if (operationCall.getContext() == null) {
                operationCall.setContext(new Context(keySchema, valSchema, schemaIn));
            }
        }

        @Override
        public void operate(FlowProcess flowProcess, BufferCall<Context> bufferCall) {
            Context context = bufferCall.getContext();

            final Tuple keyTuple = context.keyTuple;
            final int keySize = keyTuple.size();

            final Tuple valTuple = context.valTuple;
            final int valSize = valTuple.size();

//            final cascading.tuple.Tuple outputValTuple = context.outputValTuple;
            final cascading.tuple.Tuple outputTuple = context.outputTuple;

            final TupleEntry group = bufferCall.getGroup();
            final Iterator<TupleEntry> argumentsIterator = bufferCall.getArgumentsIterator();
            final TupleEntryCollector outputCollector = bufferCall.getOutputCollector();

            for (int i = 1; i <= keyTuple.size(); i++)
                keyTuple.set(i, group.get(i - 1));


            final Tuple intermediateValue = combiner.combine(
                    keyTuple,
                    new Tuples() {
                        @Override
                        public boolean hasNext() {
                            return argumentsIterator.hasNext();
                        }

                        @Override
                        public Tuple next() {
                            TupleEntry next = argumentsIterator.next();
                            for (int i = 1; i <= valSize; i++) {
                                valTuple.set(i, next.get(keySize + i - 1));
                            }
                            return valTuple;
                        }

                        @Override
                        public void remove() {
                            throw new UnsupportedOperationException();
                        }
                    }
            );
            for (int i = 0; i < keySize; i++)
                outputTuple.set(i, keyTuple.get(i + 1));
            for (int i = 0; i < intermediateValue.size(); i++)
                outputTuple.set(i + keySize, intermediateValue.get(i + 1));
            outputCollector.add(outputTuple);
        }


        protected static class Context {

            public Tuple keyTuple;
            public Tuple valTuple;
            public cascading.tuple.Tuple outputValTuple;
            public cascading.tuple.Tuple outputTuple;

            public Context(Schema keySchema, Schema valSchema, Schema outputSchema) {
                this.keyTuple = keySchema.newTuple();
                this.valTuple = valSchema.newTuple();
                this.outputTuple = cascading.tuple.Tuple.size(outputSchema.size());
            }

        }

    }

    public static class GenericDepthCombinerReducer extends cascading.operation.BaseOperation<GenericDepthCombinerReducer.Context> implements Buffer<GenericDepthCombinerReducer.Context> {

        private Schema schemaIn;
        private Schema keySchema;
        private Schema valSchema;
        private Schema outputSchema;

//        private int depth;
        private Combiner combiner;
        private Reducer reducer;

        public GenericDepthCombinerReducer(Schema schemaIn, int depth, Combiner combiner, Schema schemaOut, Reducer reducer) {
            this.schemaIn = schemaIn;
            // *** build the key schema
            this.keySchema = Schema.of0();
            for (int i = 1; i <= depth; i++)
                keySchema = keySchema.add(schemaIn.getColumnName(i), schemaIn.getColumnType(i));
            // *** build the value schema
            this.valSchema = Schema.of0();
            for (int i = depth + 1; i <= schemaIn.size(); i++)
                valSchema = valSchema.add(schemaIn.getColumnName(i), schemaIn.getColumnType(i));
            this.outputSchema = schemaOut;

//            this.depth = depth;
            this.combiner = combiner;
            this.reducer = reducer;

            combiner.setKeySchema(keySchema);
            combiner.setInputSchema(valSchema);
            combiner.setOutputSchema(valSchema);

            reducer.setKeySchema(keySchema);
            reducer.setInputSchema(valSchema);
            reducer.setOutputSchema(outputSchema);
        }

        @Override
        public void prepare(FlowProcess flowProcess, OperationCall<Context> operationCall) {
            super.prepare(flowProcess, operationCall);    //To change body of overridden methods use File | Settings | File Templates.
            if (operationCall.getContext() == null) {
                operationCall.setContext(new Context(keySchema, valSchema, outputSchema));
            }
        }

        @Override
        public void operate(FlowProcess flowProcess, BufferCall<Context> bufferCall) {
            Context context = bufferCall.getContext();

            final Tuple keyTuple = context.keyTuple;
            final Tuple valTuple = context.valTuple;
            final cascading.tuple.Tuple outputValTuple = context.outputValTuple;
            final cascading.tuple.Tuple outputTuple = context.outputTuple;

            final TupleEntry group = bufferCall.getGroup();
            final Iterator<TupleEntry> argumentsIterator = bufferCall.getArgumentsIterator();
            final TupleEntryCollector outputCollector = bufferCall.getOutputCollector();

            for (int i = 1; i <= keyTuple.size(); i++)
                keyTuple.set(i, group.get(i));

            final Tuple intermediateValue = combiner.combine(
                    keyTuple,
                    new Tuples() {
                        @Override
                        public boolean hasNext() {
                            return argumentsIterator.hasNext();
                        }

                        @Override
                        public Tuple next() {
                            TupleEntry next = argumentsIterator.next();
                            for (int i = 1; i < valTuple.size(); i++) {
                                valTuple.set(i, next.get(keyTuple.size() + i - 1));
                            }
                            return valTuple;
                        }

                        @Override
                        public void remove() {
                            throw new UnsupportedOperationException();
                        }
                    }
            );
            reducer.reduce(
                    keyTuple,
                    new Tuples() {
                        boolean _hasNext = true;
                        @Override
                        public boolean hasNext() {
                            return _hasNext;
                        }

                        @Override
                        public Tuple next() {
                            if (_hasNext) {
                                _hasNext = false;
                                return intermediateValue;
                            } else {
                                throw new NoSuchElementException();
                            }
                        }

                        @Override
                        public void remove() {
                            throw new UnsupportedOperationException();
                        }
                    },
                    new Emitter<Tuple>() {
                        @Override
                        public void emit(Tuple tuple) {
                            for (int i = 0; i < tuple.size(); i++)
                                outputTuple.set(i, tuple.get(i + 1));
                            outputCollector.add(outputTuple);
                        }
                    }
            );
        }


        protected static class Context {

            public Tuple keyTuple;
            public Tuple valTuple;
            public cascading.tuple.Tuple outputValTuple;
            public cascading.tuple.Tuple outputTuple;

            public Context(Schema keySchema, Schema valSchema, Schema outputSchema) {
                this.keyTuple = keySchema.newTuple();
                this.valTuple = valSchema.newTuple();
                this.outputTuple = cascading.tuple.Tuple.size(outputSchema.size());
            }

        }

    }

    public static class GenericColumnCombinerReducer extends cascading.operation.BaseOperation<GenericColumnCombinerReducer.Context> implements Buffer<GenericColumnCombinerReducer.Context> {

        private Schema schemaIn;
        private Schema keySchema;
        private Schema valSchema;
        private Schema outputSchema;

//        private int depth;
        private Combiner combiner;
        private Reducer reducer;

        public GenericColumnCombinerReducer(Schema schemaIn, String[] columnNames, Combiner combiner, Schema schemaOut, Reducer reducer) {
            this.schemaIn = schemaIn;
            // *** build the key schema
            this.keySchema = Schema.of0();
            for (int i = 0; i < columnNames.length; i++)
                keySchema = keySchema.add(columnNames[i], schemaIn.getColumnType(i + 1));
            // *** build the value schema
            this.valSchema = Schema.of0();
            for (int i = 0; i < schemaIn.size(); i++) {
                String name = schemaIn.getColumnName(i + 1);
                if (keySchema.getColumnIndex(name) > 0) {
                    valSchema = valSchema.add(name, schemaIn.getColumnType(i + 1));
                }
            }
            this.outputSchema = schemaOut;

//            this.depth = depth;
            this.combiner = combiner;
            this.reducer = reducer;

            combiner.setKeySchema(keySchema);
            combiner.setInputSchema(valSchema);
            combiner.setOutputSchema(valSchema);

            reducer.setKeySchema(keySchema);
            reducer.setInputSchema(valSchema);
            reducer.setOutputSchema(outputSchema);
        }

        @Override
        public void prepare(FlowProcess flowProcess, OperationCall<Context> operationCall) {
            super.prepare(flowProcess, operationCall);    //To change body of overridden methods use File | Settings | File Templates.
            if (operationCall.getContext() == null) {
                operationCall.setContext(new Context(keySchema, valSchema, outputSchema));
            }
        }

        @Override
        public void operate(FlowProcess flowProcess, BufferCall<Context> bufferCall) {
            Context context = bufferCall.getContext();

            final Tuple keyTuple = context.keyTuple;
            final Tuple valTuple = context.valTuple;
            final cascading.tuple.Tuple outputValTuple = context.outputValTuple;
            final cascading.tuple.Tuple outputTuple = context.outputTuple;

            final TupleEntry group = bufferCall.getGroup();
            final Iterator<TupleEntry> argumentsIterator = bufferCall.getArgumentsIterator();
            final TupleEntryCollector outputCollector = bufferCall.getOutputCollector();

            for (int i = 1; i <= keyTuple.size(); i++)
                keyTuple.set(i, group.get(i));

            final Tuple intermediateValue = combiner.combine(
                    keyTuple,
                    new Tuples() {
                        @Override
                        public boolean hasNext() {
                            return argumentsIterator.hasNext();
                        }

                        @Override
                        public Tuple next() {
                            TupleEntry next = argumentsIterator.next();
                            for (int i = 1; i < valTuple.size(); i++) {
                                valTuple.set(i, next.get(keyTuple.size() + i - 1));
                            }
                            return valTuple;
                        }

                        @Override
                        public void remove() {
                            throw new UnsupportedOperationException();
                        }
                    }
            );
            reducer.reduce(
                    keyTuple,
                    new Tuples() {
                        boolean _hasNext = true;
                        @Override
                        public boolean hasNext() {
                            return _hasNext;
                        }

                        @Override
                        public Tuple next() {
                            if (_hasNext) {
                                _hasNext = false;
                                return intermediateValue;
                            } else {
                                throw new NoSuchElementException();
                            }
                        }

                        @Override
                        public void remove() {
                            throw new UnsupportedOperationException();
                        }
                    },
                    new Emitter<Tuple>() {
                        @Override
                        public void emit(Tuple tuple) {
                            for (int i = 0; i < tuple.size(); i++)
                                outputTuple.set(i, tuple.get(i + 1));
                            outputCollector.add(outputTuple);
                        }
                    }
            );
        }


        protected static class Context {

            public Tuple keyTuple;
            public Tuple valTuple;
            public cascading.tuple.Tuple outputValTuple;
            public cascading.tuple.Tuple outputTuple;

            public Context(Schema keySchema, Schema valSchema, Schema outputSchema) {
                this.keyTuple = keySchema.newTuple();
                this.valTuple = valSchema.newTuple();
                this.outputTuple = cascading.tuple.Tuple.size(outputSchema.size());
            }

        }

    }

}
