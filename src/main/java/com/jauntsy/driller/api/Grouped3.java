package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 4:37 PM
 */
public class Grouped3<A,B,C,KT extends Tuple,VT extends Tuple> extends GroupedBase<Table3<A,B,C>,Tuple3<A,B,C>,KT,VT,Grouped3<A,B,C,KT,VT>> {

    Grouped3(UntypedGrouped impl) {
        super(impl);
    }

}
