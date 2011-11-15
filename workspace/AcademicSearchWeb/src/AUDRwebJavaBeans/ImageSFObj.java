package AUDRwebJavaBeans;
/*
 * 检索的时候要填写的语义特征
 */
public class ImageSFObj {
//图像的语义信息
	
	private String sfid;
	private String place;//拍摄时间
	private String format;//图像格式
	private String user_comment;//用户评论
	private String user_description;//用户描述
	private String quality;//图像质量
	private String subject;//图像主题
	private String author;//作者
	private String height;//图像高度
	private String title;//标题
	private String color;//颜色
	private String category;//种类
	private String filename;//文件名
	private String date_time;//拍摄日期时间
	private String width;//图像宽度
	private String keywords;//关键字
	private static int sfcount=16;//SF的属性个数
	public static int ATTRIBUTENUMBERS = 16;
	public ImageSFObj()
	{}
    public ImageSFObj(String place ,String format,String user_comment,String user_description,String quality,
    		String subject,String author,String height,String title,String color,String category,String filename,
    		String date_time,String width,String keywords)
    {
    	this.place=place;
    	this.format=format;
    	this.user_comment=user_comment;
    	this.user_description=user_description;
    	this.quality=quality;
    	this.subject=subject;
    	this.author=author;
    	this.height=height;
    	this.title=title;
    	this.color=color;
    	this.category=category;
    	this.filename=filename;
    	this.date_time=date_time;
    	this.width=width;
    	this.keywords=keywords;
    }
    
    public String getSfid()
    {
    	return sfid;
    }
    public void setSfid(String sfid)
    {
    	this.sfid=sfid;
    }
	public String getPlace()
	{
		return place;
	}
	public void setPlace(String place)
	{
		this.place=place;
	}
	public String getFormat()
	{
		return format;
	}
	public void setFormat(String format)
	{
		this.format=format;
	}
	public String getUser_comment()
	{
		return user_comment;
	}
	public void setUser_comment(String user_comment)
	{
		this.user_comment=user_comment;
	}
	public String getUser_description()
	{
		return user_description;
	}
	public void setUser_description(String user_description)
	{
		this.user_description=user_description;
	}
	
	public String getQuality()
	{
		return quality;
	}
	public void setQuality(String quality)
	{
		this.quality=quality;
	}
	
	public String getSubject()
	{
		return subject;
	}
	public void setSubject(String subject)
	{
		this.subject=subject;
	}
	
	public String getAuthor()
	{
		return author;
	}
	public void setAuthor(String author)
	{
		this.author=author;
	}
	public String getHeight()
	{
		return height;
	}
	public void setHeight(String height)
	{
		this.height=height;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title=title;
	}
	public String getColor()
	{
		return color;
	}
	public void setColor(String color)
	{
		this.color=color;
	}
	public String getCategory()
	{
		return category;
	}
	public void setCategory(String category)
	{
		this.category=category;
	}
	public String getFilename()
	{
		return filename;
	}
	public void setFilename(String filename)
	{
		this.filename=filename;
	}
	public String getDate_time()
	{
		return date_time;
	}
	public void setDate_time(String date_time)
	{
		this.date_time=date_time;
	}
	
	public String getWidth()
	{
		return width;
	}
	public void setWidth(String width)
	{
		this.width=width;
	}
	public String getKeywords()
	{
		return keywords;
	}
	public void setKeywords(String keywords)
	{
		this.keywords=keywords;
	}
	public static int getSfcount()
	{
		return sfcount;
	}
}
