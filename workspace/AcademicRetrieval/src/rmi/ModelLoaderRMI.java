/**
 * 
 */
package rmi;

import java.rmi.server.UnicastRemoteObject;

import act.model.ACTModel;
import act.model.ACTModelLoader;
import actm.data.ACTMGlobalData;

/**
 * @author wanghan
 *
 */
public class ModelLoaderRMI extends UnicastRemoteObject implements ModelLoaderRMIInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ACTModelLoader loader;
	
	public ModelLoaderRMI() throws Exception{
		// TODO Auto-generated constructor stub
		loader=new ACTModelLoader();
	}
	
	@Override
	public ACTMGlobalData getGlobalData() {
		// TODO Auto-generated method stub
		return loader.getGlobalData();
	}
	@Override
	public ACTModel getModel() {
		// TODO Auto-generated method stub
		return loader.getModel();
	}
	
}
