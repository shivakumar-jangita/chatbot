package com.sap.itservices.copilot.smalltalk.services;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.jsoniter.JsonIterator;
import com.sap.fiori.copilot.agentmodel.skills.model.Activity;
import com.sap.itservices.copilot.smalltalk.utils.ExternalServiceInvoker;
import com.sap.itservices.copilot.smalltalk.utils.ExternalServiceInvoker.SSOAuth;
import com.sap.itservices.copilot.smalltalk.utils.RestUtil;
import com.sap.itservices.copilot.smalltalk.utils.RestUtil.RestUtilAuth;
import com.sap.itservices.copilot.smalltalk.utils.ServiceInvokerConstants;
import com.sap.itservices.copilot.smalltalk.utils.ServiceResponse;
import com.sap.itservices.copilot.smalltalk.utils.ServicesUtil;

@Service
public class ECCManagerService {

	public Activity getActivity(String activityId) 
	
	
{
		String url = "https://lshtdc00.wdf.sap.corp:1443/sap/opu/odata/sap/YACTIVITY_SRV/Yc_Mac_T_Act_Main?$expand=to_ACloudEnv%2Cto_AOpportunity%2Cto_ADetType%2Cto_ALand%2Cto_AShowroom%2Cto_ASol%2Cto_AStrategicSol%2Cto_ASuccessFactor%2Cto_ATeam%2Cto_ACrossTopic%2Cto_ADemoCntnt&$filter=ActivityId%20eq%20'"+activityId+"'&$format=json";
//		String url ="https://lshtdc00.wdf.sap.corp:1443/sap/opu/odata/sap/YACTIVITY_SRV/Yc_Mac_T_Act_Main?&$filter=ActivityId%20eq%20%27'"+activityId+"'%27&$format=json";
		
		Integer proxyPort = null;
		String hcPort = System.getenv("HC_OP_HTTP_PROXY_PORT");
		String proxyHost=System.getenv("HC_OP_HTTP_PROXY_HOST");
		
		if(!ServicesUtil.isEmpty(hcPort)) {
			proxyPort = Integer.parseInt(hcPort);
		} else {
			proxyPort = 20003;
		}
		
		ServiceResponse restResponse = RestUtil.callRestService(url, null, null, ServiceInvokerConstants.HTTP_METHOD_GET, ServiceInvokerConstants.APPLICATION_JSON, true, null, null, null, null, null, RestUtilAuth.PRINCIPAL_PROPOGATION_AUTH, proxyHost, proxyPort, ServiceInvokerConstants.CC_LOCATION_ID);
		System.err.println("RestResponse : "+restResponse);
		Activity activity = null;
		
		System.out.println("proxyHost"+proxyHost+"hcPort"+hcPort);
		ServiceResponse serviceResponse = ExternalServiceInvoker.invokeService(url, null, ServiceInvokerConstants.HTTP_METHOD_GET, ServiceInvokerConstants.APPLICATION_JSON, true, null, 
				"C5264224", "yashika002@", null, null, SSOAuth.PRINCIPAL_PROPOGATION_AUTH, proxyHost, proxyPort, ServiceInvokerConstants.CC_LOCATION_ID);
		
		if(!ServicesUtil.isEmpty(serviceResponse) && serviceResponse.getResponseCode() == 200) {
			Object responseObject = serviceResponse.getResponseObject();
			System.err.println("responseObject==="+responseObject.toString());
			if(!ServicesUtil.isEmpty(responseObject) && responseObject instanceof JSONObject) {
				activity = JsonIterator.deserialize(((JSONObject) responseObject).toString(), Activity.class);
				
				System.err.println("activity opp==="+activity.getOportunityNumber());
			}
		}
		return activity;
	}
	
	
}
