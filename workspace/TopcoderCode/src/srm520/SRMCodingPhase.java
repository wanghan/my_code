package srm520;


public class SRMCodingPhase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]={300, 600, 900};
		int b[]={30, 65, 90};
		int c=25;
		System.out.println(new SRMCodingPhase().countScore(a, b, c));
	}
	public int countScore(int[] points, int[] skills, int luck){
		int max=0;
		
		for(int i=7;i>=1;--i){
			int teml=luck;
			int time=75;
			int score=0;
			int a=i/4;
			int b=i/2%2;
			int c=i%2;
	//		System.out.println(a+" "+b+" "+c);
			if(a==1){
				if((skills[2]-teml)<=time){
					if(skills[2]>teml){
						score+=points[2]-8*(skills[2]-teml);
						
						time-=(skills[2]-teml);
						teml=0;
					}
					else{
						score+=points[2]-8;
						teml-=(skills[2]-1);
						time-=1;
					}
				}
			}
			if(b==1){
				if((skills[1]-teml)<=time){
					if(skills[1]>teml){
						score+=points[1]-4*(skills[1]-teml);
						
						time-=(skills[1]-teml);
						teml=0;
					}
					else{
						score+=points[1]-4;
						teml-=(skills[1]-1);
						time-=1;
					}
				}
			}
			if(c==1){
				if((skills[0]-teml)<=time){
					if(skills[0]>teml){
						score+=points[0]-2*(skills[0]-teml);
						
						time-=(skills[0]-teml);
						teml=0;
					}
					else{
						score+=points[0]-2;
						teml-=(skills[0]-1);
						time-=1;
					}
				}
			}
	//		System.out.println(score);
			max=Math.max(max, score);
		}
		
		
		return max;
		
	}
}
