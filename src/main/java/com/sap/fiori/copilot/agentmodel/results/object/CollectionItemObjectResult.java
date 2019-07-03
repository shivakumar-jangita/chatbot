package com.sap.fiori.copilot.agentmodel.results.object;

import java.util.ArrayList;
import java.util.Collection;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.context.data.ContextData;
import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;
import com.sap.fiori.copilot.agentmodel.fiori.context.FioriLegacyContext;
import com.sap.fiori.copilot.agentmodel.results.text.CollectionItemTextResult;

@ApiObject(name="CollectionItemObjectResult", group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK)
public class CollectionItemObjectResult extends CollectionItemTextResult {

  

	public enum Type {
        object, link, user, video
    }
    
    @ApiObjectField
    protected OData odata;
    @ApiObjectField
    protected Collection<Property> properties;
    
    @ApiObjectField(description="<em>@Deprecated</em> but kept for compatibility. Please use field <code>objectContextData</code> instead")
    @Deprecated
    protected FioriLegacyContext fiori;
    @ApiObjectField
    protected Badge badge;
    
    @ApiObjectField
    protected String objectType;
    
 
	@ApiObjectField(description="May Contain Object specific data in a \"custom\" property and/or a <code>NavigationContext</code> that can be used to navigate to a decidated link in the context of this object result" )
    protected ContextData objectContextData;
    
    public ContextData getObjectContextData() {
		return objectContextData;
	}

	public void setObjectContextData(ContextData objectContextData) {
		this.objectContextData = objectContextData;
	}

	public CollectionItemObjectResult() {
        properties = new ArrayList<Property>();
        type = Type.object.toString();
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getObjectType() {
 		return objectType;
 	}

 	public void setObjectType(String objectType) {
 		this.objectType = objectType;
 	}


    public void setTypeEnum(Type type) {
        this.setType(type.toString());
    }

    public OData getOdata() {
        return odata;
    }

    public void setOdata(OData odata) {
        this.odata = odata;
    }

    public Collection<Property> getProperties() {
        return properties;
    }

    public void setProperties(Collection<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property) {
        this.properties.add(property);
    }
    
    @Deprecated  
    public FioriLegacyContext getFiori() {
        return fiori;
    }
    @Deprecated
    public void setFiori(FioriLegacyContext fiori) {
        this.fiori = fiori;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }
    
	@Override
    public String toString() {
        return "CollectionItemObjectResult [odata=" + odata + ", properties=" + properties + ", fiori=" + fiori
                + ", badge=" + badge + ", type=" + type +  "]";
    }
    
 // for documenation generation only
    @ApiObjectField(description="Mandatory technical attribute that makes sure CoPilot deserializes this JSON object into the right Java class at runtime. Must be set to the value given below!", 
            name="@class", 
            required=true, 
            allowedvalues="com.sap.fiori.copilot.agentmodel.results.object.CollectionItemObjectResult")
    @JsonIgnore
    private String _documentationOnlyClassname;
    
    @ApiObjectField(name="type", description="Mandatory technical field that tells CoPilot what type of result this is", allowedvalues={"object", "link", "user"}, required=true)
    @JsonIgnore
    private String _documentationOnlyType;

}
