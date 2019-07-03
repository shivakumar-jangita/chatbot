package com.sap.fiori.copilot.agentmodel.context;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="CopilotContext", group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK, description="The context information that CoPilot sends in every request to the agents. The context contains information about the current UI context as well as user and tenant context that the "
        + "request is made in")
public class CopilotContext {
    
    @ApiObjectField(description="Context information from the client side, such as source system, application metadata, currently open collection, etc. Check the object for more details.")
    private ClientData clientData;
    
    @ApiObjectField(description="The user that originated this request")
    private CopilotUser copilotUser;
    
    @ApiObjectField(description="The GUID of the CoPilot tenant for which this request is made")
    private String tenantId;
    
    @ApiObjectField(description="The SCP account ID for which this request is made. There is a de facto 1:1 mapping between account id and tenant id.")
    private String accountId;
    
    @ApiObjectField(description="The OAuth Token URL the agent has to call to retrieve a valid OAuth 2.0 login token for this tenant."
            + " The OAuth client id is sent in the request in HTTP header 'X-Copilot-Oauthclient'.")
    private String oauthTokenUrl;
    
    @ApiObjectField(description="The URL of the Copilot OData endpoint for the current tenant.")
    private String odataAPIUrl;
    
    @ApiObjectField(description="The GUID of the digital assistant collection for the current user and source system")
    private String currentDACollectionGuid;
    
    @ApiObjectField(description="The SAP product type from which the request originated. Possible values: S4_PC (S4 Public Cloud), S4_OP(S4 On Premise), SFSF_TA (Success Factors Talent Management), IBP_OD (Integrated Business Planning), S4_ME (S/4 Marketing Edition), IOT_BR (IoT Leonardo), FCE (Fiori Cloud Edition)")
    private String productType;

    public ClientData getClientData() {
        return clientData;
    }

    public void setClientData(ClientData clientData) {
        this.clientData = clientData;
    }
    
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public CopilotUser getCopilotUser() {
        return copilotUser;
    }

    public void setCopilotUser(CopilotUser copilotUser) {
        this.copilotUser = copilotUser;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOauthTokenUrl() {
        return oauthTokenUrl;
    }

    public void setOauthTokenUrl(String oauthTokenUrl) {
        this.oauthTokenUrl = oauthTokenUrl;
    }

    public String getOdataAPIUrl() {
        return odataAPIUrl;
    }

    public void setOdataAPIUrl(String odataAPIUrl) {
        this.odataAPIUrl = odataAPIUrl;
    }

    public String getCurrentDACollectionGuid() {
        return currentDACollectionGuid;
    }

    public void setCurrentDACollectionGuid(String currentDACollectionGuid) {
        this.currentDACollectionGuid = currentDACollectionGuid;
    }

    @Override
    public String toString() {
        return "CopilotContext [clientData=" + clientData + ", copilotUser=" + copilotUser + ", tenantId=" + tenantId
                + ", accountId=" + accountId + ", oauthTokenUrl=" + oauthTokenUrl + ", odataAPIUrl=" + odataAPIUrl
                + "]";
    }

}
