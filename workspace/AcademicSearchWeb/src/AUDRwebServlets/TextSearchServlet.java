package AUDRwebServlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sys.TextQueryResult;
import AUDRwebJavaBeans.TextBasicFeature;
import AUDRwebJavaBeans.TextItem;
import AUDRwebJavaBeans.TextModel;
import AUDRwebJavaBeans.TextSemanticFeature;
import AUDRwebJavaBeans.SearchType;
import Common.SYSPROPERTIES;

public class TextSearchServlet extends HttpServlet {

	String userPath;

	/**
	 * Constructor of the object.
	 */
	public TextSearchServlet() {
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
	// public static String searchText = null;
	public static int count = 0;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int ps = 1;
		String n = request.getParameter("id");
		try {
			ps = Integer.parseInt(n);
		} catch (Exception e) {
			// TODO: handle exception
		}

		int start = (ps - 1) * this.pageSize; // ��ʼҳ
		// int end = ps*this.pageSize;

		long s = System.currentTimeMillis();

		TextQueryResult[] results = (TextQueryResult[]) request.getSession()
				.getAttribute("results");
		ArrayList<TextItem> list = this.TISList(start, results);
		long e = System.currentTimeMillis();
		request.setAttribute("s", s);// ��ʼ��ѯʱ��
		request.setAttribute("e", e);// ������ѯʱ��

		// ��ʾ����ʵ��
		request.getSession().removeAttribute("list");
		request.getSession().setAttribute("list", list);
		// ��ǰҳ
		request.setAttribute("nowpage", ps);
		// ==================================================

		RequestDispatcher disp = request
				.getRequestDispatcher("/SearchText/searchResults.jsp");
		disp.forward(request, response);
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

		SearchType tt = new SearchType();
		// ������ʾ����
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// ��ȡ�������͡��ļ����͡������ַ���
		String searchType = "";
		String searchTypeC = "";

		String fileType = "nothing";

		String searchText = "";
		System.out.println(searchType + "\t" + fileType + "\t" + searchText);

		String[] bf = new String[5];
		String[] sf = new String[16];

		String rs = request.getParameter("rs");
		if (rs == null || rs.equals("")) {
			// ��ȡ��ѯ����
			searchType = request.getParameter("searchTypeE");
			searchTypeC = request.getParameter("searchTypeC");
			// ��ȡ�ļ�����
			String tmp = request.getParameter("fileType");
			if (tmp != null || !tmp.equals("")) {
				fileType = tmp;
			}
			// ��ʼ����ѯ�ִ�
			String tmp0 = request.getParameter("searchText");
			if (tmp0 != null || !tmp0.equals("")) {
				searchText = tmp0;
			}

		} else {
			int n = 0, m = 0;
			if (rs.equals("image")) {// �ı�����
				System.out
						.println("===================�ı���������=======================");
				// searchTypeC = "�ı���������";
				n = 3;
				m = 1;
			} else if (rs.equals("audio")) {// �ı�����
				System.out
						.println("===================��Ƶ��������=======================");
				// searchTypeC = "��Ƶ��������";
				n = 5;
				m = 1;
			} else if (rs.equals("video")) {// �ı�����
				System.out
						.println("===================��Ƶ��������=======================");
				// searchTypeC = "��Ƶ��������";
				n = 4;
				m = 1;
			}

			if (n != 0 && m == 1) {
				String[] tmp2 = { "avit_rs_cb_0_tx", "avit_rs_cb_1_tx",
						"avit_rs_cb_2_tx", "avit_rs_cb_3_tx", "avit_rs_cb_4_tx" };
				String[] tmp3 = { "avit_rs_cb_0_sl", "avit_rs_cb_1_sl",
						"avit_rs_cb_2_sl", "avit_rs_cb_3_sl", "avit_rs_cb_4_sl" };

				String d = request.getParameter("avitType");// �ı���������
				if (d != null && !d.equals("")) {
					searchType = d.split(",")[0];
					searchTypeC = d.split(",")[1];
				}

				// ��֯bf
				for (int i = 0; i < bf.length; i++) {
					bf[i] = "nothing";
				}

				// ��Ƶ��������ֵ
				String[] tmp20 = new String[n];
				for (int i = 0; i < tmp20.length; i++) {
					String t0 = request.getParameter(tmp2[i]);
					if (t0 == null || t0.equals(""))
						tmp20[i] = "nothing";
					else
						tmp20[i] = t0;
				}
				// ����ֵ��Ӧ��Ƶ��sf��
				String[] tmp30 = new String[n];
				for (int i = 0; i < tmp30.length; i++) {
					tmp30[i] = request.getParameter(tmp3[i]);
				}

				// ��ȡ�û�ѡ�е���
				String[] tmp4 = request.getParameterValues("cb_rs");
				int[] tmp40;// = new int[tmp4.length];
				if (tmp4 != null || tmp4.length != 0) {
					tmp40 = new int[tmp4.length];
					for (int i = 0; i < tmp4.length; i++) {
						try {
							tmp40[i] = Integer.parseInt(tmp4[i]);
						} catch (Exception e) {
							// TODO: handle exception
							tmp40[i] = 0;
						}
					}
				} else {
					tmp40 = null;
					// tmp40[0] = 0;
				}

				// �ж��û�ѡ�����Ƶ����---�ж��û�ѡ�����Ƶ����

				String[] tmp50 = null;
				String[] tmp51 = null;
				if (tmp40 != null && tmp40.length > 0) {
					tmp50 = new String[tmp40.length];
					tmp51 = new String[tmp40.length];
					for (int i = 0; i < tmp40.length; i++) {
						tmp50[i] = tmp30[tmp40[i]];
						tmp51[i] = tmp20[tmp40[i]];
					}
				}

				// ���е�sf��
				for (int i = 0; i < sf.length; i++) {
					sf[i] = "nothing";
				}

				searchText = "";
				if (tmp51.length > 0) {
					for (String string : tmp51) {
						searchText += string + " ";
					}
				}
				searchText = searchText.trim();
			} else {
				RequestDispatcher disp = request
						.getRequestDispatcher("/common/ERROR.jsp");
				disp.forward(request, response);
			}
		}

		// ����������ʼʱ��
		long s = System.currentTimeMillis();

		// ����-->��ȡ�ļ����Ŀ¼
		// SimpleDateFormat fmt=new SimpleDateFormat("yyyyMMddhh");

		ServletContext scDir = request.getSession().getServletContext();
		userPath = scDir.getRealPath("\\");

		SYSPROPERTIES sps = new SYSPROPERTIES(new File(userPath
				+ "sys/tempFile.properties"));
		userPath += sps.getDirectoryName("text");
		File dir = new File(userPath);
		if (!dir.exists())
			dir.mkdir();

		// System.out.println(userPath);

		TextQueryResult[] results = null;
		try {
			System.out.println("results:" + searchText + "��bf��sf��" + searchType
					+ "��" + userPath);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�����ܿز�ѯ���󣬼������Ƿ��Ѿ�����");
			Err(request, response);
		}

		// ����ҳ��������

		if (results != null && results.length != 0) {
			System.out.println("�ĵ�������" + results.length);
		} else if (results != null && results.length == 0) {
			System.out.println("��ѯ���Ϊ��");
		} else {
			System.out.println("result error!");
			Err(request, response);
		}

		// ����result����
		// ȡ��BF SF
		System.out.println("results:" + results);
		if (results != null)
			System.out.println("results.length:" + results.length);

		ArrayList<TextItem> list = new ArrayList<TextItem>();
		if (results != null)
			list = this.TISList(0, results); // ������

		System.out.println("list:" + list);
		// ������������ʱ��
		long e = System.currentTimeMillis();

		request.getSession().setAttribute("results", results); // ��ѯ�����

		request.setAttribute("s", s);// ��ʼ��ѯʱ��
		request.setAttribute("e", e);// ������ѯʱ��

		request.getSession().setAttribute("searchTypeE", searchType); // ��ѯ���E
		request.getSession().setAttribute("searchTypeC", searchTypeC); // ��ѯ���C

		request.getSession().setAttribute("searchText", searchText); // ��ѯ�ַ���
		request.getSession().setAttribute("totPage",
				(results.length - 1) / pageSize + 1); // ��ҳ��

		// ==================================================
		request.getSession().removeAttribute("list");
		request.getSession().setAttribute("list", list);
		// ��ǰҳ
		request.setAttribute("nowpage", 1);

		RequestDispatcher disp = request
				.getRequestDispatcher("/SearchText/searchResults.jsp");
		disp.forward(request, response);

	}

	int pageSize = 10;

	/**
	 * ��ҳ���� ��������ʼ�� ����ֵ��list
	 * */
	public ArrayList<TextItem> TISList(int start, TextQueryResult[] trs) {
		System.out.println("��ǰ��ʼ�У�" + start + "\t" + "��������" + trs.length);
		ArrayList<TextItem> list = new ArrayList<TextItem>();

		if (start < 0) {
			start = 0;
		}

		if (trs != null && trs.length != 0 && start >= 0) {
			// int c=(a<b)?a:b;
			int num = (trs.length < this.pageSize) ? trs.length : this.pageSize;
			ArrayList<String> keys = new ArrayList<String>();
			// String[] keys=new String[num];

			HashMap<String, TextModel> tmMap = new HashMap<String, TextModel>();
			HashMap<String, TextBasicFeature> tbfMap = new HashMap<String, TextBasicFeature>();
			HashMap<String, TextSemanticFeature> tsfMap = new HashMap<String, TextSemanticFeature>();
			if (start < trs.length - this.pageSize) {
				for (int i = start; i < start + num; i++) {
					// System.out.print(i+"\t");
					keys.add(trs[i].getId());
				}
			} else {
				for (int i = (trs.length - 1) / this.pageSize * this.pageSize; i < trs.length; i++) {
					// System.out.print(i+"\t");
					keys.add(trs[i].getId());
				}
			}

			System.out.println(start / 10 + 1 + "keys:" + keys);

		
			// ���±�������ͨ��results.id ��Ϊ��ֵ��
			// results��list��textBF��textSF

			if (trs.length < num) {
				num = trs.length;
			}

			int end = start + num;
			if (end > trs.length)
				end = trs.length;

			for (int i = start; i < end; i++) {
				TextItem ttm = new TextItem();
				ttm.setTqr(trs[i]);
				ttm.setTm(tmMap.get(trs[i].getId()));
				ttm.setTbf(tbfMap.get(trs[i].getId()));
				ttm.setTsf(tsfMap.get(trs[i].getId()));
				list.add(ttm);
			}
			return list;
		}
		return null;
	}

	public void Err(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Error</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    ����������ʧ�ܣ����¼�������  ");
		out.print("	<a href='./SearchText/textSearch.jsp'>�ı�����</a>");
		out.println(" лл������");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
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

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getUserPath() {
		return userPath;
	}

	public void setUserPath(String userPath) {
		this.userPath = userPath;
	}

}
