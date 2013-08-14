package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:57 PM
 */
public class Table8<A,B,C,D,E,F,G,H> extends TableBase<Tuple8<A,B,C,D,E,F,G,H>,Tuple8<A,B,C,D,E,F,G,H>,Table8<A,B,C,D,E,F,G,H>> {

    Table8(UntypedTable backingTable) {
        super(backingTable);
    }

    public Grouped8<A, B, C, D, E, F, G, H, Tuple0, Tuple8<A, B, C, D, E, F, G, H>> groupBy0() {
        return (Grouped8<A, B, C, D, E, F, G, H, Tuple0, Tuple8<A, B, C, D, E, F, G, H>>) groupBy(0);
    }

    public Grouped8<A, B, C, D, E, F, G, H, Tuple1<A>, Tuple7<B, C, D, E, F, G, H>> groupBy1() {
        return (Grouped8<A, B, C, D, E, F, G, H, Tuple1<A>, Tuple7<B, C, D, E, F, G, H>>) groupBy(1);
    }

    public Grouped8<A, B, C, D, E, F, G, H, Tuple2<A, B>, Tuple6<C, D, E, F, G, H>> groupBy2() {
        return (Grouped8<A, B, C, D, E, F, G, H, Tuple2<A, B>, Tuple6<C, D, E, F, G, H>>) groupBy(2);
    }

    public Grouped8<A, B, C, D, E, F, G, H, Tuple3<A, B, C>, Tuple5<D, E, F, G, H>> groupBy3() {
        return (Grouped8<A, B, C, D, E, F, G, H, Tuple3<A, B, C>, Tuple5<D, E, F, G, H>>) groupBy(3);
    }

    public Grouped8<A, B, C, D, E, F, G, H, Tuple4<A, B, C, D>, Tuple4<E, F, G, H>> groupBy4() {
        return (Grouped8<A, B, C, D, E, F, G, H, Tuple4<A, B, C, D>, Tuple4<E, F, G, H>>) groupBy(4);
    }

    public Grouped8<A, B, C, D, E, F, G, H, Tuple5<A, B, C, D, E>, Tuple3<F, G, H>> groupBy5() {
        return (Grouped8<A, B, C, D, E, F, G, H, Tuple5<A, B, C, D, E>, Tuple3<F, G, H>>) groupBy(5);
    }

    public Grouped8<A, B, C, D, E, F, G, H, Tuple6<A, B, C, D, E, F>, Tuple2<G, H>> groupBy6() {
        return (Grouped8<A, B, C, D, E, F, G, H, Tuple6<A, B, C, D, E, F>, Tuple2<G, H>>) groupBy(6);
    }

    public Grouped8<A, B, C, D, E, F, G, H, Tuple7<A, B, C, D, E, F, G>, Tuple1<H>> groupBy7() {
        return (Grouped8<A, B, C, D, E, F, G, H, Tuple7<A, B, C, D, E, F, G>, Tuple1<H>>) groupBy(7);
    }

    public Grouped8<A, B, C, D, E, F, G, H, Tuple8<A, B, C, D, E, F, G, H>, Tuple0> groupBy8() {
        return (Grouped8<A, B, C, D, E, F, G, H, Tuple8<A, B, C, D, E, F, G, H>, Tuple0>) groupBy(8);
    }

}
