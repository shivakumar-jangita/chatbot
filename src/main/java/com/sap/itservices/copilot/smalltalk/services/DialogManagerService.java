package com.sap.itservices.copilot.smalltalk.services;

import com.sap.fiori.copilot.agentmodel.AIRequest;
import com.sap.itservices.copilot.smalltalk.dialog.Dialog;
import com.sap.itservices.copilot.smalltalk.recast.RecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DialogManagerService {


    @Autowired
    private RecastService recastService;

    @Autowired
    private SkillProcessorDispatcher skillProcessorDispatcher;

    private Map<String, Dialog> dialogs =new ConcurrentHashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(DialogManagerService.class);
    

    public Dialog getDialog(String conversationId){

         Dialog dialog = dialogs.get(conversationId);
         if (dialog == null){
             LOGGER.debug("{} id doesn't exist. Create a new one. RecastService is {}, " +
                     "conversationId is {}, SkillProcessorDispatcher is {}", skillProcessorDispatcher);
             dialog = new Dialog(recastService, conversationId, skillProcessorDispatcher);
             dialogs.put(conversationId, dialog);
         }
         return dialog;
    }


    public void removeDialog(String conversationId){
        dialogs.remove(conversationId);
    }

    public void removeDialog(Dialog dialog){
        removeDialog(dialog.getConversationId());
    }
    
    
    public Dialog getDialog(AIRequest aiRequest){
        return this.getDialog(aiRequest.getConversationID());
    }
    
}
