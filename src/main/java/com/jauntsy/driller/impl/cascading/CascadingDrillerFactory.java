package com.jauntsy.driller.impl.cascading;

import com.jauntsy.driller.api.Driller;
import com.jauntsy.driller.api.DrillerFactory;
import org.apache.hadoop.conf.Configuration;

/**
 * User: ebishop
 * Date: 6/19/12
 * Time: 6:25 PM
 */
public class CascadingDrillerFactory implements DrillerFactory {
    @Override
    public Driller createPipeline(Configuration configuration, Class applicationJarClass) {
        return new CascadingDriller(configuration, applicationJarClass);
    }
}
