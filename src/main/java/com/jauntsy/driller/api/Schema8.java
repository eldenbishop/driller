package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:32 PM
 */
public class Schema8<A,B,C,D,E,F,G,H> extends TypedSchema<Tuple8<A,B,C,D,E,F,G,H>, Tuple8<A,B,C,D,E,F,G,H>, Table8<A,B,C,D,E,F,G,H>, Schema8<A,B,C,D,E,F,G,H>> {
    public Schema8(
            String aName, Class<A> aClass,
            String bName, Class<B> bClass,
            String cName, Class<C> cClass,
            String dName, Class<D> dClass,
            String eName, Class<E> eClass,
            String fName, Class<F> fClass,
            String gName, Class<G> gClass,
            String hName, Class<H> hClass
    ) {
        this(
                Column.from(aName, aClass),
                Column.from(bName, bClass),
                Column.from(cName, cClass),
                Column.from(dName, dClass),
                Column.from(eName, eClass),
                Column.from(fName, fClass),
                Column.from(gName, gClass),
                Column.from(hName, hClass)
        );
    }

    Schema8(Column<A> a, Column<B> b, Column<C> c, Column<D> d, Column<E> e, Column<F> f, Column<G> g, Column<H> h) {
        super(8, a, b, c, d, e, f, g, h);
    }

    @Override
    public <Z> Schema add(String columnName, Class<Z> columnType) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
