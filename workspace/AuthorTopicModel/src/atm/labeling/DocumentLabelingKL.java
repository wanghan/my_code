/**
 * 
 */
package atm.labeling;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import atm.model.AuthorTopicModel;
import atm.model.ATMDocument;

import utils.MapSorting;


/**
 * @author wanghan
 *
 */
public class DocumentLabelingKL {

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
//		TopicLabelingKL labeling=new TopicLabelingKL(model);
//		
//		TreeMap<Integer,ArrayList<LabelScoreMap>> labels=labeling.labeling();
		DocumentLabelingKL docLabeling=new DocumentLabelingKL(model);
		
//		try {
//			FileWriter writer=new FileWriter("docLabels.txt");
//			for (ATMDocument doc : model.DataSet.documentSet) {
//				String s=docLabeling.labeling(doc);
//				writer.write(s+"\r\n");
//			}
//			writer.flush();
//			writer.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}
	
	
	private AuthorTopicModel atmModel;
//	private TreeMap<Integer,ArrayList<LabelScoreMap>> topicLabels;
	public DocumentLabelingKL(AuthorTopicModel model) {
		// TODO Auto-generated constructor stub
		this.atmModel=model;
//		this.topicLabels=topicLabels;
	}

	
	public String labeling(ATMDocument doc){
		Double weight[]=getTopicPercentage(doc);
		int [] order=new MapSorting<Double>(weight).sort(true);
		
		StringBuilder sb=new StringBuilder();
		
		sb.append(doc.Title);
		DecimalFormat format=new DecimalFormat(".##");
		for(int i=0;i<4;++i){
			sb.append("\t"+order[i]+" : "+format.format(weight[order[i]]*100)+" ");
//			StringBuilder sb=new StringBuilder();
//			for(int j=0;j<4;++j){
//				sb.append(topicLabels.get(order[i]).get(j).getParse().toString());
//				sb.append(" ## ");
//			}
//			System.out.println(sb.toString());
		}
		
		return sb.toString();
	}
	
	private Double[] getTopicPercentage(ATMDocument doc){
		Integer [] assign=getTopicAssignment(doc);
		Double weight[]=new Double[atmModel.T];
		for(int i=0;i<atmModel.T;++i){
			weight[i]=new Double(0);
		}
		double sum=0;
		for (Integer index : assign) {
			
			weight[atmModel.z[index]]++;
			sum++;
		}
		
		//normalize
		for(int i=0;i<atmModel.T;++i){
			weight[i]/=sum;
		}
		return weight;
	}
	
	private Integer [] getTopicAssignment(ATMDocument doc){
		ArrayList<Integer> assign=new ArrayList<Integer>();
//		for (Integer integer : atmModel.DataSet.GlobalIndexDocMap.keySet()) {
//			if(atmModel.DataSet.GlobalIndexDocMap.get(integer).GlobalIndex==doc.GlobalIndex){
//				assign.add(integer);
//			}
//		}
		
		return assign.toArray(new Integer[0]);
	}
}
