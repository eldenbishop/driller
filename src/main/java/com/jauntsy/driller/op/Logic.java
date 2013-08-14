package com.jauntsy.driller.op;

import com.jauntsy.driller.api.*;

/**
 * User: ebishop
 * Date: 6/25/12
 * Time: 3:01 PM
 */
public class Logic {

    private static Table _ror(Table table) {
        return null;
    }

    public static Table0 ror(Table0 table) { return table; }
    public static <A> Table1<A> ror(Table1<A> table) { return table; }
    public static <A,B> Table2<B,A> ror(Table2<A,B> table) { return (Table2<B, A>) _ror(table); }
    public static <A,B,C> Table3<C,A,B> ror(Table3<A,B,C> table) { return (Table3<C, A, B>) _ror(table); }
    public static <A,B,C,D> Table4<D,A,B,C> ror(Table4<A,B,C,D> table) { return (Table4<D, A, B, C>) _ror(table); }
    public static <A,B,C,D,E> Table5<E,A,B,C,D> ror(Table5<A,B,C,D,E> table) { return (Table5<E, A, B, C, D>) _ror(table); }
    public static <A,B,C,D,E,F> Table6<F,A,B,C,D,E> ror(Table6<A,B,C,D,E,F> table) { return (Table6<F, A, B, C, D, E>) _ror(table); }
    public static <A,B,C,D,E,F,G> Table7<G,A,B,C,D,E,F> ror(Table7<A,B,C,D,E,F,G> table) { return (Table7<G, A, B, C, D, E, F>) _ror(table); }
    public static <A,B,C,D,E,F,G,H> Table8<H,A,B,C,D,E,F,G> ror(Table8<A,B,C,D,E,F,G,H> table) { return (Table8<H, A, B, C, D, E, F, G>) _ror(table); }

}
