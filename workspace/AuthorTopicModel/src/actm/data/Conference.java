package actm.data;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wanghan
 *
 */
public class Conference implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5343824925099734860L;
	private String name;
	private Date date;
	private int index;
	private int globalIndex;
	private String globalName;
	
	public Conference() {
		// TODO Auto-generated constructor stub
		this.globalName=null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getGlobalIndex() {
		return globalIndex;
	}

	public void setGlobalIndex(int globalIndex) {
		this.globalIndex = globalIndex;
	}

	public String getGlobalName() {
		if(globalName==null){
			globalName=name.substring(0,name.lastIndexOf('_'));
		}
		return globalName;
	}
	
	
}
