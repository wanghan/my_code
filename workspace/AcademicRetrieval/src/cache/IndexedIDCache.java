/**
 * 
 */
package cache;

import java.io.Serializable;
import java.util.HashSet;

/**
 * @author wanghan
 *
 */
public class IndexedIDCache implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5148077516522375935L;
	HashSet<Integer> paperIDSet;
	HashSet<Long> authorIDSet;
	HashSet<Integer> conferenceIDSet;
	public IndexedIDCache() {
		// TODO Auto-generated constructor stub
		this.authorIDSet=new HashSet<Long>();
		this.conferenceIDSet=new HashSet<Integer>();
		this.paperIDSet=new HashSet<Integer>();
	}
	
	public static String storagePath="index\\ram\\id.cache";
	
	public boolean hasPaperID(int id){
		return paperIDSet.contains(id);
	}
	public boolean hasAuthorID(long id){
		return authorIDSet.contains(id);
	}
	public boolean hasConferenceID(int id){
		return conferenceIDSet.contains(id);
	}
	
	public void insertPaperID(int id){
		paperIDSet.add(id);
	}
	public void insertAuthorID(Long id){
		authorIDSet.add(id);
	}
	public void insertConferenceID(int id){
		conferenceIDSet.add(id);
	}
}
