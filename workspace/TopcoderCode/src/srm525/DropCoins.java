package srm525;

import java.util.HashSet;
import java.util.LinkedList;

public class DropCoins {

	/**
	 * @param args
	 */
	
	private static boolean hasResult=false;
	private static int best=Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s[]={"...."
				,".oo."
				,".oo."
				,"...."};
		System.out.println(new DropCoins().getMinimum(s, 3));
	}
	public int getMinimum(String[] board, int K){
		
		int c=0;
		for(int i=0;i<board.length;++i){
			for(int j=0;j<board[0].length();++j){
				if(board[i].charAt(j)=='o')
					c++;
			}
		}
		HashSet<String> set=new HashSet<String>();
		Board b=new Board(board, 0, c,0);
		LinkedList<Board> qu=new LinkedList<Board>();
		qu.add(b);
		set.add(b.toString());
		while(qu.size()>0){
			Board bb=qu.getFirst();
			qu.removeFirst();
		
			if(bb.coins==K){
				return bb.move1;
			}
			else if(bb.coins<K){
				continue;
			}
			else if(bb.coins==0){
				continue;
			}
			else{
				for(int i=1;i<=4;++i){
					Board newv=new Board(bb.bb,i,bb.coins,bb.move1+1);
					if(newv.coins==K){
						return newv.move1;
					}
					else if(newv.coins<K){
						continue;
					}
					else if(newv.coins==0){
						continue;
					}
					else if(set.contains(newv.toString()))
						continue;
					else{
						qu.addLast(newv);
						set.add(newv.toString());
					}
				}
			}
		}
		return -1;
	}
}

class Board{
	String[] bb;
	int coins;
	int move1;
	public Board(String[] b, int move,int c,int m) {
		bb=new String[b.length];
		coins=c;
		move1=m;
		if(move==0){
			for(int i=0;i<b.length;++i){
				bb[i]=new String(b[i]);
			}
		}
		else if(move==1){
			for(int i=0;i<b.length-1;++i){
				bb[i]=new String(b[i+1]);
			}
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<b[0].length();++i){
				sb.append('.');
				if(b[0].charAt(i)=='o'){
					coins--;
				}
			}
			bb[b.length-1]=sb.toString();	
		}
		else if(move==2){
			for(int i=1;i<b.length;++i){
				bb[i]=new String(b[i-1]);
			}
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<b[0].length();++i){
				sb.append('.');
				if(b[bb.length-1].charAt(i)=='o'){
					coins--;
				}
			}
			bb[0]=sb.toString();	
		}
		else if(move==3){
			for(int i=0;i<b.length;++i){
				bb[i]=b[i].substring(1)+".";
				if(b[i].charAt(0)=='o')
					coins--;
			}
		}
		else{
			for(int i=0;i<b.length;++i){
				bb[i]="."+b[i].substring(0,b[i].length()-1);
				if(b[i].charAt(b[i].length()-1)=='o')
					coins--;
			}
		}
	}
	public String toString() {
		StringBuffer sb=new StringBuffer();
		for (String s : bb) {
			sb.append(s);
		}
		return sb.toString();
	}
}