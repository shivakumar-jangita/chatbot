package com.sap.itservices.copilot.smalltalk.utils;

import com.sap.fiori.copilot.agentmodel.AIRequest;
import com.sap.fiori.copilot.agentmodel.IRequest;
import com.sap.fiori.copilot.agentmodel.IResolvedIntent;

import java.util.Collection;

public class AIRequestBuilder {
    
    private final AIRequest aiRequest;

    public AIRequestBuilder(){
        aiRequest = new AIRequest();
    }

    public AIRequest build(){

        return aiRequest;

    }


    public AIRequestBuilder clientContext(String clientContext){
        aiRequest.setClientContext(clientContext);
        return this;
    }

    public AIRequestBuilder text(String text){
        aiRequest.setText(text);
        return this;
    }

    public AIRequestBuilder requestedAgents(Collection<String> requestedAgents){

        aiRequest.setRequestedAgents(requestedAgents);
        return this;

    }


    public AIRequestBuilder sourceSystem(String sourceSystem){

        aiRequest.setSourceSystem(sourceSystem);
        return this;
    }



    public AIRequestBuilder includeResults(boolean includeResults){
        aiRequest.setIncludeResults(includeResults);
        return this;
    }

    public AIRequestBuilder requestType(IRequest.AIRequestType requestType){
        aiRequest.setRequestType(requestType);
        return this;
    }

    
    public AIRequestBuilder previousResolvedIntents(Collection<IResolvedIntent> previousResolvedIntents){
        aiRequest.setResolvedIntents(previousResolvedIntents);
        return this;
    }





}
