package com.sap.itservices.copilot.smalltalk.dialog;

import com.sap.fiori.copilot.agentmodel.*;
import com.sap.fiori.copilot.agentmodel.results.text.CollectionItemTextResult;
import com.sap.itservices.copilot.smalltalk.config.ApplicationContextProvider;
import com.sap.itservices.copilot.smalltalk.execption.SkillNotFoundException;
import com.sap.itservices.copilot.smalltalk.recast.Intent;
import com.sap.itservices.copilot.smalltalk.recast.RecastService;
import com.sap.itservices.copilot.smalltalk.recast.dialog.RecastDialogMessage;
import com.sap.itservices.copilot.smalltalk.recast.dialog.RecastDialogResponse;
import com.sap.itservices.copilot.smalltalk.services.SkillProcessorDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;

/**
 * The dialog class represents one dialog between user and copilot. It has a unique id,
 * conversationId. The id keeps the connection between copilot and recast
 */
public class Dialog {

    private static final Logger LOGGER = LoggerFactory.getLogger(Dialog.class);

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    private String conversationId;
    
    private RecastService recastService;
    

    
    private SkillProcessorDispatcher skillProcessorDispatcher;
    

    public Dialog(RecastService recastService, String conversationId, SkillProcessorDispatcher skillProcessorDispatcher){
        this.recastService = recastService;
        this.conversationId = conversationId;
        this.skillProcessorDispatcher = skillProcessorDispatcher;
    }


    public AIResponse handleRequest(AIRequest request) {

        //TODO: AIResponse error handling
        AIResponse aiResponse = null;
        switch (request.getRequestType()){
            case ASK:
                aiResponse = getResponseForAsk(request);
                break;
            case ACT:
                aiResponse = getResponseForACT(request);
                break;
            case CONVERSE:
                aiResponse = getResponseForConverse(request);
                break;
            case REFRESH:
                aiResponse = getResponseForRefresh(request);
                break;
        }

        return aiResponse;
    }






    private AIResponse getResponseForAsk(AIRequest request){

        LOGGER.debug("ASK request received. The value is {}", request);
        AIResponse response = new AIResponse();
        response.setConversationID(request.getConversationID());
        response.setResolvedRequestText(request.getText());

        //call the recast
        RecastDialogMessage message = new RecastDialogMessage();
        message.setType("text");
        message.setConversationId(request.getConversationID());
        message.setContent(request.getText());
        RecastDialogResponse dialogResponse = recastService.sendDialogText(message);
        List<Intent> intents = dialogResponse.getNlp().getIntents();

        if (intents.size() == 0){
              //TODO: If no intent got matched, what should we do?
            LOGGER.debug("No intent got matched.");
            return response;

        }
        

        
        String slug = intents.get(0).getName();
        double slugConfidence = intents.get(0).getConfidence();
        String intentDesp = intents.get(0).getDescription();
        
        BaseIntent baseIntent = new BaseIntent();
        baseIntent.setDomain("it");
        baseIntent.setName(slug);

        
        ResolvedIntent resolvedIntent = new ResolvedIntent();
        resolvedIntent.setIntent(baseIntent);
        resolvedIntent.setConfidence(slugConfidence);

        //Hao: Try to set the name and description, when disambiguation happens.
        resolvedIntent.setName("Presales Asistant:" +" " + intentDesp);
        resolvedIntent.setExplanation("Recast example implementation");
        resolvedIntent.setDetails(request.getText());


        response.addResolvedIntent(resolvedIntent);

        response.setConversationDone(true);
        response.setWasSuccess(true);
        resolvedIntent.setNeedsExecutionCallback(true);

        return response;
    }

    

    private AIResponse getResponseForACT(AIRequest request){

        
        LOGGER.debug("ACT request received. The value is {}", request);
        AIResponse response = new AIResponse();
        response.setConversationID(request.getConversationID());
        response.setResolvedRequestText(request.getText());
        
        if (!request.hasUniqueResolvedIntent()) {
            LOGGER.warn("Request has multiple resolved intents.");
            return response;
        }
        ResolvedIntent resolvedIntent = (ResolvedIntent) request.getUniqueResolvedIntent();
        response.addResolvedIntent(resolvedIntent);

        LOGGER.debug("Unique resolved Intent is {}", resolvedIntent);

        String reqText = request.getText();
        if (reqText.contains("Presales Asistant")) {
            reqText = (String)resolvedIntent.getDetails();
            LOGGER.debug("The reqText for Presales Asistant is {}", reqText);
            Collection<IResolvedIntent> intentsCollection = request.getPreviousResolvedIntents();
            if (intentsCollection != null && !intentsCollection.isEmpty()){
                ArrayList<IResolvedIntent> intents = new ArrayList<>(request.getPreviousResolvedIntents());
                IResolvedIntent theFirstResolvedIntent = intents.get(0);
                LOGGER.debug("IResolvedIntent is {}", theFirstResolvedIntent);
            }
        }
        
        RecastDialogMessage dialogMessage = new RecastDialogMessage();
        dialogMessage.setConversationId(request.getConversationID());
        dialogMessage.setContent(reqText);
        RecastDialogResponse recastResp = recastService.sendDialogText(dialogMessage);

        
        try {
            skillProcessorDispatcher.dispatch(recastResp, request, response);
        } catch (SkillNotFoundException e) {
            CollectionItemTextResult text = new CollectionItemTextResult();
            text.setText("No skill could be determined by Recast.ai");
            response.addResult(text);
        }

        response.setConversationDone(true);
        response.setWasSuccess(true);
        return response;
        
    }


    private AIResponse getResponseForConverse(AIRequest request){

        LOGGER.debug("CONVERSE request received. The value is {}", request);
        

        AIResponse response = new AIResponse();
        response.setConversationID(request.getConversationID());
        response.setResolvedRequestText(request.getText());

        //First step to get the Intent from dialog.
        RecastDialogMessage dialogMessage = new RecastDialogMessage();
        dialogMessage.setConversationId(request.getConversationID());
        dialogMessage.setContent(request.getText());

        RecastDialogResponse dialogResponse = recastService.sendDialogText(dialogMessage);
        List<Intent> intents = dialogResponse.getNlp().getIntents();
        //TODO: at the moment we only process the first content with higher intent.
        if (intents.size() == 0) {
            LOGGER.debug("No intent found for the request. {}", request);
            return response;
        }

        Intent intent = intents.get(0);


        BaseIntent baseIntent = new BaseIntent();
        baseIntent.setDomain("it");
        baseIntent.setName(intent.getName());

        ResolvedIntent resolvedIntent = new ResolvedIntent();
        resolvedIntent.setIntent(baseIntent);
        resolvedIntent.setConfidence(intent.getConfidence());
        resolvedIntent.setName("Recast");
        resolvedIntent.setExplanation("Recast Example implementation");
        resolvedIntent.setDetails(request.getText());

        response.addResolvedIntent(resolvedIntent);

        //2nd step:

        if (response.hasUniqueResolvedIntent()){
             //why called Recast again?
        }

        
        return response;
    }

    private AIResponse getResponseForRefresh(AIRequest request){
        LOGGER.debug("REFRESH request received. The value is {}", request);

        return new AIResponse();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dialog dialog = (Dialog) o;
        return Objects.equals(conversationId, dialog.conversationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversationId);
    }


}
