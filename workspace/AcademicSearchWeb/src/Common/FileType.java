package Common;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class FileType {

	public FileType() {
		// TODO Auto-generated constructor stub
	}
	
	//文件管理显示类型
	public String[] action = {"addform","updageform","deleteform",
			"imglist","imgmsg",		
			"txtlist","txtmsg",
			"audlist","audmsg",
			"vidlist","vidmsg",
			"ud_imglist","ud_imgmsg",
			"ud_txtlist","ud_txtmsg",
			"ud_audlist","ud_audmsg",
			"ud_vidlist","ud_vidmsg"};
	public String[] urls = {"file_addform.jsp","file_updform.jsp","file_delform.jsp",
			//删除
			"showlist_image.jsp","",
			"showlist_text.jsp","",
			"showlist_audio.jsp","",
			"showlist_video.jsp","",
			//修改
			"up_showlist_image.jsp","update_image.jsp",
			"up_showlist_text.jsp","update_text.jsp",
			"up_showlist_audio.jsp","update_audio.jsp",
			"up_showlist_video.jsp","update_video.jsp"};
	
	//文件传递参数
	public String[] cmid = {"cm_to","cm_ftype","cm_sub_ftype","cm_contro","action"};
	
	//管理类型
	public String[] dbtye = {"add","update","delete"};
	public String[] dbtyc = {"添加","修改","删除"};
	
	//文件类型
	public String[] filetye = {"image","text","audio","video"};
	public String[] filetyc = {"图像","文本","音频","视频"};
	
	//文件子类型
	public String[] fste_img = {"animal","plant","instrument","scene","activity",
			"structure","food","medicine"};
	public String[] fstc_img = {"动物","植物","仪器","风景","活动",
			"建筑","食物","医学"};
	
	public String[] fste_txt = {"finance","health","education", "military", "science", "tour", "sport", "culture", "consumption", "government"};
	public String[] fstc_txt = {"财经","健康","教育", "军事", "科技", "旅游", "体育", "文化", "消费", "政府"};
	
	public String[] fste_aud = {"Audio_pop", "Audio_light_music", "Audio_classic", "Audio_rock_roll", "Audio_jazz", "Audio_country_folk", "Audio_movie_animation", "Audio_child_religion", "Audio_other"};
	public String[] fstc_aud = {"流行", "轻音乐", "古典", "摇滚", "爵士", "乡村&民歌", "影视&动漫", "少儿&宗教", "其他"};
	
	public String[] fste_vid = {"Video_Business","Video_Entertainment","Video_Health","Video_News","Video_Education-and-Culture",
			"Video_Science-and-Technology","Video_Society-and-Life","Video_Sports"};
	public String[] fstc_vid = {"商业","娱乐","健康","新闻","教育文化",
			"科技","社会生活","体育"};
	
	public String[][] fte = {fste_img,fste_txt,fste_aud,fste_vid};
	public String[][] ftc = {fstc_img,fstc_txt,fstc_aud,fstc_vid};
	
	
	/**
	 * 以下为各个类型的bf、sf中英文对照
	 * 2011年3月3日13:40:29
	 * 黄吉军
	 * */
	//bf:6,sf:16
	public String[] img_bfe = {"date","author","filepath","filename","filesize"};	
	public String[] img_bfc = {"日期","提交者","图像路径","图像名称","图像大小"};
	public String[] img_sfe = {"Place","Format","User_Comment","Description",
			"Quality","Subject","Author","Height","Title","Color","Category",
			"FileName","Date_Time","Width","Keywords"};																		
	public String[] img_sfc = {"拍摄地点","图像格式","用户评论","用户描述",
			"图像质量","主题","拍摄者","图像高度","标题","图像颜色","图像种类",
			"文件名","拍摄日期时间","图像宽度","关键词"};
	
	//bf:6,sf:17
	public String[] txt_bfe = {"date","author","filepath","filename","filesize"};		
	public String[] txt_bfc = {"日期","作者","文件路径","文件名","文件大小"};
	public String[] txt_sfe = {"Title","Subject","Url",
			"Author","Keyword","Remark","WordCount","Template",
			"Department","Sort","LastAuthor","ModifyCount","EditTime",
			"CreatTime","PrintTime"};
	public String[] txt_sfc = {"标题","主题","URL",
			"作者","关键字","备注","字数","模板",
			"单位","类别","上次保存者","修改次数","编辑持续时间",
			"创建时间","打印时间"};
	
	//bf:,sf:22
	public String[] aud_bfe = {"date","author","filepath","filename","filesize"};
	public String[] aud_bfc = {"日期","提交者","音频路径","音频名称","音频大小"};
	public String[] aud_sfe = {"FileSuffix","Bitrate","Keyword","AudioName",
			"Singer","Album","Composer","Years","Language",
			"District","Popularity","Lyrics","AverPitch","Style",
			"Tempo","Instrument","Timbre","Voice","Emotion",
			"Expression","Theme"};
	public String[] aud_sfc = {"后缀","比特率","关键字","歌曲名",
			"歌手","专辑","创造者","年代","语言",
			"地区","热门程度","歌词","整体音高","风格",
			"节奏","乐器","音色","人声","情感",
			"表现意境","主题"};
	//bf:5,sf:15
	public String[] vid_bfe = {"author","date","filename","filesize"};					
	public String[] vid_bfc = {"提交者","日期","视频名称","视频大小"};
	public String[] vid_sfe = {"Width","Height","File_size","Bit_rate","Frame_rate",
			"Language","Quality","Subtitle","Subject","KeyWord",
			"Description","Scenes","Shots","KeyFrames"};
	public String[] vid_sfc = {"分辨率宽","分辨率高","持续时间","比特率","帧率",
			"语言","质量","字幕","主题","关键字",
			"视频描述","场景描述","镜头描述","关键帧描述"};
	
	public String[][] bfe = {img_bfe,txt_bfe,aud_bfe,vid_bfe};
	public String[][] bfc = {img_bfc,txt_bfc,aud_bfc,vid_bfc};
	public String[][] sfe = {img_sfe,txt_sfe,aud_sfe,vid_sfe};
	public String[][] sfc = {img_sfc,txt_sfc,aud_sfc,vid_sfc};
	
	/**
	 * 文件格式常量
	 * */
	/*Image*/
	public String[] imgFileFix = {"jpg","jpeg","txt"};

	/*Text*/
	public String[] txtFileFix = {"txt","doc","xls","ppt","html","pdf"};

	/*Audio*/
	public String[] audFileFix = {"mp3","wav","txt"};
	
	/*Video*/
	public String[] vidFileFix = {"avi","txt","jpg"};

	/*Totle*/
	public String[][] fileFix = {imgFileFix,txtFileFix,audFileFix,vidFileFix};

	
	public String processFix(String[] s)
	{
		if(s==null||s.length==0)
			return "";
		
		String rs = "";
		for (int i=0;i<s.length;i++) {
			if(i!=s.length-1)
				rs+="*."+s[i]+";";
			else
				rs+="*."+s[i];
		}
		
		return rs;
	}
	
	/**
	 * 比较字符串是否包含在数组里面
	 * */
	public int ContainsString(String str,String[] strs)
	{
		str = str.trim();
		if(str==null||str.equals("")||strs==null||strs.length==0)
			return -1;
		
		for (int i = 0; i < strs.length; i++) {
			if(str.equals(strs[i]))
				return i;
		}
		
		return -100;
	}
	/**
	 * 比较字符串是否包含在List里面
	 * */
	public int ContainsString(String str,ArrayList list)
	{
		str = str.trim();
		if(str==null||str.equals("")||list==null||list.size()==0)
			return -1;
		
		for (int i = 0; i < list.size(); i++) {
			if(str.equals(list.get(i).toString()))
				return i;
		}
		
		return -100;
	}
	
	/**
	 * 操作对象：字符串数组
	 * 比较数组数组1是否包含在数组2里面
	 * */
	public int ContainsArray(String[] s1,String[] s2)
	{
		System.out.println("start....");
		if(s1==null)
			return 100;
		else if(s2==null)
			return -100;
		else
		{
			ArrayList temp = new ArrayList();
			loop: for (int i = 0; i < s1.length; i++) {
				 for (int j = 0; j < s2.length; j++) {
					if(s2[j].toString().toLowerCase().equals(s1[i].toString().toLowerCase()))
					{
						temp.add(s1[i]);
						continue loop;
					}
				}
			}
			if(temp.size()==s2.length&&temp.size()==s1.length)
			{
				return 0;
			}
			else if(temp.size()==s1.length)
			{
				return 1;
			}
			else if(temp.size()==s2.length)
			{
				return -1;
			}
			else
			{
				return -2;
			}
		}
		
	}
	
	//后缀名判断
	public int getAUDRType(File[] files,String[] fix)
	{
		//String[] ret = this.filetye;
		if(fix==null||fix.length==0)
			return -1;	
		if(files==null||files.length==0)
			return -1;
		
		int n=0;
		loop:for (int j = 0; j < files.length; j++) {
			String tmpname=files[j].getName();
			String tmpfix = tmpname.substring(tmpname.lastIndexOf(".")+1);
				for (int i = 0; i < fix.length; i++) {
					String tmp = fix[i];
				
					if(tmp.equals(tmpfix))
					{
						n++;
						continue loop;
					}
			}
		}
		
		if(n==files.length)
			return 1;
		
		return -1;
	}
	
	//文件分类
	public HashMap<String,ArrayList> sortFiles(File[] files)
	{
		if(files==null||files.length==0)
			return null;
		ArrayList list = new ArrayList();
		for (int i = 0; i < files.length; i++) {
			String tmp = files[i].getName();
			String fix = tmp.substring(tmp.lastIndexOf(".")+1);
			list.add(fix);
		}
		Collections.sort(list);
		
		list = new ArrayList(new HashSet(list));
		
		HashMap map = new HashMap();
		loop:for (int j = 0; j < list.size(); j++) {
			
				ArrayList<File> rs = new ArrayList<File>();
				
				for (int i = 0; i < files.length; i++) {
					String tmp = files[i].getName();
					String fix = tmp.substring(tmp.lastIndexOf(".")+1);
		
					if(fix.toString().equals(list.get(j).toString()))
					{
						rs.add(files[i]);
				}
				
				}
			map.put(list.get(j), rs);
			
		}

		return map;
	}
	
	//bf生成
	public String[] createImageBF(File[] files,String owner)
	{
		if(files==null||files.length==0)
			return null;
		if(owner==null||owner.equals(""))
			return null;
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
		String date=sdf.format(new Date());
		String sizek = "filesize";
		
		String[] bf = new String[files.length];
		boolean b = true;
		for (int i = 0; i < files.length; i++) {
			try {
				FileInputStream fileInputStream = new FileInputStream(files[i]);
				sizek =Math.round(fileInputStream.available()/ 1024 ) + " KB";
		        fileInputStream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				b=false;
				e.printStackTrace();
			}
			bf[i] = date+ ";" +owner+ ";" +files[i].getAbsolutePath()+ ";" +files[i].getName()+ ";" +sizek;
		}
		
		if(b)
			return bf;
		else
			return null;
	}
	
	public String[] createTextBF(File[] files,String owner)
	{
		if(files==null||files.length==0)
			return null;
		if(owner==null||owner.equals(""))
			return null;
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
		String date=sdf.format(new Date());
		String sizek = "filesize";
		
		String[] bf = new String[files.length];
		boolean b = true;
		for (int i = 0; i < files.length; i++) {
			try {
				FileInputStream fileInputStream = new FileInputStream(files[i]);
				sizek =Math.round(fileInputStream.available()/ 1024 ) + " KB";
		        fileInputStream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				b=false;
				e.printStackTrace();
			}
			bf[i] = date+ ";" +owner+ ";" +files[i].getAbsolutePath()+ ";" +files[i].getName()+ ";" +sizek;
		}
		
		if(b)
			return bf;
		else
			return null;
	}
	public String[] createAudioBF(File[] files,String owner)
	{
		if(files==null||files.length==0)
			return null;
		if(owner==null||owner.equals(""))
			return null;
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
		String date=sdf.format(new Date());
		String sizek = "filesize";
		
		String[] bf = new String[files.length];
		boolean b = true;
		for (int i = 0; i < files.length; i++) {
			try {
				FileInputStream fileInputStream = new FileInputStream(files[i]);
				sizek =Math.round(fileInputStream.available()/ 1024 / 1024) + " MB";
		        fileInputStream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				b=false;
				e.printStackTrace();
			}
			bf[i] = date+ ";" +owner+ ";" +files[i].getAbsolutePath()+ ";" +files[i].getAbsolutePath()+ ";" +sizek;
		}
		
		if(b)
			return bf;
		else
			return null;
	}
	public String[] createVideoBF(File[] files,String owner)
	{
		if(files==null||files.length==0)
			return null;
		if(owner==null||owner.equals(""))
			return null;
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
		String date=sdf.format(new Date());
		String sizek = "filesize";
		
		String[] bf = new String[files.length];
		boolean b = true;
		for (int i = 0; i < files.length; i++) {
			try {
				FileInputStream fileInputStream = new FileInputStream(files[i]);
				sizek =Math.round(fileInputStream.available()/ 1024 / 1024) + " MB";
		        fileInputStream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				b=false;
				e.printStackTrace();
			}
			bf[i] = owner+ ";" +date+ ";" +files[i].getName()+ ";" +sizek;
		}
		
		if(b)
			return bf;
		else
			return null;
	}
	
	
	/**
	 * Hashmap处理函数
	 * 函数功能：分离hashmap
	 * 参数：一个hashmap类型的变量
	 * 返回值：hashmap<key,list>,hashmap<value,list>
	 * */
	public HashMap<String,ArrayList> getKeyAndValue(HashMap map)
	{
		String[] kv = {"key","value"};
		HashMap<String,ArrayList> m = new HashMap<String,ArrayList>();
		if(map.isEmpty())
			return null;
		
		ArrayList tmkey = new ArrayList();
		ArrayList tmvalue = new ArrayList();
		Iterator it=map.keySet().iterator(); 
		while(it.hasNext()){
			Object key=(Object)it.next();
			Object value=map.get(key);
			//System.out.println("hashMap Key:"+key);
			tmkey.add(key.toString());
			tmvalue.add(value);
			//System.out.println("hashMap Value:"+hm.get(key));
		} 
		
		m.put(kv[0], tmkey);
		m.put(kv[1], tmvalue);
		
		return m;
	}
	
	/**
	 * 两个ArrayList连接
	 * 返回一个ArrayList
	 * */
	public ArrayList joinArrayList(ArrayList a,ArrayList b)
	{
		if(a==null&&b==null)
			return null;
		else if(a==null)
			return b;
		else if(b==null)
			return a;
		else
		{
			for (int i = 0; i < b.size(); i++) {
				a.add(b.get(i));
			}
			return a;
		}
	}
	
	public boolean deleteFile(String fpath)
	{
		if(fpath==null||fpath.equals(""))
			return false;
		File f = new File(fpath);
		if(!f.exists())
			return false;
		
		return f.delete();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
