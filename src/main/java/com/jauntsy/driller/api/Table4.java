package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:57 PM
 */
public class Table4<A,B,C,D> extends TableBase<Tuple4<A,B,C,D>,Tuple4<A,B,C,D>,Table4<A,B,C,D>> {

    Table4(UntypedTable backingTable) {
        super(backingTable);
    }

    public Grouped4<A, B, C, D, Tuple0, Tuple4<A, B, C, D>> groupBy0() {
        return (Grouped4<A, B, C, D, Tuple0, Tuple4<A, B, C, D>>) groupBy(0);
    }

    public Grouped4<A, B, C, D, Tuple1<A>, Tuple3<B, C, D>> groupBy1() {
        return (Grouped4<A, B, C, D, Tuple1<A>, Tuple3<B, C, D>>) groupBy(1);
    }

    public Grouped4<A, B, C, D, Tuple2<A, B>, Tuple2<C, D>> groupBy2() {
        return (Grouped4<A, B, C, D, Tuple2<A, B>, Tuple2<C, D>>) groupBy(2);
    }

    public Grouped4<A, B, C, D, Tuple3<A, B, C>, Tuple1<D>> groupBy3() {
        return (Grouped4<A, B, C, D, Tuple3<A, B, C>, Tuple1<D>>) groupBy(3);
    }

    public Grouped4<A, B, C, D, Tuple4<A, B, C, D>, Tuple0> groupBy4() {
        return (Grouped4<A, B, C, D, Tuple4<A, B, C, D>, Tuple0>) groupBy(4);
    }

}
