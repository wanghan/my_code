package hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * AbstractDbPaper entity provides the base persistence definition of the DbPaper entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDbPaper  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private DbConference dbConference;
     private String title;
     private String pages;
     private String source;
     private String link;
     private String abstract_;
     private String doi;
     private String doiLink;
     private Integer tmIndex;
     private Set dbAuthors = new HashSet(0);


    // Constructors

    /** default constructor */
    public AbstractDbPaper() {
    }

	/** minimal constructor */
    public AbstractDbPaper(DbConference dbConference, String title, String abstract_, Integer tmIndex) {
        this.dbConference = dbConference;
        this.title = title;
        this.abstract_ = abstract_;
        this.tmIndex = tmIndex;
    }
    
    /** full constructor */
    public AbstractDbPaper(DbConference dbConference, String title, String pages, String source, String link, String abstract_, String doi, String doiLink, Integer tmIndex, Set dbAuthors) {
        this.dbConference = dbConference;
        this.title = title;
        this.pages = pages;
        this.source = source;
        this.link = link;
        this.abstract_ = abstract_;
        this.doi = doi;
        this.doiLink = doiLink;
        this.tmIndex = tmIndex;
        this.dbAuthors = dbAuthors;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public DbConference getDbConference() {
        return this.dbConference;
    }
    
    public void setDbConference(DbConference dbConference) {
        this.dbConference = dbConference;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPages() {
        return this.pages;
    }
    
    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getSource() {
        return this.source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return this.link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }

    public String getAbstract_() {
        return this.abstract_;
    }
    
    public void setAbstract_(String abstract_) {
        this.abstract_ = abstract_;
    }

    public String getDoi() {
        return this.doi;
    }
    
    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getDoiLink() {
        return this.doiLink;
    }
    
    public void setDoiLink(String doiLink) {
        this.doiLink = doiLink;
    }

    public Integer getTmIndex() {
        return this.tmIndex;
    }
    
    public void setTmIndex(Integer tmIndex) {
        this.tmIndex = tmIndex;
    }

    public Set getDbAuthors() {
        return this.dbAuthors;
    }
    
    public void setDbAuthors(Set dbAuthors) {
        this.dbAuthors = dbAuthors;
    }
   








}