/**
 * 
 */
package act.retrieval;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import utils.StringUtils;

import act.corpus.ACMCorpusLoader;
import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;
import actm.data.ACTMGlobalData;
import actm.data.Author;
import actm.data.Conference;
import atm.labeling.LabelingUtils;
import atm.labeling.Parse;



/**
 * @author wanghan
 *
 */
public class TextIndexer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testInsertConf();
		testInsertPapers();
		testInsertAuthors();
	}




	private static void testInsertPapers() {
		try {
			HashSet<String> ngrams=new HashSet<String>();
			
			Vector<Parse> labels=LabelingUtils.loadCandidateLabelsFromNgramRankingFile("rude.x2");
			for (Parse parse : labels) {
				ngrams.add(parse.toString());
			}
			ACTMGlobalData globalData=ACTMGlobalData.deserialize("ACTModels\\act model ngram i=50 t=100\\1305507776964.glo");
			ACTMDataSet data=new ACMCorpusLoader().loadTrainData_Small(globalData,ngrams);
			new TextIndexer(data).indexAllPapers(paperIndexDir);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	private static void testInsertAuthors() {
		try {
			HashSet<String> ngrams=new HashSet<String>();
			
			Vector<Parse> labels=LabelingUtils.loadCandidateLabelsFromNgramRankingFile("rude.x2");
			for (Parse parse : labels) {
				ngrams.add(parse.toString());
			}
			ACTMGlobalData globalData=ACTMGlobalData.deserialize("ACTModels\\act model ngram i=50 t=100\\1305507776964.glo");
			ACTMDataSet data=new ACMCorpusLoader().loadTrainData_Small(globalData,ngrams);
			new TextIndexer(data).indexAllAuthors(authorIndexDir);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static String paperIndexDir="index\\paper\\";
	public static String authorIndexDir="index\\author\\";
	public static String confIndexDir="index\\conf\\";
	
//	public static String paperIndexDir_NgramOnline="index\\paper_NgramOnline\\";
//	public static String authorIndexDir_NgramOnline="index\\author_NgramOnline\\";
//	public static String confIndexDir_NgramOnline="index\\conf_NgramOnline\\";
//	
//	public static String paperIndexDir_Online="index\\paper_Online\\";
//	public static String authorIndexDir_Online="index\\author_Online\\";
//	public static String confIndexDir_Online="index\\conf_Online\\";
	
	
	public static void testInsertConf(){
		try {
		HashSet<String> ngrams=new HashSet<String>();
			
			Vector<Parse> labels=LabelingUtils.loadCandidateLabelsFromNgramRankingFile("rude1.x2");
			for (Parse parse : labels) {
				ngrams.add(parse.toString());
			}
			ACTMGlobalData globalData=ACTMGlobalData.deserialize("ACTModels\\act model ngram i=50 t=100\\1305507776964.glo");
			ACTMDataSet data=new ACMCorpusLoader().loadTrainData_Small(globalData,null);
			new TextIndexer(data).indexAllConferences(confIndexDir);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	private HashMap<String, String> authorIndexContentMap=new HashMap<String, String>();
	private HashMap<String, Author> authorIndexAuthorMap=new HashMap<String, Author>();
	private HashMap<Integer, String> confIndexContentMap=new HashMap<Integer, String>();
	private HashMap<Integer, Conference> confIndexConferenceMap=new HashMap<Integer, Conference>();
	
	private ACTMDataSet data;
	
	
	private IndexWriter paperIndexWriter;
	private IndexWriter authorIndexWriter;
	private IndexWriter conferenceIndexWriter;
	
	public TextIndexer(ACTMDataSet data) throws IOException {
		// TODO Auto-generated constructor stub
		for (ACTMDocument doc : data.documentSet) {
			
			String docContent=doc.getContent();
			
			for (Author author : doc.getAuthors()) {
				String curContent=authorIndexContentMap.get(author.getAcmIndex());
				if(curContent==null){
					authorIndexContentMap.put(author.getAcmIndex(),docContent);
					authorIndexAuthorMap.put(author.getAcmIndex(),author);
				}
				else{
					curContent+=" "+docContent;
					authorIndexContentMap.put(author.getAcmIndex(),curContent);
				}
			}
		}
		
		for (ACTMDocument doc : data.documentSet) {
			
			String docContent=doc.getContent();
			
			String curContent=confIndexContentMap.get(doc.getConference().getGlobalIndex());
			if(curContent==null){
				confIndexContentMap.put(doc.getConference().getGlobalIndex(),docContent);
				confIndexConferenceMap.put(doc.getConference().getGlobalIndex(),doc.getConference());
			}
			else{
				curContent+=" "+docContent;
				confIndexContentMap.put(doc.getConference().getGlobalIndex(),curContent);
			}
		}
		
		FSDirectory paperIndex=FSDirectory.open(new File(paperIndexDir));
		paperIndexWriter=createWriter(paperIndex);
		
		FSDirectory authorIndex=FSDirectory.open(new File(authorIndexDir));
		authorIndexWriter=createWriter(authorIndex);
		
		FSDirectory confIndex=FSDirectory.open(new File(confIndexDir));
		conferenceIndexWriter=createWriter(confIndex);
	}
	
	public void indexAllPapers(String indexLocation){
		try {
			for (ACTMDocument doc : data.documentSet) {
				Document luceneDoc=DocumentMaker.getPaperDocument(doc);
				paperIndexWriter.addDocument(luceneDoc);
				
			}
			paperIndexWriter.optimize();
			paperIndexWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}
	public void indexPaper(ACTMDocument doc){
		try {
			Document luceneDoc=DocumentMaker.getPaperDocument(doc);
			paperIndexWriter.addDocument(luceneDoc);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void indexAllAuthors(String indexLocation){
		try {			
			for (String authorIndex : authorIndexContentMap.keySet()) {
				String content=authorIndexContentMap.get(authorIndex);
				int contentSize=StringUtils.splitStringToWords(content).size();
				Document luceneDoc=DocumentMaker.getAuthorDocument(authorIndexAuthorMap.get(authorIndex),
						contentSize,
						content);
				authorIndexWriter.addDocument(luceneDoc);
				
			}
			authorIndexWriter.optimize();
			authorIndexWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}
	public void indexAuthor(Author author){
		try {
			String authorIndex=author.getAcmIndex();
			String content=authorIndexContentMap.get(authorIndex);
			int contentSize=StringUtils.splitStringToWords(content).size();
			Document luceneDoc=DocumentMaker.getAuthorDocument(author,
					contentSize,
					content);
			authorIndexWriter.addDocument(luceneDoc);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void indexAllConferences(String indexLocation){
		try {

			for (int confIndex : confIndexContentMap.keySet()) {
				String content=confIndexContentMap.get(confIndex);
				int contentSize=StringUtils.splitStringToWords(content).size();
				Document luceneDoc=DocumentMaker.getConfDocument(confIndexConferenceMap.get(confIndex),
						contentSize,
						content);
				conferenceIndexWriter.addDocument(luceneDoc);
				
			}
			conferenceIndexWriter.optimize();
			conferenceIndexWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
	}
	public void indexConference(Conference conf){
		try {
			String content=confIndexContentMap.get(conf.getGlobalIndex());
			int contentSize=StringUtils.splitStringToWords(content).size();
			Document luceneDoc=DocumentMaker.getConfDocument(conf,
					contentSize,
					content);
			conferenceIndexWriter.addDocument(luceneDoc);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void dispose(){
		try {
			if(conferenceIndexWriter!=null){
				conferenceIndexWriter.optimize();
				conferenceIndexWriter.close();
			}
			if(authorIndexWriter!=null){
				authorIndexWriter.optimize();
				authorIndexWriter.close();
			}
			if(paperIndexWriter!=null){
				paperIndexWriter.optimize();
				paperIndexWriter.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private IndexWriter createWriter(FSDirectory dir) throws IOException {
		if(!IndexUtils.checkIndexExist(dir)||dir.listAll().length==0){
			IndexWriter writer = new IndexWriter(dir, new StandardAnalyzer(Version.LUCENE_30), true,
					IndexWriter.MaxFieldLength.UNLIMITED);
			writer.setUseCompoundFile(false);
			return writer;
		}
		else{
			IndexWriter writer = new IndexWriter(dir, new StandardAnalyzer(Version.LUCENE_30), false,
					IndexWriter.MaxFieldLength.UNLIMITED);
			writer.setUseCompoundFile(false);
			return writer;
		}
	}
}
