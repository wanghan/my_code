package AUDRwebServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import AUDRwebJavaBeans.ImageJavaBean;
import AUDRwebJavaBeans.QueryBy;
import AudrConsole.DeicTransporter;
import sys.SystemInstances;
public class DeicServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("gb2312");
		String imageId=request.getParameter("imageId");
		String imagePath=request.getParameter("imagePath");
		String imageCategory=request.getParameter("imageCategory");
		String imageKeywords=request.getParameter("imageKeywords");
		String imageDescription=request.getParameter("imageDescription");
		String lireSearchType=new String("All MPEG-7 Descriptors");
	//	ImageJavaBean imageJB=new ImageJavaBean("1",imagePath,lireSearchType,imageType);
		String imagetempDir=request.getSession().getServletContext().getRealPath("/");//图像存放的服务器根目录
		java.io.File file1=new java.io.File(imagetempDir+imagePath);
		//System.out.println(file1.getAbsolutePath());
	//	imageJB.setFile(file1);
	//	imageJB.setTempfilepath(imagePath);
		DeicTransporter dt=new DeicTransporter();
		int successFlag=0;
		successFlag=dt.userSendImageInfo(file1, imageId, imagePath, imageCategory, imageKeywords,imagetempDir,imageDescription);
		//Systemout.println("hello");
	//	imageId="1";
		if(successFlag==1)//所有参数发送完后再进行跳转
			response.sendRedirect("http://"+SystemInstances.DEIC_IP+"/picClassify/template/pictureShow.jsp?imageId="+imageId);
		return;
	}

}
