package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:57 PM
 */
public class Table5<A,B,C,D,E> extends TableBase<Tuple5<A,B,C,D,E>,Tuple5<A,B,C,D,E>,Table5<A,B,C,D,E>> {

    Table5(UntypedTable backingTable) {
        super(backingTable);
    }

    public Grouped5<A, B, C, D, E, Tuple0, Tuple5<A, B, C, D, E>> groupBy0() {
        return (Grouped5<A, B, C, D, E, Tuple0, Tuple5<A, B, C, D, E>>) groupBy(0);
    }

    public Grouped5<A, B, C, D, E, Tuple1<A>, Tuple4<B, C, D, E>> groupBy1() {
        return (Grouped5<A, B, C, D, E, Tuple1<A>, Tuple4<B, C, D, E>>) groupBy(1);
    }

    public Grouped5<A, B, C, D, E, Tuple2<A, B>, Tuple3<C, D, E>> groupBy2() {
        return (Grouped5<A, B, C, D, E, Tuple2<A, B>, Tuple3<C, D, E>>) groupBy(2);
    }

    public Grouped5<A, B, C, D, E, Tuple3<A, B, C>, Tuple2<D, E>> groupBy3() {
        return (Grouped5<A, B, C, D, E, Tuple3<A, B, C>, Tuple2<D, E>>) groupBy(3);
    }

    public Grouped5<A, B, C, D, E, Tuple4<A, B, C, D>, Tuple1<E>> groupBy4() {
        return (Grouped5<A, B, C, D, E, Tuple4<A, B, C, D>, Tuple1<E>>) groupBy(4);
    }

    public Grouped5<A, B, C, D, E, Tuple5<A, B, C, D, E>, Tuple0> groupBy5() {
        return (Grouped5<A, B, C, D, E, Tuple5<A, B, C, D, E>, Tuple0>) groupBy(5);
    }

}
