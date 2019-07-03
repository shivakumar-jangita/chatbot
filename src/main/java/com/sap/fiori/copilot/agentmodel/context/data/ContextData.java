package com.sap.fiori.copilot.agentmodel.context.data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name="ContextData", group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK, description="Context data including agent specific data in field \"custom\" and eventually a navigation context")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ContextData {
	
	@ApiObjectField(description="Contains a list of navigation targets based on supported platforms.")
	protected NavigationContext navigationContext;
	
	@ApiObjectField(description="Generic custom objects to contain agent specific data")
	protected Object custom;

	public NavigationContext getNavigationContext() {
		return navigationContext;
	}

	public void setNavigateContext(NavigationContext navigateTo) {
		this.navigationContext = navigateTo;
	}
	
	public Object getCustom() {
		return custom;
	}

	public void setCustom(Object custom) {
		this.custom = custom;
	}
	
	

}
