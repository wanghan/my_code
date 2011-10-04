package srm507;

public class CubePacking {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new CubePacking().getMinimumVolume(1000000000, 1000000, 10));
	}	
	public int getMinimumVolume(int Ns, int Nb, int L){
		int a=L;
		int b=L;
		int min=Integer.MAX_VALUE;
		int minV=Ns+Nb*L*L*L;
		int l2=L*L;
		for(int i=L;i<=(min/l2+1);++i){
			int ibl=i/L;
			for(int j=i;j<=(min/i/i+1);++j){
				int c=minV/(i*j);
				if(minV%(i*j)!=0){
					c++;
				}
				if(ibl*(j/L)*(c/L)>=Nb){
					int cur=i*j*c;
					if(cur<min&&cur>0){
						min=cur;
						System.out.println(i+" "+j);
					}
				}
			}
		}
		return min;
	}
}
