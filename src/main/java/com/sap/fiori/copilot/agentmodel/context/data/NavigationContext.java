package com.sap.fiori.copilot.agentmodel.context.data;

import java.util.HashMap;
import java.util.Map;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="NavigationContext", group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK,description="Contains a map aof navigation targets. Each supported platform may have target to navigate to.")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class NavigationContext {
	
	@ApiObjectField(description="Map of targets. The key is the platform name (ex:\"fiori\"). Value contains the needed information to navigate to a specific target.")
	Map<String, Object> targets;
	
	public NavigationContext() {
		this.targets = new HashMap<String, Object>();
	}
	
	
	public Map<String, Object> getTargets() {
		return targets;
	}

	public void setTargets(Map<String, Object> targets) {
		this.targets = targets;
	}
	
	public void setTarget(String platform, Object target) {
		targets.put(platform, target);
	}
}
