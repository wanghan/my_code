package atm.labeling;

import java.io.Serializable;
import java.util.StringTokenizer;
import java.util.Vector;

public class Parse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4115618816653256621L;
	public Vector<String> tokens;
	private String parseString;
	
	public Parse(String s) {
		// TODO Auto-generated constructor stub
		this.tokens=new Vector<String>();
		StringTokenizer tokenizer=new StringTokenizer(s.toLowerCase()," -");
		
		while(tokenizer.hasMoreTokens()){
			tokens.add(tokenizer.nextToken());
		}
		if(tokens.size()>0&&(tokens.get(0).equals("the")||tokens.get(0).equals("a")||tokens.get(0).equals("an"))){
			tokens.remove(0);
		}
		StringBuilder sb=new StringBuilder();
		for (String t : tokens) {
			sb.append(t+" ");
		}
		this.parseString=sb.toString().trim();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return parseString;
	}
}
