package qua;

import java.text.DecimalFormat;
import java.util.Scanner;

public class GoroSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner reader=new Scanner(System.in);
		int t=reader.nextInt();
		for(int ttt=1;ttt<=t;++ttt){
			int n=reader.nextInt();
			int data[]=new int[n];
			int disOrder=0;
			for(int i=0;i<n;++i){
				data[i]=reader.nextInt();
				if(data[i]!=(i+1)){
					disOrder++;
				}
				
			}
			double result=2;
			if(disOrder==2){
				result=2;
			}
			else if(disOrder==0){
				result=0;
			}
			else if(disOrder==3){
				result=2;
			}
			else{
				int m2=disOrder/2;
				if(disOrder%2==0){
					result=2.0*m2;
				}
				else{
					result=2.0*m2+2;
				}
			}
			DecimalFormat format=new DecimalFormat("0.000000");
			System.out.println("Case #"+ttt+": "+format.format(result));
		}
	}

}
