package com.jauntsy.driller.impl.cascading;

import cascading.operation.Identity;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.scheme.TextLine;
import cascading.tap.Hfs;
import cascading.tap.Tap;
import cascading.tuple.Fields;
import com.jauntsy.driller.api.*;
import org.apache.hadoop.conf.Configuration;

import static com.jauntsy.driller.api.Schema.Types.*;

/**
 * User: ebishop
 * Date: 6/19/12
 * Time: 6:27 PM
 */
public class CascadingDriller implements Driller {

    private Configuration configuration;
    private Class applicationJarClass;

    public CascadingDriller(Configuration configuration, Class applicationJarClass) {
        this.configuration = configuration;
        this.applicationJarClass = applicationJarClass;
    }

    @Override
    public Table1<String> loadText(String path) {
        FlowContext flow = new FlowContext(this, applicationJarClass);
        Tap tap = new Hfs(new TextLine(), path);
        flow.setTap(tap);
        Pipe pipe = new Pipe(path);
        pipe = new Each(pipe, new Fields("line"), new Identity(new Fields("text")), Fields.RESULTS);
        return (Table1<String>) TableBase.wrap(new CascadingTable(flow, Schema.Of(strings("text")), pipe));
    }

}
