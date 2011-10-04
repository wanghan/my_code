package srm520;

/**
 * undo..
 * @author wanghan
 *
 */
public class SRMIntermissionPhase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	int limit[][];
	int hhh[];
	
	public int countWays(int[] points, String[] description){
		int n=points.length;
		hhh=new int [n+1];
		limit=new int[n][2];
		
		for(int i=0;i<n;++i){
			int low=0;
			int high=0;
			for(int j=0;j<3;++j){
				if (description[i].charAt(j)=='Y') {
					low+=1;
					high+=points[i];
				}
			}
			limit[i][0]=low;
			limit[i][1]=high;
			
		}
		
		long result=0;
		for(int i=0;i<n;++i){
			long cur=0;
			for(int k=0;k<n;++k){
				hhh[k]=limit[k][1];
			}
			for(int k=1;k<n;++k){
				for(int j=limit[i][1];j>=limit[i][0];--j){
					
				}
			}
		}
		return (int)result;
	}

}
