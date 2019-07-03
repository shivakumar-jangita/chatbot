package com.sap.fiori.copilot.agentmodel.results.object;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name="CollectionItemObjectBadgeInfo", group=DocumentationConstants.DIGITAL_ASSISTANT_AGENT_FRAMEWORK)
public class Badge {
    
    @ApiObjectField
    protected String imgUrl;
    
    @ApiObjectField
    protected String iconUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public String toString() {
        return "Badge [imgUrl=" + imgUrl + ", iconUrl=" + iconUrl + "]";
    }

}
