package com.sap.itservices.copilot.smalltalk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContextConfig {

    @Bean
    public ApplicationContextProvider applicationContextProvder(){
         return new ApplicationContextProvider();
    }
}
