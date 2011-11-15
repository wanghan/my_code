package AUDRwebJavaBeans;

/*
 * ����·���ӵĲ�ͬ������ʽҪ���õĺ���
 */
import java.io.File;
import java.util.ArrayList;

import AudrConsole.UserQueryTransporter;


import sys.RebuildQueryResult;


public class QueryBy {
	private  UserQueryTransporter uqTransporter;
	
	public QueryBy()
	{
		uqTransporter= new  UserQueryTransporter();
	}
	//ͨ����·���ܿ�����,�õ����ݻ򷵻���Ϣ
	
	//ʵ������
	//��Ҫ�Ӳ���,���ڴ�ŵõ���ͼ���·�� 
//	file path:C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps\AUDRweb\
	public  RebuildQueryResult queryByLire(ImageJavaBean imageJB,String filepath)
	{
		
		File file=imageJB.getFile();//����·���õ�ͼ���ļ�
	
		System.out.println("�ļ���:"+file.getName());
		System.out.println("�ײ������ʽ:"+imageJB.getLireSearchType()+"ͼ��ļ�����Χ"+imageJB.getImageType()+"���ص�ͼ��ŵ�·��"+filepath );
		  RebuildQueryResult  result = uqTransporter.Querybylire(file, imageJB.getLireSearchType(),imageJB.getImageType(),filepath);
		//���ڴ��ݹ������ļ���·�������ڵ�ǰ�����µ�,����Tomcat 5.5\bin�µ�,���Ҫ�Լ��Ƶ���Ӧ�Ĺ�����
		  
		  return result;
	}
	//�������
	public  RebuildQueryResult quertByexist(ImageJavaBean imageJB,ImageBFObj bf,ImageSFObj sf,String filepath)
	{
//		UserQueryTransporter uqTransporter= new  UserQueryTransporter();
		
		ArrayList<String> imagebf=new ArrayList<String>();
		if(bf.getFiledate().equals(""))
		{
			imagebf.add("nothing");
		}
		else
		{
		 imagebf.add(bf.getFiledate());
		}
		if(bf.getFileauthor().equals(""))
		{
			imagebf.add("nothing");
		}
		else
		{
			imagebf.add(bf.getFileauthor());
		}
		
		if(bf.getFilepath().equals(""))
		{
			imagebf.add("nothing");
		}
		else
		{
		 imagebf.add(bf.getFilepath());
		}
		if(bf.getFilename().equals(""))
		{
			imagebf.add("nothing");
		}
		else
		{
			imagebf.add(bf.getFilename());
		}
		if(bf.getFilesize().equals(""))
		{
			imagebf.add("nothing");
		}
		else
		{
			imagebf.add(bf.getFilesize());
		}
		 
		 ArrayList<String> imagesf=new ArrayList<String>();
		 if(sf.getPlace().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
			 imagesf.add(sf.getPlace());
		 }
		 
		 if(sf.getFormat().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 
		 else
		 {
			 imagesf.add(sf.getFormat());
		 }
		 if(sf.getUser_comment().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
			 imagesf.add(sf.getUser_comment());
		 }
		 if(sf.getUser_description().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
			 imagesf.add(sf.getUser_description());
		 }
		 if(sf.getQuality().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
			 imagesf.add(sf.getQuality());
		 }
		 if(sf.getSubject().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getSubject());
		 }
		 if(sf.getAuthor().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getAuthor());
		 }
		 if(sf.getHeight().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getHeight());
		 }
		 if(sf.getTitle().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getTitle());
		 }
		 if(sf.getColor().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getColor());
		 }
		 if(sf.getCategory().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getCategory());
		 }
		 if(sf.getFilename().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getFilename());
		 }
		 if(sf.getDate_time().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getDate_time());
		 }
		 if(sf.getWidth().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getWidth());
		 }
		 if(sf.getKeywords().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getKeywords());
		 }
		 //��ӡ���͵�����
		 for(int i=0;i<imagebf.size();i++)
		 {
			 if(!imagebf.get(i).equals("nothing"))
				 System.out.print(""+imagebf.get(i));
		 }
		 for(int i=0;i<imagesf.size();i++)
		 {
			 if(!imagesf.get(i).equals("nothing"))
				 System.out.print(""+imagesf.get(i));
		 }
		 
		 String Ekind = imageJB.getImageType();
		 
		 
		 System.out.println("ͼ�������Χ:"+Ekind);
		 System.out.println("·��:"+filepath);
		  RebuildQueryResult  result = uqTransporter.Querybyexist(imagebf.toArray(new String[imagebf.size()]), imagesf.toArray(new String[imagesf.size()]),Ekind,filepath);
		  return result;
	}
	//��������
	public  RebuildQueryResult queryByAll(ImageJavaBean imageJB,ImageBFObj bf,ImageSFObj sf,String filepath)
	{
//		UserQueryTransporter uqTransporter= new  UserQueryTransporter();
		File file=imageJB.getFile();//����·���õ�ͼ���ļ�
		
		
		ArrayList<String> imagebf=new ArrayList<String>();
		if(bf.getFiledate().equals(""))
		{
			imagebf.add("nothing");
		}
		else
		{
		 imagebf.add(bf.getFiledate());
		}
		
		if(bf.getFileauthor().equals(""))
		{
			imagebf.add("nothing");
		}
		else
		{
			imagebf.add(bf.getFileauthor());
		}
		
		if(bf.getFilepath().equals(""))
		{
			imagebf.add("nothing");
		}
		else
		{
		 imagebf.add(bf.getFilepath());
		}
		
		if(bf.getFilename().equals(""))
		{
			imagebf.add("nothing");
		}
		else
		{
			imagebf.add(bf.getFilename());
		}
		
		if(bf.getFilesize().equals(""))
		{
			imagebf.add("nothing");
		}
		else
		{
			imagebf.add(bf.getFilesize());
		}
		 
		
		
		 ArrayList<String> imagesf=new ArrayList<String>();
		 if(sf.getPlace().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
			 imagesf.add(sf.getPlace());
		 }
		 
		 if(sf.getFormat().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 
		 else
		 {
			 imagesf.add(sf.getFormat());
		 }
		 if(sf.getUser_comment().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
			 imagesf.add(sf.getUser_comment());
		 }
		 if(sf.getUser_description().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
			 imagesf.add(sf.getUser_description());
		 }
		 if(sf.getQuality().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
			 imagesf.add(sf.getQuality());
		 }
		 if(sf.getSubject().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getSubject());
		 }
		 if(sf.getAuthor().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getAuthor());
		 }
		 if(sf.getHeight().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getHeight());
		 }
		 if(sf.getTitle().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getTitle());
		 }
		 if(sf.getColor().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getColor());
		 }
		 if(sf.getCategory().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getCategory());
		 }
		 if(sf.getFilename().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getFilename());
		 }
		 if(sf.getDate_time().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getDate_time());
		 }
		 if(sf.getWidth().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getWidth());
		 }
		 if(sf.getKeywords().equals(""))
		 {
			 imagesf.add("nothing");
		 }
		 else
		 {
		 imagesf.add(sf.getKeywords());
		 }
		 
		 //��ӡ���͵�����
		 for(int i=0;i<imagebf.size();i++)
		 {
			 if(!imagebf.get(i).equals("nothing"))
				 System.out.print(""+imagebf.get(i));
		 }
		 for(int i=0;i<imagesf.size();i++)
		 {
			 if(!imagesf.get(i).equals("nothing"))
				 System.out.print(""+imagesf.get(i));
		 }
		 System.out.println("�ļ���:"+file.getName());
		 System.out.println("�ײ������ʽ:"+imageJB.getLireSearchType());
		 System.out.println("ͼ�������Χ:"+imageJB.getImageType());
		 System.out.println("·��:"+filepath);
		  RebuildQueryResult  result = uqTransporter.QueryByAll(file ,imageJB.getLireSearchType(),imagebf.toArray(new String[imagebf.size()]), imagesf.toArray(new String[imagesf.size()]),imageJB.getImageType(),filepath);
	    
		  return result;
	}

	//�õ��������ԭʼͼ��·��
	public  String[] getFullFiles()
	{
		return uqTransporter.getFullFiles();
	}
}
