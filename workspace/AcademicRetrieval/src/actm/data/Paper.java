package actm.data;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.dom4j.Element;
import org.dom4j.tree.BaseElement;

import actm.data.base.BasePaper;

/**
 * 
 */

/**
 * @author wanghan
 *
 */
public class Paper extends BasePaper{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1112339929366144895L;
	public double[] topicWeight;
	
	public Paper() {
		// TODO Auto-generated constructor stub
		this.setDoi("");
		this.setDoiLink("");
	}

	
	public Element toXMLElement(){
		Element root=new BaseElement("article");
//		Element idNode=root.addElement("id");
//		idNode.addText(String.valueOf(this.id));
		
		Element titleNode=root.addElement("title");
		titleNode.addText(this.getTitle());
		
//		Element confNode=root.addElement("conference");
//		confNode.addText(this.conference.getName());
//		confNode.addAttribute("date", conference.getDate().toString());
		
		Element pageNode=root.addElement("page");
		pageNode.addText(this.getPages());
		
//		Element authorNode=root.addElement("authors");
//		for (Author author : this.authors) {
//			Element temp=authorNode.addElement("author");
//			temp.addText(author.getName());
//			temp.addAttribute("link", author.getLink());
//		}
		
		Element sourceNode=root.addElement("source");
		sourceNode.addText(this.getSource());
		
		Element linkNode=root.addElement("link");
		linkNode.addText(this.getLink());
		
		Element doiNode=root.addElement("doi");
		doiNode.addText(this.getDoi());
//		doiNode.addAttribute("link", this.doiLink);
		
		Element absNode=root.addElement("abstract");
		absNode.addText(this.getAbstract());
		
		return root;
	}
	
	public static Paper xmlToInstance(Element element){
		Paper result=new Paper();
		result.setTitle(element.elementText("title").trim());
		result.setDoi(element.elementText("doi").trim());
		result.setDoiLink(element.element("doi").attributeValue("link").trim());
		result.setLink(element.elementText("link").trim());
		result.setSource(element.elementText("source").trim());
		result.setPages(element.elementText("page").trim());
		Element authorsNode=element.element("authors");
		Set<Author> authors=new HashSet<Author>();
		for (Object authorNode : authorsNode.elements()) {
			Author author=new Author();
			author.setLink(((Element)authorNode).attributeValue("link"));
			author.setName(((Element)authorNode).getTextTrim());
			authors.add(author);
		}
		result.setAuthors(authors);
		
		Conference conf=new Conference();
		conf.setDate(new Date(Date.parse(element.element("conference").attributeValue("date"))));
		conf.setName(element.elementText("conference").trim());
		
		result.setConference(conf);
		result.setAbstract(element.elementText("abstract").trim());
		return result;
	}
}
