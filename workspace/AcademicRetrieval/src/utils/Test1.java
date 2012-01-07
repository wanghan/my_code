package utils;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

import atm.labeling.LabelingUtils;
import atm.labeling.Parse;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileWriter writer=new FileWriter("rude.x2",false);
			Scanner reader=new Scanner(new File("rude1.x2"));
			while(reader.hasNext()){
					String line=reader.nextLine();
					if(line==null){
						break;
					}
					StringTokenizer st=new StringTokenizer(line," ");
					String firstToken=st.nextToken();
					
					String[] tokens=firstToken.split("<>");
					StringBuffer sb=new StringBuffer();
					for(int i=0;i<tokens.length-1;++i){
						sb.append(tokens[i]+" ");
						
					}
					double score=Double.parseDouble(st.nextToken());
					writer.write(sb.toString()+"\t"+score+"\n");
					writer.flush();
				}
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
