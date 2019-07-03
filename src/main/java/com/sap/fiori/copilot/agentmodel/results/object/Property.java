package com.sap.fiori.copilot.agentmodel.results.object;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="ObjectProperty", group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK)
public class Property {

    public enum PredefinedID {
        title, subtitle, type_name
    }
    
    @ApiObjectField
    protected String id;
    
    @ApiObjectField
    protected String odataPropertyPath;
    
    @ApiObjectField
    protected String dataType;
    
    @ApiObjectField
    protected String label;
    
    @ApiObjectField
    protected String sapSemantics;
    
    @ApiObjectField
    protected String sapType;
    
    @ApiObjectField
    protected String value;
    
    @ApiObjectField
    protected String url;

    public static Property createPredefinedProperty(PredefinedID id, String text, String url) {
        Property property = new Property();
        property.setIdEnum(id);
        property.setValue(text);
        property.setUrl(url);
        return property;
    }

    public String getId() {
        return id;
    }

    public void setIdEnum(PredefinedID id) {
        this.setId(id.toString());
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOdataPropertyPath() {
        return odataPropertyPath;
    }

    public void setOdataPropertyPath(String odataPropertyPath) {
        this.odataPropertyPath = odataPropertyPath;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSapSemantics() {
        return sapSemantics;
    }

    public void setSapSemantics(String sapSemantics) {
        this.sapSemantics = sapSemantics;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSapType() {
        return sapType;
    }

    public void setSapType(String sapType) {
        this.sapType = sapType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Property [id=" + id + ", odataPropertyPath=" + odataPropertyPath + ", dataType=" + dataType + ", label="
                + label + ", sapSemantics=" + sapSemantics + ", sapType=" + sapType + ", value=" + value + ", url="
                + url + "]";
    }

}
