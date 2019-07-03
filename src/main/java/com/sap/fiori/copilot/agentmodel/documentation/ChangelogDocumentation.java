package com.sap.fiori.copilot.agentmodel.documentation;

import org.jsondoc.core.annotation.global.ApiChangelog;
import org.jsondoc.core.annotation.global.ApiChangelogSet;

@ApiChangelogSet(changlogs= {
        @ApiChangelog(changes= {"Initial release"}, version="2018.01.03" ),
        @ApiChangelog(changes= {"Deprecated fiori field in API objects <em>CollectionItemObjectResult</em>, <em>CollectionItemObjectResult</em> and <em>CollectionItemQuickCreateResult</em>",
                                "Redefined type ItemContextData used in API objects <em>CollectionItemObjectResult</em>, <em>CollectionItemObjectResult</em> and <em>CollectionItemQuickCreateResult</em>",
                                "Added objectContextData field in API object <em>CollectionItemObjectResult</em>",
                                "Handle multi-platform navigation contexts through attribute NavigationContext in API object <em>ContextData</em>",
                                "<em>ListContextData</em> extends <em>ContextData</em> and adds a field queryContext to describes the eventual filters used to generate the list of results"}, version="2018.01.08"),
        @ApiChangelog(changes= {"appDescriptor field in CopilotContext is deprecated, use appMetadata instead","appMetadata contains the frontend context, including appConfiguration, appDescriptor and appLocation"}, version="2018.01.10" )
})
public class ChangelogDocumentation {

}
