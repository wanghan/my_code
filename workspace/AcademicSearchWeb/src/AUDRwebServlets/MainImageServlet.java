package AUDRwebServlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sys.RebuildQueryResult;
import Common.SYSPROPERTIES;
import ImageCommon.Images;

import AudrConsole.UserQueryTransporter;

public class MainImageServlet extends HttpServlet {

	int perPage = 10;
	
	/**
	 * Constructor of the object.
	 */
	public MainImageServlet() {
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
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int nowPage = 1;
		int totPage = 0;
		
		try {
			totPage = Integer.parseInt(request.getSession().getAttribute("totPage").toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String tmp = request.getParameter("off.set");
		double nn = 0.0;
		try {
			nn = Double.parseDouble(tmp);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		nowPage =(int)nn;
		//System.out.println(nn+"=================");
		
		if(nowPage<1)
			nowPage = 1;
		else if(nowPage>totPage)
			nowPage = totPage;
		
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("startrow", (nowPage-1)*perPage);
		request.setAttribute("endrow", nowPage*perPage-1);
		
	
		RequestDispatcher disp = request.getRequestDispatcher("/SearchImage/showimageMinipage.jsp");
		disp.forward(request, response);
		return;
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
		request.setCharacterEncoding("utf-8");
		Images img = new Images();
		
		String pathSuccess = "/SearchImage/showimageMinipage.jsp";
//		String pathFail    = "/SearchImage/mainimage.jsp";
		String path = pathSuccess;
			
		//清除上次检索结果
		if(request.getSession().getAttribute("result")!=null){
			request.getSession().removeAttribute("result");
		}
		if(request.getSession().getAttribute("fullfilespath")!=null){
			request.getSession().removeAttribute("fullfilespath");
		}
		if(request.getSession().getAttribute("searchImagePath")!=null){
			request.getSession().removeAttribute("searchImagePath");
		}
		if(request.getSession().getAttribute("searchTypeC")!=null){
			request.getSession().removeAttribute("searchTypeC");
		}
		if(request.getSession().getAttribute("lireSearchType")!=null){
			request.getSession().removeAttribute("lireSearchType");
		}
		if(request.getSession().getAttribute("other")!=null){
			request.getSession().removeAttribute("other");
		}
		if(request.getSession().getAttribute("result")!=null){
			request.getSession().removeAttribute("result");
		}
		if(request.getSession().getAttribute("totPage")!=null){
			request.getSession().removeAttribute("totPage");
		}
		if(request.getSession().getAttribute("nowPage")!=null){
			request.getSession().removeAttribute("nowPage");
		}
		if(request.getSession().getAttribute("startrow")!=null){
			request.getSession().removeAttribute("startrow");
		}
		if(request.getSession().getAttribute("endrow")!=null){
			request.getSession().removeAttribute("endrow");
		}
		
		
		//服务器返回文件的存放路径
		ServletContext   aa=request.getSession().getServletContext();
		String   userPath =   aa.getRealPath("\\");
		
		SYSPROPERTIES sps = new SYSPROPERTIES(new File(userPath+"sys/tempFile.properties"));
		userPath += sps.getDirectoryName("image");
		File tmpFile = new File(userPath);
		if(!tmpFile.exists())
			tmpFile.mkdir();
		
		//图像文件的图像类型
		String imageType = "image";
		String tmpImgtype = request.getParameter("subtype");
		if(tmpImgtype!=null&&!tmpImgtype.equals(""))
			imageType = tmpImgtype;
		
		//检索类型
		String searchType = "3";
		String searchTypeC = "关联检索";
		//底层检索方式
		String type = "0";
		String tmpType = request.getParameter("lireSearchType");
		if(tmpType!=null&&!tmpType.equals(""))
			type = tmpType;
		//bf
		String[] bfs = img.bfe;
		ArrayList<String> bf = new ArrayList<String>();
		
		//sf
		String[] sfs = img.imageSFItems;
		ArrayList<String> sf = new ArrayList<String>();
		
		
		UserQueryTransporter userquery = new UserQueryTransporter();
		RebuildQueryResult  result = null;
		String searchImagePath = "";
		
		
		String rs = request.getParameter("rs");
		if(rs==null||rs.equals(""))
		{//原始检索
			
			String tmpsearchType = request.getParameter("searchType");
			if(tmpsearchType!=null&&!tmpsearchType.equals(""))
				searchType = tmpsearchType;
			//检索条件图像原型
			String tempsip = request.getParameter("imagefilepaths");
			if(tempsip!=null&&!tempsip.equals(""))
				searchImagePath = tempsip;
			
			//开始检索
			if(searchType.equals("1"))	//实例检索
			{
				System.out.println("===================实例检索=======================");
				searchTypeC = "实例检索";
				if(searchImagePath.equals(""))
				{
					request.setAttribute("fail", "实例检索:请先选择条件图片！！！");
					
					RequestDispatcher disp = request.getRequestDispatcher(path);
						disp.forward(request, response);
					return;
				}
				else
				{//条件符合，开始检索
					for (int i = 0; i < bfs.length; i++) {
							bf.add("nothing");
					}
					for (int i = 1; i < sfs.length; i++) {
							sf.add("nothing");
					}
					//System.out.println(aa.getRealPath("/")+searchImagePath.replaceAll("/", "\\\\"));
					File file = new File(aa.getRealPath("/")+searchImagePath.replaceAll("/", "\\\\"));
					result = userquery.Querybylire(file,type,imageType,userPath);
				}
			}
			else if(searchType.equals("2"))	//语义检索
			{
				System.out.println("===================语义检索=======================");
				searchTypeC = "语义检索";
				//String[] bfvalue = new String[bfs.length];
				for (int i = 0; i < bfs.length; i++) {
					String temp = request.getParameter(bfs[i]);
					if(temp==null||temp.equals(""))
						bf.add("nothing");
					else
						bf.add(temp);
				}
				//String[] sfvalue = new String[sfs.length];
				for (int i = 1; i < sfs.length; i++) {
					String temp = request.getParameter(sfs[i]);
					if(temp==null||temp.equals(""))
						sf.add("nothing");
					else
						sf.add(temp);
				}
				boolean bool = false;
				for (String b : bf) {
					 if(!b.equals("nothing"))
					 {
						 bool = true;
						 break;
					 }
				}
				for (String s : sf) {
					if(!s.equals("nothing"))
					 {
						 bool = true;
						 break;
					 }
				}
				
				if(!bool)
				{
					request.setAttribute("fail", "语义检索：请输入基本属性或语义特征！！！");
					RequestDispatcher disp = request.getRequestDispatcher(path);
						disp.forward(request, response);
					return;
				}
				else
				{
					System.out.println("=======================================");
					System.out.println(bf);
					System.out.println(sf);
					System.out.println("imageType:"+imageType);
					System.out.println("userPath:"+userPath);
					System.out.println("=======================================");
					result = userquery.Querybyexist(bf.toArray(new String[0]), sf.toArray(new String[0]),imageType,userPath);
				}
			}
			else if(searchType.equals("3"))	//关联检索
			{
				System.out.println("===================关联检索=======================");
				searchTypeC = "关联检索";
				//String[] bfvalue = new String[bfs.length];
				for (int i = 0; i < bfs.length; i++) {
					String temp = request.getParameter(bfs[i]);
					if(temp==null||temp.equals(""))
						bf.add("nothing");
					else
						bf.add(temp);
				}
				//String[] sfvalue = new String[sfs.length];
				for (int i = 1; i < sfs.length; i++) {
					String temp = request.getParameter(sfs[i]);
					if(temp==null||temp.equals(""))
						sf.add("nothing");
					else
						sf.add(temp);
				}
				
				boolean bool = false;
				for (String b : bf) {
					 if(!b.equals("nothing"))
					 {
						 bool = true;
						 break;
					 }
				}
				for (String s : sf) {
					if(!s.equals("nothing"))
					 {
						 bool = true;
						 break;
					 }
				}
				
				if(searchImagePath.equals(""))
				{
					request.setAttribute("fail", "关联检索：请输入检索图片!!!");
					RequestDispatcher disp = request.getRequestDispatcher(path);
					disp.forward(request, response);
					return;
				}
				else if(!bool)
				{
					request.setAttribute("fail", "关联检索：请输入基本属性或语义特征!!!");
					RequestDispatcher disp = request.getRequestDispatcher(path);
					disp.forward(request, response);
					return;
				}
				else if(!searchImagePath.equals("")&&bool)
				{
					//条件符合，可以检索

					System.out.println(bf);
					System.out.println(sf);
					File file = new File(aa.getRealPath("/")+searchImagePath.replaceAll("/", "\\\\"));
					result = userquery.QueryByAll(file,type,bf.toArray(new String[bf.size()]), sf.toArray(new String[sf.size()]),imageType,userPath);

				}
			}
		}
		else 
		{
			int n = 0,m = 0;
			if(rs.equals("text"))
			{//文本关联
				System.out.println("===================文本关联检索=======================");
				searchTypeC = "文本关联检索";
				n = 1;
				m = 1;
			}else if(rs.equals("audio"))
			{//文本关联
				System.out.println("===================音频关联检索=======================");
				searchTypeC = "音频关联检索";
				n = 5;
				m = 1;
			}else if(rs.equals("video"))
			{//文本关联
				System.out.println("===================视频关联检索=======================");
				searchTypeC = "视频关联检索";
				n = 4;
				m = 1;
			}
			
			if(n!=0&&m==1)
			{
				String[] tmp2 = {"avit_rs_cb_0_tx","avit_rs_cb_1_tx","avit_rs_cb_2_tx","avit_rs_cb_3_tx","avit_rs_cb_4_tx"};
				String[] tmp3 = {"avit_rs_cb_0_sl","avit_rs_cb_1_sl","avit_rs_cb_2_sl","avit_rs_cb_3_sl","avit_rs_cb_4_sl"};
					
				String d=request.getParameter("avitType");// 音频类型
				if(d!=null&&!d.equals(""))
					imageType=d.split(",")[0];
				
				//组织bf 
				String[] parabf = img.bfe;
				for (int i = 0; i < parabf.length; i++) {
					bf.add("nothing");
				}
				
				//文本关联来的值
				String[] tmp20 = new String[n];
				for (int i = 0; i < tmp20.length; i++) {
					String t0 = request.getParameter(tmp2[i]);
					if(t0==null||t0.equals(""))
						tmp20[i] = "nothing";
					else
						tmp20[i] = t0;
				}
				//关联值对应图像的sf项标记
				//关联值对应图像的sf项
				String[] tmp30 = new String[n];
				for (int i = 0; i < tmp30.length; i++) {
					tmp30[i] = request.getParameter(tmp3[i]);
				}
				
				//获取用户选中的项
				String[] tmp4 = request.getParameterValues("cb_rs");
				int[]    tmp40 ;//= new int[tmp4.length];
				if(tmp4!=null||tmp4.length!=0)
				{
					tmp40 = new int[tmp4.length];
					for (int i = 0; i < tmp4.length; i++) {
						try {
							tmp40[i] = Integer.parseInt(tmp4[i]);
						} catch (Exception e) {
							// TODO: handle exception
							tmp40[i] = 0; 
						}
					}
				}else
				{
					tmp40 = null;
					//tmp40[0] = 0;
				}
				
				//判断用户选择的文本关联---判断用户选择的图像关联
				
				String[] tmp50 = null;
				String[] tmp51 = null;
				if(tmp40!=null&&tmp40.length>0)
				{
					tmp50 = new String[tmp40.length];
					tmp51 = new String[tmp40.length];
					for (int i = 0; i < tmp40.length; i++) {
						tmp50[i] = tmp30[tmp40[i]];
						tmp51[i] = tmp20[tmp40[i]];
					}
				}
				
				//所有的sf项
				String[] parasf = img.imageSFItems;
				for (int i = 1; i < parasf.length; i++) {
					System.out.print(parasf[i]+"\t");
				}
				System.out.println();
				loop: for (int i = 1; i < parasf.length; i++) {
					 for (int j = 0; j < tmp50.length; j++) {
						if(tmp50[j].equals(parasf[i]))
						{
							sf.add(tmp51[j]);
							continue loop;
						}
					}
					sf.add("nothing");
				}
				System.out.println("bf.size():"+bf.size());
				System.out.println("sf.size():"+sf.size());
				System.out.println("=========================================");
				System.out.println("bf:"+bf);
				System.out.println("sf:"+sf);
				System.out.println("imageType:"+imageType);
				System.out.println("userPath:"+userPath);
				System.out.println("=========================================");
				
				try {
					result = userquery.Querybyexist(bf.toArray(new String[bf.size()]), sf.toArray(new String[sf.size()]),imageType,userPath);
				} catch (Exception e) {
					// TODO: handle exception
					request.setAttribute("fail", searchTypeC+"：关联错误");
					RequestDispatcher disp = request.getRequestDispatcher(path);
					disp.forward(request, response);
					return;
				}
				
				
			}
			else
			{
				request.setAttribute("fail", "其他文件关联检索：关联错误");
				RequestDispatcher disp = request.getRequestDispatcher(path);
				disp.forward(request, response);
				return;
			}
		}
		
		request.getSession().setAttribute("searchTypeC", searchTypeC);
		
		request.getSession().setAttribute("lireSearchType", img.getLireSearchType(type));
		request.getSession().setAttribute("other", img.getOther(bf.toArray(new String[0]), sf.toArray(new String[0])));
		
		//检索完毕
		if(result==null||result.getLireID().length==1)
		{
			request.setAttribute("fail", "检索结果为空");
		}
		else if(result.getLireID().length==1&&result.getLireID()[0].equals("bf is null"))
		{
			request.setAttribute("fail", "基本属性不匹配");
		}
		else if(result.getLireID().length==1&&result.getLireID()[0].equals("sf is null"))
        {
			request.setAttribute("fail", "语义特征不匹配");
        }
		else  if(result.getLireID().length==1&&result.getLireID()[0].equals("noresult"))
        {
			request.setAttribute("fail", "没有符合条件的图像");
        }
		else
		{  
			  //得到原始图像的路径
			  String[] fullFilespath= userquery.getFullFiles();
			  if(request.getSession().getAttribute("fullfilespath")!=null)
				   request.getSession().removeAttribute("fullfilespath");
			  request.getSession().setAttribute("fullfilespath", fullFilespath);
			
			request.getSession().setAttribute("searchImagePath", searchImagePath);
			
			request.getSession().setAttribute("result", result);
			
			//分页
			request.getSession().setAttribute("totPage", (result.getImage().length-1)/perPage+1);
			request.setAttribute("nowPage", 1);
			request.setAttribute("startrow", 0);
			request.setAttribute("endrow", perPage-1);
			
			System.out.println(result.getFullFile().length);
			path = pathSuccess;
			//System.out.println(result);
		}
		
		RequestDispatcher disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
		return ;
	}

	public void Err(HttpServletRequest request, HttpServletResponse response,String fail)
	throws ServletException, IOException {
		
		//以上9个地方引用本函数
		
	response.setContentType("text/html; charset=utf-8");
	PrintWriter out = response.getWriter();
	out
			.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
	out.println("<HTML>");
	out.println("  <HEAD><TITLE>A Error</TITLE></HEAD>");
	out.println("  <BODY>");
	out.println("  错误，错误类型：");
	out.print("    <font color=red>"+fail+"</font>，重新检索请点击  ");
	out.print("	<a href='./SearchImage/mainimage.jsp'>图像检索</a>");
	out.println(" 谢谢！！！");
	out.println("  </BODY>");
	out.println("</HTML>");
	out.flush();
	out.close();
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
