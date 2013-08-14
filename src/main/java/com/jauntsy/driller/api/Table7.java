package com.jauntsy.driller.api;

public class Table7<A, B, C, D, E, F, G> extends TableBase<Tuple7<A, B, C, D, E, F, G>,Tuple7<A, B, C, D, E, F, G>,Table7<A, B, C, D, E, F, G>> {

    Table7(UntypedTable backingTable) {
        super(backingTable);
    }

    public Grouped7<A, B, C, D, E, F, G, Tuple0, Tuple7<A, B, C, D, E, F, G>> groupBy0() {
        return (Grouped7<A, B, C, D, E, F, G, Tuple0, Tuple7<A, B, C, D, E, F, G>>) groupBy(0);
    }

    public Grouped7<A, B, C, D, E, F, G, Tuple1<A>, Tuple6<B, C, D, E, F, G>> groupBy1() {
        return (Grouped7<A, B, C, D, E, F, G, Tuple1<A>, Tuple6<B, C, D, E, F, G>>) groupBy(1);
    }

    public Grouped7<A, B, C, D, E, F, G, Tuple2<A, B>, Tuple5<C, D, E, F, G>> groupBy2() {
        return (Grouped7<A, B, C, D, E, F, G, Tuple2<A, B>, Tuple5<C, D, E, F, G>>) groupBy(2);
    }

    public Grouped7<A, B, C, D, E, F, G, Tuple3<A, B, C>, Tuple4<D, E, F, G>> groupBy3() {
        return (Grouped7<A, B, C, D, E, F, G, Tuple3<A, B, C>, Tuple4<D, E, F, G>>) groupBy(3);
    }

    public Grouped7<A, B, C, D, E, F, G, Tuple4<A, B, C, D>, Tuple3<E, F, G>> groupBy4() {
        return (Grouped7<A, B, C, D, E, F, G, Tuple4<A, B, C, D>, Tuple3<E, F, G>>) groupBy(4);
    }

    public Grouped7<A, B, C, D, E, F, G, Tuple5<A, B, C, D, E>, Tuple2<F, G>> groupBy5() {
        return (Grouped7<A, B, C, D, E, F, G, Tuple5<A, B, C, D, E>, Tuple2<F, G>>) groupBy(5);
    }

    public Grouped7<A, B, C, D, E, F, G, Tuple6<A, B, C, D, E, F>, Tuple1<G>> groupBy6() {
        return (Grouped7<A, B, C, D, E, F, G, Tuple6<A, B, C, D, E, F>, Tuple1<G>>) groupBy(6);
    }

    public Grouped7<A, B, C, D, E, F, G, Tuple7<A, B, C, D, E, F, G>, Tuple0> groupBy7() {
        return (Grouped7<A, B, C, D, E, F, G, Tuple7<A, B, C, D, E, F, G>, Tuple0>) groupBy(7);
    }

}