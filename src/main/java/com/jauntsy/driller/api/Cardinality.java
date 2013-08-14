package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/25/12
 * Time: 1:27 PM
 */
public interface Cardinality<DeclaredType> {

    public interface Of0 extends Cardinality<Of0> {}
    public interface Of1<A> extends Cardinality<Of1<A>> {}
    public interface Of2<A,B> extends Cardinality<Of2<A,B>> {}
    public interface Of3<A,B,C> extends Cardinality<Of3<A,B,C>> {}
    public interface Of4<A,B,C,D> extends Cardinality<Of4<A,B,C,D>> {}
    public interface Of5<A,B,C,D,E> extends Cardinality<Of5<A,B,C,D,E>> {}
    public interface Of6<A,B,C,D,E,F> extends Cardinality<Of6<A,B,C,D,E,F>> {}
    public interface Of7<A,B,C,D,E,F,G> extends Cardinality<Of7<A,B,C,D,E,F,G>> {}
    public interface Of8<A,B,C,D,E,F,G,H> extends Cardinality<Of8<A,B,C,D,E,F,G,H>> {}
    public interface OfN extends Cardinality<OfN> {}

}
