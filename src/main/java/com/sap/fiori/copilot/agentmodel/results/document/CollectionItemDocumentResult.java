package com.sap.fiori.copilot.agentmodel.results.document;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.results.text.CollectionItemTextResult;

@ApiObject(description="Base 64 Document")
public class CollectionItemDocumentResult extends CollectionItemTextResult {
    
    
    public CollectionItemDocumentResult() {
        type="document";
    }
    
    @ApiObjectField(description="Base64 content")
    protected String base64Content;
    
    @ApiObjectField(description="Size of the document")
    protected int contentSize;
    
    @ApiObjectField(description="Type of the document")
    protected String contentType;
    

    public String getBase64Content() {
        return base64Content;
    }

    public void setBase64Content(String base64Content) {
        this.base64Content = base64Content;
    }

    public int getContentSize() {
        return contentSize;
    }

    public void setContentSize(int size) {
        this.contentSize = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    @Override
    public String toString() {
        return "CollectionItemDocumentResult [ text = " + this.text  + ", contentType = " + this.contentType + ", content = " + this.base64Content + " ]"; 
    }

}
