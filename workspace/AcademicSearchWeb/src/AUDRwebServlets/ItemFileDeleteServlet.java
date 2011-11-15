package AUDRwebServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import AudrConsole.UserDeleteImageTransporter;
import AudrConsole.UserDeleteTextTransporter;
import Common.FileType;

public class ItemFileDeleteServlet extends HttpServlet{

	/**
	 * Constructor of the object.
	 */
	public ItemFileDeleteServlet() {
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
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		FileType ft = new FileType();
		
		String type = request.getParameter("cfile_type");
		String subtype = request.getParameter("cfile_subtype");
		String[] ids = request.getParameterValues("iform");
		
		System.out.println(type+"->"+subtype+":"+ids);
		
		StringBuffer sb = new StringBuffer();
		
		if(type==null||type.equals(""))
		{
			sb.append("<h1><font color='red'>查询错误：类型为空</font></h1>");
		}
		else
		{
			if(ids==null||ids.length==0)
			{
				sb.append("<h1><font color='red'>查询错误：id为空</font></h1>");
			}
			else
			{
				int n = ft.ContainsString(type, ft.filetye);
				if(n<0||n>=ft.filetye.length)
				{
					sb.append("<h1><font color='red'>查询错误：类型不存在</font></h1>");
				}
				else
				{
					int m = ft.ContainsString(subtype, ft.fte[n]);
					if(subtype==null||subtype.equals(""))
					{
						subtype = type;
					}
					else if(m<0||m>=ft.fte[n].length)
					{
						sb.append("<h1><font color='red'>查询错误：子类型不存在</font></h1>");
					}
					else
					{
						subtype = subtype;
					}
					
					String rs = "";
					//查询开始==========================================
					if(type.equals(ft.filetye[0]))		//图像
					{
						UserDeleteImageTransporter udit = new UserDeleteImageTransporter();
						for (String string : ids) {
							System.out.print(string+"\t");
						}
						System.out.println();
						rs = udit.deleteImage(ids);
						sb.append(rs);
//						if(rs.equals(""))
//							sb.append("删除成功");
//						else
//							sb.append("删除失败");
					}
					else if(type.equals(ft.filetye[1]))	//文本
					{
						UserDeleteTextTransporter udit = new UserDeleteTextTransporter();
						for (String string : ids) {
							System.out.print(string+"\t");
						}
						System.out.println();
						rs = udit.deleteText(ids);
						System.out.println("文本文件删除："+rs);
						sb.append(rs);
//						for (String string : ids) {
//							System.out.print(string+"\t");
//						}
//						System.out.println();
//						sb.append("<h1><font color='red'>删除成功</font></h1>");
					}
				}
			}
			
		}
		PrintWriter out=response.getWriter();
		out.write(sb.toString());
		out.close();
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
