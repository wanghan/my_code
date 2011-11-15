package AUDRwebServlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import sys.RebuildQueryResult;
import sys.TextQueryResult;

import AudrConsole.UserQueryTransporter;

import AUDRcopyCS.ParseXml;
import AUDRwebJavaBeans.ImageBFObj;
import AUDRwebJavaBeans.ImageQueryInfo;
import AUDRwebJavaBeans.ImageSemanticFeature;
import AUDRwebJavaBeans.TextItem;
import Common.FileType;
import Common.SYSPROPERTIES;

public class ItemFileSelectServlet extends HttpServlet{

	FileType ft = new FileType();
	
	/**
	 * Constructor of the object.
	 */
	public ItemFileSelectServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		
		String type = request.getParameter("cfile_type");
		String subtype = request.getParameter("cfile_subtype");
		String id = request.getParameter("id");
		
		System.out.println(type+"->"+subtype+":"+id);
		
		StringBuffer sb = new StringBuffer();
		
		if(type==null||type.equals(""))
		{
			sb.append("<h1><font color='red'>查询错误：类型为空</font></h1>");
		}
		else
		{
			if(id==null||id.equals(""))
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
					
					//设置跳转路径
					String cmid[] = ft.cmid;
					String cmv[]  = new String[cmid.length];
					for (int i = 0; i < cmv.length; i++) {
						String tmp = "";
						if(request.getSession().getAttribute(cmid[i])!=null)
							tmp = request.getSession().getAttribute(cmid[i]).toString();
						
						cmv[i] = tmp;
					}
					//////////////////////////////////////////////////
					//查询开始==========================================
					if(type.equals(ft.filetye[0]))		//图像
					{
						//System.out.println("图像检索成功");
						if(cmv[3].equals(ft.dbtye[1]))
			    	   		editImage("AudrConsole/ConsoleMain.jsp",cmid,cmv,id,request,response);
			    	   	else if(cmv[3].equals(ft.dbtye[2]))
			    	   		editImage("AudrConsole/msg_image.jsp",cmid,cmv,id,request,response);
			    	   	else
			    	   		sb.append("请正确操作");
					}
					else if(type.equals(ft.filetye[1]))	//文本
					{
						System.out.println("文本检索成功");
						//System.out.println("音频检索成功");
						if(cmv[3].equals(ft.dbtye[1]))
			    	   		editText("AudrConsole/ConsoleMain.jsp",cmid,cmv,id,request,response);
			    	   	else if(cmv[3].equals(ft.dbtye[2]))
			    	   		editText("AudrConsole/msg_text.jsp",cmid,cmv,id,request,response);
			    	   	else
			    	   		sb.append("请正确操作");
					}
				}
			}
			
		}
		PrintWriter out=response.getWriter();
		out.write(sb.toString());
		out.close();
	}

	//显示图片
	private void editImage(String uri,String[] cmid,String[] cmv,String id,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String url = uri;//"AudrConsole/update_video.jsp";
		url+="?";
		for (int i = 0; i < cmv.length-1; i++) {
			url+=cmid[i]+"="+cmv[i];
			if(i!=cmv.length-2)
				url+="&";
		}	
		url += "&"+cmid[4]+"="+ft.action[12];
		
		//声明上一个、下一个
		int pre = 0;
		int nxt = 0;
		
		RebuildQueryResult result = (RebuildQueryResult) request.getSession().getAttribute("images");
		if(result==null)
			return;
		else
		{
			//String keyString=request.getParameter("id");//当前放大的图像对应的下标
			int key = 0; //每幅图像以在链表中的下标为对应的KEY
			try {
				key = Integer.parseInt(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if(key<=0)
			{
				pre = 0;
			}
			else
			{
				pre = key-1;
			}
			if(key>=result.getImage().length-1)
			{
				key = result.getImage().length-1;
				nxt = key;
			}
			else
			{
				nxt = key+1;
			}
			
			ArrayList<String> miniPics = new ArrayList<String>();
			for (int i = 0; i < result.getImage().length; i++) {
				miniPics.add(result.getImage()[i].getAbsolutePath().split("WebRoot")[1].replaceAll("\\\\","/"));
			}
			
			//原图
			String[] bigPics = (String[]) request.getSession().getAttribute("fullfilespath");
						
			//bf
			ImageBFObj bf = new ImageBFObj();
			ImageSemanticFeature sf = new ImageSemanticFeature();
			
			UserQueryTransporter uqTransporter1=new UserQueryTransporter();
			String []ID=new String[2];
			ID[0]="query";
			ID[1]=result.getLireID()[key];//得到要放大图象的LIREid
			//System.out.println("key:"+key+"\tID[1]:"+ID[1]);
			
			
			ServletContext   aa=request.getSession().getServletContext();   
			String   getfilepath =   aa.getRealPath("\\");
			
			SYSPROPERTIES sps = new SYSPROPERTIES(new File(getfilepath+"sys/tempFile.properties"));
			getfilepath += sps.getDirectoryName("image");
			File tmpFile = new File(getfilepath);
			if(!tmpFile.exists())
				tmpFile.mkdir();
			
			String filenameBF=getfilepath+"bf.xml";
			String filenameSF=getfilepath+"sf.xml";
			RebuildQueryResult  rs= uqTransporter1.QuerybylireID(ID,getfilepath);
			
			ImageQueryInfo imagequery = new ImageQueryInfo();
			if(rs!=null)
			 {
				 //得到基本属性和语义特征
				 try{
					 ParseXml xml=new ParseXml();
					 bf =  xml.setBF(rs, filenameBF).get(0);
					 sf =  xml.setSF(rs, filenameSF).get(0);
					 imagequery.setImageBF(key, bf);
					 imagequery.setImageSF(key, sf);
					 
					 //删除临时文件
					 File tmpbf = new File(filenameBF);
					 File tmpsf = new File(filenameSF);
					 if(tmpbf.exists())
						 tmpbf.delete();
					 if(tmpsf.exists())
						 tmpsf.delete();
				 }catch(SAXException r)
				 {
					 r.printStackTrace();
				 }
				 catch(ParserConfigurationException u)
				 {
					u.printStackTrace(); 
				 }
				 catch(IOException ee)
				 {
					 ee.printStackTrace();
				 }
				 
			 }
			
			request.setAttribute("pre",pre );
			request.setAttribute("nxt",nxt);
			
			request.setAttribute("id",result.getLireID()[key]);//for deic use
			request.setAttribute("bf", imagequery.getImageBF(key));
			request.setAttribute("sf", imagequery.getImageSF(key));
			request.setAttribute("nowPic", key);
			request.setAttribute("bigPic", bigPics[key].split("WebRoot")[1].replaceAll("\\\\","/"));
			
			RequestDispatcher disp = request.getRequestDispatcher(url);
			disp.forward(request, response);
			return ;
		}
	}
	
	//文本编辑
	private void editText(String uri,String[] cmid,String[] cmv,String id,
			HttpServletRequest request, HttpServletResponse response)
	{
		//String id = request.getParameter("id");
		String url = uri;
		url+="?";
		for (int i = 0; i < cmv.length-1; i++) {
			url+=cmid[i]+"="+cmv[i];
			if(i!=cmv.length-2)
				url+="&";
		}	
		url += "&"+cmid[4]+"="+ft.action[14];
		//System.out.println(url);
		
		ArrayList<TextItem> list = (ArrayList<TextItem>)request.getSession().getAttribute("texts");
		
		String fail = "";
		
		if(list==null||id==null||id.equals(""))
		{
			RequestDispatcher disp = request.getRequestDispatcher(url);
			try {
				disp.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				fail = "错误：可能是session过期";
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				fail = "错误：数据传输异常";
				e.printStackTrace();
			}
			return;
		}
		
		int flag = 0;
		TextItem Item = new TextItem();
		TextItem Item1 = new TextItem();
		TextItem Item2 = new TextItem();
		if(list!=null&&list.get(0).getTqr()!=null)
			for (int i=0;i<list.size();i++) {
				TextQueryResult r = list.get(i).getTqr();
				System.out.println(i+"-->id:"+id+";Item id:"+r.getId());
				if(id.equals(r.getId()))
				{
					Item = list.get(i);
					if(i==0)
						Item1 = Item;
					else
						Item1 = list.get(i-1);
					
					if(i==list.size()-1)
						Item2 = Item;
					else
						Item2 = list.get(i+1);
					flag++;
					break;
				}
			}
		
		//String textPath = Item.getTqr().getOriginalFilePath();
		if(flag!=0)
		{
			request.setAttribute("fail", fail);
			request.setAttribute("id", Item.getTqr().id);
			request.setAttribute("pre",Item1.getTqr().id );
			request.setAttribute("nxt",Item2.getTqr().id );
			
			request.setAttribute("Item", Item);
		}
		else
			request.setAttribute("fail", "ID错误，请正确输入");
			
		RequestDispatcher disp = request.getRequestDispatcher(url);
		try {
			disp.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
