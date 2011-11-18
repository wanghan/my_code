package servlets;

import hibernate.DbAuthor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.rmi.SearchRMIClient;


public class AuthorItemShowServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuthorItemShowServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long id = Long.parseLong(request.getParameter("fid"));
		DbAuthor author=SearchRMIClient.getInstance().getDbAuthorFromDb(id);
		if (author == null || id < 0) {

			Err(request, response, "cannot find in db, author id="+id);
		}

		request.setAttribute("author", author);
		RequestDispatcher disp = request
				.getRequestDispatcher("/authorItemShow.jsp");
		disp.forward(request, response);
	}

	public void Err(HttpServletRequest request, HttpServletResponse response,
			String error) throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Error</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("<font color='red'>");
		out.print(error);
		out.print("</font>");
		out.print("	<a href='javascript:history.go(-1);'>Searching Result</a>");
		out.println(" Thanks!");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
