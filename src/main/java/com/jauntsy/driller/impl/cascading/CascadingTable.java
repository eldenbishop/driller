package com.jauntsy.driller.impl.cascading;

import cascading.cascade.Cascade;
import cascading.cascade.CascadeConnector;
import cascading.flow.Flow;
import cascading.flow.FlowConnector;
import cascading.flow.FlowElement;
import cascading.pipe.GroupBy;
import cascading.pipe.Pipe;
import cascading.scheme.TextLine;
import cascading.tap.Hfs;
import cascading.tap.Tap;
import cascading.tuple.Fields;
import com.jauntsy.driller.api.*;

import java.util.List;
import java.util.Properties;

import static com.jauntsy.driller.api.Schema.Types.strings;

/**
 * User: ebishop
 * Date: 6/19/12
 * Time: 6:40 PM
 */
class CascadingTable extends CascadingDrillerBasePipeline implements UntypedTable {

    @Deprecated
    public CascadingTable() {
        super();
    }

    public CascadingTable(FlowContext flow, Schema schemaIn, Pipe pipeIn) {
        super(flow, schemaIn, pipeIn, schemaIn);
    }

    @Override
    public Schema getSchema() {
        return schemaOut;
    }

    @Override
    public UntypedGrouped groupBy(int i) {
        return new CascadingGroup(flow, schemaIn, pipeIn, i);
    }

    @Override
    public UntypedGrouped groupBy(String... columnNames) {
        return new CascadingGroup(flow, schemaIn, pipeIn, columnNames);
    }

    @Override
    public UntypedTable mapTo(Schema schemaOut, Mapper mapper) {
        return new CascadingMapTo(flow, schemaIn, pipeIn, schemaOut, mapper);
    }

    @Override
    public UntypedTable storeAsText(String path, Mapper<Tuple, Tuple1<String>> mapper) {
        Properties properties = new Properties();
        FlowConnector.setApplicationJarClass(properties, flow.getApplicationJarClass());

        FlowConnector flowConnector = new FlowConnector( properties );

        Pipe textPipe = new CascadingMapTo(flow, schemaIn, pipeIn, Schema.Of(strings("line")), mapper).buildOutputPipe();
        Flow tehRealFlow = flowConnector.connect(flow.getTap(), new Hfs(new TextLine(), path), textPipe);

        CascadeConnector connector = new CascadeConnector();
        Cascade cascade = connector.connect(tehRealFlow);
        cascade.complete();

        return flow.driller.loadText(path).getImpl();
    }

}
