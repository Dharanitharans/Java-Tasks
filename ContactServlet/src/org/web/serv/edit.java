package org.web.serv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Servlet implementation class edit
 */
@WebServlet("/edit")
public class edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int id = Integer.parseInt(request.getParameter("id"));
		String button = request.getParameter("button");
		String name = request.getParameter("Name");
		String number = request.getParameter("Number");
		try(PrintWriter out=response.getWriter())
		{
		if(button.equals("EDIT")){
			
			
			out.println("<!DOCTYPE html><html><head><meta charset=ISO-8859-1>");
			out.println("<link rel=stylesheet href=css/bootstrap.min.css><link rel=stylesheet href=css/bootstrap.css>");
			out.println("<title>EDIT</title></head><body>");
			out.println("<div class=container><div class=container-fluid>");
			out.println("<h1 style=\"text-align:center;margin-top: 100px;\">EDIT MENU</h1>");
			out.println("<div class=row style=margin-top: 50px;><div class=col></div><div class=col>");
	    	out.println("<form action=\"logout\">");
	    	out.println("<br><input type=submit value=logout class=btn btn-info style=\"margin-left=600px;\">");
	    	out.println("</form></div>");
		    out.println("</div></div>");
			out.println("<div class=row style=margin-top=50px;><div class=col></div>");
			out.println("<div class=col>");
    		out.println("<form class=form action=\"editdb\" method=get>");
    out.println("<div class=form-group>"+
    "<input type=text class=form-control name=id  value="+id+" style=text-align:center;>"+
    "<br></div>");
 
    out.println("<div class=form-group>"+
    "<input type=text class=form-control name=Name value="+name+" style=text-align:center;>"+
    "<br></div>");
  
    out.print("<div class=form-group>"+
   "<input type=text class=form-control name=Number value="+number+" style=text-align:center;>"+
    "<br></div><button type=submit class=btn btn-info>SAVE</button>");
  out.println("</form></div>");
  out.println("<div class=col></div></div></body></html>");
		}
		
		else{
			response.setContentType("text/plain");
			SessionFactory sf = new Configuration().configure().buildSessionFactory();
			Session session = sf.openSession();
			session.beginTransaction();
			Query query;
			query=session.createQuery("DELETE FROM Contact WHERE cnt_id=:id");
	    	query.setParameter("id",id);
	    	query.executeUpdate();
	    	session.getTransaction().commit();
	    	response.sendRedirect("view");		
		}
			
		}

		}
	}
