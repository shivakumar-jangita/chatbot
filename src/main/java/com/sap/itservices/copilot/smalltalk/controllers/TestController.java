package com.sap.itservices.copilot.smalltalk.controllers;

import com.sap.fiori.copilot.agentmodel.AIRequest;
import com.sap.itservices.copilot.smalltalk.dialog.Dialog;
import com.sap.itservices.copilot.smalltalk.recast.RecastService;
import com.sap.itservices.copilot.smalltalk.recast.dialog.RecastDialogMessage;
import com.sap.itservices.copilot.smalltalk.recast.dialog.RecastDialogResponse;
import com.sap.itservices.copilot.smalltalk.recast.text.RecastTextMessage;
import com.sap.itservices.copilot.smalltalk.recast.text.RecastTextResponse;
import com.sap.itservices.copilot.smalltalk.services.DialogManagerService;
import com.sap.itservices.copilot.smalltalk.services.HttpImageFetcherService;
import com.sap.itservices.copilot.smalltalk.services.SkillProcessorDispatcher;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.POST;
import java.io.IOException;
import java.util.UUID;


/**
 * A rest controller for testing purpose.
 */

@RestController
@RequestMapping("test")
public class TestController {


    
    @Autowired
    private RecastService recastService;

    @Autowired
    DialogManagerService dialogManagerdialogManager;

    
    @Autowired
    HttpImageFetcherService imageFetcherService;


    @GetMapping("/recast/text")
    public RecastTextResponse doTestRecast() {
        
        RecastTextMessage message = new RecastTextMessage();
        message.setText("How are you doing, Michael?");
        RecastTextResponse response = recastService.analyseText(message);
        return response;
    }

    @PostMapping("/recast/text")
    public RecastTextResponse doTestRecast(@RequestBody String textBody) {

        RecastTextMessage message = new RecastTextMessage();
        message.setText(textBody);
        RecastTextResponse response = recastService.analyseText(message);
        return response;
    }

    
    @PostMapping("/recast/dialog")
    public RecastDialogResponse dialogTestWithRecast(@RequestBody RecastDialogMessage dialogMessage){
        RecastDialogResponse dialogResponse = recastService.sendDialogText(dialogMessage);
        return dialogResponse;
    }


    @GetMapping("/test/log")
    public String testLog(){
        ILoggerFactory loggerFactory = LoggerFactory.getILoggerFactory();
        return loggerFactory.toString();
    }
    

//    @PostMapping("/copilot/dialog")
//    public RecastDialogMessage testCoPilotDialog(@RequestBody RecastDialogMessage dialogMessage){
//
//        String conversationId = dialogMessage.getConversationId();
//        Dialog dialog = dialogManagerdialogManager.getDialog(conversationId);
//
//        AIRequest request = new AIRequest();
//
//
//
//    }


    
    @GetMapping("/image")
    public ResponseEntity<byte[]> showImage() throws IOException {

        HttpImageFetcherService.HttpImageResult imageResult = imageFetcherService.
                getImage("https://www.visioncritical.com/wp-content/uploads/2014/12/BLG_Andrew-G.-River-Sample_09.13.12.png");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, imageResult.getMediaType());

        ResponseEntity<byte[]> responseEntity = ResponseEntity
                .ok()
                .headers(headers)
                .body(imageResult.getByteBuffer().array());

        return responseEntity;
    }
}