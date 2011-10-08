/**
 * 
 */
package actm.model.v2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.dom4j.DocumentException;

import actm.corpus.ACMCorpusLoader;
import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;
import actm.data.ACTMGlobalData;
import actm.online.model.v2.ACTMV2Evaluation;
import actm.visualize.v2.ACTMV2Visualizer;

/**
 * @author wanghan
 *
 */
public class ACTMV2Inferencer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ACTMGlobalData globalData=new ACTMGlobalData();
			ACTMDataSet data=new ACMCorpusLoader().loadTrainData(globalData);
			ACTMV2Inferencer infer=new ACTMV2Inferencer(data,globalData,100);
			infer.inference();
			infer.Model.SaveFinalModel(infer.modelSavedDir);
			globalData.serialize(infer.modelSavedDir+System.currentTimeMillis()+".glo");
			
			
			//save global data
			globalData.serialize(infer.modelSavedDir+"\\"+System.currentTimeMillis()+".global");
			
			String showFile=infer.modelSavedDir+"\\"+System.currentTimeMillis()+".show";
			File fileout =new File(showFile);
			FileWriter writer=new FileWriter(fileout);
			ACTMV2Visualizer visualizer=new ACTMV2Visualizer(infer.Model,globalData);
			
			writer.write(visualizer.TopKWordsPerTopic(10));
			writer.write(visualizer.TopKAuthorsPerTopic(10));
			writer.write(visualizer.TopKTopicPerConference(10));
//			writer.write(visualizer.TopKConfPerAuthor(10));
			writer.write(visualizer.TopKTopicPerAuthor(10));
			
			
			writer.flush();
			writer.close();
					
			ACTMDataSet testdata=new ACMCorpusLoader().loadTestData(globalData);
			System.out.println(ACTMV2Evaluation.measurePPX(infer.Model, globalData,testdata));
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public AuthorConferenceTopicModelV2 Model;
	public ACTMDataSet DataSet;
	public ACTMGlobalData GlobalData;
	private double probs[][];
	private int[] order;
	
	public int N_ITERS=10; //number of Gibbs sampling iteration
	public int SAVE_ITER=100;
	
	public String modelSavedDir="ACTMModels/{0}_I{1}_T{2}/";
	
	
	public ACTMV2Inferencer(ACTMDataSet data,ACTMGlobalData globalData, int topicCount) {
		// TODO Auto-generated constructor stub
		this.DataSet=data;
		this.GlobalData=globalData;
		Model=new AuthorConferenceTopicModelV2(data,globalData.getWordCount(),globalData.getAuthorCount(),globalData.getGlobalConferenceCount(),topicCount);
		Model.InitNewModel();
		this.probs=new double[Model.A][Model.T];
		this.order=new int[DataSet.N];
		this.modelSavedDir=this.modelSavedDir.replace("{0}", String.valueOf(System.currentTimeMillis()));
		this.modelSavedDir=this.modelSavedDir.replace("{1}", String.valueOf(N_ITERS));
		this.modelSavedDir=this.modelSavedDir.replace("{2}",String.valueOf(Model.T));
		File dir=new File(modelSavedDir);
		if(!dir.exists()){
			dir.mkdirs();
		}
		for(int i=0;i<DataSet.N;++i){
			order[i]=i;
		}
	}
	

	private void ReOrder(){
		int temp;
		for (int i=0; i<(DataSet.N-1); i++) {
		      // pick a random integer between i and nw
		      int rp = i + (int) (1.0* (double) (DataSet.N-i) * Math.random());
		      
		      // switch contents on position i and position rp
		      temp = order[rp];
		      order[rp]=order[i];
		      order[i]=temp;
		  }
	}
	
	public void inference(){
		double WBETA=Model.be*Model.W;
		double TALPHA=Model.T*Model.al;
		double CGAMMA=Model.C*Model.ga;
		
		ReOrder();
		
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
						probs[authorIndex][k]=(Model.CWT[curWord][k]+Model.be)/(Model.nzsum[k]+WBETA)
						*(Model.CCA[curConference][authorIndex]+Model.ga)/(Model.nxsum[authorIndex]+CGAMMA)
						*(Model.CTC[k][curConference]+Model.al)/(Model.nysum[curConference]+TALPHA);
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
				Model.phi[j][i]=1.0*(Model.be+Model.CWT[j][i])/(WBETA+Model.nzsum[i]);
			} 
			
			for (int j = 0; j < Model.C; j++) {
				Model.theta[i][j]=1.0*(Model.al+Model.CTC[i][j])/(TALPHA+Model.nysum[j]);

			}
			
		}
		for(int i=0;i<Model.C;++i){
			for(int j=0;j<Model.A;++j){
				Model.eta[i][j]=1.0*(Model.ga+Model.CCA[i][j])/(CGAMMA+Model.nxsum[j]);
			}
		}
	}
	
}
