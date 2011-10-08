/**
 * 
 */
package act.online.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import act.model.ACTModel;
import actm.data.ACTMDataSet;
import actm.online.model.ParameterWindow;

/**
 * @author wanghan
 *
 */
public class ACTOnlineModel extends ACTModel {

	public int delta; // size of slide window
	public int currentSlide;
	
	
	public double[][] beta; // window of para WT
	public double[][] alpha; // window of para TC
	public double[][] mumu; // window of para 

	public double[] windowWeight;
	ParameterWindow betaWindow;
	ParameterWindow alphaWindow;
	ParameterWindow mumuWindow;
	
	public ACTOnlineModel() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public ACTOnlineModel(ACTMDataSet data, int wordCount,
			int authorCount, int confCount, int topicCount, double[]weight){
		
		super(data, wordCount, authorCount, confCount, topicCount);
		this.windowWeight=weight;
		this.delta=weight.length;
		betaWindow = new ParameterWindow(delta);
		alphaWindow = new ParameterWindow(delta);
		mumuWindow = new ParameterWindow(delta);
		this.currentSlide=0;
	}
	
	public void updateModelForNewSlide(int curslide,ACTMDataSet data, int wordCount,
			int authorCount, int confCount){
	
		if(curslide!=0){
			betaWindow.addSlide(currentSlide, CWT);
			alphaWindow.addSlide(currentSlide, CTA ,alpha);
			mumuWindow.addSlide(currentSlide, CCT ,mumu);
		}
		
		
		this.W=wordCount;
		this.A=authorCount;
		this.C=confCount;
		this.dataSet=data;
		this.currentSlide=curslide;
	}
	
	public void InitWindowParas() {
		if(currentSlide==0){
			beta=new double[W][T];
			alpha=new double[T][A];
			mumu=new double[C][T];
			
			for(int j=0;j<T;++j){
				for(int i=0;i<W;++i){
					beta[i][j]=be;
				}
				for(int i=0;i<C;++i){
					mumu[i][j]=mu;
				}
			}
			for(int j=0;j<A;++j){
				for(int i=0;i<T;++i){
					alpha[i][j]=al;
				}
			}
			for(int i=0;i<delta;++i){
				betaWindow.addSlide(currentSlide, beta);
				alphaWindow.addSlide(currentSlide, alpha);
				mumuWindow.addSlide(currentSlide, mumu);
			}
			
		}
		else{
			
			beta=new double[W][T];
			alpha=new double[T][A];
			mumu=new double[C][T];
			
			for(int j=0;j<T;++j){
				for(int i=0;i<W;++i){
					beta[i][j]=be;
				}
				for(int i=0;i<C;++i){
					mumu[i][j]=mu;
				}
			}
			for(int j=0;j<A;++j){
				for(int i=0;i<T;++i){
					alpha[i][j]=al;
				}
			}
			
			double[][] temp=betaWindow.getParaWithWeight(windowWeight);
			int x=temp.length;
			int y=temp[0].length;
			for(int i=0;i<x;++i){
				for(int j=0;j<y;++j){
					beta[i][j]+=temp[i][j];
				}
			}
			
			temp=alphaWindow.getParaWithWeight(windowWeight);
			x=temp.length;
			y=temp[0].length;
			for(int i=0;i<x;++i){
				for(int j=0;j<y;++j){
					alpha[i][j]+=temp[i][j];
				}
			}
			temp=mumuWindow.getParaWithWeight(windowWeight);
			x=temp.length;
			y=temp[0].length;
			for(int i=0;i<x;++i){
				for(int j=0;j<y;++j){
					mumu[i][j]+=temp[i][j];
				}
			}
		}
	}
	@Override
	public void SaveWholeModel(String filepath) throws FileNotFoundException,
			IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				filepath, false));
		oos.writeObject(this.al);
		oos.writeObject(this.be);
		oos.writeObject(this.mu);
		oos.writeObject(this.W);
		oos.writeObject(this.T);
		oos.writeObject(this.A);
		oos.writeObject(this.C);
		oos.flush();
		oos.writeObject(this.phi);
		oos.flush();
		oos.writeObject(this.theta);
		oos.flush();
		oos.writeObject(this.psi);
		oos.flush();
		oos.writeObject(this.z);
		oos.flush();
		oos.writeObject(this.x);
		oos.flush();
		oos.writeObject(this.dataSet);
		oos.flush();
		oos.writeObject(this.CCT);
		oos.flush();
		oos.writeObject(this.CWT);
		oos.flush();
		oos.writeObject(this.CTA);
		oos.flush();
		oos.writeObject(this.nxsum);
		oos.flush();
		oos.writeObject(this.nzsum);
		oos.flush();
		
		oos.writeObject(windowWeight);
		oos.flush();
		oos.writeObject(this.alpha);
		oos.flush();
		oos.writeObject(this.beta);
		oos.flush();
		oos.writeObject(this.mumu);
		oos.flush();
		oos.close();
	}
	
	public static ACTOnlineModel LoadWholeModel(String filepath)
		throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				filepath));
		ACTOnlineModel model = new ACTOnlineModel();
		model.al = (Double) (ois.readObject());
		model.be = (Double) (ois.readObject());
		model.mu = (Double) (ois.readObject());
		model.W = (Integer) (ois.readObject());
		model.T = (Integer) (ois.readObject());
		model.A = (Integer) (ois.readObject());
		model.C = (Integer) (ois.readObject());
		model.phi = (double[][]) (ois.readObject());
		model.theta = (double[][]) (ois.readObject());
		model.psi = (double[][]) (ois.readObject());
		model.z = (int[]) (ois.readObject());
		model.x = (int[]) (ois.readObject());
		model.dataSet = (ACTMDataSet) (ois.readObject());
		model.CCT = (int[][]) (ois.readObject());
		model.CWT = (int[][]) (ois.readObject());
		model.CTA = (int[][]) (ois.readObject());
		model.nxsum = (int[]) (ois.readObject());
		model.nzsum = (int[]) (ois.readObject());
		
		model.windowWeight=(double[])(ois.readObject());
		model.alpha = (double[][]) (ois.readObject());
		model.beta = (double[][]) (ois.readObject());
		model.mumu = (double[][]) (ois.readObject());
		ois.close();
		return model;
	}
}
