package AUDRwebServlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import sys.RebuildQueryResult;
import AUDRcopyCS.ParseXml;
import AUDRwebJavaBeans.ImageBFObj;
import AUDRwebJavaBeans.ImageJavaBean;
import AUDRwebJavaBeans.ImageQueryInfo;
import AUDRwebJavaBeans.ImageSemanticFeature;
import Common.SYSPROPERTIES;
import AudrConsole.UserQueryTransporter;

public class SearchImageInfoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SearchImageInfoServlet() {
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
		
		request.setCharacterEncoding("utf-8");
		String path="/SearchImage/searchimageinfo.jsp";
		
		RebuildQueryResult result = (RebuildQueryResult) request.getSession().getAttribute("result");
		if(result==null)
			path = "/SearchImage/mainimage.jsp";
		else
		{
			String keyString=request.getParameter("key");//当前放大的图像对应的下标
			int key = 0; //每幅图像以在链表中的下标为对应的KEY
			try {
				key = Integer.parseInt(keyString);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(key<0)
				key = 0;
			else if(key>=result.getImage().length)
				key = result.getImage().length-1;
			
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
					 
//					 System.out.println("bf:"+bf);
					 
					 //删除临时文件
					 File tmpbf = new File(filenameBF);
					 File tmpsf = new File(filenameSF);
					 if(tmpbf.exists())
						 tmpbf.delete();
					 if(tmpsf.exists())
						 tmpsf.delete();
//				   if(request.getSession().getAttribute("imageQueryInfo")!=null)
//					   request.getSession().removeAttribute("imageQueryInfo");
//				   request.getSession().setAttribute("imageQueryInfo", imagequery);//把基本信息和语义信息存储
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
			
			//File tempFile = new File(bigPics[key]);
			//java.net.URLEncoder.encode(proInfoName,"GB2312");tempFile.getName()
			//System.out.println(tempFile.getParent().split("WebRoot")[1].replaceAll("\\\\","/")+"/"+java.net.URLEncoder.encode(tempFile.getName(),"utf-8"));
			request.setAttribute("id",result.getLireID()[key]);//for deic use
			request.setAttribute("bf", imagequery.getImageBF(key));
			request.setAttribute("sf", imagequery.getImageSF(key));
			request.setAttribute("nowPic", key);
			System.out.println(result.getLireID()[key]);
			request.setAttribute("bigPic", bigPics[key].split("WebRoot")[1].replaceAll("\\\\","/"));
			request.setAttribute("miniPics", miniPics);
			
			
		}
		
		
		//得到对应放大图像的基本属性和语义信息
		request.getRequestDispatcher(path).forward(request, response);
		return ;
		
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

		int number=ImageQueryInfo.NUMthumb;//每一组有5张
		request.setCharacterEncoding("gb2312");
		String path="/SearchImage/searchimageinfo.jsp";
	  
	  ImageQueryInfo imagequery=(ImageQueryInfo)request.getSession().getAttribute("imageQueryInfo");
	  
	   String keyString=request.getParameter("key");//当前放大的图像对应的下标
	   int key; //每幅图像以在链表中的下标为对应的KEY
	   if(keyString==null)
		   key=0;
	   else
		   key=Integer.valueOf(keyString);
	   String fail=null;
	   //还要根据FORM的不同名字来判断KEY的不同 preimage nextimge preThumbImage nextThumbImage
	   if(request.getParameter("urlkey")!=null)
	   {
		   String urlkey=request.getParameter("urlkey");
		   if(urlkey.equals("nextimage"))
		   {
			  if(key<imagequery.getThumbFile().length-1) 
		         key=key+1;
			  else
			  {
				  fail="错误！已经是最后一张检索出的图像!";
				  request.setAttribute("fail", fail);
			  }
		   }
		   else if(urlkey.equals("preimage"))
		   {
			   if(key>0)
			     key=key-1;
			   else
			   {
				   fail="错误!第一张检索出的图像";
				   request.setAttribute("fail", fail);
			   }
		   }
		   else if(urlkey.equals("nextThumbImage"))
		   {
			   
			    if((key+number)<imagequery.getThumbFile().length)
			    {
			    	  key=key+number;
			    }
			    if((key+number)==imagequery.getThumbFile().length)
			    {
			    	 fail="错误!已经是最后一组检索出的图像!";
					  request.setAttribute("fail", fail);
			    	
			    }
			    else if((key+number)>imagequery.getThumbFile().length)
			    {
				  if((imagequery.getThumbFile().length-number)>0)
					  key=imagequery.getThumbFile().length-number;
				  else
				  {
					  fail="错误!已经是最后一组检索出的图像!";
					  request.setAttribute("fail", fail);
				  }
				  
			    }
			   
		   }
		   else if(urlkey.equals("preThumbImage"))
		   {
			    if(key==0)
			    {
			    	 fail="错误!已经是第一张检索出的图像!";
					  request.setAttribute("fail", fail);
			    }
			    else if((key-number)<0)
			    	key=0;
			    else 
			    	 key=key-number;
		   }
	   }
	   //设置当前放大的索引
	   if(keyString==null)
		   System.out.println("=========================cuowu");
	   imagequery.setIndex(String.valueOf(key));
	   request.getSession().setAttribute("key", String.valueOf(key)); //设置要放大的图像对应的下标
	   
	  System.out.println(keyString);
	 //先在已有的对象里查询是否存在,这里得到基本属性和语义特征
		 ImageBFObj bf=imagequery.getImageBF(key);
		 ImageSemanticFeature sf=imagequery.getImageSF(key);
		
		//先查询是否已经存在,如果不存在,则查询
		if(bf==null||sf==null)
		{
			 UserQueryTransporter uqTransporter1=new UserQueryTransporter();
			 String []ID=new String[2];
			 ID[0]="query";
			 ID[1]=imagequery.getLireid(key);//得到要放大图象的LIREid
				 
			 
			///	file path:C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps\AUDRweb\
				ServletContext   aa=request.getSession().getServletContext();   
				  String   getfilepath  =   aa.getRealPath("/");
				String filenameBF=getfilepath+"bf.xml";
				String filenameSF=getfilepath+"sf.xml";
			 RebuildQueryResult  results= uqTransporter1.QuerybylireID(ID,getfilepath);
//			 results.getBf() 通过左边得到的是把XML文件以字符串的形式传递过来的,要解析
			
				
			 if(results!=null)
			 {
				 //得到基本属性和语义特征
				 try{
					 ParseXml xml=new ParseXml();
				 bf =  xml.setBF(results, filenameBF).get(0);
				 sf=xml.setSF(results, filenameSF).get(0);
				 
				   imagequery.setImageBF(key, bf);
				   imagequery.setImageSF(key, sf);
				   if(request.getSession().getAttribute("imageQueryInfo")!=null)
					   request.getSession().removeAttribute("imageQueryInfo");
				   request.getSession().setAttribute("imageQueryInfo", imagequery);//把基本信息和语义信息存储
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
			 //删除临时文件
			 File file=new File(filenameBF);
			 if(file.exists())
				 file.delete();
			 File file1=new File(filenameSF);
			 if(file1.exists())
				 file1.delete();
			 //删除图象检索时存储图象
	
		
		}
		if(request.getSession().getAttribute("ImageJB")!=null)
		{
			String tempfp=((ImageJavaBean)request.getSession().getAttribute("ImageJB")).getTempfilepath();//对应地址
			if(tempfp!=null)
			{
				 File f=new File(tempfp);
				 if(f.exists())
					 f.delete();
			}
			request.getSession().removeAttribute("ImageJB");
		}
			
		
		//得到对应放大图像的基本属性和语义信息
		request.getRequestDispatcher(path).forward(request, response);
		return ;
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
