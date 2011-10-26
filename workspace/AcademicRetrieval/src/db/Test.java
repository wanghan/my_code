package db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.dom4j.DocumentException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dao.test.City;
import dao.test.CityDAO;
import dao.test.HibernateUtil;

import act.corpus.ACMCorpusLoader;
import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;
import actm.data.ACTMGlobalData;
import actm.data.Conference;
import actm.data.Paper;

public class Test {

	public static void main(String[] args) {

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
			IOException, SQLException {
		Connection conn = MysqlConnection.getConnection();
		DBOperations oper = new DBOperations(conn);
		ACTMGlobalData globalData = new ACTMGlobalData();
		ACTMDataSet data = new ACMCorpusLoader().loadTrainData_Small(
				globalData, null);
		ACTMDataSet testData = new ACMCorpusLoader().loadTestData_Small(
				globalData, null);
		globalData.serialize("./" + System.currentTimeMillis() + ".glo");
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
			ACTMGlobalData globalData = new ACTMGlobalData();
			ACTMDataSet data = new ACMCorpusLoader().loadTrainData_Small(
					globalData, null);
			ACTMDataSet testData = new ACMCorpusLoader().loadTestData_Small(
					globalData, null);
			globalData.serialize("./" + System.currentTimeMillis() + ".glo");
			int i = 0;
			// for (ACTMDocument paper : data.documentSet) {
			// i++;
			// if(i>=20000){
			// oper.insertPaper(paper.getPaper());
			// System.out.println("Insert paper :"+paper.getPaper().getTitle());
			// }
			// }
			int k=0;
			for (ACTMDocument paper : testData.documentSet) {
				if(k++<50)
					break;
				paper.getPaper().Index=paper.getIndex();
				cityId = (Long) session.save(paper.getPaper());
				
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
