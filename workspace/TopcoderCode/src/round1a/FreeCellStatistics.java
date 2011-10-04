package round1a;

import java.util.Scanner;

public class FreeCellStatistics {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);
		int t = reader.nextInt();
		for (int ttt = 1; ttt <= t; ++ttt) {
			long n = reader.nextLong();
			int pd = reader.nextInt();
			int pg = reader.nextInt();
			boolean isBroken = true;
			if (pg == 100 && pd < 100) {
				isBroken = true;
			}
			else if(pg == 100 && pd == 100) {
				isBroken = false;
			} else if (pg == 0 && pd != 0) {
				isBroken = true;
			} else if (pg != 0 && pd == 0) {
				isBroken = false;
			} else if (pg == 0 && pd == 0) {
				isBroken = false;
			} else {
				int gcd=gcd(100,pd);
				long mod=100*gcd;
				if(mod<=n){
					isBroken=false;
				}
				else{
					isBroken=true;
				}
			}
			if (isBroken) {
				System.out.println("Case #" + ttt + ": Broken");
			} else {
				System.out.println("Case #" + ttt + ": Possible");
			}
		}
	}

	public static int gcd(int a, int b) {
		int temp;
		if (a < b)/* 交换两个数，使大数放在a上 */
		{
			temp = a;
			a = b;
			b = temp;
		}
		while (b != 0)/* 利用辗除法，直到b为0为止 */
		{
			temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
}
