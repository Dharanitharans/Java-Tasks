package org.web.serv;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.web.model.Contact;


/**
 * Servlet implementation class adddb
 */
@WebServlet("/adddb")
public class adddb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adddb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		int id =Integer.parseInt( request.getParameter("id"));
		String name = request.getParameter("Name");
		long number = Long.valueOf(request.getParameter("Number"));
		Contact contact = new Contact();
		contact.setId(id);
		contact.setName(name);
		contact.setNumber(number);
		session.beginTransaction();
		session.save(contact);
		session.getTransaction().commit();
		response.sendRedirect("view");
	}



}
