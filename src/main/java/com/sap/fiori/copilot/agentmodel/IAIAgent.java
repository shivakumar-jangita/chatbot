package com.sap.fiori.copilot.agentmodel;

import java.util.Collection;

import com.sap.fiori.copilot.agentmodel.skills.Skill;

/**
 * 
 * @author Martin Steiner
 *
 *         Represents an AI provider that can handle AI requests
 */
public interface IAIAgent<ResponseType, RequestType> {

    /**
     * @return ID for this provider
     */
    String getAgentID();

    Integer getPriority();
    
    /**
     * 
     * @return Collection of Skills used for help/"what can i do?" response
     */
    Collection<Skill> getSkills();

    /**
     * @param request
     *            request that should be processed
     * @return a response from this agent based on the request
     *       
     */
    AIResponse handleRequest(AIRequest request);

    

}
