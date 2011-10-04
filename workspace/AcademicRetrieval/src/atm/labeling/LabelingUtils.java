/**
 * 
 */
package atm.labeling;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

import utils.StopwordsFilter;
import utils.WordUtils;

/**
 * @author wanghan
 *
 */
public class LabelingUtils {
	
	public static void main(String[] args) {
		
		Vector<Parse> labels=LabelingUtils.loadCandidateLabelsFromNgramRankingFile("rude.x2");
		System.out.println(1);
	}
	
	
	public static Vector<Parse> loadCandidateLabelsFromFile(String filepath){
		
		Vector<Parse> candidateLabels=new Vector<Parse>();
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
			while(true){
				String line=reader.readLine();
				if(line==null){
					break;
				}
				Parse parse=new Parse(line);
				if(parse.tokens.size()>1){
					candidateLabels.add(parse);
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return candidateLabels;
	}
	
	public static Vector<Parse> loadCandidateLabelsFromNgramRankingFile(String filepath){
		Vector<Parse> candidateLabels=new Vector<Parse>();
		StopwordsFilter filter=StopwordsFilter.GetInstance();
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
			while(true){
				String line=reader.readLine();
				if(line==null){
					break;
				}
				StringTokenizer st=new StringTokenizer(line);
				String firstToken=st.nextToken();
				
				String[] tokens=firstToken.split("<>");
				StringBuffer sb=new StringBuffer();
				boolean goodLabel=true;
				for(int i=0;i<tokens.length-1;++i){
					String little=tokens[i].toLowerCase();
					if(WordUtils.isWordNumber(little)||filter.IsStopword(little)){
						goodLabel=false;
						break;
					}
					sb.append(tokens[i]+" ");
					
				}
				if(goodLabel){
					Parse parse=new Parse(sb.toString().trim());
					if(parse.tokens.size()>1){
						candidateLabels.add(parse);
					}	
				}

			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return candidateLabels;
	}
}
