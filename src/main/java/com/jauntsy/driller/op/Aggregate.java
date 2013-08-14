package com.jauntsy.driller.op;

import com.jauntsy.driller.api.*;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 6:42 PM
 */
public class Aggregate {

    private static Table _count(Table items) {
        Schema newSchema = items.getSchema().add("count", Long.class);
        Table itemsCounts = items.mapTo(
                newSchema,
                new Mapper<Tuple, Tuple>() {
                    @Override
                    public void map(Tuple row, Emitter e) {
                        Tuple tuple = outputSchema.newTuple();
                        for (int i = 1; i <= inputSchema.size(); i++) {
                            tuple.set(i, row.get(i));
                        }
                        tuple.set(outputSchema.size(), 1L);
                        e.emit(tuple);
                    }
                }
        );
        Grouped group = itemsCounts.groupBy(newSchema.size() - 1);
        Grouped combined = group.combine(new Combiner() {
            @Override
            public Tuple combine(Tuple group, Tuples tuples) {
                long total = 0L;
                while (tuples.hasNext())
                    total += tuples.next().getLong(1);
                return Tuple.of1(total);
            }
        });
        Table finished = combined.ungroup();
        return finished;
    }

    public static Table count(Table items) {
        return _count(items);
    }

    public static <A> Table2<A,Long> count(Table1<A> table) {
        return (Table2<A, Long>) _count(table);
    }

    public static <A,B> Table3<A,B,Long> count(Table2<A,B> table) {
        return (Table3<A, B, Long>) _count(table);
    }

    public static <A,B,C> Table4<A,B,C,Long> count(Table3<A,B,C> table) {
        return (Table4<A, B, C, Long>) _count(table);
    }

    public static <A,B,C,D> Table5<A,B,C,D,Long> count(Table4<A,B,C,D> table) {
        return (Table5<A, B, C, D, Long>) _count(table);
    }

    public static <A,B,C,D,E> Table6<A,B,C,D,E,Long> count(Table5<A,B,C,D,E> table) {
        return (Table6<A, B, C, D, E, Long>) _count(table);
    }

}
