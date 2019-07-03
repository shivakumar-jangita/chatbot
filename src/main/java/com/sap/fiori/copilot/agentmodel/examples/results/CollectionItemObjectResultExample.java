package com.sap.fiori.copilot.agentmodel.examples.results;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.context.data.ContextData;
import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;
import com.sap.fiori.copilot.agentmodel.results.object.CollectionItemObjectResult;


@ApiObject(name="CollectionItemObjectResultExample", group=DocumentationConstants.EXAMPLES_DIGITAL_ASSISTANT_AGENT_FRAMEWORK, description="Example of an CollectionItemObjectResult content")
public class CollectionItemObjectResultExample extends CollectionItemObjectResult {

    @ApiObjectField(description="Instance of CollectionItemContextData")
    protected  CollectionItemContextDataExample itemContextData;
    
    @ApiObjectField(description="Instance of a <code>ContextData</code>")
    protected ContextData objectContextData;

}
