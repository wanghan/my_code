/**
 * 
 */
package act.model;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import act.corpus.ACMCorpusLoader;
import actm.data.ACTMDataSet;
import actm.data.ACTMGlobalData;
import actm.data.Paper;
import atm.labeling.LabelingUtils;
import atm.labeling.Parse;

/**
 * @author wanghan
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		try {
		
			testLoadACTModel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}
	
	public static void testPPXforACTmodel(){
		
		try {
			ACTMGlobalData globalData=ACTMGlobalData.deserialize("ACTOnlineModels/1305824851648_I50_T100/Slide_S{3}/1305837221612.glo");
			
			HashSet<String> ngrams=new HashSet<String>();
			
			Vector<Parse> labels=LabelingUtils.loadCandidateLabelsFromNgramRankingFile("rude1.x2");
			for (Parse parse : labels) {
				ngrams.add(parse.toString());
			}
			
			String [] modelPathList={
					"ACTOnlineModels/1305824851648_I50_T100/Slide_S{3}/1305825036900.model",
					"ACTOnlineModels/1305824851648_I50_T100/Slide_S{3}/1305825388105.model",
					"ACTOnlineModels/1305824851648_I50_T100/Slide_S{3}/1305825880309.model",
					"ACTOnlineModels/1305824851648_I50_T100/Slide_S{3}/1305826460025.model",
					"ACTOnlineModels/1305824851648_I50_T100/Slide_S{3}/1305827272411.model",
					"ACTOnlineModels/1305824851648_I50_T100/Slide_S{3}/1305828298628.model",
					"ACTOnlineModels/1305824851648_I50_T100/Slide_S{3}/1305829618593.model",
					"ACTOnlineModels/1305824851648_I50_T100/Slide_S{3}/1305831207939.model",
					"ACTOnlineModels/1305824851648_I50_T100/Slide_S{3}/1305833164320.model",
					"ACTOnlineModels/1305824851648_I50_T100/Slide_S{3}/1305835483319.model",
					"ACTOnlineModels/1305824851648_I50_T100/Slide_S{3}/1305837200282.model"
			};
			
			HashMap<Integer, ArrayList<Paper>> testdatas=new ACMCorpusLoader().loadOnlineTestData_Small();
			ACTMDataSet testdataset=new ACTMDataSet();
			
			for (Integer slide : testdatas.keySet()) {
				ArrayList<Paper> papers=testdatas.get(slide);
				
				for (Paper paper : papers) {
					testdataset.insertPaper(paper, globalData);
				}
				
				ACTModel model=ACTModel.LoadWholeModel(modelPathList[slide]);
			
				double ppx=ACTEvaluation.measurePPX(model, testdataset);
				System.out.println(ppx);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void testRuntimeOfACT() throws Exception{
		HashSet<String> ngrams=new HashSet<String>();
		
		Vector<Parse> labels=LabelingUtils.loadCandidateLabelsFromNgramRankingFile("rude1.x2");
		for (Parse parse : labels) {
			ngrams.add(parse.toString());
		}
		
		ACTMGlobalData globalData=new ACTMGlobalData();
		HashMap<Integer, ArrayList<Paper>> traindatas=new ACMCorpusLoader().loadOnlineTrainingData_Small();
		HashMap<Integer, ArrayList<Paper>> testdatas=new ACMCorpusLoader().loadOnlineTestData_Small();
		
		ACTMDataSet testdataset=new ACTMDataSet();
		ACTMDataSet dataset=new ACTMDataSet();
		for (Integer slide : traindatas.keySet()) {
			ArrayList<Paper> papers=traindatas.get(slide);
			
			for (Paper paper : papers) {
				dataset.insertPaperWithNGrams(paper, globalData,ngrams);
			}
			
			
			ACTModel model=new ACTModel(dataset,globalData.getWordCount(),globalData.getAuthorCount(),globalData.getGlobalConferenceCount(),100);

			model.InitNewModel();
			
			ACTInference infer=new ACTInference(dataset,model);
			infer.inference(null,  null, globalData);
			
//			ArrayList<Paper> testpapers=testdatas.get(slide);
//			for (Paper paper : testpapers) {
//				testdataset.insertPaperWithNGrams(paper, globalData,ngrams);
//			}
			
			infer.Model.SaveFinalModel(infer.modelSavedDir);
			globalData.serialize(infer.modelSavedDir+System.currentTimeMillis()+".glo");
//			double ppx=ACTEvaluation.measurePPX(model, testdataset);
//			System.out.println(slide+" "+ppx);

		}
	}
	
	private static void testLoadACTModel() {
		// TODO Auto-generated method stub
		try {
			
			ACTMGlobalData globalData=ACTMGlobalData.deserialize("./ACTModels/1319975613762_I100_T100/1319975613763.glo");
			
			ACTModel model=ACTModel.LoadWholeModel("./ACTModels/1319975613762_I100_T100/1320018826651.model");
			

			ACTVisualizer visualizer=new ACTVisualizer(model,globalData);
			
			String showFile="ACTModels/"+System.currentTimeMillis()+".show";
			File fileout =new File(showFile);
			FileWriter writer=new FileWriter(fileout);
			
			writer.write(visualizer.TopKWordsPerTopic(15));
			writer.write(visualizer.TopKAuthorsPerTopic(15));
			writer.write(visualizer.TopKTopicPerConference(15));
			writer.write(visualizer.TopKTopicPerAuthor(15));
			
			
			writer.flush();
			writer.close();
			
			System.out.println(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testMeasurePPXForACTOnline(){
		try {
			
			HashSet<String> ngram=new HashSet<String>();
			
			Vector<Parse> labels=LabelingUtils.loadCandidateLabelsFromNgramRankingFile("rude.x2");
			for (Parse parse : labels) {
				ngram.add(parse.toString());
			}
			
			
			ACTMGlobalData globalData=ACTMGlobalData.deserialize("ACTOnlineModels/1304336326371_I100_T100/Slide_S{3}/1304365292935.glo");
			HashMap<Integer, ArrayList<Paper>> testdatas=new ACMCorpusLoader().loadOnlineTestData_Small();
			int i=0;
			String [] modelNameList=new String []{
					"1304336514615",
					"1304336891796",
					"1304337512596",
					"1304338281870",
					"1304339467543",
					"1304341022034",
					"1304342912796",
					"1304352255403",
					"1304365275354",
					"1304166764284",
					"1304168743622"
					
			};
			
			
			for (Integer slide : testdatas.keySet()) {
				if(i<7){
					i++;
					continue;
				}
				ACTMDataSet testdataset=new ACTMDataSet();
				ArrayList<Paper> testpapers=testdatas.get(slide);
				ACTModel model=ACTModel.LoadWholeModel("ACTOnlineModels/1304336326371_I100_T100/Slide_S{3}/"+modelNameList[i++]+".model");
				for (Paper paper : testpapers) {
					testdataset.insertPaperWithNGrams(paper, globalData, ngram);
				}
				System.out.println(slide+": "+ACTEvaluation.measurePPX(model, testdataset));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
