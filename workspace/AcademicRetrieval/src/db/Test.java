package db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.dom4j.DocumentException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import dao.test.CityDAO;
import dao.test.HibernateUtil;

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

	public static void testCityDAO() {
		CityDAO cityDAO = new CityDAO();
		long cityId1 = cityDAO.saveCity("New York");
		long cityId2 = cityDAO.saveCity("Rio de Janeiro");
		long cityId3 = cityDAO.saveCity("Tokyo");
		long cityId4 = cityDAO.saveCity("London");
		cityDAO.listCities();
		cityDAO.updateCity(cityId4, "Paris");
		cityDAO.deleteCity(cityId3);
		cityDAO.listCities();
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
			ACTModel model=ACTModel.LoadWholeModel("./ACTModels/1319975613762_I100_T100/1320018826651.model");
			ACTMGlobalData globalData=ACTMGlobalData.deserialize("./ACTModels/1319975613762_I100_T100/1319975613763.glo");
			int i = 0;
			// for (ACTMDocument paper : data.documentSet) {
			// i++;
			// if(i>=20000){
			// oper.insertPaper(paper.getPaper());
			// System.out.println("Insert paper :"+paper.getPaper().getTitle());
			// }
			// }
			int k=0;
			int authorIndex=0;
			for (ACTMDocument paper : model.dataSet.documentSet) {
				paper.getPaper().setIndex(paper.getIndex());
				session.save(paper.getPaper().getConference());
				for (Author author : paper.getPaper().getAuthors()) {
					
					if(author.getAcmIndex()==null){
						String acmIndex=author.ParseAcmIndex();
						author.setAcmIndex(acmIndex);
					}
					author.setId(Long.parseLong(author.getAcmIndex()));
					session.save(author);
				}
				session.save(paper.getPaper());
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
