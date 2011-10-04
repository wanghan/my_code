/**
 * 
 */
package rmi;

import java.io.FileWriter;
import java.rmi.Naming;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import actm.data.Author;
import actm.data.Paper;

/**
 * @author wanghan
 *
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			
			SearcherRMIInterface searcher=(SearcherRMIInterface)Naming.lookup("//localhost:1099/searcher");
			
			testSearchPaper(searcher);
			testSearchAuthor(searcher);
			
			testAssociateSearchAuthor(searcher);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	public static void testAssociateSearchAuthor(SearcherRMIInterface searcher) throws Exception{
		
		int id=100;
		
		Document xmlDocument = DocumentHelper.createDocument();
		Element root = xmlDocument.addElement("result");
		String title=searcher.getPaperTitleById(id);
		Element id1Node=root.addElement("query");
		id1Node.addText(title);

		AssociateResult result[]=searcher.getAssociatePapers(id);
		for (AssociateResult associateResult : result) {
			Element idNode=root.addElement("title");
			idNode.addText(String.valueOf(associateResult.title));
			for (Paper list : associateResult.list) {
				idNode.add(list.toXMLElement());
			}
		}
		
		FileWriter out = new FileWriter("./associate_paper_result.xml");
		OutputFormat format=new OutputFormat();
		format.setNewlines(true);
		format.setEncoding("UTF-8");
		XMLWriter  writer=new XMLWriter(out,format);
		writer.write(xmlDocument);
		
		writer.flush();
		writer.close();
		out.close();
	}
	public static void testSearchAuthor(SearcherRMIInterface searcher) throws Exception{
		Author []result1=searcher.searchAuthors("data mining");
		Document xmlDocument = DocumentHelper.createDocument();
		Element root = xmlDocument.addElement("result");
		
		for (Author i : result1) {
			root.add(i.toXMLElement());
		}
		
//		OutputStreamWriter out=new   OutputStreamWriter(new   FileOutputStream( "metadata/"+conference.getName()+".xml"), "UTF-8");
		
		FileWriter out = new FileWriter("./author_result.xml");
		OutputFormat format=new OutputFormat();
		format.setNewlines(true);
		format.setEncoding("UTF-8");
		XMLWriter  writer=new XMLWriter(out,format);
		writer.write(xmlDocument);
		
		writer.flush();
		writer.close();
		out.close();
	}
	
	public static void testSearchPaper(SearcherRMIInterface searcher) throws Exception{
		Paper []result=searcher.searchPapers("image retrieval");
		
		Document xmlDocument = DocumentHelper.createDocument();
		Element root = xmlDocument.addElement("result");
		for (Paper i : result) {
			root.add(i.toXMLElement());
		}
		
//		OutputStreamWriter out=new   OutputStreamWriter(new   FileOutputStream( "metadata/"+conference.getName()+".xml"), "UTF-8");
		
		FileWriter out = new FileWriter("./paper_result.xml");
		OutputFormat format=new OutputFormat();
		format.setNewlines(true);
		format.setEncoding("UTF-8");
		XMLWriter  writer=new XMLWriter(out,format);
		writer.write(xmlDocument);
		
		writer.flush();
		writer.close();
		out.close();
	}
}
