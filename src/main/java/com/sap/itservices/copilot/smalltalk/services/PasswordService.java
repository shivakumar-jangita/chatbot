package com.sap.itservices.copilot.smalltalk.services;

import com.sap.cloud.security.password.PasswordStorage;
import com.sap.cloud.security.password.PasswordStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordService {
    
    private static final String RECAST_TOKEN = "3e6e40666e547510c02290cf49bcf8a5";


    @Autowired
    PasswordStorage passwordStorage;


    public void savePassword(String password) throws PasswordStorageException {
        passwordStorage.setPassword(RECAST_TOKEN, password.toCharArray());
    }


    
    public Optional<String> getPassword() throws PasswordStorageException{
        char[] chars = passwordStorage.getPassword(RECAST_TOKEN);
        if (chars == null){
            return Optional.empty();
        } else {
            String password = new String(chars);
            return Optional.of(password);
        }
    }


    public void deletePassword() throws PasswordStorageException{
        passwordStorage.deletePassword(RECAST_TOKEN);
    }
    
}
