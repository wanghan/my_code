package srm522;

public class CorrectMultiplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new CorrectMultiplication().getMinimum(10, 30, 500));
	}
	public long getMinimum(int a, int b, int c){
		long la=a;
		long lb=b;
		long lc=c;
		if(la*lb==lc){
			return 0;
		}
		else{
			long min=Math.abs(lc-la*lb);
			long move=0;
			while(true){
				long ta=la+move++;
				long k=0;
				for(k=0;;++k){
					long tb=k+lb;
					long mm=ta*tb;
					long s=move+k+Math.abs(mm-lc);
					if(mm>lc&&s>min){
						break;
					}
					if(s<min){
						System.out.println(ta+" "+tb+" "+mm);
						min=s;
					}
				}
				if(k==0)
					break;
				for(k=0;;++k){
					long tb=lb-k;
					if(tb<=0)
						break;
					long mm=ta*tb;
					long s=move+k+Math.abs(mm-lc);
					if(mm<lc&&s>min){
						break;
					}
					if(s<min)
						min=s;
				}
				if(k==0)
					break;
				move++;
			}
			
			while(true){
				long ta=la-move;
				if(ta<=0)
					break;
				long k=0;
				for(k=0;;++k){
					long tb=k+lb;
					long mm=ta*tb;
					long s=move+k+Math.abs(mm-lc);
					if(mm>lc&&s>min){
						break;
					}
					if(s<min)
						min=s;
				}
				if(k==0)
					break;
				for(k=0;;++k){
					long tb=lb-k;
					if(tb<=0)
						break;
					long mm=ta*tb;
					long s=move+k+Math.abs(mm-lc);
					if(mm<lc&&s>min){
						break;
					}
					if(s<min){
						
						System.out.println(ta+" "+tb+" "+mm);
						min=s;
					}
				}
				if(k==0)
					break;
				move++;
			}
			return min;
		}
		
	}
	
}
