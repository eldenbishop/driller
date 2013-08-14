package com.jauntsy.driller.impl.cascading;

import cascading.pipe.Every;
import cascading.pipe.GroupBy;
import cascading.pipe.Pipe;
import cascading.tuple.Fields;
import com.jauntsy.driller.api.*;

/**
 * User: ebishop
 * Date: 6/21/12
 * Time: 1:35 PM
 */
public class CascadingColumnCombinedGroup extends CascadingDrillerBasePipeline implements UntypedGrouped {

    private String[] columnNames;
    private Combiner combiner;

    @Deprecated
    public CascadingColumnCombinedGroup() {
        super();
    }

    public CascadingColumnCombinedGroup(FlowContext flow, Schema schemaIn, Pipe pipe, String[] columnNames, Combiner combiner) {
        super(flow, schemaIn, pipe, schemaIn);
        this.columnNames = columnNames;
        this.combiner = combiner;
    }

    private GroupBy buildGroupBy() {
        return CascadingGroup.buildGroupBy(pipeIn, schemaOut, columnNames);
    }

    @Override
    public UntypedTable ungroup() {
        return new CascadingTable(flow, schemaOut, new Every(buildGroupBy(), Fields.ALL, new CascadingGroup.GenericColumnCombiner(schemaIn, columnNames, combiner), Fields.RESULTS));
    }

    @Override
    public UntypedGrouped combine(Combiner combiner) {
        return new CascadingColumnCombinedGroup(flow, schemaIn, pipeIn, columnNames, combiner);
    }

    @Override
    public UntypedTable reduce(Schema schema, Reducer reducer) {
        return new CascadingTable(flow, schema, new Every(buildGroupBy(), Fields.ALL, new CascadingGroup.GenericColumnCombinerReducer(schemaIn, columnNames, combiner, schema, reducer), Fields.RESULTS));
    }

}
