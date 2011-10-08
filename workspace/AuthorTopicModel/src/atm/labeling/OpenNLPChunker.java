/**
 * 
 */
package atm.labeling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Vector;

import org.dom4j.DocumentException;

import actm.corpus.ACMCorpusLoader;
import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;
import actm.data.ACTMGlobalData;
import atm.corpus.NIPSCorpusNormalizer;
import atm.model.ATMDocument;



import opennlp.tools.chunker.Chunker;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.chunker.ChunkerModelLoader;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTagger;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;

/**
 * @author wanghan
 * 
 */
public class OpenNLPChunker {

	/**
	 * @param args
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static void main(String[] args){
		
		try {
			ACTMGlobalData globalData=new ACTMGlobalData();
			ACTMDataSet datatrain=new ACMCorpusLoader().loadTrainData_Small(globalData,null);
			OpenNLPChunker chunker = new OpenNLPChunker();
			HashSet<String> candidate=new HashSet<String>();
			int i=0;
			for (ACTMDocument doc : datatrain.documentSet) {
				String s[] = chunker.sentencesDetect(doc.getPaper().getAbstractContent()+" "+doc.getPaper().getTitle());
				if(s.length==0){
					continue;
				}
				String posTag = chunker.postag(s[0]);
				Vector<Parse> parses = chunker.chunk(posTag);

				for (Parse parse : parses) {
		//			System.out.println(parse);
					candidate.add(parse.toString());
					i++;
				}
			}
			
			FileWriter candidateWriter=new FileWriter("chunkLabelsACM_train.txt");
			for (String string : candidate) {
				candidateWriter.write(string+"\r\n");
			}
			candidate.clone();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void chunkerNIPS() throws IOException {
		// TODO Auto-generated method stub
		try {
			File dir = new File("NIPSCorpus/");
			OpenNLPChunker chunker = new OpenNLPChunker();
			int i=0;
			HashSet<String> candidate=new HashSet<String>();
			for (File file : dir.listFiles()) {
				ATMDocument doc = new NIPSCorpusNormalizer()
						.xmlToDocument(file.getAbsolutePath());
				
				String s[] = chunker.sentencesDetect(doc.Contents);
				String posTag = chunker.postag(s[0]);
				Vector<Parse> parses = chunker.chunk(posTag);

				for (Parse parse : parses) {
		//			System.out.println(parse);
					candidate.add(parse.toString());
					i++;
				}
			}
			System.out.println(i);
			System.out.println(candidate.size());
			FileWriter candidateWriter=new FileWriter("chunkLabels.txt");
			for (String string : candidate) {
				candidateWriter.write(string+"\r\n");
			}
			candidate.clone();

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Chunker chunker;
	private Tokenizer tokenizer;
	private SentenceDetector senDetector;
	private POSTagger tagger;
	
	public OpenNLPChunker() throws IOException {
		// TODO Auto-generated constructor stub
		POSModel posmodel = new POSModelLoader().load(new File(
				"Resource/en-pos-perceptron.bin"));
		tagger = new POSTaggerME(posmodel);

		InputStream tokenizermodelIn = new FileInputStream(
				"Resource/en-token.bin");
		tokenizer = new TokenizerME(new TokenizerModel(tokenizermodelIn));
		senDetector = new SentenceDetectorME(new SentenceModel(
				new FileInputStream("Resource/en-sent.bin")));
		ChunkerModelLoader loader = new ChunkerModelLoader();
		ChunkerModel chunkermodel = loader.load(new File(
				"Resource/en-chunker.bin"));
		chunker = new ChunkerME(chunkermodel);
		
	}

	public String[] sentencesDetect(String content) {
		return senDetector.sentDetect(content);
	}

	/**
	 * Make the Part of Speech Tagging for a given String.
	 * 
	 * @param s
	 *            String
	 * @return String
	 */
	public String postag(String s) {
		if (tokenizer == null)
			return null;

		String[] tokens = tokenizer.tokenize(s);
		StringBuffer tline = (tokens.length > 0) ? new StringBuffer(tokens[0])
				: new StringBuffer("");
		for (int ti = 1, tn = tokens.length; ti < tn; ti++) {
			tline.append(' ').append(tokens[ti]);
		}
		return tagger.tag(tline.toString());
	}

	/**
	 * Extracted and adapted from file
	 * opennlp-tools.models.english.chunker.TreebankChunker.java
	 * 
	 * @param tagedline
	 *            String
	 * @return String
	 */
	public Vector<Parse> chunk(String tagedline) {
		if (chunker == null)
			return null;

		// System.out.printf("OpenNLPModel::chunk(String
		// tagedline)tagedline:........... [%s]\n", tagedline);
		String[] tts = tagedline.split("  *");
		// tools.print(tts);
		String[] tokens = new String[tts.length];
		String[] tags = new String[tts.length];
		for (int ti = 0, tn = tts.length; ti < tn; ti++) {
			try {
				int r = tts[ti].lastIndexOf('/');
				tokens[ti] = tts[ti].substring(0, r);
				tags[ti] = tts[ti].substring(r + 1);
				// System.out.printf(" [%3d] [%7s] [%s]\n", ti, tags[ti],
				// tokens[ti]);
			} catch (Exception exc) {
				System.out.printf("---> [%s] <---\n", tts[ti]);
				exc.printStackTrace();
			}
		}
		String[] chunks = chunker.chunk(tokens, tags);
		// tools.print(chunks);

		String finalpunct = ".?!";

		// System.out.print("\n\n");
		Vector<Parse> parses = new Vector<Parse>();
		String parseType = "";
		StringBuffer outline = new StringBuffer("");
		for (int i = 0, n = chunks.length; i < n; i++) {
			// System.out.printf(" [%4s] [%7s] [%s]\n", chunks[i], tags[i],
			// tokens[i]);

			if (i > 0 && !chunks[i].startsWith("I-")
					&& !chunks[i - 1].equals("O")) {
				// outline.append(" ]");
				if (parseType.equals("NP")) {
					Parse parse = new Parse(outline.toString().trim());
					if (parse.tokens.size() > 1&&parse.tokens.size() <4) {
						parses.add(parse);
					}

				}

			}
			if (chunks[i].startsWith("B-")) {
				// outline.append(" [").append(chunks[i].substring(2));
				parseType = chunks[i].substring(2);
				outline.delete(0, outline.length());
			}

			// outline.append(" " + tokens[i] + "/" + tags[i]);
			outline.append("  " + tokens[i]);
			if (finalpunct.indexOf(tags[i]) >= 0 && i < n - 1
					&& finalpunct.indexOf(tags[i + 1]) < 0)
				outline.append("\n\n");
		}
		if (!chunks[chunks.length - 1].equals("O")) {
			// outline.append(" ]");
			if (parseType.equals("NP")) {
				Parse parse = new Parse(outline.toString().trim());
				if (parse.tokens.size() > 1&&parse.tokens.size() <4) {
					parses.add(parse);
				}
			}
		}

		return parses;
	}
}
