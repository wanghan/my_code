/**
 * 
 */
package actm.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;


import tm.generalmodel.Word;
import utils.StringUtils;

/**
 * @author wanghan
 *
 */
public class ACTMDataSet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 953317450726322564L;

	public Vector<ACTMDocument> documentSet;
	public int N;//Number of words in corpus
	public HashMap<Integer, Word> GlobalIndexWordMap;
	public HashMap<Integer, ACTMDocument> GlobalIndexDocMap;
	
	private int docIndex=0;
	
	public ACTMDataSet() {
		// TODO Auto-generated constructor stub
		
		this.documentSet=new Vector<ACTMDocument>();
		
		this.GlobalIndexDocMap=new HashMap<Integer, ACTMDocument>();
		this.GlobalIndexWordMap=new HashMap<Integer, Word>();
		this.N=0;
	}
	
	public void insertPaper(Paper paper, ACTMGlobalData globalData){
		paper.setConference(globalData.InsertConference(paper.getConference()));
		if(paper.getAuthors().size()==0){
			return ;
			
		}
		for (Author author : paper.getAuthors()) {
			Author aaa=globalData.InsertAuthor(author);
			author.setIndex(aaa.getIndex());
		}
		ACTMDocument document=new ACTMDocument(paper,docIndex++);
		document.getPaper().setIndex(document.getIndex());
		Vector<String> tokens=StringUtils.splitStringToWords(paper.getAbstract()+" "+paper.getTitle());
		
		for (String string : tokens) {
			Word word =globalData.GetWordWithInsert(string);
			document.InsertWord(word,N);
			GlobalIndexDocMap.put(N, document);
			GlobalIndexWordMap.put(N++, word);
		}
		documentSet.add(document);
	}
	
	public void insertPaperWithNGrams(Paper paper, ACTMGlobalData globalData,HashSet<String> ngram){
		paper.setConference(globalData.InsertConference(paper.getConference()));
		if(paper.getAuthors().size()==0){
			return ;
			
		}
		for (Author author : paper.getAuthors()) {
			Author aaa=globalData.InsertAuthor(author);
			author.setIndex(aaa.getIndex());
		}
		ACTMDocument document=new ACTMDocument(paper,docIndex++);
		Vector<String> tokens=StringUtils.splitStringToNgrams(paper.getAbstract()+" "+paper.getTitle(),ngram);
		
		for (String string : tokens) {
			Word word =globalData.GetWordWithInsert(string);
			document.InsertWord(word,N);
			GlobalIndexDocMap.put(N, document);
			GlobalIndexWordMap.put(N++, word);
		}
		documentSet.add(document);
	}
}
