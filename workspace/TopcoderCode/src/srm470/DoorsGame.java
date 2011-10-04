package srm470;

import java.util.TreeSet;

public class DoorsGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String d="ABCC";
		int a=2;
		System.out.println(new DoorsGame().determineOutcome(d, a));
	}
	public int determineOutcome(String doors, int trophy){
		String a=doors.substring(0,trophy);
		String b=doors.substring(trophy);
		TreeSet<Character> aa=new TreeSet<Character>();
		TreeSet<Character> bb=new TreeSet<Character>();
		for(int i=0;i<a.length();++i){
			aa.add(a.charAt(i));
		}
		for(int i=0;i<b.length();++i){
			bb.add(b.charAt(i));
		}
		int chooseNum=0;
		boolean ture=true;
		while(true){
			chooseNum++;
			if(ture){
				if(aa.size()==1&&bb.size()==1){
					if(aa.first()==bb.first())
						return 0;
				}
				if(aa.size()==1){
					return chooseNum;
				}
				else{
					boolean flag=false;
					for (Character character : aa) {
						if(!bb.contains(character)){
							aa.remove(character);
							flag=true;
							break;
						}
					}
					if(!flag){
						Character cc=aa.first();
						aa.remove(cc);
						bb.remove(cc);
					}
				}
			}
			else{
				if(aa.size()==1&&bb.size()==1){
					if(aa.first()==bb.first())
						return 0;
				}
				if(bb.size()==1){
					return -chooseNum;
				}
				else{
					boolean flag=false;
					for (Character character : bb) {
						if(!aa.contains(character)){
							bb.remove(character);
							flag=true;
							break;
						}
					}
					if(!flag){
						Character cc=bb.first();
						aa.remove(cc);
						bb.remove(cc);
					}
				}
			}
			ture=!ture;
		}
	}

}
