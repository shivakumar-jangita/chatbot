package com.sap.fiori.copilot.agentmodel;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sap.fiori.copilot.agentmodel.ResolvedIntent;
import com.sap.fiori.copilot.agentmodel.SystemResolvedIntentDetails.SystemResolvedIntentType;

/**
 * This class expresses a ResolvedIntent which is simple and shortlived in
 * nature such as an intent within a complex intent (e.g. a CANCEL within a
 * CREATE) It is identified by the IntentResolver from the TA output present in
 * the utterance and passed to the DialogEngine which deals with the logic.
 *
 */
public class SystemResolvedIntent extends ResolvedIntent {

    public SystemResolvedIntent() {
        super();
        this.details = new SystemResolvedIntentDetails();
    }

    public SystemResolvedIntent(SystemResolvedIntentType systemResolvedIntentType) {
        super();
        this.details = new SystemResolvedIntentDetails();
        this.setSystemResolvedIntentType(systemResolvedIntentType);
    }

    @JsonIgnore
    public SystemResolvedIntentType getSystemResolvedIntentType() {
        return ((SystemResolvedIntentDetails) this.details).getSystemResolvedIntentType();
    }

    @JsonIgnore
    public void setSystemResolvedIntentType(SystemResolvedIntentType systemResolvedIntentType) {
        ((SystemResolvedIntentDetails) this.details).setSystemResolvedIntentType(systemResolvedIntentType);
        this.name = systemResolvedIntentType.name();
    }

    @Override
    public SystemResolvedIntentDetails getDetails() {
        return (SystemResolvedIntentDetails) this.details;
    }

   
    public void setDetails(SystemResolvedIntentDetails details) {
        this.details=details;
    }
    
}
