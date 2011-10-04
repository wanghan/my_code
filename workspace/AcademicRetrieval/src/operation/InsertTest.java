/**
 * 
 */
package operation;

import java.sql.Connection;

import utils.SerializeUtils;

import cache.IndexedIDCache;

import db.DBOperations;
import db.MysqlConnection;
import act.model.ACTModel;
import act.retrieval.TextIndexer;
import actm.data.ACTMDocument;
import actm.data.Author;

/**
 * @author wanghan
 *
 */
public class InsertTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
	//		ACTMGlobalData globalData=ACTMGlobalData.deserialize("act model ngram slide training\\1305783564191.glo");
			ACTModel model=ACTModel.LoadWholeModel("act model ngram slide training\\1305783341789.model");
			Connection conn = MysqlConnection.getConnection();
			DBOperations oper = new DBOperations(conn);
			IndexedIDCache idCache=new IndexedIDCache();
			TextIndexer indexer=new TextIndexer(model.dataSet);
			
			int k=0;
			int count_conf=0;
			int count_author=0;
			int count_paper=0;
			for (ACTMDocument paper : model.dataSet.documentSet) {
				if(k==1000){
					break;
				}
				k++;
			
				oper.insertPaper(paper.getPaper(),paper.getIndex());
				if(!idCache.hasPaperID(paper.getPaper().getId())){
					indexer.indexPaper(paper);
					count_paper++;
					idCache.insertPaperID(paper.getPaper().getId());
				}
				if(!idCache.hasConferenceID(paper.getConference().getId())){
					indexer.indexConference(paper.getConference());
					count_conf++;
					idCache.insertConferenceID(paper.getConference().getId());
				}
				for (Author author : paper.getAuthors()) {
					if(!idCache.hasAuthorID(author.getId())){
						indexer.indexAuthor(author);
						count_author++;
						idCache.insertAuthorID(author.getId());
					}
				}
				System.out.println("Insert paper :"+paper.getPaper().getTitle());
				
			}
			
			indexer.dispose();
			SerializeUtils.serialize(idCache, IndexedIDCache.storagePath);
			System.out.println("Insert Paper: "+count_paper);
			System.out.println("Insert Conference: "+count_conf);
			System.out.println("Insert Author: "+count_author);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
