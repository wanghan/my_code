package srm513;


public class YetAnotherIncredibleMachine { 

  /** 
   * @param args 
   */ 
  public static void main(String[] args) { 
    // TODO Auto-generated method stub 
    int a[]={100, -4215, 251}; 
    int b[]={400, 10000, 2121}; 
    int c[]={5000, 2270, 8512, 6122}; 
     

    System.out.println(new YetAnotherIncredibleMachine().countWays(a, b, c)); 
  } 
  public int countWays(int[] platformMount, int[] platformLength, int[] balls){ 
     

    long result=1; 
    for(int i=0;i<platformLength.length;++i){ 
      int sum=0; 
       
      for(int left=platformMount[i]-platformLength[i];left<=platformMount[i];++left){ 
        boolean conf=false; 
        int right=left+platformLength[i]; 
        for(int j=0;j<balls.length;++j){ 
          if(balls[j]<left||balls[j]>right){ 
            continue; 
          } 
          else{ 
            conf=true; 
            break; 
          } 
        } 
        if(!conf){ 
          sum++; 
        } 
      } 
       
      result*=(sum%1000000009); 
      result=result%1000000009; 
    } 
     
    return (int)result; 
     
  } 
   
}