package AUDRwebJavaBeans;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

/*
 * 存储一些检索要用的但又不属于基本信息和语义信息的
 */

public class ImageJavaBean 
{
	private String searchType;//检索类型
	private String filePath;//取得客户端的检索的图像路径
	private String tempfilepath=null;//在服务器上的地址
	private File file;//取得的图像
	private String lireSearchType;//图像底层检索的方式
	private String imageType;//检索的范围,是animal food ...?
	//底层检索方式对应的
  ArrayList<String> liretypes=new ArrayList<String>();
     
	public ImageJavaBean()
	{
		liretypes.add("All MPEG-7 Descriptors");
		liretypes.add("Scalable Color (MPEG-7)");
		liretypes.add("Edge Histogram (MPEG-7)");
	     liretypes.add("Color Layout (MPEG-7)");
	     liretypes.add("Auto Color Correlogram");
	     liretypes.add("CEDD");
	     liretypes.add("FCTH");
	     liretypes.add("RGB Color Histogram");
	     liretypes.add("Tamura");
	     liretypes.add("Gabor");
	}
	public ImageJavaBean(String searchType,String filePath,String lireSearchType,String imageType)
	{
		this.searchType=searchType;
		this.filePath=filePath;
		this.lireSearchType=lireSearchType;
		this.imageType=imageType;
		liretypes.add("All MPEG-7 Descriptors");
		liretypes.add("Scalable Color (MPEG-7)");
		liretypes.add("Edge Histogram (MPEG-7)");
	     liretypes.add("Color Layout (MPEG-7)");
	     liretypes.add("Auto Color Correlogram");
	     liretypes.add("CEDD");
	     liretypes.add("FCTH");
	     liretypes.add("RGB Color Histogram");
	     liretypes.add("Tamura");
	     liretypes.add("Gabor");
		
	}
	public String getSearchType()
	{
		return searchType;
	}
	public void setSearchType(String type)
	{
		searchType=type;
	}
	public String getFilePath()
	{
		return filePath;
	}
	public void setFilePath(String path)
	{
		filePath=path;
	}
	//?
	public File getFile()
	{
		return file;
	}
	public void setFile(File file)
	{
		this.file=file;
	}
	public String getLireSearchType()//返回类型 0-10
	{
		return lireSearchType;
	}
	public void  setLireSearchType(String type)//type是0-10
	{
	  this.lireSearchType=type;	
	}
	
	public String getLireSearchTypeString(String l)//l是0-10
	{
		return liretypes.get(Integer.valueOf(l));
	}
	public String getImageType()
	{
		return imageType;
	}
	public void setImageType(String kind)
	{
		this.imageType=kind;
	}
	public void setTempfilepath(String tempfilepath)
	{
		this.tempfilepath=tempfilepath;
	}
	public String getTempfilepath()
	{
		return tempfilepath;
	}
}
