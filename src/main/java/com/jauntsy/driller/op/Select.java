package com.jauntsy.driller.op;

import com.jauntsy.driller.api.Mapper;
import com.jauntsy.driller.api.Schema;
import com.jauntsy.driller.api.Tuple;
import com.jauntsy.driller.api.Tuple0;

/**
 * User: ebishop
 * Date: 6/25/12
 * Time: 3:30 PM
 */
public class Select extends Mapper<Tuple,Tuple> {
    @Override
    public void map(Tuple tuple, Emitter e) {
        Schema schema = getOutputSchema();
        Tuple out = schema.newTuple();
        for (int i = 1; i <= schema.size(); i++) {
            out.set(i, tuple.get(schema.getColumnName(i)));
        }
        e.emit(out);
    }
}
