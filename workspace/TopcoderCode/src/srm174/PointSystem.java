package srm174;

public class PointSystem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new PointSystem().oddsOfWinning(2, 1, 40));
	}
	public double oddsOfWinning(int pointsToWin, int pointsToWinBy, int skill){
		
		double stat[][]=new double[2000][2000];
		double ds=skill/100.0;
		if(pointsToWin==1&&pointsToWinBy==1){
			return ds;
		}
		stat[1][0]=ds;
		stat[0][1]=1-ds;
		double result=0;
		for(int k=1;k<1999;++k){
			for(int low=0;low<=k;++low){
				int up=k-low;
				if(up>=pointsToWin&&(up-low)>=pointsToWinBy){
					continue;
				}
				if(low>=pointsToWin&&(low-up)>=pointsToWinBy){
					result+=stat[low][up];
					continue;
				}
				stat[low+1][up]+=stat[low][up]*ds;
				stat[low][up+1]+=stat[low][up]*(1-ds);
			}
			
		}
		return result;
	}
}
