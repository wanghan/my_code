package qua;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BotTrust {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
			
			int t=Integer.parseInt(reader.readLine());
			
			for(int ttt=1;ttt<=t;++ttt){
				String line=reader.readLine();
				StringTokenizer st=new StringTokenizer(line);
				int n=Integer.parseInt(st.nextToken());
				int robot[]=new int[n];
				int button[]=new int[n];
				
				for(int i=0;i<n;++i){
					String rrr=st.nextToken().trim();
					int bbb=Integer.parseInt(st.nextToken());
					if(rrr.equals("O")){
						robot[i]=0;
					}
					else{
						robot[i]=1;
					}
					button[i]=bbb;
				}
				
				int result=0;
				int availableMove0=0;
				int availableMove1=0;
				int cur0=1;
				int cur1=1;
				for(int i=0;i<n;++i){
					if(robot[i]==0){
						if(Math.abs(cur0-button[i])>=availableMove1){
							result+=(Math.abs(cur0-button[i])-availableMove1+1);
							
							availableMove0+=Math.abs(cur0-button[i])-availableMove1+1;
							availableMove1=0;
						}
						else{
							result++;
							availableMove1=0;
							availableMove0=1;
						}
						cur0=button[i];

					}
					else{
						if(Math.abs(cur1-button[i])>=availableMove0){
							result+=(Math.abs(cur1-button[i])-availableMove0+1);
							
							availableMove1+=Math.abs(cur1-button[i])-availableMove0+1;
							availableMove0=0;
						}
						else{
							result++;
							availableMove0=0;
							availableMove1=1;
						}
						cur1=button[i];

					}
				}
				
				System.out.println("Case #"+ttt+": "+result);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
