package srm470;

import java.util.TreeSet;

public class DrawingLines {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=8;
		int b[]={1,4,3,6,7};
		int c[]={1,3,2,4,5};

		System.out.println(new DrawingLines().countLineCrossings(a, b, c));
	}
	public double countLineCrossings(int n, int[] startDot, int[] endDot){
		TreeSet<Integer> start=new TreeSet<Integer>();
		TreeSet<Integer> end=new TreeSet<Integer>();
		for(int i=0;i<startDot.length;++i){
			startDot[i]--;
			start.add(startDot[i]);
			
		}
		for(int i=0;i<endDot.length;++i){
			endDot[i]--;
			end.add(endDot[i]);
		}
		int sum=0;
		int sum1=0;
		int total=0;
		
		for(int k=0;k<startDot.length;++k){
			int sm=findSmallNum(startDot, startDot[k]);
			int em=findSmallNum(endDot,  endDot[k]);
			sum+=(startDot[k]-sm)*(n-endDot[k]-endDot.length+em)+(n-startDot[k]-startDot.length+sm)*(endDot[k]-em);
			for(int p=k+1;p<startDot.length;++p){
				if(isCross(startDot[k], endDot[k], startDot[p], endDot[p])){
					total++;
				}
			}
		}
		
		for(int i=0;i<n;++i){
			if(!start.contains(i)){
				for(int j=0;j<n;++j){
					if(!end.contains(j)){	
						int sm=findSmallNum(startDot, i);
						int em=findSmallNum(endDot, j);
						sum1+=(i-sm)*(n-j-1-endDot.length+em)+(n-i-1-startDot.length+sm)*(j-em);
					}	
				}
			}
			
		}
		if(n-startDot.length==2){
			sum1=sum1/2;
		}
		else{
			sum1=sum1/2*(n-startDot.length-2)/(n-1-startDot.length);
		}
		
		sum+=sum1;
		return 1.0*sum/(n-startDot.length)/(n-1-startDot.length)+total;
	}
	public boolean isCross(int x1,int y1,int x2,int y2){
		if((x1-x2)*(y1-y2)<0){
			return true;
		}
		else{
			return false;
		}
	}
	public int  findSmallNum(int a[],int n){
		int result=0;
		for(int i=0;i<a.length;++i){
			if(a[i]<n)
				result++;
		}
		return result;
	}
	public boolean contain(int a[],int b[],int i,int j){
		for(int k=0;k<a.length;++k){
			if(a[k]==i&&b[k]==j){
				return true;
			}
		}
		return false;
	}
	
}
