package com.sap.fiori.copilot.agentmodel.results.object;

import java.util.ArrayList;
import java.util.List;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="ODataServiceInfo", group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK)
public class OData {
    
    @ApiObjectField
    protected String edmEntityType;
    
    @ApiObjectField
    protected String serviceRoot;
    
    @ApiObjectField
    protected String entityPath;
    
    @ApiObjectField
    protected String urlParams;
    
    @ApiObjectField
    @Deprecated
    protected String annotationUrl;
    
    @ApiObjectField
    protected List<String> annotationUris;

    public List<String> getAnnotationUris() {
		return annotationUris;
	}

	public void setAnnotationUris(List<String> annotationUris) {
		this.annotationUris = annotationUris;
	}
	
	public void addAnnotationUri(String annotationUri) {
		if (annotationUri != null) {
			if (this.annotationUris == null) {
				this.annotationUris = new ArrayList<String>(1);
			}
			this.annotationUris.add(annotationUri);
		}
	}

	public String getEdmEntityType() {
        return edmEntityType;
    }

    public void setEdmEntityType(String edmEntityType) {
        this.edmEntityType = edmEntityType;
    }

    public String getServiceRoot() {
        return serviceRoot;
    }

    public void setServiceRoot(String serviceRoot) {
        this.serviceRoot = serviceRoot;
    }

    public String getEntityPath() {
        return entityPath;
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public String getUrlParams() {
        return urlParams;
    }

    public void setUrlParams(String urlParams) {
        this.urlParams = urlParams;
    }

    public String getAnnotationUrl() {
        return annotationUrl;
    }

    public void setAnnotationUrl(String annotationUrl) {
        this.annotationUrl = annotationUrl;
    }
    
    

    @Override
    public String toString() {
        return "OData [edmEntityType=" + edmEntityType + ", serviceRoot=" + serviceRoot + ", entityPath=" + entityPath
                + ", urlParams=" + urlParams + ", annotationUrl=" + annotationUrl + "]";
    }

}
