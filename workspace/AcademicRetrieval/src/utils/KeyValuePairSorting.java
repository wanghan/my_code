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
public class KeyValuePairSorting <K,V extends Comparable<V>>{
	
	ArrayList<KeyValuePair<K, V>> list;
	
	public KeyValuePairSorting(K[] ks, V[] vs) {
		// TODO Auto-generated constructor stub
		this.list=new ArrayList<KeyValuePair<K,V>>();
		for(int i=0;i<ks.length;++i){
			this.list.add(new KeyValuePair<K, V>(ks[i],vs[i]));
		}
	}
	
	public ArrayList<KeyValuePair<K, V>> sort(boolean desc){
		if(desc){
			Collections.sort(list,new Comparator<KeyValuePair<K, V>>(){
				@Override
				public int compare(KeyValuePair<K, V> o1, KeyValuePair<K, V> o2) {
					// TODO Auto-generated method stub
					return o2.getValue().compareTo(o1.getValue());
				}
			});
		}
		else{
			Collections.sort(list,new Comparator<KeyValuePair<K, V>>(){
				@Override
				public int compare(KeyValuePair<K, V> o1, KeyValuePair<K, V> o2) {
					// TODO Auto-generated method stub
					return o1.getValue().compareTo(o2.getValue());
				}
			});
		}
		return list;
	}
}