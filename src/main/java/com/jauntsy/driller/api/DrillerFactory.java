package com.jauntsy.driller.api;

import org.apache.hadoop.conf.Configuration;

/**
 * User: ebishop
 * Date: 6/19/12
 * Time: 6:26 PM
 */
public interface DrillerFactory {

    Driller createPipeline(Configuration configuration, Class applicationJarClass);

}
