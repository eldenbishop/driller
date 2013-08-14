package com.jauntsy.driller.api;

import java.io.Serializable;

/**
 * User: ebishop
 * Date: 6/21/12
 * Time: 5:51 PM
 */
public class BaseOperation<
        SrcTupleType extends Tuple,
        SrcSchemaType extends Schema<?,SrcTupleType,?,?>,
        DstTupleType extends Tuple,
        DstSchemaType extends Schema<?,DstTupleType,?,?>
        > implements Serializable {

    protected SrcSchemaType inputSchema;
    protected DstSchemaType outputSchema;

    public void setInputSchema(SrcSchemaType inputSchema) {
        this.inputSchema = inputSchema;
    }

    public SrcSchemaType getInputSchema() {
        return this.inputSchema;
    }

    public void setOutputSchema(DstSchemaType outputSchema) {
        this.outputSchema = outputSchema;
    }

    public DstSchemaType getOutputSchema() {
        return this.outputSchema;
    }

}
