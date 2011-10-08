/**
 * 
 */
package actm.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import actm.data.ACTMDataSet;
import actm.data.Paper;

/**
 * @author wanghan
 *
 */
public class OnlineTrainingListGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			genOnlineTrainingList("ACMDataList_small1.txt","ACTOnlineTraining_small1.txt");
		//	OnlineTrainingListGenerator.genTrainingAndTestData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void genOnlineTrainingList(String filein, String fileout) throws IOException{
		Scanner scanner=new Scanner(new File(filein));
		FileWriter writer=new FileWriter(new File(fileout));
		int i=0;
		TreeMap<Integer, ArrayList<String>> map=new  TreeMap<Integer, ArrayList<String>>();
		while(scanner.hasNext()){
			String line=scanner.nextLine();
			StringTokenizer st=new StringTokenizer(line," ");
			String firstToken=st.nextToken();

			int year=Integer.parseInt(firstToken);
			int month=Integer.parseInt(st.nextToken());
			ArrayList<String> list=map.get(year);
			if(list==null){
				list=new ArrayList<String>();
				list.add(line);
				map.put(year, list);
			}
			else{
				list.add(line);
			}
		}
		for (Integer key : map.keySet()) {
			ArrayList<String> lll=map.get(key);
			int slide=key-2000;
			writer.write(slide+" "+lll.size()+"\r\n");
			for (String string : lll) {
				writer.write(string+"\r\n");
			}
			writer.flush();
		}
		writer.close();
		scanner.close();
	}
	
	public static void genTrainingAndTestData() throws DocumentException, IOException{
		
		double selectionPercentage=0.1;
		
		Scanner scanner=new Scanner(new File("ACTOnlineTraining_small1.txt"));
		int trainCount=0, testCount=0;
		while(scanner.hasNext()){
			String line=scanner.nextLine();
			StringTokenizer st=new StringTokenizer(line," ");
			int slide=Integer.parseInt(st.nextToken());
			int count=Integer.parseInt(st.nextToken());
			
			
			
			ACTMDataSet dataset=new ACTMDataSet();
			
			for(int i=0;i<count;++i){
				line=scanner.nextLine();
				st=new StringTokenizer(line," ");
				String firstToken=st.nextToken();

				int year=Integer.parseInt(firstToken);
				int month=Integer.parseInt(st.nextToken());
				String confName=st.nextToken().trim();
				
				
				SAXReader reader=new SAXReader();
				
				String metadataPath="metadata/"+confName+".xml";
				
				String trainingDataPath="training_small1/"+confName+".xml";
				String testingDataPath="testing_small1/"+confName+".xml";
				Document trainingXmlDocument = DocumentHelper.createDocument();
				Element trainingRoot = trainingXmlDocument.addElement("metadata");
				Document testingXmlDocument = DocumentHelper.createDocument();
				Element testingRoot = testingXmlDocument.addElement("metadata");
				
		//		System.out.println(confName);
				Document xmldoc=reader.read(new File(metadataPath));
		//		System.out.println(metadataPath);
				Element root1=xmldoc.getRootElement();
				for (Object elem : root1.elements()) {
					Paper ppp=Paper.xmlToInstance((Element)elem);
					double random=Math.random();
					if(random>selectionPercentage){
						trainingRoot.add(ppp.toXMLElement());
						trainCount++;
					}
					else{
						testingRoot.add(ppp.toXMLElement());
						testCount++;
					}
				}
				
				FileWriter out = new FileWriter(trainingDataPath);
				OutputFormat format=new OutputFormat();
				format.setNewlines(true);
				format.setEncoding("UTF-8");
				XMLWriter  writer=new XMLWriter(out,format);
				writer.write(trainingRoot);
				
				writer.flush();
				writer.close();
				out.close();
				
				out = new FileWriter(testingDataPath);
				writer=new XMLWriter(out,format);
				writer.write(testingXmlDocument);
				
				writer.flush();
				writer.close();
				out.close();
			}

		}
		System.out.println("training set size"+trainCount);
		System.out.println("testing set size"+testCount);
	}
}
