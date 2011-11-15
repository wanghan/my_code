package AUDRwebJavaBeans;

/*
 * 跟链路连接的不同检索方式要调用的函数
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
	//通过链路跟总控连接,得到数据或返回信息
	
	//实例检索
	//还要加参数,用于存放得到的图像的路径 
//	file path:C:\Program Files\Apache Software Foundation\Tomcat 5.5\webapps\AUDRweb\
	public  RebuildQueryResult queryByLire(ImageJavaBean imageJB,String filepath)
	{
		
		File file=imageJB.getFile();//根据路径得到图像文件
	
		System.out.println("文件名:"+file.getName());
		System.out.println("底层检索方式:"+imageJB.getLireSearchType()+"图像的检索范围"+imageJB.getImageType()+"返回的图像放的路径"+filepath );
		  RebuildQueryResult  result = uqTransporter.Querybylire(file, imageJB.getLireSearchType(),imageJB.getImageType(),filepath);
		//由于传递过来的文件的路径不是在当前工程下的,是在Tomcat 5.5\bin下的,因此要自己移到对应的工程下
		  
		  return result;
	}
	//语义检索
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
		 //打印发送的条件
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
		 
		 
		 System.out.println("图像检索范围:"+Ekind);
		 System.out.println("路径:"+filepath);
		  RebuildQueryResult  result = uqTransporter.Querybyexist(imagebf.toArray(new String[imagebf.size()]), imagesf.toArray(new String[imagesf.size()]),Ekind,filepath);
		  return result;
	}
	//关联检索
	public  RebuildQueryResult queryByAll(ImageJavaBean imageJB,ImageBFObj bf,ImageSFObj sf,String filepath)
	{
//		UserQueryTransporter uqTransporter= new  UserQueryTransporter();
		File file=imageJB.getFile();//根据路径得到图像文件
		
		
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
		 
		 //打印发送的条件
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
		 System.out.println("文件名:"+file.getName());
		 System.out.println("底层检索方式:"+imageJB.getLireSearchType());
		 System.out.println("图像检索范围:"+imageJB.getImageType());
		 System.out.println("路径:"+filepath);
		  RebuildQueryResult  result = uqTransporter.QueryByAll(file ,imageJB.getLireSearchType(),imagebf.toArray(new String[imagebf.size()]), imagesf.toArray(new String[imagesf.size()]),imageJB.getImageType(),filepath);
	    
		  return result;
	}

	//得到检索后的原始图像路径
	public  String[] getFullFiles()
	{
		return uqTransporter.getFullFiles();
	}
}
