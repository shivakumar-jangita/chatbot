package com.sap.fiori.copilot.agentmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name="ResolvedIntent", group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK)
public class ResolvedIntent implements IResolvedIntent {
    public static final String SYSTEMAGENTID = "system";
    
    @ApiObjectField(description="This object is in JSON format, and contains detailed information"
     + " that is specific to the agent that returns the ResolvedIntent. "
     + "The details object allows you to store information during a conversation with the user. "
     + "The CoPilot digital assistant protocol is stateless and this details object "
     + "allows you to store the state throughout the conversation. "
     + "This way, you can keep the information from the utterances received in the previous requests that is needed "
     + "to execute the request. ")
    protected Object details;
    
    @ApiObjectField(description="The ID of the agent that created that resolved intent")
    protected String agentID;
    
    @ApiObjectField(description="Design time intent", required=true)
    protected IIntent intent;
    
    @ApiObjectField(description="Confidence level that the resolved intent matches "
            + "based on the number of parameter matches. "
            + "This confidence level ranges from 0 to 1 for the uttrance received in the request. "
            + "The confidence level will be compared with that of other agents and the agent with the highest confidence "
            + "in combination with the agent priority will be selected as the answer and the response from this agent "
            + "will be presented to the user. ", allowedvalues={"0..lowest", "1..highest"}, required=true)
    /*
     * This confidence level will be compared with other agents and the agent with 
     * the highest confidence in combination with the agent priority will determine which answer is chosen and presented to the user...."
     */
    protected double confidence; // number of parameter matches
    
    @ApiObjectField(description="Agents can decide if they need a callback from CoPilot to execute an "
     + " intent. For example: User: 'I want to create a sales order' should not"
     + " create a draft of a sales order object immediately. The agent should"
     + " return a resolved intent with a confidence. The results from all agents"
     + " are gathered and the best one is selected. If the needsExectionCallback"
     + " is true for the selected resolved intent then CoPilot calls this agent"
     + " with the resolved intent so they agent can create the draft sales order"
     + " object. Some resolved intents might not need a callback and the initial"
     + " request can contain the results already (for queries for example)")
    @JsonProperty("needsExecutionCallback")
    protected boolean needsExecutionCallback;
    
    
    @ApiObjectField(description="Name can be used for intent disambiguation buttons e.g. did you want to "
     + "'Query products' or 'Create a product'", required=true)
    protected String name;
    
    @ApiObjectField(description=" Explanation can be used for intent disambiguation, for example: Let me"
     + " clarify. Do you want to see help information on negative budgets in the"
     + " abc application [returned by DSX] see a list of actual negative budgets"
     + " in the system [returned by the OData agent]")
    protected String explanation;

    public ResolvedIntent() {
        this.intent = new BaseIntent();
        this.agentID = SYSTEMAGENTID;
        this.confidence = 0.0;
        this.needsExecutionCallback = true;
    }

    public ResolvedIntent(String agentID) {
        this();
        this.agentID = agentID;
    }

    public IIntent getIntent() {
        return intent;
    }

    public void setIntent(IIntent intent) {
        this.intent = intent;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public String getAgentID() {
        return agentID;
    }

    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }

    public Object getDetails() {
        return this.details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ResolvedIntent [details=" + details + ", agentID=" + agentID + ", intent=" + intent + ", confidence="
                + confidence + "]";
    }

    public boolean needsExecutionCallback() {
        return this.needsExecutionCallback;
    }

    public void setNeedsExecutionCallback(boolean needsExecutionCallback) {
        this.needsExecutionCallback = needsExecutionCallback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

}
