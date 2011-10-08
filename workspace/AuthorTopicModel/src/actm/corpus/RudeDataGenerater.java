/**
 * 
 */
package actm.corpus;

import java.io.File;
import java.io.FileWriter;

import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;
import actm.data.ACTMGlobalData;

/**
 * @author wanghan
 *
 */
public class RudeDataGenerater {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ACTMGlobalData globalData=new ACTMGlobalData();
			ACTMDataSet datas=new ACMCorpusLoader().loadTrainData_Small(globalData,null);
			ACTMDataSet testdatas=new ACMCorpusLoader().loadTestData_Small(globalData,null);
			String file="rudedata1.dat";
			File fileout =new File(file);
			FileWriter writer=new FileWriter(fileout);
			for (ACTMDocument doc : datas.documentSet) {
				writer.write(doc.getPaper().getAbstractContent());
				writer.write("\r\n");
				writer.flush();
			}
			for (ACTMDocument doc : testdatas.documentSet) {
				writer.write(doc.getPaper().getAbstractContent());
				writer.write("\r\n");
				writer.flush();
			}
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
