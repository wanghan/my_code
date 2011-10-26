package actm.data;

import actm.data.base.BasePaper;



public class Paper extends BasePaper {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Paper () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Paper (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Paper (
		java.lang.Integer id,
		actm.data.Conference conference,
		java.lang.String title,
		java.lang.String m_abstract,
		java.lang.Integer index) {

		super (
			id,
			conference,
			title,
			m_abstract,
			index);
	}

/*[CONSTRUCTOR MARKER END]*/


}