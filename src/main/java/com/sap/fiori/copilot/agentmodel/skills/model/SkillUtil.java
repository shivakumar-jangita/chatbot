package com.sap.fiori.copilot.agentmodel.skills.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SkillUtil {
    
    public static ArrayList<Sentences> getCommands(){
        ArrayList<String> sentences = new ArrayList<String>();
        sentences.add("create vacation");
        ArrayList<Sentences> commands= new ArrayList<Sentences>();
        return commands;
        
    }
    public static Group formatGroup(String groupName,ArrayList<String> sentences){
        List<Value> values= new ArrayList<Value>();
        Iterator<String> sentenceIterator = sentences.iterator();
        while (sentenceIterator.hasNext()){
      
        Value value=new Value();
        value.setText(sentenceIterator.next());
        values.add(value);}
        
        Group group= new Group();
        group.setGroupName(groupName);
        group.setValues(values);
        return group;
        
    }
    public static Sentences formatSentences(String type,String title, ArrayList<Group> groups){
 
        Sentences command = new Sentences();

        command.setGroups(groups);
        command.setTitle(title);;
        command.setType(type);
        return command;
    }

}
