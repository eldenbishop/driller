package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:57 PM
 */
public class Table2<A,B> extends TableBase<Tuple2<A,B>,Tuple2<A,B>,Table2<A,B>> {

    Table2(UntypedTable backingTable) {
        super(backingTable);
    }

    public Grouped2<A,B,Tuple0, Tuple2<A,B>> groupBy0() {
        return (Grouped2<A, B, Tuple0, Tuple2<A, B>>) groupBy(0);
    }

    public Grouped2<A,B,Tuple1<A>, Tuple1<B>> groupBy1() {
        return (Grouped2<A, B, Tuple1<A>, Tuple1<B>>) groupBy(1);
    }

    public Grouped2<A,B,Tuple2<A,B>, Tuple0> groupBy2() {
        return (Grouped2<A, B, Tuple2<A, B>, Tuple0>) groupBy(2);
    }

    public Grouped2<A,B,Tuple0, Tuple2<A,B>> groupBy() {
        return groupBy0();
    }

    public Grouped2<A,B,Tuple1<A>, Tuple1<B>> groupBy(String columnA) {
        checkGroup(columnA);
        return groupBy1();
    }

    public Grouped2<A,B,Tuple2<A,B>, Tuple0> groupBy(String columnA, String columnB) {
        checkGroup(columnA, columnB);
        return groupBy2();
    }

}
