package com.sap.itservices.copilot.smalltalk.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.fiori.copilot.agentmodel.AIRequest;
import com.sap.fiori.copilot.agentmodel.AIResponse;
import com.sap.fiori.copilot.agentmodel.skills.Skill;
import com.sap.itservices.copilot.smalltalk.dialog.Dialog;
import com.sap.itservices.copilot.smalltalk.services.DialogManagerService;
import com.sap.itservices.copilot.smalltalk.services.HttpImageFetcherService;
import com.sap.itservices.copilot.smalltalk.services.SkillsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@RestController
public class AgentRequestController {


    private static final Logger LOGGER = LoggerFactory.getLogger(AgentRequestController.class);


    private SkillsService skillsService;
    


    @Autowired
    DialogManagerService dialogManager;

    @Autowired
    HttpImageFetcherService imageFetcherService;


    @Autowired
    ObjectMapper objectMapper;

    


    @GetMapping("/copilot/skills")
    public Collection<Skill> skills(){
        return skillsService.getAvailableSkills();
    }

    @GetMapping("/copilot/ask/skills")
    public Collection<Skill> askSkills(){
        return skillsService.getAvailableSkills();
    }                                                                

    public AgentRequestController(){}

    
    @Autowired
    public AgentRequestController(SkillsService skillsService){
        this.skillsService = skillsService;
    }


    
    @PostMapping("/copilot/ask")
    public AIResponse ask(@RequestBody AIRequest request) {
        
        LOGGER.debug("AIRequest is coming. {}", request);
        LOGGER.debug("AIRequest conversation id is {}", request.getConversationID());
        Dialog dialog = dialogManager.getDialog(request);
        AIResponse response = dialog.handleRequest(request);
        String jsonString = jsonToString(response);
        LOGGER.debug("AIResponse json is {}", jsonString);
        return response;
        
    }



    
    
    @GetMapping("/")
    public void swaggerWelcome(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }


    
    public String jsonToString(Object jsonObj){
        try {
            return objectMapper.writeValueAsString(jsonObj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }



    

}
