package com.jauntsy.driller.api;

import java.util.Iterator;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 6:09 PM
 */
public abstract class SimpleCombiner<ValueType extends Tuple> extends Combiner<Tuple,ValueType> {

    @Override
    public ValueType combine(Tuple group, Tuples<ValueType> tuples) {
        ValueType total = tuples.next();
        while (tuples.hasNext())
            total = combine(total, tuples.next());
        return total;
    }

    public abstract ValueType combine(ValueType a, ValueType b);

}
