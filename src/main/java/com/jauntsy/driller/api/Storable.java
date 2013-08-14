package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/25/12
 * Time: 2:18 PM
 */
public interface Storable<TupleType extends Tuple> {

    public Table1<String> storeAsText(String path, Mapper<? super TupleType,Tuple1<String>> mapper);

}
