package rmi;

import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;

import org.apache.lucene.index.CorruptIndexException;
import org.hibernate.Session;

import dao.hibernate.HibernateUtil;
import db.MysqlConnection;

import utils.SerializeUtils;

import act.model.associate.TopicAssociationModel;
import act.retrieval.TextSearcher;
import actm.data.Paper;

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
	private Connection conn = MysqlConnection.getConnection();
	public SearcherRMI() throws CorruptIndexException, IOException, ClassNotFoundException{
		// TODO Auto-generated constructor stub
		searcher=new TextSearcher();
		associationModel=(TopicAssociationModel)SerializeUtils.deSerialize(TopicAssociationModel.storagePath);
	}
	
	public Integer[] searchPapers(String keywords) throws Exception{
		System.out.println("Searching paper, keyword :"+keywords);
		Integer re[]=searcher.searchPaper(keywords);
		System.out.println("-------------------------------------------------------------------------");
		return re;
	}
	
	public Integer[] searchAuthors(String keywords) throws Exception{
		System.out.println("Searching author, keyword :"+keywords);
		Integer re[]=searcher.searchAuthor(keywords);
		System.out.println("-------------------------------------------------------------------------");
		return re;
	}
	
	public AssociateResult[] getAssociatePapers(int papertmId) throws Exception{
		long before=System.currentTimeMillis();
		AssociateResult[] re=new AssociateResult[5];
		for(int i=0;i<5;++i){
			re[i]=new AssociateResult();
			
			int topicid=associationModel.paperRelatedTopic[papertmId][i];
			StringBuffer sb=new StringBuffer();
			sb.append("Topic " +associationModel.paperRelatedTopic[papertmId][i]+" ");
			for(int k=0;k<5;++k){
				String word=searcher.cache.getWordByTmIndex(associationModel.topics[topicid].topRelatedWordIds.get(k)).Word;
				sb.append(word+"; ");
			}
			re[i].title=sb.toString();
			re[i].list=new Paper[5];
			for(int k=0;k<5;++k){
				re[i].list[k]=searcher.cache.getPaperByTMIndex(associationModel.topics[topicid].topRelatedPaperIds.get(k)-1);
			}
		}
		
		System.out.println("Querying cache runtime (ms):" +String.valueOf(System.currentTimeMillis()-before));
		System.out.println("-------------------------------------------------------------------------");
		
		return re;
	}
}
