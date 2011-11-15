package AUDRwebServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginServlet  extends HttpServlet{

	public UserLoginServlet() {
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
		
		
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usercode = request.getParameter("usercode");
		
		String path = "";
		
		if(usercode==null||usercode.equals("")||request.getSession().getAttribute("_CODE")==null)
		{
			request.setAttribute("fail","<hr width='240' color='#eeeeee'>提示：<font color='red'>验证码为空</font>");
			path = "/AudrConsole/userlogin.jsp";
		}
		else if(usercode.trim().equals(request.getSession().getAttribute("_CODE").toString()))
		{
			if(username!=null&&password!=null&&
					username.trim().equals("admin")&& password.trim().equals("admin"))
			{
				//request.getSession().setAttribute("logined", 1);
				request.getSession().setAttribute("username", "admin");
				request.getSession().setAttribute("userpower", "sys");
				path = "/AudrConsole/ConsoleMain.jsp";
			}
			else
			{
				request.setAttribute("fail","<hr width='240' color='#eeeeee'>提示：<font color='red'>用户名或者密码错误</font>");
				path = "/AudrConsole/userlogin.jsp";
				
			}
		}
		else
		{
			request.setAttribute("fail","<hr width='240' color='#eeeeee'>提示：<font color='red'>验证码错误</font>");
			path = "/AudrConsole/userlogin.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
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
