package AUDRwebServlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import AudrConsole.UserUpdateImageTransporter;
import AudrConsole.UserUpdateTextTransporter;
import Common.FileType;

public class ItemFileUpdateServlet extends HttpServlet{

	FileType ft = new FileType();
	
	/**
	 * Constructor of the object.
	 */
	public ItemFileUpdateServlet() {
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
		
		StringBuffer sb = new StringBuffer();
		//声明返回url
		//String url = "AudrConsole/ConsoleMain.jsp";
		//错误类型
		String fail = "";
		
		String type = request.getParameter("cfile_type");
		String subtype = request.getParameter("cfile_subtype");
		String id = request.getParameter("id");
		
		System.out.println("///type:"+type);
		System.out.println("///subtype:"+subtype);
		System.out.println("///id:"+id);
		
		if(type==null||type.equals(""))
		{
			System.out.println("错误：文件类型为空");
			fail = "错误：文件类型为空";
			sb.append("错误：文件类型为空");
		}
		else if(ft.ContainsString(type, ft.filetye)<0||ft.ContainsString(type, ft.filetye)>=ft.filetye.length)
		{
			System.out.println("错误：文件类型不存在");
			fail = "错误：文件类型不存在";
			sb.append("错误：文件类型不存在");
		}
		else
		{
			//n为主类型在类型数组里面的位置
			int n = ft.ContainsString(type, ft.filetye);
			
			System.out.println("===type:"+type);
			System.out.println("===ft.filetye[0]:"+ft.filetye[n]);
			System.out.println("===n:"+n);
			
			if(id==null||id.equals(""))
			{
				System.out.println("错误：id为空");
				fail = "错误：id为空";
				sb.append("错误：id为空");
			}
			else
			{
				if(subtype==null||subtype.equals(""))
				{
					System.out.println("错误：文件子类型为空");
					fail = "错误：文件子类型为空";
					sb.append("错误：文件子类型为空");
				}
				else if(ft.ContainsString(subtype, ft.fte[n])<0||ft.ContainsString(subtype, ft.fte[n])>=ft.fte[n].length)
				{
					System.out.println("错误：文件子类型不存在");
					fail = "错误：文件子类型不存在";
					sb.append("错误：文件子类型不存在");
				}
				else
				{
					//m为子类型在类型数组里面的位置
					int m = ft.ContainsString(subtype, ft.fte[n]);
					
					System.out.println("===****subtype:"+subtype);
					System.out.println("===****ft.fte[n][0]:"+ft.fte[n][m]);
					System.out.println("===****m:"+m);
					
					///返回路径
					//String path = "AudrConsole/ConsoleMain.jsp";
//					String cmid[] = ft.cmid;
//					String cmv[]  = new String[cmid.length];
//					for (int i = 0; i < cmv.length; i++) {
//						String tmp = "";
//						if(request.getSession().getAttribute(cmid[i])!=null)
//							tmp = request.getSession().getAttribute(cmid[i]).toString();
//						
//						cmv[i] = tmp;
//					}
//					
//					//生成url参数
//					url+="?";
//					for (int i = 0; i < cmv.length-1; i++) {
//						url+=cmid[i]+"="+cmv[i];
//						if(i!=cmv.length-2)
//							url+="&";
//					}	
					
					ServletContext   aa=request.getSession().getServletContext();  
					String   userPath =   aa.getRealPath("\\");
					//////////////////////////////////////////////////
					//修改开始==========================================
					//获取所有提交的参数的key值、value值
					String[] bfk = ft.bfe[n];
					String[] bfv = new String[bfk.length];
					String[] sfk = ft.sfe[n];
					String[] sfv = new String[sfk.length];
					
					//传递参数
					HashMap map = new HashMap();
					//返回值
					String rs = "";
					//记录是否修改，以及修改属性数目
					int x = 0;
					for (int i = 0; i < bfk.length; i++) {
						String tmp = request.getParameter(bfk[i]);
						String tmp1 = request.getParameter(bfk[i]+"_v");
						if(tmp!=null&&!tmp.equals("")&&tmp1!=null&&!tmp.equals(tmp1))
						{
							bfv[i] = tmp;
							map.put(bfk[i], bfv[i]);
							x++;
						}
						else
						{
							bfv[i] = "nothing";
						}
					}
					for (int i = 0; i < sfk.length; i++) {
						String tmp = request.getParameter(sfk[i]);
						String tmp1 = request.getParameter(sfk[i]+"_v");
						if(tmp!=null&&!tmp.equals("")&&tmp1!=null&&!tmp.equals(tmp1))
						{
							sfv[i] = tmp;
							map.put(sfk[i], sfv[i]);
							x++;
						}
						else
						{
							sfv[i] = "nothing";
						}
					}
					
					System.out.println("map:"+map);
					
					if(type.equals(ft.filetye[0]))		//图像
					{
						String imagefilepath = request.getParameter("imagefilepath");
						if(imagefilepath==null)
							imagefilepath = "";
						File imageFile = new File(userPath+imagefilepath);
						
						//信息准备完成，修改开始
						UserUpdateImageTransporter uit = new UserUpdateImageTransporter();
						if(x==0)
						{//语义信息未修改
							if(imageFile.exists())
							{
								rs = uit.updateImage(id, imageFile.getAbsolutePath(), null);
								sb.append(rs);
//								if(rs.equals(""))
//									System.out.println("修改失败");
//								else
//									System.out.println("修改成功");
							}
							else
							{
								System.out.println("错误：上传图片错误，请重新上传");
								fail = "错误：上传图片错误，请重新上传";
								sb.append("错误：上传图片错误，请重新上传");
							}
						}
						else
						{
							if(imagefilepath.equals(""))
							{
								rs = uit.updateImage(id, "", map);
								sb.append(rs);
//								if(rs.equals(""))
//									System.out.println("修改失败");
//								else
//									System.out.println("修改成功");
							}
							else
							{
								System.out.println("错误：不能同时修改文件信息和属性信息");
								fail = "错误：不能同时修改文件信息和属性信息";
								sb.append("错误：不能同时修改文件信息和属性信息");
							}
						}
						
//						url += "&"+cmid[4]+"="+ft.action[12];
						
					}
					else if(type.equals(ft.filetye[1]))	//文本
					{
						//文本文件
						String textfilepath = request.getParameter("textfilepath");
						if(textfilepath==null)
							textfilepath = "";
						File textFile = new File(userPath+textfilepath);
						//底层文件
						String txtlfFilePath = request.getParameter("txtlfFilePath");
						if(txtlfFilePath==null)
							txtlfFilePath = "";
						File txtlfFile = new File(userPath+txtlfFilePath);
						
						//信息准备完成，修改开始
						UserUpdateTextTransporter utt = new UserUpdateTextTransporter();
						if(x==0)
						{//语义信息未修改
							if(textFile.exists())
							{
								rs = utt.updateText(id,txtlfFile.getAbsolutePath(), textFile.getAbsolutePath(), null);
								System.out.println("文本修改文件："+rs);
								sb.append(rs);
//								if(rs.equals(""))
//									System.out.println("修改失败");
//								else
//									System.out.println("修改成功");
							}
							else
							{
								System.out.println("错误：上传文本错误，请重新上传");
								fail = "错误：上传文本错误，请重新上传";
								sb.append("错误：上传文本错误，请重新上传");
							}
						}
						else
						{
							if(textfilepath!=null&&textfilepath.equals(""))
							{
								rs = utt.updateText(id, "","", map);
								System.out.println("文本修改语义："+rs);
								sb.append(rs);
//								if(rs.equals(""))
//									System.out.println("修改失败");
//								else
//									System.out.println("修改成功");
							}
							else
							{
								System.out.println("错误：不能同时修改文件信息和属性信息");
								fail = "错误：不能同时修改文件信息和属性信息";
								sb.append("错误：不能同时修改文件信息和属性信息");
							}
						}
//						url += "&"+cmid[4]+"="+ft.action[14];
					}
				}
			}
		}
		
//		request.getRequestDispatcher(url).forward(request, response);
		PrintWriter out=response.getWriter();
		out.write(sb.toString());
		out.close();
	}
	
	public void updrs(String url,String msg)
	{
		
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
}
