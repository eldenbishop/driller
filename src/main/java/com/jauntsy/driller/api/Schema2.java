package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:32 PM
 */
public class Schema2<A,B> extends TypedSchema<Tuple2<A,B>, Tuple2<A,B>, Table2<A,B>, Schema2<A,B>> {

    public Schema2() {
        super(2, null, null);
    }

    public Schema2(
            String aName, Class<A> aClass,
            String bName, Class<B> bClass
    ) {
        this(
                Column.from(aName, aClass),
                Column.from(bName, bClass)
        );
    }

    public Schema2(Column<A> a, Column<B> b) {
        super(2, a, b);
    }

    @Override
    public <Z> Schema3<A,B,Z> add(String columnName, Class<Z> columnType) {
        return new Schema3<A,B,Z>(
                get1stColumn(),
                get2ndColumn(),
                Column.from(columnName, columnType)
        );
    }

    public Column<A> get1stColumn() {
        return getColumn(1);
    }

    public Column<B> get2ndColumn() {
        return getColumn(2);
    }

}
