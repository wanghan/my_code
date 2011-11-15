package AUDRwebServlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import sys.TextQueryResult;

import AUDRwebJavaBeans.TextItem;

public class DownServletCommon extends HttpServlet {
//	private final static String SAVEPATH = "c:/textDown/";
	private final static int BTYE_SIZE = 2048;
	/**
	 * Constructor of the object.
	 */
	public DownServletCommon() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void Err(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
	PrintWriter out = response.getWriter();
	out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
	out.println("<HTML>");
	out.println("  <HEAD><TITLE>A Error</TITLE></HEAD>");
	out.println("  <BODY>");
	out.print("    下载失败，  ");
	out.print("	<a href='history.back()；'>返回</a>");
	out.println(" 谢谢！！！");
	out.println("  </BODY>");
	out.println("</HTML>");
	out.flush();
	out.close();
	}
	
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
		request.setCharacterEncoding("gb2312");
		//Item = (TextItem)request.getSession().getAttribute("tit");
		String fpath = request.getParameter("fpath");
		if(fpath==null)
			Err(request,response);
		
		ServletContext tempPath = request.getSession().getServletContext();
		String   servPath  =   tempPath.getRealPath("\\");
		
		String filepath = servPath+"/"+fpath;
		
		File file = new File(filepath);
		if(!file.exists())
			Err(request,response);
		
		response.reset();//可以加也可以不加
		response.setContentType("application/x-download");//设置为下载application/x-download
		String filenamedisplay = URLEncoder.encode(file.getName(),"UTF-8");
		response.addHeader("Content-Disposition","attachment;filename=" + filenamedisplay);
		System.out.println("filePath:"+file.getAbsolutePath());
		OutputStream output = null;
		FileInputStream fis = null;
		
		try {
			output = response.getOutputStream();
			fis = new FileInputStream(filepath);
			byte[] b = new byte[1024];
			int i = 0;
			while((i = fis.read(b)) > 0)
			{
				output.write(b, 0, i);
			}
			output.flush();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			if(fis != null)
			{
				fis.close();
				fis = null;
			}
			if(output != null)
			{
				output.close();
				output = null;
			}
		}
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

		doGet( request, response );
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	

}
