/**
 * 
 */
package tools.crawler;

import org.dom4j.Element;
import org.dom4j.tree.BaseElement;

/**
 * @author wanghan
 * 
 */
public class AuthorProfile {
	private String Id;
	private String Position;
	private String Affiliation;
	private String Address;
	private String Homepage;
	private String Interest;
	
	
	public String getInterest() {
		return Interest;
	}

	public void setInterest(String interest) {
		Interest = interest;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public String getAffiliation() {
		return Affiliation;
	}

	public void setAffiliation(String affiliation) {
		Affiliation = affiliation;
	}

	public String getHomepage() {
		return Homepage;
	}

	public void setHomepage(String homepage) {
		Homepage = homepage;
	}

	public Element toXMLElement() {
		Element root = new BaseElement("author");

		Element node0 = root.addElement("Id");
		if (this.getId() != null) {
			node0.addText(this.getId());
		} else {
			node0.addText("");
		}

		Element node1 = root.addElement("Position");
		if (this.getPosition() != null) {
			node1.addText(this.getPosition());
		} else {
			node1.addText("");
		}
		Element node2 = root.addElement("Affiliation");
		if (this.getAffiliation() != null) {
			node2.addText(this.getAffiliation());
		} else {
			node2.addText("");
		}
		Element node3 = root.addElement("Address");
		if (this.getAddress() != null) {
			node3.addText(this.getAddress());
		} else {
			node3.addText("");
		}
		Element node4 = root.addElement("Homepage");
		if (this.getHomepage() != null) {
			node4.addText(this.getHomepage());
		} else {
			node4.addText("");
		}
		
		Element node5 = root.addElement("Interest");
		if (this.getInterest() != null) {
			node5.addText(this.getInterest());
		} else {
			node5.addText("");
		}
		return root;
	}
	
	public static AuthorProfile xmlToInstance(Element element){
		AuthorProfile result=new AuthorProfile();
		result.setAddress(element.elementText("Address").trim());
		result.setAffiliation(element.elementText("Affiliation").trim());
		result.setHomepage(element.elementText("Homepage").trim());
		result.setId(element.elementText("Id").trim());
		result.setInterest(element.elementText("Interest").trim());
		result.setPosition(element.elementText("Position").trim());
		return result;
	}
}
