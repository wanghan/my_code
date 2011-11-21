package db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.dom4j.DocumentException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.hibernate.DaoAdapter;
import dao.hibernate.DbAuthor;
import dao.hibernate.DbConference;
import dao.hibernate.DbPaper;
import dao.hibernate.HibernateUtil;

import act.corpus.ACMCorpusLoader;
import act.model.ACTModel;
import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;
import actm.data.ACTMGlobalData;
import actm.data.Author;
import actm.data.Conference;

public class Test {

	public static void main(String[] args) {
		testInsertPapersHibernate();
	}

	public static void testInsertConfs() throws DocumentException,
			FileNotFoundException, SQLException {
		Connection conn = MysqlConnection.getConnection();
		DBOperations oper = new DBOperations(conn);
		ACTMGlobalData globalData = new ACTMGlobalData();
		ACTMDataSet data = new ACMCorpusLoader().loadTrainData_Small(
				globalData, null);
		ACTMDataSet testData = new ACMCorpusLoader().loadTestData_Small(
				globalData, null);

		for (Conference conf : globalData.conferenceSet.getConferences()) {
			oper.insertConference(conf);
		}
	}

	public static void testInsertPapers() throws DocumentException,
			IOException, Exception {
		Connection conn = MysqlConnection.getConnection();
		DBOperations oper = new DBOperations(conn);
		
		ACTMGlobalData globalData=ACTMGlobalData.deserialize("act model ngram slide training\\1305783564191.glo");
		ACTMDataSet data = new ACMCorpusLoader().loadTrainData_Small(
				globalData, null);
		ACTMDataSet testData = new ACMCorpusLoader().loadTestData_Small(
				globalData, null);
		int i = 0;
		// for (ACTMDocument paper : data.documentSet) {
		// i++;
		// if(i>=20000){
		// oper.insertPaper(paper.getPaper());
		// System.out.println("Insert paper :"+paper.getPaper().getTitle());
		// }
		// }
		for (ACTMDocument paper : testData.documentSet) {
			oper.insertPaper(paper.getPaper(), paper.getIndex());
			System.out.println("Insert paper :" + paper.getPaper().getTitle());
		}
	}

	public static void testInsertPapersHibernate() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long cityId = null;
		try {
			transaction = session.beginTransaction();
	//		RAMDictionary ram=(RAMDictionary)SerializeUtils.deSerialize(RAMDictionary.storagePath);
			ACTMGlobalData globalData=ACTMGlobalData.deserialize("./ACTModels/1319975613762_I100_T100/1319975613763.glo");
			
			ACTModel model=ACTModel.LoadWholeModel("./ACTModels/1319975613762_I100_T100/1320018826651.model");
	
			HashMap<String, DbAuthor> dbAuthorMap=new HashMap<String, DbAuthor>();
			HashMap<Integer, DbConference> dbConfMap=new HashMap<Integer, DbConference>();
			ArrayList<DbPaper> dbPaperList=new ArrayList<DbPaper>();

			for (ACTMDocument paper : model.dataSet.documentSet) {
				paper.getPaper().setIndex(paper.getIndex());
				DbPaper dbPaper=DaoAdapter.ToDbPaper(paper.getPaper());
				
				//set conference
				DbConference dbConference=null;
				if(dbConfMap.containsKey(paper.getConference().getIndex())){
					dbConference=dbConfMap.get(paper.getConference().getIndex());
				}
				else{
					dbConference=DaoAdapter.ToDbConference(paper.getConference());
					dbConfMap.put(paper.getConference().getIndex(), dbConference);
				}
				dbPaper.setConference(dbConference);
				dbPaper.setDbAuthors(new HashSet<DbAuthor>());
				//set authors
				for (Author author : paper.getPaper().getAuthors()) {
					if(author.getAcmIndex()==null){
						String acmIndex=author.ParseAcmIndex();
						author.setAcmIndex(acmIndex);
					}
					
					DbAuthor dbAuthor=null;
					if(dbAuthorMap.containsKey(author.getAcmIndex())){
						dbAuthor=dbAuthorMap.get(author.getAcmIndex());
					}
					else{
						dbAuthor=DaoAdapter.ToDbAuthor(author);
						dbAuthor.setDbPapers(new HashSet<DbPaper>());
						dbAuthorMap.put(author.getAcmIndex(), dbAuthor);
					}
					dbPaper.getDbAuthors().add(dbAuthor);
				}
				dbPaperList.add(dbPaper);
			}
			
			for (DbConference a : dbConfMap.values()) {
				session.save(a);
			}
			for (DbAuthor a : dbAuthorMap.values()) {
				session.save(a);
			}
			for (DbPaper a : dbPaperList) {
				session.save(a);
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}
}
