package com.sap.itservices.copilot.smalltalk.recast.dialog;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.sap.itservices.copilot.smalltalk.recast.Entity;
import com.sap.itservices.copilot.smalltalk.recast.Intent;
import com.sap.itservices.copilot.smalltalk.recast.RecastException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class RecastDialogResponse {



    private static final Logger LOGGER = LoggerFactory.getLogger(RecastDialogResponse.class);
    private static final ISO8601DateFormat ISO_8601_DATE_FORMAT = new ISO8601DateFormat();


    // Inner classes definition.
    //======================= Start of definition of inner classes =======================
    public class Message{

        private String type;
        private String content;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }


    public class Conversation {
        
        private String id;
        private String language;
        //TODO: memory at the moment not clear.
        @JsonIgnore
        private JSONObject memory;
        private String skill;
        private int skillOccurences;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public JSONObject getMemory() {
            return memory;
        }

        public void setMemory(JSONObject memory) {
            this.memory = memory;
        }

        public String getSkill() {
            return skill;
        }

        public void setSkill(String skill) {
            this.skill = skill;
        }

        public int getSkillOccurences() {
            return skillOccurences;
        }

        public void setSkillOccurences(int skillOccurences) {
            this.skillOccurences = skillOccurences;
        }
    }


    

    public class Nlp {

        private String uuid;
        private String source;
        private List<Intent> intents;
        private String act;
        private String  type;
        private String sentiment;
        private Map<String, List<Entity>> entities;
        private String language;
        private String timeStamp;
        private int status;
        private String processingLanguage;
        private String version;


       

        public String getProcessingLanguage() {
            return processingLanguage;
        }
        public String getUuid() { return uuid;}
        public String getSource() {
            return source;
        }
        public String getAct() {
            return act;
        }
        public String getType() {
            return type;
        }
        public String getSentiment() {
            return sentiment;
        }
        public String getLanguage() {
            return language;
        }
        public String getVersion() {
            return version;
        }


        @JsonProperty("timestamp")
        public String getTimeStampString() {
            return timeStamp;
        }

        @JsonIgnore
        public Date getTimeStamp() throws ParseException {
            return RecastDialogResponse.ISO_8601_DATE_FORMAT.parse(this.timeStamp);
        }

        public int getStatus() {
            return status;
        }



        public Optional<Entity> getEntity(String name){
            if (getEntities(name).isPresent()){
                return Optional.ofNullable(getEntities(name).get().get(0));
            } else {
                return  Optional.empty();
            }
        }


        public Optional<List<Entity>> getEntities(String name){
            return Optional.ofNullable(entities.get(name));
        }


        /**
         * It returns all the Intents sorted by the confidence.
         * @return
         */
        public List<Intent> getIntents() {
            intents.sort(Comparator.comparing(Intent::getConfidence));
            return intents;
        }



        public Nlp(JSONObject nlpJson){
            parseJson(nlpJson.getJSONObject("nlp"));
        }

        private void parseJson(JSONObject nlpJson){
            
            this.uuid = nlpJson.getString("uuid");
            this.source = nlpJson.getString("source");
            this.act = nlpJson.getString("act");

            if (nlpJson.isNull("type")){
                this.type = null;
            } else {
                this.type = nlpJson.getString("type");
            }
            
            
            this.sentiment = nlpJson.getString("sentiment");
            this.language = nlpJson.getString("language");
            this.processingLanguage = nlpJson.getString("processing_language");
            this.version = nlpJson.getString("version");
            this.timeStamp = nlpJson.getString("timestamp");
            this.status = nlpJson.getInt("status");
            

            JSONArray allIntents = nlpJson.getJSONArray("intents");
            if (allIntents != null){
                this.intents = parseIntents(allIntents);
            }

            JSONObject allEntities = nlpJson.getJSONObject("entities");

            if (allEntities != null){
                this.entities = parseEntities(allEntities);
            }
            
        }

        private Map<String, List<Entity>> parseEntities(JSONObject allEntities) {
            Map<String, List<Entity>> results = new HashMap<>();

            allEntities.keySet().forEach(entityName -> {

                JSONArray oneEntityArray = allEntities.getJSONArray(entityName);
                List<Entity> entityList = oneEntityArray.toList().stream().map(jsonObj -> {

                    Entity entity = new Entity(entityName,new JSONObject((HashMap)jsonObj));
                    return entity;
                }).collect(Collectors.toList());

                results.put(entityName,entityList);
            });

            return results;
        }

        private List<Intent> parseIntents(JSONArray allIntents) {
            List<Intent> intents =  allIntents.toList().
                    stream().
                    map(jsonObj ->{

                        HashMap<String, Object> val =(HashMap<String, Object>) jsonObj;
                        String name = val.get("slug").toString();
                        double confidence = (double)val.get("confidence");
                        String description = val.get("description").toString();
                        Intent intent = new Intent(name, confidence,description);
                        return intent;
                    }).
                    collect(Collectors.toList());
            return intents;
        }
    }



   //===================================================================================================================

    private Nlp nlp;

    private  Conversation conversation;
    private List<Message> messages;



    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public Nlp getNlp() {
        return nlp;
    }

    public void setNlp(Nlp nlp) {
        this.nlp = nlp;
    }




    public RecastDialogResponse(String jsonString) throws RecastException{
        JSONObject jsonRoot = new JSONObject(jsonString);

        if (jsonRoot.isNull("results")) {
            throw new RecastException(jsonRoot.getString("message"));
        } else {
            JSONObject jsonResult = jsonRoot.getJSONObject("results");
            this.messages = parseMessage(jsonResult);
            this.conversation = parseConversation(jsonResult);
            this.nlp = parseNlp(jsonResult);
        }
    }
    
    
    private Nlp parseNlp(JSONObject jsonRoot) {
        Nlp nlp = new Nlp(jsonRoot);
        return nlp;
    }

    
    private Conversation parseConversation(JSONObject jsonRoot) {
        JSONObject convJson = jsonRoot.getJSONObject("conversation");
        Conversation conversation = new Conversation();
        conversation.id = convJson.getString("id");
        conversation.language = convJson.getString("language");
        
        if (!convJson.isNull("skill")){
            conversation.skill = convJson.getString("skill");
            conversation.skillOccurences = convJson.getInt("skill_occurences");
        }

        conversation.memory = convJson.getJSONObject("memory");
        return conversation;
    }

    private List<Message> parseMessage(JSONObject jsonRoot) {
        JSONArray messageJsonArray = jsonRoot.getJSONArray("messages");

        List<Message> messages = messageJsonArray.
                toList().
                stream().
                map(msgObj -> {
                    Map<String, String> msgMap = (Map<String, String>) msgObj;
                    Message msg = new Message();
                    msg.setContent(msgMap.get("content"));
                    msg.setType(msgMap.get("type"));
                    return msg;
                }).
                collect(Collectors.toList());

        return messages;
    }

    @Override
    public String toString() {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            LOGGER.error("Cannot convert RecastDialogResponse", e);
            e.printStackTrace();
        }

        return super.toString();
    }
}
