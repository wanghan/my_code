package tccc2003_semi3;

public class ZigZag {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]={ 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 };
		System.out.println(new ZigZag().longestZigZag(a));
	}
	public int longestZigZag(int[] sequence){
		if(sequence.length==1){
			return 1;
		}
		int zig[]=new int[sequence.length];
		zig[0]=1;

		for(int i=1;i<sequence.length;++i){
			int max=1;
			for(int j=0;j<=i-1;++j){
				if(zig[j]==1){
					if(sequence[i]>sequence[j]){
						max=Math.max(Math.abs(max), 2);
					}
					else if(sequence[i]<sequence[j]){
						max=-Math.max(Math.abs(max), 2);
					}
					else{
						max=Math.max(Math.abs(max), 1);
					}
				}
				else{
					if(zig[j]>0){
						if(sequence[i]<sequence[j]){
							max=-Math.max(Math.abs(max), Math.abs(zig[j])+1);
						}
						else if(sequence[i]>sequence[j]){
							max=Math.max(Math.abs(max), 2);
						}
						else{
							
						}
					}
					else{
						if(sequence[i]<sequence[j]){
							max=-Math.max(Math.abs(max), 2);
						}
						else if(sequence[i]>sequence[j]){
							max=Math.max(Math.abs(max), Math.abs(zig[j])+1);
						}
					}
				}
			}
			zig[i]=max;
		}
		
		int max=0;
		for (int i : zig) {
			if(Math.abs(i)>max){
				max=Math.abs(i);
			}
		}
		return max;
	}
}
