/**
 * 
 */
package adm.model;

import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;

/**
 * @author amy
 *
 */
public class ADMModel {

	public double al, be, ga,mu; // hyperparameters

	// Estimated/Inferenced parameters
	public int A; // number of authors
	public int W; // vocabulary size
	public int T; // number of topics
	public int C; // number of conferences
	public int D; // number of document class

	public double[][] theta; // theta: Probabilities of topics given authors
								// size T x D
	public double[][] phi; // phi: Probabilities of words given topics, size W
							// x T
	public double[][] psi; // eta: Probabilities of topics given conference,
							// size D x A
	
	public double[][] lambada; //size C*D
	
	public int[] z;// topic assignments
	public int[] x;// author class assignments
	public int[] y; // author assignments 
	
	
	// Temp variables while sampling
	public int[][] CTD;// Number of words assigned
	public int[][] CWT;// Number of words assigned 
	public int[][] CCD;// Number of words assigned 
	public int[][] CDA;// Number of words assigned

	public int[] nzsum; // total number of words assigned to topic, size T
	public int[] nxsum; // total number of words assigned to author, size A
	public int[] nysum; // total number of words assigned to conference, size C

	public ACTMDataSet dataSet;
	
	public ADMModel() {
		// TODO Auto-generated constructor stub
	}
	
	public ADMModel(ACTMDataSet data, int wordCount, int authorCount, int confCount, int topicCount,int authorClassCount){
	this.SetDefaultParas();
		
		this.dataSet=data;
		
		this.T = topicCount;

		this.W = wordCount;
		this.A = authorCount;
		this.C = confCount;
		this.D = authorClassCount;
	}
	public void SetDefaultParas() {
		this.al = 50.0 / T;
		this.be = 0.01;
		this.ga = 50.0 / T;
		this.mu=50.0 / T;;
	}
	
public void InitNewModel() {

		
		this.z = new int[dataSet.N];
		this.x = new int[dataSet.N];
		this.y = new int[dataSet.N];

		this.nxsum = new int[D];
		this.nzsum = new int[T];
		this.nysum = new int[C];

		this.CWT = new int[W][T];
		this.CCD = new int[C][D];
		this.CDA = new int[D][A];
		this.CTD = new int[T][D];

		this.phi = new double[W][T];
		this.theta = new double[T][D];
		this.psi=new double[D][A];
		this.lambada = new double[C][D];
		
		for (int i = 0; i < W; ++i) {
			for (int j = 0; j < T; ++j) {
				this.CWT[i][j] = 0;
			}
			
		}
		for (int i = 0; i < C; ++i) {
			for (int j = 0; j < D; ++j) {
				this.CCD[i][j] = 0;
			}
			this.nysum[i]=0;
		}
		for (int i = 0; i < D; ++i) {
			for (int j = 0; j < A; ++j) {
				this.CDA[i][j] = 0;
			}	
			this.nxsum[i]=0;
		}
		for (int i = 0; i < T; ++i) {
			for (int j = 0; j < D; ++j) {
				this.CTD[i][j] = 0;
			}	
			this.nzsum[i]=0;
		}


		for (int i = 0; i < dataSet.N; ++i) {
			int curWord = dataSet.GlobalIndexWordMap.get(i).Index;
			ACTMDocument curDoc = dataSet.GlobalIndexDocMap.get(i);

			int randomTopic = (int) Math.floor(Math.random() * T);
			int randomConf = curDoc.getConference().getGlobalIndex();
			int randomAuthorIndex = (int) Math.floor(Math.random()
					* curDoc.getAuthors().size());
			int randomAuthorClass=(int) Math.floor(Math.random() * D);
			int randomAuthor=curDoc.getAuthors().get(randomAuthorIndex).getIndex();
			x[i] = randomAuthorClass;
			y[i]=randomAuthor;
			z[i]=randomTopic;

			this.CCD[randomConf][randomAuthorClass]++;
			this.CDA[randomAuthorClass][randomAuthor]++;
			this.CTD[randomTopic][randomAuthorClass]++;
			this.CWT[curWord][randomTopic]++;
			             
			this.nxsum[randomAuthorClass]++;
			this.nzsum[randomTopic]++;
			this.nysum[randomConf]++;
		}
	}
}
