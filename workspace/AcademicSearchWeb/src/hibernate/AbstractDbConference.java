package hibernate;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * AbstractDbConference entity provides the base persistence definition of the DbConference entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDbConference  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private Integer tmIndex;
     private Integer globalIndex;
     private String globalName;
     private Timestamp date;
     private Set dbPapers = new HashSet(0);


    // Constructors

    /** default constructor */
    public AbstractDbConference() {
    }

	/** minimal constructor */
    public AbstractDbConference(String name, Integer tmIndex, Timestamp date) {
        this.name = name;
        this.tmIndex = tmIndex;
        this.date = date;
    }
    
    /** full constructor */
    public AbstractDbConference(String name, Integer tmIndex, Integer globalIndex, String globalName, Timestamp date, Set dbPapers) {
        this.name = name;
        this.tmIndex = tmIndex;
        this.globalIndex = globalIndex;
        this.globalName = globalName;
        this.date = date;
        this.dbPapers = dbPapers;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Integer getTmIndex() {
        return this.tmIndex;
    }
    
    public void setTmIndex(Integer tmIndex) {
        this.tmIndex = tmIndex;
    }

    public Integer getGlobalIndex() {
        return this.globalIndex;
    }
    
    public void setGlobalIndex(Integer globalIndex) {
        this.globalIndex = globalIndex;
    }

    public String getGlobalName() {
        return this.globalName;
    }
    
    public void setGlobalName(String globalName) {
        this.globalName = globalName;
    }

    public Timestamp getDate() {
        return this.date;
    }
    
    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Set getDbPapers() {
        return this.dbPapers;
    }
    
    public void setDbPapers(Set dbPapers) {
        this.dbPapers = dbPapers;
    }
   








}