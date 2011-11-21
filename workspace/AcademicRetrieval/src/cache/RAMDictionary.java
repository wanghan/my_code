/**
 * 
 */
package cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

import act.model.ACTModel;
import actm.data.ACTMDocument;
import actm.data.ACTMGlobalData;
import actm.data.Author;
import actm.data.Paper;

import tm.generalmodel.Word;
import utils.SerializeUtils;

/**
 * @author wanghan
 *
 */
public class RAMDictionary implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1229771300080966912L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ACTMGlobalData globalData=ACTMGlobalData.deserialize("./ACTModels/1319975613762_I100_T100/1319975613763.glo");
			
			ACTModel model=ACTModel.LoadWholeModel("./ACTModels/1319975613762_I100_T100/1320018826651.model");
			
			RAMDictionary ram=new RAMDictionary(model,globalData);
			
			SerializeUtils.serialize(ram, RAMDictionary.storagePath);
			
//			long cur1=System.currentTimeMillis();
//			RAMDictionary test=(RAMDictionary)SerializeUtils.deSerialize(RAMDictionary.storagePath);
//			
//			
//			System.out.println(System.currentTimeMillis()-cur1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	HashMap<Integer, Word> tmIndexWordMap;
	HashMap<String, Word> stringWordMap;
	
	HashMap<Integer, Author> tmIndexAuthorMap;
	HashMap<String, Author> acmIndexAuthorMap;
	
	HashMap<Integer, Paper> tmIndexDocumentMap;
	HashMap<Integer, String> tmIndexConferenceNameMap; //global index
	
	public static String storagePath="index\\ram\\data.ram";
	
	public RAMDictionary(ACTModel model, ACTMGlobalData globalData) {
		// TODO Auto-generated constructor stub
		this.acmIndexAuthorMap=new HashMap<String, Author>();
		this.stringWordMap=new HashMap<String, Word>();
		this.tmIndexAuthorMap=new HashMap<Integer, Author>();
		this.tmIndexDocumentMap=new HashMap<Integer, Paper>();
		this.tmIndexWordMap=new HashMap<Integer, Word>();
		this.tmIndexConferenceNameMap=new HashMap<Integer, String>();
		
		for (Integer index : globalData.conferenceSet.globalIndexNameMap.keySet()) {
			this.tmIndexConferenceNameMap.put(index, globalData.conferenceSet.globalIndexNameMap.get(index));
			
		}
		
		for (Integer index : globalData.wordSet.indexVocabularyMap.keySet()) {
			Word w=globalData.wordSet.indexVocabularyMap.get(index);
			w.topicWeight=new double[model.T];
			for(int i=0;i<model.T;++i){
				w.topicWeight[i]=model.phi[w.Index][i];
			}
			stringWordMap.put(w.Word, w);
			tmIndexWordMap.put(w.Index, w);
		}
		
		for (Integer index : globalData.authorSet.indexAuthorMap.keySet()) {
			Author a=globalData.authorSet.indexAuthorMap.get(index);
			a.topicWeight=new double[model.T]; 
			for(int i=0;i<model.T;++i){
				a.topicWeight[i]=model.theta[i][a.getIndex()];
			}
			tmIndexAuthorMap.put(a.getIndex(), a);
			acmIndexAuthorMap.put(a.getAcmIndex(), a);
		}
		
		for (ACTMDocument doc : model.dataSet.documentSet) {
			doc.getPaper().topicWeight=new  double[model.T]; 
			for(int i=0;i<model.T;++i){
				doc.getPaper().topicWeight[i]=0;
				Iterator<Author> ita=doc.getPaper().getAuthors().iterator();
				while(ita.hasNext()){
					Author cur=ita.next();
					doc.getPaper().topicWeight[i]+=model.theta[i][cur.getIndex()];
				}
				doc.getPaper().topicWeight[i]/=doc.getPaper().getAuthors().size();
			}
			tmIndexDocumentMap.put(doc.getIndex(), doc.getPaper());
		}
		
		
	}
	
	public Author getAuthorByTMIndex(int id){
		if(tmIndexAuthorMap.containsKey(id)){
			return tmIndexAuthorMap.get(id);
		}
		return null;
	}
	public Author getAuthorByACMIndex(String id){
		if(acmIndexAuthorMap.containsKey(id)){
			return acmIndexAuthorMap.get(id);
		}
		return null;
	}
	public Word getWordByString(String s){
		
		if(stringWordMap.containsKey(s.toLowerCase())){
			return stringWordMap.get(s.toLowerCase());
		}
		return null;
	}
	public Word getWordByTmIndex(int i){
		
		if(tmIndexWordMap.containsKey(i)){
			return tmIndexWordMap.get(i);
		}
		return null;
	}
	public Paper getPaperByTMIndex(int id){
		
		if(tmIndexDocumentMap.containsKey(id)){
			return tmIndexDocumentMap.get(id);
		}
		return null;
	}
}
