/**
 * 
 */
package act.online.model;

import java.io.Serializable;
import java.util.LinkedList;


/**
 * @author wanghan
 *
 */
public class ParameterWindow implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9025035494868289925L;
	LinkedList<WindowSlide> slides;
//	HashMap<Integer, WindowSlide> slideIndexMap;
	int size;
	
	public ParameterWindow(int size) {
		// TODO Auto-generated constructor stub
		this.slides=new LinkedList<WindowSlide>();
		this.size=size;
	}
	
	public void addSlide(int slide, double [][] para){
		WindowSlide s=new WindowSlide(slide,para);
		slides.addLast(s);
		while(slides.size()>size){
			slides.pollFirst();
		}
	}
	
	public void addSlide(int slide, int [][] para){
		WindowSlide s=new WindowSlide(slide,para);
		slides.addLast(s);
		while(slides.size()>size){
			slides.pollFirst();
		}
	}
	
	public void addSlide(int slide, int [][] curpara, double [][] postpara){
		WindowSlide s=new WindowSlide(slide,curpara,postpara);
		slides.addLast(s);
		while(slides.size()>size){
			slides.pollFirst();
		}
	}

	public int getCurrentSize(){
		return slides.size();
	}
	
	public double[][] getParaWithWeight(double [] w){
		if(slides.size()==0){
			return null;
		}
		else{
			int x=slides.get(0).getParas().length;
			int y=slides.get(0).getParas()[0].length;
			double [][] result=new double[x][y];
			for(int i=0;i<x;++i){
				for(int j=0;j<y;++j){
					for(int k=0;k<w.length;++k){
						result[i][j]+=slides.get(k).getParas()[i][j]*w[k];
					}
				}
			}
			return result;
		}
		
	}
}
