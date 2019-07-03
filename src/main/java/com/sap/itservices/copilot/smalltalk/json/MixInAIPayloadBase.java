package com.sap.itservices.copilot.smalltalk.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.fiori.copilot.agentmodel.IResolvedIntent;

public abstract class MixInAIPayloadBase {
    @JsonIgnore
    abstract public IResolvedIntent getHighestConfidenceResolvedIntent();
    @JsonIgnore
    abstract public IResolvedIntent getUniqueResolvedIntent();
    
}
