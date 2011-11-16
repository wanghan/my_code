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
		
		
		//�ļ���Ϣ
		String[] addnames = {"cf_f_txtv","cf_t_txtv","cf_m_txtv","cf_n_txtv","cf_u_txtv"};
		//�ļ��������ļ����ؼ�֡����ͷ��Ϣ�ļ����ύ��
		String[] addvals  = new String[addnames.length];
		
		//��ȡ�û��ύ��Ϣ
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
		//ԭʼ�ļ�
		File audr_file = new File(userPath+addvals[0]);
		//�����ļ�
		File semantics_file = new File(userPath+addvals[1]);
		//�ؼ�֡
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
		
		//��ͷ��Ϣ�ļ�
		File lens_file = new File(userPath+addvals[3]);
		
		//�ļ�������Ϣ
		
		
		if(!audr_file.exists())
		{
			sb.append("�����ļ������ڣ��������ϴ�");
			System.out.println("�����ļ������ڣ��������ϴ�");
		}
		else if(cfile_type==null||cfile_type.equals("")
				||cfile_subtype==null||cfile_subtype.equals(""))
		{
			sb.append("��������Ϊ��");
			System.out.println("��������Ϊ��");
		}
		else
		{
			//System.out.println(audr_file.getAbsolutePath());
			int n = ft.ContainsString(cfile_type, ft.filetye);
			int m = ft.ContainsString(cfile_subtype, ft.fte[n]);
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd��");
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
			//��ѯ��ʼ==========================================
			if(n==0)		//ͼ��
			{
				if(!semantics_file.exists())
				{
					sb.append("���������ļ������ڣ�������");
					System.out.println("���������ļ������ڣ�������");
				}
				else if(addvals[4].equals("nothing"))
				{
					sb.append("��������ȷʹ�ñ�ϵͳ");
					System.out.println("��������ȷʹ�ñ�ϵͳ");
				}
				else
				{
					//��ȷ���������
					
				}
			}
			else if(n==1)	//�ı�
			{
				//��ȷ���������
	
				
				//����bf
				String[] bf = {date+ ";" +addvals[4]+ ";" +audr_file.getAbsolutePath()+ ";" +audr_file.getAbsolutePath()+ ";" +sizek};
				String[] urls = {audr_file.getAbsolutePath()};
				String[] sf = {};
				//String msg = ut.receiveInsertInfo(urls, bf, semanticfeaturepath, lfFilePath, textType);
				sb.append("��ӳɹ�");
			}
			
			else
			{
				sb.append("���������Ͳ����ڣ�������");
				System.out.println("���������Ͳ����ڣ�������");
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

