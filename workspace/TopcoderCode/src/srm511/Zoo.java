package srm511;

import java.util.Arrays;

public class Zoo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]={0, 1, 2, 3, 4,0,1,2,3,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,13,13,14,14,15,15,16,16,17,17,18,18,19,19,20,20,21,21,22,22,23,23,24,24,25,25,26,26,27,27,28,28,29,29,30,30,31,31};
		System.out.println(new Zoo().theCount(a));
	}
	public long theCount(int[] answers){
		long result=1;
		Arrays.sort(answers);
		int count[]=new int[50];
		for(int i=0;i<answers.length;++i){
			count[answers[i]]++;
		}
		
		int n=0;
		int x=0;
		int y=0;
		for(int i=0;i<count.length;++i){
			if(count[i]==2){
				x++;
				y++;
				n+=2;
			}
			else if(count[i]==1){
				int k=i;
				while(k<50&&count[k]==1){
					k++;
					n++;
				}
				y=k-1;
				break;
			}
			else if(count[i]==0){
				break;
			}
			else{
				return 0;
			}
		}
		
		if(n==answers.length){
			for(int i=0;i<x;++i){
				result*=2;
			}
			result*=2;
		}
		else{
			return 0;
		}
		return result;
	}
}
