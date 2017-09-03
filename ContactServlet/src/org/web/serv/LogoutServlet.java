package org.web.serv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.web.util.Utility;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = Utility.getCookieValue(request, "auth_user");
		String code = Utility.getCookieValue(request, "auth_key");
		LoginService.logout(response, username, code);
		try(PrintWriter out=response.getWriter())
		{
		response.setContentType("text/html");
		out.println("<!DOCTYPE html><html><head><title>LOGOUT</title></head><body>");
		out.println("<h1 style=\"text-align:center;margin-top: 100px;\">LOGGED OUT SUCCESSFULLY</h1>");
		out.println("</body></html>");
		}
	}

}
