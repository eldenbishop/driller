package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 10:30 AM
 */
public class Tuple1<A> extends TupleBase<Tuple1<A>,Tuple1<A>> {

    public Tuple1(A a) {
        super(a);
    }

    public A get1st() {
        return (A)get(1);
    }

}
