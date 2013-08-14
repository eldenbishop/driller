package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:57 PM
 */
public class Table0 extends TableBase<Tuple0,Tuple0,Table0> {

    Table0(UntypedTable backingTable) {
        super(backingTable);
    }

    public Grouped0<Tuple0, Tuple0> groupBy0() {
        return (Grouped0<Tuple0, Tuple0>) groupBy(0);
    }



}
