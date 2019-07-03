package com.sap.itservices.copilot.smalltalk.utils;

import com.sap.fiori.copilot.agentmodel.BaseIntent;

public class IntentBuilder {

    private BaseIntent intent = new BaseIntent();

    public BaseIntent build(){
        return intent;
    }

    public IntentBuilder guid(String guid){
        intent.setGuid(guid);
        return this;
    }


    public IntentBuilder name(String name){

        intent.setName(name);
        return this;
    }

    public IntentBuilder label(String label){

        intent.setLabel(label);
        return this;
    }

    public IntentBuilder domain(String domain){
        intent.setDomain(domain);
        return  this;
    }
    



}
