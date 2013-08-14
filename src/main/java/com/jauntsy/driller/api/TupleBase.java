package com.jauntsy.driller.api;

import java.util.Arrays;
import java.util.List;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 12:52 PM
 */
class TupleBase<CommonTupleType extends Tuple,TupleType extends TupleBase> extends Tuple<CommonTupleType,TupleType> {

    private String[] columnNames;
    private Object[] values;

    public TupleBase(Object... values) {
        this.values = values;
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public Object get(int column) {
        return values[column - 1];
    }

    @Override
    public void set(int column, Object value) {
        values[column - 1] = value;
    }

    @Override
    protected TupleType setAll(List items) {
        assert items.size() == values.length;
        for (int i = 0; i < values.length; i++)
            values[i] = items.get(i);
        return (TupleType) this;
    }

    @Override
    protected TupleType setAll(Object[] items) {
        assert items.length == values.length;
        for (int i = 0; i < values.length; i++)
            values[i] = items[i];
        return (TupleType) this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TupleBase)) return false;

        TupleBase that = (TupleBase) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(values, that.values)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return values != null ? Arrays.hashCode(values) : 0;
    }
}
