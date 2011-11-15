package dao.hibernate.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the db_paper_authors table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="db_paper_authors"
 */

public abstract class BaseDbPaperAuthors  implements Serializable {

	public static String REF = "DbPaperAuthors";


	// constructors
	public BaseDbPaperAuthors () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseDbPaperAuthors (
		dao.hibernate.DbAuthor author,
		dao.hibernate.DbPaper paper) {

		this.setAuthor(author);
		this.setPaper(paper);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key

	private dao.hibernate.DbAuthor author;

	private dao.hibernate.DbPaper paper;



	/**
     * @hibernate.property
     *  column=author_id
	 * not-null=true
	 */
	public dao.hibernate.DbAuthor getAuthor () {
		return this.author;
	}

	/**
	 * Set the value related to the column: author_id
	 * @param author the author_id value
	 */
	public void setAuthor (dao.hibernate.DbAuthor author) {
		this.author = author;
		this.hashCode = Integer.MIN_VALUE;
	}

	/**
     * @hibernate.property
     *  column=paper_id
	 * not-null=true
	 */
	public dao.hibernate.DbPaper getPaper () {
		return this.paper;
	}

	/**
	 * Set the value related to the column: paper_id
	 * @param paper the paper_id value
	 */
	public void setPaper (dao.hibernate.DbPaper paper) {
		this.paper = paper;
		this.hashCode = Integer.MIN_VALUE;
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof dao.hibernate.DbPaperAuthors)) return false;
		else {
			dao.hibernate.DbPaperAuthors dbPaperAuthors = (dao.hibernate.DbPaperAuthors) obj;
			if (null != this.getAuthor() && null != dbPaperAuthors.getAuthor()) {
				if (!this.getAuthor().equals(dbPaperAuthors.getAuthor())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getPaper() && null != dbPaperAuthors.getPaper()) {
				if (!this.getPaper().equals(dbPaperAuthors.getPaper())) {
					return false;
				}
			}
			else {
				return false;
			}
			return true;
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuilder sb = new StringBuilder();
			if (null != this.getAuthor()) {
				sb.append(this.getAuthor().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getPaper()) {
				sb.append(this.getPaper().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}