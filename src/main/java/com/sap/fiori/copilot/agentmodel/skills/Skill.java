package com.sap.fiori.copilot.agentmodel.skills;

import java.util.ArrayList;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="Skill", group=DocumentationConstants.DIGITAL_ASSISTANT_SKILLS_ENDPOINT, 
description="The response payload that the CoPilot digital assistant engine is expecting from an agent as a response to the /skills endpoint which should return an array of skills.")       
public class Skill {

        
    @ApiObjectField(required=true, description="Title of the skill provided by the agent e.g. 'Manage Products'.")
    private String title;
        
    @ApiObjectField(description="This should be set to an example sentence supported by the specified skill or left blank. If left blank CoPilot will set this field to the first example sentence it finds in the related intents.")    
    private String subtitle;
    
    @ApiObjectField(description="Url for small image that will be rendered in help view on Coipilot to the left of this skill, e.g. 'sap-icon://Fiori2/F0246'.")    
    private String icon;
    
    @ApiObjectField(description="Array of intents included under this skill e.g. Create Product, Update Product, etc'.")    
    private ArrayList<Intent> intents;

    public Skill() {
    }

    public Skill(String title, String subtitle, String icon, ArrayList<Intent> intents) {
        super();
        this.title = title;
        this.subtitle = subtitle;
        this.icon = icon;
        this.intents = intents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ArrayList<Intent> getIntents() {
        return intents;
    }

    public void setIntents(ArrayList<Intent> intents) {
        this.intents = intents;
    }

    @Override
    public String toString() {
        return "Skill [title=" + title + ", subtitle=" + subtitle + ", icon=" + icon + ", intents=" + intents + "]";
    }

}

   