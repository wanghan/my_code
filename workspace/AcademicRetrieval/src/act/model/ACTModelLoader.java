/**
 * 
 */
package act.model;

import java.io.Serializable;

import actm.data.ACTMGlobalData;

/**
 * @author wanghan
 *
 */
public class ACTModelLoader implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ACTMGlobalData globalData;
	private ACTModel model;
	
	public ACTModelLoader() {
		// TODO Auto-generated constructor stub
		try {
			globalData=ACTMGlobalData.deserialize("./ACTModels/1319975613762_I100_T100/1319975613763.glo");
			model=ACTModel.LoadWholeModel("./ACTModels/1319975613762_I100_T100/1320018826651.model");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public ACTMGlobalData getGlobalData() {
		return globalData;
	}

	public void setGlobalData(ACTMGlobalData globalData) {
		this.globalData = globalData;
	}

	public ACTModel getModel() {
		return model;
	}

	public void setModel(ACTModel model) {
		this.model = model;
	}
	
	
}
