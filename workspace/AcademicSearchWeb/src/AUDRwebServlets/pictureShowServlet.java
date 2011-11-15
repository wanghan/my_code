package AUDRwebServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pictureShowServlet extends HttpServlet{

	public pictureShowServlet() {
		// TODO Auto-generated constructor stub
		super();
	}
	/**
	 * Constructor of the object.
	 */
	
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		String id = request.getParameter("imageId");
		String imagePath = request.getParameter("imagePath");
		String imageCategory = request.getParameter("imageCategory");
		String imageKeywords = request.getParameter("imageKeywords");
		String imageDescription = request.getParameter("imageDescription");
		
		if(id==null||id.equals("")||
				imagePath==null||imagePath.equals(""))
		{
			System.out.println("error");
		}else
		{
			request.setAttribute("id",id);//for deic use
			request.setAttribute("imageCategory", imageCategory);
			request.setAttribute("imageKeywords", imageKeywords);
			request.setAttribute("imageDescription", imageDescription);
			request.setAttribute("bigPic", imagePath);
			
			request.getRequestDispatcher("/picClassify/pictureShow.jsp").forward(request, response);
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
