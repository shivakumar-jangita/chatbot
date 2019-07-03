package com.sap.fiori.copilot.agentmodel.results.actions;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.IResolvedIntent;
import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="ActionContext", group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK)
public class ActionContext {
    
    @ApiObjectField
    protected IResolvedIntent intent;
    
    @ApiObjectField
    protected String collectionGuid;
    
    @ApiObjectField
    protected String collectionItemGuid;
    
    
    public ActionContext(IResolvedIntent intent) {
        this(intent, null, null);
    }
    
    public ActionContext(IResolvedIntent intent, String collGuid, String collectionItemGuid) {
        this.intent = intent;
        this.collectionGuid = collGuid;
        this.collectionItemGuid = collectionItemGuid;
    }
    
    // standard constructor needed by jackson!
    public ActionContext() {
        super();
    }
    
    public IResolvedIntent getIntent() {
        return intent;
    }
    public void setIntent(IResolvedIntent intent) {
        this.intent = intent;
    }
    public String getCollectionGuid() {
        return collectionGuid;
    }
    public void setCollectionGuid(String collectionGuid) {
        this.collectionGuid = collectionGuid;
    }
    
    
    
    public String getCollectionItemGuid() {
        return collectionItemGuid;
    }

    public void setCollectionItemGuid(String collectionItemGuid) {
        this.collectionItemGuid = collectionItemGuid;
    }

    @Override
    public String toString() {
        return "ActionContext [intent=" + intent + ", collectionGuid=" + this.collectionGuid + ", collectionItemGuid=" + this.collectionItemGuid + "]"; 
    }
    
    
}
