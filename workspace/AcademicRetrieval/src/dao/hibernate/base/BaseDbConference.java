package dao.hibernate.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the db_conference table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="db_conference"
 */

public abstract class BaseDbConference  implements Serializable {

	public static String REF = "DbConference";
	public static String PROP_NAME = "Name";
	public static String PROP_GLOBAL_NAME = "GlobalName";
	public static String PROP_GLOBAL_INDEX = "GlobalIndex";
	public static String PROP_DATE = "Date";
	public static String PROP_ID = "Id";
	public static String PROP_TM_INDEX = "TmIndex";


	// constructors
	public BaseDbConference () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseDbConference (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseDbConference (
		java.lang.Integer id,
		java.lang.String name,
		java.lang.Integer tmIndex,
		java.util.Date date) {

		this.setId(id);
		this.setName(name);
		this.setTmIndex(tmIndex);
		this.setDate(date);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.util.Date date;
	private java.lang.Integer globalIndex;
	private java.lang.String globalName;
	private java.lang.String name;
	private java.lang.Integer tmIndex;

	// collections
	private java.util.Set<dao.hibernate.DbPaper> dbPapers;



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
	 * Return the value associated with the column: date
	 */
	public java.util.Date getDate () {
		return date;
	}

	/**
	 * Set the value related to the column: date
	 * @param date the date value
	 */
	public void setDate (java.util.Date date) {
		this.date = date;
	}



	/**
	 * Return the value associated with the column: global_index
	 */
	public java.lang.Integer getGlobalIndex () {
		return globalIndex;
	}

	/**
	 * Set the value related to the column: global_index
	 * @param globalIndex the global_index value
	 */
	public void setGlobalIndex (java.lang.Integer globalIndex) {
		this.globalIndex = globalIndex;
	}



	/**
	 * Return the value associated with the column: global_name
	 */
	public java.lang.String getGlobalName () {
		return globalName;
	}

	/**
	 * Set the value related to the column: global_name
	 * @param globalName the global_name value
	 */
	public void setGlobalName (java.lang.String globalName) {
		this.globalName = globalName;
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
		if (!(obj instanceof dao.hibernate.DbConference)) return false;
		else {
			dao.hibernate.DbConference dbConference = (dao.hibernate.DbConference) obj;
			if (null == this.getId() || null == dbConference.getId()) return false;
			else return (this.getId().equals(dbConference.getId()));
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