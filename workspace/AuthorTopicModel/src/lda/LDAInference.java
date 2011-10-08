/**
 * 
 */
package lda;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

import lda.visualize.LDAVisualize;

import actm.corpus.ACMCorpusLoader;
import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;
import actm.data.ACTMGlobalData;


/**
 * @author wanghan
 *
 */
public class LDAInference {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ACTMGlobalData globalData=new ACTMGlobalData();
			ACTMDataSet datas=new ACMCorpusLoader().loadTrainData(globalData);
			int topicCount=100;
			System.out.println("doc count:"+datas.documentSet.size());
			LDAModel model=new LDAModel(datas,globalData.getWordCount(),topicCount);
			model.InitNewModel();
			LDAInference infer=new LDAInference(datas,model);
			infer.inference();
			
			infer.Model.SaveFinalModel(infer.modelSavedDir);
			
			//save global data
			globalData.serialize(infer.modelSavedDir+"\\"+System.currentTimeMillis()+".global");
			String showFile=infer.modelSavedDir+"\\"+System.currentTimeMillis()+".show";
			File fileout =new File(showFile);
			FileWriter writer=new FileWriter(fileout);
			LDAVisualize visualizer=new LDAVisualize(model,globalData);
			
			writer.write(visualizer.TopKWordsPerTopic(10));			
			
			writer.flush();
			writer.close();

			System.out.println(LDAEvaluation.evaluateModel(model, globalData));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public LDAModel Model;
	public ACTMDataSet DataSet;
	private double probs[];
	private int[] order;
	
	public int N_ITERS=10; //number of Gibbs sampling iteration
	public int SAVE_ITER=100;
	public String modelSavedDir="LDAModels/"+System.currentTimeMillis()+"_I{1}_T{2}/Slide_S{3}/";
	
	public static String modelSavedDirTemplate="LDAModels/"+System.currentTimeMillis()+"_I{1}_T{2}/Slide_S{3}/";
	
	
	public LDAInference(ACTMDataSet data,LDAModel model) {
		// TODO Auto-generated constructor stub
		this.DataSet=data;

		this.Model=model;

		this.probs=new double[Model.T];
		this.order=new int[DataSet.N];
		
		this.modelSavedDir=LDAInference.modelSavedDirTemplate.replace("{1}", String.valueOf(N_ITERS));
		this.modelSavedDir=this.modelSavedDir.replace("{2}",String.valueOf(Model.T));
		this.modelSavedDir=this.modelSavedDir.replace("{3}",String.valueOf(0));
		File dir=new File(modelSavedDir);
		if(!dir.exists()){
			dir.mkdirs();
		}
		for(int i=0;i<DataSet.N;++i){
			order[i]=i;
		}
	}
	public void inference(){
		double WBETA=Model.W*Model.be;
		double TALPHA=Model.T*Model.al;
		
		
	
		
		for(int iter=0;iter<N_ITERS;++iter){
			System.err.println(new Date()+ " Iteration : "+iter);
			

			
			for(int i=0;i<DataSet.N;++i){
	
				int curWord=DataSet.GlobalIndexWordMap.get(order[i]).Index;
				ACTMDocument curDoc=DataSet.GlobalIndexDocMap.get(order[i]);
				int curTopic=Model.z[order[i]];
				int curDocument=curDoc.getIndex();
				Model.nzsum[curTopic]--;
				Model.ndsum[curDocument]--;
				Model.CWT[curWord][curTopic]--;
				Model.CTD[curTopic][curDocument]--;
				
				double totalProb=0;

				for(int k=0;k<Model.T;++k){
					probs[k]=(Model.CWT[curWord][k]+Model.be)/(Model.nzsum[k]+WBETA)
					*(Model.CTD[k][curDocument]+Model.al)/(Model.ndsum[curDocument]+TALPHA);
					totalProb+=probs[k];

				}
				 
				//sample a new topic and author assignment
				
				double r =Math.random()*totalProb;
				
				int newTopic=-1;
				
				double max=0;
				for(int j=0;j<Model.T;++j){
					max+=probs[j];
					if(max>=r){
						newTopic=j;
						break;
					}
				}
				
				
				Model.z[order[i]]=newTopic;
				Model.CTD[newTopic][curDocument]++;
				Model.CWT[curWord][newTopic]++;
				Model.nzsum[newTopic]++;
				Model.ndsum[curDocument]++;
			}
		}
		
		//updata theta and phi
		//theta: Probabilities of topics given authors size T x A
		// phi: Probabilities of words given topics, size W x T
		//this.CWT=new int[W][T];
		//this.CTA=new int[T][A];
		for(int i=0;i<Model.T;++i){
			for(int j=0;j<Model.W;++j){
				Model.phi[j][i]=1.0*(Model.be+Model.CWT[j][i])/(WBETA+Model.nzsum[i]);
			} 
			
			for (int j = 0; j < Model.D; j++) {
				Model.theta[i][j]=1.0*(Model.al+Model.CTD[i][j])/(TALPHA+Model.ndsum[j]);

			}
			
		}
		
	}
}
