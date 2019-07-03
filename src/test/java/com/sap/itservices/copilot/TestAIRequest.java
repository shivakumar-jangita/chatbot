package com.sap.itservices.copilot;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.fiori.copilot.agentmodel.AIRequest;
import com.sap.fiori.copilot.agentmodel.BaseIntent;
import com.sap.fiori.copilot.agentmodel.IRequest;
import com.sap.fiori.copilot.agentmodel.ResolvedIntent;
import com.sap.itservices.copilot.smalltalk.utils.AIRequestBuilder;
import com.sap.itservices.copilot.smalltalk.utils.IntentBuilder;
import com.sap.itservices.copilot.smalltalk.utils.ResolvedIntentBuilder;
import org.json.JSONObject;

import java.util.UUID;

public class TestAIRequest {

    public void testRequestBuilder(){
        UUID uuid = UUID.randomUUID();
        
        BaseIntent intent = new IntentBuilder()
                .domain("it")
                .guid(uuid.toString())
                .name("skip")
                .label("lable").build();


        ResolvedIntent resolvedIntent = new ResolvedIntentBuilder()
                .intent(intent)
                .agentID("defa")
                .confidence(0.88)
                .name("hello")
                .needsExecutionCallback(true)
                .build();

        AIRequest aiRequest = new AIRequestBuilder()
                .requestType(IRequest.AIRequestType.ACT)
                .text("hello,world!")
                .build();

        
    }
}
