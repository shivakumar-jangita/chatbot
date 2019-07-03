package com.sap.fiori.copilot.agentmodel;

import java.io.InputStream;
import java.util.Collection;

import com.sap.fiori.copilot.agentmodel.context.AgentContext;
import com.sap.fiori.copilot.agentmodel.context.CopilotContext;

public interface IRequest {
    enum AIRequestType {
        ASK, ACT, CONVERSE, REFRESH
    }

    /**
     * @return String Returns the users utterance if it was typed.
     */
    String getText();

    /**
     * @param text
     *            Set the users utterance (if it was typed)
     */
    void setText(String text);

    /**
     * A request can contain a list of agents that the request should be sent
     * to. If this collection is empty then the default agents are being called
     * 
     * @return Collection<String> Agents that should be used for this request
     */
    Collection<String> getRequestedAgents();

    /**
     * A request can contain a list of agents that the request should be sent
     * to. If this collection is empty then the default agents are being called
     * 
     * @param requestedAgents
     *            Set the agent IDs that should be used for this request
     */
    void setRequestedAgents(Collection<String> requestedAgents);

    /**
     * In case of a multi-tenancy set the source system determines which intents
     * are available
     * 
     * @return String The source system for provisioned intents
     */
    String getSourceSystem();

    /**
     * Set the source system in a multi-tenancy scenario
     * 
     * @param sourceSystem
     *            ID of the source system (either launchpad site ID or backend
     *            destination name)
     */
    void setSourceSystem(String sourceSystem);

    /**
     * In case of users utterance via voice
     * 
     * @return InputStream of the user voice
     */
    InputStream getSpeechInputStream();

    /**
     * Users utterance via voice
     * 
     * @param speechInputStream
     *            of the users voice
     */
    void setSpeechInputStream(InputStream speechInputStream);
    
    /**
     * 
     * @return true if user input was voice
     */
    boolean isSpeechInputUsed();

    /**
     * Set to true if user input was voice
     * 
     * @param speechInputUsed
     *          
     */
    void setSpeechInputUsed(boolean speechInputUsed);

    /**
     * Resolved intents of the active conversation (during a conversation with
     * the user resolved intents contain the recognized parameter values and
     * repliques)
     * 
     * @return Collection<IResolvedIntent> Return the list resolved intents from
     *         previous requests
     */
    Collection<IResolvedIntent> getResolvedIntents();

    /**
     * Resolved intents of the active conversation (during a conversation with
     * the user resolved intents contain the recognized parameter values and
     * repliques)
     * 
     * @param resolvedIntents
     *            Set the resolved intent from a previous request
     */
    void setResolvedIntents(Collection<IResolvedIntent> resolvedIntents);

    /**
     * Resolved intents from a previous (closed) request (during a conversation
     * with the user resolved intents contain the recognized parameter values
     * and repliques)
     * 
     * @return Collection<IResolvedIntent> Return the list resolved intents from
     *         previous requests
     */
    Collection<IResolvedIntent> getPreviousResolvedIntents();

    /**
     * Resolved intents from a previous (closed) request (during a conversation
     * with the user resolved intents contain the recognized parameter values
     * and repliques)
     * 
     * @param resolvedIntents
     *            Set the resolved intent from a previous request
     */
    void setPreviousResolvedIntents(Collection<IResolvedIntent> resolvedIntents);

    /**
     * The client context contains information from the currently running Fiori
     * application (URL, application name, etc.)
     * 
     * @return String serialized JSON object containing information about the
     *         currently running Fiori application
     */
    String getClientContext();

    /**
     * The client context contains information from the currently running Fiori
     * application (URL, application name, etc.)
     * 
     * @param clientContext
     *            Set the information about the currently running Fiori
     *            application as serialized JSON object String
     */
    void setClientContext(String clientContext);

    /**
     * The conversation ID controls how long resolved intents and contexts are
     * kept
     * 
     * @return String of the conversation ID that has been created and is
     *         maintained by the client
     */
    String getConversationID();

    /**
     * The conversation ID controls how long resolved intents and contexts are
     * kept
     * 
     * @param conversationID
     *            that has been created and is maintained by the client
     */
    void setConversationID(String conversationID);

    /**
     * The agent context contains serialized information that is agent specific
     * 
     * @return Collection<AgentContext> context information for the agent
     */
    Collection<AgentContext> getAgentContext();

    /**
     * The agent context contains serialized information that is agent specific
     * 
     * @param agentContext
     *            set context informatino for the agent
     */
    void setAgentContext(Collection<AgentContext> agentContext);

    /**
     * The copilot context contains information about the current user and
     * copilot collections
     * 
     * @return CopilotContext information regarding the copilot collection
     */
    CopilotContext getCopilotContext();

    /**
     * The copilot context contains information about the current user and
     * copilot collections
     * 
     * @param context
     *            set information regarding the copilot collection
     */
    void setCopilotContext(CopilotContext context);

    /**
     * @return If true the response will contain the results. If false the
     *         results will not be included in the response. (In the UI they
     *         will be visible because they are being pushed via web sockets
     *         channel).
     */
    boolean includeResults();

    /**
     * Sets a flag if the result payload should be included
     * 
     * @param includeResults
     *            true if the response should include the results of the
     *            request. false otherwise
     */
    void setIncludeResults(boolean includeResults);

    /**
     * @return Determines how the agent should handle the request based on its
     *         type: ASK: Evaluate request ONLY ACT: Execute resolved intent
     *         ONLY CONVERSE: Evaluate request and execute resolved intent
     */
    AIRequestType getRequestType();

    /**
     * Sets request type of the request
     * 
     * @param requestType
     */
    void setRequestType(AIRequestType requestType);
}
