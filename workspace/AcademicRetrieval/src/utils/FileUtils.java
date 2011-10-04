/**
 * 
 */
package utils;

/**
 * @author wanghan
 *
 */
public class FileUtils {
	public static String getFileSuffix(java.io.File file){
		int index=file.getName().lastIndexOf(".");
		if(index>=0){
			return file.getName().substring(index+1);
		}
		else{
			return "";
		}
	}
}
