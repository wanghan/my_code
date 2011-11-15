package AUDRcopyCS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import sys.RebuildQueryResult;
import AUDRwebJavaBeans.ImageBFObj;
import AUDRwebJavaBeans.ImageSemanticFeature;
import AUDRwebJavaBeans.TextBasicFeature;
import AUDRwebJavaBeans.TextSemanticFeature;

/*
 * 这个类用于在放大时总控传递过来对应一个图像的基本特征和语义信息
 */
public class ParseXml {

	
	public ArrayList<ImageBFObj> setBF(RebuildQueryResult aresult,String filenameBF) throws SAXException, ParserConfigurationException ,FileNotFoundException,IOException
	{
		if(aresult.getBf()!=null)
 		{
 			//读出文本
	         
		    try{
//	        	在给出 File 对象的情况下构造一个 FileWriter 对象。如果第二个参数为 true，则将字节写入文件末尾处，而不是写入文件开始处。 
//		    	   FileWriter fw = new FileWriter(filenameBF, true); //  throw IOException
//		           PrintWriter outBF = new PrintWriter(fw);//有问题
//		    	File file=new File(filenameBF);
		    	
		           RandomAccessFile randomFile=new RandomAccessFile(filenameBF,"rw");
		           if(randomFile.length()==0)
		           {
			           randomFile.write("<?xml version=\"1.0\" encoding=\"gbk\"?>\n".getBytes("gbk"));
			           randomFile.write("<basicfeature>\n".getBytes("gbk"));
			           
		           }
		           else
		           {
		        	   randomFile.seek(randomFile.length()-"</basicfeature>\n".getBytes("gbk").length);
		           }
		           String str=null;
			 		for(int i =0;i<aresult.getBf().length;i++)
			 		{
			 		    str= aresult.getBf()[i]+"\n";
			 		   randomFile.write(str.getBytes("gbk"));//把它写到文件里
						
			 		}
			 		randomFile.write("</basicfeature>\n".getBytes("gbk"));
			 		randomFile.close();	
	 		
	 		     }catch( FileNotFoundException e)
	 	     	{
	 		    	 e.printStackTrace();
	 		    }catch(IOException ee)
	 		    {
	 		    	ee.printStackTrace();
	 		    }
 		  }
		else
		{
				System.out.println("基本属性值为 空!");
		}
			
		//解析文件
		XMLToString xmlto=new XMLToString();
		
		ArrayList<ImageBFObj> bf=	xmlto. parseImageBF(true,filenameBF);
	 
		return bf;
		
		
	}
	
	
   public  ArrayList<ImageSemanticFeature>  setSF(RebuildQueryResult aresult,String filenameSF)  throws SAXException, ParserConfigurationException ,FileNotFoundException,IOException
	{
	
		if(aresult.getSf()!=null)
		{
			try{
			 		 RandomAccessFile randomFile=new RandomAccessFile(filenameSF,"rw");
			           if(randomFile.length()==0)
			           {
				           randomFile.write("<?xml version=\"1.0\" encoding=\"gbk\"?>\n".getBytes("gbk"));
				           randomFile.write("<semanticfeature>\n".getBytes("gbk"));
			           }
			           else
			           {
			        	   randomFile.seek(randomFile.length()-"</semanticfeature>\n".getBytes("gbk").length);
			           }
			           String str=null;
				 		for(int i =0;i<aresult.getSf().length;i++)
				 		{
				 		    str= aresult.getSf()[i]+"\n";
				 		   randomFile.write(str.getBytes("gbk"));//把它写到文件里
							
				 		}
//				 		randomFile.write("\n".getBytes("gbk"));
				 		randomFile.write("</semanticfeature>\n".getBytes("gbk"));
				 		randomFile.close();	
			   }catch( FileNotFoundException e)
			   {
				   e.printStackTrace();
			    }catch(IOException ee)
			    {
			    	ee.printStackTrace();
			    }
			   
		   }
		else
		{
			System.out.println("语义特征值为 空!");
		}
		
		//解析
		XMLToString xmlto=new XMLToString();
		 ArrayList<ImageSemanticFeature> sf=xmlto.parseImageSF(true,filenameSF);
		 return sf;
		
	}
   
   /*
	 * setTextBF setTextSF用于TEXT读取 BF SF
	 */
	public ArrayList<TextBasicFeature> setTextBF(String aresult) throws SAXException, ParserConfigurationException ,FileNotFoundException,IOException
	{
		String tempBf="<?xml version=\"1.0\" encoding=\"gbk\"?> <basicfeature>";
		if(aresult!=null)
		{
		 			tempBf= tempBf+aresult;
	 		    
		  }
		else
		{
				System.out.println("基本属性值为 空!");
		}
		tempBf=tempBf+"</basicfeature>";
			
		//解析文件
		XMLToString xmlto=new XMLToString();
		ArrayList<TextBasicFeature> bf=xmlto.parseTextBF(false,tempBf);
	 
		return bf;
	}
	
	
  public  ArrayList<TextSemanticFeature>  setTextSF(String aresult)  throws SAXException, ParserConfigurationException ,FileNotFoundException,IOException
	{
	   String tempBf="<?xml version=\"1.0\" encoding=\"gbk\"?> <semanticfeature>";
		if(aresult!=null)
		{
		 		
		 			tempBf= tempBf+aresult;
					
		 		
	 		    
		  }
		else
		{
				System.out.println("基本属性值为 空!");
		}
		tempBf=tempBf+"</semanticfeature>";
			
		//解析文件
		XMLToString xmlto=new XMLToString();
		ArrayList<TextSemanticFeature> sf=xmlto.parseTextSF(false,tempBf);
	 
		return sf;
	}

}
