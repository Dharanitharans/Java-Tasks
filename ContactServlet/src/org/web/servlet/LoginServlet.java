package org.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.web.serv.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginvalidate")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Validate Servlet - Get</title>");
			out.println("</head>");
			out.println("<body>");
			String code = LoginService.validate(username, password);
			if (code != null) {
				if(code.isEmpty()) {
					out.println("Already Logged in another device");
				} else {
					out.println("Logged with Access Key : " + code);
					out.println("Success : ");
					Cookie cookie = new Cookie("auth_user", username);
					cookie.setMaxAge(24 * 60 * 60);
					response.addCookie(cookie);
					cookie = new Cookie("auth_key", code);
					cookie.setMaxAge(24 * 60 * 60);
					response.addCookie(cookie);
					response.sendRedirect("view");
				}
			} else {
				request.getRequestDispatcher("error.html").include(request, response);
			}
			out.println("</body>");
			out.println("</html>");
		}
	}

}
