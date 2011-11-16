/**
 * 
 */
package act.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import tm.generalmodel.Word;


import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;

/**
 * @author amy
 * 
 */
public class ACTModel implements Serializable {

	public double al, be, mu; // hyperparameters

	// Estimated/Inferenced parameters
	public int A; // number of authors
	public int W; // vocabulary size
	public int T; // number of topics
	public int C; // number of conferences

	public double[][] theta; // theta: Probabilities of topics given authors
	// size T x A
	public double[][] phi; // phi: Probabilities of words given topics, size W
	// x T
	public double[][] psi; // eta: Probabilities of topics given conference,
	// size C x T

	public int[] z;// topic assignments
	public int[] x;// author assignments
	// public int[] y; // author assignments

	// Temp variables while sampling
	public int[][] CTA;// Number of words assigned
	public int[][] CWT;// Number of words assigned
	public int[][] CCT;// Number of words assigned

	public int[] nzsum; // total number of words assigned to topic, size T
	public int[] nxsum; // total number of words assigned to author, size A
	public int[] nzdsum;

	public ACTMDataSet dataSet;

	public ACTModel() {
		// TODO Auto-generated constructor stub
	}

	public ACTModel(ACTMDataSet data, int wordCount, int authorCount,
			int confCount, int topicCount) {

		this.dataSet = data;

		this.T = topicCount;
		this.W = wordCount;
		this.A = authorCount;
		this.C = confCount;
		this.SetDefaultParas();
	}

	public void SetDefaultParas() {
		this.al = 50.0 / T;
		this.be = 0.01;
		this.mu = 0.1;
	}

	public void InitNewModel() {

		this.z = new int[dataSet.N];
		this.x = new int[dataSet.N];
		// this.y = new int[dataSet.N];

		this.nxsum = new int[A];
		this.nzsum = new int[T];
		this.nzdsum = new int[T];

		this.CWT = new int[W][T];
		this.CTA = new int[T][A];
		this.CCT = new int[C][T];

		this.phi = new double[W][T];
		this.theta = new double[T][A];
		this.psi = new double[C][T];

		for (int i = 0; i < W; ++i) {
			for (int j = 0; j < T; ++j) {
				this.CWT[i][j] = 0;
			}

		}
		for (int i = 0; i < C; ++i) {
			for (int j = 0; j < T; ++j) {
				this.CCT[i][j] = 0;
			}
		}
		for (int i = 0; i < T; ++i) {
			for (int j = 0; j < A; ++j) {
				this.CTA[i][j] = 0;
			}
		}

		for (int i = 0; i < A; ++i) {
			nxsum[i] = 0;
		}
		for (int i = 0; i < T; ++i) {
			nzsum[i] = 0;
			nzdsum[i] = 0;
		}

		for (int i = 0; i < dataSet.N; ++i) {
			int curWord = dataSet.GlobalIndexWordMap.get(i).Index;
			ACTMDocument curDoc = dataSet.GlobalIndexDocMap.get(i);
			Word curWordIns=dataSet.GlobalIndexWordMap.get(i);
			int randomTopic = (int) Math.floor(Math.random() * T);
			int randomConf = curDoc.getConference().getGlobalIndex();
			int randomAuthorIndex = (int) Math.floor(Math.random()
					* curDoc.getAuthors().size());
			int randomAuthor = curDoc.getAuthors().get(randomAuthorIndex)
					.getIndex();
			
			x[i] = randomAuthor;
			z[i] = randomTopic;

			this.CCT[randomConf][randomTopic]+=curWordIns.getSize();
			this.CTA[randomTopic][randomAuthor]+=curWordIns.getSize();
			this.CWT[curWord][randomTopic]+=curWordIns.getSize();
			this.nxsum[randomAuthor]+=curWordIns.getSize();
			this.nzsum[randomTopic]+=curWordIns.getSize();
			this.nzdsum[randomTopic]+=curWordIns.getSize();
		}
	}

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
		oos.close();
	}

	public static ACTModel LoadWholeModel(String filepath)
			throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				filepath));
		ACTModel model = new ACTModel();
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
		ois.close();
		return model;
	}

	public String SaveFinalModel(String modelSavedDir) {
		String modleFile = modelSavedDir + System.currentTimeMillis()
				+ ".model";

		try {
			this.SaveWholeModel(modleFile);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return modleFile;
	}
}
