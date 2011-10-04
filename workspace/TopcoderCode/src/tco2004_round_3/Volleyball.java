package tco2004_round_3;

public class Volleyball {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Volleyball().win(8, 12, 0.4));
	}
	
	
	public static double stat[][]=new double[1000][1000];
	public static double p;
	public Volleyball() {
		// TODO Auto-generated constructor stub
		for(int i=0;i<1000;++i){
			for(int j=0;j<1000;++j){
				stat[i][j]=-1;
			}
		}
		stat[999][999]=0.01;
	}
	
	public 	double win(int sScore, int rScore, double probWinServe){
		p=probWinServe;
		return winn(sScore, rScore);
	}
	
	public double winn(int sScore, int rScore){
		if(sScore>=1000||rScore>=1000){
			return 0.01;
		}
		if(stat[sScore][rScore]!=-1){
			return stat[sScore][rScore];
		}
		if(sScore>=15&&(sScore-rScore)>=2){
			stat[sScore][rScore]=1;
			return 1;
		}
		if(rScore>=15&&(rScore-sScore)>=2){
			stat[sScore][rScore]=0;
			return 0;
		}
		double result=p*winn(sScore+1, rScore)+(1-p)*(1-winn(rScore+1,sScore));
		stat[sScore][rScore]=result;
		return result;
	}
}
