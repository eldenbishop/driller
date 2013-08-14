package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 4:37 PM
 */
public class Grouped1<A,G extends Tuple,V extends Tuple> extends GroupedBase<Table1<A>,Tuple1<A>,G,V,Grouped1<A,G,V>> {

    Grouped1(UntypedGrouped impl) {
        super(impl);
    }

}
