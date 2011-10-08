/**
 * 
 */
package actm.data;

import java.io.Serializable;

/**
 * @author wanghan
 *
 */
public class Author implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3909655305564996285L;
	private String name;
	private String link;
	private String acmIndex;
	private int index;
	
	public Author(String name, String link) {
		// TODO Auto-generated constructor stub
		this.link=link;
		this.name=name;
		acmIndex=null;
	}
	
	public Author() {
		// TODO Auto-generated constructor stub
		acmIndex=null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getAcmIndex() {
		if(acmIndex==null){
			int index1=link.indexOf("id=");
			int index2=link.indexOf("&");
			acmIndex=link.substring(index1+3,index2);
			return acmIndex;
		}
		else{
			return acmIndex;
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
}
