package org.web.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.web.model.Contact;
import org.web.util.HBUtil;
import org.web.util.Utility;

/**
 * Servlet implementation class ViewContactServlet
 */
@WebServlet("/view")
public class ViewContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = Utility.getCookieValue(request, "auth_user");
		String code = Utility.getCookieValue(request, "auth_key");
		response.setContentType("text/html");
		if (code != null && username != null && LoginService.check(username, code)) {
			
			
			try(PrintWriter out=response.getWriter())
			{
				response.setContentType("text/html");
			Session session = HBUtil.get().openSession();
			out.println("<html>");
			out.println("<meta charset=ISO-8859-1>");
			out.println("<link rel=\"stylesheet\" href=css/bootstrap.min.css> <link rel=\"stylesheet\" href=css/bootstrap.css>");
			out.println("<head>");
			out.println("<title>Insert title here</title>");
			out.println("</head>");
			out.println("<body>");

			Query query=session.createQuery("from Contact");
			    @SuppressWarnings("unchecked")
				List<Contact> item_all=(List<Contact>)query.getResultList();
			    out.println("<div class=container><div class=container-fluid-center><div class=row>");
			    out.println("<div class=col></div><div class=col>");
		    	out.println("<form action=\"add\" method=get>");
		    	out.println("<br><input type=submit name=button value=add class=btn btn-info style=\"margin-left=0px;\">");
		    	out.println("</form></div>");
			    out.println("<div class=col>");
		    	out.println("<form action=\"logout\">");
		    	out.println("<br><input type=submit value=logout class=btn btn-info style=\"margin-left=00px;\">");
		    	out.println("</form></div>");
			    out.println("</div></div>");
			    out.println("<div class=row style=\"margin-top:50px; margin-left:200px;\">");
			    for(Contact each:item_all)
			    {out.println("<form action=\"edit\" method=get>");
		
			    	out.println("<input type=text name=id value="+each.getId()+"  style=\"border:none; text-align:center\">");
			    	out.println("<input type=text name=Name value="+each.getName()+"  style=border:none;>");
			    	out.println("<input type=text name=Number value="+each.getNumber()+"  style=border:none;>");
			    	out.println("<input type=submit name=button value=EDIT class=btn btn-info>");
			    	out.println("<input type=submit name=button value=DELETE class=btn btn-info>");
			    	out.println("</form>");
			    	
			    }
			 

			out.println("</div></div>");
			out.println("</div></body>");
			out.println("</html>");
			
		}	
			
		} else {
			request.getRequestDispatcher("login.html").include(request, response);
		}
	}

}
