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
import org.web.util.HBUtil;

/**s
 * Servlet implementation class editdb
 */
@WebServlet("/editdb")
public class editdb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editdb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int id =Integer.parseInt( request.getParameter("id"));
		String name = request.getParameter("Name");
		long number = Long.valueOf(request.getParameter("Number"));
		try(PrintWriter out=response.getWriter())
		{
			Session session = HBUtil.get().openSession();  				    	
		    	Query query=session.createQuery("UPDATE Contact  SET cnt_name=:name_up,cnt_number=:number_up WHERE cnt_id=:id");
		    	query.setParameter("id",id);
		    	query.setParameter("name_up",name);
		    	query.setParameter("number_up",number);					    	
		    	session.beginTransaction();
		    	query.executeUpdate();
		    	session.getTransaction().commit();
		    	response.sendRedirect("view");
		 
	
			
		}
		}
	}


