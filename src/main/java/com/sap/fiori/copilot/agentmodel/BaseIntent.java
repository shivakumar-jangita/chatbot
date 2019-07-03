package com.sap.fiori.copilot.agentmodel;

public class BaseIntent implements IIntent {
    protected String guid;
    protected String name;
    protected String label;
    protected String domain;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "BaseIntent [guid=" + guid + ", name=" + name + ", label="
                + label + ", domain=" + domain + "]";
    }

}
