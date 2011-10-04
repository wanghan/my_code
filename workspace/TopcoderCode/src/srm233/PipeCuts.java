package srm233;

import java.util.Arrays;

public class PipeCuts {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int aaa[]=new int[]{99, 88, 77, 66, 55, 44, 33, 22, 11};
		
		int bbb=50;
		System.out.println(new PipeCuts().probability(aaa, bbb));
	}
	public double probability(int[] weldLocations, int L){
		Arrays.sort(weldLocations);
		int total=(weldLocations.length)*(weldLocations.length-1)/2;
		int count=0;
		int tem=0;
		for(int i=0;i<weldLocations.length;++i){
			for(int j=i+1;j<weldLocations.length;++j){
				int left=weldLocations[i];
				int center=weldLocations[j]-weldLocations[i];
				int right=100-weldLocations[j];
				if(left>L||right>L||center>L){
					count+=1;
				}
				else{
			//		System.out.println(left+" "+right+" "+center);
					tem++;
				}
			}
		}
		return 1.0*count/total;
	}
}
