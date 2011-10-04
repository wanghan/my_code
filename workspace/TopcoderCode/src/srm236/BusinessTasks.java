package srm236;

import java.util.Vector;

public class BusinessTasks {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public String getTask(String[] list, int n){
		Vector<String> vvv=new Vector<String>();
		for (String string : list) {
			vvv.add(string);
		}
		int cur=0;
		while(vvv.size()>1){
			cur=(cur+n-1)%vvv.size();
			vvv.remove(cur);
		}
		return vvv.get(0);
	}
}
