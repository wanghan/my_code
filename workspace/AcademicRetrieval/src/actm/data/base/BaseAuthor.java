package actm.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the author table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="author"
 */

public abstract class BaseAuthor  implements Serializable {

	public static String REF = "Author";
	public static String PROP_NAME = "Name";
	public static String PROP_INDEX = "Index";
	public static String PROP_LINK = "Link";
	public static String PROP_ACM_INDEX = "AcmIndex";
	public static String PROP_ID = "Id";


	// constructors
	public BaseAuthor () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAuthor (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAuthor (
		java.lang.Integer id,
		java.lang.String name,
		java.lang.String link,
		java.lang.Integer index) {

		this.setId(id);
		this.setName(name);
		this.setLink(link);
		this.setIndex(index);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String name;
	private java.lang.String link;
	private java.lang.String acmIndex;
	private java.lang.Integer index;

	// collections
	private java.util.Set<actm.data.Paper> papers;



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
	 * Return the value associated with the column: Papers
	 */
	public java.util.Set<actm.data.Paper> getPapers () {
		return papers;
	}

	/**
	 * Set the value related to the column: Papers
	 * @param papers the Papers value
	 */
	public void setPapers (java.util.Set<actm.data.Paper> papers) {
		this.papers = papers;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof actm.data.Author)) return false;
		else {
			actm.data.Author author = (actm.data.Author) obj;
			if (null == this.getId() || -1 == author.getId()) return false;
			else return (this.getId().equals(author.getId()));
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