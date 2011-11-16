/**
 * 
 */
package rmi;

import java.rmi.Remote;

import act.model.ACTModel;
import actm.data.ACTMGlobalData;

/**
 * @author wanghan
 *
 */
public interface ModelLoaderRMIInterface extends Remote{
	public ACTMGlobalData getGlobalData()throws Exception;
	public ACTModel getModel()throws Exception;
}
