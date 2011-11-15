package dao.hibernate;

import dao.hibernate.base.BaseDbAuthor;



public class DbAuthor extends BaseDbAuthor {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public DbAuthor () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public DbAuthor (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public DbAuthor (
		java.lang.Long id,
		java.lang.String name,
		java.lang.String link,
		java.lang.Integer tmIndex) {

		super (
			id,
			name,
			link,
			tmIndex);
	}

/*[CONSTRUCTOR MARKER END]*/


}