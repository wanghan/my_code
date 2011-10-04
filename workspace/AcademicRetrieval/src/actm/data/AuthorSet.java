/**
 * 
 */
package actm.data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author wanghan
 *
 */
public class AuthorSet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6343120536151218603L;

	public HashMap<Integer,Author> indexAuthorMap;
	
	public HashMap<String, Author> acmIndexAuthorMap;
	
	private int index;
	
	public AuthorSet() {
		// TODO Auto-generated constructor stub
		this.index=0;
		this.indexAuthorMap=new HashMap<Integer, Author>();
		this.acmIndexAuthorMap=new HashMap<String, Author>();
	}
	
	public Author InsertAuthor(Author author){
		if(acmIndexAuthorMap.containsKey(author.getAcmIndex())){
			return acmIndexAuthorMap.get(author.getAcmIndex());
		}
		else{
			author.setIndex(index);
			indexAuthorMap.put(index++, author);
			acmIndexAuthorMap.put(author.getAcmIndex(), author);
			return author;
		}
	}
	public int GetAuthorCount(){
		return indexAuthorMap.size();
	}
	
	public Author GetAuthor(int index) {
		if (indexAuthorMap.containsKey(index)) {
			return indexAuthorMap.get(index);
		} else {
			return null;
		}
	}
	
//	public int GetAuthorIndex(String name){
//		if(nameAuthorMap.containsKey(name)){
//			return nameAuthorMap.get(name).getIndex();
//		}
//		else{
//			return -1;
//		}
//	}
//	public String GetAuthorName(int index){
//		if(indexAuthorMap.containsKey(index)){
//			return indexAuthorMap.get(index).getName();
//		}
//		else{
//			return null;
//		}
//	}
//	public Author GetAuthor(String name){
//		if(nameAuthorMap.containsKey(name)){
//			return nameAuthorMap.get(name);
//		}
//		else{
//			return null;
//		}
//	}


}
