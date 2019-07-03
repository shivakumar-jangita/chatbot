package com.sap.fiori.copilot.agentmodel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="AIResponse", group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK, 
description="The response payload that the CoPilot digital assistant engine is expecting from an agent as a resonse.")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AIResponse extends AIPayloadBase implements IResponse {
    
    @ApiObjectField(description="An array of CoPilot result objects (collection items or collections) that should be created as part of the response processsing"
            + " on CoPilot side. Every visible response from an agent is expressed as a collection item result. For example, if an agent wants to ask a question to the user, a collection item"
            + " text result should be returned. CoPilot supports the following result object types:"
            + "<ul>"
            + "<li>CollectionItemTextResult</li>"
            + "<li>CollectionItemObjectResult</li>"
            + "<li>CollectionItemObjectListResult</li>"
            + "<li>CollectionItemQuickCreateResult</li>"
            + "<li>CollectionResult</li>"
            + "</ul>"
            + "<p>Please check the documentation of each result type in the 'Objects' section of this page</p>")
    protected Collection<IAIResult> results;
    protected Calendar timestamp;
    
    @ApiObjectField(description="The text that the agent understood from the received request")
    protected String resolvedRequestText;
    
    @ApiObjectField(required=true, description="Flag indicating if the agent could successfully process the request")
    @JsonProperty("wasSuccess")
    protected boolean wasSuccess;
    protected String errorMessage;
    
    @ApiObjectField(required=true, description="Flag indicating if the conversation is done as far as the current agent is concerned or if the agent expects another call from CoPilot with user input to finalize "
            + "the conversation.")
    protected boolean isConversationDone;
    
    @ApiObjectField(description="? ToDo ?")
    protected boolean executeIntent;
    
    public AIResponse() {
        super();
        this.results = new ArrayList<IAIResult>();
        this.isConversationDone = true; // by default the conversation is over
                                        // after one response
        Calendar currentTime = Calendar.getInstance();
        currentTime.setTime(new Date());
        this.timestamp = currentTime;
        this.wasSuccess = true;
        this.executeIntent = true;
        this.resolvedIntents = new ArrayList<IResolvedIntent>();
    }

    public AIResponse(AIRequest request) {
        this();
        this.copilotContext = request.getCopilotContext();
        this.resolvedRequestText = request.getText();
        this.agentContext = request.getAgentContext();
        this.conversationID = request.getConversationID();
        this.resolvedIntents = request.getResolvedIntents();
        if (this.resolvedIntents == null) {
            this.resolvedIntents = new ArrayList<IResolvedIntent>();
        }
    }

    public Collection<IAIResult> getResults() {
        return results;
    }

    public void setResults(Collection<IAIResult> results) {
        this.results = results;
    }

    @JsonIgnore
    public void addResult(IAIResult res) {
        this.results.add(res);
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public String getResolvedRequestText() {
        return resolvedRequestText;
    }

    public void setResolvedRequestText(String resolvedRequestText) {
        this.resolvedRequestText = resolvedRequestText;
    }

    @JsonProperty("wasSuccess")
    public boolean wasSuccess() {
        return wasSuccess;
    }

    @JsonProperty("wasSuccess")
    public void setWasSuccess(boolean wasSuccess) {
        this.wasSuccess = wasSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isConversationDone() {
        return isConversationDone;
    }

    public void setConversationDone(boolean isConversationDone) {
        this.isConversationDone = isConversationDone;
    }

    public boolean shouldExecuteIntent() {
        return executeIntent;
    }

    public void setExecuteIntent(boolean executeIntent) {
        this.executeIntent = executeIntent;
    }

    @Override
    public String toString() {
        return "AIResponse [results=" + results + ", timestamp=" + timestamp + ", resolvedRequestText="
                + resolvedRequestText + ", wasSuccess=" + wasSuccess + ", errorMessage=" + errorMessage
                + ", isConversationDone=" + isConversationDone + ", executeIntent=" + executeIntent
                + ", conversationID=" + conversationID + ", agentContext=" + agentContext + ", copilotContext="
                + copilotContext + ", resolvedIntents=" + resolvedIntents + "]";
    }
    
}
