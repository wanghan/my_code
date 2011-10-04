package round1b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class RPI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		
		
		try {
			int t=Integer.parseInt(reader.readLine());
			for (int ttt = 1; ttt <= t; ++ttt) {
				int n = Integer.parseInt(reader.readLine());
				team[] teamMap=new team[n];
				for(int i=0;i<n;++i){
					String line=reader.readLine();
					teamMap[i] =new team();
					for(int j=0;j<n;++j){
						if(line.charAt(j)=='.'){
							
						}
						else if(line.charAt(j)=='1'){
							teamMap[i].wins.add(j);
							teamMap[i].ops.add(j);
						}
						else if(line.charAt(j)=='0'){
							teamMap[i].lost.add(j);
							teamMap[i].ops.add(j);
						}
					}

				}
				double[] wp=new double[n];
				double[] owp=new double[n];
				double[] oowp=new double[n];
				double[] RPI=new double[n];
				for(int i=0;i<n;++i){
					owp[i]=0;
					wp[i]=1.0*teamMap[i].wins.size()/(teamMap[i].wins.size()+teamMap[i].lost.size());
					for (Integer j : teamMap[i].ops) {
						if(j!=i){
							int www=teamMap[j].getWinEx(i);
							int lll=teamMap[j].getLoseEx(i);
							owp[i]+=1.0*(www)/(www+lll);
						}
					}
	
					owp[i]/=(teamMap[i].ops.size());
				}
				
				for(int i=0;i<n;++i){
					oowp[i]=0;
					for (Integer j : teamMap[i].ops) {
						if(j!=i){
							oowp[i]+=owp[j];
						}
					}
					oowp[i]/=(teamMap[i].wins.size()+teamMap[i].lost.size());
					RPI[i]=0.25*wp[i]+0.5*owp[i]+0.25*oowp[i];
				}
				System.out.println("Case #"+ttt+":");
				for(int i=0;i<n;++i){
					System.out.println(RPI[i]);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
class team{

	HashSet<Integer> wins;
	HashSet<Integer> lost;
	HashSet<Integer> ops;
	public team() {
		// TODO Auto-generated constructor stub
		this.lost=new HashSet<Integer>();
		this.wins=new HashSet<Integer>();
		this.ops=new HashSet<Integer>();
	}
	public int getWinEx(int ex){
		if(wins.contains(ex)){
			return wins.size()-1;
		}
		else{
			return wins.size();
		}
	}
	public int getLoseEx(int ex){
		if(lost.contains(ex)){
			return lost.size()-1;
		}
		else{
			return lost.size();
		}
	}
}