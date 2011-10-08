/**
 * 
 */
package atm.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author wanghan
 *
 */
public class AuthorSet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3994579896177622510L;
	private HashMap<String,Author> nameAuthorMap;
	private HashMap<Integer,Author> indexAuthorMap;
	
	public Collection<Author> getAuthorSet(){
		return nameAuthorMap.values();
	}
	
	public AuthorSet() {
		// TODO Auto-generated constructor stub
		this.nameAuthorMap=new HashMap<String, Author>();
		this.indexAuthorMap=new HashMap<Integer, Author>();
	}
	
	public void InsertAuthor(int index, String name){
		Author author=new Author();
		author.AuthorName=name;
		author.Index=index;
		this.indexAuthorMap.put(index, author);
		this.nameAuthorMap.put(name, author);
	}
	
	public int GetAuthorIndex(String name){
		if(nameAuthorMap.containsKey(name)){
			return nameAuthorMap.get(name).Index;
		}
		else{
			return -1;
		}
	}
	public String GetAuthorIndex(int index){
		if(indexAuthorMap.containsKey(index)){
			return indexAuthorMap.get(index).AuthorName;
		}
		else{
			return null;
		}
	}
	public Author GetAuthor(String name){
		if(nameAuthorMap.containsKey(name)){
			return nameAuthorMap.get(name);
		}
		else{
			return null;
		}
	}
	public Author GetAuthor(int index){
		if(indexAuthorMap.containsKey(index)){
			return indexAuthorMap.get(index);
		}
		else{
			return null;
		}
	}
	public int GetAuthorCount(){
		return nameAuthorMap.size();
	}
}
