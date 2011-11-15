package actm.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the paper_authors table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="paper_authors"
 */

public abstract class BasePaperAuthors  implements Serializable {

	public static String REF = "PaperAuthors";


	// constructors
	public BasePaperAuthors () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BasePaperAuthors (
		actm.data.Author author,
		actm.data.Paper paper) {

		this.setAuthor(author);
		this.setPaper(paper);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key

	private actm.data.Author author;

	private actm.data.Paper paper;



	/**
     * @hibernate.property
     *  column=author_id
	 * not-null=true
	 */
	public actm.data.Author getAuthor () {
		return this.author;
	}

	/**
	 * Set the value related to the column: author_id
	 * @param author the author_id value
	 */
	public void setAuthor (actm.data.Author author) {
		this.author = author;
		this.hashCode = Integer.MIN_VALUE;
	}

	/**
     * @hibernate.property
     *  column=paper_id
	 * not-null=true
	 */
	public actm.data.Paper getPaper () {
		return this.paper;
	}

	/**
	 * Set the value related to the column: paper_id
	 * @param paper the paper_id value
	 */
	public void setPaper (actm.data.Paper paper) {
		this.paper = paper;
		this.hashCode = Integer.MIN_VALUE;
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof actm.data.PaperAuthors)) return false;
		else {
			actm.data.PaperAuthors paperAuthors = (actm.data.PaperAuthors) obj;
			if (null != this.getAuthor() && null != paperAuthors.getAuthor()) {
				if (!this.getAuthor().equals(paperAuthors.getAuthor())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getPaper() && null != paperAuthors.getPaper()) {
				if (!this.getPaper().equals(paperAuthors.getPaper())) {
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