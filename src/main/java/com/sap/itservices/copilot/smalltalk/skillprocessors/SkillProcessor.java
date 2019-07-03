package com.sap.itservices.copilot.smalltalk.skillprocessors;

import com.sap.fiori.copilot.agentmodel.AIRequest;
import com.sap.fiori.copilot.agentmodel.AIResponse;
import com.sap.itservices.copilot.smalltalk.recast.dialog.RecastDialogResponse;


public interface SkillProcessor {
    void process(AIRequest request, AIResponse response, RecastDialogResponse recastResponse);
    
}
