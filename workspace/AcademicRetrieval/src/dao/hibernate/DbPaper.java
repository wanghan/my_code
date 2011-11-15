package dao.hibernate;

import dao.hibernate.base.BaseDbPaper;



public class DbPaper extends BaseDbPaper {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public DbPaper () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public DbPaper (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public DbPaper (
		java.lang.Integer id,
		dao.hibernate.DbConference conference,
		java.lang.String title,
		java.lang.String m_abstract,
		java.lang.Integer tmIndex) {

		super (
			id,
			conference,
			title,
			m_abstract,
			tmIndex);
	}

/*[CONSTRUCTOR MARKER END]*/


}