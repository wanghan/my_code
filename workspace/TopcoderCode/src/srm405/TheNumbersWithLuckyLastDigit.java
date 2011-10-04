package srm405;

public class TheNumbersWithLuckyLastDigit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new TheNumbersWithLuckyLastDigit().find(9999995));
	}
	public int find(int n){
		if(n<4 || n==5 ||n==6){
			return -1;
		}
		if(n%10==4||n%10==7) return 1;
		if(n%10==1) return 2;
		if(n%10==2) return 3;
		int a=find(n-7);
		int b=find(n-4);
		if(a==-1 &&b==-1)
			return -1;
		if(a==-1) return b+1;
		if(b==-1) return a+1;
		return Math.min(a,b)+1;
	}
}
