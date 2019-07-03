package com.sap.fiori.copilot.agentmodel.examples.results.navigation;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="NavigationContextExample", show=false, group=DocumentationConstants.EXAMPLES_DIGITAL_ASSISTANT_AGENT_FRAMEWORK, description="")
public class NavigationContextExample {
    
   @ApiObjectField(description="Map of targets for hebus")
   protected TargetsExample targets;
   
   
	
	

}
