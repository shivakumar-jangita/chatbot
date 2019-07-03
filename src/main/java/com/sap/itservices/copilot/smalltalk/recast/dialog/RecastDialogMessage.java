package com.sap.itservices.copilot.smalltalk.recast.dialog;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.itservices.copilot.smalltalk.recast.RecastMessage;
import org.json.JSONObject;

import javax.validation.constraints.NotNull;

public class RecastDialogMessage implements RecastMessage {

    private String content;
    private String type = "text";

    @NotNull
    @JsonProperty("conversation_id")
    private String conversationId;
    private String language = "en";


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toJsonString() {

        JSONObject jsonObject = new JSONObject();
        JSONObject messageJson = new JSONObject();
        messageJson.put("content", content);

        messageJson.put("type", type);
        jsonObject.put("message", messageJson);
        jsonObject.put("conversation_id", conversationId);


        jsonObject.put("language", language);
        return jsonObject.toString();
    }



    
}
