package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 7/6/12
 * Time: 11:19 AM
 */
public interface OrderedGroup<TableType extends Table, TupleType extends Tuple, KeyTupleType extends Tuple, ValueTupleType extends Tuple, GroupType extends Grouped> extends Grouped<TableType, TupleType, KeyTupleType, ValueTupleType, GroupType> {
}
