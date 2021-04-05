package com.promontory.message.service.assessment;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Slf4j
@EnableAsync
public class MessageSvcSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageSvcSpringBootApplication.class, args);
    }

    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        return tomcat;
    }

}
