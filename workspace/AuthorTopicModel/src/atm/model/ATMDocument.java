/**
 * 
 */
package atm.model;

import java.io.Serializable;
import java.util.Vector;

import tm.generalmodel.Word;

/**
 * @author wanghan
 *
 */
public class ATMDocument implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6324893020089337837L;
	public int Year;
	public int Index;
	public int GlobalIndex;
	public String Title;
	public String Contents;
	public Vector<Author> Authors;
	public Vector<String> Subjects;
//	public HashMap<Word, Integer> BagOfWord;
	public Vector<Word> BagOfWord;
	
	public ATMDocument() {
		// TODO Auto-generated constructor stub
		this.Authors=new Vector<Author>();
		this.GlobalIndex=-1;
//		this.BagOfWord=new HashMap<Word, Integer>();
		this.BagOfWord=new Vector<Word>();
		this.Subjects=new Vector<String>();
	}
	
	public void InsertWord(Word w){
//		Integer count=BagOfWord.get(w);
//		if(count==null){
//			BagOfWord.put(w, 1);
//		}
//		else{
//			BagOfWord.put(w, 1+count);
//		}
		this.BagOfWord.add(w);
	}
}
