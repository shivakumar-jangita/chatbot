package com.sap.fiori.copilot.agentmodel.skills.model;

import java.util.List;

public class Sentences {

    private String title;
    private String type;
    private List<Group> groups = null;

    public Sentences(String title, String type, List<Group> groups) {
        super();
        this.title = title;
        this.type = type;
        this.groups = groups;
    }

    public Sentences() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Command [title=" + title + ", type=" + type + ", groups=" + groups + "]";
    }

}
