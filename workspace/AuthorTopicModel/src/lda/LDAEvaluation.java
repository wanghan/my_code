/**
 * 
 */
package lda;

import java.io.FileNotFoundException;
import org.dom4j.DocumentException;

import tm.generalmodel.Word;

import actm.corpus.ACMCorpusLoader;
import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;
import actm.data.ACTMGlobalData;

/**
 * @author wanghan
 *
 */
public class LDAEvaluation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ACTMGlobalData globalData;
		try {
			globalData = ACTMGlobalData.deserialize("LDAModels\\1303042110156_I10_T100\\Slide_S0\\1303042344843.global");
			
			LDAModel model=LDAModel.LoadWholeModel("LDAModels\\1303042110156_I10_T100\\Slide_S0\\1303042221421.model");
			
			System.out.println(LDAEvaluation.evaluateModel(model, globalData));;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
	
	public static double evaluateModel(LDAModel model,ACTMGlobalData globalData) throws FileNotFoundException, DocumentException{
		ACTMDataSet testdata=new ACMCorpusLoader().loadTestData(globalData);
		
		double ppx=measurePPX(model, globalData, testdata);
		return ppx;
	}
	
	private static double measurePPX(LDAModel model,ACTMGlobalData globalData,ACTMDataSet testdata){
		
		double result=0;
		
		double [] thetaT=new double[model.T];
		for(int i=0;i<model.T;++i){
			thetaT[i]=0;
			for(int j=0;j<model.D;++j){
				thetaT[i]+=model.theta[i][j];
			}
		}
		
		for (ACTMDocument doc : testdata.documentSet) {
			
			for (Word word : doc.getBagOfWords()) {
				int wordIndex=word.Index;
				
				double temp=0;
				if(wordIndex>=model.phi.length){
					continue;
				}
				for(int i=0;i<model.T;++i){
					
						
						temp+=model.phi[wordIndex][i];
					
				}

				result+=Math.log(temp);
			}
		}
		return Math.exp(-1.0*result/testdata.N);
	}
}
