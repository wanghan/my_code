package round1c;

import java.util.Scanner;

public class PerfectHarmony {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);
		int t = reader.nextInt();
		for (int ttt = 1; ttt <= t; ++ttt) {
			int n=reader.nextInt();
			int l=reader.nextInt();
			int h=reader.nextInt();
			int aaa[]=new int[n];
			for(int i=0;i<n;++i){
				aaa[i]=reader.nextInt();
			}
			int result=-1;
			for(int k=l;k<=h;++k){
				boolean isOK=true;
				for(int i=0;i<n;++i){
					if(k%aaa[i]==0||aaa[i]%k==0){
						
					}
					else{
						isOK=false;
						break;
					}
				}
				if(isOK){
					result=k;
					break;
				}
			}
			if(result==-1){
				System.out.println("Case #"+ttt+": NO");
			}
			else{
				System.out.println("Case #"+ttt+": "+result);
			}
		}
	}

}
