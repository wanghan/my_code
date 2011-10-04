/**
 * 
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import actm.data.Author;
import actm.data.Conference;
import actm.data.Paper;

/**
 * @author wanghan
 *
 */
public class DBOperations {
	
	Connection conn;
	
	public DBOperations(Connection con) {
		// TODO Auto-generated constructor stub
		this.conn=con;
	}
	
//	public ArrayList<Paper> queryPapers(int []ids){
//		
//		
//	}
	
	public void insertPaper(Paper paper,int tmIndex) throws SQLException{
		
		
		StringBuffer sqlCom=new StringBuffer("INSERT INTO paper (tm_index,title,abstract,doi,doi_link,acm_link,source_link,page,conferenceid) VALUES(");
		
		sqlCom.append("'"+String.valueOf(tmIndex)+"' ,");
		sqlCom.append("'"+paper.getTitle().replace('\'', ' ').trim()+"' ,");
		sqlCom.append("'"+paper.getAbstractContent().replace('\'', ' ').trim()+"' ,");
		sqlCom.append("'"+paper.getDoi()+"' ,");
		sqlCom.append("'"+paper.getDoiLink()+"' ,");
		sqlCom.append("'"+paper.getLink().trim()+"' ,");
		sqlCom.append("'"+paper.getSource().trim()+"' ,");
		sqlCom.append("'"+paper.getPages().trim()+"' ,");
		
		//insert conference
		int confid=insertConference(paper.getConference());
		sqlCom.append("'"+confid+"'");
		sqlCom.append(")");
		paper.getConference().setId(confid);
		
		PreparedStatement st = conn.prepareStatement(sqlCom.toString(), Statement.RETURN_GENERATED_KEYS); 
		st.executeUpdate();
		ResultSet rs=st.getGeneratedKeys();
		rs.next();
		paper.setId(rs.getInt(1));
		
		//insert authors
		for (Author aa : paper.getAuthors()) {
			int authorid=insertAuthor(aa);
			insertPaperAuthor(paper.getId(), authorid);
			aa.setId(authorid);
		}
		
	}
	
	public int insertPaperAuthor(int paperid, int authorid) throws SQLException{
		StringBuffer sqlCom=new StringBuffer("INSERT INTO paperauthors (paperid,authorid) VALUES(");
		
		sqlCom.append("'"+paperid+"' ,");
		sqlCom.append("'"+authorid+"'");

		sqlCom.append(")");
		
		PreparedStatement st = conn.prepareStatement(sqlCom.toString(), Statement.RETURN_GENERATED_KEYS); 
		st.executeUpdate();
		ResultSet rs=st.getGeneratedKeys();
		rs.next();
		
		return rs.getInt(1);
	}
	
	public int insertConference(Conference conf) throws SQLException{
		
		Conference tempc=getConferenceByName(conf.getName());
		
		if(tempc!=null){
			return tempc.getId();
			
		}
		StringBuffer sqlCom=new StringBuffer("INSERT INTO conference (name,year,global_name, tm_index, tm_global_index) VALUES(");
		
		sqlCom.append("'"+conf.getName().trim()+"' ,");
		Calendar ca=Calendar.getInstance();
		ca.setTime(conf.getDate());
		sqlCom.append("'"+ca.get(Calendar.YEAR)+"' ,");
		sqlCom.append("'"+conf.getGlobalName()+"' ,");
		sqlCom.append("'"+conf.getIndex()+"' ,");
		sqlCom.append("'"+conf.getGlobalIndex()+"'");
		sqlCom.append(")");
		
//		System.out.println(sqlCom.toString());
		
		PreparedStatement st = conn.prepareStatement(sqlCom.toString(), Statement.RETURN_GENERATED_KEYS); 
		st.executeUpdate();
		ResultSet rs=st.getGeneratedKeys();
		rs.next();
		conf.setId(rs.getInt(1));
		
		return conf.getId();
	}
	
	
	public Conference getConferenceByName(String name) throws SQLException{
		Statement st=conn.createStatement();
		
		String sql="select * from conference where name='"+name+"'";
		
		ResultSet result=st.executeQuery(sql);
		if(!result.next()){
			return null;
		}
		else{
			Conference conf=new Conference();
			conf.setName(result.getString("name"));
			Calendar ca=Calendar.getInstance();
			ca.set(Calendar.YEAR, result.getInt("year"));
			conf.setDate(ca.getTime());
			conf.setId(result.getInt("id"));
			return conf;
		}
	}
	
	public Paper getPaperById(int id) throws SQLException{
		Statement st=conn.createStatement();
		
		String sql="select * from paper where id='"+id+"'";
		
		ResultSet result=st.executeQuery(sql);
		if(!result.next()){
			return null;
		}
		else{
			Paper re=new Paper();
			re.setAbstractContent(result.getString("abstract"));
			re.setId(result.getInt("id"));
			re.setId(result.getInt("id"));
			re.setTitle(result.getString("title"));
			re.setDoi(result.getString("doi"));
			re.setLink(result.getString("acm_link"));
			re.setPages(result.getString("page"));
			re.setSource(result.getString("source_link"));
			return re;
		}
	}
	
	public int insertAuthor(Author author) throws SQLException{
		
		Author queryA=getAuthorByAcmIndex(author.getAcmIndex());
		if(queryA!=null){
			return queryA.getId();
		}
		
		
		StringBuffer sqlCom=new StringBuffer("INSERT INTO author (name,acm_link, tm_index, acm_index) VALUES(");
		
		sqlCom.append("'"+author.getName().replace('\'', ' ').trim()+"' ,");
		sqlCom.append("'"+author.getLink()+"' , ");

		sqlCom.append("'"+author.getIndex()+"' ,");
		sqlCom.append("'"+author.getAcmIndex()+"'");
		
		sqlCom.append(")");
		
		PreparedStatement st = conn.prepareStatement(sqlCom.toString(), Statement.RETURN_GENERATED_KEYS); 
		st.executeUpdate();
		ResultSet rs=st.getGeneratedKeys();
		rs.next();
		author.setId(rs.getInt(1));
		return author.getId();
	}
	
	public Author getAuthorByAcmIndex(String index) throws SQLException{
		
		Statement st=conn.createStatement();
		
		String sql="select * from author where acm_index='"+index+"'";
		
		ResultSet result=st.executeQuery(sql);
		if(!result.next()){
			return null;
		}
		else{
			Author a=new Author();
			a.setName(result.getString("name"));
			a.setLink(result.getString("acm_link"));
			a.setIndex(result.getInt("tm_index"));
			a.setAcmIndex(result.getString("acm_index"));
			a.setId(result.getInt("id"));
			return a;
		}
	}
	
	public Author getAuthorByTMIndex(int index) throws SQLException{
		
		Statement st=conn.createStatement();
		
		String sql="select * from author where tm_index='"+index+"'";
		
		ResultSet result=st.executeQuery(sql);
		if(!result.next()){
			return null;
		}
		else{
			Author a=new Author();
			a.setName(result.getString("name"));
			a.setLink(result.getString("acm_link"));
			a.setIndex(result.getInt("tm_index"));
			a.setAcmIndex(result.getString("acm_index"));
			a.setId(result.getInt("id"));
			return a;
		}
	}
	
	public Author getAuthorById(int id) throws SQLException{
		
		Statement st=conn.createStatement();
		
		String sql="select * from author where id='"+id+"'";
		
		ResultSet result=st.executeQuery(sql);
		if(!result.next()){
			return null;
		}
		else{
			Author a=new Author();
			a.setName(result.getString("name"));
			a.setLink(result.getString("acm_link"));
			a.setIndex(result.getInt("tm_index"));
			a.setAcmIndex(result.getString("acm_index"));
			a.setId(result.getInt("id"));
			return a;
		}
	}
}
