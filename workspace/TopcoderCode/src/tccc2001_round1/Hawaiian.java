package tccc2001_round1;

import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.Vector;

public class Hawaiian {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public String[] getWords(String sentence){
		HashSet<Character> chars=new HashSet<Character>();
		chars.add('a');
		chars.add('e');
		chars.add('o');chars.add('i');
		chars.add('u');
		chars.add('h');
		chars.add('k');
		chars.add('l');
		chars.add('m');
		chars.add('n');
		chars.add('p');
		chars.add('w');
		
		StringTokenizer st=new StringTokenizer(sentence);
		Vector<String> result=new Vector<String>();
		while(st.hasMoreTokens()){
			String temp=st.nextToken();
			if(isStringVaild(chars, temp.toLowerCase())){
				result.add(temp);
			}
		}
		return result.toArray(new String[0]);
	}
	
	public boolean isStringVaild(HashSet<Character> chars, String s){
		for(int i=0;i<s.length();++i){
			char c=s.charAt(i);
			if(!chars.contains(c)){
				return false;
			}
		}
		return true;
	}
}	
