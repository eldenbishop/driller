package com.jauntsy.driller.api;

import java.io.Serializable;
import java.util.List;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 10:25 AM
 */
public abstract class Tuple<CommonTupleType extends Tuple,TupleType extends Tuple> implements Serializable {

    String[] columnNames = null;

    public abstract int size();

    public abstract Object get(int column);

    public abstract void set(int column, Object value);

    public TupleType as(String... columnNames) {
        this.columnNames = columnNames;
        return (TupleType)this;
    }

    public String getColumnName(int pos) {
        return columnNames == null ? null : columnNames[pos - 1];
    }

    public int getColumnPos(String columnName) {
        if (columnNames != null) {
            for (int i = 0; i < columnNames.length; i++) {
                if (columnNames[i].equals(columnName))
                    return i + 1;
            }
        }
        // *** Throw an exception and a printout of the current schema
        StringBuilder sb = new StringBuilder("No such column: ").append(columnName).append(", schema:[");
        if (columnNames == null)
            sb.append("null");
        else {
            for (int i = 0; i < columnNames.length; i++) {
                if (i > 0) sb.append(",");
                sb.append(columnNames[i]);
            }
        }
        sb.append("]");
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public Object get(String column) {
        return get(getColumnPos(column));
    }

    public Integer getInt(String column) {
        return get(Integer.class, column);
    }

    public Long getLong(String column) {
        return get(Long.class, column);
    }

    public String getString(String column) {
        return get(String.class, column);
    }

    public <T> T get(Class<T> type, String column) {
        return get(type, getColumnPos(column));
    }

    public <T> T get(Class<T> type, int column) {
        return (T)get(column);
    }

    public Integer getInt(int column) {
        return get(Integer.class, column);
    }

    public Long getLong(int column) {
        return get(Long.class, column);
    }

    public String getString(int column) {
        return get(String.class, column);
    }

    public static <A> Tuple1<A> of1(A a) {
        return new Tuple1<A>(a);
    }

    public static <A,B> Tuple2<A,B> of2(A a, B b) {
        return new Tuple2<A,B>(a, b);
    }

    public static <A,B,C> Tuple3<A,B,C> of3(A a, B b, C c) {
        return new Tuple3<A,B,C>(a, b, c);
    }

    public static <A,B,C,D> Tuple4<A,B,C,D> of4(A a, B b, C c, D d) {
        return new Tuple4<A,B,C,D>(a, b, c, d);
    }

    public static <A,B,C,D,E> Tuple5<A,B,C,D,E> of5(A a, B b, C c, D d, E e) {
        return new Tuple5<A,B,C,D,E>(a, b, c, d, e);
    }

    public static <A,B,C,D,E,F> Tuple6<A,B,C,D,E,F> from6(A a, B b, C c, D d, E e, F f) {
        return new Tuple6<A,B,C,D,E,F>(a, b, c, d, e, f);
    }

    public static <A,B,C,D,E,F,G> Tuple7<A,B,C,D,E,F,G> of7(A a, B b, C c, D d, E e, F f, G g) {
        return new Tuple7<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
    }

    public static <A,B,C,D,E,F,G,H> Tuple8<A,B,C,D,E,F,G,H> of8(A a, B b, C c, D d, E e, F f, G g, H h) {
        return new Tuple8<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
    }

    public static <A> Tuple1<A> of(A a) {
        return new Tuple1<A>(a);
    }

    public static <A,B> Tuple2<A,B> of(A a, B b) {
        return new Tuple2<A,B>(a, b);
    }

    public static <A,B,C> Tuple3<A,B,C> of(A a, B b, C c) {
        return new Tuple3<A,B,C>(a, b, c);
    }

    public static <A,B,C,D> Tuple4<A,B,C,D> of(A a, B b, C c, D d) {
        return new Tuple4<A,B,C,D>(a, b, c, d);
    }

    public static <A,B,C,D,E> Tuple5<A,B,C,D,E> of(A a, B b, C c, D d, E e) {
        return new Tuple5<A,B,C,D,E>(a, b, c, d, e);
    }

    public static <A,B,C,D,E,F> Tuple6<A,B,C,D,E,F> of(A a, B b, C c, D d, E e, F f) {
        return new Tuple6<A,B,C,D,E,F>(a, b, c, d, e, f);
    }

    public static <A,B,C,D,E,F,G> Tuple7<A,B,C,D,E,F,G> of(A a, B b, C c, D d, E e, F f, G g) {
        return new Tuple7<A,B,C,D,E,F,G>(a, b, c, d, e, f, g);
    }

    public static <A,B,C,D,E,F,G,H> Tuple8<A,B,C,D,E,F,G,H> of(A a, B b, C c, D d, E e, F f, G g, H h) {
        return new Tuple8<A,B,C,D,E,F,G,H>(a, b, c, d, e, f, g, h);
    }

    public static Tuple ofN(List items) {
        return size(items.size()).setAll(items);
    }

    public static Tuple ofN(Object[] items) {
        return size(items.length).setAll(items);
    }

    protected abstract Tuple setAll(List items);

    protected abstract Tuple setAll(Object[] items);

    public static Tuple size(int size) {
        switch(size) {
            case 0: return new Tuple0();
            case 1: return new Tuple1(null);
            case 2: return new Tuple2(null,null);
            case 3: return new Tuple3(null,null,null);
            case 4: return new Tuple4(null,null,null,null);
            case 5: return new Tuple5(null,null,null,null,null);
            case 6: return new Tuple6(null,null,null,null,null,null);
            case 7: return new Tuple7(null,null,null,null,null,null,null);
            case 8: return new Tuple8(null,null,null,null,null,null,null,null);
            default: return new TupleN(new Object[size]);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("(");
        for (int i = 0; i < size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(get(i + 1));
        }
        sb.append(")");
        return sb.toString();
    }

}
