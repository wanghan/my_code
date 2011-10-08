/**
 * 
 */
package actm.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import act.model.ACTEvaluation;
import act.model.ACTModel;
import act.model.ACTVisualizer;
import act.online.model.ACTOnlineInference;
import act.online.model.ACTOnlineModel;
import actm.corpus.ACMCorpusLoader;
import actm.data.ACTMDataSet;
import actm.data.ACTMGlobalData;
import actm.data.Paper;
import actm.model.AuthorConferenceTopicModel;
import actm.online.model.v2.ACTMV2Evaluation;
import actm.online.model.v2.ACTMV2OnlineInference;
import actm.online.model.v2.AuthorConferenceTopicOnlineModelV2;
import actm.visualize.v2.ACTMV2Visualizer;
import atm.labeling.LabelingUtils;
import atm.labeling.Parse;

/**
 * @author wanghan
 * 
 */
public class Test {

	public static void main(String[] args) {
		testACTOnlineEvaluation();
	}

	public static void testLoadACTMModel() {
		// String
		// path="E:\\workspace\\AuthorTopicModel\\ACTMModels\\1301243763271_I300_T100\\1301256359072.model";
		// try {
		//			
		// File pathFile=new File(path);
		// String
		// showFile=pathFile.getParent()+"\\"+System.currentTimeMillis()+".show";
		// File fileout =new File(showFile);
		// FileWriter writer=new FileWriter(fileout);
		//			
		// AuthorConferenceTopicModel
		// model=AuthorConferenceTopicModel.LoadWholeModel(path);
		// ACTMVisualizer visualizer=new ACTMVisualizer(model);
		//						
		// writer.write(visualizer.TopKAuthorsPerTopic(10));
		// writer.write(visualizer.TopKAuthorPerConference(10));
		// writer.write(visualizer.TopKWordsPerTopic(10));
		// writer.write(visualizer.TopKTopicPerConference(10));
		//			
		// writer.flush();
		// writer.close();
		//			
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (ClassNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public static void testACTOnlineEvaluation() {
		try {

			HashSet<String> ngrams = new HashSet<String>();

			Vector<Parse> labels = LabelingUtils
					.loadCandidateLabelsFromNgramRankingFile("rude.x2");
			for (Parse parse : labels) {
				ngrams.add(parse.toString());
			}

			
			//{ 0.33, 0.33, 0.34 }, { 0 },
			//{ 0.5, 0.5 }, { 0.4, 0.6 }, { 0.2, 0.8 }, 
			
			
			File out = new File("out.txt");
			FileWriter writer = new FileWriter(out);
			double[][] weight = new double[][] { 
					{ 0.2, 0.3, 0.5 }, { 0.15, 0.25, 0.6 },
					{ 0.05, 0.15, 0.8 }, { 0.01, 0.03, 0.06, 0.9 },
					{ 0.03, 0.07, 0.1, 0.8 }, { 0.05, 0.15, 0.2, 0.6 },
					{ 0.25, 0.25, 0.25, 0.25 }, { 0.2, 0.2, 0.2, 0.2, 0.2 },
					{ 0.05, 0.1, 0.15, 0.2, 0.6 },
					{ 0.05, 0.05, 0.1, 0.15, 0.7 } };

			HashMap<Integer, ArrayList<Paper>> traindatas = new ACMCorpusLoader()
					.loadOnlineTrainingData_Small();
			HashMap<Integer, ArrayList<Paper>> testdatas = new ACMCorpusLoader()
					.loadOnlineTestData_Small();

			for (int i = 0; i < weight.length; ++i) {
				for (int j = 0; j < weight[i].length; ++j) {
					writer.append(weight[i][j] + "\t");
				}
				writer.append("\r\n");
				writer.flush();
				

				runACTOnline(weight[i], writer,ngrams,traindatas,testdatas);

				writer.flush();

			}

			writer.flush();
			writer.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void runACTOnline(double[] weight, FileWriter out, HashSet<String> ngrams,
			HashMap<Integer, ArrayList<Paper>> traindatas,
			HashMap<Integer, ArrayList<Paper>> testdatas) {
		try {
			HashMap<Integer, String> modelPathMap = new HashMap<Integer, String>();

			ACTMGlobalData globalData = new ACTMGlobalData();

			int topicCount = 100;
			ACTOnlineModel model = null;
			for (Integer slide : traindatas.keySet()) {
				ArrayList<Paper> papers = traindatas.get(slide);
				ACTMDataSet dataset = new ACTMDataSet();
				for (Paper paper : papers) {
					dataset.insertPaperWithNGrams(paper, globalData, ngrams);
				}
				if (slide == 0) {
					model = new ACTOnlineModel(dataset, globalData
							.getWordCount(), globalData.getAuthorCount(),
							globalData.getGlobalConferenceCount(), topicCount,
							weight);
					model.InitNewModel();
					model.InitWindowParas();
				} else {

					model.updateModelForNewSlide(slide, dataset, globalData
							.getWordCount(), globalData.getAuthorCount(),
							globalData.getGlobalConferenceCount());
					model.InitNewModel();
					model.InitWindowParas();
				}

				ACTOnlineInference infer = new ACTOnlineInference(dataset,
						model);
				infer.inference();
				String modelpath = infer.Model
						.SaveFinalModel(infer.modelSavedDir);

				modelPathMap.put(slide, modelpath);

				globalData.serialize(infer.modelSavedDir
						+ System.currentTimeMillis() + ".glo");
				// globalData.outputAuthorList(infer.modelSavedDir+System.currentTimeMillis()+".author");
				// globalData.outputConfList(infer.modelSavedDir+System.currentTimeMillis()+".conf");

				String showFile = infer.modelSavedDir + "\\"
						+ System.currentTimeMillis() + ".show";
				File fileout = new File(showFile);
				FileWriter writer = new FileWriter(fileout);
				ACTVisualizer visualizer = new ACTVisualizer(model, globalData);

				writer.write(visualizer.TopKWordsPerTopic(10));
				writer.write(visualizer.TopKAuthorsPerTopic(10));
				writer.write(visualizer.TopKTopicPerConference(10));
				writer.write(visualizer.TopKTopicPerAuthor(10));

				writer.flush();
				writer.close();

				System.gc();

			}

			for (Integer slide : testdatas.keySet()) {
				ACTMDataSet testdataset = new ACTMDataSet();
				ArrayList<Paper> testpapers = testdatas.get(slide);
				ACTModel tempmodel = ACTModel.LoadWholeModel(modelPathMap
						.get(slide));
				for (Paper paper : testpapers) {
					testdataset
							.insertPaperWithNGrams(paper, globalData, ngrams);
				}
				
				double ppx=ACTEvaluation.measurePPX(tempmodel, testdataset);
				double averEntropy=ACTEvaluation.measureAverEntropy(tempmodel);
				double averKL=ACTEvaluation.measureAverKL(tempmodel);
				
				System.out.println(slide + ": "
						+ ppx);
				System.out.println(slide + ": "
						+ averEntropy);
				System.out.println(slide + ": "
						+ averKL);
				
				out.append(slide + ": " + ppx + "\r\n");
				out.flush();
				out.append(slide + ": " + averEntropy + "\r\n");
				out.flush();
				out.append(slide + ": " + averKL + "\r\n");
				out.flush();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void testACTMV2OnlinePPX() {
		try {
			File out = new File("out.txt");
			FileWriter writer = new FileWriter(out);
			double[][] weight = new double[][] { { 0.33, 0.33, 0.34 }, { 0 },
					{ 0.5, 0.5 }, { 0.4, 0.6 }, { 0.2, 0.8 }, { 1 },
					{ 0.2, 0.3, 0.5 }, { 0.15, 0.25, 0.6 },
					{ 0.05, 0.15, 0.8 }, { 0.01, 0.03, 0.06, 0.9 },
					{ 0.03, 0.07, 0.1, 0.8 }, { 0.05, 0.15, 0.2, 0.6 },
					{ 0.25, 0.25, 0.25, 0.25 }, { 0.2, 0.2, 0.2, 0.2, 0.2 },
					{ 0.05, 0.1, 0.15, 0.2, 0.6 },
					{ 0.05, 0.05, 0.1, 0.15, 0.7 } };

			HashMap<Integer, ArrayList<Paper>> traindatas = new ACMCorpusLoader()
					.loadOnlineTrainingData(null);
			HashMap<Integer, ArrayList<Paper>> testdatas = new ACMCorpusLoader()
					.loadOnlineTestData(null);

			for (int i = 0; i < weight.length; ++i) {
				for (int j = 0; j < weight[i].length; ++j) {
					writer.append(weight[i][j] + "\t");
				}
				writer.append("\r\n");
				writer.flush();
				ACTMGlobalData globalData = new ACTMGlobalData();

				runACTMV2Online(weight[i], writer);

				writer.flush();

			}

			writer.flush();
			writer.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void runACTMV2Online(double[] weight, FileWriter out) {
		try {
			// ACTMGlobalData globalData,HashMap<Integer, ArrayList<Paper>>
			// traindatas,HashMap<Integer, ArrayList<Paper>> testdatas,

			int topicCount = 100;
			AuthorConferenceTopicOnlineModelV2 model = null;

			ACTMGlobalData globalData = new ACTMGlobalData();
			HashMap<Integer, ArrayList<Paper>> traindatas = new ACMCorpusLoader()
					.loadOnlineTrainingData(globalData);
			HashMap<Integer, ArrayList<Paper>> testdatas = new ACMCorpusLoader()
					.loadOnlineTestData(globalData);

			for (Integer slice : traindatas.keySet()) {
				System.out.println(slice + " " + traindatas.get(slice).size());
			}
			for (Integer slice : testdatas.keySet()) {
				System.out.println(slice + " " + testdatas.get(slice).size());
			}

			for (Integer slide : traindatas.keySet()) {
				ArrayList<Paper> papers = traindatas.get(slide);
				ACTMDataSet dataset = new ACTMDataSet();

				for (Paper paper : papers) {
					dataset.insertPaper(paper, globalData);
				}
				if (slide == 0) {
					model = new AuthorConferenceTopicOnlineModelV2(dataset,
							globalData.getWordCount(), globalData
									.getAuthorCount(), globalData
									.getGlobalConferenceCount(), topicCount,
							weight);
					model.InitNewModel();
					model.InitWindowParas();
				} else {

					model.updateModelForNewSlide(slide, dataset, globalData
							.getWordCount(), globalData.getAuthorCount(),
							globalData.getGlobalConferenceCount());
					model.InitNewModel();
					model.InitWindowParas();
				}

				ACTMV2OnlineInference infer = new ACTMV2OnlineInference(
						dataset, model);
				infer.inference();
				infer.Model.SaveFinalModel(infer.modelSavedDir);

				// globalData.outputAuthorList(infer.modelSavedDir+System.currentTimeMillis()+".author");
				// globalData.outputConfList(infer.modelSavedDir+System.currentTimeMillis()+".conf");

				String showFile = infer.modelSavedDir + "\\"
						+ System.currentTimeMillis() + ".show";
				File fileout = new File(showFile);
				FileWriter writer = new FileWriter(fileout);
				ACTMV2Visualizer visualizer = new ACTMV2Visualizer(model,
						globalData);

				writer.write(visualizer.TopKWordsPerTopic(10));
				writer.write(visualizer.TopKAuthorsPerTopic(10));
				writer.write(visualizer.TopKTopicPerConference(10));
				// writer.write(visualizer.TopKConfPerAuthor(10));
				writer.write(visualizer.TopKTopicPerAuthor(10));

				writer.flush();
				writer.close();

				ACTMDataSet testdataset = new ACTMDataSet();
				ArrayList<Paper> testpapers = testdatas.get(slide);

				for (Paper paper : testpapers) {
					testdataset.insertPaper(paper, globalData);
				}

				double ppx = ACTMV2Evaluation.measurePPX(model, globalData,
						testdataset);

				System.out.println(slide + ": " + ppx);
				out.append(slide + ": " + ppx + "\r\n");
				out.flush();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static int testCACSum() {
		String path = "E:\\workspace\\AuthorTopicModel\\ACTMModels\\1301240031993_I500_T200\\1301240535978.model";
		try {

			int sum = 0;

			AuthorConferenceTopicModel model = AuthorConferenceTopicModel
					.LoadWholeModel(path);
			for (int i = 0; i < model.A; ++i) {
				for (int j = 0; j < model.C; ++j) {
					sum += model.CAC[i][j];
				}

			}
			return sum;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
