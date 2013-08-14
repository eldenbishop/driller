package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/20/12
 * Time: 9:48 AM
 */
public interface UntypedGrouped {

    UntypedTable ungroup();

    UntypedGrouped combine(Combiner combiner);

    UntypedTable reduce(Schema schema, Reducer reducer);

    int size();

    Schema getSchema();

}
