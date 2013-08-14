package com.jauntsy.driller.api;

import com.jauntsy.driller.op.ColumnCombiner;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 4:37 PM
 static class Table<TableType extends Table,KeyTupleType extends Tuple, ValTupleType extends Tuple, KeyType extends Key> {

 */
public interface Grouped<TableType extends Table, TupleType extends Tuple, KeyTupleType extends Tuple, ValueTupleType extends Tuple, GroupType extends Grouped> {

    @Deprecated
    public TableType ungroup();

    public GroupType combine(Combiner<? super KeyTupleType, ? super ValueTupleType> combiner);

//    public GroupType combine(ColumnCombiner... columnCombiner);

    public <
            OutputTupleType extends Tuple,
            OutputTableType extends Table,
            OutputSchemaType extends Schema
            >
    OutputTableType reduce(
                Schema<?,OutputTupleType,OutputTableType,OutputSchemaType> schema,
                Reducer<? super KeyTupleType,? super ValueTupleType,? super OutputTupleType> reducer
    );

    public Table1<String> storeAsText(String path, Mapper<TupleType,Tuple1<String>> mapper);

}
