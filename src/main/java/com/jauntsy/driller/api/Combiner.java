package com.jauntsy.driller.api;

import java.util.Iterator;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 4:12 PM
 */
public abstract class Combiner<
        KeyType extends Tuple,
        ValueType extends Tuple
> extends BaseOperation<
        ValueType,
        Schema<?,ValueType,?,?>,
        ValueType,
        Schema<?,ValueType,?,?>
        > {

    private Schema<?,KeyType,?,?> keySchema;

    public abstract ValueType combine(KeyType key, Tuples<ValueType> values);

    public void setKeySchema(Schema keySchema) {
        this.keySchema = keySchema;
    }

    public void setValueSchema(Schema valueSchema) {
        setInputSchema(valueSchema);
        setOutputSchema(valueSchema);
    }

}
