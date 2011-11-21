/**
 * 
 */
package common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author wanghan
 * 
 */
public class Configuration {

	private Properties p;
	private static Configuration instance=null;
	private Configuration(){
		try {
			String name = "D:/git/my_code/workspace/AcademicSearchWeb/WebRoot/path.properties";
			InputStream in = new BufferedInputStream(new FileInputStream(name));
			p = new Properties();
			p.load(in);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}
	public static Configuration getInstance(){
		if(instance==null){
			instance=new Configuration();
		}
		return instance;
		
	}
	
	public String getProfileImagePath(){
		return instance.p.getProperty("profile_image_path");
	}
}
