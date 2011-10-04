/**
 * 
 */
package act.online.model;

import java.io.Serializable;

/**
 * @author wanghan
 *
 */
public class WindowSlide implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2004287792651685954L;
	private int slide;
	private double [][] paras;
	
	public WindowSlide(int slide, double [][]para) {
		// TODO Auto-generated constructor stub
		this.slide=slide;
		this.paras=para;
	}
	
	public WindowSlide(int slide,  int [][] curpara, double [][] postpara) {
		// TODO Auto-generated constructor stub
		this.slide=slide;
		this.paras=new double[curpara.length][curpara[0].length];
		for(int i=0;i<curpara.length;++i){
			for(int j=0;j<curpara[0].length;++j){
				this.paras[i][j]=curpara[i][j]+postpara[i][j];
			}
		}
		
	}

	public WindowSlide(int slide,  int [][] curpara) {
		// TODO Auto-generated constructor stub
		this.slide=slide;
		this.paras=new double[curpara.length][curpara[0].length];
		for(int i=0;i<curpara.length;++i){
			for(int j=0;j<curpara[0].length;++j){
				this.paras[i][j]=curpara[i][j];
			}
		}
		
	}
	
	public int getSlide() {
		return slide;
	}

	public double[][] getParas() {
		return paras;
	}

	
	
}
