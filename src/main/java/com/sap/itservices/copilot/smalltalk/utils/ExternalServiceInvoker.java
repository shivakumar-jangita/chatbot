package com.sap.itservices.copilot.smalltalk.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.directory.InitialDirContext;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.cloud.account.TenantContext;
import com.sap.core.connectivity.api.authentication.AuthenticationHeader;
import com.sap.core.connectivity.api.authentication.AuthenticationHeaderProvider;
import com.sap.core.connectivity.api.configuration.ConnectivityConfiguration;
import com.sap.itservices.copilot.smalltalk.skillprocessors.MelodyReplyProcess;

public class ExternalServiceInvoker {

	@Resource
	private static TenantContext tenantContext;

	private static final Logger LOGGER = LoggerFactory.getLogger(MelodyReplyProcess.class);

	public enum SSOAuth {
		PRINCIPAL_PROPOGATION_AUTH, APP_TO_APP_SSO_AUTH;
	}
	
	/**
	 * @param requestURL
	 * @param entity - input entity for POST/PATCH call
	 * @param method - POST/GET - PMCConstant.HTTP_METHOD_POST / PMCConstant.HTTP_METHOD_GET / PMCConstant.HTTP_METHOD_PATCH
	 * @param contentType - Content Type - ServiceInvokerConstants.APPLICATION_JSON / ServiceInvokerConstants.APPLICATION_XML etc.
	 * @param isSSO - true if trying to do API Call using SSO 
	 * @param xCsrfToken - "Fetch", if need to do a PATCH/POST operation and x-csrf-token is required
	 * @param userId - userId for Basic Authentication
	 * @param password - password for Basic Authentication
	 * @param accessToken - Access token for other types of Authentications 
	 * @param tokenType - Access token for other types of Authentications - Bearer/Basic etc.
	 * @param auth - PrincipalPropogation SSO/ AppToAppSSO - SSOAuth.PRINCIPAL_PROPOGATION_AUTH/ SSOAuth.APP_TO_APP_SSO_AUTH
	 * @param proxyHost - SAP Cloud Connector Proxy Host
	 * @param proxyPort - SAP Cloud Connector Proxy Port
	 * @param ccLocation - SAP Cloud Connector location
	 * @return ServiceResponse with returnObject, responseCode, httpResponse
	 */
	public static ServiceResponse invokeService(String requestURL, String entity, String method,
			String contentType, Boolean isSSO, String xCsrfToken, String userId, String password, String accessToken,
			String tokenType, SSOAuth auth, String proxyHost, Integer proxyPort, String ccLocation) {

		ServiceResponse serviceResponse = null;
		DefaultProxyRoutePlanner proxyRoutePlanner = null;
		CloseableHttpClient httpClient = null;
		HttpHost proxy = null;
		HttpRequestBase httpRequestBase = null;
		HttpResponse httpResponse = null;
		StringEntity input = null;
		String json = null;
		JSONObject obj = null;
		JSONArray array = null;
		Object returnJson = null;

		AuthenticationHeader appToAppSSOHeader = null;
		if (requestURL != null) {
			if (!ServicesUtil.isEmpty(proxyHost) && !ServicesUtil.isEmpty(proxyPort)) {
				proxy = new HttpHost(proxyHost, proxyPort, HttpHost.DEFAULT_SCHEME_NAME);
				proxyRoutePlanner = new DefaultProxyRoutePlanner(proxy);
			}
			httpClient = HttpClientBuilder.create().setRoutePlanner(proxyRoutePlanner).build();
			if (!ServicesUtil.isEmpty(httpClient)) {
				serviceResponse = new ServiceResponse();
				if (isSSO) {
					if (ServicesUtil.isEmpty(appToAppSSOHeader)) {
						appToAppSSOHeader = refreshAppToAppSSOHeader(requestURL, auth);
					}
				}

				try {
					if (xCsrfToken != null && xCsrfToken.equalsIgnoreCase("fetch")) {
						httpRequestBase = new HttpGet(requestURL);
						httpRequestBase.setHeader("x-csrf-token", xCsrfToken);
						if (appToAppSSOHeader != null) {
							httpRequestBase.addHeader(appToAppSSOHeader.getName(), appToAppSSOHeader.getValue());
						}
						httpResponse = httpClient.execute(httpRequestBase);
						Header[] headers = httpResponse.getAllHeaders();
						for (Header header : headers) {
							if (header.getName().equalsIgnoreCase("x-csrf-token")) {
								xCsrfToken = header.getValue();
							}
						}
					}
				} catch (IOException e) {
					System.err.println("Exception : " + e.getMessage());
				}

				if (method.equalsIgnoreCase(ServiceInvokerConstants.HTTP_METHOD_GET)) {
					httpRequestBase = new HttpGet(requestURL);
				} else if (method.equalsIgnoreCase(ServiceInvokerConstants.HTTP_METHOD_POST)) {
					httpRequestBase = new HttpPost(requestURL);
					if (!ServicesUtil.isEmpty(entity)) {
						try {
							input = new StringEntity(entity);
							input.setContentType(contentType);
						} catch (UnsupportedEncodingException e) {
							System.err.println("Input UnsupportedEncodingException : " + e.getMessage());
						}
						((HttpPost) httpRequestBase).setEntity(input);
					}
				} else if (method.equalsIgnoreCase(ServiceInvokerConstants.HTTP_METHOD_PATCH)) {
					httpRequestBase = new HttpPatch(requestURL);
					if (!ServicesUtil.isEmpty(entity)) {
						try {
							input = new StringEntity(entity);
							input.setContentType(contentType);
						} catch (UnsupportedEncodingException e) {
							System.err.println("Input UnsupportedEncodingException : " + e.getMessage());
						}
						((HttpPatch) httpRequestBase).setEntity(input);
					}
				}
				if (appToAppSSOHeader != null) {
					httpRequestBase.addHeader(appToAppSSOHeader.getName(), appToAppSSOHeader.getValue());
				}

				if (!ServicesUtil.isEmpty(isSSO) && SSOAuth.PRINCIPAL_PROPOGATION_AUTH.equals(auth)
						&& !ServicesUtil.isEmpty(tenantContext)) {
					httpRequestBase.addHeader("SAP-Connectivity-ConsumerAccount", tenantContext.getTenant().getId());
					httpRequestBase.addHeader("SAP-Connectivity-SCC-Location_ID", ccLocation);
				}

				if (!ServicesUtil.isEmpty(xCsrfToken) && !xCsrfToken.equalsIgnoreCase("fetch"))
					httpRequestBase.addHeader("x-csrf-token", xCsrfToken);
				httpRequestBase.addHeader("accept", contentType);
				if (!ServicesUtil.isEmpty(userId) && !ServicesUtil.isEmpty(password)) {
					httpRequestBase.addHeader("Authorization", ServicesUtil.getBasicAuth(userId, password));
				}

				if (!ServicesUtil.isEmpty(accessToken) && !ServicesUtil.isEmpty(tokenType)
						&& ServicesUtil.isEmpty(userId)) {
					httpRequestBase.addHeader("Authorization", ServicesUtil.getAuthorization(accessToken, tokenType));
				}
				try {
					httpResponse = httpClient.execute(httpRequestBase);
					System.err.println("httpResponse:::"+httpResponse);
					if (!ServicesUtil.isEmpty(httpResponse) && !ServicesUtil.isEmpty(httpResponse.getEntity())) {
						json = EntityUtils.toString(httpResponse.getEntity());
						try {
							if (!ServicesUtil.isEmpty(json)) {
								returnJson = new Object[2];
								if (json.charAt(0) == '{') {
									obj = new JSONObject(json);
									returnJson = obj;
								} else if (json.charAt(0) == '[') {
									array = new JSONArray(json);
									returnJson = array;
								}
								serviceResponse.setResponseObject(returnJson);
							}
						} catch (JSONException e) {
							System.err.println("JSONException : " + e + "JSON Object : " + json);
						}
					}
					serviceResponse.setHttpResponse(httpResponse);
					serviceResponse.setResponseCode(httpResponse.getStatusLine().getStatusCode());
					httpClient.close();
				} catch (IOException e) {
					System.err.println("IOException : " + e);
				}
			}
		}
		return serviceResponse;
	}

	private static AuthenticationHeader refreshAppToAppSSOHeader(String requestURL, SSOAuth auth) {
		
		AuthenticationHeader appToAppSSOHeader = null;
		AuthenticationHeaderProvider authHeaderProvider=null;
		
		if (!ServicesUtil.isEmpty(auth)) {
			System.err.println("auth:::"+auth);
			try {
				Context ctx = new InitialDirContext();
				authHeaderProvider = (AuthenticationHeaderProvider) ctx.lookup("java:comp/env/authHeaderProvider");
				System.err.println("authHeaderProvider:::"+authHeaderProvider);
				switch (auth) {
				case APP_TO_APP_SSO_AUTH:
					appToAppSSOHeader = authHeaderProvider.getAppToAppSSOHeader(requestURL);
					break;
				case PRINCIPAL_PROPOGATION_AUTH:
					appToAppSSOHeader = authHeaderProvider.getPrincipalPropagationHeader();
					break;
				}
			} catch (Exception ex) {
				System.err.println("Exception while fetching auth Header Provider : " + ex.getMessage());
			}
		}
		return appToAppSSOHeader;
	}

}
