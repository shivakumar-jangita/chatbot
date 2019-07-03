package com.sap.itservices.copilot.smalltalk.utils;

import com.sap.fiori.copilot.agentmodel.BaseIntent;

import com.sap.fiori.copilot.agentmodel.ResolvedIntent;

public class ResolvedIntentBuilder {

    ResolvedIntent resolvedIntent = new ResolvedIntent();

    public ResolvedIntent build(){
        return resolvedIntent;
    }
    
    public ResolvedIntentBuilder agentID(String agentId){


        resolvedIntent.setAgentID(agentId);
        return this;
    }

    public ResolvedIntentBuilder intent(BaseIntent intent){
        resolvedIntent.setIntent(intent);
        return this;

    }

    public ResolvedIntentBuilder confidence(double confidence){
        resolvedIntent.setConfidence(confidence);
        return this;
    }

    public ResolvedIntentBuilder details(Object details){
        resolvedIntent.setDetails(details);
        return this;
    }

    public ResolvedIntentBuilder name(String name){
        resolvedIntent.setName(name);
        return this;
    }

    public ResolvedIntentBuilder explanation(String explanation){
        resolvedIntent.setExplanation(explanation);
        return this;
    }

    public ResolvedIntentBuilder needsExecutionCallback(boolean needsExecutionCallback){
        resolvedIntent.setNeedsExecutionCallback(needsExecutionCallback);
        return this;
    }
}



