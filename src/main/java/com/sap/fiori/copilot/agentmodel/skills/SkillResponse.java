package com.sap.fiori.copilot.agentmodel.skills;

import java.util.Collection;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.sap.fiori.copilot.agentmodel.documentation.DocumentationConstants;

@ApiObject(name = "SkillResponse", group = DocumentationConstants.DIGITAL_ASSISTANT_SKILLS_ENDPOINT, description = "The response payload that the CoPilot digital assistant engine is expecting from an agent as a response to the /skills endpoint which should return an array of skills.")
public class SkillResponse {

    @ApiObjectField(description = "Collection of Skill. This is collection should be returned in response when Copilot calls the agent's GET /skills endpoint.")
    Collection<Skill> Skills;

    public SkillResponse() {
    }

    public Collection<Skill> getSkills() {
        return Skills;
    }

    public void setSkills(Collection<Skill> skills) {
        Skills = skills;
    }

    @Override
    public String toString() {
        return "SkillResponse [Skills=" + Skills + "]";
    }

}
