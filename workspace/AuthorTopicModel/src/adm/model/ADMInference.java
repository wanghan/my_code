/**
 * 
 */
package adm.model;

import java.io.File;
import actm.data.ACTMDataSet;
import actm.data.ACTMGlobalData;

/**
 * @author amy
 *
 */
public class ADMInference {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ADMModel Model;
	public ACTMDataSet DataSet;
	public ACTMGlobalData GlobalData;
//	private double probs[][];
	private int[] order;
	
	public int N_ITERS=10; //number of Gibbs sampling iteration
	public int SAVE_ITER=100;
	
	public String modelSavedDir="ADMModels/{0}_I{1}_T{2}/";
	
	public ADMInference(ACTMDataSet data,ACTMGlobalData globalData, int topicCount, int authorClassCount) {
		// TODO Auto-generated constructor stub
		// TODO Auto-generated constructor stub
		this.DataSet=data;
		this.GlobalData=globalData;
		Model=new ADMModel(data,globalData.getWordCount(),globalData.getAuthorCount(),globalData.getGlobalConferenceCount(),topicCount,authorClassCount);
		Model.InitNewModel();
//		this.probs=new double[Model.D][Model.T];
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
		double DGAMMA=Model.D*Model.ga;
		double CMU=Model.C*Model.mu;
		
		ReOrder();
//		
//		for(int iter=0;iter<N_ITERS;++iter){
//			System.err.println(new Date()+ " Iteration : "+iter);
//			
////			save model per SAVE_ITER
////			if(iter!=0&&iter%SAVE_ITER==0){
////				File dir=new File(modelSavedDir+"IT"+iter+"/");
////				if(!dir.exists()){
////					dir.mkdirs();
////				}
////				this.Model.SaveTempModel(modelSavedDir+"IT"+iter+"/");
////				
////			}
//			
//			for(int i=0;i<DataSet.N;++i){
//	
//				int curWord=DataSet.GlobalIndexWordMap.get(order[i]).Index;
//				ACTMDocument curDoc=DataSet.GlobalIndexDocMap.get(order[i]);
//				int curTopic=Model.z[order[i]];
//				int curAuthorClass=Model.x[order[i]];
//				int curConference=curDoc.getConference().getGlobalIndex();
//				int curAuthor=Model.y[order[i]];
//				
//				
//				Model.nxsum[curAuthorClass]--;
//				Model.nzsum[curTopic]--;
//				Model.nysum[curConference]--;
//				Model.CCD[curConference][curAuthorClass]--;
//				Model.CWT[curWord][curTopic]--;
//				Model.CDA[curAuthorClass][curAuthor]--;
//				
//				double totalProb=0;
//				double [] authorSum=new double[curDoc.getAuthors().size()];
//				double [] topicSum=new double[Model.T];
//				
//				
//				
//				for(int j=0;j<curDoc.getAuthors().size();++j){
//					int authorIndex=curDoc.getAuthors().get(j).getIndex();
//					
//					for(int lll=0;lll<Model.D;++lll){
//					
//						for(int k=0;k<Model.T;++k){
//							double probs=(Model.CWT[curWord][k]+Model.be)/(Model.nzsum[k]+WBETA)
//							*(Model.CTD[k][cur]+Model.ga)/(Model.nxsum[authorIndex]+CGAMMA)
//							*(Model.CTC[k][curConference]+Model.al)/(Model.nysum[curConference]+TALPHA);
//							totalProb+=probs;
//	
//							authorSum[j]+=probs[authorIndex][k];
//							topicSum[k]+=probs[authorIndex][k];
//						}
//					}
//				}
//				 
//				//sample a new topic and author assignment
//				
//				double r =Math.random()*totalProb;
//				
//				int newAuthor=-1,newTopic=-1;
//				
//				double max=0;
//				for(int j=0;j<curDoc.getAuthors().size();++j){
//					max+=authorSum[j];
//					if(max>=r){
//						newAuthor=curDoc.getAuthors().get(j).getIndex();
//						break;
//					}
//				}
//				max=0;
//				for(int k=0;k<Model.T;++k){
//					max+=topicSum[k];
//					if(max>=r){
//						newTopic=k;
//						break;
//					}
//				}
//				
//				Model.z[order[i]]=newTopic;
//				Model.x[order[i]]=newAuthor;
//				Model.CTC[newTopic][curConference]++;
//				Model.CWT[curWord][newTopic]++;
//				Model.CCA[curConference][newAuthor]++;
//				Model.nzsum[newTopic]++;
//				Model.nxsum[newAuthor]++;
//				Model.nysum[curConference]++;
//			}
//		}
//		
//		//updata theta and phi
//		//theta: Probabilities of topics given authors size T x A
//		// phi: Probabilities of words given topics, size W x T
//		//this.CWT=new int[W][T];
//		//this.CTA=new int[T][A];
//		for(int i=0;i<Model.T;++i){
//			for(int j=0;j<Model.W;++j){
//				Model.phi[j][i]=1.0*(Model.be+Model.CWT[j][i])/(WBETA+Model.nzsum[i]);
//			} 
//			
//			for (int j = 0; j < Model.C; j++) {
//				Model.theta[i][j]=1.0*(Model.al+Model.CTC[i][j])/(TALPHA+Model.nysum[j]);
//
//			}
//			
//		}
//		for(int i=0;i<Model.C;++i){
//			for(int j=0;j<Model.A;++j){
//				Model.eta[i][j]=1.0*(Model.ga+Model.CCA[i][j])/(CGAMMA+Model.nxsum[j]);
//			}
//		}
	}
}
