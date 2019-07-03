package com.sap.fiori.copilot.agentmodel.context;

public class AgentContext {
    private String agentID;
    private Object agentData;

    public String getAgentID() {
        return agentID;
    }

    public void setAgentID(String providerID) {
        this.agentID = providerID;
    }

    public Object getAgentData() {
        return agentData;
    }

    public void setAgentData(Object providerData) {
        this.agentData = providerData;
    }

    @Override
    public String toString() {
        return "AgentContext [agentID=" + agentID + ", agentData=" + agentData + "]";
    }

}
