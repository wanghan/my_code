package AUDRwebServlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sys.Configuration;

public class AUDRConfigAction  extends HttpServlet{

	/**
	 * Constructor of the object.
	 */
	public AUDRConfigAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
//		System.out.println("AUDR CONFIG========================");
		
		if(Configuration.GetInstance().configFilePath==null||Configuration.GetInstance().configFilePath.equals(""))
		{
			
			ServletContext   aa=request.getSession().getServletContext();
			String   userPath =   aa.getRealPath("\\");
//			System.out.println(userPath);
			Configuration.GetInstance().setConfigFilePath(userPath+"ServerIP.properties");
			
			System.out.println(Configuration.GetInstance().SetIP());
			
		}
		request.getRequestDispatcher("/index.jsp").forward(
				request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		doGet( request, response );
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
