/**
 * 
 */
package rmi;

import java.io.Serializable;

import actm.data.Paper;

/**
 * @author wanghan
 *
 */
public class AssociateResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8830353259579524547L;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public String title;
	public Paper[] list;

}
