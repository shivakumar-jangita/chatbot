package com.sap.fiori.copilot.agentmodel;

import java.net.URL;

public class DialogEngineConfiguration {
    private String dialogModelName;
    private URL scxmlURL;
    @SuppressWarnings("rawtypes")
    private Class scxmlImplementationClass;

    public String getDialogModelName() {
        return dialogModelName;
    }

    public void setDialogModelName(String dialogModelName) {
        this.dialogModelName = dialogModelName;
    }

    public URL getScxmlURL() {
        return scxmlURL;
    }

    public void setScxmlURL(URL scxmlURL) {
        this.scxmlURL = scxmlURL;
    }

    @SuppressWarnings("rawtypes")
    public Class getScxmlImplementationClass() {
        return scxmlImplementationClass;
    }

    @SuppressWarnings("rawtypes")
    public void setScxmlImplementationClass(Class scxmlImplementationClass) {
        this.scxmlImplementationClass = scxmlImplementationClass;
    }

    @Override
    public String toString() {
        return "DialogEngineConfiguration [dialogModelName=" + dialogModelName + ", scxmlURL=" + scxmlURL
                + ", scxmlImplementationClass=" + scxmlImplementationClass + "]";
    }

}
