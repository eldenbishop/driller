package com.jauntsy.driller.impl.cascading;

import cascading.tap.Tap;

/**
 * User: ebishop
 * Date: 6/21/12
 * Time: 12:33 PM
 */
public class FlowContext {

    public CascadingDriller driller;
    private final Class applicationJarClass;
    private Tap tap;

    public FlowContext(CascadingDriller cascadingDriller, Class applicationJarClass) {
        this.driller = cascadingDriller;
        this.applicationJarClass = applicationJarClass;
    }

    public void setTap(Tap tap) {
        this.tap = tap;
    }

    public Class getApplicationJarClass() {
        return applicationJarClass;
    }

    public Tap getTap() {
        return tap;
    }
}
