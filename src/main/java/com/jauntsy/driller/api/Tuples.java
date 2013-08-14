package com.jauntsy.driller.api;

import java.util.Iterator;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 6:29 PM
 */
public interface Tuples<T extends Tuple> extends Iterator<T> {

    @Override
    public boolean hasNext();

    @Override
    public T next();

    @Override
    public void remove();

}
