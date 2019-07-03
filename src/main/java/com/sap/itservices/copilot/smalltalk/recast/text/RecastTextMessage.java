package com.sap.itservices.copilot.smalltalk.recast.text;

import com.sap.itservices.copilot.smalltalk.recast.RecastMessage;
import org.json.JSONObject;

public class RecastTextMessage implements RecastMessage {

    private String language ="en";
    private String text;


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toJsonString() {

        JSONObject json = new JSONObject();
        json.put("text", text);
        json.put("language", language);
        return json.toString();
    }
}
