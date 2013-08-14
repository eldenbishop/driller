package com.jauntsy.driller.api;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ebishop
 * Date: 6/25/12
 * Time: 5:51 PM
 */
public class SortBy {

    List<String> columns = new ArrayList<String>();
    List<Integer> direction = new ArrayList<Integer>();

    public SortBy add(String column, int dir) {
        columns.add(column);
        direction.add(dir);
        return this;
    }

}
