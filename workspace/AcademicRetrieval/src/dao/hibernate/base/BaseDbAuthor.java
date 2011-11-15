package dao.hibernate.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the db_author table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="db_author"
 */

public abstract class BaseDbAuthor  implements Serializable {

	public static String REF = "DbAuthor";
	public static String PROP_HOMEPAGE = "Homepage";
	public static String PROP_AFFILIATION = "Affiliation";
	public static String PROP_NAME = "Name";
	public static String PROP_LINK = "Link";
	public static String PROP_INTEREST = "Interest";
	public static String PROP_ADDRESS = "Address";
	public static String PROP_ACM_INDEX = "AcmIndex";
	public static String PROP_POSITION = "Position";
	public static String PROP_ID = "Id";
	public static String PROP_TM_INDEX = "TmIndex";


	// constructors
	public BaseDbAuthor () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseDbAuthor (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseDbAuthor (
		java.lang.Long id,
		java.lang.String name,
		java.lang.String link,
		java.lang.Integer tmIndex) {

		this.setId(id);
		this.setName(name);
		this.setLink(link);
		this.setTmIndex(tmIndex);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String name;
	private java.lang.String link;
	private java.lang.String acmIndex;
	private java.lang.Integer tmIndex;
	private java.lang.String position;
	private java.lang.String affiliation;
	private java.lang.String address;
	private java.lang.String homepage;
	private java.lang.String interest;

	// collections
	private java.util.Set<dao.hibernate.DbPaper> dbPapers;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="id"
     */
	public java.lang.Long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: name
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: name
	 * @param name the name value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}



	/**
	 * Return the value associated with the column: link
	 */
	public java.lang.String getLink () {
		return link;
	}

	/**
	 * Set the value related to the column: link
	 * @param link the link value
	 */
	public void setLink (java.lang.String link) {
		this.link = link;
	}



	/**
	 * Return the value associated with the column: acm_index
	 */
	public java.lang.String getAcmIndex () {
		return acmIndex;
	}

	/**
	 * Set the value related to the column: acm_index
	 * @param acmIndex the acm_index value
	 */
	public void setAcmIndex (java.lang.String acmIndex) {
		this.acmIndex = acmIndex;
	}



	/**
	 * Return the value associated with the column: tm_index
	 */
	public java.lang.Integer getTmIndex () {
		return tmIndex;
	}

	/**
	 * Set the value related to the column: tm_index
	 * @param tmIndex the tm_index value
	 */
	public void setTmIndex (java.lang.Integer tmIndex) {
		this.tmIndex = tmIndex;
	}



	/**
	 * Return the value associated with the column: position
	 */
	public java.lang.String getPosition () {
		return position;
	}

	/**
	 * Set the value related to the column: position
	 * @param position the position value
	 */
	public void setPosition (java.lang.String position) {
		this.position = position;
	}



	/**
	 * Return the value associated with the column: affiliation
	 */
	public java.lang.String getAffiliation () {
		return affiliation;
	}

	/**
	 * Set the value related to the column: affiliation
	 * @param affiliation the affiliation value
	 */
	public void setAffiliation (java.lang.String affiliation) {
		this.affiliation = affiliation;
	}



	/**
	 * Return the value associated with the column: address
	 */
	public java.lang.String getAddress () {
		return address;
	}

	/**
	 * Set the value related to the column: address
	 * @param address the address value
	 */
	public void setAddress (java.lang.String address) {
		this.address = address;
	}



	/**
	 * Return the value associated with the column: homepage
	 */
	public java.lang.String getHomepage () {
		return homepage;
	}

	/**
	 * Set the value related to the column: homepage
	 * @param homepage the homepage value
	 */
	public void setHomepage (java.lang.String homepage) {
		this.homepage = homepage;
	}



	/**
	 * Return the value associated with the column: interest
	 */
	public java.lang.String getInterest () {
		return interest;
	}

	/**
	 * Set the value related to the column: interest
	 * @param interest the interest value
	 */
	public void setInterest (java.lang.String interest) {
		this.interest = interest;
	}



	/**
	 * Return the value associated with the column: DbPapers
	 */
	public java.util.Set<dao.hibernate.DbPaper> getDbPapers () {
		return dbPapers;
	}

	/**
	 * Set the value related to the column: DbPapers
	 * @param dbPapers the DbPapers value
	 */
	public void setDbPapers (java.util.Set<dao.hibernate.DbPaper> dbPapers) {
		this.dbPapers = dbPapers;
	}

	public void addToDbPapers (dao.hibernate.DbPaper dbPaper) {
		if (null == getDbPapers()) setDbPapers(new java.util.TreeSet<dao.hibernate.DbPaper>());
		getDbPapers().add(dbPaper);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof dao.hibernate.DbAuthor)) return false;
		else {
			dao.hibernate.DbAuthor dbAuthor = (dao.hibernate.DbAuthor) obj;
			if (null == this.getId() || null == dbAuthor.getId()) return false;
			else return (this.getId().equals(dbAuthor.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}