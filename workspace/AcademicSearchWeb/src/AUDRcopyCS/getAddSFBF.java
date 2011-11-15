package AUDRcopyCS;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import sys.TextQueryResult;
import AUDRwebJavaBeans.TextBasicFeature;
import AUDRwebJavaBeans.TextModel;
import AUDRwebJavaBeans.TextSemanticFeature;
import AudrConsole.UserQueryTextTransporter;

/*
 * ��this.list this.textBF this.textSF���
 * ����Ҫ�ӵ��ĵ���ID,��ǰҳ�ĵ�һ�е��±�
 */
//������·
public class getAddSFBF {

	private  HashMap<String ,TextModel> list=new  HashMap<String ,TextModel> ();//�洢SF BF
	/*
	 * ���ĵ���Ψһ��IDΪKEY
	 */
	
	private HashMap<String ,TextBasicFeature> textBF=null;
	private HashMap<String ,TextSemanticFeature> textSF=null;
	//C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps\AUDRweb\
	private String userPath;
	public getAddSFBF(HashMap<String ,TextModel>  l,HashMap<String ,TextBasicFeature> bf,HashMap<String ,TextSemanticFeature> sf)
	{
		
		this.list=l;
		this.textBF=bf;
		this.textSF=sf;
	}
	 public  void AddSFBF(int firstRow ,String []keys, TextQueryResult []results,String userPath)
	 {

		 	String [] content=new String[keys.length];
			String [] path=new String[keys.length];
			String []textType=new String[keys.length];
			String []tempurl=new String[keys.length];
			
			for(int i=0;i<keys.length;i++)
			{
				 content[i] =results[firstRow+i].getSnippet();
				 //����c/s b/sҪ����
//				 C/S C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps\AUDRweb\dir_1272549669037\d.html
//				 B/S http://localhost:8080/AUDRweb/dir_1272549669037/d.html
				 path[i]=results[firstRow+i].getOriginalFilePath();
				 
				 String []s=results[firstRow+i].getOriginalFileName().split("\\.");
				 //System.out.println("type:"+s[s.length-1]);
				 textType[i]=s[s.length-1];
//				 if(textType[i].equalsIgnoreCase("html"))
//				 {
					 tempurl[i]=path[i].replace(userPath, "\\");//������,�����ĵ�,ʲôʱ�����,������
					 tempurl[i]=tempurl[i].replace("\\", "/");
//				 }
			}
			
			UserQueryTextTransporter uqTransporter1=new UserQueryTextTransporter();
			 String [] resultbf= uqTransporter1.queryBFs(keys);
			 String[] resultsf=uqTransporter1.querySFs(keys);
			 if(resultbf!=null&&resultsf!=null)
			 {
				 //�õ��������Ժ���������
				 ParseXml xml=new ParseXml();
				 try{
					 for(int i=0;i<resultbf.length;i++)
					 {
						TextBasicFeature tempbf =xml.setTextBF(resultbf[i]).get(0);
						TextSemanticFeature  tempsf=xml.setTextSF(resultsf[i]).get(0);
						
						textBF.put(keys[i],tempbf);
						tempsf.set("TextType", textType[i]);
						textSF.put(keys[i], tempsf);
						 
						 TextModel text=new TextModel();
						 //��������
						  text.setDate(tempbf.getStrDate());
						  text.setFileName(tempbf.getFileName());
						  text.setFilePath(tempbf.getFilePath());
						  text.setFileSize(tempbf.getFileSize());
						  //����
//						  text.setTextType(tempsf.get("TextType"));
						  text.setTextType(textType[i]);
//						  System.out.println(textType[i]);
						  text.setTitle(tempsf.get("Title"));
						  text.setSubject(tempsf.get("Subject"));
						  //System.out.println("url:"+tempsf.get("Url"));
						  text.setUrl(tempsf.get("Url"));
						  text.setAuthor(tempsf.get("Author"));
						  text.setKeyword(tempsf.get("Keyword"));
						  text.setRemark(tempsf.get("Remark"));
						  text.setWordCount(tempsf.get("WordCount"));
						  text.setTemplate(tempsf.get("Template"));
						  text.setDepartment(tempsf.get("Department"));
						  text.setSort(tempsf.get("Sort"));
						  text.setLastAuthor(tempsf.get("LastAuthor"));
						  text.setModifyCount(tempsf.get("ModifyCount"));
						  text.setEditTime(tempsf.get("EditTime"));
						  text.setCreateTime(tempsf.get("CreatTime"));
						  text.setPrintTime(tempsf.get("PrintTime"));
						  text.setContent(content[i]);
						  text.setPath(path[i]);
						  if(tempurl!=null&&tempurl.length>i)
						  {
							  text.setTempUrl(tempurl[i]);
						  }
						 //�ѽ�����Ƹ�TEXT
						 
						 list.put(keys[i], text);
					 }
				 }catch(SAXException r)
				 {
					 r.printStackTrace();
					 System.out.println("�쳣����r");
				 }
				 catch(ParserConfigurationException u)
				 {
					 u.printStackTrace(); 
					 System.out.println("�쳣����u");
				 }
				 catch(IOException ee)
				 {
					 ee.printStackTrace();
					 System.out.println("�쳣����ee");
				 }
				 
			 }
			 System.out.println("�Ƽ�����BF��SF��ʼ����ϣ�����");
			 
	 }
	 
	 public void setList(HashMap<String ,TextModel> list)
	 {
		 this.list=list;
	 }
	 public void setTextBF(HashMap<String ,TextBasicFeature> textbf)
	 {
		  this.textBF=textbf;
	 }
	 public void setTextSF(HashMap<String ,TextSemanticFeature>  textsf)
	 {
		  this.textSF=textsf;
	 }
	 
	 public HashMap<String ,TextModel> getList()
	 {
		 return this.list;
	 }
	 public HashMap<String ,TextBasicFeature>getTextBF()
	 {
		 return this.textBF;
	 }
	 public HashMap<String ,TextSemanticFeature>  getTextSF()
	 {
		 return this.textSF;
	 }
	 
}
