package actm.data;

import actm.data.base.BasePaperAuthors;



public class PaperAuthors extends BasePaperAuthors {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public PaperAuthors () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public PaperAuthors (
		actm.data.Author author,
		actm.data.Paper paper) {

		super (
			author,
			paper);
	}

/*[CONSTRUCTOR MARKER END]*/


}