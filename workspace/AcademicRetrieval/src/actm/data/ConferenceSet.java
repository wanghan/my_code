/**
 * 
 */
package actm.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author wanghan
 *
 */
public class ConferenceSet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6921672775601717616L;
	public HashMap<String,Conference> nameConfenceMap;
	public HashMap<Integer,Conference> indexConfenceMap;
	public HashMap<Integer, String> globalIndexNameMap;
	public HashMap<String, Integer> globalNameIndexMap;
	private int index;
	private int globalIndex;
	
	public ConferenceSet() {
		// TODO Auto-generated constructor stub
		this.index=0;
		this.globalIndex=0;
		this.indexConfenceMap=new HashMap<Integer, Conference>();
		this.nameConfenceMap=new HashMap<String, Conference>();
		this.globalIndexNameMap=new HashMap<Integer, String>();
		this.globalNameIndexMap=new HashMap<String, Integer>();
	}
	
	public Collection<Conference> getConferences(){
		
		return indexConfenceMap.values();
	}
	
	public Conference InsertConference(Conference conf){
		
		String globalName=conf.getName().substring(0,conf.getName().lastIndexOf('_'));
		if(globalNameIndexMap.containsKey(globalName)){
			int gloIndex=globalNameIndexMap.get(globalName);
			conf.setGlobalIndex(gloIndex);
		}
		else{
			int gloIndex=globalIndex++;
			conf.setGlobalIndex(gloIndex);
			globalNameIndexMap.put(globalName, gloIndex);
			globalIndexNameMap.put(gloIndex, globalName);
		}
		if(!nameConfenceMap.containsKey(conf.getName())){
			conf.setIndex(index);
			nameConfenceMap.put(conf.getName(), conf);
			indexConfenceMap.put(index++, conf);
			return conf;
		}
		else{
			return nameConfenceMap.get(conf.getName());
		}
	}
	public int getConferenceCount(){
		return indexConfenceMap.size();
	}
	public Conference getConference(int index){
		return indexConfenceMap.get(index);
	}
	
	public String getGlobalName(int index){
		return globalIndexNameMap.get(index);
	}
	public int getGlobalConferenceCount(){
		return globalIndexNameMap.size();
	}
}
