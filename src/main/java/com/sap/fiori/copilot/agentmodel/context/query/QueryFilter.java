package com.sap.fiori.copilot.agentmodel.context.query;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="QueryFilter",group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK,description="Describes a filter applied to a specific property")
public class QueryFilter {
	
	
	@ApiObjectField(description="The property on which to apply the filter range")
	protected String property;
	
	
	@ApiObjectField(description="The filter range to apply")
	protected QueryFilterRange filterRange;
	
	public QueryFilter(String property, QueryFilterRange filterRange) {
		this.property = property;
		this.filterRange = filterRange;
	}
	
	public QueryFilter() {
		
	}

	public String getProperty() {
		return property;
	}


	public QueryFilterRange getFilterRange() {
		return filterRange;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public void setFilterRange(QueryFilterRange filterRange) {
		this.filterRange = filterRange;
	}


	
	
	
	

}
