/**
 * 
 */
package rmi;

import java.rmi.Naming;

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
			
//			testSearchPaper(searcher);
//			testSearchAuthor(searcher);
			
			testAssociateSearchAuthor(searcher);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	public static void testAssociateSearchAuthor(SearcherRMIInterface searcher) throws Exception{
		
		int id=105;
		

		AssociateResult result[]=searcher.getAssociatePapers(id);
		for (AssociateResult associateResult : result) {
			System.out.println(associateResult.title);
		}
		
	}
	public static void testSearchAuthor(SearcherRMIInterface searcher) throws Exception{
//		Author []result1=searcher.searchAuthors("data mining");
//		Document xmlDocument = DocumentHelper.createDocument();
//		Element root = xmlDocument.addElement("result");
//		
//		for (Author i : result1) {
//			if(i!=null)
//			root.add(i.toXMLElement());
//		}
//		
////		OutputStreamWriter out=new   OutputStreamWriter(new   FileOutputStream( "metadata/"+conference.getName()+".xml"), "UTF-8");
//		
//		FileWriter out = new FileWriter("./author_result.xml");
//		OutputFormat format=new OutputFormat();
//		format.setNewlines(true);
//		format.setEncoding("UTF-8");
//		XMLWriter  writer=new XMLWriter(out,format);
//		writer.write(xmlDocument);
//		
//		writer.flush();
//		writer.close();
//		out.close();
	}
	
	public static void testSearchPaper(SearcherRMIInterface searcher) throws Exception{
//		Paper []result=searcher.searchPapers("image retrieval");
//		
//		Document xmlDocument = DocumentHelper.createDocument();
//		Element root = xmlDocument.addElement("result");
//		for (Paper i : result) {
//			if(i!=null)
//				root.add(i.toXMLElement());
//		}
//		
////		OutputStreamWriter out=new   OutputStreamWriter(new   FileOutputStream( "metadata/"+conference.getName()+".xml"), "UTF-8");
//		
//		FileWriter out = new FileWriter("./paper_result.xml");
//		OutputFormat format=new OutputFormat();
//		format.setNewlines(true);
//		format.setEncoding("UTF-8");
//		XMLWriter  writer=new XMLWriter(out,format);
//		writer.write(xmlDocument);
//		
//		writer.flush();
//		writer.close();
//		out.close();
	}
}
