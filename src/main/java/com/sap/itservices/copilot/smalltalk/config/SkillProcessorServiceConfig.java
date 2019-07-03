package com.sap.itservices.copilot.smalltalk.config;

import com.sap.itservices.copilot.smalltalk.services.SkillProcessorDispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SkillProcessorServiceConfig {
    
    @Bean
    public SkillProcessorDispatcher skillProcessorDispatcher(){
        return new SkillProcessorDispatcher("com.sap.itservices.copilot.smalltalk.skillprocessors");
    }
}
