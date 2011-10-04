package qua;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class TheKillerWord {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
			
			int t=Integer.parseInt(reader.readLine());
			
			for(int ttt=1;ttt<=t;++ttt){
				HashMap<Integer, vocabulary> vocaMap=new HashMap<Integer, vocabulary>();
				String line=reader.readLine();
				StringTokenizer st=new StringTokenizer(line);
				int n=Integer.parseInt(st.nextToken());
				int m=Integer.parseInt(st.nextToken());
				
				String d[]=new String[n];
				String l[]=new String[m];
				for(int i=0;i<n;++i){
					d[i]=reader.readLine().trim();
					vocabulary vvv=vocaMap.get(d[i].length());
					if(vvv==null){
						vvv=new vocabulary();	
						vocaMap.put(d[i].length(), vvv);
					}
					vvv.insert(d[i]);
				}
				for(int i=0;i<m;++i){
					l[i]=reader.readLine().trim();
				}
				
				StringBuffer result=new StringBuffer();
				for(int i=0;i<m;++i){
					int max=-1;
					String maxs=null;
					for(int j=0;j<n;++j){
						String curWord=d[j];
						vocabulary curV=vocaMap.get(d[j].length()).clone();
						if(curV.words.size()==1){
							if(max<0){
								max=0;
								maxs=d[j];
							}
							continue;
						}
						int curScore=0;
						for(int k=0;k<26;++k){
							if(curV.al[l[i].charAt(k)-'a']>0){
								
								Integer[] lo=getLoca(d[j],l[i].charAt(k));
								if(lo.length==0){
									curScore++;
									curV.removeContain(l[i].charAt(k));
									if(curV.words.size()==1){
										break;
									}
									continue;
								}
								else{
									curV.reveal(l[i].charAt(k), lo);
									if(curV.words.size()==1){
										break;
									}
								}
							}
						}
	//					System.out.println(d[j]+" "+curScore);
						if(curScore>max){
							max=curScore;
							maxs=d[j];
						}
					}
					result.append(maxs+" ");
				}
				System.out.println("Case #"+ttt+": "+result.toString().trim());
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static Integer[] getLoca(String s, char c){
		ArrayList<Integer> lo=new ArrayList<Integer>();
		for(int i=0;i<s.length();++i){
			if(s.charAt(i)==c){
				lo.add(i);
			}
		}
		return lo.toArray(new Integer[0]);
	}
}
class vocabulary{
	LinkedList<String> words;
	int [] al;
	public vocabulary() {
		// TODO Auto-generated constructor stub
		this.words=new LinkedList<String>();
		al=new int[26];
	}
	public void insert(String s){
		words.add(s);
		for(int i=0;i<s.length();++i){
			al[s.charAt(i)-'a']++;
		}
	}
	public void remove(String s){
		
		words.remove(s);
		for(int i=0;i<s.length();++i){
			al[s.charAt(i)-'a']--;
		}
	}
	public void removeContain(char c){
		LinkedList<String> temp=new LinkedList<String>();
		for (String string : words) {
				if(string.indexOf(c)>=0){
					temp.add(string);
					continue;
				}
		}
		for (String string : temp) {
			remove(string);
		}
	}
	public void reveal(char c, Integer[] loca){
		LinkedList<String> temp=new LinkedList<String>();
		for (String string : words) {
			Integer[] tempInt=TheKillerWord.getLoca(string, c);
			if(tempInt.length!=loca.length){
				temp.add(string);
				continue;
			}
			else{
				for(int i=0;i<loca.length;++i){
					if(tempInt[i]!=loca[i]){
						temp.add(string);
						break;
					}
				}
			}
			
		}
		for (String string : temp) {
			remove(string);
		}
	}
	public vocabulary clone(){
		vocabulary vvv=new vocabulary();
		for (String s : words) {
			vvv.insert(s);
			
		}
		return vvv;
	}
}