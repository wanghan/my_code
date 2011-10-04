/**
 * 
 */
package utils;

import java.util.ArrayList;

/**
 * @author wanghan
 *
 */
public class Trie {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String [] s={"Teachers","leave","them","kids","alone"};
		TrieNode root=new TrieNode();
		root.word=new Word("");

//		for (String string : s) {
//			root=TrieNode.AddWord(root, string, 0);
//		}
		System.out.println(1);
	}


}

class TrieNode{
	
	static int MAX_INDEX=52;
	
	Word word;
	TrieNode edges[];
	
	public TrieNode() {
		// TODO Auto-generated constructor stub
		this.edges=new TrieNode[MAX_INDEX];
		for(int i=0;i<MAX_INDEX;++i){
			edges[i]=null;
		}
		this.word=null;
	}
	
	public static int getIndex(char c){
		if(Character.isLowerCase(c)){
			return c-'a';
		}
		else{
			return 26+c-'A';
		}
	}
	
	public static TrieNode AddWord(TrieNode root, Word w,int level){
		if(w.word.length()==(level+1)){
			int index=TrieNode.getIndex(w.word.charAt(level));
			if(root.edges[index]==null){
				TrieNode node=new TrieNode();
				node.word=new Word(w.word);
				node.word.startIndex.addAll(w.startIndex);
				root.edges[index]=node;
			}
			else{
				if(root.edges[index].word==null){
					root.edges[index].word=new Word(w.word);
				}

				root.edges[index].word.startIndex.addAll(w.startIndex);
			}
		}
		else{
			int index=TrieNode.getIndex(w.word.charAt(level));
			if(root.edges[index]==null){
				TrieNode node=new TrieNode();
				root.edges[index]=AddWord(node, w,level+1);
			}
			else{
				root.edges[index]=AddWord(root.edges[index],w,level+1);
			}
		}
		return root;
	}
	public static Word search(TrieNode root,String w){
		for(int i=0;i<w.length()-1;++i){
			int index=TrieNode.getIndex(w.charAt(i));
			if(root.edges[index]==null){
				return null;
			}
			else{
				root=root.edges[index];
			}
		}
		int index=TrieNode.getIndex(w.charAt(w.length()-1));
		if(root.edges[index]==null){
			return null;
		}
		return root.edges[index].word;
	}
}
class Word{
	String word;
	ArrayList<Integer> startIndex;
	public Word(String w) {
		// TODO Auto-generated constructor stub
		this.word=w;
		startIndex=new ArrayList<Integer>();
	}
}