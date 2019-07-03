package com.sap.itservices.copilot.smalltalk.config;

import com.sap.cloud.security.password.PasswordStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;

@Configuration
public class PasswordStorageConfig {


    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordStorageConfig.class);


    @Bean
    public PasswordStorage passwordStorage() {

        String contextPath = "java:comp/env/PasswordStorage";
        try {
            LOGGER.debug("Bind PasswordStorage from contextPath '{}'", contextPath);
            InitialContext ctx = new InitialContext();
            PasswordStorage passwordStorage = (PasswordStorage) ctx.lookup(contextPath);
            return passwordStorage;
        } catch (NamingException | RuntimeException e) {
            throw new RuntimeException("Could not lookup password storage at " + contextPath, e);
        }
    }
}
