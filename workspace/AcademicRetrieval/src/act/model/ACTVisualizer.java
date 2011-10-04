/**
 * 
 */
package act.model;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Vector;

import tm.generalmodel.Word;

import actm.data.ACTMGlobalData;
import actm.data.Author;

/**
 * @author wanghan
 *
 */
public class ACTVisualizer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private ACTModel model;
	private ACTMGlobalData globalData;
	
	public ACTVisualizer(ACTModel model,ACTMGlobalData globalData) {
		// TODO Auto-generated constructor stub
		this.model=model;
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
	public String TopKConfPerTopic(int k){
		StringBuffer result=new StringBuffer();
		
		
		for (int i = 0; i < model.T; ++i) {
			Vector<KeyValueMap> map = new Vector<KeyValueMap>();
	
			for (int j = 0; j < model.C; ++j) {
				String name = globalData.getGlobalConferenceName(j);
				map.add(new KeyValueMap(model.psi[j][i], name));
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
	public String TopKTopicPerConference(int k){
		StringBuffer result=new StringBuffer();
		
		
		for (int i = 0; i < model.C; ++i) {
			Vector<KeyValueMap> map = new Vector<KeyValueMap>();
			String name = globalData.getGlobalConferenceName(i);
			for (int j = 0; j < model.T; ++j) {
				map.add(new KeyValueMap(model.psi[i][j], j));
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
