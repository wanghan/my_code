package srm511;

public class FiveHundredEleven {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]={3, 5, 7, 9, 510};
		System.out.println(new FiveHundredEleven().theWinner(a));
	}
	
	public long wina=0;
	public long winb=0;
	public int suma=0;
	public int sumb=0;
	public boolean sel[];
	public int card[];
	public String theWinner(int[] cards){
		int result=0;
		
		if(cards.length==1){
			if(cards[0]==511){
				return "Toastman";
			}
			else{
				return "Fox Ciel";
			}
		}
		for(int i=0;i<cards.length;++i){
			result=result|cards[i];
		}
		if(result!=511){
			if(cards.length%2==0){
				return "Fox Ciel";
			}
			else{
				return "Toastman";
			}
		}
		else{
			sel=new boolean[cards.length];
			card=cards;
			for(int i=0;i<cards.length;++i){
				sel[i]=false;
			}
			find(0);
			if(wina>winb){
				return "Fox Ciel";
			}
			else{
				return "Toastman";
			}
		}
	}
	
	public void find(int pick){
		boolean hasMore=false;
		for(int i=0;i<card.length;++i){
			if(!sel[i]){
				hasMore=true;
				if(pick==0){
					int temp=suma;
					suma=suma|card[i];
					if(suma==511){
						winb++;
					}
					else{
						find(1);
					}
					suma=temp;
				}
				else{
					int temp=sumb;
					sumb=sumb|card[i];
					sel[i]=true;
					if(sumb==511){
						wina++;
					}
					else{
						find(0);
					}
					sel[i]=false;
					sumb=temp;
				}
			}
		}
		if(!hasMore){
			if(pick==0){
				winb++;
			}
			else{
				wina++;
			}
		}
	}
}
