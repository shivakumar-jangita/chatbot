package com.sap.itservices.copilot.smalltalk.utils;

import java.util.Collection;

/**
 * Contains utility functions to be used by Services
 * 
 * @version R1
 */
public class ServicesUtil {

	public static boolean isEmpty(Object[] objs) {
		if (objs == null || objs.length == 0) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Collection<?> o) {
		if (o == null || o.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(String str) {
		if (str == null || str.trim().isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(StringBuffer sb) {
		if (sb == null || sb.length() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(StringBuilder sb) {
		if (sb == null || sb.length() == 0) {
			return true;
		}
		return false;
	}

	public static String getBasicAuth(String userName, String password) {
		String userpass = userName + ":" + password;
		return "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
	}

	public static String getAuthorization(String accessToken, String tokenType) {
		return tokenType + " " + accessToken;
	}

}
