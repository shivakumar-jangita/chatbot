package com.sap.fiori.copilot.agentmodel;

/**
 * @author i803573
 *
 */
public interface IResolvedIntent {

    /**
     * The ID of the agent that created that resolved intent
     * 
     * @return String ID of the agent
     */
    String getAgentID();

    /**
     * Design time intent
     * 
     * @return IIntent intent metadata
     */
    IIntent getIntent();

    /**
     * Level of confidence that the resolved intent matches
     * 
     * @return double confidence (0..lowest; 1..highest)
     */
    double getConfidence();

    /**
     * Agent specific data to help execute the intent
     * 
     * @return Object serialized agent specific data
     */
    Object getDetails();

    /**
     * Agents can decide if they need a callback from CoPilot to execute an
     * intent. For example: User: "I want to create a sales order" should not
     * create a draft of a sales order object immediately. The agent should
     * return a resolved intent with a confidence. The results from all agents
     * are gathered and the best one is selected. If the needsExectionCallback
     * is true for the selected resolved intent then CoPilot calls this agent
     * with the resolved intent so they agent can create the draft sales order
     * object. Some resolved intents might not need a callback and the initial
     * request can contain the results already (for queries for example)
     * 
     * @return true if the agent wants a callback from CoPilot to act on a
     *         resolved intent
     */
    boolean needsExecutionCallback();

    /**
     * Name can be used for intent disambiguation buttons e.g. did you want to
     * "Query products" or "Create a product"
     * 
     * @return a name describing the resolved intent e.g. "Query products",
     *         "Create leave request", etc.
     */
    String getName();

    /**
     * Explanation can be used for intent disambiguation, for example: Let me
     * clarify. Do you want to see help information on negative budgets in the
     * abc application [returned by DSX] see a list of actual negative budgets
     * in the system [returned by the OData agent]”
     * 
     * @return explanation of this intent
     */
    String getExplanation();
}