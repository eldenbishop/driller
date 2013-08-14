package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 4:37 PM
 */
public class Grouped2<A,B,K extends Tuple,V extends Tuple> extends GroupedBase<Table2<A,B>,Tuple2<A,B>,K,V,Grouped2<A,B,K,V>> {

    Grouped2(UntypedGrouped impl) {
        super(impl);
    }

}
