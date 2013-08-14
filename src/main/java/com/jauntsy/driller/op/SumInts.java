package com.jauntsy.driller.op;

import com.jauntsy.driller.api.Tuple;
import com.jauntsy.driller.api.Tuple1;
import com.jauntsy.driller.api.Tuples;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 6:36 PM
 */
public class SumInts extends ColumnCombiner<Integer> {

    @Override
    public Tuple1<Integer> combine(Tuple group, Tuples<Tuple1<Integer>> tuples) {
        int total = 0;
        while (tuples.hasNext())
            total += tuples.next().get1st();
        return Tuple.of(total);
    }

}
