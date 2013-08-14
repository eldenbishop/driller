package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:57 PM
 */
public class Table3<A,B,C> extends TableBase<Tuple3<A,B,C>,Tuple3<A,B,C>,Table3<A,B,C>> {

    Table3(UntypedTable backingTable) {
        super(backingTable);
    }

    public Grouped3<A,B,C,Tuple0, Tuple3<A,B,C>> groupBy0() {
        return (Grouped3<A, B, C, Tuple0, Tuple3<A, B, C>>) groupBy(0);
    }

    public Grouped3<A, B, C, Tuple1<A>, Tuple2<B, C>> groupBy1() {
        return (Grouped3<A, B, C, Tuple1<A>, Tuple2<B,C>>) groupBy(1);
    }

    public Grouped3<A, B, C, Tuple2<A, B>, Tuple1<C>> groupBy2() {
        return (Grouped3<A, B, C, Tuple2<A, B>, Tuple1<C>>) groupBy(2);
    }

    public Grouped3<A,B,C,Tuple3<A,B,C>, Tuple0> groupBy3() {
        return (Grouped3<A, B, C, Tuple3<A, B, C>, Tuple0>) groupBy(3);
    }

}
