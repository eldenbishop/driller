package com.jauntsy.driller.io;

import com.jauntsy.driller.api.Mapper;
import com.jauntsy.driller.api.Tuple;
import com.jauntsy.driller.api.Tuple1;

/**
 * User: ebishop
 * Date: 6/22/12
 * Time: 5:11 PM
 */
public class CsvWriter extends Mapper<Tuple,Tuple1<String>> {

    private String separator;

    public CsvWriter() {
        this("\t");
    }

    public CsvWriter(String separator) {
        this.separator = separator;
    }

    @Override
    public void map(Tuple tuple, Emitter e) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tuple.size(); i++) {
            if (i > 0) sb.append(separator);
            sb.append(String.valueOf(tuple.get(i + 1)));
        }
        e.emit(Tuple.of1(sb.toString()));
    }

}
