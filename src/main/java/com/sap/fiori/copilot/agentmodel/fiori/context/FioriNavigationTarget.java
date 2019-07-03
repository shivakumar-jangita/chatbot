package com.sap.fiori.copilot.agentmodel.fiori.context;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.HashMap;
import java.util.Map;

@ApiObject(name="FioriNavigationTarget",group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK,description="Fiori Navigation target as defined in the UI5 documentation <a target=\"_blank\" href=\"https://sapui5.hana.ondemand.com/#/api/sap.ushell.services.CrossApplicationNavigation/methods/toExternal\">SAP UI5 documentation</a>.  <br>See <code>FioriTargetExample</code>")
public class FioriNavigationTarget {
	
	
	public FioriNavigationTarget() {
		this.target = new HashMap<String, String>();
	}
	
	@ApiObjectField(description="Contains both the semanticObject and the action to define a Fiori Intent. Allowed keys are \"semanticObject\" and \"action\" ")
	protected Map<String, String> target;


	public Map<String, String> getTarget() {
		return target;
	}

	@ApiObjectField(description="Name/Value pairs to be added to the generted URL. Object must be able to convert into uri parameter (String, bool, int, long...)")
	protected Map<String, Object> params;

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public void addParam(String name, Object value) {
		if (this.params == null) {
			this.params = new HashMap<String, Object>();
		}
		this.params.put(name, value);
	}
	
	public void setSemanticObject(String semanticObject) {
		target.put("semanticObject", semanticObject);
	}


	@JsonIgnore() 	//Ignore as the semantic object is already part of the target
	public String getSemanticObject() {
	    return target.get("semanticObject");
	}
	
	public void setAction(String action) {
		target.put("action", action);
	}
	
	@JsonIgnore //Ignore as the semantic object is already part of the target
	public String getAction() {
	    return target.get("action");
	}
	
}
