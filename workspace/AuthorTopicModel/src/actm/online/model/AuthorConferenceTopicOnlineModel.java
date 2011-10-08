/**
 * 
 */
package actm.online.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import actm.data.ACTMDataSet;
import actm.model.AuthorConferenceTopicModel;

/**
 * @author wanghan
 * 
 */
public class AuthorConferenceTopicOnlineModel extends
		AuthorConferenceTopicModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6800156605859964115L;

	public int delta; // size of slide window
	public int currentSlide;
	
	
	public double[][] beta; // window of para WT
	public double[][] alpha; // window of para TA
	public double[][] gamma; // window of para AC

	public double[] windowWeight;
	ParameterWindow betaWindow;
	ParameterWindow alphaWindow;
	ParameterWindow gammaWindow;
	

	public AuthorConferenceTopicOnlineModel() {
		// TODO Auto-generated constructor stub
		super();
	}

	public AuthorConferenceTopicOnlineModel(ACTMDataSet data, int wordCount,
			int authorCount, int confCount, int topicCount, double[]weight) {
		super(data, wordCount, authorCount, confCount, topicCount);
		this.windowWeight=weight;
		this.delta=weight.length;
		betaWindow = new ParameterWindow(delta);
		alphaWindow=new ParameterWindow(delta);
		gammaWindow=new ParameterWindow(delta);
		this.currentSlide=0;
	}

	
	public void updateModelForNewSlide(int curslide,ACTMDataSet data, int wordCount,
			int authorCount, int confCount){
	//	beta=new double[W][T];
	
		if(curslide!=0){
			betaWindow.addSlide(currentSlide, beta);
			alphaWindow.addSlide(currentSlide, CTA ,alpha);
			gammaWindow.addSlide(currentSlide, CAC ,gamma);
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
			gamma=new double[A][C];
		
			for(int j=0;j<T;++j){
				for(int i=0;i<W;++i){
					beta[i][j]=be;
				}
				for(int i=0;i<A;++i){
					alpha[j][i]=al;
				}
			}
			for(int j=0;j<A;++j){
				for(int i=0;i<C;++i){
					gamma[j][i]=ga;
				}
			}
			for(int i=0;i<delta;++i){
				betaWindow.addSlide(currentSlide, beta);
				alphaWindow.addSlide(currentSlide, alpha);
				gammaWindow.addSlide(currentSlide, gamma);
			
			}
			
		}
		else{
			
			beta=new double[W][T];
			alpha=new double[T][A];
			gamma=new double[A][C];
		
			for(int j=0;j<T;++j){
				for(int i=0;i<W;++i){
					beta[i][j]=be;
				}
				for(int i=0;i<A;++i){
					alpha[j][i]=al;
				}
			}
			for(int j=0;j<A;++j){
				for(int i=0;i<C;++i){
					gamma[j][i]=ga;
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
			temp=gammaWindow.getParaWithWeight(windowWeight);
			x=temp.length;
			y=temp[0].length;
			for(int i=0;i<x;++i){
				for(int j=0;j<y;++j){
					gamma[i][j]+=temp[i][j];
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
		oos.writeObject(this.ga);
		oos.writeObject(this.W);
		oos.writeObject(this.T);
		oos.writeObject(this.A);
		oos.writeObject(this.C);
		oos.flush();
		oos.writeObject(this.phi);
		oos.flush();
		oos.writeObject(this.theta);
		oos.flush();
		oos.writeObject(this.eta);
		oos.flush();
		oos.writeObject(this.z);
		oos.flush();
		oos.writeObject(this.x);
		oos.flush();
		oos.writeObject(this.dataSet);
		oos.flush();
		oos.writeObject(this.CTA);
		oos.flush();
		oos.writeObject(this.CWT);
		oos.flush();
		oos.writeObject(this.CAC);
		oos.flush();
		oos.writeObject(this.nxsum);
		oos.flush();
		oos.writeObject(this.nzsum);
		oos.flush();
		oos.writeObject(this.nysum);
		oos.flush();
		oos.writeObject(this.betaWindow);
		oos.writeObject(this.delta);
		oos.flush();
		oos.close();
	}

	public static AuthorConferenceTopicOnlineModel LoadWholeModel(
			String filepath) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				filepath));
		AuthorConferenceTopicOnlineModel model = new AuthorConferenceTopicOnlineModel();
		model.al = (Double) (ois.readObject());
		model.be = (Double) (ois.readObject());
		model.ga = (Double) (ois.readObject());
		model.W = (Integer) (ois.readObject());
		model.T = (Integer) (ois.readObject());
		model.A = (Integer) (ois.readObject());
		model.C = (Integer) (ois.readObject());
		model.phi = (double[][]) (ois.readObject());
		model.theta = (double[][]) (ois.readObject());
		model.eta = (double[][]) (ois.readObject());
		model.z = (int[]) (ois.readObject());
		model.x = (int[]) (ois.readObject());
		model.dataSet = (ACTMDataSet) (ois.readObject());
		model.CTA = (int[][]) (ois.readObject());
		model.CWT = (int[][]) (ois.readObject());
		model.CAC = (int[][]) (ois.readObject());
		model.nxsum = (int[]) (ois.readObject());
		model.nzsum = (int[]) (ois.readObject());
		model.nysum = (int[]) (ois.readObject());
		model.betaWindow = (ParameterWindow) (ois.readObject());
		model.delta = (Integer) (ois.readObject());
		ois.close();
		return model;
	}

}
