/**
 * 
 */
package atm.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import actm.data.ACTMDataSet;
import actm.data.ACTMDocument;


/**
 * @author wanghan
 *
 */
public class AuthorTopicModel implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3291893353583485956L;


	public double alpha, beta; //hyperparameters

	
	// Estimated/Inferenced parameters
	public int A; //number of authors
	public int W; //vocabulary size
	public int T; //number o  f topics
	
	public double [][] theta; //theta: Probabilities of topics given authors size T x A
	public double [][] phi; // phi: Probabilities of words given topics, size W x T
	
	public int[] z;// topic assignments
	public int[] x;// author assignments
	
	// Temp variables while sampling
	public int[][] CTA;// Number of words assigned to author and topic
	public int[][] CWT;//Number of words assigned to topic and word.

	public int [] nzsum; //nwsum[j]: total number of words assigned to topic, size T
	public int [] nxsum; //ndsum[i]: total number of words assigned to author, size A
	
	
	public ACTMDataSet DataSet;
	
	public AuthorTopicModel() {
		// TODO Auto-generated constructor stub
	}

	public AuthorTopicModel(ACTMDataSet data,int topicCount,int wordCount,int authorCount) {
		
		this.SetDefaultParas();
		this.T=topicCount;
		
		this.DataSet=data;
		this.W=wordCount;
		this.A=authorCount;
		
		
		
		this.z=new int[data.N];
		this.x=new int[data.N];
		this.nxsum=new int[A];
		this.nzsum=new int[T];
		
		this.CWT=new int[W][T];
		this.CTA=new int[T][A];
		
		this.phi=new double[W][T];
		this.theta=new double[T][A];
	}
		
	
	public void SaveModel(String rootPath){
		try {
			SavePhi(rootPath+System.currentTimeMillis()+".phi");
			SaveTheta(rootPath+System.currentTimeMillis()+".theta");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SaveWholeModel(String filepath) throws FileNotFoundException, IOException{
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filepath,false));
		oos.writeObject(this.alpha);
		oos.writeObject(this.beta);
		oos.writeObject(this.W);
		oos.writeObject(this.T);
		oos.writeObject(this.A);
		oos.flush();
		oos.writeObject(this.phi);
		oos.flush();
		oos.writeObject(this.theta);
		oos.flush();
		oos.writeObject(this.z);
		oos.flush();
		oos.writeObject(this.x);
		oos.flush();
//		oos.writeObject(this.DataSet);
//		oos.flush();
		oos.writeObject(this.CTA);
		oos.flush();
		oos.writeObject(this.CWT);
		oos.flush();
		oos.writeObject(this.nxsum);
		oos.flush();
		oos.writeObject(this.nzsum);
		oos.flush();
		oos.close();
	}
	
	public static AuthorTopicModel LoadWholeModel(String filepath) throws IOException, ClassNotFoundException{
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(filepath));
		AuthorTopicModel model=new AuthorTopicModel();
		model.alpha=(Double)(ois.readObject());
		model.beta=(Double)(ois.readObject());
		model.W=(Integer)(ois.readObject());
		model.T=(Integer)(ois.readObject());
		model.A=(Integer)(ois.readObject());
		model.phi=(double[][])(ois.readObject());
		model.theta=(double[][])(ois.readObject());
		model.z=(int[])(ois.readObject());
		model.x=(int[])(ois.readObject());
	//	model.DataSet=(ACTMDataSet)(ois.readObject());
		model.CTA=(int[][])(ois.readObject());
		model.CWT=(int[][])(ois.readObject());
		model.nxsum=(int[])(ois.readObject());
		model.nzsum=(int[])(ois.readObject());
		ois.close();
		return model;
	}
	
	public void SaveFinalModel(String modelSavedDir){
		String modleFile=modelSavedDir+System.currentTimeMillis()+".model";
		String showFile=modelSavedDir+System.currentTimeMillis()+".show";
		try {
			this.SaveWholeModel(modleFile);
			
//			ATMVisualizer vv=new ATMVisualizer(this);  
//			File fileout =new File(showFile);
//			FileWriter writer=new FileWriter(fileout);
//			writer.write(vv.TopKAuthorsPerTopic(10));
//			writer.write(vv.TopKWordsPerTopic(10));
//			writer.write(vv.TopKTopicPerAuthor(10));
//			writer.flush();
//			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	private void SaveTheta(String filepath) throws IOException{
		FileWriter writer=new FileWriter(filepath,false);
		for(int i=0;i<T;++i){
			for(int j=0;j<A;++j){
				writer.write(String.valueOf(this.CTA[i][j]));
				writer.write("\t");
				writer.flush();
			}
			writer.write("\n");
		}
		writer.flush();
		writer.close();
	}
	
	private void SavePhi(String filepath) throws IOException{
		FileWriter writer=new FileWriter(filepath,false);
		for(int i=0;i<W;++i){
			for(int j=0;j<T;++j){
				writer.write(String.valueOf(this.CWT[i][j]));
				writer.write("\t");
				writer.flush();
			}
			writer.write("\n");
		}
		writer.flush();
		writer.close();
	}
	public void InitNewModel(){
		
		for(int i=0;i<W;++i){
			for(int j=0;j<T;++j){
				this.CWT[i][j]=0;
			}
		}
		for(int i=0;i<T;++i){
			for(int j=0;j<A;++j){
				this.CTA[i][j]=0;
			}
		}
		
//		int globalWordIndex=0;
//		for(int i=0;i<DataSet.documentSet.size();++i){
//			Document doc=DataSet.documentSet.get(i);
//
//			for (Word word : doc.BagOfWord) {
//				int curWord=word.Index;
//				int count=1;
//				
//				int randomTopic=(int)Math.floor(Math.random() * T);
//				
//				int randomAuthor=(int)Math.floor(Math.random() * doc.Authors.size());
//				x[globalWordIndex]=doc.Authors.get(randomAuthor).Index;
//				CTA[randomTopic][doc.Authors.get(randomAuthor).Index]+=count;
//				this.nxsum[doc.Authors.get(randomAuthor).Index]+=count;
//				
//				z[globalWordIndex]=randomTopic;
//				CWT[curWord][randomTopic]+=count;
//				this.nzsum[randomTopic]+=count;
//				globalWordIndex++;
//			}
//		}
			
		
		for(int i=0;i<DataSet.N;++i){
			int curWord=DataSet.GlobalIndexWordMap.get(i).Index;
			ACTMDocument curDoc=DataSet.GlobalIndexDocMap.get(i);
			
			int randomTopic=(int)Math.floor(Math.random() * T);
			
			int randomAuthor=(int)Math.floor(Math.random() * curDoc.getAuthors().size());
			x[i]=curDoc.getAuthors().get(randomAuthor).getIndex();
			CTA[randomTopic][curDoc.getAuthors().get(randomAuthor).getIndex()]++;
			this.nxsum[curDoc.getAuthors().get(randomAuthor).getIndex()]++;
			
			z[i]=randomTopic;
			CWT[curWord][randomTopic]++;
			this.nzsum[randomTopic]++;
		}
		
//		for(int i=0;i<W;++i){
//			
//		}
		
		theta = new double[T][A];		
		phi = new double[W][T];
	}
	
	
	public void SetDefaultParas(){
		this.T=100;
		this.alpha=50.0/T;
		this.beta=0.01;
	}
}
