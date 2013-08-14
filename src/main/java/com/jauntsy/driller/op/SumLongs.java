package com.jauntsy.driller.op;

import com.jauntsy.driller.api.*;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 6:36 PM
 */
public class SumLongs extends ColumnCombiner<Long> {

    @Override
    public Tuple1<Long> combine(Tuple group, Tuples<Tuple1<Long>> tuples) {
        long total = 0L;
        while (tuples.hasNext())
            total += tuples.next().get1st();
        return Tuple.of1(total);
    }

}
