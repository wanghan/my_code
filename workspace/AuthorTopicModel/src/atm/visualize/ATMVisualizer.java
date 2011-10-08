/**
 * 
 */
package atm.visualize;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Vector;

import tm.generalmodel.Word;

import actm.data.ACTMGlobalData;
import actm.data.Author;
import atm.model.AuthorTopicModel;


/**
 * @author wanghan
 *
 */
public class ATMVisualizer {

	private AuthorTopicModel model;
	private ACTMGlobalData globalData;
	
	public ATMVisualizer(AuthorTopicModel model,ACTMGlobalData globalData) {
		// TODO Auto-generated constructor stub
		this.model=model;
		this.globalData=globalData;
	}
	
	public String TopKWordsPerTopic(int k){
		
		StringBuffer result=new StringBuffer();
		for(int i=0;i<model.T;++i){
			Vector<KeyValueMap> map=new Vector<KeyValueMap>();
			for(int j=0;j<model.W;++j){
				Word w=globalData.GetWord(j);
				map.add(new KeyValueMap(model.phi[j][i], w));
			}
			Collections.sort(map);
			result.append("Topic "+i+" : ");
			for(int j=0;j<k;++j){
				result.append(map.get(j).toString()+" ");
			}
			result.append("\n");
		}
		return result.toString();
	}
	
	public String TopKAuthorsPerTopic(int k){
		StringBuffer result=new StringBuffer();
		for(int i=0;i<model.T;++i){
			Vector<KeyValueMap> map=new Vector<KeyValueMap>();
			for(int j=0;j<model.A;++j){
				Author a=globalData.GetAuthor(j);
				map.add(new KeyValueMap(model.theta[i][j], a));
			}
			Collections.sort(map);
			result.append("Topic "+i+" : ");
			for(int j=0;j<k;++j){
				result.append(map.get(j).toString()+" ");
			}
			result.append("\n");
		}
		return result.toString();
	}
	
	public String TopKTopicPerAuthor(int k){
		StringBuffer result=new StringBuffer();
		for(int i=0;i<model.A;++i){
			Vector<KeyValueMap> map=new Vector<KeyValueMap>();
			Author a=globalData.GetAuthor(i);
			for(int j=0;j<model.T;++j){
				
				map.add(new KeyValueMap(model.theta[j][i], j));
			}
			Collections.sort(map);
			result.append("Author "+a.getName()+" : ");
			for(int j=0;j<k;++j){
				result.append(map.get(j).toString()+" ");
			}
			result.append("\n");
		}
		return result.toString();
	}
	
//	public HashMap<Integer,TreeMap<Integer, Double>> TopicTrends(){
		
//		HashMap<Integer,TreeMap<Integer, Double>> TopicTrend=new HashMap<Integer, TreeMap<Integer,Double>>();
//		
//		//key: year value: assignment count
//		HashMap<Integer, Integer> yearAssignment=new HashMap<Integer, Integer>();
//		
//		
//		HashMap<Integer,TreeMap<Integer, Integer>> topicYearTrend=new HashMap<Integer, TreeMap<Integer,Integer>>();
//		for(int i=0;i<model.DataSet.N;++i){
//			
//			int year=model.DataSet.GlobalIndexDocMap.get(i).getConference().g;
//			Integer count=yearAssignment.get(year);
//			if(count==null){
//				yearAssignment.put(year, 1);
//			}
//			else{
//				yearAssignment.put(year, count+1);
//			}
//			
//			int topic=model.z[i];
//			
//			TreeMap<Integer, Integer> trend=topicYearTrend.get(topic);
//			
//			if(trend==null){
//				trend=new TreeMap<Integer, Integer>();
//				trend.put(year, 1);
//				topicYearTrend.put(topic, trend);
//			}
//			else{
//				Integer ccc=trend.get(year);
//				if(ccc==null){
//					trend.put(year, 1);
//				}
//				else{
//					trend.put(year, ccc+1);
//				}
//				topicYearTrend.put(topic, trend);
//			}
//		}
//		for (Integer topic : topicYearTrend.keySet()) {
//			TreeMap<Integer, Integer> trend=topicYearTrend.get(topic);
//			if(trend!=null){
//				TreeMap<Integer, Double> trendPercentage=new TreeMap<Integer, Double>();
//				for (Integer year : trend.keySet()) {
//					Integer count=trend.get(year);
//					if(count!=null){
//						trendPercentage.put(year, 1.0*count/yearAssignment.get(year));
//					}
//				}
//				TopicTrend.put(topic, trendPercentage);
//			}
//		}
//		return TopicTrend;
//	}
}
class KeyValueMap implements Comparable<KeyValueMap>{
	Double key;
	Object value;
	public KeyValueMap(Double k, Object v) {
		// TODO Auto-generated constructor stub
		this.key=k;
		this.value=v;
	}
	
	@Override
	public int compareTo(KeyValueMap o) {
		// TODO Auto-generated method stub
		if(this.key>o.key){
			return -1;
		}
		else if(this.key<o.key){
			return 1;
		}
		else{
			return 0;
		}
			
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		DecimalFormat format=new DecimalFormat();
		format.setMaximumFractionDigits(4);
		return this.value+","+format.format(this.key);
	}
}