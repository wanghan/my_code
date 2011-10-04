package qua;

import java.util.Arrays;
import java.util.Scanner;

public class CandySplitting {

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
			int sum=0;
			long sum10=0;
			for(int i=0;i<n;++i){
				data[i]=reader.nextInt();
				sum^=data[i];
				sum10+=data[i];
			}
			if(sum==0){
				Arrays.sort(data);
				long result=sum10-data[0];
				System.out.println("Case #"+ttt+": "+result);
			}
			else{
				System.out.println("Case #"+ttt+": NO");
			}
		}
	}

}
