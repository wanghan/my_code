/**
 * 
 */
package atm.corpus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import atm.model.ATMDataSet;
import atm.model.ATMDocument;


/**
 * @author wanghan
 * 
 */
public class NIPSCorpusNormalizer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
	//		TMDocument doc=new NIPSCorpusNormalizer().xmlToDocument(NIPS_CORPUS_PATH+"1.xml");
	//		System.out.println(doc.Contents);
			
			new NIPSCorpusNormalizer().normalize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private ATMDataSet dataSet;
	private static String NIPS_CORPUS_PATH = "NIPSCorpus/";

	public NIPSCorpusNormalizer() {
		
	}

	public void normalize() {
		NIPSCorpusLoader corpus = new NIPSCorpusLoader();
		corpus.SHOW_DEBUG_INFO = true;
		dataSet = corpus.Load(0, 12);
		
		System.out.println(dataSet.documentSet.size());
		for (ATMDocument doc : dataSet.documentSet) {
			try {
				documentToXML(doc);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println("Write to XML error: " + doc.Title);
			}
		}
	}

	public ATMDocument xmlToDocument(String path) throws DocumentException{
		
		ATMDocument result=new ATMDocument();
		
		SAXReader reader = new SAXReader();
	    Document document = reader.read(new File(path));
	    Element root= document.getRootElement();
	    result.GlobalIndex=Integer.parseInt(root.element("index").getStringValue());
	    result.Year=Integer.parseInt(root.element("year").getStringValue());
	    result.Title=root.elementText("title");
	    result.Contents=root.elementText("description");
	    
	    
	    return result;
	}
	
	private void documentToXML(ATMDocument doc) throws IOException {

		System.out.println("XMLing "+doc.Title);
		
		Document xmlDocument = DocumentHelper.createDocument();
		

		Element root = xmlDocument.addElement("metadata");
		Element indexNode = root.addElement("index");
		indexNode.addText(String.valueOf(doc.GlobalIndex));

		Element yearNode = root.addElement("year");
		yearNode.addText(String.valueOf(doc.Year));

		Element titleNode = root.addElement("title");
		titleNode.addText(doc.Title);

	
		Element descriptionNode = root.addElement("description");
		descriptionNode.addText(doc.Contents);

		FileWriter out = new FileWriter(NIPS_CORPUS_PATH + doc.GlobalIndex + ".xml");
		OutputFormat format=new OutputFormat();
		format.setEncoding("GB2312");
		XMLWriter  writer=new XMLWriter(out,format);
		writer.write(xmlDocument);
		
		writer.flush();
		writer.close();
		out.close();
	}
}
