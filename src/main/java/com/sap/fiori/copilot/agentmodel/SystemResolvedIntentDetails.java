package com.sap.fiori.copilot.agentmodel;
    
public class SystemResolvedIntentDetails {
  
    public SystemResolvedIntentType systemResolvedIntentType;
    
    

    public enum SystemResolvedIntentType {

        CANCEL, POSITIVE_CONFIRM, NEGATIVE_CONFIRM, HELP, SUGGEST //what else?, 


        }
   
    public SystemResolvedIntentDetails() {
        super();   
    }
    
    public SystemResolvedIntentType getSystemResolvedIntentType() {
        return systemResolvedIntentType;
    }

    public void setSystemResolvedIntentType(SystemResolvedIntentType systemResolvedIntentType) {
        this.systemResolvedIntentType = systemResolvedIntentType;
    }
    
    @Override
    public String toString() {
        return "SystemResolvedIntentDetails [systemResolvedIntentType=" + systemResolvedIntentType + "]";
    }
    
}
