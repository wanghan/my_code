package actm.data;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import actm.data.base.BaseConference;

/**
 * @author wanghan
 *
 */
public class Conference extends BaseConference {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5343824925099734860L;
	
	public Conference() {
		// TODO Auto-generated constructor stub
		
	}


//	public String getGlobalName() {
//		if(globalName==null){
//			globalName=name.substring(0,name.lastIndexOf('_'));
//		}
//		return globalName;
//	}

	
	
}
