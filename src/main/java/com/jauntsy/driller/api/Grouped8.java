package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 4:37 PM
 */
public class Grouped8<A,B,C,D,E,F,G,H,KT extends Tuple,VT extends Tuple> extends GroupedBase<Table8<A,B,C,D,E,F,G,H>,Tuple8<A,B,C,D,E,F,G,H>,KT,VT,Grouped8<A,B,C,D,E,F,G,H,KT,VT>> {

    Grouped8(UntypedGrouped impl) {
        super(impl);
    }
}
