package AUDRwebJavaBeans;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
/*
 * �������²�õ��������ʱ�洢��JavaBean
 */
public class ImageQueryInfo {
  public static int NUM=10;//������һҳ����Էŵ�����ͼ������	
  public static int NUMthumb=5;//�ڷŴ����������Կ��Էŵĸ���
  private String index;//��ǰ���ʱ���ʵ�ͼ���ID
  private ArrayList<File> thumbFile=new ArrayList<File>();//�õ�����ͼ
  private ArrayList<String> thumbFilePath=new ArrayList<String>();//��Ӧ����ͼ�ڷ������ϵĵ�ַ ;Ӧ��Ҫ��Ե�ַ
  private ArrayList<File>  file=new ArrayList<File>();//�õ�ԭʼͼ��
  private ArrayList<String>filepath=new ArrayList<String>();//�õ�ԭʼͼ���ڷ������ϵĵ�ַ
  private ArrayList<String> lireId=new ArrayList<String>();//�õ�ͼ���LIREID
  private Hashtable<Integer,ImageBFObj> imageBF=new Hashtable<Integer,ImageBFObj>();//�洢�Ŵ��õ��Ļ�����Ϣ
  private Hashtable<Integer,ImageSemanticFeature> imageSF=new Hashtable<Integer,ImageSemanticFeature>();//�洢�Ŵ��õ���������Ϣ
  
//����������ڵ�����ȡ��  ÿ��ͼ�����������е��±�Ϊ��Ӧ��KEY
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
//  ���ԭ���Ѿ���ͼ��Ļ����ͼ���ȥ
  public void setThumbFile(File [] file)
  {
	  for(int i=0;i<file.length;i++)
		  thumbFile.add(file[i]);
  }
//����ͼ���·��
  public String[]  getThumbFilePath()
  {
	  return thumbFilePath.toArray(new String[thumbFilePath.size()]);
  }
  public void setThumbFilePath(String [] path)
  {
	  for(int i=0;i<path.length;i++)
		  thumbFilePath.add(path[i]);
  }
  
  //ԭʼͼ��
  public File[] getFile()
  {
	  return file.toArray(new File[file.size()]);
  }
  //���
  public void setFile(File[] file)
  {
	  for(int i=0;i<file.length;i++)
		  this.file.add(file[i]);
  }
  //ԭʼͼ���·��
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
  //ֻ����һ��
  public void setLireId(String[] id)
  {
	  if(lireId.size()==0)
	  {
	     for(int i=0;i<id.length;i++)
		  lireId.add(id[i]);
	  }
  }
  //����ĳ��INDEX�õ� LireID
  public String getLireid(int index)
  {
	  return lireId.get(index);
  }
  
}
