/**
 * 
 */
package AUDRwebJavaBeans;

import hibernate.DbAuthor;
import hibernate.DbPaper;

import java.util.ArrayList;

/**
 * @author wanghan
 *
 */
public class TopicAssociate {

	private String title;
	private ArrayList<DbPaper> topPapers;
	private ArrayList<DbAuthor> topAuthors;
	private int type;
	
	public TopicAssociate() {
		// TODO Auto-generated constructor stub
		this.topAuthors=new ArrayList<DbAuthor>();
		this.topPapers=new ArrayList<DbPaper>();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<DbPaper> getTopPapers() {
		return topPapers;
	}
	public void setTopPapers(ArrayList<DbPaper> topPapers) {
		this.topPapers = topPapers;
	}
	public ArrayList<DbAuthor> getTopAuthors() {
		return topAuthors;
	}
	public void setTopAuthors(ArrayList<DbAuthor> topAuthors) {
		this.topAuthors = topAuthors;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
