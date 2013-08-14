package com.jauntsy.driller.api;

import java.util.List;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 12:51 PM
 */
public class TupleN extends TupleBase<Tuple,TupleN> {

    public TupleN(Object... values) {
        super(values);
    }

    public TupleN(List<Object> values) {
        super(values.toArray());
    }

}
