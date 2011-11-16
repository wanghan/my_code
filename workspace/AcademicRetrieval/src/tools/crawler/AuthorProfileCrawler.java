package tools.crawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableRow;
import org.htmlparser.util.NodeList;

import utils.WebpageDownloader;

public class AuthorProfileCrawler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int k=0;
//			ACTMGlobalData globalData=new ACTMGlobalData();
//			ACTMDataSet data=new ACMCorpusLoader().loadAllData_Small(globalData,null);
//			Thread.sleep(3600*1000);
			System.out.println("run");
			Scanner reader=new Scanner(new File("./aaa.txt"));
			
			AuthorProfileCrawler cc=new AuthorProfileCrawler();
			
			while(reader.hasNext()){
				k++;
				String line=reader.nextLine();
				int index=line.indexOf("\t");
				String id=line.substring(0,index);
				String name=line.substring(index+1);
				String outPath=temp_path+id+".html";
				if(new File(outPath).exists()){
	//				System.out.println("Skip :"+id);
					continue;
				}
				cc.extract(name, id);
				System.out.print(Calendar.getInstance().getTime()+"\t"+k+ "\t");
				if(k%200==0){
					Thread.sleep(1000*60*2);
					System.out.println("run");
				}
			}
			reader.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

	}

	
	private static String save_path="./author_profile/";
	private static String temp_path="./author_temp/";
	
	public void extract(String name, String id){
		
		name=name.replace(" ", "%20");
		
		String url="http://arnetminer.org/viewperson.do?id=-1&name="+name;
		boolean successful=false;
		WebpageDownloader downloader=new WebpageDownloader();
		downloader.setWebAddress(url);
		String outPath=temp_path+id+".html";
		downloader.setDestFile(outPath);
		try {
			trNodes.clear();
			divNodes.clear();
			imageNodes.clear();
			downloader.download();
			Parser parser = new Parser(outPath);
			NodeList nodes = parser.parse(null);
			travelAllImageNodes(nodes);
			travelAllTRNodes(nodes);
			travelAllDivNodes(nodes);

			AuthorProfile profile=new AuthorProfile();
			profile.setId(id);
			for (TableRow tr : trNodes) {
				try {
					if(tr.childAt(1).getFirstChild().getText().startsWith("Statistics")){
						break;
					}
					else if(tr.childAt(1).getFirstChild().getText().startsWith("Position")){
						profile.setPosition(tr.childAt(3).getFirstChild().getText());
					}
					else if(tr.childAt(1).getFirstChild().getText().startsWith("Affiliation")){
						profile.setAffiliation(tr.childAt(3).getFirstChild().getText());
					}
					else if(tr.childAt(1).getFirstChild().getText().startsWith("Address")){
						profile.setAddress(tr.childAt(3).getFirstChild().getText());
					}
					else if(tr.childAt(1).getFirstChild().getText().startsWith("Homepage")){
						profile.setHomepage(((LinkTag)tr.childAt(3).getFirstChild()).extractLink());
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			for (Div div : divNodes) {
				if(div.getAttribute("id")!=null&&div.getAttribute("id").startsWith("interest_")){
					profile.setInterest(div.getFirstChild().getText());
					break;
				}
			}
		
			for (ImageTag image : imageNodes) {
				if(image.getAttribute("class")!=null&&image.getAttribute("class").equals("portray")){
					String imagePath=save_path+id+".jpg";
					WebpageDownloader imaged=new WebpageDownloader();
					imaged.setWebAddress(image.getAttribute("src"));
					imaged.setDestFile(imagePath);
					imaged.download();
					break;
				}
			}
			
			Document xmlDocument = DocumentHelper.createDocument();
			xmlDocument.add(profile.toXMLElement());
			
			FileWriter out = new FileWriter(save_path+id+".xml");
			OutputFormat format=new OutputFormat();
			format.setNewlines(true);
			format.setEncoding("UTF-8");
			XMLWriter  writer=new XMLWriter(out,format);
			writer.write(xmlDocument);
			
			writer.flush();
			writer.close();
			out.close();
			
			successful=true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(successful){
				System.out.println("download Successfully "+name+" "+id);
			}
			else{
				System.err.println("download Unsuccessfully "+name+" "+id);
			}
		}
	}
	
	private Vector<TableRow> trNodes = new Vector<TableRow>();
	private Vector<ImageTag> imageNodes = new Vector<ImageTag>();
	private Vector<Div> divNodes=new Vector<Div>();

	private void travelAllImageNodes(NodeList nodes) {
		for (Node node : nodes.toNodeArray()) {
			if (node instanceof ImageTag) {
				imageNodes.add((ImageTag) node);
			} else if (node.getChildren() != null) {
				travelAllImageNodes(node.getChildren());
			}
		}
	}
	
	private void travelAllDivNodes(NodeList nodes) {
		for (Node node : nodes.toNodeArray()) {
			if (node instanceof Div) {
				divNodes.add((Div) node);
			}
			if (node.getChildren() != null) {
				travelAllDivNodes(node.getChildren());
			}
		}
	}
	
	private void travelAllTRNodes(NodeList nodes) {
		for (Node node : nodes.toNodeArray()) {
			if (node instanceof TableRow) {
				trNodes.add((TableRow) node);
			} else if (node.getChildren() != null) {
				travelAllTRNodes(node.getChildren());
			}
		}
	}
}
