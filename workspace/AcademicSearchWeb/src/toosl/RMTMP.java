package toosl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class RMTMP {

	FindFiles ffs = new FindFiles();
	String[] Dirs = {"fileAudio","fileText","fileVideo","fileImage"};
	public RMTMP() {
		// TODO Auto-generated constructor stub
	}
	
	public long nowDate()
	{
		return  System.currentTimeMillis();
	}
	
	public long parseLong(String dat)
	{
		return Long.valueOf(dat).longValue();
	}
	
	public String getPath()
	{
		File file = new File(".");
		String path = "";
		try {
			path = file.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	public boolean delFile(String dir,String fix)
	{
		ArrayList list = ffs.getFiles(new ArrayList(), dir ,fix);
		for (Object object : list) {
			String tmp = object.toString();
			File tmpf = new File(tmp);
			HashMap map = ffs.getFileInformation(tmpf);
			
			long now = this.nowDate();
			long fct = this.parseLong(map.get("lmt2").toString());
			
			if(tmpf.isFile() && (now - fct > 1000*60*60))
			{
				return ffs.delFiles(tmpf);
			}
		}
		return false;
	}
	public boolean delDir(String dir)
	{
		ArrayList list = ffs.getDirs(new ArrayList(), dir);
		for (Object object : list) {
			String tmp = object.toString();
			File tmpf = new File(tmp);
			if (tmpf.isDirectory()) {
				return ffs.delEmptyDir(tmpf);
			}
		}
		return false;
	}
	
	public void go(int n)
	{
		String path = this.getPath();
		ArrayList thePath = new ArrayList();
		for (String str : Dirs) {
			thePath.add(ffs.findDir(new ArrayList(), path, str));
		}
		
		switch (n) {
		case 0:
		{
			for (Object object : thePath) {
				this.delFile(object.toString(),"");	//mp3
			}
			break;
		}
		case 1:
		{
			for (Object object : thePath) {
				this.delDir(object.toString());
			}
			break;
		}
		default:
			break;
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final RMTMP mp = new RMTMP();
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				Date date = new Date();
				SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhh", Locale.SIMPLIFIED_CHINESE);
				//2011 01 11 04
				String nowstr = fmt.format(date);
				if(nowstr.substring(8, 10).equals("00"))
					mp.go(0);
				else if(nowstr.substring(6, 8).equals("00"))
					mp.go(1);
				else
					;
			}
		},0,1000);
	}



}
