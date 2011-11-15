package AUDRwebJavaBeans;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
/*
 * 是用于下层得到检索结果时存储的JavaBean
 */
public class ImageQueryInfo {
  public static int NUM=10;//检索后一页面可以放的缩略图的数量	
  public static int NUMthumb=5;//在放大后下面的所略可以放的个数
  private String index;//当前最大化时访问的图像的ID
  private ArrayList<File> thumbFile=new ArrayList<File>();//得到缩略图
  private ArrayList<String> thumbFilePath=new ArrayList<String>();//对应缩略图在服务器上的地址 ;应该要相对地址
  private ArrayList<File>  file=new ArrayList<File>();//得到原始图像
  private ArrayList<String>filepath=new ArrayList<String>();//得到原始图像在服务器上的地址
  private ArrayList<String> lireId=new ArrayList<String>();//得到图象的LIREID
  private Hashtable<Integer,ImageBFObj> imageBF=new Hashtable<Integer,ImageBFObj>();//存储放大后得到的基本信息
  private Hashtable<Integer,ImageSemanticFeature> imageSF=new Hashtable<Integer,ImageSemanticFeature>();//存储放大后得到的语义信息
  
//下面的用于在单独读取的  每幅图像以在链表中的下标为对应的KEY
  public  ImageBFObj getImageBF(int key)
  {
  	return  imageBF.get(key);
  }
  public ImageSemanticFeature  getImageSF(int key)
  {
  	return  imageSF.get(key);
  }
  
  public void setImageBF(int key,ImageBFObj value)
  {
	  if(imageBF!=null)
		  imageBF.put(key, value);
  }
  public void setImageSF(int key,ImageSemanticFeature value)
  {
	  if(imageSF!=null)
		  imageSF.put(key, value);
  }
  
  public String getIndex()
  {
	  return index;
  }
  public void setIndex(String index)
  {
	  this.index=index;
  }
  public File[] getThumbFile()
  {
	  return thumbFile.toArray(new File[thumbFile.size()]);
  }
//  如果原来已经有图像的化，就加上去
  public void setThumbFile(File [] file)
  {
	  for(int i=0;i<file.length;i++)
		  thumbFile.add(file[i]);
  }
//缩略图像的路径
  public String[]  getThumbFilePath()
  {
	  return thumbFilePath.toArray(new String[thumbFilePath.size()]);
  }
  public void setThumbFilePath(String [] path)
  {
	  for(int i=0;i<path.length;i++)
		  thumbFilePath.add(path[i]);
  }
  
  //原始图像
  public File[] getFile()
  {
	  return file.toArray(new File[file.size()]);
  }
  //添加
  public void setFile(File[] file)
  {
	  for(int i=0;i<file.length;i++)
		  this.file.add(file[i]);
  }
  //原始图像的路径
  public String[]  getFilepath()
  {
	  return filepath.toArray(new String[filepath.size()]);
  }
  public void setFilepath(String [] path)
  {
	  for(int i=0;i<path.length;i++)
		  filepath.add(path[i]);
  }
  public String [] getLireId()
  {
	  return lireId.toArray(new String[lireId.size()]);
  }
  //只调用一次
  public void setLireId(String[] id)
  {
	  if(lireId.size()==0)
	  {
	     for(int i=0;i<id.length;i++)
		  lireId.add(id[i]);
	  }
  }
  //根据某个INDEX得到 LireID
  public String getLireid(int index)
  {
	  return lireId.get(index);
  }
  
}
