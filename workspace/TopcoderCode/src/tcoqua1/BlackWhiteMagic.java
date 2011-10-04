package tcoqua1;

public class BlackWhiteMagic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new BlackWhiteMagic().count("BBBB"));
	}
	public int count(String creatures){
		int i=0;
		int j=creatures.length()-1;
		int result=0;
		while(i<j&&i<creatures.length()&&j>=0){
			while(i<creatures.length()&&creatures.charAt(i)=='W'){
				i++;
			}
			while(j>=0&&creatures.charAt(j)=='B'){
				j--;
			}
			if(i<j){
				result++;
				i++;
				j--;
			}
		}
		return result;
	}
}
