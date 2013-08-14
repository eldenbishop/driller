package com.jauntsy.driller.api;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * User: ebishop
 * Date: 7/3/12
 * Time: 10:00 AM
 */
public abstract class TypeRef<T> {

    private final Type type;

    protected TypeRef() {
        ParameterizedType superclass = (ParameterizedType)
                getClass().getGenericSuperclass();
        type = superclass.getActualTypeArguments()[0];
    }

    @Override public boolean equals (Object o) {
        return o instanceof TypeRef &&
                ((TypeRef)o).type.equals(type);
    }

    @Override public int hashCode() {
        return type.hashCode();
    }

}
