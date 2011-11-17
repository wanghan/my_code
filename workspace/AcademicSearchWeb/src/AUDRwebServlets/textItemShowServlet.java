package AUDRwebServlets;

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


public class textItemShowServlet extends HttpServlet{
	
	/**
	 * Constructor of the object.
	 */
	public textItemShowServlet() {
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
	PdfToSwf pts = new PdfToSwf();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		list = (ArrayList<DbPaper>)request.getSession().getAttribute("list");
		String id = request.getParameter("fid");

		if(list==null||id==null||id.equals(""))
		{
			Err(request,response,"session list is null or empty!");
		}
		
		TextItem Item = new TextItem();
		
		String[] ttc = {"DOC","DOCX","XLS","XLSX","PPT","TXT","PDF"};
		String[] tth = {"HTML"};
		
		//源文件
		String textPath = Item.getTqr().getOriginalFilePath();
		//待显示的文件
		String showPath = "";
		
		File file = new File(textPath);
		if(!file.exists()||file.isDirectory())
			Err(request,response,"查看文件不存在，请重试！！！");
		else if(file.isFile())
		{
			String filePath = file.getParent();
			String fileName = file.getName();
			String fileNameTemp = fileName.substring(0,fileName.indexOf("."));
			String fileType = fileName.substring(fileName.indexOf(".")+1,fileName.length());
			
			if(fileType==null||fileType.equals(""))
				Err(request,response,"查看文件类型错误，请重试！！！");
			else if(fileType.toUpperCase().equals(tth[0]))
			{//html文件显示
				showPath = textPath;
			}
			else
			{
				for (String string : ttc) {
					if(fileType.toUpperCase().equals(string))
					{
						//生成pdf、swf文件
						ServletContext   aa=request.getSession().getServletContext();   
						String   userPath  =   aa.getRealPath("\\");		//获取当前项目路径
						String p = userPath+"sys\\serverConfig.properties";
						
						showPath =  filePath+"\\"+fileNameTemp+".swf";
					}
				}
			}
		}
		else
		{Err(request,response,"网络错误，请重试！！！");}
		
		String[] itmsTextPath = textPath.split("\\\\");
		int i = 0;
		for (String string2 : itmsTextPath) {
//			System.out.println("string2:"+i+"、"+itmsTextPath.length+"||"+string2);
			i++;
		}
		System.out.println("itmsTextPath[itmsTextPath.length-1]:"+itmsTextPath[itmsTextPath.length-1]);
		String pp1 = itmsTextPath[itmsTextPath.length-1];
		
//		System.out.println("pp1:"+pp1);
		String[] itmsTextPath2 = pp1.split("\\.");
//		System.out.println("pp1 length:"+itmsTextPath2.length);
		
		for (String string2 : itmsTextPath2) {
//			System.out.println("string3:"+string2);
		}
		
		request.setAttribute("Item", Item);
		showPath = showPath.split("WebRoot")[1].replaceAll("\\\\", "/");
		textPath = textPath.split("fileText")[1].replaceAll("\\\\", "/");
		
		request.getSession().setAttribute("tp1", itmsTextPath[itmsTextPath.length-2]);
		request.getSession().setAttribute("tp2", itmsTextPath2[0]);
		request.getSession().setAttribute("tp3", itmsTextPath2[1]);
		
		request.setAttribute("showPath", showPath);
		RequestDispatcher disp = request.getRequestDispatcher("/SearchText/textShow.jsp");
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
	out.print("	<a href='javascript:history.go(-1);'>检索结果</a>");
	out.println(" 谢谢！！！");
	out.println("  </BODY>");
	out.println("</HTML>");
	out.flush();
	out.close();
	}
	
}
