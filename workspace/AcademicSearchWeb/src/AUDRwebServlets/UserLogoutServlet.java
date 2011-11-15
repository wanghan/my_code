package AUDRwebServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLogoutServlet  extends HttpServlet{

	public UserLogoutServlet() {
		// TODO Auto-generated constructor stub
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
		if(request.getSession().getAttribute("username")!=null&&!request.getSession().getAttribute("username").toString().equals(""))
		{
			request.getSession().removeAttribute("username");
			request.getSession().removeAttribute("userpower");
			//request.setAttribute("fail","ÍË³ö³É¹¦");
			request.getRequestDispatcher("/AudrConsole/logout.jsp").forward(
					request, response);
		}
		else
		{
			request.getRequestDispatcher("/AudrConsole/userlogin.jsp").forward(
					request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
	}
	
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
