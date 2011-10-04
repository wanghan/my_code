/**
 * 
 */
package act.retrieval;

import org.apache.lucene.store.FSDirectory;

/**
 * @author wanghan
 *
 */
public class IndexUtils {
	
	public static boolean checkIndexExist(FSDirectory dir){
		if(dir.getFile().list().length>0){
			return true;
		}
		else{
			return false;
		}
	}
}
