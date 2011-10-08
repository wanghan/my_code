/**
 * 
 */
package actm.online.model.v2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import actm.data.ACTMDataSet;
import actm.model.v2.AuthorConferenceTopicModelV2;
import actm.online.model.ParameterWindow;

/**
 * @author wanghan
 * 
 */
public class AuthorConferenceTopicOnlineModelV2 extends
	AuthorConferenceTopicModelV2 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6800156605859964115L;

	public int delta; // size of slide window
	public int currentSlide;
	
	
	public double[][] beta; // window of para WT
	public double[][] alpha; // window of para TC
	public double[][] gamma; // window of para 

	public double[] windowWeight;
	ParameterWindow betaWindow;
	ParameterWindow alphaWindow;
	ParameterWindow gammaWindow;
	

	public AuthorConferenceTopicOnlineModelV2() {
		// TODO Auto-generated constructor stub
		super();
	}

	public AuthorConferenceTopicOnlineModelV2(ACTMDataSet data, int wordCount,
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
	
		if(curslide!=0){
			betaWindow.addSlide(currentSlide, CWT);
			alphaWindow.addSlide(currentSlide, CTC,alpha);
			gammaWindow.addSlide(currentSlide, CCA,gamma);
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
			alpha=new double[T][C];
			gamma=new double[C][A];
			
			for(int j=0;j<T;++j){
				for(int i=0;i<W;++i){
					beta[i][j]=be;
				}
				for(int i=0;i<C;++i){
					alpha[j][i]=al;
				}
			}
			for(int j=0;j<A;++j){
				for(int i=0;i<C;++i){
					gamma[i][j]=ga;
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
			alpha=new double[T][C];
			gamma=new double[C][A];
			
			for(int j=0;j<T;++j){
				for(int i=0;i<W;++i){
					beta[i][j]=be;
				}
				for(int i=0;i<C;++i){
					alpha[j][i]=al;
				}
			}
			for(int j=0;j<A;++j){
				for(int i=0;i<C;++i){
					gamma[i][j]=ga;
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
		oos.writeObject(this.CCA);
		oos.flush();
		oos.writeObject(this.CWT);
		oos.flush();
		oos.writeObject(this.CTC);
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

	public static AuthorConferenceTopicOnlineModelV2 LoadWholeModel(
			String filepath) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				filepath));
		AuthorConferenceTopicOnlineModelV2 model = new AuthorConferenceTopicOnlineModelV2();
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
		model.CCA = (int[][]) (ois.readObject());
		model.CWT = (int[][]) (ois.readObject());
		model.CTC = (int[][]) (ois.readObject());
		model.nxsum = (int[]) (ois.readObject());
		model.nzsum = (int[]) (ois.readObject());
		model.nysum = (int[]) (ois.readObject());
		model.betaWindow = (ParameterWindow) (ois.readObject());
		model.delta = (Integer) (ois.readObject());
		ois.close();
		return model;
	}
	public void SavePriorParas(String rootPath) {
		try {
			SaveAlpha(rootPath + System.currentTimeMillis() + ".alpha");
			SaveBeta(rootPath + System.currentTimeMillis() + ".beta");
			SaveGamma(rootPath + System.currentTimeMillis() + ".gamma");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void SaveAlpha(String filepath) throws IOException {
		FileWriter writer = new FileWriter(filepath, false);
		for (int i = 0; i < T; ++i) {
			for (int j = 0; j < C; ++j) {
				writer.write(String.valueOf(this.alpha[i][j]));
				writer.write("\t");
				writer.flush();
			}
			writer.write("\n");
		}
		writer.flush();
		writer.close();
	}
	private double[][] LoadAlpha(String filepath) throws FileNotFoundException{
		Scanner reader=new Scanner(new File(filepath));
		double[][] result=new double[T][C];
		for (int i = 0; i < T; ++i) {
			for (int j = 0; j < C; ++j) {
				result[i][j]=reader.nextDouble();
			}
		
		}
		return result;
	}
	private void SaveBeta(String filepath) throws IOException {
		FileWriter writer = new FileWriter(filepath, false);
		for (int i = 0; i < W; ++i) {
			for (int j = 0; j < T; ++j) {
				writer.write(String.valueOf(this.beta[i][j]));
				writer.write("\t");
				writer.flush();
			}
			writer.write("\n");
		}
		writer.flush();
		writer.close();
	}
	private double[][] LoadBeta(String filepath) throws FileNotFoundException{
		Scanner reader=new Scanner(new File(filepath));
		double[][] result=new double[W][T];
		for (int i = 0; i < W; ++i) {
			for (int j = 0; j < T; ++j) {
				result[i][j]=reader.nextDouble();
			}
		
		}
		return result;
	}
	private void SaveGamma(String filepath) throws IOException {
		FileWriter writer = new FileWriter(filepath, false);
		for (int i = 0; i < A; ++i) {
			for (int j = 0; j < C; ++j) {
				writer.write(String.valueOf(this.gamma[j][i]));
				writer.write("\t");
				writer.flush();
			}
			writer.write("\n");
		}
		writer.flush();
		writer.close();
	}
	private double[][] LoadGamma(String filepath) throws FileNotFoundException{
		Scanner reader=new Scanner(new File(filepath));
		double[][] result=new double[C][A];
		for (int i = 0; i < A; ++i) {
			for (int j = 0; j < C; ++j) {
				result[j][i]=reader.nextDouble();
			}
		
		}
		return result;
	}
	
}
