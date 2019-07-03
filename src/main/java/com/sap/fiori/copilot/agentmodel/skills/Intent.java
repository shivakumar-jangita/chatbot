package com.sap.fiori.copilot.agentmodel.skills;

import java.util.ArrayList;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="Intent", group=DocumentationConstants.DIGITAL_ASSISTANT_SKILLS_ENDPOINT, 
description="The Intent is part of the skills payload that the CoPilot digital assistant engine is expecting from an agent as a response to the /skills endpoint. A skill can have multiple intents. Each intent has an array of hintsentences and an array of commands.")
public class Intent {
   
    @ApiObjectField(description="Label of the intent will be rendered as header for a group of hintsentences. For example: 'Query products'.")
    private String label;
    
    @ApiObjectField(description="Commands will be rendered as clickable list items so they should be executable by the the agent. Commands should normally be short phrases typically formed by a verb followed by a noun. Typically base commands do not include parameters. For example: 'Create product', 'Update product', 'Show me products'. ")
    private ArrayList<String> commands;
    
    @ApiObjectField(description="HintSentences are typically examples of commands with parameters. The parameters may or may not be valid for the given service and enduser so the examples are not clickable. For example 'Show me products from supplier Avantel'.")
    private ArrayList<String> hintSentences;  
 

    public Intent() {}
    
    public Intent(String label, ArrayList<String> hintSentences, ArrayList<String> commands) {
        super();
        this.label = label;
        this.hintSentences = hintSentences;
        this.commands = commands;
    }
    
    public ArrayList<String> getHintSentences() {
        return hintSentences;
    }

    public void setHintSentences(ArrayList<String> hintSentences) {
        this.hintSentences = hintSentences;
    }

    public ArrayList<String> getCommands() {
        return commands;
    }

    public void setCommands(ArrayList<String> commands) {
        this.commands = commands;
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Intent [label=" + label + "commands=" + commands + ", hintSentences=" + hintSentences  + "]";
    }

}
