package com.sap.itservices.copilot.smalltalk.config;


import com.sap.itservices.copilot.smalltalk.recast.RecastService;
import com.sap.itservices.copilot.smalltalk.recast.RecastServiceSCP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecastServiceConfig {

    //TODO The token should be stored in PasswordStorage class.
    private static final String TOKEN_PRIVATE = "bbad062f03bed1077d5f09fc4fa60c25";

   // private static final String TOKEN_DEV = "06852ccd8a055498928b31872af7703a";
    
    private static final String TOKEN_DEV = "3e6e40666e547510c02290cf49bcf8a5";
    
    @Bean
    public RecastService scpRecastService(){
        RecastService service = new RecastServiceSCP();
        service.setToken(TOKEN_DEV);
        return service;
    }


    @Bean
    public OkHttpDestinationFactorySpring okHttpDestinationFactory(){
        return new OkHttpDestinationFactorySpring();
    }
}
