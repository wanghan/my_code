package ImageCommon;

import java.util.HashMap;

public class Images {

	public String[] lireSearchType = {"All MPEG-7 Descriptors","Scalable Color (MPEG-7)","Edge Histogram (MPEG-7)","Color Layout (MPEG-7)","Auto Color Correlogram"
				,"CEDD","FCTH","RGB Color Histogram","Tamura","Gabor"};
	
	String[] type = {"image","animal","plant","instrument","scene","activity","structure","food","medicine"};
	
	/**
	 * CS bf
	 * public String[] bfe = {"imageDate","imageAuthor","imagePath","imageName","imageSize"};
	 * */
	
	public String[] bfe = {"imageDate","imageAuthor","imagePath","imageName","imageSize"};
	public String[] bfc = {"日期","提交者","图像路径","图像名称","图像大小"};
	
	/**
	 * CS sf
	 * public String[] sfe = {"place","Format","User_Comment","Description","Quality",
	 *		"Subject","Author","Height","Title","Color",
	 *		"Category","FileName","Date_Time","Width","Keywords"};
	 * 
	 * */
	public String []imageSFItems={"sfid","Place","Format","User_Comment","Description",
			"Quality","Subject","Author","Height","Title","Color","Category",
			"FileName","Date_Time","Width","Keywords"};
	public String []imageSFItemsCH={"sfid","拍摄地点","图像格式","用户评论","用户描述",
			"图像质量","主题","拍摄者","图像高度","标题","图像颜色","图像种类",
			"文件名","拍摄日期时间","图像宽度","关键词"};
	
	public String[] sfe = {"Title","Subject","Keyword","Quality","Picname",
			"Category","Width","Height","Color","Format",
			"Date_time","Place","Author","Description","User_Comment"};
	public String[] sfc = {"标题","主题","关键字","图像质量","文件名",
				"图像种类","图像尺寸-->宽","图像尺寸-->高","图像颜色","图像格式",
				"拍摄日期时间","拍摄地点","拍摄者","图像描述","图像评论"};
	public Images() {
		// TODO Auto-generated constructor stub
	}

	public String getLireSearchType(String id)
	{
		String tmp[] = this.lireSearchType;
		if(id!=null&&!id.equals(""))
		{
			int n = 0;
			try {
				n = Integer.parseInt(id);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if(n<0)
				n=0;
			if(n>tmp.length)
				n=tmp.length;
			
			return tmp[n];
		}
		return tmp[0];
	}
	
	public HashMap getOther(String[] bfv,String[] sfv)
	{
		HashMap map = new HashMap();
		if(bfv==null||sfv==null)
			return null;
		else
		{
			for (int i = 0; i < bfv.length; i++) {
				if(!bfv[i].equals("nothing"))
					map.put(this.bfc[i], bfv[i]);
			}
			for (int i = 0; i < sfv.length; i++) {
				if(!sfv[i].equals("nothing"))
					map.put(this.imageSFItemsCH[i+1], sfv[i]);
			}
		}
		
		return map;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
