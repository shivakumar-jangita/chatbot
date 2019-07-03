package com.sap.fiori.copilot.agentmodel.skills.model;

import java.util.List;

public class Group {

    public Group() {
        super();
    }

    public Group(String groupName, List<Value> values) {
        super();
        this.groupName = groupName;
        this.values = values;
    }

    private String groupName;

    private List<Value> values = null;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Group [groupName=" + groupName + ", values=" + values + "]";
    }
}
