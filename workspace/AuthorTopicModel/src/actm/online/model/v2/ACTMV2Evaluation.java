/**
 * 
 */
package actm.online.model.v2;

import tm.generalmodel.Word;
import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;
import actm.data.ACTMGlobalData;
import actm.data.Author;
import actm.model.v2.AuthorConferenceTopicModelV2;

/**
 * @author wanghan
 *
 */
public class ACTMV2Evaluation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ACTMGlobalData globalData;
//		try {
//			globalData = ACTMGlobalData.deserialize("ACTMV2OnlineModels\\1302961806188_I10_T100\\Slide_S10\\1302962863900.glo");
//			
//			AuthorConferenceTopicOnlineModelV2 model=AuthorConferenceTopicOnlineModelV2.LoadWholeModel("ACTMV2OnlineModels\\1302961806188_I10_T100\\Slide_S10\\1302962844169.model");
//			
//			System.out.println(ACTMV2Evaluation.measurePPX(model, globalData));;
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
	}

//	public static double evaluateModel(AuthorConferenceTopicOnlineModelV2 model,ACTMGlobalData globalData) throws FileNotFoundException, DocumentException{
//		ACTMDataSet testdata=new ACMCorpusLoader().loadTestData(globalData);
//		
//		double ppx=measurePPX(model, globalData, testdata);
//		return ppx;
//	}
	
	public static double measurePPX(AuthorConferenceTopicModelV2 model,ACTMGlobalData globalData,ACTMDataSet testdata){
		
		
//		double [][] theTA=new double [model.T][model.A]
		
		double result=0;
		for (ACTMDocument doc : testdata.documentSet) {
			
			int confIndex=doc.getConference().getGlobalIndex();
			
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
						wordSum+=model.phi[wordIndex][i]*model.eta[confIndex][authorIndex]*model.theta[i][confIndex];
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
