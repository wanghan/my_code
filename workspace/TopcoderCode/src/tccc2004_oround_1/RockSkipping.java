package tccc2004_oround_1;

public class RockSkipping {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="........................X";
//		System.out.println(s.length());
		System.out.println(new RockSkipping().probability(s, 50));
	}
	public double probability(String pads, int maxDist){
		
		double state[]=new double[6000];
		state[0]=1;
		while(maxDist>0){
			double temp[]=new double[6000];
			for(int i=5999;i>=0;--i){
				char c=pads.charAt(i%pads.length());
				if(state[i]>0&&c!='X'){
					for(int j=1;j<=maxDist;++j){
						temp[i+j]+=state[i]/maxDist;
					}
				}
				else{
					temp[i]+=state[i];
				}
			}
			maxDist--;
			for(int i=5999;i>=0;--i){
				state[i]=temp[i];
			}
		}
		double result=0;
		for(int i=5999;i>=0;--i){
			char c=pads.charAt(i%pads.length());
			if(c=='X'){
				result+=state[i];
			}
		}
		return 100-result*100;
	}
}
