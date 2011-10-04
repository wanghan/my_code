package srm410;

import java.util.HashSet;

public class AddElectricalWires {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new AddElectricalWires().maxNewWires(	new String[]{"01000", "10100", "01010", "00100", "00000"}, new int[]{2, 4}));
	}
	public int maxNewWires(String[] wires, int[] gridConnections){
		int n=wires[0].length();
		HashSet<Integer> set=new HashSet<Integer>();
		for (Integer integer : gridConnections) {
			set.add(integer);
		}
		node sss[]=new node [n];
		for(int i=0;i<sss.length;++i){
			sss[i]=new node();
			sss[i].index=i;
		}
		for(int i=0;i<n;++i){
			for(int j=0;j<n;++j){
				if(!(set.contains(i)&&set.contains(j))){
					if(wires[i].charAt(j)!='1'){
						sss[i].childrens.add(j);
						sss[j].childrens.add(i);
					}
				}
			}
		}
		for (node node : sss) {
			if(node.childrens.contains(node.index)){
				node.childrens.remove(node.index);
			}
		}
		int result=0;
		while(true){
			boolean changed=false;
			node cur=null;
			for (node node : sss) {
				if(node.childrens.size()==1&&!node.asked){
					cur=node;
					break;
				}
				
			}
			if(cur==null)
				break;
			result++;
			cur.asked=true;
			for (Integer ci : cur.childrens) {
				for (node node : sss) {
					if(node.childrens.contains(ci)){
						node.childrens.remove(ci);
					}
				}
			}
			if(!changed){
				break;
			}
		}
		return result;
	}
}
class node{
	public int index;
	public boolean asked;
	public HashSet<Integer> childrens;
	public node() {
		// TODO Auto-generated constructor stub
		this.childrens=new HashSet<Integer>();
		asked=false;
	}
}