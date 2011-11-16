package hibernate;

import java.util.Set;


/**
 * DbAuthor entity. @author MyEclipse Persistence Tools
 */
public class DbAuthor extends AbstractDbAuthor implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public DbAuthor() {
    }

	/** minimal constructor */
    public DbAuthor(Long id, String name, String link, Integer tmIndex) {
        super(id, name, link, tmIndex);        
    }
    
    /** full constructor */
    public DbAuthor(Long id, String name, String link, String acmIndex, Integer tmIndex, String position, String affiliation, String address, String homepage, String interest, Set dbPapers) {
        super(id, name, link, acmIndex, tmIndex, position, affiliation, address, homepage, interest, dbPapers);        
    }
   
}
