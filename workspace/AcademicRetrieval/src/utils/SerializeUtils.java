/**
 * 
 */
package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author wanghan
 *
 */
public class SerializeUtils {
	public static void serialize(Object o, String path) throws FileNotFoundException, IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				path, false));
		oos.writeObject(o);
		oos.close();
	}
	
	public static Object deSerialize(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(path));
		Object o=ois.readObject();
		return o;
	}
}
