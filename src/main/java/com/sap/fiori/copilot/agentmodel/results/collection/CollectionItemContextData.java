package com.sap.fiori.copilot.agentmodel.results.collection;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.context.data.ContextData;
import com.sap.fiori.copilot.agentmodel.context.query.QueryContext;
import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name = "CollectionItemContextData", group = DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK, description = "Any type of additional JSON information that should be stored with the collection item. This can later be used at runtime for further processing on"
		+ " collection item level and will also be available when reading collection items via CoPilot's OData service.")
public class CollectionItemContextData extends ContextData {
    
    @ApiObjectField
    protected QueryContext queryContext;
    
	public CollectionItemContextData() {

	}

	/*
	 * Add any additional json data properties here, that can be set for all
	 * collection items
	 */

    public QueryContext getQueryContext() {
        return queryContext;
    }

    public void setQueryContext(QueryContext queryContext) {
        this.queryContext = queryContext;
    }
	
}
