package AUDRwebJavaBeans;
/*
 * 文本的基本属性
 */
import java.util.Calendar;

public class TextBasicFeature {

public TextBasicFeature()
{}
	
public TextBasicFeature(String abfid, Calendar adate, String aauthor,String afilePath,String afileName,String afileSize)
{
	bfid = abfid;
	date = adate;
	author = aauthor;
	filePath=afilePath;
	fileName=afileName;
	fileSize=afileSize;
}
public  TextBasicFeature(String bf,String date,String au,String fp,String fn,String fz)
{
	bfid = bf;
	strdate = date;
	author = au;
	filePath=fp;
	fileName=fn;
	fileSize=fz;
}
//public TextBasicFeature(String bauthor,  String bbfid,Calendar bdate,String bfileName,String bfilePath,String bfileSize,String bstrdate)
//{
//	bfid = bbfid;
//	date = bdate;
//	author = bauthor;
//	filePath=bfilePath;
//	
//	fileName=bfileName;
//	fileSize=bfileSize;
//	strdate = bstrdate;
//}
public void setBfid(String id) {
	bfid = id;
}

public void setDate(Calendar d) {
	/*
	 * date.set(Calendar.YEAR, d.get(Calendar.YEAR));
	 * date.set(Calendar.MONTH, d.get(Calendar.MONTH));
	 * date.set(Calendar.DAY_OF_MONTH, d.get(Calendar.DAY_OF_MONTH));
	 */
	date = d;

}
public void setStrDate(String d)
{
	strdate = d;
}

public void setAuthor(String a) {
	author = a;
}
//jia new
public void setFilePath(String a)
{
	filePath=a;
}
public void setFileName(String a)
{
	fileName=a;
}
public void setFileSize(String a)
{
	fileSize=a;
}

public String getBfid() {
	return bfid;
}

public Calendar getDate() {
	return date;
}
public String getStrDate()
{
	return strdate;
}

public String getAuthor() {
	return author;
}
//
public String getFilePath()
{
	return filePath;
}
public String getFileName()
{
	return fileName;
}
public String getFileSize()
{
	return fileSize;
}

private String bfid;//BF ID
private Calendar date;//日期
private String strdate;//日期的String的类型
private String author;//作者
//
private String filePath;//文件路径
private String fileName;//文件名
private String fileSize;//图像的大小
public static int ATTRIBUTENUMBERS = 6;//?

}
