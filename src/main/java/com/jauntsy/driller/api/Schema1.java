package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:32 PM
 */
public class Schema1<A> extends TypedSchema<Tuple1<A>, Tuple1<A>, Table1<A>, Schema1<A>> {

    public Schema1() {
        super(1, null);
    }

    public Schema1(String aName, Class<A> aClass) {
        this(Column.from(aName, aClass));
    }

    public Schema1(Column<A> a) {
        super(1, a);
    }

    @Override
    public <Z> Schema2<A,Z> add(String columnName, Class<Z> columnType) {
        return (Schema2<A, Z>) new Schema2<A,Z>(
                get1stColumn(),
                Column.from(columnName, columnType)
        );
    }

    public Column<A> get1stColumn() {
        return getColumn(1);
    }

}
