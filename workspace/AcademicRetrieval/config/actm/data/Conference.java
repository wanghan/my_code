package actm.data;

import actm.data.base.BaseConference;



public class Conference extends BaseConference {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Conference () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Conference (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Conference (
		java.lang.Integer id,
		java.lang.String name,
		java.lang.Integer index,
		java.util.Date date) {

		super (
			id,
			name,
			index,
			date);
	}

/*[CONSTRUCTOR MARKER END]*/


}