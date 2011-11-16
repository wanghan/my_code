package hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * AbstractDbAuthor entity provides the base persistence definition of the DbAuthor entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDbAuthor  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String name;
     private String link;
     private String acmIndex;
     private Integer tmIndex;
     private String position;
     private String affiliation;
     private String address;
     private String homepage;
     private String interest;
     private Set dbPapers = new HashSet(0);


    // Constructors

    /** default constructor */
    public AbstractDbAuthor() {
    }

	/** minimal constructor */
    public AbstractDbAuthor(Long id, String name, String link, Integer tmIndex) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.tmIndex = tmIndex;
    }
    
    /** full constructor */
    public AbstractDbAuthor(Long id, String name, String link, String acmIndex, Integer tmIndex, String position, String affiliation, String address, String homepage, String interest, Set dbPapers) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.acmIndex = acmIndex;
        this.tmIndex = tmIndex;
        this.position = position;
        this.affiliation = affiliation;
        this.address = address;
        this.homepage = homepage;
        this.interest = interest;
        this.dbPapers = dbPapers;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return this.link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }

    public String getAcmIndex() {
        return this.acmIndex;
    }
    
    public void setAcmIndex(String acmIndex) {
        this.acmIndex = acmIndex;
    }

    public Integer getTmIndex() {
        return this.tmIndex;
    }
    
    public void setTmIndex(Integer tmIndex) {
        this.tmIndex = tmIndex;
    }

    public String getPosition() {
        return this.position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }

    public String getAffiliation() {
        return this.affiliation;
    }
    
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomepage() {
        return this.homepage;
    }
    
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getInterest() {
        return this.interest;
    }
    
    public void setInterest(String interest) {
        this.interest = interest;
    }

    public Set getDbPapers() {
        return this.dbPapers;
    }
    
    public void setDbPapers(Set dbPapers) {
        this.dbPapers = dbPapers;
    }
   








}