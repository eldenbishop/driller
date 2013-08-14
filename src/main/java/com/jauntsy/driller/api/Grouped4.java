package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 4:37 PM
 */
public class Grouped4<A,B,C,D,KT extends Tuple,VT extends Tuple> extends GroupedBase<Table4<A,B,C,D>,Tuple4<A,B,C,D>,KT,VT,Grouped4<A,B,C,D,KT,VT>> {

    Grouped4(UntypedGrouped impl) {
        super(impl);
    }
}
