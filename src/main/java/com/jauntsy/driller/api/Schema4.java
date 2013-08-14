package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:32 PM
 */
public class Schema4<A,B,C,D> extends TypedSchema<Tuple4<A,B,C,D>, Tuple4<A,B,C,D>, Table4<A,B,C,D>, Schema4<A,B,C,D>> {

    Schema4(
            String aName, Class<A> aClass,
            String bName, Class<B> bClass,
            String cName, Class<C> cClass,
            String dName, Class<D> dClass
    ) {
        this(
                Column.from(aName, aClass),
                Column.from(bName, bClass),
                Column.from(cName, cClass),
                Column.from(dName, dClass)
        );
    }

    Schema4(Column<A> a, Column<B> b, Column<C> c, Column<D> d) {
        super(4, a, b, c, d);
    }

    @Override
    public <Z> Schema5<A,B,C,D,Z> add(String columnName, Class<Z> columnType) {
        return new Schema5<A,B,C,D,Z>(
                get1stColumn(),
                get2ndColumn(),
                get3rdColumn(),
                get4thColumn(),
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

    public Column<D> get4thColumn() {
        return getColumn(4);
    }
}
