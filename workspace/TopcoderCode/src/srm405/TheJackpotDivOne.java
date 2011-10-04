package srm405;

import java.math.BigInteger;
import java.util.Arrays;

public class TheJackpotDivOne {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long b=Long.parseLong("1000000000000000000");
		long a[]={b,b,b,b,b,b,b,b,b,b,
				b,b,b,b,b,b,b,b,b,b,
				b,b,b,b,b,b,b,b,b,b,
				b,b,b,b,b,b,b,b,b,b,
				b,b,b,b,b,b,b};
		
		long[] re=new TheJackpotDivOne().find(a,b-100);
		for (long l : re) {
			System.out.println(l);
		}
	}
	public long[] find(long[] money, long jackpot){
		BigInteger N=new BigInteger(String.valueOf(money.length));
		BigInteger b1=new BigInteger("1");
		BigInteger sum=new BigInteger("0");
	//	BigInteger jack=new BigInteger(String.valueOf(jackpot));
		for(int i=0;i<money.length;++i){
			sum=sum.add(new BigInteger(String.valueOf(money[i])));
		}
		while(jackpot>0){
	//		System.out.println(jackpot);
			Arrays.sort(money);
			if(money[0]==money[money.length-1]){
				long ttt=jackpot/money.length;
				for(int i=0;i<money.length;++i){
					money[i]+=ttt;
				}
				long qqq=jackpot%money.length;
				for(int i=0;i<qqq;++i){
					money[i]++;
				}
				break;
			}
			long sub=Long.parseLong(sum.divide(N).add(b1).subtract(new BigInteger(String.valueOf(money[0]))).toString());
			if(sub<jackpot){
				money[0]+=(long)sub;
				jackpot-=(long)sub;
				sum=sum.add(new BigInteger(String.valueOf(sub)));
			}
			else{
				money[0]+=jackpot;
				break;
			}
		}
		Arrays.sort(money);
		return money;
	}
}
