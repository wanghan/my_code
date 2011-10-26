package actm.data;

import actm.data.base.BaseAuthor;



public class Author extends BaseAuthor {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Author () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Author (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Author (
		java.lang.Integer id,
		java.lang.String name,
		java.lang.String link,
		java.lang.Integer index) {

		super (
			id,
			name,
			link,
			index);
	}

/*[CONSTRUCTOR MARKER END]*/


}