package com.jauntsy.driller.op;

import com.jauntsy.driller.api.Tuple;
import com.jauntsy.driller.api.Tuple1;
import com.jauntsy.driller.api.Tuples;

/**
 * User: ebishop
 * Date: 6/25/12
 * Time: 7:23 PM
 */
public class FirstString extends ColumnCombiner<String> {
    @Override
    public Tuple1<String> combine(Tuple group, Tuples<Tuple1<String>> tuples) {
        Tuple1<String> val = Tuple.of1((String)null);
        while (tuples.hasNext()) {
            Tuple1<String> next = tuples.next();
            String s = next.get1st();
            if (s != null) {
                val.set(1, s);
                break;
            }
        }
        return val;
    }
}
