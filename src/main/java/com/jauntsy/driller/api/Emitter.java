package com.jauntsy.driller.api;

/**
 * User: ebishop
 * Date: 6/18/12
 * Time: 4:06 PM
 */
public interface Emitter<T extends Tuple> {
    void emit(T t);
}
