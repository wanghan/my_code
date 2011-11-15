package AUDRwebServlets;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sys.RebuildQueryResult;

import AudrConsole.UserClusterTransporter;

import AUDRwebJavaBeans.QueryBy;

public class ClusterServlet extends HttpServlet{

	QueryBy queryByexample = new QueryBy();
	
	public ClusterServlet() {
		// TODO Auto-generated constructor stub
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
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		String success = "/SearchImage/clusterShowImage.jsp";
		String fail    = "/SearchImage/showimageMinipage.jsp";
		String path = fail;
		
		//聚类方式
		String clusterType = request.getParameter("clusteringType");
		int clusterTypeInt = 1;
		if(clusterType.equals("s"))
			clusterTypeInt = 2;

		String[] ctich = {"查询图像的底层聚类","查询图像的语义聚类"};
		
		System.out.println("聚类方式："+clusterTypeInt);
		
		//获取聚类的链路id
		RebuildQueryResult result = (RebuildQueryResult) request.getSession().getAttribute("result");
		if(result!=null)
		{
			String[] lireId = result.getLireID();
			System.out.println("链路id数："+lireId.length);
			
			UserClusterTransporter uct = new UserClusterTransporter();
			HashMap<String,String[]> map = (HashMap<String, String[]>) uct.clustering(clusterTypeInt,lireId);
			HashMap<String,String[]> newmap = new HashMap<String,String[]>();
			System.out.println("所有类型数："+map.size());
			Iterator iter = map.entrySet().iterator();
			while(iter.hasNext()){
				Map.Entry entry = (Map.Entry)iter.next();
				String key=(String)entry.getKey();
				//这里做判断
//				System.out.println("=================================");
//				System.out.print(key+":");
				String[] tmp = (String[])entry.getValue();
				String[] temp = new String[tmp.length];
				for (int i = 0; i < temp.length; i++) {
					String ss[] = result.getLireID();
					//System.out.println(ss.length+"###################");
					for (int j = 0; j < ss.length; j++) {
						//System.out.println(tmp[i]+":"+ss[j]+":"+result.getImage()[j].getAbsolutePath()+"\t");
						if(tmp[i].equals(ss[j]))
						{
							temp[i] = result.getImage()[j].getAbsolutePath().split("WebRoot")[1].replaceAll("\\\\","/");
							//System.out.println(result.getImage()[j].getAbsolutePath()+"\t");
						}
					}
				}
				newmap.put(key, temp);
				//System.out.println();
			}
			
			request.setAttribute("clusterType", ctich[clusterTypeInt-1]);
			request.setAttribute("map",newmap);
			path = success;
			//System.out.println(map);
		}
		
		RequestDispatcher disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
		
		
	}
}
