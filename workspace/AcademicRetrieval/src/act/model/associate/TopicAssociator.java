/**
 * 
 */
package act.model.associate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import utils.IndexValuePairSorting;
import utils.KeyValuePair;
import utils.SerializeUtils;
import act.model.ACTModel;
import actm.data.ACTMDocument;
import actm.data.Author;
import actm.data.Topic;

/**
 * @author wanghan
 *
 */
public class TopicAssociator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ACTModel model=ACTModel.LoadWholeModel("act model ngram slide training\\1305783341789.model");
			TopicAssociationModel assModel=TopicAssociator.modelingAssociation(model);
			SerializeUtils.serialize(assModel, TopicAssociationModel.storagePath);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int TopKNumber=10;
	public static int TopKNumberForTopic=5;
	
	public static TopicAssociationModel modelingAssociation(ACTModel model){
		
		Topic topics[]=new Topic[model.T];
		for (int i = 0; i < model.T; ++i) {
			Double [] wordWeight=new Double[model.W];
			Double [] docWeight=new Double[model.dataSet.documentSet.size()];
			Double [] authorWeight=new Double[model.A];
			for(int j=0;j<model.A;++j){
				authorWeight[j]=model.theta[i][j];
			}
			for(int j=0;j<model.W;++j){
				wordWeight[j]=model.phi[j][i];
			}
			for (ACTMDocument doc : model.dataSet.documentSet) {
				docWeight[doc.getIndex()]=0.0;
				Iterator<Author> ita=doc.getPaper().getAuthors().iterator();
				while(ita.hasNext()){
					Author cur=ita.next();
					docWeight[doc.getIndex()]+=model.theta[i][cur.getIndex()];
				}
				docWeight[doc.getIndex()]/=doc.getPaper().getAuthors().size();
			}
			
			IndexValuePairSorting<Double> wordSorting=new IndexValuePairSorting<Double>(wordWeight);
			IndexValuePairSorting<Double> authorSorting=new IndexValuePairSorting<Double>(authorWeight);
			IndexValuePairSorting<Double> paperSorting=new IndexValuePairSorting<Double>(docWeight);
			
			ArrayList<KeyValuePair<Integer, Double>> wordSortResult=wordSorting.sort(true);
			ArrayList<KeyValuePair<Integer, Double>> authorSortResult=authorSorting.sort(true);
			ArrayList<KeyValuePair<Integer, Double>> paperSortResult=paperSorting.sort(true);
			
			topics[i]=new Topic();
			topics[i].index=i;
			
			for(int k=0;k<TopKNumber;++k){
				topics[i].topRelatedAuthorIds.add(authorSortResult.get(k).getKey());
				topics[i].topRelatedWordIds.add(wordSortResult.get(k).getKey());
				topics[i].topRelatedPaperIds.add(paperSortResult.get(k).getKey());
			}
			
		}
		
		TopicAssociationModel result=new TopicAssociationModel();
		result.topics=topics;
		result.authorRelatedTopic=new int[model.A][TopKNumberForTopic];
		result.conferenceRelatedTopic=new int[model.C][TopKNumberForTopic];
		result.paperRelatedTopic=new int[model.dataSet.documentSet.size()][TopKNumberForTopic];
		
		for (ACTMDocument doc : model.dataSet.documentSet) {
			Double [] docWeight=new Double[model.T]; 
			for (int i = 0; i < model.T; ++i) {
				docWeight[i]=0.0;
			}
			for (int i = 0; i < model.T; ++i) {
				Iterator<Author> ita=doc.getPaper().getAuthors().iterator();
				while(ita.hasNext()){
					Author cur=ita.next();
					docWeight[doc.getIndex()]+=model.theta[i][cur.getIndex()];
				}
				docWeight[i]/=doc.getPaper().getAuthors().size();
			}
			IndexValuePairSorting<Double> paperSorting=new IndexValuePairSorting<Double>(docWeight);
			ArrayList<KeyValuePair<Integer, Double>> paperSortResult=paperSorting.sort(true);
			for(int k=0;k<TopKNumberForTopic;++k){
				result.paperRelatedTopic[doc.getIndex()][k]=paperSortResult.get(k).getKey();
			}
		}
		
		for(int j=0;j<model.A;++j){
			Double [] authorWeight=new Double[model.T];
			for (int i = 0; i < model.T; ++i) {
				authorWeight[i]=model.theta[i][j];
			}
			IndexValuePairSorting<Double> authorSorting=new IndexValuePairSorting<Double>(authorWeight);
			ArrayList<KeyValuePair<Integer, Double>> authorSortResult=authorSorting.sort(true);
			for(int k=0;k<TopKNumberForTopic;++k){
				result.authorRelatedTopic[j][k]=authorSortResult.get(k).getKey();
			}
		}
		
		return result;
	}
	
}
