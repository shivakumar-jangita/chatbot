package com.sap.fiori.copilot.agentmodel.examples.results;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.examples.results.navigation.NavigationContextExample;

@ApiObject(name="CollectionItemContextData",show=false)
public class CollectionItemContextDataExample {

    @ApiObjectField
    protected NavigationContextExample navigationContext;
    
    @ApiObjectField
    protected Object custom; 
}
