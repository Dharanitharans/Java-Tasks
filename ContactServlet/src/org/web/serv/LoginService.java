package org.web.serv;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.web.model.Users;
import org.web.util.HBUtil;
import org.web.util.Utility;

public class LoginService {
	public static String validate(String username, String password) {
		String code = null;
		Session session = HBUtil.get().openSession();
		session.beginTransaction();
		Users user = session.get(Users.class, username);
		if (user != null && user.getPassword().equals(password)) {
			if (user.getCode().isEmpty()) {
				code = Utility.generateKey(20);
				user.setCode(code);
				session.update(user);
			} else {
				code = "";
			}
		}
		session.getTransaction().commit();
		session.close();
		return code;
	}
	
	public static boolean logout(HttpServletResponse response, String username, String code) {
		boolean status = false;
		Session session = HBUtil.get().openSession();
		session.beginTransaction();
		Users user = session.get(Users.class, username);
		if (user != null && user.getCode().equals(code)) {
			Cookie cookie = new Cookie("auth_user", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			cookie = new Cookie("auth_key", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			user.setCode("");
			status = true;
			session.update(user);
		}
		session.getTransaction().commit();
		session.close();
		return status;
	}

	public static boolean check(String username, String code) {
		boolean status = false;
		Session session = HBUtil.get().openSession();
		Users user = session.get(Users.class, username);
		if (user != null && user.getCode().equals(code)) {
			status = true;
		}
		session.close();
		return status;
	}
}
