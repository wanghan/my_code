package act.retrieval;

import java.io.IOException;
import org.apache.lucene.index.CorruptIndexException;

import rmi.AssociateResult;

import utils.SerializeUtils;
import act.model.associate.TopicAssociationModel;
import actm.data.Paper;

public class SearchTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int id=2;

			TextSearcher searcher=new TextSearcher();
			TopicAssociationModel assModel=(TopicAssociationModel)SerializeUtils.deSerialize(TopicAssociationModel.storagePath);
			Paper p=searcher.cache.getPaperByTMIndex(id);
			long before=System.currentTimeMillis();
			AssociateResult[] re=new AssociateResult[5];
			for(int i=0;i<5;++i){
				int topicid=assModel.paperRelatedTopic[id][i];
				System.out.print("Topic " +assModel.paperRelatedTopic[3144][i]+" ");
				for(int k=0;k<5;++k){
					String word=searcher.cache.getWordByTmIndex(assModel.topics[topicid].topRelatedWordIds.get(k)).Word;
					System.out.print(word+"; ");
				}
				System.out.println();
				for(int k=0;k<5;++k){
					Paper pp=searcher.cache.getPaperByTMIndex(assModel.topics[topicid].topRelatedPaperIds.get(k)-1);
					System.out.println("\t"+pp.getTitle());
				}
			}
			
//			Connection conn = MysqlConnection.getConnection();
//			DBOperations oper = new DBOperations(conn);
//			
//			TextSearcher searcher=new TextSearcher();
//			
//			TopicAssociationModel assModel=(TopicAssociationModel)SerializeUtils.deSerialize(TopicAssociationModel.storagePath);
//			
//			long before=System.currentTimeMillis();
//			int []result=searcher.searchPaper("image retrieval");
//			long after1=System.currentTimeMillis();
//			System.out.println("Searching runtime (ms):" +(after1-before));
//			for (int i : result) {
//				Paper p=oper.getPaperById(i);
//				for(int j=0;j<5;++j){
//					System.out.print("\tTopic " +assModel.paperRelatedTopic[i][j]+" ");
//					for(int k=0;k<5;++k){
//						String word=searcher.cache.getWordByTmIndex(assModel.topics[assModel.paperRelatedTopic[i][j]].topRelatedWordIds.get(k)).Word;
//						System.out.print(word+"; ");
//					}
//					System.out.println();
//				}
//				System.out.println(i+" "+p.getTitle());
//			}
//			System.out.println("Searching runtime (ms):" +String.valueOf(System.currentTimeMillis()-after1));
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
