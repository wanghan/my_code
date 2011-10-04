/**
 * 
 */
package utils;

/**
 * @author wanghan
 *
 */
public class NumberUtils {
	
	public static boolean IsInteger(String s){
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
}
