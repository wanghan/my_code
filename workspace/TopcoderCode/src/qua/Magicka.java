package qua;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Magicka {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner reader=new Scanner(System.in);
		int t=Integer.parseInt(reader.next());
		for(int ttt=1;ttt<=t;++ttt){
			int no=Integer.parseInt(reader.next());
			
			HashMap<String, Character> formMap=new HashMap<String, Character>();
			HashSet<String> oppoMap=new HashSet<String>();
			if(no!=0){
				for(int i=0;i<no;++i){
					String temp=reader.next();
					formMap.put(String.valueOf(temp.charAt(0))+String.valueOf(temp.charAt(1)), temp.charAt(2));
					formMap.put(String.valueOf(temp.charAt(1))+String.valueOf(temp.charAt(0)), temp.charAt(2));
				}
			}
			int nf=Integer.parseInt(reader.next());
			for(int i=0;i<nf;++i){
				String temp=reader.next();
				oppoMap.add(String.valueOf(temp.charAt(0))+String.valueOf(temp.charAt(1)));
				oppoMap.add(String.valueOf(temp.charAt(1))+String.valueOf(temp.charAt(0)));
				
			}
			
			
			int n=Integer.parseInt(reader.next());
			String list=reader.next();
			
			LinkedList<Character> curList=new LinkedList<Character>();
			
			for(int i=0;i<n;++i){
				if(curList.size()==0){
					curList.addLast(list.charAt(i));
				}
				else{
					String testForm=curList.getLast()+String.valueOf(list.charAt(i));
					if(formMap.containsKey(testForm)){
						curList.removeLast();
						curList.addLast(formMap.get(testForm));
					}
					else{
						boolean beCleared=false;
						for (Character character : curList) {
							String testS=String.valueOf(character)+String.valueOf(list.charAt(i));
							if(oppoMap.contains(testS)){
								curList.clear();
								beCleared=true;
								break;
							}
						}
						if(!beCleared){
							curList.addLast(list.charAt(i));
						}
					}
				}
			}
//			StringBuffer result=new StringBuffer();
//			result.append("[");
			System.out.println("Case #"+ttt+": "+curList.toString());
			
		}
	}

}
