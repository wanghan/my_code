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
	 * 获得所有文件
	 * 参数：一个空链表存放返回值，一个目录，文件后缀
	 * 返回值：链表
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
	
	//判断文件类型
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
	 * 获得文件的创建信息
	 * 参数：
	 * 返回值：HashMap{文件大小，文件名，最后修改时间格式1，最后修改时间格式2}；
	 * 返回值：HashMap{len，nam，lmt1,lmt2}；
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
	 * 获得所有文件夹
	 * 参数：一个空链表存放返回值，一个目录
	 * 返回值：链表
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
	
	//判断文件夹是否为空
	public boolean dirIsEmpty(File dir)
	{//返回true为空
		if(dir.isDirectory())
		{
			File[] fs = dir.listFiles();
			if(fs.length==0)
				return true;
		}
		return false;
	}
	
	//删除文件,删除成功返回true
	public boolean delFiles(File file)
	{
		if(file.isFile())
		{
			return file.delete();
		}
		return false;
	}
	
	//删除空文件夹
	public boolean delEmptyDir(File dir)
	{
		if(dir.isDirectory())
		{
			if(dirIsEmpty(dir))
				return dir.delete();
		}
		return false;
	}
	
	//查找文件用文件名
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
	//查找文件用文件夹
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
		String hh = "E:\\桌面备份";
		ArrayList list = new ArrayList();
		FindFiles ff = new FindFiles();
		ArrayList lit = ff.getDirs(list,hh);
		System.out.println(lit.size());
		System.out.println(lit);
		
		
		//测试
		File f = new File("E:\\工作汇报.pdf");
		System.out.println(ff.getFileInformation(f));
//		ff.fixVerdict(f, "pdf");
	}


}
