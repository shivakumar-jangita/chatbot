package com.sap.fiori.copilot.agentmodel.context.query;

import java.util.ArrayList;
import java.util.List;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="QueryContext",group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK,description="Describes the query details such as the filters used and the total number of results")
public class QueryContext {

	@ApiObjectField(description="The total number of results matching the query filters")
	protected int numberOfResults;
	
	@ApiObjectField(description="The global search string that was used to execute the query (if applicable). This is a global search string, whereas the queryFilters are individual filter values"
	        + " per attribute. This string should also be used for filtered action lists (used in value helps, for example) to indicate what the last search string was.")
	protected String searchString;

	@ApiObjectField(description = "A collection of query ranges.")
	protected List<QueryFilter> queryFilters;

	public QueryContext() {
		this.queryFilters = new ArrayList<QueryFilter>();
	}

	public void setQueryFilters(List<QueryFilter> queryFilters) {
		this.queryFilters = queryFilters;
	}

	public List<QueryFilter> getQueryFilters() {
		return this.queryFilters;
	}

	public void addQueryFilter(QueryFilter queryFilter) {
		this.queryFilters.add(queryFilter);
	}

	public int getNumberOfResults() {
		return numberOfResults;
	}

	public void setNumberOfResults(int numberOfResults) {
		this.numberOfResults = numberOfResults;
	}

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
	
}
