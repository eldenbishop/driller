package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:32 PM
 */
public class Schema0 extends TypedSchema<Tuple0, Tuple0, Table0, Schema0> {

    public Schema0() {
        super(0, new Column[0]);
    }

    @Override
    public <Z> Schema1<Z> add(String columnName, Class<Z> columnType) {
        return new Schema1<Z>(Column.from(columnName, columnType));
    }

}
