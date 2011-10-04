package srm232;

import java.util.ArrayList;

public class WordFind {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String a[]={"EASYTOFINDEAGSRVHOTCJYG",
		 "FLVENKDHCESOXXXXFAGJKEO",
		 "YHEDYNAIRQGIZECGXQLKDBI",
		 "DEIJFKABAQSIHSNDLOMYJIN",
		 "CKXINIMMNGRNSNRGIWQLWOG",
		 "VOFQDROQGCWDKOUYRAFUCDO",
		 "PFLXWTYKOITSURQJGEGSPGG"};
		String b[]={"EASYTOFIND", "DIAG", "GOING", "THISISTOOLONGTOFITINTHISPUZZLE"};
		
		String r[]=new WordFind().findWords(a, b);
		for (String string : r) {
			System.out.println(string);
		}
	}
	public String[] findWords(String[] grid, String[] wordList){
		ArrayList<String> result=new ArrayList<String>();
		for (String word : wordList) {
			result.add(search(grid, word));
		}
		return result.toArray(new String[0]);
	}
	private String search(String[] grid, String word){
		int r=grid.length;
		int c=grid[0].length();
		for(int i=0;i<r;++i){
			for(int j=0;j<c;++j){
				 if(grid[i].charAt(j)!=word.charAt(0)){
					 continue;
				 }
				 else{
					 if(r-i>=word.length()){
						 boolean isEqual=true;
						 for(int k=0;k<word.length();++k){
							 if(grid[i+k].charAt(j)!=word.charAt(k)){
								 isEqual=false;
								 break;
							 }
						 }
						 if(isEqual){
							 return i+" "+j;
						 }
					 }
					 if(c-j>=word.length()){
						 boolean isEqual=true;
						 for(int k=0;k<word.length();++k){
							 if(grid[i].charAt(j+k)!=word.charAt(k)){
								 isEqual=false;
								 break;
							 }
						 }
						 if(isEqual){
							 return i+" "+j;
						 }
					 }
					 if((r-i)>=word.length()&&(c-j)>=word.length()){
						 boolean isEqual=true;
						 for(int k=0;k<word.length();++k){
							 if(grid[i+k].charAt(j+k)!=word.charAt(k)){
								 isEqual=false;
								 break;
							 }
						 }
						 if(isEqual){
							 return i+" "+j;
						 }
					 }
				 }
			}
		}
		return "";
	}
}
