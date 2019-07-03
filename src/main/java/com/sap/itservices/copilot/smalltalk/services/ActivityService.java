package com.sap.itservices.copilot.smalltalk.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.fiori.copilot.agentmodel.skills.model.Activity;
import com.sap.itservices.copilot.smalltalk.utils.DestinationClient;

@Service
public class ActivityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityService.class);

	 @Autowired
	    private DestinationClient destinationClient;

	  public List<Activity>  getActivityRequestor(String activityId){
	        String constructedPath="/sap/opu/odata/sap/YACTIVITY_SRV/Yc_Mac_T_Act_Main?&";
	        //constructedPath+="expand=to_ACloudEnv%2Cto_AOpportunity%2Cto_ADetType%2Cto_ALand%2Cto_AShowroom%2Cto_ASol%2Cto_AStrategicSol%2Cto_ASuccessFactor%2Cto_ATeam%2Cto_ACrossTopic%2Cto_ADemoCntnt&";
	            constructedPath+="$filter=ActivityId%20eq%20'"+activityId+"'&$format=json&sap-client=001";

	        System.err.println("URL in the service is>>> "+constructedPath);
	        return getActivityDetailsWithHttpClient(constructedPath.replaceAll(" ","%20"),activityId);

	    }
	  
	  public boolean isMockMode (){
	        if (!destinationClient.getMode().equalsIgnoreCase(DestinationClient.NORMAL_MODE)) return true;
	        else return false;
	        }

	private List<Activity> getActivityDetailsWithHttpClient(String path,String activityId) {
		
		 try {
			 System.err.println("inside getActivityDetailsWithHttpClient"+path);
			 System.err.println("mode>>>>"+destinationClient.getMode());
	            if (destinationClient.getMode().equalsIgnoreCase(DestinationClient.NORMAL_MODE)) {
	            	String respBody = destinationClient.getFromISP(path);
	                LOGGER.error(respBody);
	                System.out.println("respBody>>>>>"+respBody);
	                return ActivityResponseMapper.mapToActivityDetails(respBody);
	            }else{
	                List<Activity> poDetailsList = new ArrayList<>();
	                return poDetailsList;
		
	           }
	        }catch (Exception e){
	            e.printStackTrace();
	        }
		return null;
	}
	
}
