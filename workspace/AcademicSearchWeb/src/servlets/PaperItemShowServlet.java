package servlets;

import hibernate.DbPaper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sys.TextQueryResult;
import textCommon.PdfToSwf;

import AUDRwebJavaBeans.TextItem;


public class PaperItemShowServlet extends HttpServlet{
	
	/**
	 * Constructor of the object.
	 */
	public PaperItemShowServlet() {
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
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
	ArrayList<DbPaper> list;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		list = (ArrayList<DbPaper>)request.getSession().getAttribute("list");
		int id = Integer.parseInt(request.getParameter("fid"));

		if(list==null||id<0)
		{
			
			Err(request,response,"paper id null or empty!");
		}
		
		DbPaper Item=null;
		for (DbPaper paper : list) {
			if(paper.getId()==id){
				Item=paper;
				break;
			}
		}
		
		request.setAttribute("Item", Item);
		RequestDispatcher disp = request.getRequestDispatcher("/textItemShow.jsp");
		disp.forward(request, response);
	}
	
	
	public void Err(HttpServletRequest request, HttpServletResponse response,String error)
	throws ServletException, IOException {

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
