/**
 * 
 */
package atm.labeling;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.Vector;

import atm.model.AuthorTopicModel;


/**
 * @author wanghan
 *
 */
public class TopicLabelingKL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AuthorTopicModel model=null;
		try {
			model = AuthorTopicModel.LoadWholeModel("Models/1299430569746_I2000_T100/1299467420745.model");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TopicLabelingKL labeling=new TopicLabelingKL(model);
		
		TreeMap<Integer,ArrayList<LabelScoreMap>> labels=labeling.labeling();
		
		for (Integer k : labels.keySet()) {
			ArrayList<LabelScoreMap> labelScoreMap=labels.get(k);
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<4;++i){
				sb.append(labelScoreMap.get(i).getParse().toString());
				sb.append(" ## ");
			}
			System.out.println(k+" : " +sb.toString());
		}
		
		
	}

	private AuthorTopicModel atmModel;
	private Vector<Parse> candidateLabels;
	
	public TopicLabelingKL(AuthorTopicModel model) {
		// TODO Auto-generated constructor stub
		candidateLabels=new Vector<Parse>();
		this.atmModel=model;
		
		loadCandidateLabels();
	}
	
	
	public TreeMap<Integer,ArrayList<LabelScoreMap>> labeling(){
		
		TreeMap<Integer,ArrayList<LabelScoreMap>> result=new TreeMap<Integer, ArrayList<LabelScoreMap>>();
		
		for(int k=0;k<atmModel.T;++k){
			double[] p=new double[atmModel.W];
			for(int i=0;i<atmModel.W;++i){
				p[i]=atmModel.phi[i][k];
			}
			
			ArrayList<LabelScoreMap> labelScoreMap=new ArrayList<LabelScoreMap>();
			
//			for (Parse parse : candidateLabels) {
//				double q[]=new double[atmModel.W];
//				boolean hasNoIllegalWord=true;
//				for (String token : parse.tokens) {
//					Word w=atmModel.DataSet.wordSet.wordVocabularyMap.get(token);
//					if(w==null){
//						hasNoIllegalWord=false;
//						break;
//					}
//					else{
//						q[w.Index]=1.0/parse.tokens.size();
//					}
//				}
//				if(hasNoIllegalWord){
//					double score=KLDivergence.CalKL(q,p);
//					labelScoreMap.add(new LabelScoreMap(parse,score));
//				}
//				
//			}
			
			Collections.sort(labelScoreMap,new Comparator<LabelScoreMap>(){
				@Override
				public int compare(LabelScoreMap o1, LabelScoreMap o2) {
					// TODO Auto-generated method stub
					if(o1.getScore()>o2.getScore()){
						return 1;
					}
					else if(o1.getScore()==o2.getScore()){
						return 0;
					}
					else{
						return -1;
					}
				}
			});
			
			result.put(k, labelScoreMap);
		}
		return result;
	}
	
	private void loadCandidateLabels(){
		
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream("chunkLabels.txt")));
			while(true){
				String line=reader.readLine();
				if(line==null){
					break;
				}
				Parse parse=new Parse(line);
				if(parse.tokens.size()>1){
					this.candidateLabels.add(parse);
				}
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
