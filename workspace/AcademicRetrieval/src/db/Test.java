package db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.dom4j.DocumentException;

import act.corpus.ACMCorpusLoader;
import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;
import actm.data.ACTMGlobalData;
import actm.data.Conference;
import actm.data.Paper;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection conn = MysqlConnection.getConnection();
			DBOperations oper = new DBOperations(conn);
			Paper p=oper.getPaperById(11);
			
			System.out.println(1);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

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
		globalData.serialize("./"+System.currentTimeMillis()+".glo");
		int i=0;
//		for (ACTMDocument paper : data.documentSet) {
//				i++;
//				if(i>=20000){
//					oper.insertPaper(paper.getPaper());
//					System.out.println("Insert paper :"+paper.getPaper().getTitle());
//				}		
//		}
		for (ACTMDocument paper : testData.documentSet) {
			oper.insertPaper(paper.getPaper(),paper.getIndex());
			System.out.println("Insert paper :"+paper.getPaper().getTitle());
		}
	}
}
