package com.sap.fiori.copilot.agentmodel.results.quickcreate;

import java.util.ArrayList;
import java.util.Collection;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;
import com.sap.fiori.copilot.agentmodel.fiori.context.FioriLegacyContext;
import com.sap.fiori.copilot.agentmodel.results.text.CollectionItemTextResult;

@ApiObject(name = "CollectionItemQuickCreateResult",group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK)
public class CollectionItemQuickCreateResult extends CollectionItemTextResult {

	@ApiObjectField
	protected String draftId;

	@ApiObjectField(description = "Deprecated Fiori Context")
	@Deprecated
	protected FioriLegacyContext fiori;

	@ApiObjectField
	protected Collection<Property> properties;

	@ApiObjectField
	protected String objectType;

	@ApiObjectField
	protected String ui5ComponentName;

	@ApiObjectField
	protected String ui5ComponentPath;

	public CollectionItemQuickCreateResult() {
		properties = new ArrayList<Property>();
		type = "quickcreate";
	}

	public String getDraftId() {
		return draftId;
	}

	public void setDraftId(String draftId) {
		this.draftId = draftId;
	}

	public FioriLegacyContext getFiori() {
		return fiori;
	}

	public void setFiori(FioriLegacyContext fiori) {
		this.fiori = fiori;
	}

	public Collection<Property> getProperties() {
		return properties;
	}

	public void setProperties(Collection<Property> properties) {
		this.properties = properties;
	}

	public void addProperty(Property property) {
		this.properties.add(property);
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getUi5ComponentName() {
		return ui5ComponentName;
	}

	public void setUi5ComponentName(String ui5ComponentName) {
		this.ui5ComponentName = ui5ComponentName;
	}

	public String getUi5ComponentPath() {
		return ui5ComponentPath;
	}

	public void setUi5ComponentPath(String ui5ComponentPath) {
		this.ui5ComponentPath = ui5ComponentPath;
	}

	@Override
	public String toString() {
		return "CollectionItemQuickCreateResult [draftId=" + draftId + ", fiori=" + fiori + ", properties=" + properties
				+ "]";
	}

	// for documenation generation only
	@ApiObjectField(description = "Mandatory technical attribute that makes sure CoPilot deserializes this JSON object into the right Java class at runtime. Must be set to the value given below!", name = "@class", required = true, allowedvalues = "com.sap.fiori.copilot.agentmodel.results.quickcreate.CollectionItemQuickCreateResult")
	@JsonIgnore
	private String _documentationOnlyClassname;

	@ApiObjectField(name = "type", description = "Mandatory technical field that tells CoPilot what type of result this is", allowedvalues = {
			"quickcreate" }, required = true)
	@JsonIgnore
	private String _documentationOnlyType;

}
