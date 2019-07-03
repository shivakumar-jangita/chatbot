package com.sap.itservices.copilot.smalltalk.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.naming.Context;
import javax.naming.InitialContext;

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
import org.springframework.stereotype.Component;

import com.sap.core.connectivity.api.authentication.AuthenticationHeader;
import com.sap.core.connectivity.api.authentication.AuthenticationHeaderProvider;

@Component
public class RestUtil {
	
	public enum RestUtilAuth {
		PRINCIPAL_PROPOGATION_AUTH, APP_TO_APP_SSO_AUTH;
	}

	public static ServiceResponse callRestService(String requestURL, String samlHeaderKey, String entity, String method,
			String contentType, Boolean isSSO, String xCsrfToken, String userId, String password, String accessToken,
			String tokenType, RestUtilAuth auth, String proxyHost, Integer proxyPort, String ccLocationId) {
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
					appToAppSSOHeader = null;
					// if (ServicesUtil.isEmpty(appToAppSSOHeader)) {
					appToAppSSOHeader = refreshAppToAppSSOHeader(requestURL, auth);
					// System.err.println("[inside if
					// ][appToAppSSOHeader]"+appToAppSSOHeader);
					// }
				}
				System.err.println("[appToAppSSOHeader][1]" + appToAppSSOHeader);
				try {
					if (xCsrfToken != null && xCsrfToken.equalsIgnoreCase("fetch")) {
						System.err.println("[callRestService][fetch]" + "");
						httpRequestBase = new HttpGet(requestURL);
						httpRequestBase.setHeader("x-csrf-token", xCsrfToken);
						if (appToAppSSOHeader != null) {
							httpRequestBase.addHeader(appToAppSSOHeader.getName(), appToAppSSOHeader.getValue());
						}
						httpResponse = httpClient.execute(httpRequestBase);
						// System.err.println("[callRestService][httpResponse]"
						// +httpResponse);
						Header[] headers = httpResponse.getAllHeaders();
						for (Header header : headers) {
							System.err.println("[callRestService][header.getName()]" + header.getName()
									+ ": header.getValue()" + header.getValue());
							if (header.getName().equalsIgnoreCase("x-csrf-token")) {
								xCsrfToken = header.getValue();
							}
						}
						appToAppSSOHeader = refreshAppToAppSSOHeader(requestURL, auth);
						System.err.println("[appToAppSSOHeader][2]" + appToAppSSOHeader);
					}
				} catch (IOException e) {
					System.err.println("Exception : " + e.getMessage());
				}
				System.err.println("[requestUrl]" + requestURL + "");
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
				httpRequestBase.addHeader("SAP-Connectivity-ConsumerAccount", "br339jmc4c");
				if(!ServicesUtil.isEmpty(ccLocationId))
					httpRequestBase.addHeader("SAP-Connectivity-SCC-Location_ID", ccLocationId);
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
								System.err.println("[returnJson]" + returnJson);
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

	private static AuthenticationHeader refreshAppToAppSSOHeader(String requestURL, RestUtilAuth auth) {
		Context ctx;
		AuthenticationHeader appToAppSSOHeader = null;
		AuthenticationHeaderProvider authHeaderProvider;
		if (!ServicesUtil.isEmpty(auth)) {
			try {
				ctx = new InitialContext();
				authHeaderProvider = (AuthenticationHeaderProvider) ctx.lookup("java:comp/env/AuthHeaderProvider");
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
		// System.err.println("[appToAppSSOHeader]"+appToAppSSOHeader);
		return appToAppSSOHeader;
	}

}