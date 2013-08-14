package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 3:45 PM
 */
public abstract class Mapper<S extends Tuple, D extends Tuple> extends BaseOperation<S,Schema<?,S,?,?>,D,Schema<?,D,?,?>> {

    protected Schema<?,S,?,?> inputSchema;
    protected Schema<?,D,?,?> outputSchema;

    public abstract void map(S tuple, Emitter e);
    private transient EmitterProxy notThreadSafeProxy = new EmitterProxy();

    public void execute(final S tupleIn, final Emitter e) {
        inputSchema.bind(tupleIn);
        if (notThreadSafeProxy == null) {
            notThreadSafeProxy = new EmitterProxy();
        }
        notThreadSafeProxy.e = e;
        map(tupleIn, notThreadSafeProxy);
    }

    public abstract class Emitter implements com.jauntsy.driller.api.Emitter<D> {
        public abstract void emit(D e);
    }

    private class EmitterProxy extends Emitter {
        Emitter e;
        @Override
        public void emit(D tupleOut) {
            outputSchema.bind(tupleOut);
            e.emit(tupleOut);
        }
    }

    public Schema<?,S, ?, ?> getInputSchema() {
        return inputSchema;
    }

    public void setInputSchema(Schema<?, S, ?, ?> inputSchema) {
        this.inputSchema = inputSchema;
    }

    public Schema<?,D, ?, ?> getOutputSchema() {
        return outputSchema;
    }

    public void setOutputSchema(Schema<?, D, ?, ?> outputSchema) {
        this.outputSchema = outputSchema;
    }
}
