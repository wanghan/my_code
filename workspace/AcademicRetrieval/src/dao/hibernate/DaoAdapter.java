/**
 * 
 */
package dao.hibernate;

import tools.crawler.AuthorProfile;
import actm.data.Author;
import actm.data.Conference;
import actm.data.Paper;

/**
 * @author wanghan
 *
 */
public class DaoAdapter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static DbAuthor ToDbAuthor(Author author){
		if(author==null)
			return null;
		
		DbAuthor result=new DbAuthor();
		
		result.setAcmIndex(author.getAcmIndex());
		result.setId(Long.parseLong(author.getAcmIndex()));
		result.setName(author.getName());
		result.setTmIndex(author.getIndex());
		result.setLink(author.getLink());
		
		return result;
	}
	
	public static void UpdateDbAuthorProfile(DbAuthor author, AuthorProfile profile){
		if(profile.getAddress()!=null){
			author.setAddress(profile.getAddress());
		}
		
		if(profile.getAffiliation()!=null){
			author.setAffiliation(profile.getAffiliation());
		}
		
		if(profile.getHomepage()!=null){
			author.setHomepage(profile.getHomepage());
		}
		
		if(profile.getInterest()!=null){
			author.setInterest(profile.getInterest());
		}
		
		if(profile.getPosition()!=null){
			author.setPosition(profile.getPosition());
		}
	}
	
	public static DbPaper ToDbPaper(Paper paper){
		DbPaper result=new DbPaper();
		
		result.setAbstract(paper.getAbstract());
		result.setTitle(paper.getTitle());
		
		if(paper.getPages()!=null){
			result.setPages(paper.getPages());
		}
		
		if(paper.getSource()!=null){
			result.setSource(paper.getSource());
		}
		if(paper.getLink()!=null){
			result.setLink(paper.getLink());
		}
		if(paper.getDoi()!=null){
			result.setDoi(paper.getDoi());
		}
		if(paper.getDoiLink()!=null){
			result.setDoiLink(paper.getDoiLink());
		}
		result.setTmIndex(paper.getIndex());
		
		return result;
	}
	
	public static DbConference ToDbConference(Conference conf){
		DbConference result=new DbConference();
		
		result.setDate(conf.getDate());
		if(conf.getGlobalIndex()!=null){
			result.setGlobalIndex(conf.getGlobalIndex());
		}
		if(conf.getGlobalName()!=null){
			result.setGlobalName(conf.getGlobalName());
		}
		result.setTmIndex(conf.getIndex());
		result.setName(conf.getName());
		
		return result;
	}
}
