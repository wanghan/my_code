package dao.hibernate;

import dao.hibernate.base.BaseDbPaperAuthors;



public class DbPaperAuthors extends BaseDbPaperAuthors {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public DbPaperAuthors () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public DbPaperAuthors (
		dao.hibernate.DbAuthor author,
		dao.hibernate.DbPaper paper) {

		super (
			author,
			paper);
	}

/*[CONSTRUCTOR MARKER END]*/


}