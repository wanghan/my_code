/**
 * 
 */
package act.retrieval;

import java.util.TreeMap;
import tm.generalmodel.Word;
import utils.SerializeUtils;
import act.model.ACTModel;
import actm.data.ACTMGlobalData;

/**
 * @author wanghan
 *
 */
public class ModelIndexer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ACTMGlobalData globalData=ACTMGlobalData.deserialize("act model ngram slide training\\1305783564191.glo");
	//		ACTModel model=ACTModel.LoadWholeModel("act model ngram slide training\\1305783341789.model");
			
			SerializeUtils.serialize(globalData.wordSet.indexVocabularyMap, indexLocation+"word.hash");
			TreeMap<Integer, Word> treemap=new TreeMap<Integer, Word>();
			for (Integer i : globalData.wordSet.indexVocabularyMap.keySet()) {
				treemap.put(i, globalData.wordSet.indexVocabularyMap.get(i));
			}
			SerializeUtils.serialize(treemap, indexLocation+"word.tree");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private ACTModel model;
	private ACTMGlobalData globalData;
	private static String indexLocation="index\\model\\";
	
	public ModelIndexer(ACTModel m,ACTMGlobalData globalData) {
		// TODO Auto-generated constructor stub
		this.model=m;
		this.globalData=globalData;
	}
	
	
	public void indexAuthors(){
		
	}
}
