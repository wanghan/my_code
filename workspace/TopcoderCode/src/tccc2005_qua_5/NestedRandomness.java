package tccc2005_qua_5;

public class NestedRandomness {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new NestedRandomness().probability(4, 3, 1));
	}
	
	
	public double probability(int N, int nestings, int target){
		double prob[]=new double[N];
		int nest=1;
		for(int i=0;i<N;++i){
			prob[i]=1.0/N;
		}
		while(nest<nestings){
			
			for(int i=0;i<=N-nest;++i){
				double temp=0;
				for(int j=i+1;j<=N-nest;++j){
					temp+=1.0/j*prob[j];
				}
				prob[i]=temp;
			}
			nest++;
		}
		
		return prob[target];
	}
}
