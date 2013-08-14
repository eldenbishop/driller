package com.jauntsy.driller.api;

import com.jauntsy.driller.op.ColumnCombiner;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 4:37 PM
 static class Table<TableType extends Table,KeyTupleType extends Tuple, ValTupleType extends Tuple, KeyType extends Key> {

 */
public class GroupedBase
    <
        TableType extends Table,
        TupleType extends Tuple,
        KeyTupleType extends Tuple,
        ValueTupleType extends Tuple,
        GroupType extends Grouped
    >
        implements Grouped
    <
        TableType,
        TupleType,
        KeyTupleType,
        ValueTupleType,
        GroupType
    >
{

    protected UntypedGrouped _impl;

    protected GroupedBase(UntypedGrouped impl) {
        this._impl = impl;
    }

    @Override
    public
        <OutputTupleType extends Tuple, OutputTableType extends Table, OutputSchemaType extends Schema>
        OutputTableType
        reduce(
            Schema<?,OutputTupleType,
                    OutputTableType,
                    OutputSchemaType
            > schema,
            Reducer<? super KeyTupleType, ? super ValueTupleType, ? super OutputTupleType> reducer
    ) {
        return (OutputTableType) TableBase.wrap(_impl.reduce(schema, reducer));
    }

    @Override
    public Table1<String> storeAsText(String path, Mapper<TupleType, Tuple1<String>> mapper) {
        return ungroup().storeAsText(path, mapper);
    }

    @Override
    public GroupType combine(Combiner<? super KeyTupleType, ? super ValueTupleType> combiner) {
        return (GroupType) GroupedBase.wrap(_impl.combine(combiner));
    }

    @Override
    public GroupType add(Adder<? super KeyTupleType, ? super ValueTupleType> combiner) {
        return (GroupType) GroupedBase.wrap(_impl.combine(combiner));
    }

    @Deprecated
    @Override
    public TableType ungroup() {
        return (TableType) TableBase.wrap(_impl.ungroup());
    }

    static GroupedBase wrap(UntypedGrouped impl) {
        switch (impl.size()) {
            case 0: return new Grouped0(impl);
            case 1: return new Grouped1(impl);
            case 2: return new Grouped2(impl);
            case 3: return new Grouped3(impl);
            case 4: return new Grouped4(impl);
            case 5: return new Grouped5(impl);
            case 6: return new Grouped6(impl);
            case 7: return new Grouped7(impl);
            case 8: return new Grouped8(impl);
            default: return new GroupedN(impl);
        }
    }

}
