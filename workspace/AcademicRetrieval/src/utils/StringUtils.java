package utils;

import java.util.HashSet;
import java.util.Vector;

public class StringUtils {

	public static Vector<String> splitStringToWords(String s){
		StopwordsFilter filter=StopwordsFilter.GetInstance();
		String temp[]=s.split("[^a-zA-Z_]+");
		Vector<String> result=new Vector<String>();
		for (String string : temp) {
			String sss=string.toLowerCase().trim();
			if(!filter.IsStopword(sss)){
				result.add(sss);
			}
		}
		return result;
	}
	/**
	 * max N=3
	 */
	public static Vector<String> splitStringToNgrams(String s,HashSet<String> ngram){
		StopwordsFilter filter=StopwordsFilter.GetInstance();
		String temp[]=s.split("[^a-zA-Z_]+");
		Vector<String> result=new Vector<String>();
		for(int i=0;i<temp.length;++i){
			String sss=temp[i].toLowerCase().trim();
			if(!filter.IsStopword(sss)){
				result.add(sss);
			}
			if(i+1<temp.length){
				String ttt=(temp[i]+" "+temp[i+1]).toLowerCase();
				if(ngram.contains(ttt))
					result.add(ttt);
			}
			if(i+2<temp.length){
				String ttt=(temp[i]+" "+temp[i+1]+" "+temp[i+2]).toLowerCase();
				if(ngram.contains(ttt))
					result.add(ttt);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		String test="In a multimodal conversational interface supporting speech and deictic gesture, deictic gestures on the graphical display have been traditionally used to identify user attention, for example, through reference resolution. Since the context of the identified attention can potentially constrain the associated intention, our hypothesis is that deictic gestures can go beyond attention and apply to intention recognition. Driven by this assumption, this paper systematically investigates the role of deictic gestures in intention recognition. We experiment with different model-based methods and instancebased methods to incorporate gestural information for intention recognition. We examine the effects of utilizing gestural information in two different processing stages: speech recognition stage and language understanding stage. Our empirical results have shown that utilizing gestural information improves intention recognition. The performance is further improved when gestures are incorporated in both speech recognition and language understanding stages compared to either stage alone.";
		Vector<String> temp=splitStringToWords(test);
		System.out.println(1);
	}
}
