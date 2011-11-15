package AUDRwebJavaBeans;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class FilePro {

	final String fix = ".avi";
	
	public FilePro() {
		// TODO Auto-generated constructor stub
	}
	
	public File updateFileName(File file)
	{
		if(file==null)
			return null;
		
		Date date = new Date();
		SimpleDateFormat fmt=new SimpleDateFormat("yyyyMMddhhmmsss");
		String logName = fmt.format(date)+".log";
		
		String path = file.getParent()+"\\";
		
		String oldName = file.getName();
		String newName = fmt.format(date)+fix;
		
		System.out.println("path:"+path);
		WriteLog(path+logName,oldName,newName);
		
		File newfile = new File(path+newName);
			file.renameTo(newfile);
			//file.delete();
		
		return newfile;
	}
	
	private void WriteLog(String logPname,String oldVname,String newVname)
	{
		File file = new File(logPname);
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		
		String text = oldVname+"\r\n"+newVname;
		try {
			FileWriter   fw = new   FileWriter(file.getPath(),true);
			fw.write(text);
			fw.flush();
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private boolean delFile(File file)
	{
		if(!file.exists())
			return false;

		return file.delete();
	}
	
	private void delFiles(String path)
	{
		File dir = new File(path);
		if(!dir.isDirectory())
			;
		else
		{
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				if(files[i].isFile())
					delFile(files[i]);
				else
					delFiles(files[i].getPath());
			}
		}
	}
	
	
	String path;
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	Timer t = new Timer();
	public void execute()
	{
		SimpleDateFormat fmtm=new SimpleDateFormat("yyyyMMdd");	
		SimpleDateFormat fmtd=new SimpleDateFormat("yyyyMMddhh");	
		Date date = new Date();
		final String str1 = fmtm.format(date);	//文件名前8位
		final String str2 = fmtd.format(date);	//文件名前10位
		
		TimerTask tt = new TimerTask()
		{
		   public void run()
		   {
			  
			  File dir = new File(path);
			  File[] files = dir.listFiles();
			  for (File file : files) {
				String tmp1 = file.getName().substring(0,8);
				String tmp2 = file.getName().substring(0,10);
				if(str1.equals(tmp1))
				{
					if(str2.equals(tmp2))
					{
						delFile(file);
					}
				}
			}
		   }
		};
		t.scheduleAtFixedRate(tt,0,10000);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FilePro fp = new FilePro();
		String path = "D:\\Work\\AUDRweb\\WebRoot\\0300010000000085400";
		//new FilePro().delFiles(path);
		fp.setPath(path);
		fp.execute();
	}

	

}
