package com.sap.fiori.copilot.agentmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.fiori.copilot.agentmodel.context.AgentContext;
import com.sap.fiori.copilot.agentmodel.context.CopilotContext;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.ArrayList;
import java.util.Collection;

@ApiObject(show=false, name="AIPayloadBase", 
description="This object represents the payload that is common between requests and responses that are sent between CoPilot and agents. Objects <b>AIRequest</b> and <b>AIResponse</b> inherit from this")
public abstract class AIPayloadBase {
    
    @ApiObjectField(description="A generated ID that CoPilot sends to the agent identifying the current conversation. Will be reset when the conversation is marked as done by the agent")
    protected String conversationID;
    
    @ApiObjectField(description="An opaque container of context information by agent ID, that the agent can return in a response and CoPilot will guarantee that this context will send back to the agent in subsequent requests. "
            + "Can be any JSON structure")
    protected Collection<AgentContext> agentContext;
    
    @ApiObjectField(description="Context information that CoPilot will send to agents in each request. Contains context info about the UI, user and tenant that might be needed by the agent to process requests")
    protected CopilotContext copilotContext;
    
    @ApiObjectField(description="An array of resolved intents. An agent can respond with resolved intents when processing a request and previously resolved intents will be passed back to the agent in subsequent requests (stateless request/response model). "
            + "For more details see the documentation on ResolvedIntent")
    protected Collection<IResolvedIntent> resolvedIntents;

    public AIPayloadBase() {
        this.agentContext = new ArrayList<AgentContext>();
    }

    public String getConversationID() {
        return conversationID;
    }

    public void setConversationID(String conversationID) {
        this.conversationID = conversationID;
    }

    public Collection<AgentContext> getAgentContext() {
        return agentContext;
    }

    public void setAgentContext(Collection<AgentContext> agentContext) {
        this.agentContext = agentContext;
    }

    public void addAgentContext(AgentContext context) {
        this.agentContext.add(context);
    }

    public CopilotContext getCopilotContext() {
        return copilotContext;
    }

    public void setCopilotContext(CopilotContext copilotContext) {
        this.copilotContext = copilotContext;
    }

    public Collection<IResolvedIntent> getResolvedIntents() {
        return resolvedIntents;
    }

    public void setResolvedIntents(Collection<IResolvedIntent> resolvedIntents) {
        this.resolvedIntents = resolvedIntents;
    }

    @JsonIgnore
    public void addResolvedIntent(IResolvedIntent resolvedIntent) {
        if (this.resolvedIntents == null) {
            this.resolvedIntents = new ArrayList<IResolvedIntent>();
        }
        this.resolvedIntents.add(resolvedIntent);
    }

    @JsonIgnore
    public boolean hasUniqueResolvedIntent() {
        if (this.resolvedIntents == null) {
            this.resolvedIntents = new ArrayList<IResolvedIntent>();
        }
        return this.resolvedIntents.size() == 1;
    }

    /**
     * @return IResolvedIntent return the resolved intent, if it is the only one
     *         in the request
     */
    @JsonIgnore
    public IResolvedIntent getUniqueResolvedIntent() {
        if (hasUniqueResolvedIntent()) {
            return this.resolvedIntents.iterator().next();
        }
        return null;
    }
    
    @JsonIgnore
    public IResolvedIntent getHighestConfidenceResolvedIntent() {
        if (this.resolvedIntents == null) {
            this.resolvedIntents = new ArrayList<IResolvedIntent>();
        }
        IResolvedIntent highestConfidenceResolvedIntent = null;
        for (IResolvedIntent resolvedIntent : this.resolvedIntents) {
            if (highestConfidenceResolvedIntent == null
                    || resolvedIntent.getConfidence() > highestConfidenceResolvedIntent.getConfidence()) {
                highestConfidenceResolvedIntent = resolvedIntent;
            }
        }
        return highestConfidenceResolvedIntent;
    }

}
