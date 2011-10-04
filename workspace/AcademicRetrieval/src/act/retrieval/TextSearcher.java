package act.retrieval;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


import cache.RAMDictionary;

import tm.generalmodel.Word;
import utils.KeyValuePair;
import utils.KeyValuePairSorting;
import utils.SerializeUtils;
import utils.StringUtils;

import actm.data.ACTMDataSet;
import actm.data.ACTMGlobalData;
import actm.data.Author;
import actm.data.Paper;

public class TextSearcher implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6139944632226504506L;
	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		testSearchAuthors();
//		testSearchConfs();
//		testSearchPapers();
//	}
//
//	private static void testSearchAuthors() {
//		try {
//
//			HashMap<String, ACTMDocument> titleDocMap = new HashMap<String, ACTMDocument>();
//
//			ACTMGlobalData globalData=ACTMGlobalData.deserialize("ACTModels\\act model ngram i=50 t=100\\1305507776964.glo");
//			ACTMDataSet data = new ACMCorpusLoader().loadTrainData_Small(
//					globalData, null);
//
//			HashMap<String, Author> authorIndexAuthorMap = new HashMap<String, Author>();
//			for (ACTMDocument doc : data.documentSet) {
//				for (Author author : doc.getAuthors()) {
//					Author curA = authorIndexAuthorMap.get(author.getAcmIndex());
//					if (curA == null) {
//						authorIndexAuthorMap.put(author.getAcmIndex(), author);
//					}
//				}
//			}
//
////			ACTModel model = ACTModel
////			.LoadWholeModel("ACTModels\\act model ngram i=50 t=100\\1305783159620.model");
//
//			System.out.println("begin searching");
//
//			String[] querys = new String[] { "machine translation",
//					"language model", "supervised learning", "xml database",
//					"wireless network", "search engine", "computing education",
//					"relevance feedback", "object oriented programming",
//					"information retrieval", "virtual reality",
//					"user interface design", "support vector machine" };
//
//			FileWriter writer = new FileWriter(new File("queryresult_author.txt"));
//
//			for (String query : querys) {
//				System.out.println(query);
//				writer.append(query + "\r\n");
//				new TextSearcher().searchAuthor(TextIndexer.authorIndexDir,
//						query.toLowerCase(), globalData, data, null,
//						authorIndexAuthorMap, writer,null);
//			}
//			writer.flush();
//			writer.close();
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//
//	private static void testSearchConfs() {
//		try {
////			HashSet<String> ngrams=new HashSet<String>();
////			
////			Vector<Parse> labels=LabelingUtils.loadCandidateLabelsFromNgramRankingFile("rude.x2");
////			for (Parse parse : labels) {
////				ngrams.add(parse.toString());
////			}
//			HashMap<String, ACTMDocument> titleDocMap = new HashMap<String, ACTMDocument>();
//
//			ACTMGlobalData globalData=ACTMGlobalData.deserialize("ACTModels\\act model ngram i=50 t=100\\1305507776964.glo");
//			ACTMDataSet data = new ACMCorpusLoader().loadTrainData_Small(
//					globalData, null);
//
//
//			HashMap<String, ACTMDocument> indexDocMap = new HashMap<String, ACTMDocument>();
//			for (ACTMDocument doc : data.documentSet) {
//
//				indexDocMap.put(doc.getPaper().getTitle(), doc);
//			}
//
//			ACTModel model = ACTModel
//			.LoadWholeModel("ACTModels\\act model ngram i=50 t=100\\1305783159620.model");
//			
//			System.out.println("begin searching");
//
//			String[] querys = new String[] { "machine translation",
//					"language model", "supervised learning", "xml database",
//					"wireless network", "search engine", "computing education",
//					"relevance feedback", "object oriented programming",
//					"information retrieval", "virtual reality",
//					"user interface design", "support vector machine" };
//
//			FileWriter writer = new FileWriter(new File("queryresult_conf.txt"));
//
//			for (String query : querys) {
//				System.out.println(query);
//				writer.append(query + "\r\n");
//				new TextSearcher().searchConference(TextIndexer.confIndexDir,
//						query.toLowerCase(), globalData, data, model, writer,null);
//			}
//			writer.flush();
//			writer.close();
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//
//	private static void testSearchPapers() {
//		try {
//
//			HashMap<String, ACTMDocument> titleDocMap = new HashMap<String, ACTMDocument>();
//
//			ACTMGlobalData globalData=ACTMGlobalData.deserialize("ACTModels\\act model ngram i=50 t=100\\1305507776964.glo");
//			ACTMDataSet data = new ACMCorpusLoader().loadTrainData_Small(
//					globalData, null);
//
////			HashSet<String> ngrams=new HashSet<String>();
////			
////			Vector<Parse> labels=LabelingUtils.loadCandidateLabelsFromNgramRankingFile("rude.x2");
////			for (Parse parse : labels) {
////				ngrams.add(parse.toString());
////			}
//			
//
//			HashMap<String, ACTMDocument> indexDocMap = new HashMap<String, ACTMDocument>();
//			for (ACTMDocument doc : data.documentSet) {
//
//				indexDocMap.put(doc.getPaper().getTitle(), doc);
//			}
//			
//			ACTModel model = ACTModel
//			.LoadWholeModel("ACTModels\\act model ngram i=50 t=100\\1305783159620.model");
//			
//			System.out.println("begin searching");
//
//			String[] querys = new String[] { "machine translation",
//					"language model", "supervised learning", "xml database",
//					"wireless network", "search engine", "computing education",
//					"relevance feedback", "object oriented programming",
//					"information retrieval", "virtual reality",
//					"user interface design", "support vector machine" };
//
//			FileWriter writer = new FileWriter(new File("queryresult_paper.txt"));
//
//			for (String query : querys) {
//				System.out.println(query);
//				writer.append(query + "\n\r");
//				new TextSearcher().searchPaper(TextIndexer.paperIndexDir, query
//						.toLowerCase(), globalData, data, model, indexDocMap,
//						writer,null);
//			}
//			writer.flush();
//			writer.close();
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
	private ACTMGlobalData globalData;
	private ACTMDataSet data;
	public RAMDictionary cache;
	private HashSet<String> ngrams;
	private IndexReader authorIndexReader;
	private IndexReader paperIndexReader;
	private IndexReader conferenceIndexReader;
	private IndexSearcher authorIndexSearcher;
	private IndexSearcher paperIndexSearcher;
	private IndexSearcher conferenceIndexSearcher;
	
	private int returnNum=50;
	
	
	public TextSearcher() throws CorruptIndexException, IOException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
		authorIndexReader=IndexReader.open(FSDirectory.open(new File(TextIndexer.authorIndexDir)));
		paperIndexReader=IndexReader.open(FSDirectory.open(new File(TextIndexer.paperIndexDir)));
		conferenceIndexReader=IndexReader.open(FSDirectory.open(new File(TextIndexer.confIndexDir)));
		authorIndexSearcher=new IndexSearcher(authorIndexReader);
		paperIndexSearcher=new IndexSearcher(paperIndexReader);
		conferenceIndexSearcher=new IndexSearcher(conferenceIndexReader);
		cache=(RAMDictionary)SerializeUtils.deSerialize(RAMDictionary.storagePath);
	}

	public int[] searchAuthor(String keyword){

		try {
			Vector<String> tokens=null;
			if(ngrams==null){
				tokens= StringUtils.splitStringToWords(keyword
						.toLowerCase());
			}
			else{
				tokens= StringUtils.splitStringToNgrams(keyword
						.toLowerCase(), ngrams);
			}

			Query query=new QueryParser(Version.LUCENE_30,
					IndexFields.CONTENTS,
					new StandardAnalyzer(Version.LUCENE_30)).parse(keyword);
			
			TopDocs tops=authorIndexSearcher.search(query, returnNum*2);
			Integer ids[]=new Integer[tops.scoreDocs.length];
			Double scores[]=new Double[tops.scoreDocs.length];
			for (int i = 0; i < tops.scoreDocs.length; ++i) {
				Document doc=authorIndexSearcher.doc(tops.scoreDocs[i].doc);
				ids[i]=Integer.parseInt(doc.get(IndexFields.DB_ID));
				int tm_id=Integer.parseInt(doc.get(IndexFields.TM_INDEX));
				double weight=tops.scoreDocs[i].score;
				Author a=cache.getAuthorByTMIndex(tm_id);
				
				for (String token : tokens) {
					Word w=cache.getWordByString(token);
					int len=w.topicWeight.length;
					double temp=0;
					for(int k=0;k<len;++k){
						temp+=w.topicWeight[k]*a.topicWeight[k];
					}
					if(temp==0){
						weight/=100;
						continue;
					}
					weight*=temp;
				}
				scores[i]=weight;
			}
			KeyValuePairSorting<Integer, Double> pairs=new KeyValuePairSorting<Integer, Double>(ids,scores);
			ArrayList<KeyValuePair<Integer, Double>> sortedPairs=pairs.sort(true);
			int[] result=new int[returnNum];
			for(int i=0;i<returnNum;++i){
				result[i]=sortedPairs.get(i).getKey();
			}
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

//	public int[] searchConference(String indexLocation, String keyword,
//			ACTMGlobalData globalData, ACTMDataSet data, ACTModel model,
//			FileWriter writer,HashSet<String> ngrams) throws CorruptIndexException, IOException {
//
//		try {
//			Vector<String> tokens=null;
//			if(ngrams==null){
//				tokens= StringUtils.splitStringToWords(keyword
//						.toLowerCase());
//			}
//			else{
//				tokens= StringUtils.splitStringToNgrams(keyword
//						.toLowerCase(), ngrams);
//			}
//
//			Query query=new QueryParser(Version.LUCENE_30,
//					IndexFields.CONTENTS,
//					new StandardAnalyzer(Version.LUCENE_30)).parse(keyword);
//			
//			TopDocs tops=authorIndexSearcher.search(query, returnNum*2);
//			Integer ids[]=new Integer[tops.scoreDocs.length];
//			Double scores[]=new Double[tops.scoreDocs.length];
//			for (int i = 0; i < tops.scoreDocs.length; ++i) {
//				Document doc=authorIndexSearcher.doc(tops.scoreDocs[i].doc);
//				ids[i]=Integer.parseInt(doc.get(IndexFields.DB_ID));
//				int tm_id=Integer.parseInt(doc.get(IndexFields.TM_INDEX));
//				double weight=tops.scoreDocs[i].score;
//				Author a=cache.getAuthorByTMIndex(tm_id);
//				
//				for (String token : tokens) {
//					Word w=cache.getWordByString(token);
//					int len=w.topicWeight.length;
//					double temp=0;
//					for(int k=0;k<len;++k){
//						temp+=w.topicWeight[k]*a.topicWeight[k];
//					}
//					if(temp==0){
//						weight/=100;
//						continue;
//					}
//					weight*=temp;
//				}
//				scores[i]=weight;
//			}
//			KeyValuePairSorting<Integer, Double> pairs=new KeyValuePairSorting<Integer, Double>(ids,scores);
//			ArrayList<KeyValuePair<Integer, Double>> sortedPairs=pairs.sort(true);
//			int[] result=new int[returnNum];
//			for(int i=0;i<returnNum;++i){
//				result[i]=sortedPairs.get(i).getKey();
//			}
//			return result;
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return null;
//		
//	}

	public int[] searchPaper(String keyword)
			throws CorruptIndexException, IOException {

		try {
			Vector<String> tokens=null;
			if(ngrams==null){
				tokens= StringUtils.splitStringToWords(keyword
						.toLowerCase());
			}
			else{
				tokens= StringUtils.splitStringToNgrams(keyword
						.toLowerCase(), ngrams);
			}

			Query query=new QueryParser(Version.LUCENE_30,
					IndexFields.CONTENTS,
					new StandardAnalyzer(Version.LUCENE_30)).parse(keyword);
			
			TopDocs tops=paperIndexSearcher.search(query, returnNum*2);
			Integer ids[]=new Integer[tops.scoreDocs.length];
			Double scores[]=new Double[tops.scoreDocs.length];
			for (int i = 0; i < tops.scoreDocs.length; ++i) {
				Document doc=paperIndexSearcher.doc(tops.scoreDocs[i].doc);
				ids[i]=Integer.parseInt(doc.get(IndexFields.DB_ID));
				int tm_id=Integer.parseInt(doc.get(IndexFields.TM_INDEX));
				double weight=tops.scoreDocs[i].score;
				Paper d=cache.getPaperByTMIndex(tm_id);
				
				for (String token : tokens) {
					Word w=cache.getWordByString(token);
					int len=w.topicWeight.length;
					double temp=0;
					for(int k=0;k<len;++k){
						temp+=w.topicWeight[k]*d.topicWeight[k];
					}
					if(temp==0){
						weight/=100;
						continue;
					}
					weight*=temp;
				}
				scores[i]=weight;
			}
			KeyValuePairSorting<Integer, Double> pairs=new KeyValuePairSorting<Integer, Double>(ids,scores);
			ArrayList<KeyValuePair<Integer, Double>> sortedPairs=pairs.sort(true);
			int[] result=new int[returnNum];
			for(int i=0;i<returnNum;++i){
				result[i]=sortedPairs.get(i).getKey();
			}
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}