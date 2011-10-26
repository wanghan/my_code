package actm.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the conference table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="conference"
 */

public abstract class BaseConference  implements Serializable {

	public static String REF = "Conference";
	public static String PROP_NAME = "Name";
	public static String PROP_GLOBAL_NAME = "GlobalName";
	public static String PROP_GLOBAL_INDEX = "GlobalIndex";
	public static String PROP_INDEX = "Index";
	public static String PROP_DATE = "Date";
	public static String PROP_ID = "Id";


	// constructors
	public BaseConference () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseConference (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseConference (
		java.lang.Integer id,
		java.lang.String name,
		java.lang.Integer index,
		java.util.Date date) {

		this.setId(id);
		this.setName(name);
		this.setIndex(index);
		this.setDate(date);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String name;
	private java.lang.Integer index;
	private java.lang.Integer globalIndex;
	private java.lang.String globalName;
	private java.util.Date date;



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
	 * Return the value associated with the column: conf_name
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: conf_name
	 * @param name the conf_name value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof actm.data.Conference)) return false;
		else {
			actm.data.Conference conference = (actm.data.Conference) obj;
			if (null == this.getId() || null == conference.getId()) return false;
			else return (this.getId().equals(conference.getId()));
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