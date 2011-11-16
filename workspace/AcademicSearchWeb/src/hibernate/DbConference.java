package hibernate;

import java.sql.Timestamp;
import java.util.Set;


/**
 * DbConference entity. @author MyEclipse Persistence Tools
 */
public class DbConference extends AbstractDbConference implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public DbConference() {
    }

	/** minimal constructor */
    public DbConference(String name, Integer tmIndex, Timestamp date) {
        super(name, tmIndex, date);        
    }
    
    /** full constructor */
    public DbConference(String name, Integer tmIndex, Integer globalIndex, String globalName, Timestamp date, Set dbPapers) {
        super(name, tmIndex, globalIndex, globalName, date, dbPapers);        
    }
   
}
