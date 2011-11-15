package dao.hibernate;

import dao.hibernate.base.BaseDbConference;



public class DbConference extends BaseDbConference {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public DbConference () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public DbConference (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public DbConference (
		java.lang.Integer id,
		java.lang.String name,
		java.lang.Integer tmIndex,
		java.util.Date date) {

		super (
			id,
			name,
			tmIndex,
			date);
	}

/*[CONSTRUCTOR MARKER END]*/


}