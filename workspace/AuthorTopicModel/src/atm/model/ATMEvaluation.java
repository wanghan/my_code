/**
 * 
 */
package atm.model;

import tm.generalmodel.Word;
import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;
import actm.data.ACTMGlobalData;
import actm.data.Author;

/**
 * @author wanghan
 *
 */
public class ATMEvaluation {
public static double measurePPX(AuthorTopicModel model,ACTMGlobalData globalData,ACTMDataSet testdata){
		
		
//		double [][] theTA=new double [model.T][model.A]
		
		double result=0;
		for (ACTMDocument doc : testdata.documentSet) {
			
			for (Word word : doc.getBagOfWords()) {
				int wordIndex=word.Index;
				
			
				if(wordIndex>=model.phi.length){
					continue;
				}
				
				double authorsum=0;
				for (Author author : doc.getAuthors()) {
					int authorIndex=author.getIndex();
					
					double wordSum=0;
					
					if(authorIndex>=model.A){
						continue;
					}
					for(int i=0;i<model.T;++i){
						wordSum+=model.phi[wordIndex][i]*model.theta[i][authorIndex];
					}
					
					authorsum+=wordSum;
				}
				
				if(authorsum!=0){
				
					authorsum=authorsum/doc.getAuthors().size();
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
	
}
