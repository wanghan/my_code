/**
 * 
 */
package actm.online.model.v2;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import actm.corpus.ACMCorpusLoader;
import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;
import actm.data.ACTMGlobalData;
import actm.data.Paper;
import actm.visualize.v2.ACTMV2Visualizer;

/**
 * @author wanghan
 *
 */
public class ACTMV2OnlineInference{
	
	public static void main(String[] args) {
		try {
			ACTMGlobalData globalData=new ACTMGlobalData();
			HashMap<Integer, ArrayList<Paper>> traindatas=new ACMCorpusLoader().loadOnlineTrainingData(globalData);
			HashMap<Integer, ArrayList<Paper>> testdatas=new ACMCorpusLoader().loadOnlineTestData(globalData);
			int topicCount=100;
			double weight[]=new double[]{0,0,0};
			AuthorConferenceTopicOnlineModelV2 model=null;
			for (Integer slide : traindatas.keySet()) {
				ArrayList<Paper> papers=traindatas.get(slide);
				ACTMDataSet dataset=new ACTMDataSet();
				for (Paper paper : papers) {
					dataset.insertPaper(paper, globalData);
				}
				if(slide==0){
					model=new AuthorConferenceTopicOnlineModelV2(dataset, 
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
				
				
				
				
				ACTMV2OnlineInference infer=new ACTMV2OnlineInference(dataset,model);
				infer.inference();
				infer.Model.SaveFinalModel(infer.modelSavedDir);
				globalData.serialize(infer.modelSavedDir+System.currentTimeMillis()+".glo");
//				globalData.outputAuthorList(infer.modelSavedDir+System.currentTimeMillis()+".author");
//				globalData.outputConfList(infer.modelSavedDir+System.currentTimeMillis()+".conf");

				String showFile=infer.modelSavedDir+"\\"+System.currentTimeMillis()+".show";
				File fileout =new File(showFile);
				FileWriter writer=new FileWriter(fileout);
				ACTMV2Visualizer visualizer=new ACTMV2Visualizer(model,globalData);
				
				writer.write(visualizer.TopKWordsPerTopic(10));
				writer.write(visualizer.TopKAuthorsPerTopic(10));
				writer.write(visualizer.TopKTopicPerConference(10));
	//			writer.write(visualizer.TopKConfPerAuthor(10));
				writer.write(visualizer.TopKTopicPerAuthor(10));
				
				
				writer.flush();
				writer.close();
						
				ACTMDataSet testdataset=new ACTMDataSet();
				ArrayList<Paper> testpapers=testdatas.get(slide);
				
				for (Paper paper : testpapers) {
					testdataset.insertPaper(paper, globalData);
				}
				System.out.println(slide+": "+ACTMV2Evaluation.measurePPX(model, globalData, testdataset));
			}
			
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public AuthorConferenceTopicOnlineModelV2 Model;
	public ACTMDataSet DataSet;
	private double probs[][];
	private int[] order;
	
	public int N_ITERS=100; //number of Gibbs sampling iteration
	public int SAVE_ITER=100;
	public String modelSavedDir="ACTMV2OnlineModels/"+System.currentTimeMillis()+"_I{1}_T{2}/Slide_S{3}/";
	
	public static String modelSavedDirTemplate="ACTMV2OnlineModels/"+System.currentTimeMillis()+"_I{1}_T{2}/Slide_S{3}/";
	
	
	public ACTMV2OnlineInference(ACTMDataSet data,AuthorConferenceTopicOnlineModelV2 model) {
		// TODO Auto-generated constructor stub
		this.DataSet=data;

		this.Model=model;

		this.probs=new double[Model.A][Model.T];
		this.order=new int[DataSet.N];
		
		this.modelSavedDir=ACTMV2OnlineInference.modelSavedDirTemplate.replace("{1}", String.valueOf(N_ITERS));
		this.modelSavedDir=this.modelSavedDir.replace("{2}",String.valueOf(Model.T));
		this.modelSavedDir=this.modelSavedDir.replace("{3}",String.valueOf(Model.currentSlide));
		File dir=new File(modelSavedDir);
		if(!dir.exists()){
			dir.mkdirs();
		}
		for(int i=0;i<DataSet.N;++i){
			order[i]=i;
		}
	}
	public void inference(){
		double WBETA[]=new double[Model.T];
		double TALPHA[]=new double[Model.C];
		double CGAMMA[] =new double[Model.A];
		
		for(int i=0;i<Model.T;++i){
			for(int j=0;j<Model.W;++j){
				WBETA[i]+=Model.beta[j][i];
			}
			for(int j=0;j<Model.C;++j){
				TALPHA[j]+=Model.alpha[i][j];
			}
		}
		for(int i=0;i<Model.C;++i){
			for(int j=0;j<Model.A;++j){
				CGAMMA[j]+=Model.gamma[i][j];
			}
		}
	
		
		for(int iter=0;iter<N_ITERS;++iter){
			System.err.println(new Date()+ " Iteration : "+iter);
			
//			save model per SAVE_ITER
			if(iter!=0&&iter%SAVE_ITER==0){
				File dir=new File(modelSavedDir+"IT"+iter+"/");
				if(!dir.exists()){
					dir.mkdirs();
				}
				this.Model.SaveTempModel(modelSavedDir+"IT"+iter+"/");
				
			}
			
			for(int i=0;i<DataSet.N;++i){
	
				int curWord=DataSet.GlobalIndexWordMap.get(order[i]).Index;
				ACTMDocument curDoc=DataSet.GlobalIndexDocMap.get(order[i]);
				int curTopic=Model.z[order[i]];
				int curAuthor=Model.x[order[i]];
				int curConference=curDoc.getConference().getGlobalIndex();
				
				Model.nxsum[curAuthor]--;
				Model.nzsum[curTopic]--;
				Model.nysum[curConference]--;
				Model.CCA[curConference][curAuthor]--;
				Model.CWT[curWord][curTopic]--;
				Model.CTC[curTopic][curConference]--;
				
				double totalProb=0;
				double [] authorSum=new double[curDoc.getAuthors().size()];
				double [] topicSum=new double[Model.T];
				
				
				
				for(int j=0;j<curDoc.getAuthors().size();++j){
					int authorIndex=curDoc.getAuthors().get(j).getIndex();
//					if(authorIndex==1511){
//						System.out.println(1);
//					}
					for(int k=0;k<Model.T;++k){
						probs[authorIndex][k]=(Model.CWT[curWord][k]+Model.beta[curWord][k])/(Model.nzsum[k]+WBETA[k])
						*(Model.CCA[curConference][authorIndex]+Model.gamma[curConference][authorIndex])/(Model.nxsum[authorIndex]+CGAMMA[authorIndex])
						*(Model.CTC[k][curConference]+Model.alpha[k][curConference])/(Model.nysum[curConference]+TALPHA[curConference]);
						totalProb+=probs[authorIndex][k];

						authorSum[j]+=probs[authorIndex][k];
						topicSum[k]+=probs[authorIndex][k];
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
				
				Model.z[order[i]]=newTopic;
				Model.x[order[i]]=newAuthor;
				Model.CTC[newTopic][curConference]++;
				Model.CWT[curWord][newTopic]++;
				Model.CCA[curConference][newAuthor]++;
				Model.nzsum[newTopic]++;
				Model.nxsum[newAuthor]++;
				Model.nysum[curConference]++;
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
			
			for (int j = 0; j < Model.C; j++) {
				Model.theta[i][j]=1.0*(Model.alpha[i][j]+Model.CTC[i][j])/(TALPHA[j]+Model.nysum[j]);

			}
			
		}
		for(int i=0;i<Model.C;++i){
			for(int j=0;j<Model.A;++j){
				Model.eta[i][j]=1.0*(Model.gamma[i][j]+Model.CCA[i][j])/(CGAMMA[j]+Model.nxsum[j]);
			}
		}
	}
}
