package servlets;

import hibernate.DbAuthor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.rmi.SearchRMIClient;

public class AuthorSearchServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuthorSearchServlet() {
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
		String n = request.getParameter("author_id");
		try {
			ps = Integer.parseInt(n);
		} catch (Exception e) {
			// TODO: handle exception
		}

		int start = (ps - 1) * this.pageSize; // 开始页
		// int end = ps*this.pageSize;

		long s = System.currentTimeMillis();

		List<DbAuthor> result = (List<DbAuthor>) request.getSession()
				.getAttribute("author_result");
		List<DbAuthor> list = this.TISListForAuthor(start, result);
		long e = System.currentTimeMillis();
		request.setAttribute("author_s", s);// begin time
		request.setAttribute("author_e", e);// end time

		// 显示内容实体
		request.getSession().removeAttribute("author_list");
		request.getSession().setAttribute("author_list", list);
		// 当前页
		request.getSession().setAttribute("author_nowpage", ps);
		// ==================================================

		RequestDispatcher disp = request
				.getRequestDispatcher("/authorResults.jsp");
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
		
		//search authors
		
		// 声明检索开始时间
		long author_s = System.currentTimeMillis();

		List<DbAuthor> author_result=null;
		
		try {
			author_result=SearchRMIClient.getInstance().searchAuthors(searchText);

		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("RMI error，please check the search service");
			Err(request, response);
			return ;
		}

		// 返回页面结果参数

		if (author_result != null && author_result.size() != 0) {
			System.out.println("result number：" + author_result.size());
		} else if (author_result != null && author_result.size() == 0) {
			System.out.println("result is null");
		} else {
			System.out.println("result error!");
			Err(request, response);
			return ;
		}

		// 声明检索结束时间
		long author_e = System.currentTimeMillis();

		
		ArrayList<DbAuthor> list = new ArrayList<DbAuthor>();
		if (author_result != null)
			list = this.TISListForAuthor(0, author_result);		
		
		request.getSession().setAttribute("author_result", author_result); 

		request.setAttribute("author_s", author_s);// begin time
		request.setAttribute("author_e", author_e);// end time

		request.getSession().setAttribute("searchText", searchText); 
		request.getSession().setAttribute("author_totPage",
				(author_result.size() - 1) / pageSize + 1); // total page

		// ==================================================
		request.getSession().removeAttribute("list");

		// current page
		request.getSession().setAttribute("author_nowpage", 1);
		request.getSession().removeAttribute("author_list");
		request.getSession().setAttribute("author_list", list);
		
		RequestDispatcher disp = request
				.getRequestDispatcher("/authorResults.jsp");
		disp.include(request, response);

	}

	int pageSize = 10;

	
	/**
	 * split the page
	 * start: start index
	 * */
	public ArrayList<DbAuthor> TISListForAuthor(int start, List<DbAuthor> results) {
		
		ArrayList<DbAuthor> list = new ArrayList<DbAuthor>();

		if (start < 0) {
			start = 0;
		}

		if (results != null && results.size() != 0 && start >= 0) {
			// int c=(a<b)?a:b;
			int num = (results.size() < this.pageSize) ? results.size() : this.pageSize;
			ArrayList<Long> keys = new ArrayList<Long>();
			
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

		ArrayList<DbAuthor> list = new ArrayList<DbAuthor>();
		RequestDispatcher disp = request
		.getRequestDispatcher("/authorResults.jsp");
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
