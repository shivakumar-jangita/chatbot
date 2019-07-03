package com.sap.itservices.copilot.smalltalk.recast;

import org.json.JSONObject;

public class Intent {
    private String name;
    private double confidence;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public Intent(String name, double confidence, String description) {
        this.name = name;
        this.confidence = confidence;
        this.description = description;
    }

    public Intent(JSONObject obj) {
        this(obj.optString("slug"), obj.optDouble("confidence"), obj.optString("description"));
    }

    public double getConfidence() {
        return confidence;
    }

    public String getName() {
        return name;
    }
}
