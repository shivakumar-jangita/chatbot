package com.sap.itservices.copilot.smalltalk.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.fiori.copilot.agentmodel.skills.model.Activity;

public class ActivityResponseMapper {
	  private static final Logger LOGGER = LoggerFactory.getLogger(ActivityResponseMapper.class);
	  
	  public static List<Activity> mapToActivityDetails(String response){
	        JSONParser parser = new JSONParser();
	        try {

	            JSONObject responseObject = (JSONObject)parser.parse(response);
	            List<Activity> poDetailsList = new ArrayList<>();
	            JSONObject dobj= (JSONObject) responseObject.get("d");
	            if (dobj==null){
	                LOGGER.error("Failed getting PO data from backend. Backend response is: "+response);
	               return poDetailsList;
	                  }
	            JSONArray res = (JSONArray) dobj.get("results");

	            for (Object o : res) {
	                JSONObject obj = (JSONObject) o;

	                Activity details = new Activity();
	               
	                //details.setoData((String) ((HashMap)obj.get("__metadata")).get("uri"));
	                details.setStatus((String) obj.get("Status"));
	                details.setActivityId((String) obj.get("ActivityId"));
	                System.err.println("josn object>>>>>>>>"+obj.get("ActivityId"));
	            }
	            return poDetailsList;
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return null;

	    }

}
