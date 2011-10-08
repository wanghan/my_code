package act.retrieval;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermFreqVector;
import org.apache.lucene.store.FSDirectory;

import tm.generalmodel.Word;
import utils.KeyValuePair;
import utils.KeyValuePairSorting;
import utils.StringUtils;

import act.model.ACTModel;
import actm.corpus.ACMCorpusLoader;
import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;
import actm.data.ACTMGlobalData;
import actm.data.Author;

public class TextSearcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testSearchAuthors();
		testSearchConfs();
		testSearchPapers();
	}

	private static void testSearchAuthors() {
		try {

			HashMap<String, ACTMDocument> titleDocMap = new HashMap<String, ACTMDocument>();

			ACTMGlobalData globalData=ACTMGlobalData.deserialize("ACTModels\\act model ngram i=50 t=100\\1305507776964.glo");
			ACTMDataSet data = new ACMCorpusLoader().loadTrainData_Small(
					globalData, null);

			HashMap<String, Author> authorIndexAuthorMap = new HashMap<String, Author>();
			for (ACTMDocument doc : data.documentSet) {
				for (Author author : doc.getAuthors()) {
					Author curA = authorIndexAuthorMap.get(author.getAcmIndex());
					if (curA == null) {
						authorIndexAuthorMap.put(author.getAcmIndex(), author);
					}
				}
			}

//			ACTModel model = ACTModel
//			.LoadWholeModel("ACTModels\\act model ngram i=50 t=100\\1305783159620.model");

			System.out.println("begin searching");

			String[] querys = new String[] { "machine translation",
					"language model", "supervised learning", "xml database",
					"wireless network", "search engine", "computing education",
					"relevance feedback", "object oriented programming",
					"information retrieval", "virtual reality",
					"user interface design", "support vector machine" };

			FileWriter writer = new FileWriter(new File("queryresult_author.txt"));

			for (String query : querys) {
				System.out.println(query);
				writer.append(query + "\r\n");
				new TextSearcher().searchAuthor(TextIndexer.authorIndexDir_Online,
						query.toLowerCase(), globalData, data, null,
						authorIndexAuthorMap, writer,null);
			}
			writer.flush();
			writer.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private static void testSearchConfs() {
		try {
//			HashSet<String> ngrams=new HashSet<String>();
//			
//			Vector<Parse> labels=LabelingUtils.loadCandidateLabelsFromNgramRankingFile("rude.x2");
//			for (Parse parse : labels) {
//				ngrams.add(parse.toString());
//			}
			HashMap<String, ACTMDocument> titleDocMap = new HashMap<String, ACTMDocument>();

			ACTMGlobalData globalData=ACTMGlobalData.deserialize("ACTModels\\act model ngram i=50 t=100\\1305507776964.glo");
			ACTMDataSet data = new ACMCorpusLoader().loadTrainData_Small(
					globalData, null);


			HashMap<String, ACTMDocument> indexDocMap = new HashMap<String, ACTMDocument>();
			for (ACTMDocument doc : data.documentSet) {

				indexDocMap.put(doc.getPaper().getTitle(), doc);
			}

			ACTModel model = ACTModel
			.LoadWholeModel("ACTModels\\act model ngram i=50 t=100\\1305783159620.model");
			
			System.out.println("begin searching");

			String[] querys = new String[] { "machine translation",
					"language model", "supervised learning", "xml database",
					"wireless network", "search engine", "computing education",
					"relevance feedback", "object oriented programming",
					"information retrieval", "virtual reality",
					"user interface design", "support vector machine" };

			FileWriter writer = new FileWriter(new File("queryresult_conf.txt"));

			for (String query : querys) {
				System.out.println(query);
				writer.append(query + "\r\n");
				new TextSearcher().searchConference(TextIndexer.confIndexDir_Online,
						query.toLowerCase(), globalData, data, model, writer,null);
			}
			writer.flush();
			writer.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private static void testSearchPapers() {
		try {

			HashMap<String, ACTMDocument> titleDocMap = new HashMap<String, ACTMDocument>();

			ACTMGlobalData globalData=ACTMGlobalData.deserialize("ACTModels\\act model ngram i=50 t=100\\1305507776964.glo");
			ACTMDataSet data = new ACMCorpusLoader().loadTrainData_Small(
					globalData, null);

//			HashSet<String> ngrams=new HashSet<String>();
//			
//			Vector<Parse> labels=LabelingUtils.loadCandidateLabelsFromNgramRankingFile("rude.x2");
//			for (Parse parse : labels) {
//				ngrams.add(parse.toString());
//			}
			

			HashMap<String, ACTMDocument> indexDocMap = new HashMap<String, ACTMDocument>();
			for (ACTMDocument doc : data.documentSet) {

				indexDocMap.put(doc.getPaper().getTitle(), doc);
			}
			
			ACTModel model = ACTModel
			.LoadWholeModel("ACTModels\\act model ngram i=50 t=100\\1305783159620.model");
			
			System.out.println("begin searching");

			String[] querys = new String[] { "machine translation",
					"language model", "supervised learning", "xml database",
					"wireless network", "search engine", "computing education",
					"relevance feedback", "object oriented programming",
					"information retrieval", "virtual reality",
					"user interface design", "support vector machine" };

			FileWriter writer = new FileWriter(new File("queryresult_paper.txt"));

			for (String query : querys) {
				System.out.println(query);
				writer.append(query + "\n\r");
				new TextSearcher().searchPaper(TextIndexer.paperIndexDir_Online, query
						.toLowerCase(), globalData, data, model, indexDocMap,
						writer,null);
			}
			writer.flush();
			writer.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void searchAuthor(String indexLocation, String keyword,
			ACTMGlobalData globalData, ACTMDataSet data, ACTModel model,
			HashMap<String, Author> authorIndexAuthorMap, FileWriter writer,HashSet<String> ngrams)
			throws CorruptIndexException, IOException {
		FSDirectory indexDir = FSDirectory.open(new File(indexLocation));
		IndexReader indexReader = IndexReader.open(indexDir);

		int nDoc = indexReader.numDocs();

		// get average length of doc
		double averLength = 0;
		for (int i = 0; i < nDoc; ++i) {
			Document doc = indexReader.document(i);
			int docLength = Integer.parseInt(doc.get(IndexFields.Length));
			averLength += docLength;
		}
		averLength /= nDoc;

		Vector<String> tokens=null;
		if(ngrams==null){
			tokens= StringUtils.splitStringToWords(keyword
					.toLowerCase());
		}
		else{
			tokens= StringUtils.splitStringToNgrams(keyword
					.toLowerCase(), ngrams);
		}

		String[] authorName = new String[nDoc];
		Double[] scores = new Double[nDoc];
		if(ngrams==null){
			for (int i = 0; i < nDoc; ++i) {

				Document doc = indexReader.document(i);
				double score = 1;
				TermFreqVector vector = indexReader.getTermFreqVector(i,
						IndexFields.CONTENTS);
				int docLength = Integer.parseInt(doc.get(IndexFields.Length));

				double lamda = docLength / (docLength + averLength);
				int NCorpus = data.N;

				for (String string : tokens) {
					score *= measurePWDByLM(indexReader, vector, docLength, lamda,
							NCorpus, string);

//					score *= measurePWAByACT(model, string, authorIndexAuthorMap
//							.get(doc.get(IndexFields.AuthorACMIndex)), globalData);
				}

				authorName[i] = doc.get(IndexFields.AuthorName);
				scores[i] = score;
			}
		}
		else{
			for (int i = 0; i < nDoc; ++i) {

				Document doc = indexReader.document(i);
				double score = 1;
				TermFreqVector vector = indexReader.getTermFreqVector(i,
						IndexFields.CONTENTS);
				int docLength = Integer.parseInt(doc.get(IndexFields.Length));

				double lamda = docLength / (docLength + averLength);
				int NCorpus = data.N;

				for (String string : tokens) {
					Word word = globalData.wordSet.GetWord(string);
					if(word.getSize()>1){
						score *= (1.4*measurePWAByACT(model, string, authorIndexAuthorMap
								.get(doc.get(IndexFields.AuthorACMIndex)), globalData));
					}
					else{
						score *= measurePWDByLM(indexReader, vector, docLength, lamda,
								NCorpus, string);
	
						score *= measurePWAByACT(model, string, authorIndexAuthorMap
								.get(doc.get(IndexFields.AuthorACMIndex)), globalData);
					}
				}

				authorName[i] = doc.get(IndexFields.AuthorName);
				scores[i] = score;
			}
		}
		int k = 0;
		ArrayList<KeyValuePair<String, Double>> result = new KeyValuePairSorting(
				authorName, scores).sort(true);
		for (KeyValuePair<String, Double> keyValuePair : result) {
			k++;
			if (k == 11)
				break;
			System.out.println(keyValuePair.getKey() + " : "
					+ keyValuePair.getValue());
			writer.append(k + " ### " +"http://academic.research.microsoft.com/Search?query="+keyword+" "+keyValuePair.getKey() +"\r\n");
			writer.flush();
		}

	}

	public void searchConference(String indexLocation, String keyword,
			ACTMGlobalData globalData, ACTMDataSet data, ACTModel model,
			FileWriter writer,HashSet<String> ngrams) throws CorruptIndexException, IOException {
		FSDirectory indexDir = FSDirectory.open(new File(indexLocation));
		IndexReader indexReader = IndexReader.open(indexDir);

		int nDoc = indexReader.numDocs();

		// get average length of doc
		double averLength = 0;
		for (int i = 0; i < nDoc; ++i) {
			Document doc = indexReader.document(i);
			int docLength = Integer.parseInt(doc.get(IndexFields.Length));
			averLength += docLength;
		}
		averLength /= nDoc;

		Vector<String> tokens=null;
		if(ngrams==null){
			tokens= StringUtils.splitStringToWords(keyword
					.toLowerCase());
		}
		else{
			tokens= StringUtils.splitStringToNgrams(keyword
					.toLowerCase(), ngrams);
		}

		String[] authorName = new String[nDoc];
		Double[] scores = new Double[nDoc];
		if(ngrams==null){
			for (int i = 0; i < nDoc; ++i) {

				Document doc = indexReader.document(i);
				double score = 1;
				TermFreqVector vector = indexReader.getTermFreqVector(i,
						IndexFields.CONTENTS);
				int docLength = Integer.parseInt(doc.get(IndexFields.Length));

				double lamda = docLength / (docLength + averLength);
				int NCorpus = data.N;

				for (String string : tokens) {
					score *= measurePWDByLM(indexReader, vector, docLength, lamda,
							NCorpus, string);

//					score *= measurePWCByACT(model, string, Integer.parseInt(doc
//							.get(IndexFields.ConferenceIndex)), globalData);
				}

				authorName[i] = doc.get(IndexFields.ConferenceName);
				scores[i] = score;
			}
		}
		else{
			for (int i = 0; i < nDoc; ++i) {

				Document doc = indexReader.document(i);
				double score = 1;
				TermFreqVector vector = indexReader.getTermFreqVector(i,
						IndexFields.CONTENTS);
				int docLength = Integer.parseInt(doc.get(IndexFields.Length));

				double lamda = docLength / (docLength + averLength);
				int NCorpus = data.N;

				for (String string : tokens) {
					Word word = globalData.wordSet.GetWord(string);
					if(word.getSize()>1){


//						score *=(1.4* measurePWCByACT(model, string, Integer.parseInt(doc
//								.get(IndexFields.ConferenceIndex)), globalData));
					
					}
					else{
						score *= measurePWDByLM(indexReader, vector, docLength, lamda,
								NCorpus, string);

//						score *= measurePWCByACT(model, string, Integer.parseInt(doc
//								.get(IndexFields.ConferenceIndex)), globalData);
					}
				
				}

				authorName[i] = doc.get(IndexFields.ConferenceName);
				scores[i] = score;
			}
		}
		int k = 0;
		ArrayList<KeyValuePair<String, Double>> result = new KeyValuePairSorting(
				authorName, scores).sort(true);
		for (KeyValuePair<String, Double> keyValuePair : result) {
			k++;
			if (k == 11)
				break;
			System.out.println(keyValuePair.getKey() + " : "
					+ keyValuePair.getValue());
			writer.append(k + "###" + keyValuePair.getKey() + " : "
					+ keyValuePair.getValue() + "\r\n");
			writer.flush();
		}
		
	}

	public void searchPaper(String indexLocation, String keyword,
			ACTMGlobalData globalData, ACTMDataSet data, ACTModel model,
			HashMap<String, ACTMDocument> titleDocMap, FileWriter writer,HashSet<String> ngrams)
			throws CorruptIndexException, IOException {
		FSDirectory indexDir = FSDirectory.open(new File(indexLocation));
		IndexReader indexReader = IndexReader.open(indexDir);

		int nDoc = indexReader.numDocs();

		// get average length of doc
		double averLength = 0;
		for (int i = 0; i < nDoc; ++i) {
			Document doc = indexReader.document(i);
			int docLength = Integer.parseInt(doc.get(IndexFields.Length));
			averLength += docLength;
		}
		averLength /= nDoc;
		Vector<String> tokens=null;
		if(ngrams==null){
			tokens= StringUtils.splitStringToWords(keyword
					.toLowerCase());
		}
		else{
			tokens= StringUtils.splitStringToNgrams(keyword
					.toLowerCase(), ngrams);
		}

		String[] titles = new String[nDoc];
		Double[] scores = new Double[nDoc];

		if(ngrams==null){
			for (int i = 0; i < nDoc; ++i) {

				Document doc = indexReader.document(i);
				double score = 1;
				TermFreqVector vector = indexReader.getTermFreqVector(i,
						IndexFields.CONTENTS);
				int docLength = Integer.parseInt(doc.get(IndexFields.Length));

				double lamda = docLength / (docLength + averLength);
				int NCorpus = data.N;

				for (String string : tokens) {
					score *= measurePWDByLM(indexReader, vector, docLength, lamda,
							NCorpus, string);

//					score *= measurePWDByACT(model, string, titleDocMap.get(doc
//							.get(IndexFields.Title)), globalData);
				}

				titles[i] = doc.get(IndexFields.Title) + "###"
						+ doc.get(IndexFields.CONTENTS);
				scores[i] = score;
			}
		}
		else{
			for (int i = 0; i < nDoc; ++i) {

				Document doc = indexReader.document(i);
				double score = 1;
				TermFreqVector vector = indexReader.getTermFreqVector(i,
						IndexFields.CONTENTS);
				int docLength = Integer.parseInt(doc.get(IndexFields.Length));

				double lamda = docLength / (docLength + averLength);
				int NCorpus = data.N;

				for (String string : tokens) {
					
					
					Word word = globalData.wordSet.GetWord(string);
					if(word.getSize()>1){
						score *=(1.4* measurePWDByACT(model, string, titleDocMap.get(doc
								.get(IndexFields.Title)), globalData));
					}
					else{
						score *= measurePWDByLM(indexReader, vector, docLength, lamda,
								NCorpus, string);
//						score *= measurePWDByACT(model, string, titleDocMap.get(doc
//								.get(IndexFields.Title)), globalData);
					}
				}

				titles[i] = doc.get(IndexFields.Title) + "###"
						+ doc.get(IndexFields.CONTENTS);
				scores[i] = score;
			}
		}
		
		int k = 0;
		ArrayList<KeyValuePair<String, Double>> result = new KeyValuePairSorting(
				titles, scores).sort(true);
		for (KeyValuePair<String, Double> keyValuePair : result) {
			k++;
			if (k == 11)
				break;
			System.out.println(keyValuePair.getKey() + " : "
					+ keyValuePair.getValue());
			writer.append(k + "###" + keyValuePair.getKey() + " : "
					+ keyValuePair.getValue() + "\r\n");
			writer.flush();
		}

	}

	private double measurePWDByACT(ACTModel model, String token,
			ACTMDocument doc, ACTMGlobalData globalData) {
		double result = 0;
		Word word = globalData.wordSet.GetWord(token);
		if (word == null) {
			return 0;
		}
		for (int i = 0; i < model.T; ++i) {
			double temp = 0;
			for (int j = 0; j < doc.getAuthors().size(); ++j) {
				int authorIndex = doc.getAuthors().get(j).getIndex();
				temp += model.phi[word.Index][i] * model.theta[i][authorIndex];
			}
			result += temp / doc.getAuthors().size();
		}
		return result;
	}

	private double measurePWCByACT(ACTModel model, String token, int confIndex,
			ACTMGlobalData globalData) {
		double result = 0;
		Word word = globalData.wordSet.GetWord(token);
		if (confIndex >= model.C) {
			return 0;
		}
		if (word == null) {
			return 0;
		}
		for (int i = 0; i < model.T; ++i) {
			result += model.phi[word.Index][i] * model.psi[confIndex][i];
		}
		return result;
	}

	private double measurePWAByACT(ACTModel model, String token, Author author,
			ACTMGlobalData globalData) {
		double result = 0;
		if (author == null) {
			return 0;
		}
		Word word = globalData.wordSet.GetWord(token);
		int authorIndex = author.getIndex();
		if (authorIndex >= model.A) {
			return 0;
		}
		if (word == null) {
			return 0;
		}
		for (int i = 0; i < model.T; ++i) {
			result += model.phi[word.Index][i] * model.theta[i][authorIndex];
		}
		return result;
	}

	private double measurePWDByLM(IndexReader indexReader,
			TermFreqVector vector, int docLength, double lamda, int NCorpus,
			String token) throws IOException {
		int termi = vector.indexOf(token);
		Term term = new Term(IndexFields.CONTENTS, token);
		int tf = indexReader.docFreq(term);
		double pwd = 0;
		if (termi != -1) {
			pwd = lamda * vector.getTermFrequencies()[termi] / docLength
					+ (1 - lamda) * tf / NCorpus;
		} else {
			pwd = (1 - lamda) * tf / NCorpus;
		}

		return pwd;
	}

}
