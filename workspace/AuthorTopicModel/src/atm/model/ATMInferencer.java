/**
 * 
 */
package atm.model;

import java.io.File;
import java.util.Date;

import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;

/**
 * @author wanghan
 *
 */
public class ATMInferencer {

	public AuthorTopicModel Model;
	public ACTMDataSet DataSet;
	public int N_ITERS=20; //number of Gibbs sampling iteration
	public int SAVE_ITER=1000;
	
	private double probs[][];
	private int[] order;
	
	private String modelSavedDir="Models/{0}_I{1}_T{2}/";
	
	public ATMInferencer(ACTMDataSet data, AuthorTopicModel model) {
		// TODO Auto-generated constructor stub
		this.DataSet=data;
		Model=model;
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
	
	
	public void Inferencer(){
		double WBETA=Model.beta*Model.W;
		double TALPHA=Model.T*Model.alpha;

		ReOrder();
		
		for(int iter=0;iter<N_ITERS;++iter){
	//		save model per SAVE_ITER
			if(iter!=0&&iter%SAVE_ITER==0){
				File dir=new File(modelSavedDir+"IT"+iter+"/");
				if(!dir.exists()){
					dir.mkdirs();
				}
				this.Model.SaveModel(modelSavedDir+"IT"+iter+"/");
				this.SaveModel(modelSavedDir+"IT"+iter+"/");
			}
			System.err.println(new Date()+ " Iteration : "+iter);
			
			for(int i=0;i<DataSet.N;++i){
				int curWord=DataSet.GlobalIndexWordMap.get(order[i]).Index;
				ACTMDocument curDoc=DataSet.GlobalIndexDocMap.get(order[i]);
				int curTopic=Model.z[order[i]];
				int curAuthor=Model.x[order[i]];
				
				Model.nxsum[curAuthor]--;
				Model.nzsum[curTopic]--;
				Model.CTA[curTopic][curAuthor]--;
				Model.CWT[curWord][curTopic]--;
				
				double totalProb=0;
				
				for(int j=0;j<curDoc.getAuthors().size();++j){
					int authorIndex=curDoc.getAuthors().get(j).getIndex();
					
					for(int k=0;k<Model.T;++k){
						probs[authorIndex][k]=(Model.CWT[curWord][k]+Model.beta)/(Model.nzsum[k]+WBETA)
						*(Model.CTA[k][authorIndex]+Model.alpha)/(Model.nxsum[authorIndex]+TALPHA);
						totalProb+=probs[authorIndex][k];
					}
				}
				
				
				//sample a new topic and author assignment
				
				double r =Math.random()*totalProb;
				
				int newAuthor=0,newTopic=0;
				
				double[] authorSum=new double[curDoc.getAuthors().size()];
				double [] topicSum=new double[Model.T];
				
				for(int j=0;j<curDoc.getAuthors().size();++j){
					int authorIndex=curDoc.getAuthors().get(j).getIndex();
					for(int k=0;k<Model.T;++k){
						authorSum[j]+=probs[authorIndex][k];
						topicSum[k]+=probs[authorIndex][k];
					}
				}
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
//				for(int j=0;j<curDoc.Authors.size();++j){
//					int authorIndex=curDoc.Authors.get(j).Index;
//					for(int k=0;k<Model.T;++k){	
//						if(max>=r){
//							newAuthor=authorIndex;
//							newTopic=k;
//							k=Model.T;
//							j=curDoc.Authors.size();
//							flag=0;
//							break;
//						}
//						else{
//							max+=probs[authorIndex][k];
//						}
//					}
//				}
//				if(flag==1){
//					newAuthor=curDoc.Authors.get(curDoc.Authors.size()-1).Index;
//					newTopic=Model.T-1;
//				}
				Model.z[order[i]]=newTopic;
				Model.x[order[i]]=newAuthor;
				Model.CWT[curWord][newTopic]++;
				Model.CTA[newTopic][newAuthor]++;
				Model.nzsum[newTopic]++;
				Model.nxsum[newAuthor]++;
			}
			
//			int globalWordIndex=0;
//
//			for(int i=0;i<Model.DataSet.documentSet.size();++i){
//				Document doc=Model.DataSet.documentSet.get(i);
//				for (Word word : doc.BagOfWord) {
//					
//					
//					int curWord=word.Index;
//					int count=1;
//					
//					int curTopic=Model.z[order[globalWordIndex]];
//					int curAuthor=Model.x[order[globalWordIndex]];
//					
//					Model.nxsum[curAuthor]-=count;
//					Model.nzsum[curTopic]-=count;
//					Model.CTA[curTopic][curAuthor]-=count;
//					Model.CWT[curWord][curTopic]-=count;
//					
//					double totalProb=0;
//					
//					for(int j=0;j<doc.Authors.size();++j){
//						int authorIndex=doc.Authors.get(j).Index;
//						
//						for(int k=0;k<Model.T;++k){
//							probs[authorIndex][k]=(Model.CWT[curWord][k]+Model.beta)/(Model.nzsum[k]+WBETA)
//							*(Model.CTA[k][authorIndex]+Model.alpha)/(Model.nxsum[authorIndex]+TALPHA);
//							totalProb+=probs[authorIndex][k];
//						}
//					}
//					
//					
//					//sample a new topic and author assignment
//					
//					double r =Math.random()*totalProb;
//					double max=0;
//					int newAuthor=0,newTopic=0;
//					for(int j=0;j<doc.Authors.size();++j){
//						int authorIndex=doc.Authors.get(j).Index;
//						for(int k=0;k<Model.T;++k){	
//							if(max>=r){
//								newAuthor=authorIndex;
//								newTopic=k;
//								k=Model.T;
//								j=doc.Authors.size();
//								break;
//							}
//							else{
//								max+=probs[authorIndex][k];
//							}
//						}
//					}
//						
//					Model.z[order[globalWordIndex]]=newTopic;
//					Model.x[order[globalWordIndex]]=newAuthor;
//					Model.CWT[curWord][newTopic]+=count;
//					Model.CTA[newTopic][newAuthor]+=count;
//					Model.nzsum[newTopic]+=count;
//					Model.nxsum[newAuthor]+=count;
//					globalWordIndex++;
//
//				}
//				
//			}
		}
		
		//updata theta and phi
		//theta: Probabilities of topics given authors size T x A
		// phi: Probabilities of words given topics, size W x T
		//this.CWT=new int[W][T];
		//this.CTA=new int[T][A];
		for(int i=0;i<Model.T;++i){
			for(int j=0;j<Model.W;++j){
				Model.phi[j][i]=1.0*(Model.beta+Model.CWT[j][i])/(WBETA+Model.nzsum[i]);
			}
			for(int j=0;j<Model.A;++j){
				Model.theta[i][j]=1.0*(Model.alpha+Model.CTA[i][j])/(TALPHA+Model.nxsum[j]);
			}
		}
	}
	public void SaveModel(){
		this.Model.SaveFinalModel(modelSavedDir);
	}
	public void SaveModel(String dir){
		this.Model.SaveFinalModel(dir);
	}
}
