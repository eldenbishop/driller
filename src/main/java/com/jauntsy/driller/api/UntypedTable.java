package com.jauntsy.driller.api;

import java.io.Serializable;

/**
 * User: ebishop
 * Date: 6/20/12
 * Time: 9:54 AM
 */
public interface UntypedTable extends Serializable {

    Schema getSchema();

    UntypedGrouped groupBy(int i);

    UntypedGrouped groupBy(String... columnNames);

    UntypedTable mapTo(Schema schema, Mapper mapper);

    int size();

    UntypedTable storeAsText(String path, Mapper<Tuple,Tuple1<String>> mapper);

}
