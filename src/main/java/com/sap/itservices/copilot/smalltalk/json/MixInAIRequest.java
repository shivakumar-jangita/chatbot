package com.sap.itservices.copilot.smalltalk.json;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.InputStream;

abstract public class MixInAIRequest {
    @JsonIgnore
    abstract public InputStream getSpeechInputStream();
}
