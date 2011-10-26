package actm.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the paper table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="paper"
 */

public abstract class BasePaper  implements Serializable {

	public static String REF = "Paper";
	public static String PROP_SOURCE = "Source";
	public static String PROP_INDEX = "Index";
	public static String PROP_LINK = "Link";
	public static String PROP_DOI = "Doi";
	public static String PROP_PAGES = "Pages";
	public static String PROP_DOI_LINK = "DoiLink";
	public static String PROP_ID = "Id";
	public static String PROP_CONFERENCE = "Conference";
	public static String PROP_TITLE = "Title";
	public static String PROP_ABSTRACT = "Abstract";


	// constructors
	public BasePaper () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BasePaper (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BasePaper (
		java.lang.Integer id,
		actm.data.Conference conference,
		java.lang.String title,
		java.lang.String m_abstract,
		java.lang.Integer index) {

		this.setId(id);
		this.setConference(conference);
		this.setTitle(title);
		this.setAbstract(m_abstract);
		this.setIndex(index);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String title;
	private java.lang.String pages;
	private java.lang.String source;
	private java.lang.String link;
	private java.lang.String m_abstract;
	private java.lang.String doi;
	private java.lang.String doiLink;
	private java.lang.Integer index;

	// many to one
	private actm.data.Conference conference;

	// collections
	private java.util.Set<actm.data.Author> authors;



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
	 * Return the value associated with the column: index
	 */
	public java.lang.Integer getIndex () {
		return index;
	}

	/**
	 * Set the value related to the column: index
	 * @param index the index value
	 */
	public void setIndex (java.lang.Integer index) {
		this.index = index;
	}



	/**
	 * Return the value associated with the column: conference_id
	 */
	public actm.data.Conference getConference () {
		return conference;
	}

	/**
	 * Set the value related to the column: conference_id
	 * @param conference the conference_id value
	 */
	public void setConference (actm.data.Conference conference) {
		this.conference = conference;
	}



	/**
	 * Return the value associated with the column: Authors
	 */
	public java.util.Set<actm.data.Author> getAuthors () {
		return authors;
	}

	/**
	 * Set the value related to the column: Authors
	 * @param authors the Authors value
	 */
	public void setAuthors (java.util.Set<actm.data.Author> authors) {
		this.authors = authors;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof actm.data.Paper)) return false;
		else {
			actm.data.Paper paper = (actm.data.Paper) obj;
			if (null == this.getId() || null == paper.getId()) return false;
			else return (this.getId().equals(paper.getId()));
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