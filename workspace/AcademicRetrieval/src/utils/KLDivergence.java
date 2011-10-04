/**
 * 
 */
package utils;

/**
 * @author wanghan
 *
 */
public class KLDivergence {
	
	private static double infinity=10000000;
	
	public static double calKL(double []p,double [] q)
	{  
		double kl=0;
	    
	    for(int i=0;i<p.length;++i){
	    	if(p[i]==0){
	    		continue;
	    	}
	    	else{
	    		kl+=p[i]*(myLog(p[i])-myLog(q[i]));
	    	}
	    	
	    }
	    return kl/2;
	    
	}
	
	public static double calKLByColumn(double[][] matrix, int x, int y){
		
		int dim=matrix.length;
		double kl=0;
		for(int i=0;i<dim;++i){
			if(matrix[i][x]==0){
	    		continue;
	    	}
	    	else{
	    		kl+=matrix[i][x]*(myLog(matrix[i][x])-myLog(matrix[i][y]));
	    	}
		}
		
		return kl;
	}
	
	private static double myLog(double s){
		if(s==0){
			return infinity;
		}
		else{
			return Math.log(s);
		}
	}
}
