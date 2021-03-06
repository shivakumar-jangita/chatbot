package com.sap.fiori.copilot.agentmodel.results.text;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;
import com.sap.fiori.copilot.agentmodel.results.actions.Action;
import com.sap.fiori.copilot.agentmodel.results.collection.CollectionItemContextData;
import com.sap.fiori.copilot.agentmodel.results.collection.ICollectionItemResult;
import com.sap.fiori.copilot.agentmodel.results.collection.ICopilotEntityResult;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.ArrayList;
import java.util.Collection;

@ApiObject(name="CollectionItemTextResult", group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK, description="Result for adding simple text messages (questions, answers, chat messages) into the CoPilot conversation")
public class CollectionItemTextResult implements ICopilotEntityResult, ICollectionItemResult {
    
    public static final String ITEM_SUBTYPE_SEARCHABLE_ACTIONLIST = "SRCH_ACTN";
    
    @ApiObjectField(description="The text of the message")
    protected String text;
    
    @ApiObjectField(description="An array of actions that should be rendered with the item. Actions are usually rendered as a value help list or buttons, and the user can click on an action and trigger it")
    protected Collection<Action> actions;
    
    @ApiObjectField(description="Any type of additional JSON information that should be stored with the collection item. This can later be used at runtime for further processing on"
        + " collection item level and will also be available when reading collection items via CoPilot's OData service.")
    protected CollectionItemContextData itemContextData;
    
    // optional guid generated by agent, so that agent can later reference the
    // created object from Copilot again
    @ApiObjectField(description="CoPilot generates guids for all persisted objects. However, if the agent wants to specifically provide a guid, so that it can be also stored on the agent side and referenced later "
            + ", it can be provided beforehand and CoPilot will persist the object using the provided Guid. This should really be a Guid so that uniqueness is guaranteed.")
    protected String guid;
    
    
    @ApiObjectField(description="Mandatory technical field that tells CoPilot what type of result this is", allowedvalues={"text"}, required=true)
    protected String type;
    
    @ApiObjectField(description="Optional type string for fine classifying collection item results. Can be used to later filter collection items. Limited to 10 characters!")
    protected String subType;
    
    
    public CollectionItemTextResult() {
        actions = new ArrayList<Action>();
        type = "text";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getType() {
        return type;
    }

    public String getGuid() {
        return this.guid;
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public Collection<Action> getActions() {
        return actions;
    }

    public void setActions(Collection<Action> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "CollectionItemTextResult [text=" + text + ", actions=" + actions + ", guid=" + guid + "]";
    }

    public void setItemContextData(CollectionItemContextData itemContextData) {
        this.itemContextData = itemContextData;
    }

    public CollectionItemContextData getItemContextData() {
        // TODO Auto-generated method stub
        return itemContextData;
    }
    
    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }



    // for documenation generation only
    @ApiObjectField(description="Mandatory technical attribute that makes sure CoPilot deserializes this JSON object into the right Java class at runtime. Must be set to the value given below!", 
            name="@class", 
            required=true, 
            allowedvalues="com.sap.fiori.copilot.agentmodel.results.text.CollectionItemTextResult")
    @JsonIgnore
    private String _documentationOnlyClassname;
}
