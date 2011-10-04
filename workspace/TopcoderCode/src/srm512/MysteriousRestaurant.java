package srm512;

public class MysteriousRestaurant {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String []s={"26", "14", "72", "39", "32", "85", "06", "91","26", "14", "72", "39", "32", "85", "06", "91","26", "14", "72", "39", "32", "85", "06", "91","26", "14", "72", "39", "32", "85", "06", "91","26", "14", "72", "39", "32", "85", "06", "91","26", "14", "72", "39", "32", "85", "06", "91"};
		System.out.println( new MysteriousRestaurant().maxDays(s,1000));
	}
	
	int max;
	int [] picked=new int[7];
	String[] ppp;

	public int maxDays(String[] prices, int budget){
		ppp=prices;
		max=0;
		find(0, budget);
		return max;
	}
	
	private void find(int day,int money){
		if(day==ppp.length-1){
			int value=getValue(ppp[day].charAt(picked[day%7]));
			if(money<value){
				max=Math.max(max, day);
				return ;
			}
			else{
				max=Math.max(max, day+1);
			}
		}
		else if(day<7){
			int n=ppp[day].length();
			for(int i=0;i<n;++i){
				
				int value=getValue(ppp[day].charAt(i));
				if(money<value){
					max=Math.max(max, day);
//					for (int kkk : picked) {
//						System.out.print(kkk+" ");
//						
//					}
//					System.out.println(max);
				}
				else{
					picked[day]=i;
					find(day+1, money-value);
				}
			}
		}
		else{
			int value=getValue(ppp[day].charAt(picked[day%7]));
			if(money<value){
				max=Math.max(max, day);
//				for (int kkk : picked) {
//					System.out.print(kkk+" ");
//					
//				}
//				System.out.println(max);
				return ;
			}
			else{
				find(day+1, money-value);
			}
		}
	}
	
	private int getValue(char c){
		if(c>='0'&&c<='9'){
			
			return c-'0';
		}
		
	if(c>='a'&&c<='z'){
			
			return c-'a'+36;
		}
	if(c>='A'&&c<='Z'){
		
		return c-'A'+10;
	}
	return 0;
	}
}
