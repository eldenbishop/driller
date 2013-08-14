package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 10:36 AM
 */
public class Tuple5<A,B,C,D,E> extends TupleBase<Tuple5<A,B,C,D,E>,Tuple5<A,B,C,D,E>> {

    public Tuple5(A a, B b, C c, D d, E e) {
        super(a, b, c, d, e);
    }

    public A get1st() { return (A)get(1); }
    public B get2nd() { return (B)get(2); }
    public C get3rd() { return (C)get(3); }
    public D get4th() { return (D)get(4); }
    public E get5th() { return (E)get(5); }

    public void set1st(A a) { set(1, a); }
    public void set2nd(B b) { set(2, b); }
    public void set3nd(C c) { set(2, c); }
    public void set4nd(D d) { set(2, d); }
    public void set5nd(E e) { set(2, e); }

}
