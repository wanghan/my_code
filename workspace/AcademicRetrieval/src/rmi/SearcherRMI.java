package rmi;

import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;

import org.apache.lucene.index.CorruptIndexException;

import db.DBOperations;
import db.MysqlConnection;

import utils.SerializeUtils;

import act.model.associate.TopicAssociationModel;
import act.retrieval.TextSearcher;
import actm.data.Author;
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
	private DBOperations oper = new DBOperations(conn);
	public SearcherRMI() throws CorruptIndexException, IOException, ClassNotFoundException{
		// TODO Auto-generated constructor stub
		searcher=new TextSearcher();
		associationModel=(TopicAssociationModel)SerializeUtils.deSerialize(TopicAssociationModel.storagePath);
	}
	
	public Paper[] searchPapers(String keywords) throws Exception{
		System.out.println("Searching paper, keyword :"+keywords);
		long before=System.currentTimeMillis();
		int re[]=searcher.searchPaper(keywords);
		long afterIndex=System.currentTimeMillis();
		System.out.println("Searching index runtime (ms):" +String.valueOf(afterIndex-before));
		Paper re1[]=new Paper[re.length];
		for(int i=0;i<re.length;++i){
			re1[i]=oper.getPaperById(re[i]);
		}
		System.out.println("Searching db runtime (ms):" +String.valueOf(System.currentTimeMillis()-before));
		System.out.println("-------------------------------------------------------------------------");
		return re1;
	}
	
	public Author[] searchAuthors(String keywords) throws Exception{
		System.out.println("Searching author, keyword :"+keywords);
		long before=System.currentTimeMillis();
		int re[]=searcher.searchAuthor(keywords);
		long afterIndex=System.currentTimeMillis();
		System.out.println("Searching index runtime (ms):" +String.valueOf(afterIndex-before));
		Author re1[]=new Author[re.length];
		for(int i=0;i<re.length;++i){
			re1[i]=oper.getAuthorById(re[i]);
		}
		System.out.println("Querying db runtime (ms):" +String.valueOf(System.currentTimeMillis()-before));
		System.out.println("-------------------------------------------------------------------------");
		return re1;
	}
	public String getPaperTitleById(int id) throws Exception{
		return oper.getPaperById(id).getTitle();
	}
	public AssociateResult[] getAssociatePapers(int paperId) throws Exception{
		long before=System.currentTimeMillis();
		AssociateResult[] re=new AssociateResult[5];
		for(int i=0;i<5;++i){
			re[i]=new AssociateResult();
			
			int topicid=associationModel.paperRelatedTopic[paperId][i];
			StringBuffer sb=new StringBuffer();
			sb.append("Topic " +associationModel.paperRelatedTopic[paperId][i]+" ");
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
