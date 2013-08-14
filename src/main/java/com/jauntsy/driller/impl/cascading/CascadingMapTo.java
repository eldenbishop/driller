package com.jauntsy.driller.impl.cascading;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.tuple.*;
import com.jauntsy.driller.api.*;
import com.jauntsy.driller.api.Tuple;

import java.io.Serializable;
import java.util.List;

/**
 * User: ebishop
 * Date: 6/21/12
 * Time: 11:06 AM
 */
public class CascadingMapTo extends CascadingDrillerBasePipeline implements UntypedTable {

    private Mapper outMapper;

    public CascadingMapTo() {
        super();
    }

    public CascadingMapTo(FlowContext flow, Schema schemaIn, Pipe pipeIn, Schema schemaOut, Mapper mapper) {
        super(flow, schemaIn, pipeIn, schemaOut);
        this.outMapper = mapper;
        this.outMapper.setInputSchema(schemaIn);
        this.outMapper.setOutputSchema(schemaOut);
    }

    Pipe buildOutputPipe() {
        return new Each(pipeIn, new MapFunction(this.outMapper));
    }

    @Override
    public UntypedGrouped groupBy(int i) {
        return new CascadingGroup(flow, schemaOut, buildOutputPipe(), i);
    }

    @Override
    public UntypedGrouped groupBy(String... columnNames) {
        return new CascadingGroup(flow, schemaOut, buildOutputPipe(), columnNames);
    }

    @Override
    public UntypedTable mapTo(Schema schema, Mapper mapper) {
        return new CascadingMapTo(flow, schemaIn, pipeIn, schema, new ReMapper(schemaIn, this.outMapper, schemaOut, mapper, schema));
    }

    @Override
    public UntypedTable storeAsText(String path, Mapper<Tuple, Tuple1<String>> mapper) {
        //return new CascadingTable(flow, schemaOut, buildOutputPipe());
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static class ReMapper extends Mapper {

        private Mapper a;
        private Mapper b;

        public ReMapper(Schema inputSchema, Mapper a, Schema intermediateSchema, Mapper b, Schema outputSchema) {
            this.inputSchema = inputSchema;
            this.outputSchema = outputSchema;

            this.a = a;
            a.setInputSchema(inputSchema);
            a.setOutputSchema(intermediateSchema);

            this.b = b;
            b.setInputSchema(intermediateSchema);
            b.setOutputSchema(outputSchema);
        }

        @Override
        public void map(final Tuple tuple, final Emitter e) {
            a.execute(tuple, new Emitter() {
                @Override
                public void emit(Tuple transformedByA) {
                    b.execute(transformedByA, e);
                }
            });
        }
    }

    public static class MapFunction extends BaseOperation implements Function, Serializable {

        private Mapper mapper;

        public MapFunction(Mapper outMapper) {
            super(getFields(outMapper));
            this.mapper = outMapper;
        }

        @Override
        public void operate(FlowProcess flowProcess, FunctionCall functionCall) {
            final TupleEntry input = functionCall.getArguments();
            final TupleEntryCollector outputCollector = functionCall.getOutputCollector();
            final Tuple tuple = CascTool.toDrillerTuple(mapper.getInputSchema(), input.getTuple());
            mapper.map(tuple, mapper.new Emitter() {
                @Override
                public void emit(Tuple tuple) {
                    outputCollector.add(CascTool.toCascadingTuple(tuple));
                }
            });
        }

        public static Fields getFields(Mapper mapper) {
            List fieldNames = mapper.getOutputSchema().getColumnNames();
            Comparable[] objects = (Comparable[]) fieldNames.toArray(new Comparable[fieldNames.size()]);
            return new Fields(objects);
        }
    }

}
