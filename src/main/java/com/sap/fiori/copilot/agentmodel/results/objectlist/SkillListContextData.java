package com.sap.fiori.copilot.agentmodel.results.objectlist;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsondoc.core.annotation.ApiObject;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;
import com.sap.fiori.copilot.agentmodel.skills.model.Sentences;

@ApiObject(show=false, name="SkillListContextData", group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK, description="Context data of object lists which is stored with the generated collection item as additional JSON information")
public class SkillListContextData extends ListContextData {
	

	protected HashMap<String,ArrayList<Sentences>> helpSentences ;
    
    public HashMap<String, ArrayList<Sentences>> getHelpSentences() {
        return helpSentences;
    }
    public void setHelpSentences(HashMap<String, ArrayList<Sentences>> helpSentences) {
        this.helpSentences = helpSentences;
    }
  
    
    
    
    
}
