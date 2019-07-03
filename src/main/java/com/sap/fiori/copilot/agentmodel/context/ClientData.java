package com.sap.fiori.copilot.agentmodel.context;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="ClientData", group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK, description="The client side context information that CoPilot sends in every request to the agents.")
public class ClientData {
    
    @ApiObjectField(description="<em>@Deprecated</em> Use appMetadata instead! Application metadata (if applicable). For Fiori apps this will be the app descriptor JSON")
    @Deprecated
    private Object appDescriptor;
    
    @ApiObjectField(description="Application metadata (if applicable). For Fiori apps this will contain the app descriptor in a field 'appDescriptor'")
    private Object appMetadata;
    
    @ApiObjectField(description="If the request was made from the web client, this will contain the browser URL")
    private String browserUrl;
    
    @ApiObjectField(description="The user as it is logged in to the source system. Depending on the authentication method of the CoPilot endpoint used, this user might not "
            + "be identical with the user logged in to the CoPilot endpoint. For example, if a user is logged on to an ABAP hosted Fiori Launchpad, the user id might be different "
            + "from the user used to logon to CoPilot if SSO (SAML) is not used. This field will contain the ABAP user in this case.")
    private SourceSystemUser sourceSystemUser;
    
    @ApiObjectField(description="The source system where the request is coming from. This is a unique identifier of the logical system making the request, for example 'abap-UYZ415'")
    private String copilotSourceSystem;
    
    @ApiObjectField(description="The GUID of the current CoPilot collection that the user has open in the UI. If the user currently has no collection open, this field will be null.")
    private String currentCollectionGuid;
    
    
    @ApiObjectField(description="The platform of the client that makes the request")
    private String platform;
    
    public Object getAppDescriptor() {
        return appDescriptor;
    }

    public void setAppDescriptor(Object appDescriptor) {
        this.appDescriptor = appDescriptor;
    }
    
    public Object getAppMetadata() {
        return appMetadata;
    }

    public void setAppMetadata(Object appMetadata) {
        this.appMetadata = appMetadata;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getBrowserUrl() {
        return browserUrl;
    }

    public void setBrowserUrl(String browserUrl) {
        this.browserUrl = browserUrl;
    }

    public SourceSystemUser getSourceSystemUser() {
        return sourceSystemUser;
    }

    public void setSourceSystemUser(SourceSystemUser sourceSystemUser) {
        this.sourceSystemUser = sourceSystemUser;
    }

    public String getCopilotSourceSystem() {
        return copilotSourceSystem;
    }

    public void setCopilotSourceSystem(String copilotSourceSystem) {
        this.copilotSourceSystem = copilotSourceSystem;
    }

    public String getCurrentCollectionGuid() {
        return currentCollectionGuid;
    }

    public void setCurrentCollectionGuid(String currentCollectionGuid) {
        this.currentCollectionGuid = currentCollectionGuid;
    }

    @Override
    public String toString() {
        return "ClientData [appDescriptor=" + appDescriptor + ", browserUrl=" + browserUrl + ", sourceSystemUser="
                + sourceSystemUser + ", copilotSourceSystem=" + copilotSourceSystem + ", currentCollectionGuid="
                + currentCollectionGuid + "]";
    }

}
