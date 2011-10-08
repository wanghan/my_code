/**
 * 
 */
package atm.labeling;

import java.io.IOException;
import java.text.DecimalFormat;

import atm.model.Author;
import atm.model.AuthorTopicModel;

import utils.MapSorting;

/**
 * @author wanghan
 *
 */
public class AuthorLabelingKL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		AuthorLabelingKL autLabeling=new AuthorLabelingKL(model);
		
//		try {
//			FileWriter writer=new FileWriter("authorLabels.txt");
//			for (Author author : model.DataSet.authorSet.getAuthorSet()) {
//				String s=autLabeling.labeling(author);
//				writer.write(s+"\r\n");
//			}
//			writer.flush();
//			writer.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}

	
	private AuthorTopicModel atmModel;
	public AuthorLabelingKL(AuthorTopicModel model) {
		// TODO Auto-generated constructor stub
		this.atmModel=model;
	}

	
	public String labeling(Author author){
		Double weight[]=getAuthorPercentage(author);
		int [] order=new MapSorting<Double>(weight).sort(true);
		
		StringBuilder sb=new StringBuilder();
		
		sb.append(author.AuthorName);
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
	
	private Double[] getAuthorPercentage(Author author){
		Double weight[]=new Double[atmModel.T];
		double sum=0;
		for(int i=0;i<atmModel.T;++i){
			weight[i]=atmModel.theta[i][author.Index];
			sum+=weight[i];
		}

		
		//normalize
		for(int i=0;i<atmModel.T;++i){
			weight[i]/=sum;
		}
		return weight;
	}
}
