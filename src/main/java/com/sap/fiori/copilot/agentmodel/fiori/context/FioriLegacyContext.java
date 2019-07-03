package com.sap.fiori.copilot.agentmodel.fiori.context;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.pojo.ApiStage;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="FioriLegacyContext",group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK,description="Maintain compatibility with previous Agent versions. ", stage=ApiStage.DEPRECATED)
public class FioriLegacyContext {
	
    @ApiObjectField
    protected String semanticObject;
    
    @ApiObjectField
    protected String intendNavigationUrl;


    public String getSemanticObject() {
        return semanticObject;
    }

    public void setSemanticObject(String semanticObject) {
        this.semanticObject = semanticObject;
    }

    public String getIntendNavigationUrl() {
        return intendNavigationUrl;
    }

    public void setIntendNavigationUrl(String intendNavigationUrl) {
        this.intendNavigationUrl = intendNavigationUrl;
    }

    @Override
    public String toString() {
        return "Fiori [semanticObject=" + semanticObject + ", intendNavigationUrl=" + intendNavigationUrl + "]";
    }
}
