package com.jauntsy.driller.api;

import com.jauntsy.driller.op.Select;

/**
 * User: ebishop
 * Date: 6/19/12
 * Time: 7:41 PM
 */
public class TableBase<CommonType extends Tuple,TupleType extends Tuple,TableType extends TableBase> extends Table<CommonType,TupleType,TableType> {

    private UntypedTable _impl;

    public TableBase(UntypedTable backingTable) {
        this._impl = backingTable;
    }

    @Override
    public Schema<CommonType,TupleType, TableType, ?> getSchema() {
        return _impl.getSchema();
    }

    @Override
    public Grouped groupBy(int i) {
        return GroupedBase.wrap(_impl.groupBy(i));
    }

    @Override
    public Grouped groupBy(String... columnNames) {
        return GroupedBase.wrap(_impl.groupBy(columnNames));
    }

    @Override
    public <
            DTCommon extends Tuple,
            DTTuple extends Tuple,
            DTTable extends Table,
            DTSchema extends Schema
    > DTTable mapTo(Schema<DTCommon, DTTuple, DTTable, DTSchema> schema, Mapper<? super TupleType, DTTuple> mapper) {
        return (DTTable) TableBase.wrap(_impl.mapTo(schema, mapper));
    }

    @Override
    public <DTTuple extends Tuple, DTTable extends Table, DTSchema extends Schema> DTTable mapTo(Schema<CommonType,DTTuple, DTTable, DTSchema> schema) {
        return (DTTable) TableBase.wrap(_impl.mapTo(schema, new Select()));
    }

    @Override
    public Table1<String> storeAsText(String path, Mapper<? super TupleType, Tuple1<String>> mapper) {
        return (Table1<String>)TableBase.wrap(_impl.storeAsText(path, (Mapper)mapper));
    }

    public static TableBase wrap(UntypedTable impl) {
        switch (impl.size()) {
            case 0: return new Table0(impl);
            case 1: return new Table1(impl);
            case 2: return new Table2(impl);
            case 3: return new Table3(impl);
            case 4: return new Table4(impl);
            case 5: return new Table5(impl);
            case 6: return new Table6(impl);
            case 7: return new Table7(impl);
            case 8: return new Table8(impl);
            default: return new TableN(impl);
        }
    }

    public UntypedTable getImpl() {
        return _impl;
    }

    protected void checkGroup(String... columns) {
        if (columns.length > getSchema().size()) throw new IllegalArgumentException("TODO");
        for (int i = 0; i < columns.length; i++) {
            if (!columns[i].equals(getSchema().getColumnName(i))) throw new IllegalArgumentException("TODO");
        }
    }
}