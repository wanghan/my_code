package rmi;

import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.lucene.index.CorruptIndexException;

import utils.SerializeUtils;

import act.model.associate.TopicAssociationModel;
import act.retrieval.TextSearcher;

/**
 * 
 * @author wanghan
 *
 */


public class SearcherRMI extends UnicastRemoteObject implements SearcherRMIInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2896113609526682534L;
	private TextSearcher searcher;
	private TopicAssociationModel associationModel;
	
	public SearcherRMI() throws CorruptIndexException, IOException, ClassNotFoundException{
		// TODO Auto-generated constructor stub
		searcher=new TextSearcher();
		associationModel=(TopicAssociationModel)SerializeUtils.deSerialize(TopicAssociationModel.storagePath);
	}
	
	public Integer[] searchPapers(String keywords) throws IOException{
		System.out.println("Searching paper, keyword :"+keywords);
		Integer re[]=searcher.searchPaper(keywords);
		System.out.println("-------------------------------------------------------------------------");
		return re;
	}
	
	public Integer[] searchAuthors(String keywords) throws IOException{
		System.out.println("Searching author, keyword :"+keywords);
		Integer re[]=searcher.searchAuthor(keywords);
		System.out.println("-------------------------------------------------------------------------");
		return re;
	}
	
	public AssociateResult[] getAssociatePapers(int papertmId) throws IOException{
		long before=System.currentTimeMillis();
		AssociateResult[] re=new AssociateResult[5];
		for(int i=0;i<5;++i){
			re[i]=new AssociateResult();
			
			int topicid=associationModel.paperRelatedTopic[papertmId][i];
			re[i].topic=associationModel.topics[topicid];
			StringBuffer sb=new StringBuffer();
			sb.append("Topic : ");
			for(int k=0;k<associationModel.topics[topicid].topRelatedWordIds.size();++k){
				String word=searcher.cache.getWordByTmIndex(associationModel.topics[topicid].topRelatedWordIds.get(k)).Word;
				if(k<5){
					sb.append(word+"; ");
				}
				re[i].topWords.add(word);
			}
			re[i].title=sb.toString();
		}
		
		System.out.println("Querying cache runtime (ms):" +String.valueOf(System.currentTimeMillis()-before));
		System.out.println("-------------------------------------------------------------------------");
		
		return re;
	}
}
