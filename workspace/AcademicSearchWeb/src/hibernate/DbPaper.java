package hibernate;

import java.util.Set;


/**
 * DbPaper entity. @author MyEclipse Persistence Tools
 */
public class DbPaper extends AbstractDbPaper implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public DbPaper() {
    }

	/** minimal constructor */
    public DbPaper(DbConference dbConference, String title, String abstract_, Integer tmIndex) {
        super(dbConference, title, abstract_, tmIndex);        
    }
    
    /** full constructor */
    public DbPaper(DbConference dbConference, String title, String pages, String source, String link, String abstract_, String doi, String doiLink, Integer tmIndex, Set dbAuthors) {
        super(dbConference, title, pages, source, link, abstract_, doi, doiLink, tmIndex, dbAuthors);        
    }
   
}
