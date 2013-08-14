package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:32 PM
 */
public class Schema5<A,B,C,D,E> extends TypedSchema<Tuple5<A,B,C,D,E>, Tuple5<A,B,C,D,E>, Table5<A,B,C,D,E>, Schema5<A,B,C,D,E>> {

    Schema5(
            String aName, Class<A> aClass,
            String bName, Class<B> bClass,
            String cName, Class<C> cClass,
            String dName, Class<D> dClass,
            String eName, Class<E> eClass
    ) {
        this(
                Column.from(aName, aClass),
                Column.from(bName, bClass),
                Column.from(cName, cClass),
                Column.from(dName, dClass),
                Column.from(eName, eClass)
        );
    }

    Schema5(Column<A> a, Column<B> b, Column<C> c, Column<D> d, Column<E> e) {
        super(5, a, b, c, d, e);
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

    public Column<E> get5thColumn() {
        return getColumn(5);
    }

    @Override
    public <Z> Schema6<A,B,C,D,E,Z> add(String columnName, Class<Z> columnType) {
        return new Schema6<A,B,C,D,E,Z>(
                get1stColumn(),
                get2ndColumn(),
                get3rdColumn(),
                get4thColumn(),
                get5thColumn(),
                Column.from(columnName, columnType)
        );
    }
}
