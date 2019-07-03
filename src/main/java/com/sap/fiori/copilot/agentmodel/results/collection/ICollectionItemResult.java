package com.sap.fiori.copilot.agentmodel.results.collection;

import java.util.Collection;

import com.sap.fiori.copilot.agentmodel.results.actions.Action;

public interface ICollectionItemResult extends ICopilotEntityResult {

    Collection<Action> getActions();
    Object getItemContextData();
    String getSubType();
}
