package com.sap.fiori.copilot.agentmodel.skills.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "text" })
public class Value {

    public Value() {};
    
    public Value(String text) {
        super();
        this.text = text;
    }
    
    @JsonProperty("text")
    private String text;
   

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

  

    @Override
    public String toString() {
        return "Value [text=" + text + "]";
    }

 

}
