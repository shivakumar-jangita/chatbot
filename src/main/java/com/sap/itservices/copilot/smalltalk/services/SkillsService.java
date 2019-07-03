package com.sap.itservices.copilot.smalltalk.services;

import com.sap.fiori.copilot.agentmodel.skills.Intent;
import com.sap.fiori.copilot.agentmodel.skills.Skill;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;

@Service
public class SkillsService {

    public Collection<Skill> getAvailableSkills(){

        String icon = "sap-icon://cloud";
        ArrayList<String> commands = new ArrayList<>();


        commands.add("Show common presales tools");
        commands.add("Tell us something about ARTES");
        commands.add("Tell us something about Melody ");


        ArrayList<String> hintsentences = new ArrayList<>();

        hintsentences.add("ARTES is a time entry System");
        hintsentences.add("Melody is a demo reg application");
        hintsentences.add("Thanks for your help");

        Intent skillIntent = new Intent("Presales Asistant",hintsentences,commands);
        ArrayList<Intent> skillIntents= new ArrayList<>();
        skillIntents.add(skillIntent);

        Skill skill0 = new Skill("Presales Tools","Show common presales tools", icon, skillIntents);

        ArrayList<Skill> skills = new ArrayList<>();
        
        skills.add(skill0);

        

        return skills;
    }
}
