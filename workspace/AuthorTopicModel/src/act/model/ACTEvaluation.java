/**
 * 
 */
package act.model;

import java.util.HashSet;
import java.util.Vector;

import tm.generalmodel.Word;
import utils.KLDivergence;
import act.online.model.ACTOnlineModel;
import actm.corpus.ACMCorpusLoader;
import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;
import actm.data.ACTMGlobalData;
import actm.data.Author;
import atm.labeling.LabelingUtils;
import atm.labeling.Parse;

/**
 * @author wanghan
 *
 */
public class ACTEvaluation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			HashSet<String> ngrams=new HashSet<String>();
			
			Vector<Parse> labels=LabelingUtils.loadCandidateLabelsFromNgramRankingFile("rude.x2");
			for (Parse parse : labels) {
				ngrams.add(parse.toString());
			}
			
			ACTOnlineModel model=ACTOnlineModel.LoadWholeModel("ACTOnlineModels/1305479706644_I50_T100/Slide_S{3}/1305516558577.model");
			ACTMGlobalData globalData=ACTMGlobalData.deserialize("ACTOnlineModels/1305479706644_I50_T100/Slide_S{3}/1305516586000.glo");
			ACTMDataSet testData=new ACMCorpusLoader().loadTestData_Small(globalData,null);

			double ppx=ACTEvaluation.measurePPX(model, testData);
			
			System.out.println(ppx);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	public static double measurePPX(ACTModel model,ACTMDataSet testdata){
		
		
//		double [][] theTA=new double [model.T][model.A]
		
		double result=0;
		int totalN=0;
		for (ACTMDocument doc : testdata.documentSet) {
			
			for (Word word : doc.getBagOfWords()) {
				int wordIndex=word.Index;
				
			
				if(wordIndex>=model.phi.length){
					continue;
				}
				totalN++;
				double authorsum=0;
				int authorCount=0;
				for (Author author : doc.getAuthors()) {
					int authorIndex=author.getIndex();
					
					double wordSum=0;
					
					if(authorIndex>=model.A){
						continue;
					}
					for(int i=0;i<model.T;++i){
						wordSum+=model.phi[wordIndex][i]*model.theta[i][authorIndex];
					}
					authorCount++;
					authorsum+=wordSum;
				}
				
				if(authorsum!=0){
				
					authorsum=authorsum/authorCount;
					result+=Math.log(authorsum);
				}
				
//				double sum=0;
//				
//				for(int i=0;i<model.T;++i){
//					for(int j=0;j<model.C;++j){
//						for(int k=0;k<model.A;++k){
//							sum+=model.phi[wordIndex][i]*model.eta[j][k]*model.theta[i][j];
//						}
//					}
//				}
//				result+=Math.log(sum);
			}
		}
		return Math.exp(-1.0*result/testdata.N);
	}
	
	public static double measurePPX1(ACTModel model,ACTMDataSet testdata){
		
		
//		double [][] theTA=new double [model.T][model.A]
		
		double result=0;
		for (ACTMDocument doc : testdata.documentSet) {
			
			for (Word word : doc.getBagOfWords()) {
				int wordIndex=word.Index;
				
			
				if(wordIndex>=model.phi.length){
					continue;
				}
				
				double authorsum=0;
				int authorCount=0;
				for (Author author : doc.getAuthors()) {
					int authorIndex=author.getIndex();
					
					double wordSum=0;
					
					if(authorIndex>=model.A){
						continue;
					}
					for(int i=0;i<model.T;++i){
						wordSum+=model.phi[wordIndex][i]*model.theta[i][authorIndex]*model.nxsum[authorIndex]/model.dataSet.N;
					}
		//			authorCount++;
					authorsum+=wordSum;
				}
				
				if(authorsum!=0){
				
	//				authorsum=authorsum/authorCount;
					result+=Math.log(authorsum);
				}
			}
		}
		return Math.exp(-1.0*result/testdata.N);
	}
	
	public static double measureAverEntropy(ACTModel model){
		double result=0;
		for(int i=0;i<model.T;++i){
			double pz=1.0*model.nzsum[i]/model.dataSet.N;
			result-=pz*Math.log(pz)/Math.log(2);
		}
		return result;
	}
	
	public static double measureAverKL(ACTModel model){
		double result=0;
	//	double[][] klMatrix=new double[model.T][model.T];
		for(int i=0;i<model.T;++i){
			for(int j=0;j<model.T;++j){
				if(i==j){
					;
				}
				else{
				//	klMatrix[i][j]=KLDivergence.calKLByColumn(model.phi, i, j);
					result+=KLDivergence.calKLByColumn(model.phi, i, j);
				}
			}
		}
		return result/model.T/model.T;
		
		
	}
}
