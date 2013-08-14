package com.jauntsy.driller.op;

import com.jauntsy.driller.api.*;

/**
 * User: ebishop
 * Date: 6/25/12
 * Time: 7:17 PM
 */
public abstract class ColumnCombiner<T> extends Combiner<Tuple,Tuple1<T>> {

    public static <A> Combiner<Tuple, Tuple1<A>> build(ColumnCombiner<A> a) {
        return null;
    }

    public static <A,B> Combiner<Tuple, Tuple2<A,B>> build(ColumnCombiner<A> a, ColumnCombiner<B> b) {
        return null;
    }

    public static <A,B,C> Combiner<Tuple, Tuple3<A,B,C>> build(ColumnCombiner<A> a, ColumnCombiner<B> b, ColumnCombiner<C> c) {
        return null;
    }

}
