package com.jauntsy.driller.api;

import java.util.List;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:32 PM
 */
public class SchemaN extends TypedSchema<Tuple,Tuple, TableN, SchemaN> {

    SchemaN(Column... columns) {
        super(columns.length, columns);
    }

    @Override
    public <Z> SchemaN add(String columnName, Class<Z> columnType) {
        List<Column> columns = getColumns();
        columns.add(Column.from(columnName, columnType));
        return (SchemaN)Schema.ofN(columns);
    }

}
