/**
 * 
 */
package atm.labeling;

/**
 * @author wanghan
 *
 */
public class LabelScoreMap {

	private Parse parse;
	private double score;
	
	public LabelScoreMap(Parse parse,double score) {
		// TODO Auto-generated constructor stub
		this.parse=parse;
		this.score=score;
	}

	public Parse getParse() {
		return parse;
	}

	public void setParse(Parse parse) {
		this.parse = parse;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	
}
