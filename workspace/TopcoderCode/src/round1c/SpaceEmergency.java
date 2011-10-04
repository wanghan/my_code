package round1c;

import java.util.Scanner;

public class SpaceEmergency {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);
		int t = reader.nextInt();
		for (int ttt = 1; ttt <= t; ++ttt) {
			int l=reader.nextInt();
			int tt=reader.nextInt();
			int n=reader.nextInt();
			int c=reader.nextInt();
			int cc[]=new int[c];
			for(int i=0;i<c;++i){
				cc[i]=reader.nextInt();
			}
			int dics[]=new int[n];
			for(int i=0;i<n;++i){
				dics[i]=cc[i%c];
			}
			if(l==1){
				int min=Integer.MAX_VALUE;
				for(int i=0;i<n;++i){
					int pick=i;
					int curt=0;
					for(int j=0;j<n;++j){
						if(j==pick){
							if(curt>=tt){
								curt+=dics[j];
							}
							else{
								curt+=2*dics[j];
							}
						}
						else{
							curt+=2*dics[j];
						}
					}
					if(curt<min){
						min=curt;
					}
				}
				System.out.println(min);
			}
			else if(l==2){
				int min=Integer.MAX_VALUE;
				for(int i=0;i<n;++i){
					for(int k=0;k<n;++k){
						if(k!=i){
							int curt=0;
							for(int j=0;j<n;++j){
								if(j==k||j==i){
									if(curt>=tt){
										curt+=dics[j];
									}
									else{
										curt+=2*dics[j];
									}
								}
								else{
									curt+=2*dics[j];
								}
							}
							if(curt<min){
								min=curt;
							}
						}
					}
				}
				System.out.println(min);
			}
		}
	}

}
