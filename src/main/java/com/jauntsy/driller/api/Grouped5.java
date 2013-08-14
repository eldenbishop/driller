package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 4:37 PM
 */
public class Grouped5<A,B,C,D,E,KT extends Tuple,VT extends Tuple> extends GroupedBase<Table5<A,B,C,D,E>,Tuple5<A,B,C,D,E>,KT,VT,Grouped5<A,B,C,D,E,KT,VT>> {

    Grouped5(UntypedGrouped impl) {
        super(impl);
    }
}
