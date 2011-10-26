/**
 * 
 */
package actm.data;

import java.io.Serializable;
import java.util.Set;

import org.dom4j.Element;
import org.dom4j.tree.BaseElement;

import actm.data.base.BaseAuthor;

/**
 * @author wanghan
 *
 */
public class Author extends BaseAuthor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3909655305564996285L;
	
	public double[] topicWeight;
	
	public Author(String name, String link) {
		// TODO Auto-generated constructor stub
		setLink(link);
		setName(name);
	}
	
	public Author() {
		// TODO Auto-generated constructor stub
	
	}
//
//	public String getAcmIndex() {
//		if(acmIndex==null){
//			int index1=link.indexOf("id=");
//			int index2=link.indexOf("&");
//			acmIndex=link.substring(index1+3,index2);
//			return acmIndex;
//		}
//		else{
//			return acmIndex;
//		}
//	}
//	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName();
	}
	
	public Element toXMLElement(){
		Element root=new BaseElement("author");
		Element titleNode=root.addElement("name");
		titleNode.addText(this.getName());
		
		Element confNode=root.addElement("link");
		confNode.addText(this.getLink());
		
		return root;
	}
}
