package round1c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SquareTiles {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
			
			int t=Integer.parseInt(reader.readLine());
			
			for(int ttt=1;ttt<=t;++ttt){
				String line=reader.readLine();
				StringTokenizer st=new StringTokenizer(line);
				int n=Integer.parseInt(st.nextToken());
				int m=Integer.parseInt(st.nextToken());
				char c[][]=new char[n][m];
				int nblue=0;
				for(int i=0;i<n;++i){
					line=reader.readLine();
					for(int j=0;j<m;++j){
						c[i][j]=line.charAt(j);
						if(c[i][j]=='#'){
							nblue++;
						}
					}
				}
				if(nblue%4!=0){
					System.out.println("Case #" + ttt + ":");
					System.out.println("Impossible");
				}
				else
				{
					boolean isPos=true;
					for(int i=0;i<n;++i){
						for(int j=0;j<m;++j){
							if(c[i][j]=='#'){
								if(i==n-1||j==m-1){
									isPos=false;
									i=n;j=m;break;
								}
								else{
									if(c[i+1][j]=='#'&&c[i][j+1]=='#'&&c[i+1][j+1]=='#'){
										c[i][j]='/';
										c[i+1][j]='\\';
										c[i][j+1]='\\';
										c[i+1][j+1]='/';
									}
									else{
										isPos=false;
										i=n;j=m;break;
									}
								}
							}
						}
					}
					if(isPos){
						System.out.println("Case #" + ttt + ":");
						for(int i=0;i<n;++i){
							StringBuffer sb=new StringBuffer();
							for(int j=0;j<m;++j){
								sb.append(c[i][j]);
							}
							System.out.println(sb.toString());
						}
					}
					else{
						System.out.println("Case #" + ttt + ":");
						System.out.println("Impossible");
					}
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
