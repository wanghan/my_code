package AUDRwebJavaBeans;
/*
 * 检索时填写的基本信息
 */
public class ImageBFObj {
//图像基本属性
	private String bfid;
	private String filedate;//提交时间
	private String fileauthor;//提交者
	private String filepath;//图像路径
	private String filename;//图像名
	private String filesize;//图像 大小
	private static int bfcount=6;//属性的个数
	public ImageBFObj()
	{}
	public ImageBFObj(String filedate,String fileauthor,String filepath,String filename,String filesize)
	{
		this.filedate=filedate;
		this.fileauthor=fileauthor;
		this.filepath=filepath;
		this.filename=filename;
		if(filesize.equals("0"))
		{
			this.filesize="nothing";
		}
		else if(filesize.equals("1"))
		{
			this.filesize="large";
		}
		else if(filesize.equals("2"))
		{
			this.filesize="medium";
		}
		else if(filesize.equals("3"))
		{
			this.filesize="small";
		}
		
	}
	
	public String getBfid()
	{
		return bfid;
	}
	public void setBfid(String bfid)
	{
		this.bfid=bfid;
	}
	public String getFiledate()
	{
		return filedate;
	}
	public void setFiledate(String date)
	{
		this.filedate=date;
	}
	public String getFileauthor()
	{
		return fileauthor;
	}
	public void setFileauthor(String fileauthor)
	{
		this.fileauthor=fileauthor;
	}
	public String getFilepath()
	{
		return filepath;
	}
	public void setFilepath(String filepath)
	{
		this.filepath=filepath;
	}
	public String getFilename()
	{
		return filename;
	}
	public void setFilename(String filename)
    {
		this.filename=filename;
    }
	public String getFilesize()
	{
		return filesize;
	}
	public void setFilesize(String filesize)
	{
		//this.filesize=filesize;
		this.filesize = filesize;
//		if(filesize.equals("0"))
//		{
//			this.filesize="nothing";
//		}
//		else if(filesize.equals("1"))
//		{
//			this.filesize="large";
//		}
//		else if(filesize.equals("2"))
//		{
//			this.filesize="medium";
//		}
//		else if(filesize.equals("3"))
//		{
//			this.filesize="small";
//		}
	}
	public static int getBfcount()
	{
		return bfcount;
	}


}
