package srm522;

public class RowAndCoins {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	public 
	String getWinner(String cells){
		if(cells.charAt(0)=='A'||cells.charAt(cells.length()-1)=='A'){
			return "Alice";
		}
		return "Bob";
	}
}
