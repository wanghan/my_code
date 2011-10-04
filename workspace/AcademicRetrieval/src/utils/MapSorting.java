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
public class MapSorting<K extends Comparable<K>> {

	ArrayList<KeyValueMap<K>> list;
	
	public MapSorting(K[] v) {
		// TODO Auto-generated constructor stub
		this.list=new ArrayList<KeyValueMap<K>>();
		for(int i=0;i<v.length;++i){
			this.list.add(new KeyValueMap<K>(i,v[i]));
		}
	}
	
	public int[] sort(boolean desc){
		
		Collections.sort(list, new Comparator<KeyValueMap<K>>(){
			@Override
			public int compare(KeyValueMap<K> arg0, KeyValueMap<K> arg1) {
				// TODO Auto-generated method stub
				return arg0.value.compareTo(arg1.value);
			}
		});
		int [] result=new int[list.size()];
		if(desc){
			for(int i=list.size()-1;i>=0;--i){
				result[list.size()-1-i]=list.get(i).index;
			}
		}
		else{
			for(int i=0;i<list.size();++i){
				result[i]=list.get(i).index;
			}
		}
		return result;
		
	}
}
class KeyValueMap<K>{
	int index;
	K value;
	public KeyValueMap(int index, K k) {
		// TODO Auto-generated constructor stub
		this.index=index;
		this.value=k;
	}
}