package com.jauntsy.driller.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 12:41 PM
 */
public
        abstract
        class TypedSchema<CommonType extends Tuple, TupleType extends Tuple, TableType extends Table, SchemaType extends TypedSchema>
        extends Schema<CommonType, TupleType, TableType, SchemaType> {

    int size;
    Column[] columns;
    String[] columnNames;

    TypedSchema(int size, Column... columns) {
        this.size = size;
        this.columns = columns;
        this.columnNames = new String[columns.length];
        for (int i = 0; i < columns.length; i++)
            columnNames[i] = columns[i].getName();
    }

    public TupleType newTuple() {
        return (TupleType) Tuple.size(size());
    }

    public int size() {
        return size;
    }

    public void bind(Tuple t) {
        t.columnNames = columnNames;
    }

    @Override
    public List<Column> getColumns() {
        return Arrays.asList(columns);
    }

    @Override
    public Column getColumn(int column) {
        return columns[column - 1];
    }

    @Override
    public Column getColumn(String column) {
        for (Column c : columns)
            if (column.equals(c.getName()))
                return c;
        return null;
    }

    @Override
    public int getColumnIndex(String column) {
        for (int i = 0; i < columns.length; i++)
            if (column.equals(columns[i]))
                return i + 1;
        return -1;
    }

    @Override
    public List<String> getColumnNames() {
        List<String> columnNames = new ArrayList<String>();
        for (Column c : columns) {
            columnNames.add(c.getName());
        }
        return columnNames;
    }

    @Override
    public String getColumnName(int column) {
        return getColumn(column).getName();
    }

    @Override
    public Class getColumnType(int column) {
        return getColumn(column).getType();
    }

    @Override
    public Class getColumnType(String column) {
        return getColumn(column).getType();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Schema(size:");
        sb.append(size);
        for (Column c : columns)
            sb.append(",").append(c.getName()).append(":").append(c.getType().getSimpleName());
        sb.append(")");
        return sb.toString();
    }
}
