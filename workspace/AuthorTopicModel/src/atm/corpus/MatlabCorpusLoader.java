/**
 * 
 */
package atm.corpus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

import tm.generalmodel.Vocabulary;

import atm.model.ATMDataSet;
import atm.model.Author;
import atm.model.AuthorSet;
import atm.model.ATMDocument;


/**
 * @author wanghan
 *
 */
public class MatlabCorpusLoader {
	
	private HashMap<Integer, ATMDocument> indexDocMap;
	private Vocabulary wordSet;
	
	public MatlabCorpusLoader() {
		// TODO Auto-generated constructor stub
		this.indexDocMap=new HashMap<Integer, ATMDocument>();
	}
	
	public ATMDataSet Load(){
		
		
		wordSet=LoadWords();
		AuthorSet authorSet=LoadAuthors();
		LoadDocs(authorSet);
		LoadDocWords();
		Vector<ATMDocument> docSet=new Vector<ATMDocument>();
		docSet.addAll(indexDocMap.values());
		ATMDataSet data=new ATMDataSet(wordSet,authorSet,docSet);
		return data;
	}
	
	private void LoadDocWords(){
		File DSfile=new File("DS.txt");
		File WSfile=new File("WS.txt");
		try {
			BufferedReader dsreader=new BufferedReader(new InputStreamReader(new FileInputStream(DSfile)));
			BufferedReader wsreader=new BufferedReader(new InputStreamReader(new FileInputStream(WSfile)));
			while(true){
				String line1=dsreader.readLine();
				String line2=wsreader.readLine();
				if(line1==null&&line2==null){
					break;
				}
				else if(line1==null||line2==null){
					System.err.println("error");
				}
				
				ATMDocument doc=indexDocMap.get((int)(Double.parseDouble(line1))-1);
				doc.BagOfWord.add(wordSet.GetWord((int)(Double.parseDouble(line2))-1));
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void LoadDocs(AuthorSet authorSet){
		File file=new File("authorDoc.txt");
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while(true){
				String line=reader.readLine();
				if(line==null){
					break;
				}
				StringTokenizer st=new StringTokenizer(line,": ");
				int authorIndex=Integer.parseInt(st.nextToken())-1;
				while(st.hasMoreTokens()){
					int docIndex=Integer.parseInt(st.nextToken())-1;
					ATMDocument doc=indexDocMap.get(docIndex);
					if(doc==null){
						doc=new ATMDocument();
						doc.GlobalIndex=docIndex;
						indexDocMap.put(docIndex, doc);
					}
					Author author=authorSet.GetAuthor(authorIndex);
					if(author!=null){
						doc.Authors.add(author);
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Vocabulary LoadWords(){
		Vocabulary vvv=new Vocabulary();
		File file=new File("words.txt");
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while(true){
				String line=reader.readLine();
				if(line==null){
					break;
				}
				line=line.trim();
				vvv.InsertWord(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vvv;
	}
	private AuthorSet LoadAuthors(){
		AuthorSet result=new AuthorSet();
		File file=new File("author.txt");
		
		int index=0;
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while(true){
				String line=reader.readLine();
				if(line==null){
					break;
				}
				result.InsertAuthor(index++, line.trim());
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
}
