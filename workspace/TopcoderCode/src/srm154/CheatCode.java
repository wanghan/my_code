package srm154;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheatCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public int[] matches(String keyPresses, String[] codes){
		ArrayList<Integer> result=new ArrayList<Integer>();
		
		for(int i=0;i<codes.length;++i){
			StringBuffer reg=new StringBuffer(codes[i].charAt(0));
			for(int j=1;j<codes[i].length();++j){
				reg.append("[A-Z]*");
				reg.append(codes[i].charAt(j));
			}
			
			Pattern p=Pattern.compile(reg.toString());
			
			Matcher m=p.matcher(keyPresses);
			if(m.find()){
				result.add(i);
			}
		}
		
		int[] array=new int[result.size()];
		for(int i=0;i<result.size();++i){
			array[i]=result.get(i);
		}
		return array;
	}
}
