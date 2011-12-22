package srm358;

import java.util.ArrayList;

public class CyclicWords {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//test for ssl

	}
	public int differentCW(String[] words){
		ArrayList<String> keys=new ArrayList<String>();
		keys.add(words[0]);
		
		for(int i=1;i<words.length;++i){
			String w=words[i];
			boolean flag=false;
			for (String string : keys) {
				if(isCyclic(string, w)){
					flag=true;
					break;
				}
			}
			if(!flag){
				keys.add(w);
			}
		}
		return keys.size();
	}
	
	private boolean isCyclic(String w1, String w2){
		if(w1.length()!=w2.length()){
			return false;
		}
		String s=w1+w1;
		if(s.indexOf(w2)>=0){
			return true;
		}
		else{
			return false;
		}
	}
}
