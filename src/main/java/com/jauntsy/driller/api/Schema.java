package com.jauntsy.driller.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 12:41 PM
 */
public abstract class Schema<CommonType extends Tuple,TupleType extends Tuple, TableType extends Table, SchemaType extends Schema> implements Serializable {

    public abstract <Z> Schema add(String columnName, Class<Z> columnType);

    public abstract TupleType newTuple();

    public abstract int size();

    public abstract List<Column> getColumns();

    public abstract List<String> getColumnNames();

    public abstract Column getColumn(int column);

    public abstract Column getColumn(String column);

    public abstract String getColumnName(int column);

    public abstract int getColumnIndex(String column);

    public abstract Class getColumnType(int column);

    public abstract Class getColumnType(String column);

    public static Schema0 Of() {
        return new Schema0();
    }

    public static <A> Schema1<A> Of(
            String aName, Class<A> aClass
    ) {
        return new Schema1<A>(
                aName, aClass
        );
    }

    public static <A,B> Schema2<A,B> Of(
            String aName, Class<A> aClass,
            String bName, Class<B> bClass
    ) {
        return new Schema2<A,B>(
                aName, aClass,
                bName, bClass
        );
    }

    public static <A,B,C> Schema3<A,B,C> Of(
            String aName, Class<A> aClass,
            String bName, Class<B> bClass,
            String cName, Class<C> cClass
    ) {
        return new Schema3<A,B,C>(
                aName, aClass,
                bName, bClass,
                cName, cClass
        );
    }

    public static <A,B,C,D> Schema4<A,B,C,D> Of(
            String aName, Class<A> aClass,
            String bName, Class<B> bClass,
            String cName, Class<C> cClass,
            String dName, Class<D> dClass
    ) {
        return new Schema4<A,B,C,D>(
                aName, aClass,
                bName, bClass,
                cName, cClass,
                dName, dClass
        );
    }

    public static <A> Schema1<A> Of(
            Column<A> a
    ) {
        return new Schema1<A>(a);
    }

    public static <A,B> Schema2<A,B> Of(
            Column<A> a,
            Column<B> b
    ) {
        return new Schema2<A,B>(a,b);
    }

    public static <A,B,C> Schema3<A,B,C> Of(
            Column<A> a,
            Column<B> b,
            Column<C> c
    ) {
        return new Schema3<A,B,C>(a,b,c);
    }

    public static <A,B,C,D> Schema4<A,B,C,D> Of(
            Column<A> a,
            Column<B> b,
            Column<C> c,
            Column<D> d
    ) {
        return new Schema4<A,B,C,D>(a,b,c,d);
    }

    public static <A,B,C,D,E> Schema5<A,B,C,D,E> Of(
            Column<A> a,
            Column<B> b,
            Column<C> c,
            Column<D> d,
            Column<E> e
    ) {
        return new Schema5<A,B,C,D,E>(a,b,c,d,e);
    }

    public static <A,B,C,D,E,F> Schema6<A,B,C,D,E,F> Of(
            Column<A> a,
            Column<B> b,
            Column<C> c,
            Column<D> d,
            Column<E> e,
            Column<F> f
    ) {
        return new Schema6<A,B,C,D,E,F>(a,b,c,d,e,f);
    }

    public static <A,B,C,D,E,F,G> Schema7<A,B,C,D,E,F,G> Of(
            Column<A> a,
            Column<B> b,
            Column<C> c,
            Column<D> d,
            Column<E> e,
            Column<F> f,
            Column<G> g
    ) {
        return new Schema7<A,B,C,D,E,F,G>(a,b,c,d,e,f,g);
    }

    public static <A,B,C,D,E,F,G,H> Schema8<A,B,C,D,E,F,G,H> Of(
            Column<A> a,
            Column<B> b,
            Column<C> c,
            Column<D> d,
            Column<E> e,
            Column<F> f,
            Column<G> g,
            Column<H> h
    ) {
        return new Schema8<A,B,C,D,E,F,G,H>(a,b,c,d,e,f,g,h);
    }

    public static SchemaN Of(
            Column a,
            Column b,
            Column c,
            Column d,
            Column e,
            Column f,
            Column g,
            Column h,
            Column i,
            Column... zzz
    ) {
        if (zzz == null || zzz.length == 0) {
            return new SchemaN(a,b,c,d,e,f,g,h,i);
        } else {
            Column[] columnsA = { a, b, c, d, e, f, g, h, i };
            Column[] columnsB = zzz;
            Column[] columnsMerged = new Column[columnsA.length + columnsB.length];
            System.arraycopy(columnsA, 0, columnsMerged, 0, columnsA.length);
            System.arraycopy(columnsB, 0, columnsMerged, columnsA.length, columnsB.length);
            return new SchemaN(columnsMerged);
        }
    }

    public static Schema of0() {
        return new Schema0();
    }

    public static Schema ofN(List<Column> columns) {
        Column[] v = columns.toArray(new Column[columns.size()]);
        switch(columns.size()) {
            case 0: return new Schema0();
            case 1: return new Schema1(v[0]);
            case 2: return new Schema2(v[0],v[1]);
            case 3: return new Schema3(v[0],v[1],v[2]);
            case 4: return new Schema4(v[0],v[1],v[2],v[3]);
            case 5: return new Schema5(v[0],v[1],v[2],v[3],v[4]);
            case 6: return new Schema6(v[0],v[1],v[2],v[3],v[4],v[5]);
            case 7: return new Schema7(v[0],v[1],v[2],v[3],v[4],v[5],v[6]);
            case 8: return new Schema8(v[0],v[1],v[2],v[3],v[4],v[5],v[6],v[7]);
            default: return new SchemaN(v);
        }
    }

    public abstract void bind(Tuple tuple);

    public static class Column<T> implements Serializable {

        private String name;
        private String typeName;
        private transient Class<T> type;

        public Column() {

        }

        public Column(String name, Class<T> type) {
            this.name = name;
            this.typeName = type.getName();
            this.type = type;
        }

        private Column(String name, Schema type) {
            this.name = name;
            this.typeName = type.getClass().getName();
            this.type = (Class<T>)List.class;
        }

        public static <T> Column<T> from(String name, Class<T> type) {
            return new Column<T>(name, type);
        }

        public static Column<List<Tuple0>> from(String name, Schema0 scheme) {
            return new Column<List<Tuple0>>(name, scheme);
        }

        public static <A> Column<List<Tuple1<A>>> from(String name, Schema1<A> scheme) {
            return new Column<List<Tuple1<A>>>(name, scheme);
        }

        public static <A,B> Column<List<Tuple2<A,B>>> from(String name, Schema2<A,B> scheme) {
            return new Column<List<Tuple2<A,B>>>(name, scheme);
        }

        public static <A,B,C> Column<List<Tuple3<A,B,C>>> from(String name, Schema3<A,B,C> scheme) {
            return new Column<List<Tuple3<A,B,C>>>(name, scheme);
        }

        public String getName() {
            return name;
        }

        public Class<T> getType() {
            try {
                if (type == null)
                    type = (Class<T>)Class.forName(typeName);
                return type;
            } catch(Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static class Types {

        public static Column<String> strings(String columnName) { return Column.from(columnName, String.class); }

        public static Column<Short> shorts(String columnName) { return Column.from(columnName, Short.class); }
        public static Column<Integer> ints(String columnName) { return Column.from(columnName, Integer.class); }
        public static Column<Long> longs(String columnName) { return Column.from(columnName, Long.class); }

        public static Column<Float> floats(String columnName) { return Column.from(columnName, Float.class); }
        public static Column<Double> doubles(String columnName) { return Column.from(columnName, Double.class); }

        public static Column<Boolean> booleans(String columnName) { return Column.from(columnName, Boolean.class); }

        public static Column<List<Tuple0>> table(String columnName, Schema0 schema0) { return Column.from(columnName, schema0); }

        public static Schema0 schema() { return Schema.Of(); }
        public static <A> Schema1<A> schema(Column<A> a) { return Schema.Of(a); }
        public static <A,B> Schema2<A,B> schema(Column<A> a, Column<B> b) { return Schema.Of(a, b); }
        public static <A,B,C> Schema3<A,B,C> schema(Column<A> a, Column<B> b, Column<C> c) { return Schema.Of(a, b, c); }
        public static <A,B,C,D> Schema4<A,B,C,D> schema(Column<A> a, Column<B> b, Column<C> c, Column<D> d) { return Schema.Of(a, b, c, d); }
        public static <A,B,C,D,E> Schema5<A,B,C,D,E> schema(Column<A> a, Column<B> b, Column<C> c, Column<D> d, Column<E> e) { return Schema.Of(a, b, c, d, e); }
        public static <A,B,C,D,E,F> Schema6<A,B,C,D,E,F> schema(Column<A> a, Column<B> b, Column<C> c, Column<D> d, Column<E> e, Column<F> f) { return Schema.Of(a, b, c, d, e, f); }
        public static <A,B,C,D,E,F,G> Schema7<A,B,C,D,E,F,G> schema(Column<A> a, Column<B> b, Column<C> c, Column<D> d, Column<E> e, Column<F> f, Column<G> g) { return Schema.Of(a, b, c, d, e, f, g); }
        public static <A,B,C,D,E,F,G,H> Schema8<A,B,C,D,E,F,G,H> schema(Column<A> a, Column<B> b, Column<C> c, Column<D> d, Column<E> e, Column<F> f, Column<G> g, Column<H> h) { return Schema.Of(a, b, c, d, e, f, g, h); }

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName() + "(");
        boolean first = true;
        for (Column c : getColumns()) {
            if (first) first = false;
            else sb.append(", ");
            sb.append(c.getName());
            sb.append(":");
            sb.append(c.getType().getSimpleName());
        }
        sb.append(")");
        return sb.toString();
    }

}
