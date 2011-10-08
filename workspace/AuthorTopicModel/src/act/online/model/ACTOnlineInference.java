/**
 * 
 */
package act.online.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import tm.generalmodel.Word;

import act.model.ACTVisualizer;
import actm.corpus.ACMCorpusLoader;
import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;
import actm.data.ACTMGlobalData;
import actm.data.Paper;
import atm.labeling.LabelingUtils;
import atm.labeling.Parse;
/**
 * @author wanghan
 *
 */
public class ACTOnlineInference {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			Thread.sleep(1000*4*3600);
			HashSet<String> ngrams=new HashSet<String>();
			
			Vector<Parse> labels=LabelingUtils.loadCandidateLabelsFromNgramRankingFile("rude1.x2");
			for (Parse parse : labels) {
				ngrams.add(parse.toString());
			}
			
			
			
			
			HashMap<Integer, String> modelPathMap=new HashMap<Integer, String>();
			
			ACTMGlobalData globalData=new ACTMGlobalData();
			HashMap<Integer, ArrayList<Paper>> traindatas=new ACMCorpusLoader().loadOnlineTrainingData_Small();
			HashMap<Integer, ArrayList<Paper>> testdatas=new ACMCorpusLoader().loadOnlineTestData_Small();
			int topicCount=100;
			double weight[]=new double[]{0.25,0.25,0.25,0.25};
			ACTOnlineModel model=null;
			for (Integer slide : traindatas.keySet()) {
				ArrayList<Paper> papers=traindatas.get(slide);
				ACTMDataSet dataset=new ACTMDataSet();
				for (Paper paper : papers) {
					dataset.insertPaperWithNGrams(paper, globalData,ngrams);
				}
				if(slide==0){
					model=new ACTOnlineModel(dataset, 
							globalData.getWordCount(), 
							globalData.getAuthorCount(), 
							globalData.getGlobalConferenceCount(), 
							topicCount,weight);
					model.InitNewModel();
					model.InitWindowParas();
				}
				else{
					
					model.updateModelForNewSlide(slide, dataset, 
							globalData.getWordCount(), 
							globalData.getAuthorCount(), 
							globalData.getGlobalConferenceCount());
					model.InitNewModel();
					model.InitWindowParas();
				}
				
				ACTOnlineInference infer=new ACTOnlineInference(dataset,model);
				infer.inference();
				String modelpath=infer.Model.SaveFinalModel(infer.modelSavedDir);
				
//				modelPathMap.put(slide, modelpath);
				
				globalData.serialize(infer.modelSavedDir+System.currentTimeMillis()+".glo");
//				globalData.outputAuthorList(infer.modelSavedDir+System.currentTimeMillis()+".author");
//				globalData.outputConfList(infer.modelSavedDir+System.currentTimeMillis()+".conf");

				outputVisulizationFile(globalData, model, infer);	
				
				System.gc();

			}
			
			
//			for (Integer slide : testdatas.keySet()) {
//				ACTMDataSet testdataset=new ACTMDataSet();
//				ArrayList<Paper> testpapers=testdatas.get(slide);
//				ACTModel tempmodel=ACTModel.LoadWholeModel(modelPathMap.get(slide));
//				for (Paper paper : testpapers) {
//					testdataset.insertPaperWithNGrams(paper, globalData, ngrams);
//				}
//				System.out.println(slide+": "+ACTEvaluation.measurePPX(tempmodel, testdataset));
//				System.out.println(slide+": "+ACTEvaluation.measureAverEntropy(tempmodel));
//				System.out.println(slide+": "+ACTEvaluation.measureAverKL(tempmodel));
//			}			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private static void outputVisulizationFile(ACTMGlobalData globalData,
			ACTOnlineModel model, ACTOnlineInference infer) throws IOException {
		String showFile=infer.modelSavedDir+"\\"+System.currentTimeMillis()+".show";
		File fileout =new File(showFile);
		FileWriter writer=new FileWriter(fileout);
		ACTVisualizer visualizer=new ACTVisualizer(model,globalData);
		
		writer.write(visualizer.TopKWordsPerTopic(10));
		writer.write(visualizer.TopKAuthorsPerTopic(10));
		writer.write(visualizer.TopKTopicPerConference(10));
		writer.write(visualizer.TopKTopicPerAuthor(10));
		
		
		writer.flush();
		writer.close();
	}
	
	public ACTMDataSet DataSet;
	public ACTOnlineModel Model;
	public int N_ITERS=50; //number of Gibbs sampling iteration
	public int SAVE_ITER=100;
	public String modelSavedDir="ACTOnlineModels/"+System.currentTimeMillis()+"_I{1}_T{2}/Slide_S{3}/";
	
	public static String modelSavedDirTemplate="ACTOnlineModels/"+System.currentTimeMillis()+"_I{1}_T{2}/Slide_S{3}/";
	
	public ACTOnlineInference(ACTMDataSet data,ACTOnlineModel model) {
		// TODO Auto-generated constructor stub
		this.DataSet=data;
		this.Model=model;
//		this.probs=new double[Model.D][Model.T];
//		this.order=new int[DataSet.N];
		this.modelSavedDir=modelSavedDirTemplate.replace("{0}", String.valueOf(System.currentTimeMillis()));
		this.modelSavedDir=this.modelSavedDir.replace("{1}", String.valueOf(N_ITERS));
		this.modelSavedDir=this.modelSavedDir.replace("{2}",String.valueOf(Model.T));
		File dir=new File(modelSavedDir);
		if(!dir.exists()){
			dir.mkdirs();
		}
	}
	
	public void inference(){
		double WBETA[]=new double[Model.T];
		double TALPHA[]=new double[Model.A];
		double CMU[] =new double[Model.T];
		
		for(int i=0;i<Model.T;++i){
			for(int j=0;j<Model.W;++j){
				WBETA[i]+=Model.beta[j][i];
			}
			for(int j=0;j<Model.A;++j){
				TALPHA[j]+=Model.alpha[i][j];
			}
		}
		for(int i=0;i<Model.C;++i){
			for(int j=0;j<Model.T;++j){
				CMU[j]+=Model.mumu[i][j];
			}
		}
		
		
		for(int iter=0;iter<N_ITERS;++iter){
			System.err.println(new Date()+ " Iteration : "+iter);
			
//			save model per SAVE_ITER
//			if(iter!=0&&iter%SAVE_ITER==0){
//				File dir=new File(modelSavedDir+"IT"+iter+"/");
//				if(!dir.exists()){
//					dir.mkdirs();
//				}
//				this.Model.SaveTempModel(modelSavedDir+"IT"+iter+"/");
//				
//			}

			for (ACTMDocument doc : DataSet.documentSet) {

				for(int i=0;i<doc.getBagOfWordSize();++i){
					ACTMDocument curDoc=doc;
					int curConference=curDoc.getConference().getGlobalIndex();
					Word curWord=doc.getBagOfWords().get(i);
					int curWordGloIndex=doc.getBagOfWordGloIndexes().get(i);
					int curTopic=Model.z[curWordGloIndex];
					
					Model.CCT[curConference][curTopic]-=curWord.getSize();
					Model.nzdsum[curTopic]-=curWord.getSize();
				}
				
				for(int i=0;i<doc.getBagOfWordSize();++i){
					ACTMDocument curDoc=doc;
					int curWord=doc.getBagOfWords().get(i).Index;
					int curWordGloIndex=doc.getBagOfWordGloIndexes().get(i);
					int curTopic=Model.z[curWordGloIndex];
					int curConference=curDoc.getConference().getGlobalIndex();
					int curAuthor=Model.x[curWordGloIndex];
					Word curWordIns=doc.getBagOfWords().get(i);
					
					
					Model.nxsum[curAuthor]-=curWordIns.getSize();
					Model.nzsum[curTopic]-=curWordIns.getSize();
					
					Model.CWT[curWord][curTopic]-=curWordIns.getSize();
					Model.CTA[curTopic][curAuthor]-=curWordIns.getSize();
					
					
					double totalProb=0;
					double [] authorSum=new double[Model.A];
					double [] topicSum=new double[Model.T];
					
					
					
					for(int j=0;j<curDoc.getAuthors().size();++j){
						int authorIndex=curDoc.getAuthors().get(j).getIndex();
						
						
						for(int k=0;k<Model.T;++k){
							double probs=(Model.CWT[curWord][k]+Model.beta[curWord][k])/(Model.nzsum[k]+WBETA[k])
							*(Model.CTA[k][authorIndex]+Model.alpha[k][authorIndex])/(Model.nxsum[authorIndex]+TALPHA[authorIndex])
							*(Model.CCT[curConference][k]+Model.mumu[curConference][k])/(Model.nzdsum[k]+CMU[k]);
							
							totalProb+=probs;
							
							authorSum[j]+=probs;
							topicSum[k]+=probs;
						}
					}
					 
					//sample a new topic and author assignment
					
					double r =Math.random()*totalProb;
					
					int newAuthor=-1,newTopic=-1;
					
					double max=0;
					for(int j=0;j<curDoc.getAuthors().size();++j){
						max+=authorSum[j];
						if(max>=r){
							newAuthor=curDoc.getAuthors().get(j).getIndex();
							break;
						}
					}
					max=0;
					for(int k=0;k<Model.T;++k){
						max+=topicSum[k];
						if(max>=r){
							newTopic=k;
							break;
						}
					}
					
					
					
					Model.z[curWordGloIndex]=newTopic;
					Model.x[curWordGloIndex]=newAuthor;
					Model.nxsum[newAuthor]+=curWordIns.getSize();
					Model.nzsum[newTopic]+=curWordIns.getSize();
					Model.CWT[curWord][newTopic]+=curWordIns.getSize();
					Model.CTA[newTopic][newAuthor]+=curWordIns.getSize();
					Model.CCT[curConference][newTopic]+=curWordIns.getSize();
					Model.nzdsum[newTopic]+=curWordIns.getSize();
					
				}
				
//				for(int i=0;i<doc.getBagOfWordSize();++i){
//					ACTMDocument curDoc=doc;
//					int curConference=curDoc.getConference().getGlobalIndex();
//					int curWordGloIndex=doc.getBagOfWordGloIndexes().get(i);
//					int curTopic=Model.z[curWordGloIndex];
//					Word curWord=doc.getBagOfWords().get(i);
//					
//					Model.CCT[curConference][curTopic]+=curWord.getSize();
//					Model.nzdsum[curTopic]+=curWord.getSize();
//				}
			}
			
			
		}
		
		//updata theta and phi
		//theta: Probabilities of topics given authors size T x A
		// phi: Probabilities of words given topics, size W x T
		//this.CWT=new int[W][T];
		//this.CTA=new int[T][A];
		for(int i=0;i<Model.T;++i){
			for(int j=0;j<Model.W;++j){
				Model.phi[j][i]=1.0*(Model.beta[j][i]+Model.CWT[j][i])/(WBETA[i]+Model.nzsum[i]);
			} 
			
			for (int j = 0; j < Model.A; j++) {
				Model.theta[i][j]=1.0*(Model.alpha[i][j]+Model.CTA[i][j])/(TALPHA[j]+Model.nxsum[j]);

			}
			for (int j = 0; j < Model.C; j++) {
				Model.psi[j][i]=1.0*(Model.mumu[j][i]+Model.CCT[j][i])/(CMU[i]+Model.nzdsum[i]);

			}
		}
		
	}
}
