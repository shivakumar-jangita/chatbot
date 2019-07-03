package com.sap.fiori.copilot.agentmodel.results.quickcreate;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="QuickCreateProperty", group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK)
public class Property {
    
    @ApiObjectField
    private String key;
    
    @ApiObjectField
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Property [key=" + key + ", value=" + value + "]";
    }

}
