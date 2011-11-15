package Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SYSPROPERTIES {

	private String[] audr = {"image","text","audio","video"};
	File file;
	public SYSPROPERTIES(File file) {
		// TODO Auto-generated constructor stub
		this.file = file;
	}
	
	public String getDirectoryName(String id)
	{
		if(id==null||id.equals(""))
			return "";
		
		Properties properties;
		FileInputStream inputFile;
		
		properties = new Properties();
		
		try {
			inputFile = new FileInputStream(file.getAbsolutePath());
			properties.load(inputFile);
			inputFile.close();
			
			for (String str : audr) {
				if(id.equals(str))
				{
					//System.out.println(file.getAbsolutePath());
					return properties.getProperty(str);
				}
			}
		}
		catch(FileNotFoundException e){System.out.println("notFound File");}
		catch(IOException e){System.out.println("File IO error");}
		
		return "";
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
