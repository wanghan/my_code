package srm507;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class CubeStickers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s[]={"purple","orange","orange","purple","black","orange","purple"};
		System.out.println(new CubeStickers().isPossible(s));
	}
	public String isPossible(String[] sticker){

		HashMap<String,tem>sss=new HashMap<String,tem>();
		for (String string : sticker) {
			if(sss.containsKey(string)){
				tem cur=sss.get(string);
				cur.v++;
			}
			else{
				sss.put(string, new tem(string));
			}
		}
		
		tem[] ttt=sss.values().toArray(new tem[0]);
		Arrays.sort(ttt,new Comparator<tem>(){
			
			@Override
			public int compare(tem arg0, tem arg1) {
				// TODO Auto-generated method stub
				return arg0.v-arg1.v;
			}
		});
		
		if(sss.size()>=5){
			return "YES";
		}
		else if(sss.size()==3){
			if(ttt[0].v>=2&&ttt[1].v>=2){
				return "YES";
			}
			else{
				return "NO";
			}
		}
		else if(sss.size()==4){
			if((ttt[0].v+ttt[1].v+ttt[2].v)>=4){
				return "YES";
			}
			else{
				return "NO";
			}
		}
		else{
			return "NO";
		}
	}
}
class tem{
	String s;
	int v;
	public tem(String ss) {
		// TODO Auto-generated constructor stub
		s=ss;
		v=1;
	}
}