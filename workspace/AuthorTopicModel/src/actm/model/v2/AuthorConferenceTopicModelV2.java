/**
 * 
 */
package actm.model.v2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

import utils.FileUtils;

import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;

/**
 * @author wanghan
 * 
 */
public class AuthorConferenceTopicModelV2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3519814356945091478L;

	public double al, be, ga; // hyperparameters

	// Estimated/Inferenced parameters
	public int A; // number of authors
	public int W; // vocabulary size
	public int T; // number of topics
	public int C; // number of conferences

	public double[][] theta; // theta: Probabilities of topics given authors
								// size T x C
	public double[][] phi; // phi: Probabilities of words given topics, size W
							// x T
	public double[][] eta; // eta: Probabilities of topics given conference,
							// size C x A

	public int[] z;// topic assignments
	public int[] x;// author assignments
	// public int[] y; // conference assignments

	// Temp variables while sampling
	public int[][] CCA;// Number of words assigned to author and topic
	public int[][] CWT;// Number of words assigned to topic and word.
	public int[][] CTC;// Number of words assigned to conference and topic

	public int[] nzsum; // total number of words assigned to topic, size T
	public int[] nxsum; // total number of words assigned to author, size A
	public int[] nysum; // total number of words assigned to conference, size C

	public ACTMDataSet dataSet;
	
	public AuthorConferenceTopicModelV2() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public AuthorConferenceTopicModelV2(ACTMDataSet data, int wordCount, int authorCount, int confCount, int topicCount) {
		this.SetDefaultParas();
		
		this.dataSet=data;
		
		this.T = topicCount;

		this.W = wordCount;
		this.A = authorCount;
		this.C = confCount;

		
	}

	public void SetDefaultParas() {
		this.T = 100;
		this.al = 50.0 / T;
		this.be = 0.01;
		this.ga = 0.1;
	}

	public void InitNewModel() {

		
		this.z = new int[dataSet.N];
		this.x = new int[dataSet.N];

		this.nxsum = new int[A];
		this.nzsum = new int[T];
		this.nysum = new int[C];

		this.CWT = new int[W][T];
		this.CCA = new int[C][A];
		this.CTC = new int[T][C];

		this.phi = new double[W][T];
		this.theta = new double[T][C];
		this.eta = new double[C][A];
		
		for (int i = 0; i < W; ++i) {
			for (int j = 0; j < T; ++j) {
				this.CWT[i][j] = 0;
			}
			
		}
		for (int i = 0; i < C; ++i) {
			for (int j = 0; j < A; ++j) {
				this.CCA[i][j] = 0;
			}
			this.nzsum[i]=0;
		}
		for (int i = 0; i < T; ++i) {
			for (int j = 0; j < C; ++j) {
				this.CTC[i][j] = 0;
			}	
			
		}
		for (int j = 0; j < C; ++j) {
			this.nysum[j]=0;
		}
		for (int j = 0; j < A; ++j) {
			this.nxsum[j]=0;
		}

		for (int i = 0; i < dataSet.N; ++i) {
			int curWord = dataSet.GlobalIndexWordMap.get(i).Index;
			ACTMDocument curDoc = dataSet.GlobalIndexDocMap.get(i);

			int randomTopic = (int) Math.floor(Math.random() * T);
			int randomConf = curDoc.getConference().getGlobalIndex();
			int randomAuthor = (int) Math.floor(Math.random()
					* curDoc.getAuthors().size());
			x[i] = curDoc.getAuthors().get(randomAuthor).getIndex();

			CCA[randomConf][x[i]]++;
			this.nxsum[x[i]]++;

			z[i] = randomTopic;
			CWT[curWord][randomTopic]++;
			this.nzsum[randomTopic]++;

			CTC[randomTopic][randomConf]++;
			this.nysum[randomConf]++;
		}
	}

	public void SaveTempModel(String rootPath) {
		try {
			SavePhi(rootPath + System.currentTimeMillis() + ".phi");
			SaveTheta(rootPath + System.currentTimeMillis() + ".theta");
			SaveEta(rootPath + System.currentTimeMillis() + ".eta");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void LoadTempModel(String rootpath){
		try {
			File root=new File(rootpath);
			for (File file : root.listFiles()) {
				String suffix=FileUtils.getFileSuffix(file);
				if(suffix.equals("phi")){
					this.CWT=LoadPhi(file.getAbsolutePath());
				}
				if(suffix.equals("eta")){
					this.CCA=LoadTheta(file.getAbsolutePath());
				}
				if(suffix.equals("theta")){
					this.CTC=LoadEta(file.getAbsolutePath());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	private void SaveTheta(String filepath) throws IOException {
		FileWriter writer = new FileWriter(filepath, false);
		for (int i = 0; i < C; ++i) {
			for (int j = 0; j < T; ++j) {
				writer.write(String.valueOf(this.CTC[j][i]));
				writer.write("\t");
				writer.flush();
			}
			writer.write("\n");
		}
		writer.flush();
		writer.close();
	}
	private int[][] LoadTheta(String filepath) throws FileNotFoundException{
		Scanner reader=new Scanner(new File(filepath));
		int[][] result=new int[T][C];
		for (int i = 0; i < C; ++i) {
			for (int j = 0; j < T; ++j) {
				result[j][i]=reader.nextInt();
			}
		
		}
		return result;
	}
	private void SavePhi(String filepath) throws IOException {
		FileWriter writer = new FileWriter(filepath, false);
		for (int i = 0; i < W; ++i) {
			for (int j = 0; j < T; ++j) {
				writer.write(String.valueOf(this.CWT[i][j]));
				writer.write("\t");
				writer.flush();
			}
			writer.write("\n");
		}
		writer.flush();
		writer.close();
	}
	private int[][] LoadPhi(String filepath) throws FileNotFoundException{
		Scanner reader=new Scanner(new File(filepath));
		int[][] result=new int[W][T];
		for (int i = 0; i < W; ++i) {
			for (int j = 0; j < T; ++j) {
				result[i][j]=reader.nextInt();
			}
		
		}
		return result;
	}
	private void SaveEta(String filepath) throws IOException {
		FileWriter writer = new FileWriter(filepath, false);
		for (int i = 0; i < A; ++i) {
			for (int j = 0; j < C; ++j) {
				writer.write(String.valueOf(this.CCA[j][i]));
				writer.write("\t");
				writer.flush();
			}
			writer.write("\n");
		}
		writer.flush();
		writer.close();
	}
	private int[][] LoadEta(String filepath) throws FileNotFoundException{
		Scanner reader=new Scanner(new File(filepath));
		int[][] result=new int[C][A];
		for (int i = 0; i < A; ++i) {
			for (int j = 0; j < C; ++j) {
				result[j][i]=reader.nextInt();
			}
		
		}
		return result;
	}
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
//		oos.writeObject(this.CCA);
//		oos.flush();
//		oos.writeObject(this.CWT);
//		oos.flush();
//		oos.writeObject(this.CTC);
//		oos.flush();
		oos.writeObject(this.nxsum);
		oos.flush();
		oos.writeObject(this.nzsum);
		oos.flush();
		oos.writeObject(this.nysum);
		oos.flush();
		oos.close();
	}

	public static AuthorConferenceTopicModelV2 LoadWholeModel(String filepath)
			throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				filepath));
		AuthorConferenceTopicModelV2 model = new AuthorConferenceTopicModelV2();
		model.al = (Double) (ois.readObject());
		model.be = (Double) (ois.readObject());
		model.ga=(Double) (ois.readObject());
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
//		model.CCA = (int[][]) (ois.readObject());
//		model.CWT = (int[][]) (ois.readObject());
//		model.CTC = (int[][]) (ois.readObject());
		model.nxsum = (int[]) (ois.readObject());
		model.nzsum = (int[]) (ois.readObject());
		model.nysum = (int[]) (ois.readObject());
		ois.close();
		return model;
	}
	public void SaveFinalModel(String modelSavedDir){
		String modleFile=modelSavedDir+System.currentTimeMillis()+".model";

		try {
			this.SaveWholeModel(modleFile);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
