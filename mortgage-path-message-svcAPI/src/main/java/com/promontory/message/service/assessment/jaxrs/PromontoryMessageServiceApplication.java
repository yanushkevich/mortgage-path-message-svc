package com.promontory.message.service.assessment.jaxrs;

import com.promontory.message.service.assessment.exceptions.MessageNotFoundException;
import com.promontory.message.service.assessment.jaxrs.resources.MessageResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
@Configuration
public class PromontoryMessageServiceApplication extends AbstractJaxRsResourceConfig{

    public PromontoryMessageServiceApplication(BuildProperties buildProperties,
                                               @Value("${server.servlet.context-path") String path) {
        super(buildProperties, path);
    }

    @Override
    protected void registerResources() {
        register(MessageResource.class);
        register(MessageNotFoundException.class);
    }
}
