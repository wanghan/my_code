/**
 * 
 */
package actm.data;

import java.io.Serializable;
import java.util.Vector;

import tm.generalmodel.Word;



/**
 * @author wanghan
 *
 */
public class ACTMDocument implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2821873368132169777L;

	private Paper paper;
	private int index;
	private Vector<Word> bagOfWords;
	private Vector<Integer> bagOfWordGloIndexes;
	

	
	public ACTMDocument(Paper p, int index) {
		// TODO Auto-generated constructor stub
		this.paper=p;
		this.bagOfWords=new Vector<Word>();
		this.bagOfWordGloIndexes=new Vector<Integer>();
		this.index=index;
	}

	public Paper getPaper() {
		return paper;
	}
	
	public void InsertWord(Word w,int gloIndex){
		bagOfWords.add(w);
		bagOfWordGloIndexes.add(gloIndex);
	}
	
	public int getBagOfWordSize(){
		return bagOfWords.size();
	}
	
	public Vector<Author> getAuthors(){
		return this.paper.getAuthors();
	}

	public Vector<Word> getBagOfWords() {
		return bagOfWords;
	}
	public Conference getConference(){
		return paper.getConference();
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Vector<Integer> getBagOfWordGloIndexes() {
		return bagOfWordGloIndexes;
	}
	
	public String getContent(){
		return (paper.getTitle()+" "+paper.getAbstractContent()).toLowerCase();
	}
}
