package utils;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1="011101000111001001101111011010100110000101101";
		String s2="11000101110011010000111010001101101";
		String s=s1+s2;
		for(int i=0;i<80;i+=8){
			System.out.println(s.substring(i,i+8));
		}
	}

}
