/**
 * 
 */
package actm.visualize;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Vector;

import tm.generalmodel.Word;
import actm.model.AuthorConferenceTopicModel;
import actm.data.ACTMGlobalData;
import actm.data.Author;

/**
 * @author wanghan
 * 
 */
public class ACTMVisualizer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private AuthorConferenceTopicModel model;
	private ACTMGlobalData globalData;

	public ACTMVisualizer(AuthorConferenceTopicModel m, ACTMGlobalData globalData) {
		// TODO Auto-generated constructor stub
		this.model = m;
		this.globalData=globalData;
	}

	public String TopKWordsPerTopic(int k) {

		StringBuffer result = new StringBuffer();
		for (int i = 0; i < model.T; ++i) {
			Vector<KeyValueMap> map = new Vector<KeyValueMap>();
			for (int j = 0; j < model.W; ++j) {
				Word w = globalData.GetWord(j);
				map.add(new KeyValueMap(model.phi[j][i], w));
			}
			Collections.sort(map);
			result.append("Topic " + i + " : ");
			for (int j = 0; j < k; ++j) {
				result.append(map.get(j).toString() + " ");
			}
			result.append("\n");
		}
		return result.toString();
	}

	public String TopKAuthorsPerTopic(int k) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < model.T; ++i) {
			Vector<KeyValueMap> map = new Vector<KeyValueMap>();
			for (int j = 0; j < model.A; ++j) {
				Author a = globalData.GetAuthor(j);
				map.add(new KeyValueMap(model.theta[i][j], a));
			}
			Collections.sort(map);
			result.append("Topic " + i + " : ");
			for (int j = 0; j < k; ++j) {
				result.append(map.get(j).toString() + " ");
			}
			result.append("\n");
		}
		return result.toString();
	}

	public String TopKTopicPerAuthor(int k) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < model.A; ++i) {
			Vector<KeyValueMap> map = new Vector<KeyValueMap>();
			Author a = globalData.GetAuthor(i);
			for (int j = 0; j < model.T; ++j) {

				map.add(new KeyValueMap(model.theta[j][i], j));
			}
			Collections.sort(map);
			result.append("Author " + a.getName() + " : ");
			for (int j = 0; j < k; ++j) {
				result.append(map.get(j).toString() + " ");
			}
			result.append("\n");
		}
		return result.toString();
	}
	
	public String TopKAuthorPerConference(int k){
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < model.C; ++i) {
			Vector<KeyValueMap> map = new Vector<KeyValueMap>();
			String name = globalData.getGlobalConferenceName(i);
			for (int j = 0; j < model.A; ++j) {
				Author a = globalData.GetAuthor(j);
				map.add(new KeyValueMap(model.eta[j][i], a));
			}
			Collections.sort(map);
			result.append("Conf " + name + " : ");
			for (int j = 0; j < k; ++j) {
				result.append(map.get(j).toString() + " ");
			}
			result.append("\n");
		}
		return result.toString();
	}
	
	public String TopKTopicPerConference(int k){
		StringBuffer result=new StringBuffer();
		
		double [][] cap=new double[model.C][model.T];
		for(int i=0;i<model.C;++i){
	//		double sum=0;
			for(int j=0;j<model.T;++j){
				double temp=0;
				for(int x=0;x<model.A;++x){
					temp+=model.theta[j][x]*model.eta[x][i];
					if(temp==Double.NaN){
						System.out.println(model.theta[j][x]);
						System.out.println(model.eta[x][i]);
					}
				}
				cap[i][j]=temp;
	//			sum+=temp;
			}
	//		System.out.println(i+" "+sum);
		}
		
		for (int i = 0; i < model.C; ++i) {
			Vector<KeyValueMap> map = new Vector<KeyValueMap>();
			String name = globalData.getGlobalConferenceName(i);
			for (int j = 0; j < model.T; ++j) {
				map.add(new KeyValueMap(cap[i][j], j));
			}
			Collections.sort(map);
			result.append("Conf " + name + " : ");
			for (int j = 0; j < k; ++j) {
				result.append(map.get(j).toString() + " ");
			}
			result.append("\n");
		}
		
		return result.toString();
	}
	
}

class KeyValueMap implements Comparable<KeyValueMap> {
	Double key;
	Object value;

	public KeyValueMap(Double k, Object v) {
		// TODO Auto-generated constructor stub
		this.key = k;
		this.value = v;
	}

	@Override
	public int compareTo(KeyValueMap o) {
		// TODO Auto-generated method stub
		if (this.key > o.key) {
			return -1;
		} else if (this.key < o.key) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		DecimalFormat format = new DecimalFormat();
		format.setMaximumFractionDigits(4);
		return this.value + "," + format.format(this.key);
	}
}