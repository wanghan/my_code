/**
 * 
 */
package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author wanghan
 *
 */
public class IndexValuePairSorting <V extends Comparable<V>>{
	
	ArrayList<KeyValuePair<Integer, V>> list;
	
	public IndexValuePairSorting(V[] vs) {
		// TODO Auto-generated constructor stub
		this.list=new ArrayList<KeyValuePair<Integer,V>>();
		for(int i=0;i<vs.length;++i){
			this.list.add(new KeyValuePair<Integer, V>(i,vs[i]));
		}
	}
	
	public ArrayList<KeyValuePair<Integer, V>> sort(boolean desc){
		if(desc){
			Collections.sort(list,new Comparator<KeyValuePair<Integer, V>>(){
				@Override
				public int compare(KeyValuePair<Integer, V> o1, KeyValuePair<Integer, V> o2) {
					// TODO Auto-generated method stub
					return o2.getValue().compareTo(o1.getValue());
				}
			});
		}
		else{
			Collections.sort(list,new Comparator<KeyValuePair<Integer, V>>(){
				@Override
				public int compare(KeyValuePair<Integer, V> o1, KeyValuePair<Integer, V> o2) {
					// TODO Auto-generated method stub
					return o1.getValue().compareTo(o2.getValue());
				}
			});
		}
		return list;
	}
}