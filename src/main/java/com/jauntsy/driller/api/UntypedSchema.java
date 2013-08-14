package com.jauntsy.driller.api;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ebishop
 * Date: 6/20/12
 * Time: 9:56 AM
 */
public class UntypedSchema {

    Schema.Column[] columns;

    public UntypedSchema(Schema.Column... columns) {
        this.columns = columns;
    }

    public UntypedSchema(UntypedSchema baseSchema, Schema.Column... newColumns) {
        columns = new Schema.Column[baseSchema.columns.length + (newColumns == null ? 0 : newColumns.length)];
        int i = 0;
        for (Schema.Column c : baseSchema.columns)
            columns[i++] = c;
        if (newColumns != null) {
            for (Schema.Column c : newColumns)
                columns[i++] = c;
        }

    }

    public UntypedSchema add(String columnName, Class columnType) {
        return new UntypedSchema(this, Schema.Column.from(columnName, columnType));
    }

    public List<String> getColumnNames() {
        List<String> rval = new ArrayList<String>();
        for (Schema.Column column : columns)
            rval.add(column.getName());
        return rval;
    }

    public int size() {
        return columns.length;
    }

    public Tuple newTuple() {
        return Tuple.ofN(columns);
    }

    public Schema.Column getColumn(int column) {
        return columns[column];
    }

    public Schema.Column[] getColumns() {
        return columns;
    }
}
