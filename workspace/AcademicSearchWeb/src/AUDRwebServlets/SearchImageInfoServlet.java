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
			String keyString=request.getParameter("key");//��ǰ�Ŵ��ͼ���Ӧ���±�
			int key = 0; //ÿ��ͼ�����������е��±�Ϊ��Ӧ��KEY
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
			
			//ԭͼ
			String[] bigPics = (String[]) request.getSession().getAttribute("fullfilespath");
						
			//bf
			ImageBFObj bf = new ImageBFObj();
			ImageSemanticFeature sf = new ImageSemanticFeature();
			
			UserQueryTransporter uqTransporter1=new UserQueryTransporter();
			String []ID=new String[2];
			ID[0]="query";
			ID[1]=result.getLireID()[key];//�õ�Ҫ�Ŵ�ͼ���LIREid
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
				 //�õ��������Ժ���������
				 try{
					 ParseXml xml=new ParseXml();
					 bf =  xml.setBF(rs, filenameBF).get(0);
					 sf =  xml.setSF(rs, filenameSF).get(0);
					 imagequery.setImageBF(key, bf);
					 imagequery.setImageSF(key, sf);
					 
//					 System.out.println("bf:"+bf);
					 
					 //ɾ����ʱ�ļ�
					 File tmpbf = new File(filenameBF);
					 File tmpsf = new File(filenameSF);
					 if(tmpbf.exists())
						 tmpbf.delete();
					 if(tmpsf.exists())
						 tmpsf.delete();
//				   if(request.getSession().getAttribute("imageQueryInfo")!=null)
//					   request.getSession().removeAttribute("imageQueryInfo");
//				   request.getSession().setAttribute("imageQueryInfo", imagequery);//�ѻ�����Ϣ��������Ϣ�洢
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
		
		
		//�õ���Ӧ�Ŵ�ͼ��Ļ������Ժ�������Ϣ
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

		int number=ImageQueryInfo.NUMthumb;//ÿһ����5��
		request.setCharacterEncoding("gb2312");
		String path="/SearchImage/searchimageinfo.jsp";
	  
	  ImageQueryInfo imagequery=(ImageQueryInfo)request.getSession().getAttribute("imageQueryInfo");
	  
	   String keyString=request.getParameter("key");//��ǰ�Ŵ��ͼ���Ӧ���±�
	   int key; //ÿ��ͼ�����������е��±�Ϊ��Ӧ��KEY
	   if(keyString==null)
		   key=0;
	   else
		   key=Integer.valueOf(keyString);
	   String fail=null;
	   //��Ҫ����FORM�Ĳ�ͬ�������ж�KEY�Ĳ�ͬ preimage nextimge preThumbImage nextThumbImage
	   if(request.getParameter("urlkey")!=null)
	   {
		   String urlkey=request.getParameter("urlkey");
		   if(urlkey.equals("nextimage"))
		   {
			  if(key<imagequery.getThumbFile().length-1) 
		         key=key+1;
			  else
			  {
				  fail="�����Ѿ������һ�ż�������ͼ��!";
				  request.setAttribute("fail", fail);
			  }
		   }
		   else if(urlkey.equals("preimage"))
		   {
			   if(key>0)
			     key=key-1;
			   else
			   {
				   fail="����!��һ�ż�������ͼ��";
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
			    	 fail="����!�Ѿ������һ���������ͼ��!";
					  request.setAttribute("fail", fail);
			    	
			    }
			    else if((key+number)>imagequery.getThumbFile().length)
			    {
				  if((imagequery.getThumbFile().length-number)>0)
					  key=imagequery.getThumbFile().length-number;
				  else
				  {
					  fail="����!�Ѿ������һ���������ͼ��!";
					  request.setAttribute("fail", fail);
				  }
				  
			    }
			   
		   }
		   else if(urlkey.equals("preThumbImage"))
		   {
			    if(key==0)
			    {
			    	 fail="����!�Ѿ��ǵ�һ�ż�������ͼ��!";
					  request.setAttribute("fail", fail);
			    }
			    else if((key-number)<0)
			    	key=0;
			    else 
			    	 key=key-number;
		   }
	   }
	   //���õ�ǰ�Ŵ������
	   if(keyString==null)
		   System.out.println("=========================cuowu");
	   imagequery.setIndex(String.valueOf(key));
	   request.getSession().setAttribute("key", String.valueOf(key)); //����Ҫ�Ŵ��ͼ���Ӧ���±�
	   
	  System.out.println(keyString);
	 //�������еĶ������ѯ�Ƿ����,����õ��������Ժ���������
		 ImageBFObj bf=imagequery.getImageBF(key);
		 ImageSemanticFeature sf=imagequery.getImageSF(key);
		
		//�Ȳ�ѯ�Ƿ��Ѿ�����,���������,���ѯ
		if(bf==null||sf==null)
		{
			 UserQueryTransporter uqTransporter1=new UserQueryTransporter();
			 String []ID=new String[2];
			 ID[0]="query";
			 ID[1]=imagequery.getLireid(key);//�õ�Ҫ�Ŵ�ͼ���LIREid
				 
			 
			///	file path:C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps\AUDRweb\
				ServletContext   aa=request.getSession().getServletContext();   
				  String   getfilepath  =   aa.getRealPath("/");
				String filenameBF=getfilepath+"bf.xml";
				String filenameSF=getfilepath+"sf.xml";
			 RebuildQueryResult  results= uqTransporter1.QuerybylireID(ID,getfilepath);
//			 results.getBf() ͨ����ߵõ����ǰ�XML�ļ����ַ�������ʽ���ݹ�����,Ҫ����
			
				
			 if(results!=null)
			 {
				 //�õ��������Ժ���������
				 try{
					 ParseXml xml=new ParseXml();
				 bf =  xml.setBF(results, filenameBF).get(0);
				 sf=xml.setSF(results, filenameSF).get(0);
				 
				   imagequery.setImageBF(key, bf);
				   imagequery.setImageSF(key, sf);
				   if(request.getSession().getAttribute("imageQueryInfo")!=null)
					   request.getSession().removeAttribute("imageQueryInfo");
				   request.getSession().setAttribute("imageQueryInfo", imagequery);//�ѻ�����Ϣ��������Ϣ�洢
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
			 //ɾ����ʱ�ļ�
			 File file=new File(filenameBF);
			 if(file.exists())
				 file.delete();
			 File file1=new File(filenameSF);
			 if(file1.exists())
				 file1.delete();
			 //ɾ��ͼ�����ʱ�洢ͼ��
	
		
		}
		if(request.getSession().getAttribute("ImageJB")!=null)
		{
			String tempfp=((ImageJavaBean)request.getSession().getAttribute("ImageJB")).getTempfilepath();//��Ӧ��ַ
			if(tempfp!=null)
			{
				 File f=new File(tempfp);
				 if(f.exists())
					 f.delete();
			}
			request.getSession().removeAttribute("ImageJB");
		}
			
		
		//�õ���Ӧ�Ŵ�ͼ��Ļ������Ժ�������Ϣ
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
