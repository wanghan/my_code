/**
 * 
 */
package actm.data;

import org.dom4j.Element;
import org.dom4j.tree.BaseElement;

import actm.data.base.BaseAuthor;

/**
 * @author wanghan
 *
 */
public class Author extends BaseAuthor{

	public double[] topicWeight;
	
	public Author(String name, String link) {
		// TODO Auto-generated constructor stub
		setLink(link);
		setName(name);
	}
	
	public Author() {
		// TODO Auto-generated constructor stub
	
	}

	public String ParseAcmIndex() {
		
		int index1=getLink().indexOf("id=");
		int index2=getLink().indexOf("&");
		String result=getLink().substring(index1+3,index2);
		return result;
		
	}
	

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
