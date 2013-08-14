package com.jauntsy.driller.demo;

import java.util.Iterator;

/**
 * User: ebishop
 * Date: 6/25/12
 * Time: 10:39 AM
 */
public class TypeTest {
    public static void main(String[] args) {
        Table t = null;
        t.combine(new Combiner() {
            @Override
            public void combine(TupleEntry values, Emitter emitter) {
                long count = 0L;
                while (values.next())
                    count += values.getLong(2);
                emitter.emit(null);
            }
        });
        t.combine(new Combiner() {
            @Override
            public void combine(TupleEntry values, Emitter e) {
            }
        });
        Table2<String,Long> t2 = null;
        t2.combine(new Combiner2<String, Long>() {
            @Override
            public void combine(TupleEntry2<String, Long> values, Emitter<Tuple2<String, Long>> e) {
                long count = 0L;
                while (values.next())
                    count += values.get2();
                e.emit(null);
            }
        });

        Schema1<String> s1 = null;
        t2.reduce(s1, new Reducer<Tuple2<String, Long>, Tuple1<String>>() {
            @Override
            public void reduce(TupleIterator<Tuple2<String, Long>> values, Emitter e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });


        t.reduce(s1, new Reducer() {
            @Override
            public void reduce(TupleIterator values, Emitter e) {

            }
        });
    }

    public static class Table<TableType extends Table, TupleType extends Tuple, TupleEntryType extends TupleEntry, CombinerType extends Combiner> {
        public TableType combine(Combiner<TupleEntryType,Emitter<TupleType>> combiner) {
            return null;
        }
        public <DestTableType extends Table,DestTupleType extends Tuple> DestTableType reduce(Schema<?,DestTableType,DestTupleType> schema, Reducer<TupleType,DestTupleType> reducer) {
            return null;
        }
    }

    public static class Table1<A> extends Table<Table1<A>, Tuple1<A>, TupleEntry1<A>, Combiner1<A>> {

    }

    public static class Table2<A,B> extends Table<Table2<A,B>, Tuple2<A,B>, TupleEntry2<A,B>, Combiner2<A,B>> {
    }

    public static class Table3<A,B,C> extends Table<Table3<A,B,C>, Tuple3<A,B,C>, TupleEntry3<A,B,C>, Combiner3<A,B,C>> {

    }

    public static abstract class Reducer<S extends Tuple,D extends Tuple> {
        public abstract class Emitter implements TypeTest.Emitter<D> {}
        public abstract void reduce(TupleIterator<S> values, Emitter e);
    }

    public abstract class TupleIterator<TupleType extends Tuple> implements Iterator<TupleType> {
    }

    public interface Emitter<T extends Tuple> {
        void emit(T t);
    }

    public interface Combiner<TupleEntryType extends TupleEntry, EmitterType extends Emitter> {
        public void combine(TupleEntryType values, EmitterType e);
    }

    public interface Combiner1<A> extends Combiner<TupleEntry1<A>, Emitter<Tuple1<A>>> {}
    public interface Combiner2<A,B> extends Combiner<TupleEntry2<A,B>, Emitter<Tuple2<A,B>>> {}
    public interface Combiner3<A,B,C> extends Combiner<TupleEntry3<A,B,C >, Emitter<Tuple3<A,B,C>>> {}

    public interface Tuple<TupleType extends Tuple> {
        public Object get(int column);
        public Long getLong(int column);
        public String getString(int column);
    }

    public interface Tuple1<A> extends Tuple<Tuple1<A>> {
        public A get1();
    }

    public interface Tuple2<A,B> extends Tuple<Tuple2<A,B>> {
        public A get1();
        public B get2();
    }

    public interface Tuple3<A,B,C> extends Tuple<Tuple3<A,B,C>> {
        public A get1();
        public B get2();
        public C get3();
    }

    public interface TupleEntry<TupleEntryType extends TupleEntry, TupleType extends Tuple> extends Tuple<TupleType> {
        public boolean next();
    }

    public interface TupleEntry1<A> extends Tuple1<A>, TupleEntry<TupleEntry1<A>, Tuple1<A>> {

    }

    public interface TupleEntry2<A,B> extends Tuple2<A,B>, TupleEntry<TupleEntry2<A,B>, Tuple2<A,B>> {

    }

    public interface TupleEntry3<A,B,C> extends Tuple3<A,B,C>, TupleEntry<TupleEntry3<A,B,C>, Tuple3<A,B,C>> {

    }

    public static class Schema<SchemaType extends Schema, TableType extends Table, TupleType extends Tuple> {

    }

    public static class Schema1<A> extends Schema<Schema1<A>, Table1<A>, Tuple1<A>> {

    }

    public static class Schema2<A,B> extends Schema<Schema2<A,B>, Table2<A,B>, Tuple2<A,B>> {

    }

    public static class Schema3<A,B,C> extends Schema<Schema3<A,B,C>, Table3<A,B,C>, Tuple3<A,B,C>> {

    }

}
