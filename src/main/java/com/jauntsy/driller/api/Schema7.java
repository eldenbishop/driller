package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:32 PM
 */
public class Schema7<A,B,C,D,E,F,G> extends TypedSchema<Tuple7<A,B,C,D,E,F,G>, Tuple7<A,B,C,D,E,F,G>, Table7<A,B,C,D,E,F,G>, Schema7<A,B,C,D,E,F,G>> {
    Schema7(
            String aName, Class<A> aClass,
            String bName, Class<B> bClass,
            String cName, Class<C> cClass,
            String dName, Class<D> dClass,
            String eName, Class<E> eClass,
            String fName, Class<F> fClass,
            String gName, Class<G> gClass
    ) {
        this(
                Column.from(aName, aClass),
                Column.from(bName, bClass),
                Column.from(cName, cClass),
                Column.from(dName, dClass),
                Column.from(eName, eClass),
                Column.from(fName, fClass),
                Column.from(gName, gClass)
        );
    }

    Schema7(Column<A> a, Column<B> b, Column<C> c, Column<D> d, Column<E> e, Column<F> f, Column<G> g) {
        super(7, a, b, c, d, e, f, g);
    }

    @Override
    public <Z> Schema8<A,B,C,D,E,F,G,Z> add(String columnName, Class<Z> columnType) {
        throw new UnsupportedOperationException();
    }
}
