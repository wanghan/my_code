/**
 * 
 */
package utils;

import java.util.Vector;

/**
 * @author wanghan
 * 
 */
public class WordUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
//	public static Vector<String> SplitString(String s) {
//		Vector<String> vector = new Vector<String>();
//		Stemmer stemmer=new Stemmer();
//		String[] tokens=s.split("[^a-zA-Z]");
//		for (String string : tokens) {
//			String temp=string.trim();
//			stemmer.add(temp.toCharArray(), temp.toCharArray().length);
//			stemmer.stem();
//			if(temp.length()>2){
//				vector.add(stemmer.toString());
//			}
//		}
//		
//		return vector;
//	}
	public static Vector<String> SplitString(String s) {
		
		Vector<String> vector = new Vector<String>();

		String[] tokens=s.split("[^a-zA-Z]");
		for (String string : tokens) {
			String temp=string.trim();
			if(temp.length()>2){
				vector.add(temp);
			}
		}
		/*
		 * http://ftp.haie.edu.cn/Resource/GZ/GZYY/DCYFWF/NJSYYY/421b0061ZW_0015.
		 * htm
		 */
		for (int i = 0; i < vector.size(); i++) {
			String token = (String) vector.elementAt(i).trim();
			if (token.equalsIgnoreCase("feet")) {
				token = "foot";
			} else if (token.equalsIgnoreCase("geese")) {
				token = "goose";
			} else if (token.equalsIgnoreCase("lice")) {
				token = "louse";
			} else if (token.equalsIgnoreCase("mice")) {
				token = "mouse";
			} else if (token.equalsIgnoreCase("teeth")) {
				token = "tooth";
			} else if (token.equalsIgnoreCase("oxen")) {
				token = "ox";
			} else if (token.equalsIgnoreCase("children")) {
				token = "child";
			} else if (token.endsWith("men")) {
				token = token.substring(0, token.length() - 3) + "man";
			} else if (token.endsWith("ies")) {
				token = token.substring(0, token.length() - 3) + "y";
			} else if (token.endsWith("ves")) {
				if (token.equalsIgnoreCase("knives")
						|| token.equalsIgnoreCase("wives")
						|| token.equalsIgnoreCase("lives")) {
					token = token.substring(0, token.length() - 3) + "fe";
				} else {
					token = token.substring(0, token.length() - 3) + "f";
				}
			} else if (token.endsWith("oes") || token.endsWith("ches")
					|| token.endsWith("shes") || token.endsWith("ses")
					|| token.endsWith("xes")) {
				token = token.substring(0, token.length() - 2);
			} else if (token.endsWith("s")) {
				token = token.substring(0, token.length() - 1);
			}

			vector.setElementAt(token, i);
		}
		return vector;
	}
	
	public static boolean isWordNumber(String s){
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
