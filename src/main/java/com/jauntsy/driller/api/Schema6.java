package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:32 PM
 */
public class Schema6<A,B,C,D,E,F> extends TypedSchema<Tuple6<A,B,C,D,E,F>, Tuple6<A,B,C,D,E,F>, Table6<A,B,C,D,E,F>, Schema6<A,B,C,D,E,F>> {

    public Schema6(
            String aName, Class<A> aClass,
            String bName, Class<B> bClass,
            String cName, Class<C> cClass,
            String dName, Class<D> dClass,
            String eName, Class<E> eClass,
            String fName, Class<F> fClass
    ) {
        this(
                Column.from(aName, aClass),
                Column.from(bName, bClass),
                Column.from(cName, cClass),
                Column.from(dName, dClass),
                Column.from(eName, eClass),
                Column.from(fName, fClass)
        );
    }

    public Schema6(
            Column<A> a,
            Column<B> b,
            Column<C> c,
            Column<D> d,
            Column<E> e,
            Column<F> f
    ) {
        super(6, a, b, c, d, e, f);
    }

    @Override
    public <Z> Schema7<A,B,C,D,E,F,Z> add(String columnName, Class<Z> columnType) {
        return new Schema7<A,B,C,D,E,F,Z>(
                get1stColumn(),
                get2ndColumn(),
                get3rdColumn(),
                get4thColumn(),
                get5thColumn(),
                get6thColumn(),
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

    public Column<E> get5thColumn() {
        return getColumn(5);
    }

    public Column<F> get6thColumn() {
        return getColumn(6);
    }

}
