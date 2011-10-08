/**
 * 
 */
package lda.visualize;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Vector;

import tm.generalmodel.Word;
import lda.LDAModel;
import actm.data.ACTMGlobalData;

/**
 * @author wanghan
 *
 */
public class LDAVisualize {
	private ACTMGlobalData globalData;
	private LDAModel model;
	
	public LDAVisualize(LDAModel model,ACTMGlobalData globalData) {
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