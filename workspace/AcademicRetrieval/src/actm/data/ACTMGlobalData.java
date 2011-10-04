/**
 * 
 */
package actm.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import tm.generalmodel.Vocabulary;
import tm.generalmodel.Word;



/**
 * @author wanghan
 *
 */
public class ACTMGlobalData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3475196420479731031L;
	public Vocabulary wordSet;
	public AuthorSet authorSet;
	public ConferenceSet conferenceSet;
	
	public ACTMGlobalData() {
		// TODO Auto-generated constructor stub
		this.wordSet=new Vocabulary();
		this.conferenceSet=new ConferenceSet();
		this.authorSet=new AuthorSet();
	}
	public ACTMGlobalData(Vocabulary v, AuthorSet a, ConferenceSet c) {
		this.authorSet=a;
		this.wordSet=v;
		this.conferenceSet=c;
	}
	public Word GetWord(int index){
		return wordSet.GetWord(index);
	}
	public Word GetWordWithInsert(String s){
		return wordSet.GetWordWithInsert(s);
	}
	
	public Conference InsertConference(Conference c){
		return conferenceSet.InsertConference(c);
	}
	
	public Author InsertAuthor(Author a){
		return authorSet.InsertAuthor(a);
	}
	
	public Author GetAuthor(int index) {
		return authorSet.GetAuthor(index);
	}
	
	public Conference getConference(int index){
		return conferenceSet.getConference(index);
	}
	public String getGlobalConferenceName(int index){
		return conferenceSet.getGlobalName(index);
	}
	public int getWordCount(){
		return wordSet.GetWordCount();
	}
	
	public int getAuthorCount(){
		return authorSet.GetAuthorCount();
	}
	
	public int getConferenceCount(){
		return conferenceSet.getConferenceCount();
	}
	
	public int getGlobalConferenceCount(){
		return conferenceSet.getGlobalConferenceCount();
	}
	
	public void serialize(String filepath) throws FileNotFoundException, IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				filepath, false));
		oos.writeObject(this.authorSet);
		oos.flush();
		oos.writeObject(this.wordSet);
		oos.flush();
		oos.writeObject(this.conferenceSet);
		oos.flush();
		oos.close();
	}
	
	public static ACTMGlobalData deserialize(String filepath) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				filepath));
		AuthorSet authors=(AuthorSet) ois.readObject();
		Vocabulary words=(Vocabulary) ois.readObject();
		ConferenceSet confs=(ConferenceSet) ois.readObject();
		ACTMGlobalData result=new ACTMGlobalData(words,authors,confs);
		return result;
	}
	
	public void outputAuthorList(String filepath) throws FileNotFoundException, IOException{
		int count=getAuthorCount();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				filepath, false));
		for(int i=0;i<count;++i){
			oos.writeChars(authorSet.GetAuthor(i).getName());
			oos.writeChars("\r\n");
			oos.flush();
			
		}
		oos.close();
	}
	
	public void outputConfList(String filepath) throws FileNotFoundException, IOException{
		int count=getGlobalConferenceCount();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				filepath, false));
		for(int i=0;i<count;++i){
			oos.writeChars(conferenceSet.getGlobalName(i));
			oos.writeChars("\r\n");
			oos.flush();
			
		}
		oos.close();
	}
}
