package com.sap.fiori.copilot.agentmodel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="AIRequest", group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK,
 description="The request payload that CoPilot sends to each agent")

@JsonIgnoreProperties(ignoreUnknown = true)
public class AIRequest extends AIPayloadBase implements IRequest {
    
    private String clientContext;
    @JsonIgnore
    private InputStream speechInputStream;
   
    @ApiObjectField(description="Flag indicating if the input was spoken; true if the text field was compiled from the speech to text engine. This parameter will be set by the UI.")
    private boolean speechInputUsed;

    @ApiObjectField(description="The text that the user entered or was compiled from the speech to text engine")
    private String text;
    
    @ApiObjectField(description="List of agent IDs that are selected for processing this request. This parameter can be used to limit the set of agents that can possibly answer the request. If ommitted, "
            + "all active agents will receive the request")
    private Collection<String> requestedAgents;
    private String sourceSystem;
    
    private boolean includeResults;
    
    @ApiObjectField(description="If this request was triggered by an action (action button click, selection of a list item, etc), then this field will contain the ID of the triggered action."
            + "When the agent returned the actions that were presented to the user, the agent should remember the action IDs or be able to make sense of an action ID that is passed in subsequent requests")
    private String actionid;
    
    @ApiObjectField(description="The type of request, which represents the phase in which the conversation with an agent is. A typical CoPilot conversation handled by an agent happens in three phases:"
            + "<p>1.) ASK: Each connected agent is sent the same request and the agent who answers with the highest confidence wins. If multiple agents answer with high confidence, CoPilot will let the user "
            + "disambiguate which intent is meant by the user.</p>"
            + "<p>2.) ACT: Once an agent was selected who 'won' the conversation, the agent is then sent an ACT request, meaning the agent should exectute the resolved intent that it returned in the ASK request.</p>"
            + "<p>3.) CONVERSE: If the conversation with the user is not over after the ACT response - maybe because further user input is needed - all subsequent requests are CONVERSE requests until the agent responds "
            + "with 'conversationDone=true'", allowedvalues = {"ASK", "ACT", "CONVERSE"})
    private AIRequestType requestType;
    
    @ApiObjectField(description="The list of resolved intents which were collected in previous request/response cycles.?????? Please explain")
    private Collection<IResolvedIntent> previousResolvedIntents;

    public AIRequest() {
        super();
        this.requestedAgents = new ArrayList<String>();
        this.includeResults = false;
    }

    public AIRequest(AIRequest request, Collection<IResolvedIntent> resolvedIntents, AIRequestType requestType) {
        this(request);
        this.requestType = requestType;
        this.resolvedIntents = resolvedIntents;
    }

    public AIRequest(AIRequest request) {
        this();
        this.clientContext = request.clientContext;
        this.speechInputStream = request.speechInputStream;
        this.speechInputUsed = request.speechInputUsed;
        this.text = request.text;
        this.requestedAgents = request.requestedAgents;
        this.sourceSystem = request.sourceSystem;
        this.includeResults = request.includeResults;
        this.actionid = request.actionid;
        this.requestType = request.requestType;
        this.previousResolvedIntents = request.previousResolvedIntents;
        this.conversationID = request.conversationID;
        this.agentContext = request.agentContext;
        this.copilotContext = request.copilotContext;
        this.resolvedIntents = request.resolvedIntents;
    }

    /**
     * This creates a copy of the request and allows setting the PREVIOUS
     * resolved intents (not the resolved intents)
     * 
     * @param request
     * @param previousResolvedIntents
     */
    public AIRequest(AIRequest request, Collection<IResolvedIntent> previousResolvedIntents) {
        this(request);
        this.previousResolvedIntents = previousResolvedIntents;
    }

    public boolean hasSpeechInputStream() {
        return speechInputStream == null;
    }

    public InputStream getSpeechInputStream() {
        return speechInputStream;
    }

    public void setSpeechInputStream(InputStream speechInputStream) {
        this.speechInputStream = speechInputStream;
    }

    public String getClientContext() {
        return clientContext;
    }

    public void setClientContext(String clientContext) {
        this.clientContext = clientContext;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Collection<String> getRequestedAgents() {
        return requestedAgents;
    }

    public void setRequestedAgents(Collection<String> requestedAgents) {
        this.requestedAgents = requestedAgents;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public AIRequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(AIRequestType requestType) {
        this.requestType = requestType;
    }

    @Override
    public String toString() {
        return "AIRequest [clientContext=" + clientContext + ", text=" + text + ", requestedAgents=" + requestedAgents
                + ", sourceSystem=" + sourceSystem + ", includeResults=" + includeResults + ", requestType="
                + requestType + "]";
    }

    public boolean includeResults() {
        return this.includeResults;
    }

    public void setIncludeResults(boolean includeResults) {
        this.includeResults = includeResults;
    }

    public String getActionid() {
        return actionid;
    }

    public void setActionid(String actionid) {
        this.actionid = actionid;
    }

    public Collection<IResolvedIntent> getPreviousResolvedIntents() {
        return this.previousResolvedIntents;
    }

    public void setPreviousResolvedIntents(Collection<IResolvedIntent> resolvedIntents) {
        this.previousResolvedIntents = resolvedIntents;
    }
    
    public boolean isSpeechInputUsed() {
        return speechInputUsed;
    }

    public void setSpeechInputUsed(boolean speechInputUsed) {
        this.speechInputUsed = speechInputUsed;
    }


}
