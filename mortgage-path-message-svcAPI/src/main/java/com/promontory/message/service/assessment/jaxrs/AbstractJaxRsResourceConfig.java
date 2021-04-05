package com.promontory.message.service.assessment.jaxrs;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.info.BuildProperties;

public abstract class AbstractJaxRsResourceConfig extends ResourceConfig {


    protected final BuildProperties buildProperties;
    protected final String apiPath;

    public AbstractJaxRsResourceConfig(BuildProperties buildProperties, String apiPath) {
        super();
        this.buildProperties = buildProperties;
        this.apiPath = apiPath;

        registerResources();

        property(ServletProperties.FILTER_FORWARD_ON_404, true);
        register(RolesAllowedDynamicFeature.class);
    }

    protected abstract void registerResources();
}
