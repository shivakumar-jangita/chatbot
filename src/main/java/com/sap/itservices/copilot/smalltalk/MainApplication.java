package com.sap.itservices.copilot.smalltalk;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.sap.itservices.copilot.smalltalk")
//@ComponentScan("com.sap.itservices.copilot.smalltalk")

public class MainApplication extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        LOGGER.debug("Application starts to run!! ");
        

        return builder.sources(MainApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
