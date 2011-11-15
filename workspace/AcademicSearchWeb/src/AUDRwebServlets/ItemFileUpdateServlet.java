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
		//��������url
		//String url = "AudrConsole/ConsoleMain.jsp";
		//��������
		String fail = "";
		
		String type = request.getParameter("cfile_type");
		String subtype = request.getParameter("cfile_subtype");
		String id = request.getParameter("id");
		
		System.out.println("///type:"+type);
		System.out.println("///subtype:"+subtype);
		System.out.println("///id:"+id);
		
		if(type==null||type.equals(""))
		{
			System.out.println("�����ļ�����Ϊ��");
			fail = "�����ļ�����Ϊ��";
			sb.append("�����ļ�����Ϊ��");
		}
		else if(ft.ContainsString(type, ft.filetye)<0||ft.ContainsString(type, ft.filetye)>=ft.filetye.length)
		{
			System.out.println("�����ļ����Ͳ�����");
			fail = "�����ļ����Ͳ�����";
			sb.append("�����ļ����Ͳ�����");
		}
		else
		{
			//nΪ���������������������λ��
			int n = ft.ContainsString(type, ft.filetye);
			
			System.out.println("===type:"+type);
			System.out.println("===ft.filetye[0]:"+ft.filetye[n]);
			System.out.println("===n:"+n);
			
			if(id==null||id.equals(""))
			{
				System.out.println("����idΪ��");
				fail = "����idΪ��";
				sb.append("����idΪ��");
			}
			else
			{
				if(subtype==null||subtype.equals(""))
				{
					System.out.println("�����ļ�������Ϊ��");
					fail = "�����ļ�������Ϊ��";
					sb.append("�����ļ�������Ϊ��");
				}
				else if(ft.ContainsString(subtype, ft.fte[n])<0||ft.ContainsString(subtype, ft.fte[n])>=ft.fte[n].length)
				{
					System.out.println("�����ļ������Ͳ�����");
					fail = "�����ļ������Ͳ�����";
					sb.append("�����ļ������Ͳ�����");
				}
				else
				{
					//mΪ���������������������λ��
					int m = ft.ContainsString(subtype, ft.fte[n]);
					
					System.out.println("===****subtype:"+subtype);
					System.out.println("===****ft.fte[n][0]:"+ft.fte[n][m]);
					System.out.println("===****m:"+m);
					
					///����·��
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
//					//����url����
//					url+="?";
//					for (int i = 0; i < cmv.length-1; i++) {
//						url+=cmid[i]+"="+cmv[i];
//						if(i!=cmv.length-2)
//							url+="&";
//					}	
					
					ServletContext   aa=request.getSession().getServletContext();  
					String   userPath =   aa.getRealPath("\\");
					//////////////////////////////////////////////////
					//�޸Ŀ�ʼ==========================================
					//��ȡ�����ύ�Ĳ�����keyֵ��valueֵ
					String[] bfk = ft.bfe[n];
					String[] bfv = new String[bfk.length];
					String[] sfk = ft.sfe[n];
					String[] sfv = new String[sfk.length];
					
					//���ݲ���
					HashMap map = new HashMap();
					//����ֵ
					String rs = "";
					//��¼�Ƿ��޸ģ��Լ��޸�������Ŀ
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
					
					if(type.equals(ft.filetye[0]))		//ͼ��
					{
						String imagefilepath = request.getParameter("imagefilepath");
						if(imagefilepath==null)
							imagefilepath = "";
						File imageFile = new File(userPath+imagefilepath);
						
						//��Ϣ׼����ɣ��޸Ŀ�ʼ
						UserUpdateImageTransporter uit = new UserUpdateImageTransporter();
						if(x==0)
						{//������Ϣδ�޸�
							if(imageFile.exists())
							{
								rs = uit.updateImage(id, imageFile.getAbsolutePath(), null);
								sb.append(rs);
//								if(rs.equals(""))
//									System.out.println("�޸�ʧ��");
//								else
//									System.out.println("�޸ĳɹ�");
							}
							else
							{
								System.out.println("�����ϴ�ͼƬ�����������ϴ�");
								fail = "�����ϴ�ͼƬ�����������ϴ�";
								sb.append("�����ϴ�ͼƬ�����������ϴ�");
							}
						}
						else
						{
							if(imagefilepath.equals(""))
							{
								rs = uit.updateImage(id, "", map);
								sb.append(rs);
//								if(rs.equals(""))
//									System.out.println("�޸�ʧ��");
//								else
//									System.out.println("�޸ĳɹ�");
							}
							else
							{
								System.out.println("���󣺲���ͬʱ�޸��ļ���Ϣ��������Ϣ");
								fail = "���󣺲���ͬʱ�޸��ļ���Ϣ��������Ϣ";
								sb.append("���󣺲���ͬʱ�޸��ļ���Ϣ��������Ϣ");
							}
						}
						
//						url += "&"+cmid[4]+"="+ft.action[12];
						
					}
					else if(type.equals(ft.filetye[1]))	//�ı�
					{
						//�ı��ļ�
						String textfilepath = request.getParameter("textfilepath");
						if(textfilepath==null)
							textfilepath = "";
						File textFile = new File(userPath+textfilepath);
						//�ײ��ļ�
						String txtlfFilePath = request.getParameter("txtlfFilePath");
						if(txtlfFilePath==null)
							txtlfFilePath = "";
						File txtlfFile = new File(userPath+txtlfFilePath);
						
						//��Ϣ׼����ɣ��޸Ŀ�ʼ
						UserUpdateTextTransporter utt = new UserUpdateTextTransporter();
						if(x==0)
						{//������Ϣδ�޸�
							if(textFile.exists())
							{
								rs = utt.updateText(id,txtlfFile.getAbsolutePath(), textFile.getAbsolutePath(), null);
								System.out.println("�ı��޸��ļ���"+rs);
								sb.append(rs);
//								if(rs.equals(""))
//									System.out.println("�޸�ʧ��");
//								else
//									System.out.println("�޸ĳɹ�");
							}
							else
							{
								System.out.println("�����ϴ��ı������������ϴ�");
								fail = "�����ϴ��ı������������ϴ�";
								sb.append("�����ϴ��ı������������ϴ�");
							}
						}
						else
						{
							if(textfilepath!=null&&textfilepath.equals(""))
							{
								rs = utt.updateText(id, "","", map);
								System.out.println("�ı��޸����壺"+rs);
								sb.append(rs);
//								if(rs.equals(""))
//									System.out.println("�޸�ʧ��");
//								else
//									System.out.println("�޸ĳɹ�");
							}
							else
							{
								System.out.println("���󣺲���ͬʱ�޸��ļ���Ϣ��������Ϣ");
								fail = "���󣺲���ͬʱ�޸��ļ���Ϣ��������Ϣ";
								sb.append("���󣺲���ͬʱ�޸��ļ���Ϣ��������Ϣ");
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
