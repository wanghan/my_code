package dao.test.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the citys table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="citys"
 */

public abstract class BaseCitys  implements Serializable {

	public static String REF = "Citys";
	public static String PROP_CITY_NAME = "CityName";
	public static String PROP_ID = "Id";


	// constructors
	public BaseCitys () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCitys (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String cityName;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="CITY_ID"
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
	 * Return the value associated with the column: CITY_NAME
	 */
	public java.lang.String getCityName () {
		return cityName;
	}

	/**
	 * Set the value related to the column: CITY_NAME
	 * @param cityName the CITY_NAME value
	 */
	public void setCityName (java.lang.String cityName) {
		this.cityName = cityName;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof dao.test.Citys)) return false;
		else {
			dao.test.Citys citys = (dao.test.Citys) obj;
			if (null == this.getId() || null == citys.getId()) return false;
			else return (this.getId().equals(citys.getId()));
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