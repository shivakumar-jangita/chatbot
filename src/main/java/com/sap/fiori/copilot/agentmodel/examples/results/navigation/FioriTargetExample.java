package com.sap.fiori.copilot.agentmodel.examples.results.navigation;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="FioriTargetExample", group=DocumentationConstants.EXAMPLES_DIGITAL_ASSISTANT_AGENT_FRAMEWORK, description="Fiori Navigation target as defined in the UI5 documentation <a target=\"_blank\" href=\"https://sapui5.hana.ondemand.com/#/api/sap.ushell.services.CrossApplicationNavigation/methods/toExternal\">SAP UI5 documentation</a>")
public class FioriTargetExample {
    
    
    @ApiObjectField(name="target", description="Intent target as described in the above SAPui5 documenentation link", order=1)
    protected FioriIntentTarget target;
    
    @ApiObjectField(order=2, description="name/value pairs")
    protected FioriParams params;

}


@ApiObject(name="FioriNavigationTarget",show=false)
class FioriIntentTarget {
    
    @ApiObjectField(order=1)
    protected String semanticObject;
    
    @ApiObjectField(order=2)
    protected String action;
}

@ApiObject(name="params",show=false)
class FioriParams {
    
    @ApiObjectField(name="A", order=1)
    protected String param1;
   
    
    @ApiObjectField(name="B", order=2)
    protected String param2;
}
