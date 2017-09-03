package org.web.util;

import java.security.SecureRandom;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Utility {
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static SecureRandom rnd = new SecureRandom();

	public static String generateKey(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	public static String getCookieValue(HttpServletRequest request, String name) {
		String value = null;
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
		    for (int i = 0; i < cookies.length; i++) {
		        if(cookies[i].getName().equals(name)) {
		        	value = cookies[i].getValue();
		        }
		    }
		}
		return value;
	}
}
