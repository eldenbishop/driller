package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:57 PM
 */
public class Table1<A> extends TableBase<Tuple1<A>,Tuple1<A>,Table1<A>> {

    Table1(UntypedTable backingTable) {
        super(backingTable);
    }

    public Grouped1<A,Tuple0, Tuple1<A>> groupBy0() {
        return (Grouped1<A, Tuple0, Tuple1<A>>) groupBy(0);
    }

    public Grouped1<A,Tuple1<A>, Tuple0> groupBy1() {
        return (Grouped1<A, Tuple1<A>, Tuple0>) groupBy(1);
    }

}
