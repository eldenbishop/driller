package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:57 PM
 */
public class Table6<A,B,C,D,E,F> extends TableBase<Tuple6<A,B,C,D,E,F>,Tuple6<A,B,C,D,E,F>,Table6<A,B,C,D,E,F>> {

    Table6(UntypedTable backingTable) {
        super(backingTable);
    }

    public Grouped6<A, B, C, D, E, F, Tuple0, Tuple6<A, B, C, D, E, F>> groupBy0() {
        return (Grouped6<A, B, C, D, E, F, Tuple0, Tuple6<A, B, C, D, E, F>>) groupBy(0);
    }

    public Grouped6<A, B, C, D, E, F, Tuple1<A>, Tuple5<B, C, D, E, F>> groupBy1() {
        return (Grouped6<A, B, C, D, E, F, Tuple1<A>, Tuple5<B, C, D, E, F>>) groupBy(1);
    }

    public Grouped6<A, B, C, D, E, F, Tuple2<A, B>, Tuple4<C, D, E, F>> groupBy2() {
        return (Grouped6<A, B, C, D, E, F, Tuple2<A, B>, Tuple4<C, D, E, F>>) groupBy(2);
    }

    public Grouped6<A, B, C, D, E, F, Tuple3<A, B, C>, Tuple3<D, E, F>> groupBy3() {
        return (Grouped6<A, B, C, D, E, F, Tuple3<A, B, C>, Tuple3<D, E, F>>) groupBy(3);
    }

    public Grouped6<A, B, C, D, E, F, Tuple4<A, B, C, D>, Tuple2<E, F>> groupBy4() {
        return (Grouped6<A, B, C, D, E, F, Tuple4<A, B, C, D>, Tuple2<E, F>>) groupBy(4);
    }

    public Grouped6<A, B, C, D, E, F, Tuple5<A, B, C, D, E>, Tuple1<F>> groupBy5() {
        return (Grouped6<A, B, C, D, E, F, Tuple5<A, B, C, D, E>, Tuple1<F>>) groupBy(5);
    }

    public Grouped6<A, B, C, D, E, F, Tuple6<A, B, C, D, E, F>, Tuple0> groupBy6() {
        return (Grouped6<A, B, C, D, E, F, Tuple6<A, B, C, D, E, F>, Tuple0>) groupBy(6);
    }

}
