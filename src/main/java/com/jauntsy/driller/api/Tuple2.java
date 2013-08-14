package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 10:36 AM
 */
public class Tuple2<A,B> extends TupleBase<Tuple2<A,B>,Tuple2<A,B>> {

    public Tuple2(A a, B b) {
        super(a, b);
    }

    public A get1st() { return (A)get(1); }
    public B get2nd() { return (B)get(2); }

    public void set1st(A a) { set(1, a); }
    public void set2nd(B b) { set(2, b); }

}
