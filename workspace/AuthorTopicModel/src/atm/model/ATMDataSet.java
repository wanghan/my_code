/**
 * 
 */
package atm.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

import tm.generalmodel.Vocabulary;
import tm.generalmodel.Word;

/**
 * @author wanghan
 *
 */
public class ATMDataSet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2657319920556901619L;
	//Corpus data
	public Vocabulary wordSet ;
	public AuthorSet authorSet;
	public Vector<ATMDocument> documentSet;
	
	public int N;//Number of words in corpus
	public HashMap<Integer, Word> GlobalIndexWordMap;
	public HashMap<Integer, ATMDocument> GlobalIndexDocMap;
	
	public ATMDataSet(Vocabulary wordSet,AuthorSet authorSet,Vector<ATMDocument> documentSet) {
		// TODO Auto-generated constructor stub
		this.wordSet=wordSet;
		this.authorSet=authorSet;
		this.documentSet=documentSet;
		this.N=0;
		
		this.GlobalIndexDocMap=new HashMap<Integer, ATMDocument>();
		this.GlobalIndexWordMap=new HashMap<Integer, Word>();
		
		
		for (ATMDocument document : documentSet) {
			
			for (Word ww : document.BagOfWord) {
				GlobalIndexDocMap.put(N, document);
				GlobalIndexWordMap.put(N, ww);
				N++;
			}
		}
		
	}
	
}
