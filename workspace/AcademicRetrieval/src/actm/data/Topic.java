/**
 * 
 */
package actm.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author wanghan
 *
 */
public class Topic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9182892280613547135L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public int index;
	public ArrayList<String> tags;
	public ArrayList<Integer> topRelatedPaperIds;
	public ArrayList<Integer> topRelatedAuthorIds;
	public ArrayList<Integer> topRelatedConferenceIds;
	public ArrayList<Integer> topRelatedWordIds;
	
	public Topic() {
		// TODO Auto-generated constructor stub
		this.topRelatedAuthorIds=new ArrayList<Integer>();
		this.topRelatedConferenceIds=new ArrayList<Integer>();
		this.topRelatedPaperIds=new ArrayList<Integer>();
		this.topRelatedWordIds=new ArrayList<Integer>();
	}
	
}
