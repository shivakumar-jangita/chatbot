package com.sap.itservices.copilot.smalltalk.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.fiori.copilot.agentmodel.IResolvedIntent;


public abstract class MixInAIResponse {

    @JsonProperty("wasSuccess")
    abstract public boolean wasSuccess();
    @JsonProperty("wasSuccess")
    abstract  public void setWasSuccess(boolean wasSuccess);
}
