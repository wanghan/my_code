package round1b;

import java.util.ArrayList;
import java.util.Scanner;

public class RevengeoftheHotDogs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);
		int t = reader.nextInt();
		for (int ttt = 1; ttt <= t; ++ttt) {
			int c=reader.nextInt();
			int d=reader.nextInt();
			double center=0;
			ArrayList<Integer> ven=new ArrayList<Integer>();
			ArrayList<Double> inip=new ArrayList<Double>();
			for(int i=0;i<c;++i){
				int p=reader.nextInt();
				int v=reader.nextInt();
				
				for(int j=0;j<v;++j){
					ven.add(p);

				}
			}
			
			double lll=ven.get(0);
			int move=0;
			double dis=0;
			double min=Double.MAX_VALUE;
			double ppp;
			int j=1;
			for(int i=1;i<ven.size();++i){
				if(Math.abs(ven.get(i)-lll)<j*d){
					move++;
					dis+=Math.abs(ven.get(i)-lll-j*d);
					j++;
					
				}else{
					if(move!=0)
						min=Math.min(min, dis/(move+1));
					move=0;
					dis=0;
					lll=ven.get(i);
					j=1;
				}
			}
			if(move!=0)
				min=Math.min(min, dis/(move+1));
			System.out.println(min);
		}
	}

}
