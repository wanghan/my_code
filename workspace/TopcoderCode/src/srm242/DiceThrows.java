package srm242;

import java.util.Arrays;

public class DiceThrows {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=2;
		int b[]={1,1,1,2,2,2};
		int c=3;
		int d[]={1,1,1,1,1,1};
		System.out.println(new DiceThrows().winProbability(a, b, c, d));

	}
	public 	double winProbability(int numDiceA, int[] sidesA, int numDiceB, int[] sidesB){
		Arrays.sort(sidesA);
		Arrays.sort(sidesB);
		
		double staA[]=new double[20001];
		double staB[]=new double[20001];
		for(int k=0;k<sidesA.length;++k){
			staA[sidesA[k]]+=1.0/6;
			staB[sidesB[k]]+=1.0/6;
		}
		for(int i=1;i<numDiceA;++i){
			double temp[]=new double[20001];
			for(int j=20000;j>=0;--j){
				if(staA[j]>0){
					for(int k=0;k<sidesA.length;++k){
						temp[j+sidesA[k]]+=1.0/6*staA[j];
					}
				}
			}
			for(int j=20000;j>=0;--j){
				staA[j]=temp[j];
			}
		}
		for(int i=1;i<numDiceB;++i){
			double temp[]=new double[20001];
			for(int j=20000;j>=0;--j){
				if(staB[j]>0){
					for(int k=0;k<sidesB.length;++k){
						temp[j+sidesB[k]]+=1.0/6*staB[j];
					}
				}
			}
			for(int j=20000;j>=0;--j){
				staB[j]=temp[j];
			}
		}
		
		double result=0;
		for(int i=0;i<20001;++i){
			for(int j=0;j<i;++j){
				result+=staA[i]*staB[j];
			}
		}
		return result;
	}
}
