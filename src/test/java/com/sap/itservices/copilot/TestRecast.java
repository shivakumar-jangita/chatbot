package com.sap.itservices.copilot;


import com.sap.itservices.copilot.smalltalk.recast.Entity;
import com.sap.itservices.copilot.smalltalk.recast.dialog.RecastDialogResponse;
import org.junit.Test;


import java.text.ParseException;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class TestRecast {
    

    private static final String DIALOG_RESPONSE = "{\n" +
            "  \"results\" : {\n" +
            "    \"messages\" : [\n" +
            "      {\n" +
            "        \"type\" : \"text\",\n" +
            "        \"content\" : \"Hi, nice to meet you :)\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"conversation\" : {\n" +
            "      \"id\" : \"CONVERSATION_ID\",\n" +
            "      \"language\" : \"en\",\n" +
            "      \"memory\" : {},\n" +
            "      \"skill\" : \"greetings\",\n" +
            "      \"skill_occurences\" : 3\n" +
            "    },\n" +
            "    \"nlp\" : {\n" +
            "      \"uuid\" : \"e859c4ee-721d-4137-a049-8be8923ab01b\",\n" +
            "      \"source\" : \"Hello, Michael!\",\n" +
            "      \"intents\" : [\n" +
            "        {\n" +
            "          \"slug\" : \"greetings\",\n" +
            "          \"confidence\" : 0.99,\n" +
            "          \"description\" : \"Says hello\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"act\" : \"assert\",\n" +
            "      \"type\" : null,\n" +
            "      \"sentiment\" : \"neutral\",\n" +
            "      \"entities\" : {\n" +
            "        \"person\" : [\n" +
            "          {\n" +
            "            \"fullname\" : \"Michael\",\n" +
            "            \"raw\" : \"Michael\",\n" +
            "            \"confidence\" : 0.96\n" +
            "          }\n" +
            "        ]\n" +
            "      },\n" +
            "      \"language\" : \"en\",\n" +
            "      \"processing_language\" : \"en\",\n" +
            "      \"version\" : \"2.12.0\",\n" +
            "      \"timestamp\" : \"2018-05-14T15:03:31.943213+00:00\",\n" +
            "      \"status\" : 200\n" +
            "    }\n" +
            "  },\n" +
            "  \"message\" : \"Dialog rendered with success\"\n" +
            "}";

    @Test
    public void testRecastDialogResponseParse(){

        RecastDialogResponse recastDialogResponse = new RecastDialogResponse(DIALOG_RESPONSE);
        assertNotNull(recastDialogResponse.getConversation());
        assertNotNull(recastDialogResponse.getMessages());
        assertNotNull(recastDialogResponse.getNlp());
    }



    @Test
    public void testRecastDialogEntities(){

        RecastDialogResponse recastDialogResponse = new RecastDialogResponse(DIALOG_RESPONSE);
        Optional<List<Entity>> personEntities = recastDialogResponse.getNlp().getEntities("person");

        assertTrue(personEntities.isPresent());
        assertEquals(1, personEntities.get().size());

        
        Optional<Entity> personEntity = recastDialogResponse.getNlp().getEntity("person");
        assertTrue(personEntity.isPresent());

        assertEquals("person", personEntity.get().getName());
        assertEquals("Michael", personEntity.get().getString("fullname").get());
        assertEquals(0.96, personEntity.get().getConfidence(), 0);
        

        

    }
    
    @Test
    public void testRecastDialogNlp() throws ParseException {
        RecastDialogResponse recastDialogResponse = new RecastDialogResponse(DIALOG_RESPONSE);
        assertEquals(1, recastDialogResponse.getNlp().getIntents().size());
        assertEquals("greetings", recastDialogResponse.getNlp().getIntents().get(0).getName());
        assertEquals(0.99, recastDialogResponse.getNlp().getIntents().get(0).getConfidence(), 0);
        assertNotNull(recastDialogResponse.getNlp().getTimeStamp());
        
    }

    @Test
    public void testRecastDialogConversation(){

        RecastDialogResponse recastDialogResponse = new RecastDialogResponse(DIALOG_RESPONSE);
        RecastDialogResponse.Conversation conversation = recastDialogResponse.getConversation();

        assertEquals("CONVERSATION_ID", conversation.getId());
        assertEquals("en", conversation.getLanguage());
        assertEquals("greetings", conversation.getSkill());
        assertEquals(3, conversation.getSkillOccurences());
    }

    @Test
    public void testRecastDialogMessage(){
        RecastDialogResponse recastDialogResponse = new RecastDialogResponse(DIALOG_RESPONSE);
        List<RecastDialogResponse.Message> messages = recastDialogResponse.getMessages();
        assertEquals(1, messages.size());
        RecastDialogResponse.Message message = messages.get(0);
        assertEquals("text", message.getType());
        assertEquals("Hi, nice to meet you :)", message.getContent());
    }
    


    


}
