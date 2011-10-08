/**
 * 
 */
package atm.model;

import java.io.Serializable;

/**
 * @author wanghan
 *
 */
public class Author implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7880997842904847082L;
	public int Index;
	public String AuthorName;
	
	public Author() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return AuthorName;
	}
}
