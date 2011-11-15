package toosl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class FindFiles {

	public FindFiles() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��������ļ�
	 * ������һ���������ŷ���ֵ��һ��Ŀ¼���ļ���׺
	 * ����ֵ������
	 * */
	public ArrayList getFiles(ArrayList list,String perpath,String fix)
	{
		if(perpath==null||perpath.trim().equals(""))
			;
		else
		{
			File dir = new File(perpath);
			if(dir.isFile()||!dir.exists())
				;
			else
			{
				File[] files = dir.listFiles();
				for (File file : files) {
					if(file.isFile())
					{
						//System.out.println(file.getPath());
						String temp = this.fixVerdict(file, fix);
						if(!temp.equals(""))
							list.add(temp);
					}
					else if(file.isDirectory())
					{
						//list.add(file.getPath());
						//System.out.println(file.getPath());
						getFiles(list,file.getAbsolutePath(),fix);
					}
					else
						;
				}
			}
		}
		return list;
	}
	
	//�ж��ļ�����
	public String fixVerdict(File file,String fix)
	{
		if(file.isFile())
		{
			if(fix!=null&&!fix.trim().equals(""))
			{
				String[] ttm = file.getName().split("\\.");
				String tem = "";
				if(ttm.length==2)
					tem = ttm[1];
				else
					tem = ttm[0];
				//System.out.println(tem);
				if(fix.toUpperCase().trim().equals(tem.toUpperCase().trim()))
				{return file.getAbsolutePath();}
			}
			else
			{
				return file.getAbsolutePath();
			}
		}
		return "";
	}
	
	/**
	 * ����ļ��Ĵ�����Ϣ
	 * ������
	 * ����ֵ��HashMap{�ļ���С���ļ���������޸�ʱ���ʽ1������޸�ʱ���ʽ2}��
	 * ����ֵ��HashMap{len��nam��lmt1,lmt2}��
	 * */
	public HashMap getFileInformation(File file)
	{
		SimpleDateFormat fmt = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
						 fmt.applyPattern("yyyyMMddhhmm");
						 
		HashMap map = new HashMap();
		if(file.isDirectory()||file.isFile())
		{
			map.put("len", file.length()/1024);
			map.put("nam", file.getName());
			map.put("lmt1", fmt.format(new Date(file.lastModified())));
			map.put("lmt2", file.lastModified());
		}
		return map;
	}
	
	/**
	 * ��������ļ���
	 * ������һ���������ŷ���ֵ��һ��Ŀ¼
	 * ����ֵ������
	 * */
	public ArrayList getDirs(ArrayList list,String perpath)
	{
		if(perpath==null||perpath.trim().equals(""))
			;
		else
		{
			File dir = new File(perpath);
			if(dir.isFile()||!dir.exists())
				;
			else
			{
				File[] files = dir.listFiles();
				for (File file : files) {
					if(file.isDirectory())
					{
						list.add(file.getAbsolutePath());
						//System.out.println(file.getPath());
						getDirs(list,file.getAbsolutePath());
					}
					else
						;
				}
			}
		}
		return list;
	}
	
	//�ж��ļ����Ƿ�Ϊ��
	public boolean dirIsEmpty(File dir)
	{//����trueΪ��
		if(dir.isDirectory())
		{
			File[] fs = dir.listFiles();
			if(fs.length==0)
				return true;
		}
		return false;
	}
	
	//ɾ���ļ�,ɾ���ɹ�����true
	public boolean delFiles(File file)
	{
		if(file.isFile())
		{
			return file.delete();
		}
		return false;
	}
	
	//ɾ�����ļ���
	public boolean delEmptyDir(File dir)
	{
		if(dir.isDirectory())
		{
			if(dirIsEmpty(dir))
				return dir.delete();
		}
		return false;
	}
	
	//�����ļ����ļ���
	public ArrayList findFile(ArrayList list,String dir,String name)
	{
		if(dir==null||dir.trim().equals(""))
			;
		else
		{
			File dirFile = new File(dir);
			if(dirFile.isFile()||!dirFile.exists())
				;
			else
			{
				File[] files = dirFile.listFiles();
				for (File file : files) {
					if(file.isFile())
					{
						//System.out.println(file.getPath());
						if(name.equals("")||name == null)
							list.add(file.getAbsolutePath());
						else if(name.equals(file.getName())||name.equals(file.getName().split("\\.")[0]))
						{
							list.add(file.getAbsolutePath());
						}
						else
							;
							
					}
					else if(file.isDirectory())
					{
						findFile(list,file.getAbsolutePath(),name);
					}
					else
						;
				}
			}
		}
		return list;
	}
	//�����ļ����ļ���
	public ArrayList findDir(ArrayList list,String dir,String name)
	{
		if(dir==null||dir.trim().equals(""))
			;
		else
		{
			File dirFile = new File(dir);
			if(dirFile.isFile()||!dirFile.exists())
				;
			else
			{
				File[] files = dirFile.listFiles();
				for (File file : files) {
					if(file.isDirectory())
					{
						if(name.equals("")||name == null)
							list.add(file.getName());
						else if(name.equals(file.getName()))
						{
							//list.clear();
							list.add(file.getAbsolutePath());
						}else
							;
						
						findDir(list,file.getAbsolutePath(),name);
					}
					else
						;
				}
			}
		}
		return list;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String hh = "E:\\���汸��";
		ArrayList list = new ArrayList();
		FindFiles ff = new FindFiles();
		ArrayList lit = ff.getDirs(list,hh);
		System.out.println(lit.size());
		System.out.println(lit);
		
		
		//����
		File f = new File("E:\\�����㱨.pdf");
		System.out.println(ff.getFileInformation(f));
//		ff.fixVerdict(f, "pdf");
	}


}
