package atm.test;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeSet;

import org.dom4j.DocumentException;

import tm.generalmodel.Word;

import actm.corpus.ACMCorpusLoader;
import actm.data.ACTMDataSet;
import actm.data.ACTMGlobalData;
import atm.corpus.MatlabCorpusLoader;
import atm.corpus.NIPSCorpusLoader;
import atm.model.ATMDataSet;
import atm.model.ATMEvaluation;
import atm.model.ATMInferencer;
import atm.model.AuthorTopicModel;
import atm.visualize.ATMVisualizer;



/**
 * 
 */

/**
 * @author wanghan
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			TestInference();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	public static void TestMatlabCorpus(){
		MatlabCorpusLoader corpus=new MatlabCorpusLoader();
		ATMDataSet data=corpus.Load();
		
		System.out.println(1);
	}
	public static void TestIntermediateResult(){
		NIPSCorpusLoader corpus=new NIPSCorpusLoader();
		corpus.SHOW_DEBUG_INFO=true;
		ATMDataSet data=corpus.Load(0, 12);
		
	}
	
	
	public static void TestNIPSCorpus(){
		NIPSCorpusLoader corpus=new NIPSCorpusLoader();
		corpus.SHOW_DEBUG_INFO=true;
		ATMDataSet data=corpus.Load(1, 12);
		System.out.println(data.documentSet.size());
		System.out.println(data.authorSet.GetAuthorCount());
		TreeSet<String> set=new TreeSet<String>();
		set.addAll(data.wordSet.wordVocabularyMap.keySet());
		for (String  ss : set) {
			System.out.println(ss);
		}
		
		HashMap<String, Integer> wordCount=new HashMap<String, Integer>();
		for (Word ww : data.GlobalIndexWordMap.values()) {
			Integer temp=wordCount.get(ww.Word);
			if(temp==null){
				wordCount.put(ww.Word, 1);
			}
			else{
				wordCount.put(ww.Word, temp+1);
			}
		}
		
		FileWriter writer;
		try {
			writer = new FileWriter("aaa.txt");
			for (Word ww : data.wordSet.GetWordsCollection()) {
				writer.write(ww.Word+"\n");
				writer.flush();
				
			}
			writer.close();
			System.out.println(data.wordSet.GetWordCount());
			System.out.println(data.N);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	public static void TestTopTopicWord(){
//		AuthorTopicModel model;
//		try {
//			model = AuthorTopicModel.LoadWholeModel("Models/1288021219328_I2001_T100/1288061946837.model");
//			String s =new ATMVisualizer(model).TopKWordsPerTopic(20);
//			System.out.println(s);
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
//	public static void TestTopicTrend() {
//		AuthorTopicModel model;
//		try {
//			model = AuthorTopicModel.LoadWholeModel("Models/1288021219328_I2001_T100/1288061946837.model");
//			
//			
//			HashMap<Integer,TreeMap<Integer, Double>> trend=new ATMVisualizer(model).TopicTrends();
//			
//			for (Integer topic : trend.keySet()) {
//				TreeMap<Integer, Double> ttt=trend.get(topic);
//				StringBuffer sb=new StringBuffer(topic+":\t");
//				if(ttt!=null){
//					for (Integer year : ttt.keySet()) {
//						Double ppp=ttt.get(year);
//						if(ppp==null){
//							sb.append(year+":0.00\t");
//						}
//						else{
//							sb.append(year+":"+ppp+"\t");
//						}
//					}
//				}
//				System.out.println(sb.toString());
//			}
//			
//			System.out.println(1);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public static void TestInference() throws FileNotFoundException, DocumentException {
//		ATMDataSet data=new NIPSCorpusLoader().Load(1,12);
		ACTMGlobalData globaldata=new ACTMGlobalData();
		ACTMDataSet data=new ACMCorpusLoader().loadTrainData(globaldata);
		AuthorTopicModel model=new AuthorTopicModel(data,100,globaldata.getWordCount(),globaldata.getAuthorCount());
		
		ATMInferencer infer=new ATMInferencer(data,model);
		infer.Inferencer();
		
		ACTMDataSet testdata=new ACMCorpusLoader().loadTestData(globaldata);
		
		System.out.println(ATMEvaluation.measurePPX(model, globaldata, testdata));
		infer.SaveModel();
		ATMVisualizer vv=new ATMVisualizer(infer.Model,globaldata);
		System.out.println(vv.TopKAuthorsPerTopic(10));
		System.out.println(vv.TopKWordsPerTopic(10));      
		
		
	}

}
