package com.sap.fiori.copilot.agentmodel.examples.results;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.context.query.QueryContext;

@ApiObject(name="ListContextData",show=false)
public class CollectionItemContextListDataExample extends CollectionItemContextDataExample{

    @ApiObjectField
    protected QueryContext queryContext;
}
