package com.sap.fiori.copilot.agentmodel;

import java.util.Calendar;
import java.util.Collection;

import com.sap.fiori.copilot.agentmodel.context.AgentContext;
import com.sap.fiori.copilot.agentmodel.context.CopilotContext;

public interface IResponse {

    Collection<AgentContext> getAgentContext();

    void setAgentContext(Collection<AgentContext> agentContext);

    CopilotContext getCopilotContext();

    void setCopilotContext(CopilotContext copilotContext);

    Collection<IResolvedIntent> getResolvedIntents();

    void setResolvedIntents(Collection<IResolvedIntent> resolvedIntents);

    String getConversationID();

    void setConversationID(String conversationID);

    Calendar getTimestamp();

    String getResolvedRequestText();

    void setResolvedRequestText(String resolvedText);

    Collection<IAIResult> getResults();

    void setResults(Collection<IAIResult> results);

    boolean wasSuccess();

    void setWasSuccess(boolean wasSuccess);

    String getErrorMessage();

    void setErrorMessage(String message);

    boolean isConversationDone();

    void setConversationDone(boolean conversationDone);

    boolean shouldExecuteIntent();

    void setExecuteIntent(boolean executeIntent);

}
