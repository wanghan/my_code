package tccc2001_round1;

import java.util.ArrayList;
public class FlowerGarden {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]={1,2,3,4,5,6};
		int b[]={1,3,1,3,1,3};
		int c[]={2,4,2,4,2,4};
		
		int d[]=new FlowerGarden().getOrdering(a, b, c);
	}
	public int[] getOrdering(int[] height, int[] bloom, int[] wilt){
		flower[] flows=new flower[height.length];
		for (int i=0;i<bloom.length;++i){
			flows[i]=new flower(height[i],bloom[i],wilt[i]);
		}
		
		for (int i=0;i<bloom.length;++i){
			for (int j=i+1;j<bloom.length;++j){
				if(!((flows[i].bloom>flows[j].wilt)||(flows[j].bloom>flows[i].wilt))){
					if(flows[i].height<flows[j].height){
						flows[i].children.add(flows[j]);
						flows[j].patent.add(flows[i]);
					}
					else{
						flows[j].children.add(flows[i]);
						flows[i].patent.add(flows[j]);
					}
				}
			}
		}
		
		ArrayList<Integer> result=new ArrayList<Integer>();
		while(result.size()<bloom.length){
			int max=0;
			flower cur=null;
			for (flower f : flows) {
				if(f.patent.size()==0&&f.mark==0){
					if(f.height>max){
						cur=f;
						max=f.height;
					}
				}
			}
			result.add(cur.height);
			for (flower f : cur.children) {
				f.patent.remove(cur);
			}
			cur.mark=1;
		}
		int [] rrr=new int[result.size()];
		for(int i=0;i<bloom.length;++i){
			rrr[i]=result.get(i);
		}
		return rrr;
	}
}
class flower{
	int height;
	int bloom;
	int wilt;
	int mark=0;
	public flower(int h,int b,int w) {
		// TODO Auto-generated constructor stub
		this.height=h;
		this.bloom=b;
		this.wilt=w;
		children=new ArrayList<flower>();
		patent=new ArrayList<flower>();
	}
	
	ArrayList<flower> children;
	ArrayList<flower> patent;
	
}