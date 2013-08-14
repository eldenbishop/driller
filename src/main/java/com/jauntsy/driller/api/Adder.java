package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 8/9/13
 * Time: 3:49 PM
 */
public abstract class Adder<K extends Tuple, T extends Tuple> extends Combiner<K,T> {

    private T zero;

    public Adder(T zero) {
        this.zero = zero;
    }

    @Override
    public T combine(K key, Tuples<T> values) {
        T a = zero;
        while (values.hasNext())
            a = add(a, values.next());
        return a;
    }

    public abstract T add(T a, T b);

}
