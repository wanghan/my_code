package srm233;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class SmartWordToy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a="aaaa";
			String b="mmnn";
				String v[]={};

		System.out.println(System.currentTimeMillis());
		System.out.println(new SmartWordToy().minPresses(a, b, v));
		System.out.println(System.currentTimeMillis());
	}
	public int minPresses(String start, String finish, String[] forbid){
//		HashSet<String> forbidMap=new HashSet<String>();
		
		int [][][][] table=new int[26][26][26][26];
		
		for (String string : forbid) {
			StringTokenizer st=new StringTokenizer(string);
			String s1=st.nextToken();
			String s2=st.nextToken();
			String s3=st.nextToken();
			String s4=st.nextToken();
			
			for(int i1=0;i1<s1.length();++i1){
				for(int i2=0;i2<s2.length();++i2){
					for(int i3=0;i3<s3.length();++i3){
						for(int i4=0;i4<s4.length();++i4){
							StringBuffer sb=new StringBuffer();
							sb.append((char)s1.charAt(i1));
							sb.append((char)s2.charAt(i2));
							sb.append((char)s3.charAt(i3));
							sb.append((char)s4.charAt(i4));
						//	System.out.println(sb.toString());
						//	forbidMap.add(sb.toString());
							table[s1.charAt(i1)-'a'][s2.charAt(i2)-'a'][s3.charAt(i3)-'a'][s4.charAt(i4)-'a']=-1;
						}
					}
				}
			}
		}
		if(testCharfob(table, start)||testCharfob(table, finish)){
			return -1;
		}
		else if(start.equals(finish)){
			return 0;
		}
		LinkedList<String> queue=new LinkedList<String>();
		queue.add(start);
		while(queue.size()>0){
			String s=queue.poll();
			
			int curLevel=table[s.charAt(0)-'a'][s.charAt(1)-'a'][s.charAt(2)-'a'][s.charAt(3)-'a'];
	//		System.out.println(s+" "+curLevel);
			if(s.equals(finish)){
				return curLevel;
			}
			for(int i=0;i<4;++i){
				StringBuffer sb=new StringBuffer(s);
				int curChar=s.charAt(i);
				char left=(char)((curChar-'a'+25)%26+'a');
				char right=(char)((curChar-'a'+1)%26+'a');
				
				sb.setCharAt(i, left);
				if(sb.toString().equals(finish)){
					return curLevel+1;
				}
				if(!testCharfob(table, sb.toString())){
					queue.add(sb.toString());
					String s1=sb.toString();
					table[s1.charAt(0)-'a'][s1.charAt(1)-'a'][s1.charAt(2)-'a'][s1.charAt(3)-'a']=curLevel+1;
				}
				sb.setCharAt(i, right);
				if(sb.toString().equals(finish)){
					return curLevel+1;
				}
				if(!testCharfob(table, sb.toString())){
					queue.add(sb.toString());
					String s1=sb.toString();
					table[s1.charAt(0)-'a'][s1.charAt(1)-'a'][s1.charAt(2)-'a'][s1.charAt(3)-'a']=curLevel+1;
				}
			}
		}
		
		
		return -1;
	}
	
	public boolean testCharfob(int table[][][][], String s){
		if(table[s.charAt(0)-'a'][s.charAt(1)-'a'][s.charAt(2)-'a'][s.charAt(3)-'a']==-1){
			return true;
		}
		else if(table[s.charAt(0)-'a'][s.charAt(1)-'a'][s.charAt(2)-'a'][s.charAt(3)-'a']>0){
			return true;
		}
		return false;
	}
	
}
class item{
	String aa;
	int depth;
	public item(String s,int d) {
		// TODO Auto-generated constructor stub
		aa=s;
		depth=d;
	}
}