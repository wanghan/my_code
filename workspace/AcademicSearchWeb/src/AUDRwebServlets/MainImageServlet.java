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
			
		//����ϴμ������
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
		
		
		//�����������ļ��Ĵ��·��
		ServletContext   aa=request.getSession().getServletContext();
		String   userPath =   aa.getRealPath("\\");
		
		SYSPROPERTIES sps = new SYSPROPERTIES(new File(userPath+"sys/tempFile.properties"));
		userPath += sps.getDirectoryName("image");
		File tmpFile = new File(userPath);
		if(!tmpFile.exists())
			tmpFile.mkdir();
		
		//ͼ���ļ���ͼ������
		String imageType = "image";
		String tmpImgtype = request.getParameter("subtype");
		if(tmpImgtype!=null&&!tmpImgtype.equals(""))
			imageType = tmpImgtype;
		
		//��������
		String searchType = "3";
		String searchTypeC = "��������";
		//�ײ������ʽ
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
		{//ԭʼ����
			
			String tmpsearchType = request.getParameter("searchType");
			if(tmpsearchType!=null&&!tmpsearchType.equals(""))
				searchType = tmpsearchType;
			//��������ͼ��ԭ��
			String tempsip = request.getParameter("imagefilepaths");
			if(tempsip!=null&&!tempsip.equals(""))
				searchImagePath = tempsip;
			
			//��ʼ����
			if(searchType.equals("1"))	//ʵ������
			{
				System.out.println("===================ʵ������=======================");
				searchTypeC = "ʵ������";
				if(searchImagePath.equals(""))
				{
					request.setAttribute("fail", "ʵ������:����ѡ������ͼƬ������");
					
					RequestDispatcher disp = request.getRequestDispatcher(path);
						disp.forward(request, response);
					return;
				}
				else
				{//�������ϣ���ʼ����
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
			else if(searchType.equals("2"))	//�������
			{
				System.out.println("===================�������=======================");
				searchTypeC = "�������";
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
					request.setAttribute("fail", "���������������������Ի���������������");
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
			else if(searchType.equals("3"))	//��������
			{
				System.out.println("===================��������=======================");
				searchTypeC = "��������";
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
					request.setAttribute("fail", "�������������������ͼƬ!!!");
					RequestDispatcher disp = request.getRequestDispatcher(path);
					disp.forward(request, response);
					return;
				}
				else if(!bool)
				{
					request.setAttribute("fail", "����������������������Ի���������!!!");
					RequestDispatcher disp = request.getRequestDispatcher(path);
					disp.forward(request, response);
					return;
				}
				else if(!searchImagePath.equals("")&&bool)
				{
					//�������ϣ����Լ���

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
			{//�ı�����
				System.out.println("===================�ı���������=======================");
				searchTypeC = "�ı���������";
				n = 1;
				m = 1;
			}else if(rs.equals("audio"))
			{//�ı�����
				System.out.println("===================��Ƶ��������=======================");
				searchTypeC = "��Ƶ��������";
				n = 5;
				m = 1;
			}else if(rs.equals("video"))
			{//�ı�����
				System.out.println("===================��Ƶ��������=======================");
				searchTypeC = "��Ƶ��������";
				n = 4;
				m = 1;
			}
			
			if(n!=0&&m==1)
			{
				String[] tmp2 = {"avit_rs_cb_0_tx","avit_rs_cb_1_tx","avit_rs_cb_2_tx","avit_rs_cb_3_tx","avit_rs_cb_4_tx"};
				String[] tmp3 = {"avit_rs_cb_0_sl","avit_rs_cb_1_sl","avit_rs_cb_2_sl","avit_rs_cb_3_sl","avit_rs_cb_4_sl"};
					
				String d=request.getParameter("avitType");// ��Ƶ����
				if(d!=null&&!d.equals(""))
					imageType=d.split(",")[0];
				
				//��֯bf 
				String[] parabf = img.bfe;
				for (int i = 0; i < parabf.length; i++) {
					bf.add("nothing");
				}
				
				//�ı���������ֵ
				String[] tmp20 = new String[n];
				for (int i = 0; i < tmp20.length; i++) {
					String t0 = request.getParameter(tmp2[i]);
					if(t0==null||t0.equals(""))
						tmp20[i] = "nothing";
					else
						tmp20[i] = t0;
				}
				//����ֵ��Ӧͼ���sf����
				//����ֵ��Ӧͼ���sf��
				String[] tmp30 = new String[n];
				for (int i = 0; i < tmp30.length; i++) {
					tmp30[i] = request.getParameter(tmp3[i]);
				}
				
				//��ȡ�û�ѡ�е���
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
				
				//�ж��û�ѡ����ı�����---�ж��û�ѡ���ͼ�����
				
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
				
				//���е�sf��
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
					request.setAttribute("fail", searchTypeC+"����������");
					RequestDispatcher disp = request.getRequestDispatcher(path);
					disp.forward(request, response);
					return;
				}
				
				
			}
			else
			{
				request.setAttribute("fail", "�����ļ�������������������");
				RequestDispatcher disp = request.getRequestDispatcher(path);
				disp.forward(request, response);
				return;
			}
		}
		
		request.getSession().setAttribute("searchTypeC", searchTypeC);
		
		request.getSession().setAttribute("lireSearchType", img.getLireSearchType(type));
		request.getSession().setAttribute("other", img.getOther(bf.toArray(new String[0]), sf.toArray(new String[0])));
		
		//�������
		if(result==null||result.getLireID().length==1)
		{
			request.setAttribute("fail", "�������Ϊ��");
		}
		else if(result.getLireID().length==1&&result.getLireID()[0].equals("bf is null"))
		{
			request.setAttribute("fail", "�������Բ�ƥ��");
		}
		else if(result.getLireID().length==1&&result.getLireID()[0].equals("sf is null"))
        {
			request.setAttribute("fail", "����������ƥ��");
        }
		else  if(result.getLireID().length==1&&result.getLireID()[0].equals("noresult"))
        {
			request.setAttribute("fail", "û�з���������ͼ��");
        }
		else
		{  
			  //�õ�ԭʼͼ���·��
			  String[] fullFilespath= userquery.getFullFiles();
			  if(request.getSession().getAttribute("fullfilespath")!=null)
				   request.getSession().removeAttribute("fullfilespath");
			  request.getSession().setAttribute("fullfilespath", fullFilespath);
			
			request.getSession().setAttribute("searchImagePath", searchImagePath);
			
			request.getSession().setAttribute("result", result);
			
			//��ҳ
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
		
		//����9���ط����ñ�����
		
	response.setContentType("text/html; charset=utf-8");
	PrintWriter out = response.getWriter();
	out
			.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
	out.println("<HTML>");
	out.println("  <HEAD><TITLE>A Error</TITLE></HEAD>");
	out.println("  <BODY>");
	out.println("  ���󣬴������ͣ�");
	out.print("    <font color=red>"+fail+"</font>�����¼�������  ");
	out.print("	<a href='./SearchImage/mainimage.jsp'>ͼ�����</a>");
	out.println(" лл������");
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
