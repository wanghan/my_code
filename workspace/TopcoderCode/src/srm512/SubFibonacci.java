package srm512;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class SubFibonacci {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> test=new ArrayList<Integer>();
		test.add((int)(Math.random()*10));
		for(int i=1;i<50;++i){
			test.add(test.get(test.size()-1)+(int)(Math.random()*500));
		}
		System.out.println(new SubFibonacci().maxElements(test.toArray(new Integer[0])));
	}
	public int maxElements(Integer[] S){
		int max=0;
		Arrays.sort(S);
		for(int i=0;i<S.length;++i){
			max=Math.max(max, S[i]);
		}
		HashSet<Integer> fff=new HashSet<Integer>();
		ArrayList<Integer> remains=new ArrayList<Integer>();
		int result=0;
		for(int i=1;i<max;++i){
			for(int j=1;j<max;++j){
				fff.clear();
				remains.clear();
				fff.add(i);
				fff.add(j);
				int a=i;
				int b=j;
				while(true){
					int temp=a;
					a=b;
					b+=temp;
					if(b>max){
						break;
					}
					fff.add(b);
				}
				int count=0;
				int maxIndex=0;
				for(int k=0;k<S.length;++k){
					if(fff.contains(S[k])){
						count++;
						maxIndex=k;
					}
					
				}
				if((count+S.length-1-maxIndex)<result){
					continue;
				}
				for(int k=maxIndex+1;k<S.length;++k){
					remains.add(S[k]);
				}
				if(count>=S.length-2){
					return S.length;
				}
				else if(count==0||count==1){
					continue;
				}
				else{
					
					count+=countMax(remains);
					result=Math.max(result, count);
				}
			}
		}
		return result;
	}
	private int countMax(ArrayList<Integer> re){
		int max=0;
		for(int i=0;i<re.size();++i){
			max=Math.max(max, re.get(i));
		}
		int result=0;
		HashSet<Integer> fff=new HashSet<Integer>();
		for(int i=1;i<max;++i){
			for(int j=1;j<max;++j){
				fff.clear();
				fff.add(i);
				fff.add(j);
				int a=i;
				int b=j;
				while(true){
					int temp=a;
					a=b;
					b+=temp;
					if(b>max){
						break;
					}
					fff.add(b);
				}
				int count=0;
				for(int k=0;k<re.size();++k){
					if(fff.contains(re.get(k))){
						count++;
					}
				}
				if(count==re.size()){
					return re.size();
				}
				else{
					result=Math.max(result, count);
				}
			}
		}
		return result;
	}
}
