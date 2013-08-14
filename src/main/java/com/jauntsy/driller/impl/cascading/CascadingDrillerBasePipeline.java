package com.jauntsy.driller.impl.cascading;

import cascading.pipe.Pipe;
import com.jauntsy.driller.api.Schema;

/**
 * User: ebishop
 * Date: 6/21/12
 * Time: 12:19 PM
 */
public class CascadingDrillerBasePipeline {

    protected FlowContext flow;
    protected Schema schemaIn;
    protected Pipe pipeIn;
    protected Schema schemaOut;

    @Deprecated
    protected CascadingDrillerBasePipeline() {

    }

    public CascadingDrillerBasePipeline(FlowContext flow, Schema schemaIn, Pipe pipeIn, Schema schemaOut) {
        this.flow = flow;
        this.schemaIn = schemaIn;
        this.pipeIn = pipeIn;
        this.schemaOut = schemaOut;
    }

    public Schema getSchema() {
        return schemaOut;
    }

    public int size() {
        return schemaOut.size();
    }

}
