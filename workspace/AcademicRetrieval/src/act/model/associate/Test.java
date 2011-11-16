/**
 * 
 */
package act.model.associate;

import utils.SerializeUtils;

/**
 * @author wanghan
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			TopicAssociationModel assModel=(TopicAssociationModel)SerializeUtils.deSerialize(TopicAssociationModel.storagePath);
			System.out.println();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

}
