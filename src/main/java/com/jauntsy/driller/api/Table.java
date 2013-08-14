package com.jauntsy.driller.api;

import java.io.Serializable;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 1:59 PM
 */
public abstract class Table<CommonType extends Tuple,TupleType extends Tuple, TableType extends Table> implements Storable<TupleType>, Serializable {

    public abstract Schema<CommonType, TupleType, TableType, ?> getSchema();

    public abstract Grouped groupBy(int i);

    public abstract Grouped groupBy(String... columnNames);

    public abstract <
            DTCommon extends Tuple,
            DTTuple extends Tuple,
            DTTable extends Table,
            DTSchema extends Schema
    > DTTable mapTo(Schema<DTCommon,DTTuple, DTTable, DTSchema> schema, Mapper<? super TupleType, DTTuple> mapper);

    public abstract <
            DTTuple extends Tuple,
            DTTable extends Table,
            DTSchema extends Schema
    > DTTable mapTo(Schema<CommonType,DTTuple, DTTable, DTSchema> schema);

//    public TableType sortBy(String columnA, int dirA) { return sortBy(new SortBy()
//            .add(columnA, dirA));
//    }
//
//    public TableType sortBy(String columnA, int dirA, String columnB, int dirB) { return sortBy(new SortBy()
//            .add(columnA, dirA)
//            .add(columnB, dirB)
//        );
//    }
//
//    public abstract TableType sortBy(SortBy sortBy);

    public static Schema0 of() { return Schema.Of(); }
    public static <A> Schema1<A> of(Schema.Column<A> a) { return Schema.Of(a); }
    public static <A,B> Schema2<A,B> of(Schema.Column<A> a, Schema.Column<B> b) { return Schema.Of(a, b); }
    public static <A,B,C> Schema3<A,B,C> of(Schema.Column<A> a, Schema.Column<B> b, Schema.Column<C> c) { return Schema.Of(a, b, c); }
    public static <A,B,C,D> Schema4<A,B,C,D> of(Schema.Column<A> a, Schema.Column<B> b, Schema.Column<C> c, Schema.Column<D> d) { return Schema.Of(a, b, c, d); }
    public static <A,B,C,D,E> Schema5<A,B,C,D,E> of(Schema.Column<A> a, Schema.Column<B> b, Schema.Column<C> c, Schema.Column<D> d, Schema.Column<E> e) { return Schema.Of(a, b, c, d, e); }
    public static <A,B,C,D,E,F> Schema6<A,B,C,D,E,F> of(Schema.Column<A> a, Schema.Column<B> b, Schema.Column<C> c, Schema.Column<D> d, Schema.Column<E> e, Schema.Column<F> f) { return Schema.Of(a, b, c, d, e, f); }
    public static <A,B,C,D,E,F,G> Schema7<A,B,C,D,E,F,G> of(Schema.Column<A> a, Schema.Column<B> b, Schema.Column<C> c, Schema.Column<D> d, Schema.Column<E> e, Schema.Column<F> f, Schema.Column<G> g) { return Schema.Of(a, b, c, d, e, f, g); }
    public static <A,B,C,D,E,F,G,H> Schema8<A,B,C,D,E,F,G,H> of(Schema.Column<A> a, Schema.Column<B> b, Schema.Column<C> c, Schema.Column<D> d, Schema.Column<E> e, Schema.Column<F> f, Schema.Column<G> g, Schema.Column<H> h) { return Schema.Of(a, b, c, d, e, f, g, h); }

}
