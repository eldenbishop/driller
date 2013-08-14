package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:32 PM
 */
public class Schema3<A,B,C> extends TypedSchema<Tuple3<A,B,C>, Tuple3<A,B,C>, Table3<A,B,C>, Schema3<A,B,C>> {

    @Deprecated
    public Schema3() {
        super(3, null, null, null);
    }

    Schema3(
            String aName, Class<A> aClass,
            String bName, Class<B> bClass,
            String cName, Class<C> cClass
    ) {
        this(
                Column.from(aName, aClass),
                Column.from(bName, bClass),
                Column.from(cName, cClass)
        );
    }

    Schema3(Column<A> a, Column<B> b, Column<C> c) {
        super(3, a, b, c);
    }

    @Override
    public <Z> Schema4<A,B,C,Z> add(String columnName, Class<Z> columnType) {
        return new Schema4(
                get1stColumn(),
                get2ndColumn(),
                get3rdColumn(),
                Column.from(columnName, columnType)
        );
    }

    public Column<A> get1stColumn() {
        return getColumn(1);
    }

    public Column<B> get2ndColumn() {
        return getColumn(2);
    }

    public Column<C> get3rdColumn() {
        return getColumn(3);
    }
}
