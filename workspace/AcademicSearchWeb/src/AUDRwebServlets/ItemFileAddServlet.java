package AUDRwebServlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Common.FileType;

public class ItemFileAddServlet extends HttpServlet{

	/**
	 * Constructor of the object.
	 */
	public ItemFileAddServlet() {
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
		
		ServletContext   aa=request.getSession().getServletContext();  
		String   userPath =   aa.getRealPath("\\");
		
		//System.out.print(userPath);
		String cfile_type = request.getParameter("cfile_type");
		String cfile_subtype = request.getParameter("cfile_subtype");
		
		
		//文件信息
		String[] addnames = {"cf_f_txtv","cf_t_txtv","cf_m_txtv","cf_n_txtv","cf_u_txtv"};
		//文件、语义文件、关键帧、镜头信息文件、提交者
		String[] addvals  = new String[addnames.length];
		
		//获取用户提交信息
		for (int i = 0; i < addvals.length; i++) {
			String tmp = request.getParameter(addnames[i]);
			if(tmp==null||tmp.equals(""))
			{
				addvals[i] = "nothing";
			}
			else
			{
				addvals[i] = tmp;
			}
		}
		
		
		StringBuffer sb = new StringBuffer();
		
		int flag = 0;
		//原始文件
		File audr_file = new File(userPath+addvals[0]);
		//语义文件
		File semantics_file = new File(userPath+addvals[1]);
		//关键帧
		String[] kfms = addvals[2].split(",");
		File[] kfmfs = new File[kfms.length];
		if(kfms!=null&&kfms.length>0)
		{
			for (int i = 0; i < kfmfs.length; i++) {
				File tmp = new File(userPath+kfms[i]);
				if(tmp.exists())
					kfmfs[i] = tmp;
				else
				{
					kfmfs[i] = null;
					flag++;
				}
			}
			
		}
		
		//镜头信息文件
		File lens_file = new File(userPath+addvals[3]);
		
		//文件基本信息
		
		
		if(!audr_file.exists())
		{
			sb.append("错误：文件不存在，请重新上传");
			System.out.println("错误：文件不存在，请重新上传");
		}
		else if(cfile_type==null||cfile_type.equals("")
				||cfile_subtype==null||cfile_subtype.equals(""))
		{
			sb.append("错误：类型为空");
			System.out.println("错误：类型为空");
		}
		else
		{
			//System.out.println(audr_file.getAbsolutePath());
			int n = ft.ContainsString(cfile_type, ft.filetye);
			int m = ft.ContainsString(cfile_subtype, ft.fte[n]);
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
			String date=sdf.format(new Date());
			String sizek = "filesize";
			String sizem = "filesize";
			try {
				FileInputStream fileInputStream = new FileInputStream(audr_file);
				sizek =Math.round(fileInputStream.available()/ 1024 ) + " KB";
				sizem =Math.round(fileInputStream.available()/ (1024 * 10.24)) / 100.0 + " MB";
		        fileInputStream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//查询开始==========================================
			if(n==0)		//图像
			{
				if(!semantics_file.exists())
				{
					sb.append("错误：语义文件不存在，请重试");
					System.out.println("错误：语义文件不存在，请重试");
				}
				else if(addvals[4].equals("nothing"))
				{
					sb.append("错误：请正确使用本系统");
					System.out.println("错误：请正确使用本系统");
				}
				else
				{
					//正确，可以添加
					
				}
			}
			else if(n==1)	//文本
			{
				//正确，可以添加
	
				
				//生成bf
				String[] bf = {date+ ";" +addvals[4]+ ";" +audr_file.getAbsolutePath()+ ";" +audr_file.getAbsolutePath()+ ";" +sizek};
				String[] urls = {audr_file.getAbsolutePath()};
				String[] sf = {};
				//String msg = ut.receiveInsertInfo(urls, bf, semanticfeaturepath, lfFilePath, textType);
				sb.append("添加成功");
			}
			
			else
			{
				sb.append("错误：主类型不存在，请重试");
				System.out.println("错误：主类型不存在，请重试");
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
}

