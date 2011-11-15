package dao.hibernate.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the db_paper table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="db_paper"
 */

public abstract class BaseDbPaper  implements Serializable {

	public static String REF = "DbPaper";
	public static String PROP_SOURCE = "Source";
	public static String PROP_LINK = "Link";
	public static String PROP_DOI = "Doi";
	public static String PROP_PAGES = "Pages";
	public static String PROP_DOI_LINK = "DoiLink";
	public static String PROP_ID = "Id";
	public static String PROP_TM_INDEX = "TmIndex";
	public static String PROP_CONFERENCE = "Conference";
	public static String PROP_TITLE = "Title";
	public static String PROP_ABSTRACT = "Abstract";


	// constructors
	public BaseDbPaper () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseDbPaper (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseDbPaper (
		java.lang.Integer id,
		dao.hibernate.DbConference conference,
		java.lang.String title,
		java.lang.String m_abstract,
		java.lang.Integer tmIndex) {

		this.setId(id);
		this.setConference(conference);
		this.setTitle(title);
		this.setAbstract(m_abstract);
		this.setTmIndex(tmIndex);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String m_abstract;
	private java.lang.String doi;
	private java.lang.String doiLink;
	private java.lang.String link;
	private java.lang.String pages;
	private java.lang.String source;
	private java.lang.String title;
	private java.lang.Integer tmIndex;

	// many to one
	private dao.hibernate.DbConference conference;

	// collections
	private java.util.Set<dao.hibernate.DbAuthor> dbAuthors;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="id"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: abstract
	 */
	public java.lang.String getAbstract () {
		return m_abstract;
	}

	/**
	 * Set the value related to the column: abstract
	 * @param m_abstract the abstract value
	 */
	public void setAbstract (java.lang.String m_abstract) {
		this.m_abstract = m_abstract;
	}



	/**
	 * Return the value associated with the column: doi
	 */
	public java.lang.String getDoi () {
		return doi;
	}

	/**
	 * Set the value related to the column: doi
	 * @param doi the doi value
	 */
	public void setDoi (java.lang.String doi) {
		this.doi = doi;
	}



	/**
	 * Return the value associated with the column: doi_link
	 */
	public java.lang.String getDoiLink () {
		return doiLink;
	}

	/**
	 * Set the value related to the column: doi_link
	 * @param doiLink the doi_link value
	 */
	public void setDoiLink (java.lang.String doiLink) {
		this.doiLink = doiLink;
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
	 * Return the value associated with the column: pages
	 */
	public java.lang.String getPages () {
		return pages;
	}

	/**
	 * Set the value related to the column: pages
	 * @param pages the pages value
	 */
	public void setPages (java.lang.String pages) {
		this.pages = pages;
	}



	/**
	 * Return the value associated with the column: source
	 */
	public java.lang.String getSource () {
		return source;
	}

	/**
	 * Set the value related to the column: source
	 * @param source the source value
	 */
	public void setSource (java.lang.String source) {
		this.source = source;
	}



	/**
	 * Return the value associated with the column: title
	 */
	public java.lang.String getTitle () {
		return title;
	}

	/**
	 * Set the value related to the column: title
	 * @param title the title value
	 */
	public void setTitle (java.lang.String title) {
		this.title = title;
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
	 * Return the value associated with the column: conference_id
	 */
	public dao.hibernate.DbConference getConference () {
		return conference;
	}

	/**
	 * Set the value related to the column: conference_id
	 * @param conference the conference_id value
	 */
	public void setConference (dao.hibernate.DbConference conference) {
		this.conference = conference;
	}



	/**
	 * Return the value associated with the column: DbAuthors
	 */
	public java.util.Set<dao.hibernate.DbAuthor> getDbAuthors () {
		return dbAuthors;
	}

	/**
	 * Set the value related to the column: DbAuthors
	 * @param dbAuthors the DbAuthors value
	 */
	public void setDbAuthors (java.util.Set<dao.hibernate.DbAuthor> dbAuthors) {
		this.dbAuthors = dbAuthors;
	}

	public void addToDbAuthors (dao.hibernate.DbAuthor dbAuthor) {
		if (null == getDbAuthors()) setDbAuthors(new java.util.TreeSet<dao.hibernate.DbAuthor>());
		getDbAuthors().add(dbAuthor);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof dao.hibernate.DbPaper)) return false;
		else {
			dao.hibernate.DbPaper dbPaper = (dao.hibernate.DbPaper) obj;
			if (null == this.getId() || null == dbPaper.getId()) return false;
			else return (this.getId().equals(dbPaper.getId()));
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