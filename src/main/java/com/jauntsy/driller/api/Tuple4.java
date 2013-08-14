package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 10:36 AM
 */
public class Tuple4<A,B,C,D> extends TupleBase<Tuple4<A,B,C,D>,Tuple4<A,B,C,D>> {

    public Tuple4(A a, B b, C c, D d) {
        super(a, b, c, d);
    }

    public A get1st() { return (A)get(1); }
    public B get2nd() { return (B)get(2); }
    public C get3rd() { return (C)get(3); }
    public D get4th() { return (D)get(4); }

    public void set1st(A a) { set(1, a); }
    public void set2nd(B b) { set(2, b); }
    public void set3nd(C c) { set(2, c); }
    public void set4nd(D d) { set(2, d); }

}
