package com.sap.itservices.copilot.smalltalk.skillprocessors;

import com.sap.fiori.copilot.agentmodel.AIRequest;
import com.sap.fiori.copilot.agentmodel.AIResponse;
import com.sap.fiori.copilot.agentmodel.results.document.CollectionItemDocumentResult;
import com.sap.fiori.copilot.agentmodel.results.object.Badge;
import com.sap.fiori.copilot.agentmodel.results.object.CollectionItemObjectResult;
import com.sap.fiori.copilot.agentmodel.results.text.CollectionItemTextResult;
import com.sap.itservices.copilot.smalltalk.recast.Intent;
import com.sap.itservices.copilot.smalltalk.recast.dialog.RecastDialogResponse;
import com.sap.itservices.copilot.smalltalk.recast.dialog.RecastDialogResponse.Message;
import com.sap.itservices.copilot.smalltalk.services.HttpImageFetcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.util.List;


@RecastSkill({"toolsnames","tools-list"})
public class MessageReplyProcessor implements SkillProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageReplyProcessor.class);


    @Autowired
    public HttpImageFetcherService imageFetcherService;

    @Override
    public void process(AIRequest request, AIResponse response, RecastDialogResponse recastResponse) {

        LOGGER.debug("Try to process presales tools skill.");
        LOGGER.debug("AIRequest is {}", request);
        LOGGER.debug("AIResponse is {}", response);
        LOGGER.debug("RecastResponse is {}", recastResponse);

        List<Message> messages = recastResponse.getMessages();
        if (messages.size() == 0) {
            LOGGER.debug("No message found in Recast response.");
            CollectionItemTextResult text = new CollectionItemTextResult();

            List<Intent> intents = recastResponse.getNlp().getIntents();
            String textMessage;

            if (intents.size() > 0){
                Intent intent = intents.get(0);
                textMessage = String.format("Intent:%s isn't defined in SkillBuilder", intent.getName());

            } else {
                textMessage = "No intent or message is set at Recast.ai";
            }
            
            text.setText(textMessage);
            response.addResult(text);
            return;
        }

        Message message = messages.get(0);
        LOGGER.debug("Recast Message type is {}", message.getType());

        switch (message.getType()) {
            case "picture":
//                CollectionItemObjectResult item = new CollectionItemObjectResult();
//                item.setTypeEnum(CollectionItemObjectResult.Type.object);
//                Badge badge = new Badge();
//                badge.setImgUrl(message.getContent());
//                item.setBadge(badge);

                CollectionItemDocumentResult item = createImage(message.getContent());
                response.addResult(item);
                break;
            case "text":
                CollectionItemTextResult textResult = new CollectionItemTextResult();
                textResult.setText(message.getContent());
                response.addResult(textResult);
                break;
        }



    }


    private CollectionItemDocumentResult createImage(String url){
        CollectionItemDocumentResult documentResult = new CollectionItemDocumentResult();

        try {
            HttpImageFetcherService.HttpImageResult imageResult = imageFetcherService.getImage(url);
            byte[] imageArray = imageResult.getByteBuffer().array();

            String imageBase64 = Base64Utils.encodeToString(imageArray);
            documentResult.setBase64Content(imageBase64);
            documentResult.setContentSize(imageArray.length);
            documentResult.setContentType(imageResult.getMediaType());

        } catch (IOException e) {
            LOGGER.error("Cannot fetch image from URL", e);
        }

        return documentResult;

    }
}
                                                                                                                                                                                                                    
