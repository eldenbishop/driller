package com.jauntsy.driller.impl.cascading;

import com.jauntsy.driller.api.Grouped;
import com.jauntsy.driller.api.Schema;
import com.jauntsy.driller.api.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ebishop
 * Date: 6/19/12
 * Time: 6:55 PM
 */
public class CascTool {

    public static cascading.tuple.Tuple toCascadingTuple(Tuple tuple) {
        cascading.tuple.Tuple out = cascading.tuple.Tuple.size(tuple.size());
        for (int i = 0; i < tuple.size(); i++)
            out.set(i, tuple.get(i + 1));
        return out;
    }

    public static Tuple toDrillerTuple(Schema schema, cascading.tuple.Tuple tupleIn) {
        List<Object> values = new ArrayList<Object>();
        for (int i = 0; i < schema.size(); i++)
            values.add(tupleIn.get(i));
        return Tuple.ofN(values);
    }

}
