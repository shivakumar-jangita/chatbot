package com.sap.fiori.copilot.agentmodel.examples.results;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;
import com.sap.fiori.copilot.agentmodel.results.objectlist.CollectionItemObjectListResult;


@ApiObject(name="CollectionItemListResultExample", group=DocumentationConstants.EXAMPLES_DIGITAL_ASSISTANT_AGENT_FRAMEWORK, description="Example of an CollectionItemObjectResult content")
public class CollectionItemListResultExample extends CollectionItemObjectListResult {

    @ApiObjectField(name="itemContextData", description="Example of a <code>ListContextData</code> including a fiori navigation target as in the <code>ForiTargetExample<code>")
    protected  CollectionItemContextListDataExample itemContextData;

}
