/**
 * 
 */
package act.corpus;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import actm.data.ACTMDataSet;
import actm.data.ACTMGlobalData;
import actm.data.Paper;

/**
 * @author wanghan
 *
 */
public class ACMCorpusLoader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ACTMGlobalData globalData=new ACTMGlobalData();
			ACTMDataSet datas=new ACMCorpusLoader().loadTrainData_Small(globalData,null);
			
			System.out.println(1);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public ACTMDataSet loadTrainData(ACTMGlobalData data) throws DocumentException, FileNotFoundException{
		
		ACTMDataSet dataset=new ACTMDataSet();
		
		
		
		Scanner scanner=new Scanner(new File("ACMDataList.txt"));
		int i=0;
		while(scanner.hasNext()){
			String line=scanner.nextLine();
			StringTokenizer st=new StringTokenizer(line," ");
			String firstToken=st.nextToken();

			int year=Integer.parseInt(firstToken);
			int month=Integer.parseInt(st.nextToken());
			String confName=st.nextToken().trim();
			
			SAXReader reader=new SAXReader();
			String metadataPath="training/"+confName+".xml";
	//		System.out.println(confName);
			Document xmldoc=reader.read(new File(metadataPath));
			Element root1=xmldoc.getRootElement();
			for (Object elem : root1.elements()) {
				Paper ppp=Paper.xmlToInstance((Element)elem);
				dataset.insertPaper(ppp,data);
				i+=ppp.getAuthors().size();
			}
		}
		return dataset;
	}
	
	public ACTMDataSet loadAllData_Small(ACTMGlobalData data,HashSet<String> ngrams) throws DocumentException, FileNotFoundException{
		int randomDIO=0;
		
		HashSet<String> dios=new HashSet<String>();
		
		ACTMDataSet dataset=new ACTMDataSet();
		
		Scanner scanner=new Scanner(new File("ACMDataList_small1.txt"));
		int i=0;
		while(scanner.hasNext()){
			String line=scanner.nextLine();
			StringTokenizer st=new StringTokenizer(line," ");
			String firstToken=st.nextToken();

			int year=Integer.parseInt(firstToken);
			int month=Integer.parseInt(st.nextToken());
			String confName=st.nextToken().trim();
			
			SAXReader reader=new SAXReader();
			String metadataPath="training_small1/"+confName+".xml";
	//		System.out.println(confName);
			Document xmldoc=reader.read(new File(metadataPath));
			Element root1=xmldoc.getRootElement();
			if(ngrams==null){
				for (Object elem : root1.elements()) {
					Paper ppp=Paper.xmlToInstance((Element)elem);
					if(ppp.getDoi()==null||ppp.getDoi().equals("")){
						ppp.setDoi(System.currentTimeMillis()+String.valueOf(randomDIO++));
					}
					dataset.insertPaper(ppp,data);
					i+=ppp.getAuthors().size();
				}
			}
			else{
				for (Object elem : root1.elements()) {
					Paper ppp=Paper.xmlToInstance((Element)elem);
					if(ppp.getDoi()==null||ppp.getDoi().equals("")){
						ppp.setDoi(System.currentTimeMillis()+String.valueOf(randomDIO++));
					}
					dataset.insertPaperWithNGrams(ppp,data,ngrams);
					i+=ppp.getAuthors().size();
				}
			}
		}
		scanner.close();
		scanner=new Scanner(new File("ACMDataList_small1.txt"));
		i=0;
		while(scanner.hasNext()){
			String line=scanner.nextLine();
			StringTokenizer st=new StringTokenizer(line," ");
			String firstToken=st.nextToken();
			int year=Integer.parseInt(firstToken);
			int month=Integer.parseInt(st.nextToken());
			String confName=st.nextToken().trim();
			
			SAXReader reader=new SAXReader();
			String metadataPath="testing_small1/"+confName+".xml";
	//		System.out.println(confName);
			Document xmldoc=reader.read(new File(metadataPath));
			Element root1=xmldoc.getRootElement();
			if(ngrams==null){
				for (Object elem : root1.elements()) {
					Paper ppp=Paper.xmlToInstance((Element)elem);
					dataset.insertPaper(ppp,data);
					i+=ppp.getAuthors().size();
				}
			}
			else{
				for (Object elem : root1.elements()) {
					Paper ppp=Paper.xmlToInstance((Element)elem);
					dataset.insertPaperWithNGrams(ppp,data,ngrams);
					i+=ppp.getAuthors().size();
				}
			}
		}
		scanner.close();
		return dataset;
		
	}
	
	public ACTMDataSet loadTrainData_Small(ACTMGlobalData data,HashSet<String> ngrams) throws DocumentException, FileNotFoundException{
		
		int randomDIO=0;
		
		HashSet<String> dios=new HashSet<String>();
		
		ACTMDataSet dataset=new ACTMDataSet();
		
		Scanner scanner=new Scanner(new File("ACMDataList_small1.txt"));
		int i=0;
		while(scanner.hasNext()){
			String line=scanner.nextLine();
			StringTokenizer st=new StringTokenizer(line," ");
			String firstToken=st.nextToken();

			int year=Integer.parseInt(firstToken);
			int month=Integer.parseInt(st.nextToken());
			String confName=st.nextToken().trim();
			
			SAXReader reader=new SAXReader();
			String metadataPath="training_small1/"+confName+".xml";
	//		System.out.println(confName);
			Document xmldoc=reader.read(new File(metadataPath));
			Element root1=xmldoc.getRootElement();
			if(ngrams==null){
				for (Object elem : root1.elements()) {
					Paper ppp=Paper.xmlToInstance((Element)elem);
					if(ppp.getDoi()==null||ppp.getDoi().equals("")){
						ppp.setDoi(System.currentTimeMillis()+String.valueOf(randomDIO++));
					}
					dataset.insertPaper(ppp,data);
					i+=ppp.getAuthors().size();
				}
			}
			else{
				for (Object elem : root1.elements()) {
					Paper ppp=Paper.xmlToInstance((Element)elem);
					if(ppp.getDoi()==null||ppp.getDoi().equals("")){
						ppp.setDoi(System.currentTimeMillis()+String.valueOf(randomDIO++));
					}
					dataset.insertPaperWithNGrams(ppp,data,ngrams);
					i+=ppp.getAuthors().size();
				}
			}
		}
		return dataset;
	}
	
	
	
	public ACTMDataSet loadTestData(ACTMGlobalData data) throws DocumentException, FileNotFoundException{
		
		ACTMDataSet dataset=new ACTMDataSet();
		
		Scanner scanner=new Scanner(new File("ACMDataList.txt"));
		int i=0;
		while(scanner.hasNext()){
			String line=scanner.nextLine();
			StringTokenizer st=new StringTokenizer(line," ");
			String firstToken=st.nextToken();

			int year=Integer.parseInt(firstToken);
			int month=Integer.parseInt(st.nextToken());
			String confName=st.nextToken().trim();
			
			SAXReader reader=new SAXReader();
			String metadataPath="testing/"+confName+".xml";
	//		System.out.println(confName);
			Document xmldoc=reader.read(new File(metadataPath));
			Element root1=xmldoc.getRootElement();
			for (Object elem : root1.elements()) {
				Paper ppp=Paper.xmlToInstance((Element)elem);
				dataset.insertPaper(ppp,data);
				i+=ppp.getAuthors().size();
			}
		}
		
		System.out.println("testing data"+ dataset.documentSet);
		
		return dataset;
	} 
	
	public ACTMDataSet loadTestData_Small(ACTMGlobalData data,HashSet<String> ngrams ) throws DocumentException, FileNotFoundException{
		
		ACTMDataSet dataset=new ACTMDataSet();
		
		Scanner scanner=new Scanner(new File("ACMDataList_small1.txt"));
		int i=0;
		while(scanner.hasNext()){
			String line=scanner.nextLine();
			StringTokenizer st=new StringTokenizer(line," ");
			String firstToken=st.nextToken();

			int year=Integer.parseInt(firstToken);
			int month=Integer.parseInt(st.nextToken());
			String confName=st.nextToken().trim();
			
			SAXReader reader=new SAXReader();
			String metadataPath="testing_small1/"+confName+".xml";
	//		System.out.println(confName);
			Document xmldoc=reader.read(new File(metadataPath));
			Element root1=xmldoc.getRootElement();
			if(ngrams==null){
				for (Object elem : root1.elements()) {
					Paper ppp=Paper.xmlToInstance((Element)elem);
					dataset.insertPaper(ppp,data);
					i+=ppp.getAuthors().size();
				}
			}
			else{
				for (Object elem : root1.elements()) {
					Paper ppp=Paper.xmlToInstance((Element)elem);
					dataset.insertPaperWithNGrams(ppp,data,ngrams);
					i+=ppp.getAuthors().size();
				}
			}
		}
		
		
		return dataset;
	} 
	
	public HashMap<Integer, ArrayList<Paper>> loadOnlineTestData(ACTMGlobalData data) throws DocumentException, FileNotFoundException{
		
		HashMap<Integer, ArrayList<Paper>> slidePapersMap=new HashMap<Integer, ArrayList<Paper>>();
		
		Scanner scanner=new Scanner(new File("ACTMOnlineTraining1.txt"));
	
		while(scanner.hasNext()){
			String line=scanner.nextLine();
			StringTokenizer st=new StringTokenizer(line," ");
			int slide=Integer.parseInt(st.nextToken());
			int count=Integer.parseInt(st.nextToken());
			
			ArrayList<Paper> paperList=slidePapersMap.get(slide);
			if(paperList==null){
				paperList=new ArrayList<Paper>();
				slidePapersMap.put(slide, paperList);
			}
			
			ACTMDataSet dataset=new ACTMDataSet();
			
			for(int i=0;i<count;++i){
				line=scanner.nextLine();
				st=new StringTokenizer(line," ");
				String firstToken=st.nextToken();

				int year=Integer.parseInt(firstToken);
				int month=Integer.parseInt(st.nextToken());
				String confName=st.nextToken().trim();
				
				
				SAXReader reader=new SAXReader();
				
				String metadataPath="testing/"+confName+".xml";
		//		System.out.println(confName);
				Document xmldoc=reader.read(new File(metadataPath));
		//		System.out.println(metadataPath);
				Element root1=xmldoc.getRootElement();
				for (Object elem : root1.elements()) {
					Paper ppp=Paper.xmlToInstance((Element)elem);
					paperList.add(ppp);
				}
			}

		}
		return slidePapersMap;
	}
	
	public HashMap<Integer, ArrayList<Paper>> loadOnlineTestData_Small() throws DocumentException, FileNotFoundException{
		
		HashMap<Integer, ArrayList<Paper>> slidePapersMap=new HashMap<Integer, ArrayList<Paper>>();
		
		Scanner scanner=new Scanner(new File("ACTOnlineTraining_small1.txt"));
	
		while(scanner.hasNext()){
			String line=scanner.nextLine();
			StringTokenizer st=new StringTokenizer(line," ");
			int slide=Integer.parseInt(st.nextToken());
			int count=Integer.parseInt(st.nextToken());
			
			ArrayList<Paper> paperList=slidePapersMap.get(slide);
			if(paperList==null){
				paperList=new ArrayList<Paper>();
				slidePapersMap.put(slide, paperList);
			}
			
			ACTMDataSet dataset=new ACTMDataSet();
			
			for(int i=0;i<count;++i){
				line=scanner.nextLine();
				st=new StringTokenizer(line," ");
				String firstToken=st.nextToken();

				int year=Integer.parseInt(firstToken);
				int month=Integer.parseInt(st.nextToken());
				String confName=st.nextToken().trim();
				
				
				SAXReader reader=new SAXReader();
				
				String metadataPath="testing_small1/"+confName+".xml";
		//		System.out.println(confName);
				Document xmldoc=reader.read(new File(metadataPath));
		//		System.out.println(metadataPath);
				Element root1=xmldoc.getRootElement();
				for (Object elem : root1.elements()) {
					Paper ppp=Paper.xmlToInstance((Element)elem);
					paperList.add(ppp);
				}
			}

		}
		return slidePapersMap;
	}
	
	
	public HashMap<Integer, ArrayList<Paper>> loadOnlineTrainingData(ACTMGlobalData data) throws DocumentException, FileNotFoundException{
		
		HashMap<Integer, ArrayList<Paper>> slidePapersMap=new HashMap<Integer, ArrayList<Paper>>();
		
		Scanner scanner=new Scanner(new File("ACTMOnlineTraining1.txt"));
	
		while(scanner.hasNext()){
			String line=scanner.nextLine();
			StringTokenizer st=new StringTokenizer(line," ");
			int slide=Integer.parseInt(st.nextToken());
			int count=Integer.parseInt(st.nextToken());
			
			ArrayList<Paper> paperList=slidePapersMap.get(slide);
			if(paperList==null){
				paperList=new ArrayList<Paper>();
				slidePapersMap.put(slide, paperList);
			}
			
			ACTMDataSet dataset=new ACTMDataSet();
			
			for(int i=0;i<count;++i){
				line=scanner.nextLine();
				st=new StringTokenizer(line," ");
				String firstToken=st.nextToken();

				int year=Integer.parseInt(firstToken);
				int month=Integer.parseInt(st.nextToken());
				String confName=st.nextToken().trim();
				
				
				SAXReader reader=new SAXReader();
				
				String metadataPath="training/"+confName+".xml";
		//		System.out.println(confName);
				Document xmldoc=reader.read(new File(metadataPath));
		//		System.out.println(metadataPath);
				Element root1=xmldoc.getRootElement();
				for (Object elem : root1.elements()) {
					Paper ppp=Paper.xmlToInstance((Element)elem);
					paperList.add(ppp);
				}
			}

		}
		return slidePapersMap;
	}
	
	public HashMap<Integer, ArrayList<Paper>> loadOnlineTrainingData_Small() throws DocumentException, FileNotFoundException{
		
		HashMap<Integer, ArrayList<Paper>> slidePapersMap=new HashMap<Integer, ArrayList<Paper>>();
		
		Scanner scanner=new Scanner(new File("ACTOnlineTraining_small1.txt"));
	
		while(scanner.hasNext()){
			String line=scanner.nextLine();
			StringTokenizer st=new StringTokenizer(line," ");
			int slide=Integer.parseInt(st.nextToken());
			int count=Integer.parseInt(st.nextToken());
			
			ArrayList<Paper> paperList=slidePapersMap.get(slide);
			if(paperList==null){
				paperList=new ArrayList<Paper>();
				slidePapersMap.put(slide, paperList);
			}
			
			ACTMDataSet dataset=new ACTMDataSet();
			
			for(int i=0;i<count;++i){
				line=scanner.nextLine();
				st=new StringTokenizer(line," ");
				String firstToken=st.nextToken();

				int year=Integer.parseInt(firstToken);
				int month=Integer.parseInt(st.nextToken());
				String confName=st.nextToken().trim();
				
				
				SAXReader reader=new SAXReader();
				
				String metadataPath="training_small1/"+confName+".xml";
		//		System.out.println(confName);
				Document xmldoc=reader.read(new File(metadataPath));
		//		System.out.println(metadataPath);
				Element root1=xmldoc.getRootElement();
				for (Object elem : root1.elements()) {
					Paper ppp=Paper.xmlToInstance((Element)elem);
					paperList.add(ppp);
				}
			}

		}
		return slidePapersMap;
	}
}
