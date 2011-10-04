package tccc2004_round4;

public class BadNeighbors {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]= { 94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,  
				  6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
				  52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72 };
		System.out.println(new BadNeighbors().maxDonations(a));
	}
	public int maxDonations(int[] donations){
		if(donations.length==2){
			return Math.max(donations[0], donations[1]);
		}
		
		int pick0[]=new int[donations.length];
		int nopick0[]=new int[donations.length];
		
		pick0[0]=donations[0];
		nopick0[0]=0;
		nopick0[1]=donations[1];
		pick0[1]=0;
		for(int i=2;i<donations.length-1;++i){
			int mmm=0;
			int nnn=0;
			for(int j=0;j<i-1;++j){
				mmm=Math.max(pick0[j]+donations[i], mmm);
				nnn=Math.max(nopick0[j]+donations[i], nnn);
			}
			nopick0[i]=nnn;
			pick0[i]=mmm;
		}
		int max=0;
		for(int i=0;i<donations.length-2;++i){
			 max=Math.max(donations[donations.length-1]+nopick0[i], max);
		}
		nopick0[donations.length-1]=max;
		
		pick0[donations.length-1]=0;
		
		max=0;
		for (int i : nopick0) {
			max=Math.max(max, i);
		}
		for (int i : pick0) {
			max=Math.max(max, i);
		}
		return max;
	}
}
