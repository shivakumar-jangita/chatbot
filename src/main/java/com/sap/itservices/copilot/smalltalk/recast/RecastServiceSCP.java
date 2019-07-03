package com.sap.itservices.copilot.smalltalk.recast;

import com.sap.ea.eacp.okhttp.destinationclient.OkHttpDestination;


import com.sap.itservices.copilot.smalltalk.config.OkHttpDestinationFactorySpring;

import com.sap.itservices.copilot.smalltalk.recast.dialog.RecastDialogResponse;
import com.sap.itservices.copilot.smalltalk.recast.text.RecastTextResponse;
import com.sap.itservices.copilot.smalltalk.services.PasswordService;
import okhttp3.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.util.Optional;


public class RecastServiceSCP implements RecastService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecastServiceSCP.class);
    private String token;


    @Autowired
    PasswordService passwordService;

    @Autowired
    private OkHttpDestinationFactorySpring okHttpDestinationFactory;

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        Optional<String> passwordOp = passwordService.getPassword();
        if (passwordOp.isPresent()){
            return passwordOp.get();
        } else {
            return token;
        }
    }

    @Override
    public RecastTextResponse analyseText(RecastMessage message) throws RecastException {
        String respBody = sendRequest(message,"v2/request");
        return new RecastTextResponse(respBody);
    }


    
    @Override
    public RecastDialogResponse sendDialogText(RecastMessage message) throws RecastException {
        String respBody = sendRequest(message,"build/v1/dialog");
        LOGGER.debug("The response from Recast is {}", respBody);
        return new RecastDialogResponse(respBody);
    }


    
    private String sendRequest(RecastMessage message, String path){
        OkHttpDestination destination = okHttpDestinationFactory.create("ext_recast_api");
        OkHttpClient client = destination.client();
        String body = message.toJsonString();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),body);
        Request request = destination.newRequest(path).
                header("Authorization","Token " + getToken()).
                post(requestBody).
                build();

        String respBody;
        try {
            Response response = client.newCall(request).execute();
            respBody = response.body().string();
        } catch (IOException e) {

            LOGGER.error("Fail to send request to Recast.", e);
            throw new RecastException("Fail to send request to Recast.",e);

        }

        return respBody;
    }
    

}