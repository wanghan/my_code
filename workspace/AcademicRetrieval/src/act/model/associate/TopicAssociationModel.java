/**
 * 
 */
package act.model.associate;

import java.io.Serializable;
import actm.data.Topic;

/**
 * @author wanghan
 *
 */
public class TopicAssociationModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2198646795627179100L;

	public static String storagePath="index\\ram\\associate.ram";
	
	public Topic [] topics;
	public int [][] paperRelatedTopic;
	public int [][] authorRelatedTopic;
	public int [][] conferenceRelatedTopic;
}
