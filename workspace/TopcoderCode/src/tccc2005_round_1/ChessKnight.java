package tccc2005_round_1;

public class ChessKnight {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public double probability(int x, int y, int n){
		double chess[][][]=new double[8][8][n+1];
		x--;
		y--;
		chess[x][y][0]=1;
		int move[][]={{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};
		
		for(int i=0;i<n;++i){
			for(int xx=0;xx<8;++xx){
				for(int yy=0;yy<8;++yy){
					if(chess[xx][yy][i]!=0){
						for(int k=0;k<8;++k){
							int curx=xx+move[k][0];
							int cury=yy+move[k][1];
							if(curx>=0&&curx<8&&cury>=0&&cury<8){
								chess[curx][cury][i+1]+=chess[xx][yy][i]/8;
							}
						}
					}
				}
			}
		}
		double result=0;
		for(int xx=0;xx<8;++xx){
			for(int yy=0;yy<8;++yy){
				result+=chess[xx][yy][n];
			}
		}
		
		return result;
	}
}
