package AUDRwebJavaBeans;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

/*
 * �洢һЩ����Ҫ�õĵ��ֲ����ڻ�����Ϣ��������Ϣ��
 */

public class ImageJavaBean 
{
	private String searchType;//��������
	private String filePath;//ȡ�ÿͻ��˵ļ�����ͼ��·��
	private String tempfilepath=null;//�ڷ������ϵĵ�ַ
	private File file;//ȡ�õ�ͼ��
	private String lireSearchType;//ͼ��ײ�����ķ�ʽ
	private String imageType;//�����ķ�Χ,��animal food ...?
	//�ײ������ʽ��Ӧ��
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
	public String getLireSearchType()//�������� 0-10
	{
		return lireSearchType;
	}
	public void  setLireSearchType(String type)//type��0-10
	{
	  this.lireSearchType=type;	
	}
	
	public String getLireSearchTypeString(String l)//l��0-10
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
