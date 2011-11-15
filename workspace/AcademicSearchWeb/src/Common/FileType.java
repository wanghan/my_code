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
	
	//�ļ�������ʾ����
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
			//ɾ��
			"showlist_image.jsp","",
			"showlist_text.jsp","",
			"showlist_audio.jsp","",
			"showlist_video.jsp","",
			//�޸�
			"up_showlist_image.jsp","update_image.jsp",
			"up_showlist_text.jsp","update_text.jsp",
			"up_showlist_audio.jsp","update_audio.jsp",
			"up_showlist_video.jsp","update_video.jsp"};
	
	//�ļ����ݲ���
	public String[] cmid = {"cm_to","cm_ftype","cm_sub_ftype","cm_contro","action"};
	
	//��������
	public String[] dbtye = {"add","update","delete"};
	public String[] dbtyc = {"���","�޸�","ɾ��"};
	
	//�ļ�����
	public String[] filetye = {"image","text","audio","video"};
	public String[] filetyc = {"ͼ��","�ı�","��Ƶ","��Ƶ"};
	
	//�ļ�������
	public String[] fste_img = {"animal","plant","instrument","scene","activity",
			"structure","food","medicine"};
	public String[] fstc_img = {"����","ֲ��","����","�羰","�",
			"����","ʳ��","ҽѧ"};
	
	public String[] fste_txt = {"finance","health","education", "military", "science", "tour", "sport", "culture", "consumption", "government"};
	public String[] fstc_txt = {"�ƾ�","����","����", "����", "�Ƽ�", "����", "����", "�Ļ�", "����", "����"};
	
	public String[] fste_aud = {"Audio_pop", "Audio_light_music", "Audio_classic", "Audio_rock_roll", "Audio_jazz", "Audio_country_folk", "Audio_movie_animation", "Audio_child_religion", "Audio_other"};
	public String[] fstc_aud = {"����", "������", "�ŵ�", "ҡ��", "��ʿ", "���&���", "Ӱ��&����", "�ٶ�&�ڽ�", "����"};
	
	public String[] fste_vid = {"Video_Business","Video_Entertainment","Video_Health","Video_News","Video_Education-and-Culture",
			"Video_Science-and-Technology","Video_Society-and-Life","Video_Sports"};
	public String[] fstc_vid = {"��ҵ","����","����","����","�����Ļ�",
			"�Ƽ�","�������","����"};
	
	public String[][] fte = {fste_img,fste_txt,fste_aud,fste_vid};
	public String[][] ftc = {fstc_img,fstc_txt,fstc_aud,fstc_vid};
	
	
	/**
	 * ����Ϊ�������͵�bf��sf��Ӣ�Ķ���
	 * 2011��3��3��13:40:29
	 * �Ƽ���
	 * */
	//bf:6,sf:16
	public String[] img_bfe = {"date","author","filepath","filename","filesize"};	
	public String[] img_bfc = {"����","�ύ��","ͼ��·��","ͼ������","ͼ���С"};
	public String[] img_sfe = {"Place","Format","User_Comment","Description",
			"Quality","Subject","Author","Height","Title","Color","Category",
			"FileName","Date_Time","Width","Keywords"};																		
	public String[] img_sfc = {"����ص�","ͼ���ʽ","�û�����","�û�����",
			"ͼ������","����","������","ͼ��߶�","����","ͼ����ɫ","ͼ������",
			"�ļ���","��������ʱ��","ͼ����","�ؼ���"};
	
	//bf:6,sf:17
	public String[] txt_bfe = {"date","author","filepath","filename","filesize"};		
	public String[] txt_bfc = {"����","����","�ļ�·��","�ļ���","�ļ���С"};
	public String[] txt_sfe = {"Title","Subject","Url",
			"Author","Keyword","Remark","WordCount","Template",
			"Department","Sort","LastAuthor","ModifyCount","EditTime",
			"CreatTime","PrintTime"};
	public String[] txt_sfc = {"����","����","URL",
			"����","�ؼ���","��ע","����","ģ��",
			"��λ","���","�ϴα�����","�޸Ĵ���","�༭����ʱ��",
			"����ʱ��","��ӡʱ��"};
	
	//bf:,sf:22
	public String[] aud_bfe = {"date","author","filepath","filename","filesize"};
	public String[] aud_bfc = {"����","�ύ��","��Ƶ·��","��Ƶ����","��Ƶ��С"};
	public String[] aud_sfe = {"FileSuffix","Bitrate","Keyword","AudioName",
			"Singer","Album","Composer","Years","Language",
			"District","Popularity","Lyrics","AverPitch","Style",
			"Tempo","Instrument","Timbre","Voice","Emotion",
			"Expression","Theme"};
	public String[] aud_sfc = {"��׺","������","�ؼ���","������",
			"����","ר��","������","���","����",
			"����","���ų̶�","���","��������","���",
			"����","����","��ɫ","����","���",
			"�����⾳","����"};
	//bf:5,sf:15
	public String[] vid_bfe = {"author","date","filename","filesize"};					
	public String[] vid_bfc = {"�ύ��","����","��Ƶ����","��Ƶ��С"};
	public String[] vid_sfe = {"Width","Height","File_size","Bit_rate","Frame_rate",
			"Language","Quality","Subtitle","Subject","KeyWord",
			"Description","Scenes","Shots","KeyFrames"};
	public String[] vid_sfc = {"�ֱ��ʿ�","�ֱ��ʸ�","����ʱ��","������","֡��",
			"����","����","��Ļ","����","�ؼ���",
			"��Ƶ����","��������","��ͷ����","�ؼ�֡����"};
	
	public String[][] bfe = {img_bfe,txt_bfe,aud_bfe,vid_bfe};
	public String[][] bfc = {img_bfc,txt_bfc,aud_bfc,vid_bfc};
	public String[][] sfe = {img_sfe,txt_sfe,aud_sfe,vid_sfe};
	public String[][] sfc = {img_sfc,txt_sfc,aud_sfc,vid_sfc};
	
	/**
	 * �ļ���ʽ����
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
	 * �Ƚ��ַ����Ƿ��������������
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
	 * �Ƚ��ַ����Ƿ������List����
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
	 * ���������ַ�������
	 * �Ƚ���������1�Ƿ����������2����
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
	
	//��׺���ж�
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
	
	//�ļ�����
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
	
	//bf����
	public String[] createImageBF(File[] files,String owner)
	{
		if(files==null||files.length==0)
			return null;
		if(owner==null||owner.equals(""))
			return null;
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd��");
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
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd��");
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
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd��");
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
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd��");
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
	 * Hashmap������
	 * �������ܣ�����hashmap
	 * ������һ��hashmap���͵ı���
	 * ����ֵ��hashmap<key,list>,hashmap<value,list>
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
	 * ����ArrayList����
	 * ����һ��ArrayList
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
