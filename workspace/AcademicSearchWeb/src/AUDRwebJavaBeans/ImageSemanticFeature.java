package AUDRwebJavaBeans;

/*
 * ͼ���������Ϣ
 */
import java.util.*;
public class ImageSemanticFeature 
{	
	
	public String []imageSFItems={"sfid","Place","Format","User_Comment","Description",
			"Quality","Subject","Author","Height","Title","Color","Category",
			"FileName","Date_Time","Width","Keywords"};
	public String []imageSFItemsCH={"sfid","����ص�","ͼ���ʽ","�û�����","�û�����",
			"ͼ������","����","������","ͼ��߶�","����","ͼ����ɫ","ͼ������",
			"�ļ���","��������ʱ��","ͼ����","�ؼ���"};
	private Hashtable<String,String> SFitemsValue=new Hashtable<String,String>();//���е�������Ϊһ��HASHTANLE
	
//ͼ���������Ϣ
	
//	private String sfid;
//	private String place;//����ʱ��
//	private String format;//ͼ���ʽ
//	private String user_comment;//�û�����
//	private String user_description;//�û�����
//	private String quality;//ͼ������
//	private String subject;//ͼ������
//	private String author;//����
//	private String height;//ͼ��߶�
//	private String title;//����
//	private String color;//��ɫ
//	private String category;//����
//	private String filename;//�ļ���
//	private String date_time;//��������ʱ��
//	private String width;//ͼ����
//	private String keywords;//�ؼ���
	private static int sfcount=16;//SF�����Ը���
	public static int ATTRIBUTENUMBERS = 16;
//    public ImageSFObj(String place ,String format,String user_comment,String user_description,String quality,
//    		String subject,String author,String height,String title,String color,String category,String filename,
//    		String date_time,String width,String keywords)
//    {
//    	this.place=place;
//    	this.format=format;
//    	this.user_comment=user_comment;
//    	this.user_description=user_description;
//    	this.quality=quality;
//    	this.subject=subject;
//    	this.author=author;
//    	this.height=height;
//    	this.title=title;
//    	this.color=color;
//    	this.category=category;
//    	this.filename=filename;
//    	this.date_time=date_time;
//    	this.width=width;
//    	this.keywords=keywords;
//    }
    
  
//    public void setSfid(String sfid)
//    {
//    	this.sfid=sfid;
//    }
	public String getPlace()
	{
		
		return SFitemsValue.get(imageSFItems[1]);
	}
//	public void setPlace(String place)
//	{
//		this.place=place;
//	}
	public String getFormat()
	{
		return  SFitemsValue.get(imageSFItems[2]);
	}
//	public void setFormat(String format)
//	{
//		this.format=format;
//	}
	public String getUser_comment()
	{
		return SFitemsValue.get(imageSFItems[3]);
	}
//	public void setUser_comment(String user_comment)
//	{
//		this.user_comment=user_comment;
//	}
	public String getUser_description()
	{
		return SFitemsValue.get(imageSFItems[4]);
	}
//	public void setUser_description(String user_description)
//	{
//		this.user_description=user_description;
//	}
	
	public String getQuality()
	{
		return SFitemsValue.get(imageSFItems[5]);
	}
//	public void setQuality(String quality)
//	{
//		this.quality=quality;
//	}
	
	public String getSubject()
	{
		return  SFitemsValue.get(imageSFItems[6]);
	}
//	public void setSubject(String subject)
//	{
//		this.subject=subject;
//	}
	
	public String getAuthor()
	{
		 return SFitemsValue.get(imageSFItems[7]);
	}
//	public void setAuthor(String author)
//	{
//		this.author=author;
//	}
	public String getHeight()
	{
		return SFitemsValue.get(imageSFItems[8]);
	}
//	public void setHeight(String height)
//	{
//		this.height=height;
//	}
	public String getTitle()
	{
		return SFitemsValue.get(imageSFItems[9]);
	}
//	public void setTitle(String title)
//	{
//		this.title=title;
//	}
	public String getColor()
	{
		return SFitemsValue.get(imageSFItems[10]);
	}
//	public void setColor(String color)
//	{
//		this.color=color;
//	}
	public String getCategory()
	{
		return SFitemsValue.get(imageSFItems[11]);
	}
//	public void setCategory(String category)
//	{
//		this.category=category;
//	}
	public String getFilename()
	{
		return SFitemsValue.get(imageSFItems[12]);
	}
//	public void setFilename(String filename)
//	{
//		this.filename=filename;
//	}
	public String getDate_time()
	{
		return SFitemsValue.get(imageSFItems[13]);
	}
//	public void setDate_time(String date_time)
//	{
//		this.date_time=date_time;
//	}
	
	public String getWidth()
	{
		return SFitemsValue.get(imageSFItems[14]);
	}
//	public void setWidth(String width)
//	{
//		this.width=width;
//	}
	public String getKeywords()
	{
		return SFitemsValue.get(imageSFItems[15]);
	}
//	public void setKeywords(String keywords)
//	{
//		this.keywords=keywords;
//	}
	public static int getSfcount()
	{
		return sfcount;
	}
	public ImageSemanticFeature()
	{
	}
	
	public  ImageSemanticFeature(String s,String f,String format,String w,String h,String k,String d,
			String date,String au,String u,String t,String  subject,String p,String c,
			String quality ,String color)
	{
		 
		 SFitemsValue.put(imageSFItems[0], s);
		 SFitemsValue.put(imageSFItems[1], p);
		 SFitemsValue.put(imageSFItems[2], format);
		 SFitemsValue.put(imageSFItems[3], u);
		 SFitemsValue.put(imageSFItems[4], d);
		 SFitemsValue.put(imageSFItems[5], quality);
		 SFitemsValue.put(imageSFItems[6], subject);
		 SFitemsValue.put(imageSFItems[7], au);
		 SFitemsValue.put(imageSFItems[8], h);
		 SFitemsValue.put(imageSFItems[9], t);
		 SFitemsValue.put(imageSFItems[10], color);
		 SFitemsValue.put(imageSFItems[11], c);
		 SFitemsValue.put(imageSFItems[12], f);
		 SFitemsValue.put(imageSFItems[13], date);
		 SFitemsValue.put(imageSFItems[14], w);
		 SFitemsValue.put(imageSFItems[15], k);
		 
	}
	//SFID���õ�
	public String  getSfid()
	{
		String sfid=get(imageSFItems[0]);
		return sfid;
	}
	//�ܵķ���
	public void set(String key,String value)
	{
		 SFitemsValue.put(key, value);
	}
	
	public String get(String key)
	{
		 return SFitemsValue.get(key);
	}
	
	//�õ����е��������ֵ
	public Hashtable<String,String> getAllItemsValue()
	{
		return SFitemsValue;
	}
	//�õ����е�������
	public String[] getAllItems()
	{
		return imageSFItems;
	}
	public String[]getAllItemsCH()
	{
		return imageSFItemsCH;
	}
	public String getSingleItem(int i)
	{
		if(i>=0&&i<imageSFItems.length)
		  return imageSFItems[i];
		else 
			return null;
	}
	
	
//	public void setDiagnostic_Info(ArrayList<LocalDiagnosis> ke)
//	{
//		ROI = ke;
//	}
	
	
//	public ArrayList<LocalDiagnosis>  getROI()
//	{
//		return ROI ;
//	}
	

	//
	/*
	private float Slice_Thickness; //��Ƭ���??
	private String Area;
	private String Case_Description;
	private String Diagnostic_Info;
	*/
//	private ArrayList<LocalDiagnosis> ROI;
//	public static int ATTRIBUTENUMBERS = 16;
}
class LocalDiagnosis
{
	public LocalDiagnosis()
	{
	}
	public LocalDiagnosis(String s,float x,float y,float w,float h,String date,String time,String area,
			String info,String annotator)
	{
		 sfid = s;
		 ROI_X = x;
		ROI_Y = y;
		ROI_Width = w;
		ROI_Height = h;
		 ROI_Annotation_Date = date;
		 ROI_Annotation_Time = time;
		 ROI_Annotation_Area = area;
		 ROI_Diagnostic_Info = info;
		 ROI_Annotator = annotator;
	}
	public void setSfid(String s)
	{
	
		sfid = s;
		
	}
	public void setROI_X(float x)
	{
		
		 ROI_X = x;
		
	}
	public void setROI_Y(float y)
	{
		
		ROI_Y = y;
		
	}
	public void setROI_Width(float w)
	{
		
		ROI_Width = w;
		
	}
	public void setROI_Height(float h)
	{
		
		ROI_Height = h;
		 
	}
	public void setROI_Annotation_Date(String date)
	{
		
		 ROI_Annotation_Date = date;
		
	}
	public void setROI_Annotation_Time(String time)
	{
		
		 ROI_Annotation_Time = time;
		 
	}
	public void setROI_Annotation_Area(String area)
	{
		
		 ROI_Annotation_Area = area;
		
	}
	public void setROI_Diagnostic_Info(String info)
	{
		
		 ROI_Diagnostic_Info = info;
	}
	public void setROI_Annotator(String annotator)
	{
		 ROI_Annotator = annotator;
	}
	
	public String  getSfid()
	{
	
		return sfid;
		
	}
	public float getROI_X( )
	{
		
		 return ROI_X ;
		
	}
	public float  getROI_Y( )
	{
		
		return ROI_Y ;
		
	}
	public float getROI_Width()
	{
		
		return ROI_Width ;
		
	}
	public float  getROI_Height()
	{
         	return	  ROI_Height;
		 
	}
	public String  getROI_Annotation_Date()
	{
		
		return  ROI_Annotation_Date ;
		
	}
	public String  getROI_Annotation_Time()
	{
		
		 return ROI_Annotation_Time ;
		 
	}
	public String  getROI_Annotation_Area()
	{
		
		 return ROI_Annotation_Area ;
		
	}
	public String  getROI_Diagnostic_Info()
	{
		
		 return ROI_Diagnostic_Info ;
	}
	public String  getROI_Annotator()
	{
		return  ROI_Annotator ;
	}
	
	private String sfid;
	private float ROI_X;
	private float ROI_Y;
	private float ROI_Width;
	private float ROI_Height;
	private String ROI_Annotation_Date;
	private String ROI_Annotation_Time;
	private String ROI_Annotation_Area;
	private String ROI_Diagnostic_Info;
	private String ROI_Annotator;
	public static int ATTRIBUTENUMBERS = 16;//����Ȥ�����Ի�û�а���
	}

