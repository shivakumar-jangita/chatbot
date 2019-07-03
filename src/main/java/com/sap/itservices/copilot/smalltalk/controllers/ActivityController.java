package com.sap.itservices.copilot.smalltalk.controllers;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.websocket.server.PathParam;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.fiori.copilot.agentmodel.skills.model.Activity;
import com.sap.itservices.copilot.smalltalk.services.ECCManagerService;

@RestController
@RequestMapping("activity")
public class ActivityController {

	@Autowired
	private ECCManagerService eccManagerService;

	@GetMapping(value = "/get/{activityId}")
	public Activity getActivity(@PathVariable(value = "activityId") String activityId) 
{
		System.out.println("activityId"+activityId);
		return eccManagerService.getActivity(activityId);
	}

}
