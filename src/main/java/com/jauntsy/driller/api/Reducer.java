package com.jauntsy.driller.api;

import java.io.Serializable;
import java.util.Iterator;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 4:40 PM
 */
public abstract class Reducer<
        KeyTupleType extends Tuple,
        SrcTupleType extends Tuple,
        DstTupleType extends Tuple
> extends BaseOperation<
        SrcTupleType,
        Schema<?,SrcTupleType,?,?>,
        DstTupleType,
        Schema<?,DstTupleType,?,?>
> {

    protected Schema<?,KeyTupleType,?,?> keySchema;

    public abstract void reduce(KeyTupleType group, Tuples<SrcTupleType> tuples, Emitter<DstTupleType> e);

    public void setKeySchema(Schema<?,KeyTupleType,?,?> keySchema) {
        this.keySchema = keySchema;
    }

}
