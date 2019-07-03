package com.sap.fiori.copilot.agentmodel.results.actions;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="Action", group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK)
public class Action {
    
    public static final String SEARCH_ACTION_LIST_ID = "__SEARCH_ACTIONS__";
    
    public enum ActionType {
        // this action type is used for actions that request the digital
        // assistant to act on an associated resolved intent which will be
        // persisted with the action, assuming the action can be
        // executed not only within the conversation it was created, but also at
        // a later point in time.
        // These actions will send requests of type "ACT"
        DA_EXECUTE_INTENT,

        // this action type is not associated with an intent but only lives
        // within the conversation it is created in. These actions are one-time
        // actions
        // and clicking on an action button is equivalent to the user exactly
        // typing the text on the button as a response. These types of actions
        // are just
        // shortcuts for a user's utterance, but not a self-contained action
        // (including different intent) in their own right.
        // These actions will send requests of type "CONVERSE"
        DA_PROCESS_UTTERANCE,

        // this action does not involve a digital assistant callback but they
        // are processed only on the client side
        CLIENT_SIDE
    }

    // list of supported native Copilot client side actions
    public enum ClientSideActionIds {
        TAKE_SCREENSHOT, NAVIGATE_TO_URL, UPLOAD_IMAGE
    }

    public enum UIType {
        BUTTON, LISTITEM, AUTOEXECUTE
    }
    
    @ApiObjectField
    protected String type;
    
    @ApiObjectField
    protected String text;
    
    @ApiObjectField
    protected String uitype;
    
    @ApiObjectField
    protected String actionid;

    // can either be an absolute url (starting with a URI protocol, like
    // https://) or a Fiori launchpad hash, starting with "#"
    @ApiObjectField
    protected String url;
    
    @ApiObjectField
    protected ActionContext context;
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTypeEnum(ActionType actionType) {
        this.setType(actionType.toString());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUitype() {
        return uitype;
    }

    public void setUitype(String uitype) {
        this.uitype = uitype;
    }

    public void setUitypeEnum(UIType uitype) {
        this.setUitype(uitype.toString());
    }

    public String getActionid() {
        return actionid;
    }

    public void setActionid(String actionid) {
        this.actionid = actionid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    

    public ActionContext getContext() {
        return context;
    }

    public void setContext(ActionContext context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "Action [type=" + type + ", text=" + text + ", uitype=" + uitype + ", actionid=" + actionid + ", url="
                + url + ", context=" + context + "]";
    }

}