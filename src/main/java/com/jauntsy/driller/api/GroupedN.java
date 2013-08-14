package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/19/12
 * Time: 7:16 PM
 */
public class GroupedN extends GroupedBase<Table,Tuple,Tuple,Tuple,Grouped> {

    GroupedN(UntypedGrouped impl) {
        super(impl);
        assert impl.size() > 8;
    }

}
