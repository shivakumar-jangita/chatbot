package com.sap.itservices.copilot.smalltalk.recast;


import com.sap.itservices.copilot.smalltalk.recast.dialog.RecastDialogResponse;
import com.sap.itservices.copilot.smalltalk.recast.text.RecastTextResponse;

public interface RecastService {

    void setToken(String token);
    String  getToken();
    
    RecastTextResponse analyseText(RecastMessage message) throws RecastException;

    RecastDialogResponse sendDialogText(RecastMessage message) throws RecastException;
    
}
