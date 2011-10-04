//package srm510;
//
//import java.util.HashSet;
//import java.util.TreeSet;
//
//public class TheAlmostLuckyNumbersDivOne {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		long rrr=new TheAlmostLuckyNumbersDivOne().find(Long.parseLong("1"), Long.parseLong("10000000000000000"));
//		System.out.println(rrr);
//		
//
//	}
//	public long find(long a, long b){
//		
//		long result=0;
//		if((b-a)<=10000){
//			for(long i=a;i<=b;++i){
//				if(dirTest(i)){
//					result++;
//				}
//			}
//			return result;
//		}
//		else{
//			TreeSet<Long> set=new TreeSet<Long>();
//			int la=String.valueOf(a).length();
//			int lb=String.valueOf(b).length();
//			if(la==lb){
//				result+=test(a, b);
//			}
//			else{
//				for(int i=la;i<=lb;++i){
//					if(i<lb&&i>la){
//						result+=test(createNum(9,i-1)+1, createNum(9,i));
//					}
//					else if(i==la){
//						result+=test(a, createNum(9,i));
//					}
//					else{
//						result+=test(createNum(9,i-1)+1, b);
//					}
//					
//				}
//			}
//			
//			return result;
//		}
//		
//	}
//	public boolean dirTest(long aaa){
//		int num47=0;
//		String s=String.valueOf(aaa);
//		for(int i=0;i<s.length();++i){
//			if(s.charAt(i)!='4'&&s.charAt(i)!='7'){
//				num47++;
//			}
//		}
//		if(num47>1){
//			return false;
//		}
//		else{
//			return true;
//		}
//	}
//	public long test(long b){
//		long result=0;
//		int ll=String.valueOf(b).length();
//		
//		for(int i=0;i<ll;++i){
//			for(int j=0;j<ll;++j){
//				if(j!=i){
//					
//				}
//			}
//		}
//		return result;
//	}
//	
//	public long createNum(int a, int l){
//		StringBuffer sb=new StringBuffer();
//		for(int i=0;i<l;++i){
//			sb.append(a);
//		}
//		return Long.parseLong(sb.toString());
//	}
//}
