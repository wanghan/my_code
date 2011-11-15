package sys;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	public static String DEFAULT_HQ_IP = "127.0.0.1";	  
	public static String DEFAULT_FILE_PATH = "./ServerIP.properties";
	
	private static Configuration instance = new Configuration();
	
	private Configuration() { };
	
	public static Configuration GetInstance()
	{
	    return instance;
	}
	
	public static String configFilePath;
	
	public static void setConfigFilePath(String configFilePath) {
		Configuration.configFilePath = configFilePath;
	}

	public static String SetIP()
	{
		String config = configFilePath;
		
		if(config==null||config.equals(""))
			config = DEFAULT_FILE_PATH;
		
		File file = new File(config);
		if(!file.exists())
			config = DEFAULT_FILE_PATH;
		
		FileInputStream input = null;
		String HQ_IP = null;
		try{		
			input = new FileInputStream(config);
			Properties prop = new Properties();
			prop.load(input);
			HQ_IP = (String) prop.get("HQ_IP");
		}
		catch (IOException e){
			e.printStackTrace();
			return DEFAULT_HQ_IP;
		}
		
		System.out.println("HQ_IP:"+HQ_IP);
		return HQ_IP;
	}

	private static String getConfigFilePath() {
		// TODO Auto-generated method stub
		return null;
	}

}
