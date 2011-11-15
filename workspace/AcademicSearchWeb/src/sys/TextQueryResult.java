/**
 * 
 */
package sys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * @author wanghan
 *
 */
public class TextQueryResult {

	public String id;//文档的唯一标识
	public String originalFileName; //原始文档的文件名
	public String originalFilePath; //原始文档的文件路径
	public double score;  //底层特征的相似度评分
	public String snippet;
	public TextQueryResult(){
		
	}
	
	
	public TextQueryResult(String id,String fileName,String filePath,double score) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.originalFileName=fileName;
		this.originalFilePath=filePath;
		this.score=score;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id+"@"+originalFileName+"@"+originalFilePath+"@"+score;
	}

	public String getBf()
	{
		return id;
	}
	public String getSf()
	{
		return originalFileName;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getOriginalFilePath() {
		return originalFilePath;
	}

	public void setOriginalFilePath(String originalFilePath) {
		this.originalFilePath = originalFilePath;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}


	public String getSnippet() {
		return snippet;
	}


	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	
//	public String getContent()//这个信息要修改
//	{
//		try
//		{
//		 BufferedReader   in   =   new   BufferedReader(   
//	                new   FileReader(this.originalFilePath));   
//		  StringBuffer   sb   =   new   StringBuffer();   
//		  String   temp   =   null;   
//		  while   (   (temp   =   in.readLine())   !=   null   )   
//		          sb.append(temp);   
//		  
//		  System.out.println(sb.toString());
//            return sb.toString();
//		  
//	
//		}catch(IOException e)
//		{
//			e.printStackTrace();
//		}
//		return null;
//		
//	}
	
	
}
