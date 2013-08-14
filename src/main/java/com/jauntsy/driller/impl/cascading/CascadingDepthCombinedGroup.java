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
public class CascadingDepthCombinedGroup extends CascadingDrillerBasePipeline implements UntypedGrouped {

    private Integer depth;
    private Combiner combiner;

    @Deprecated
    public CascadingDepthCombinedGroup() {
        super();
    }

    public CascadingDepthCombinedGroup(FlowContext flow, Schema schemaIn, Pipe pipe, int depth, Combiner combiner) {
        super(flow, schemaIn, pipe, schemaIn);
        this.depth = depth;
        this.combiner = combiner;
    }

    private GroupBy buildGroupBy() {
        return CascadingGroup.buildGroupBy(pipeIn, schemaOut, depth);
    }

    @Override
    public UntypedTable ungroup() {
        return new CascadingTable(flow, schemaOut, new Every(buildGroupBy(), Fields.ALL, new CascadingGroup.GenericDepthCombiner(schemaIn, depth, combiner), Fields.RESULTS));
    }

    @Override
    public UntypedGrouped combine(Combiner combiner) {
        return new CascadingDepthCombinedGroup(flow, schemaIn, pipeIn, depth, combiner);
    }

    @Override
    public UntypedTable reduce(Schema schema, Reducer reducer) {
        return new CascadingTable(flow, schema, new Every(buildGroupBy(), Fields.ALL, new CascadingGroup.GenericDepthCombinerReducer(schemaIn, depth, combiner, schema, reducer), Fields.RESULTS));
    }

}
