/**
 * 
 */
package act.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import org.dom4j.DocumentException;

import tm.generalmodel.Word;



import act.corpus.ACMCorpusLoader;
import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;
import actm.data.ACTMGlobalData;
import actm.data.Author;
import atm.labeling.LabelingUtils;
import atm.labeling.Parse;

/**
 * @author amy
 *
 */
public class ACTInference {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		
			HashSet<String> ngrams=new HashSet<String>();
			
			Vector<Parse> labels=LabelingUtils.loadCandidateLabelsFromNgramRankingFile("rude1.x2");
			for (Parse parse : labels) {
				ngrams.add(parse.toString());
			}
			
			ACTMGlobalData globalData=new ACTMGlobalData();
			ACTMDataSet data=new ACMCorpusLoader().loadAllData_Small(globalData,ngrams);

			
			ACTModel model=new ACTModel(data,globalData.getWordCount(),globalData.getAuthorCount(),globalData.getGlobalConferenceCount(),100);

			
			model.InitNewModel();
			
			ACTInference infer=new ACTInference(data,model);
			globalData.serialize(infer.modelSavedDir+System.currentTimeMillis()+".glo");
			infer.inference(ngrams,  null, globalData);
			
			infer.Model.SaveFinalModel(infer.modelSavedDir);
			
		
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public ACTModel Model;
	public ACTMDataSet DataSet;
//	private double probs[][];
//	private int[] order;
	

	public int N_ITERS=10; //number of Gibbs sampling iteration
	public int SAVE_ITER=20000; 

	public String modelSavedDir="ACTModels/{0}_I{1}_T{2}/";
	
	public ACTInference(ACTMDataSet data,ACTModel model) {
		// TODO Auto-generated constructor stub
		this.DataSet=data;
		this.Model=model;
//		this.probs=new double[Model.D][Model.T];
//		this.order=new int[DataSet.N];
		this.modelSavedDir=this.modelSavedDir.replace("{0}", String.valueOf(System.currentTimeMillis()));
		this.modelSavedDir=this.modelSavedDir.replace("{1}", String.valueOf(N_ITERS));
		this.modelSavedDir=this.modelSavedDir.replace("{2}",String.valueOf(Model.T));
		File dir=new File(modelSavedDir);
		if(!dir.exists()){
			dir.mkdirs();
		}
//		for(int i=0;i<DataSet.N;++i){
//			order[i]=i;
//		}
	}
//	private void ReOrder(){
//		int temp;
//		for (int i=0; i<(DataSet.N-1); i++) {
//		      // pick a random integer between i and nw
//		      int rp = i + (int) (1.0* (double) (DataSet.N-i) * Math.random());
//		      
//		      // switch contents on position i and position rp
//		      temp = order[rp];
//		      order[rp]=order[i];
//		      order[i]=temp;
//		  }
//	}
//	
	public void inference(HashSet<String> ngrams, ACTMDataSet testdata,ACTMGlobalData globalData) throws FileNotFoundException, DocumentException{
		double WBETA=Model.be*Model.W;
		double TALPHA=Model.T*Model.al;
		double CMU=Model.C*Model.mu;
		
//		ReOrder();
		
		for(int iter=0;iter<N_ITERS;++iter){
			System.err.println(new Date()+ " Iteration : "+iter);
			
		//	measure pp
			if(iter!=0&&iter%SAVE_ITER==0){
//				for(int i=0;i<Model.T;++i){
//					for(int j=0;j<Model.W;++j){
//						Model.phi[j][i]=1.0*(Model.be+Model.CWT[j][i])/(WBETA+Model.nzsum[i]);
//					} 
//					
//					for (int j = 0; j < Model.A; j++) {
//						Model.theta[i][j]=1.0*(Model.al+Model.CTA[i][j])/(TALPHA+Model.nxsum[j]);
//
//					}
//					for (int j = 0; j < Model.C; j++) {
//						Model.psi[j][i]=1.0*(Model.mu+Model.CCT[j][i])/(CMU+Model.nzdsum[i]);
//
//					}
//				}
//				
//				double ppx=ACTEvaluation.measurePPX(Model, testdata);
////				double averEtropy=ACTEvaluation.measureAverEntropy(Model);
////				double averKL=ACTEvaluation.measureAverKL(Model);
//				System.out.println(ppx);
////				System.out.println(averEtropy);
////				System.out.println(averKL);
//				try {
//					Model.SaveFinalModel(modelSavedDir+iter+"_");
//				} catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
				
			}

			for (ACTMDocument doc : DataSet.documentSet) {

				int oldTopic=-1;
				
				for(int i=0;i<doc.getBagOfWordSize();++i){
					ACTMDocument curDoc=doc;
					int curConference=curDoc.getConference().getGlobalIndex();
					Word curWord=doc.getBagOfWords().get(i);
					int curWordGloIndex=doc.getBagOfWordGloIndexes().get(i);
					int curTopic=Model.z[curWordGloIndex];
					oldTopic=curTopic;
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
					
					
					Iterator<Author> ita=curDoc.getAuthors().iterator();
					while(ita.hasNext()){
						Author curA=ita.next();
						int authorIndex=curA.getIndex();
						
						
						for(int k=0;k<Model.T;++k){
							double probs=(Model.CWT[curWord][k]+Model.be)/(Model.nzsum[k]+WBETA)
							*(Model.CTA[k][authorIndex]+Model.al)/(Model.nxsum[authorIndex]+TALPHA)
							*(Model.CCT[curConference][k]+Model.mu)/(Model.nzdsum[k]+CMU);
							
							totalProb+=probs;
							
							authorSum[curA.getIndex()]+=probs;
							topicSum[k]+=probs;
						}
					}
					 
					//sample a new topic and author assignment
					
					double r =Math.random()*totalProb;
					
					int newAuthor=-1,newTopic=-1;
					
					double max=0;
					Iterator<Author> ita1=curDoc.getAuthors().iterator();
					while(ita1.hasNext()){
						Author curA=ita1.next();
						max+=authorSum[curA.getIndex()];
						if(max>=r){
							newAuthor=curA.getIndex();
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
//					Model.CCT[curConference][oldTopic]+=curWord.getSize();
//					Model.nzdsum[oldTopic]+=curWord.getSize();
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
				Model.phi[j][i]=1.0*(Model.be+Model.CWT[j][i])/(WBETA+Model.nzsum[i]);
			} 
			
			for (int j = 0; j < Model.A; j++) {
				Model.theta[i][j]=1.0*(Model.al+Model.CTA[i][j])/(TALPHA+Model.nxsum[j]);

			}
			for (int j = 0; j < Model.C; j++) {
				Model.psi[j][i]=1.0*(Model.mu+Model.CCT[j][i])/(CMU+Model.nzdsum[i]);

			}
		}
		
	}
}
