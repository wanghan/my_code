package servlets;

import hibernate.DbPaper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.rmi.SearchRMIClient;

public class PaperSearchServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PaperSearchServlet() {
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

		int start = (ps - 1) * this.pageSize; // 开始页
		// int end = ps*this.pageSize;

		long s = System.currentTimeMillis();

		List<DbPaper> result = (List<DbPaper>) request.getSession()
				.getAttribute("result");
		List<DbPaper> list = this.TISList(start, result);
		long e = System.currentTimeMillis();
		request.setAttribute("s", s);// begin time
		request.setAttribute("e", e);// end time

		// 显示内容实体
		request.getSession().removeAttribute("list");
		request.getSession().setAttribute("list", list);
		// 当前页
		request.getSession().setAttribute("nowpage", ps);
		// ==================================================

		RequestDispatcher disp = request
				.getRequestDispatcher("/paperResults.jsp");
		disp.include(request, response);
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
		response.setCharacterEncoding("utf-8");

		String searchText = "";
		searchText=request.getParameter("searchText");
		System.out.println("searching keywords "+searchText);
		request.getSession().setAttribute("searchText", searchText); 
		
		// 声明检索开始时间
		long s = System.currentTimeMillis();

		List<DbPaper> result=null;
		
		try {
			result=SearchRMIClient.getInstance().searchPapers(searchText);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("RMI error，please check the search service");
			Err(request, response);
			return ;
		}

		// 返回页面结果参数

		if (result != null && result.size() != 0) {
			System.out.println("result number：" + result.size());
		} else if (result != null && result.size() == 0) {
			System.out.println("result is null");
		} else {
			System.out.println("result error!");
			Err(request, response);
			return ;
		}

		// 声明检索结束时间
		long e = System.currentTimeMillis();

		ArrayList<DbPaper> list = new ArrayList<DbPaper>();
		if (result != null)
			list = this.TISList(0, result);		
		
		request.getSession().setAttribute("result", result); 

		request.setAttribute("s", s);// begin time
		request.setAttribute("e", e);// end time


		request.getSession().setAttribute("totPage",
				(result.size() - 1) / pageSize + 1); // total page

		// current page
		request.setAttribute("nowpage", 1);
		request.getSession().removeAttribute("list");
		request.getSession().setAttribute("list", list);
		
		RequestDispatcher disp = request
				.getRequestDispatcher("/paperResults.jsp");
		disp.include(request, response);

	}

	int pageSize = 10;

	/**
	 * split the page
	 * start: start index
	 * */
	public ArrayList<DbPaper> TISList(int start, List<DbPaper> results) {
		
		ArrayList<DbPaper> list = new ArrayList<DbPaper>();

		if (start < 0) {
			start = 0;
		}

		if (results != null && results.size() != 0 && start >= 0) {
			// int c=(a<b)?a:b;
			int num = (results.size() < this.pageSize) ? results.size() : this.pageSize;
			ArrayList<Integer> keys = new ArrayList<Integer>();
			
			if (start < results.size() - this.pageSize) {
				for (int i = start; i < start + num; i++) {
					// System.out.print(i+"\t");
					keys.add(results.get(i).getId());
				}
			} else {
				for (int i = (results.size() - 1) / this.pageSize * this.pageSize; i < results.size(); i++) {
					// System.out.print(i+"\t");
					keys.add(results.get(i).getId());
				}
			}

			System.out.println(start / 10 + 1 + "keys:" + keys);

		
			System.out.println("=====================================");
			
			// the following varible are using results.id as key
			// results list

			if (results.size() < num) {
				num = results.size();
			}

			int end = start + num;
			if (end > results.size())
				end = results.size();

			for (int i = start; i < end; i++) {
				list.add(results.get(i));
			}
			return list;
		}
		return null;
	}

	public void Err(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<DbPaper> list = new ArrayList<DbPaper>();
		RequestDispatcher disp = request
		.getRequestDispatcher("/paperResults.jsp");
disp.include(request, response);
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

}
