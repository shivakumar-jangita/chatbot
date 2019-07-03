package com.sap.itservices.copilot.smalltalk.controllers;

import com.sap.cloud.security.password.PasswordStorageException;
import com.sap.itservices.copilot.smalltalk.services.PasswordService;
import com.sap.itservices.copilot.smalltalk.utils.SCPRoles;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RestController
@RequestMapping("admin")
public class AdminController {
    
    private PasswordService passwordService;

    @Autowired
    public AdminController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }


    @ApiOperation("Create or update the Recast access token.")
    @PutMapping(value = "/recast/token", produces = MediaType.TEXT_PLAIN_VALUE,consumes = MediaType.TEXT_PLAIN_VALUE)
    @RolesAllowed(SCPRoles.ADMIN)
    public ResponseEntity<String> updateAccessToken(@RequestBody String accessToken){
        passwordService.savePassword(accessToken);
        return ResponseEntity.ok("Access Token is updated");
    }


    @ApiOperation("Delete the Recast access token")
    @DeleteMapping(value = "/recast/token", produces = MediaType.TEXT_PLAIN_VALUE)
    @RolesAllowed(SCPRoles.ADMIN)
    public ResponseEntity<String> deleteAccessToken() {
        passwordService.deletePassword();
        return ResponseEntity.ok("Access Token is deleted.");
    }


    @ApiOperation("Get the Recast access token")
    @GetMapping(value = "/recast/token", produces = MediaType.TEXT_PLAIN_VALUE)
    @RolesAllowed(SCPRoles.ADMIN)
    public ResponseEntity<String> getAccessToken() {
        Optional<String> passwordOp = passwordService.getPassword();
        if (passwordOp.isPresent()){
            return ResponseEntity.ok(passwordOp.get());
        } else {
            return ResponseEntity.ok("Access Token doesn't exist.");
        }

    }
    
    @ExceptionHandler({PasswordStorageException.class})
    public ResponseEntity<String> handleException(PasswordStorageException ex) {
        return ResponseEntity.status(500).body(ex.getMessage());
    }
    
}
