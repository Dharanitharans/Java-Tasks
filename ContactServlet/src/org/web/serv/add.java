package org.web.serv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class add
 */
@WebServlet("/add")
public class add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out=response.getWriter())
		{
		response.setContentType("text/html");
		
		out.println("<!DOCTYPE html><html><head><meta charset=ISO-8859-1>");
		out.println("<link rel=stylesheet href=css/bootstrap.min.css><link rel=stylesheet href=css/bootstrap.css>");
		out.println("<title>EDIT</title></head><body>");
		out.println("<div class=container><div class=container-fluid>");
		out.println("<h1 style=\"text-align:center;margin-top: 100px;\">EDIT MENU</h1>");
		out.println("<div class=row style=margin-top: 50px;><div class=col></div><div class=col> </div></div>");
		out.println("<div class=row style=margin-top:50px;><div class=col></div>");
		out.println("<div class=col>");
		out.println("<form class=form action=\"adddb\" method=get>");
out.println("<div class=form-group>"+
"<input type=text class=form-control name=id  placeholder=ID style=text-align:center;>"+
"<br></div>");

out.println("<div class=form-group>"+
"<input type=text class=form-control name=Name placeholder=NAME style=text-align:center;>"+
"<br></div>");

out.print("<div class=form-group>"+
"<input type=text class=form-control name=Number placeholder=NUMBER style=text-align:center;>"+
"<br></div><button type=submit class=btn btn-info>SAVE</button>");
out.println("</form></div><div class=col></div></div></body></html>");


		}
	}

}
