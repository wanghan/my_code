package srm203;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnLinker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new UnLinker().clean("O4:http://www.infoY0pBwww..HxbmX5ID.edueduE9www.os"));
	}
	public String clean(String text){
		Pattern p=Pattern.compile("(http://|http://www\\.|www\\.)[a-zA-Z0-9\\.]+[\\.](com|org|edu|info|tv)");
    
		int k=1;
		String temp="OMIT";
		Matcher m=p.matcher(text);
		StringBuffer sb=new StringBuffer(text);
		while(m.find()){
			sb.replace(m.start(), m.end(), temp+String.valueOf(k++));
			m=p.matcher(sb.toString());
		}
		
		return sb.toString();
	}
}	
