package com.sap.itservices.copilot.smalltalk.config;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sap.fiori.copilot.agentmodel.BaseIntent;
import com.sap.fiori.copilot.agentmodel.IIntent;
import com.sap.fiori.copilot.agentmodel.IResolvedIntent;
import com.sap.fiori.copilot.agentmodel.ResolvedIntent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
    
    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder){

        builder.failOnUnknownProperties(false);
        ObjectMapper mapper = builder.build();
        SimpleModule module = new SimpleModule("ResolvedIntent", Version.unknownVersion());
        SimpleAbstractTypeResolver resolver = new SimpleAbstractTypeResolver();
        resolver.addMapping(IResolvedIntent.class, ResolvedIntent.class);
        resolver.addMapping(IIntent.class, BaseIntent.class);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        module.setAbstractTypes(resolver);
        mapper.registerModule(module);
//        mapper.addMixIn(AIRequest.class, MixInAIRequest.class);
//        mapper.addMixIn(AIResponse.class, MixInAIResponse.class);
//        mapper.addMixIn(AIPayloadBase.class, MixInAIPayloadBase.class);
        
        return mapper;
    }
}
