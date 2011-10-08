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
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

import atm.model.ATMDataSet;
import atm.model.Author;
import atm.model.AuthorSet;
import atm.model.ATMDocument;

import tm.generalmodel.Vocabulary;
import tm.generalmodel.Word;
import utils.NumberUtils;
import utils.Stemmer;
import utils.StopwordsFilter;
import utils.WordUtils;




/**
 * @author wanghan
 *
 */
public class NIPSCorpusLoader {

	/**
	 * @param args
	 */
	
	public boolean SHOW_DEBUG_INFO=false;
	public static boolean IS_STEM_WORD=false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NIPSCorpusLoader().Load(0,12);
	}

	private String FilesRoot="nips12raw_str602/nipstxt/";
	
	private static int MIN_ABS_LENGTH=50;
	
	
	public ATMDataSet Load(int startIndex, int endIndex){
		
		HashMap<String,ATMDocument> docTitleMap=new HashMap<String, ATMDocument>();
		HashMap<Integer,ATMDocument> docGloIndexMap=new HashMap<Integer, ATMDocument>();
		for(int i=startIndex;i<=endIndex;++i){
			NumberFormat format=NumberFormat.getIntegerInstance();
			format.setMinimumIntegerDigits(2);
			int year=1987+i;
			
			String paperPath=FilesRoot+"nips"+format.format(i);
			
			HashMap<Integer, ATMDocument> documentSet=new HashMap<Integer, ATMDocument>();
			File paperRoot=new File(paperPath);
			for (File paper : paperRoot.listFiles()) {
				ATMDocument doc=LoadDocument(paper);
				if(doc!=null){
					doc.Year=year;
					documentSet.put(doc.Index, doc);
				}
			}
			
			String authorPath=FilesRoot+"idx/c"+format.format(i)+".txt";
			LoadDocTities(authorPath, documentSet);
			
			String subjectPath=FilesRoot+"idx/s"+format.format(i)+".txt";
			LoadDocsSubjects(subjectPath, documentSet);
			
			for (ATMDocument doc : documentSet.values()) {
				ATMDocument old=docTitleMap.put(doc.Title, doc);
				
				if(old!=null)
					System.err.println("Duplicate doc:"+doc.GlobalIndex);
			}
			
		}
		LoadGlobalTitles(docTitleMap);
		
		for (ATMDocument doc : docTitleMap.values()) {
			
			docGloIndexMap.put(doc.GlobalIndex, doc);
		}
		
		AuthorSet authorSet=LoadAuthors();
		LoadAuthorDoc(authorSet, docGloIndexMap);
		
		
		Vector<ATMDocument> refinedDocSet=new Vector<ATMDocument>();
		Vocabulary wordSet=LoadWords();
		
		
		for (ATMDocument doc : docGloIndexMap.values()) {
			if(doc.Authors.size()>0&&doc.Contents.length()>MIN_ABS_LENGTH){
				
				Vector<String> words=WordUtils.SplitString(doc.Contents+" "+doc.Title);
				for (String word : words) {
					word=word.toLowerCase();
					Word w=wordSet.GetWordWithInsert(word);
					if(w!=null)
						doc.InsertWord(w);
				}
				refinedDocSet.add(doc);
			}
			
		}
		
		
		return new ATMDataSet(wordSet, authorSet, refinedDocSet);
	}
	
	private void LoadDocsSubjects(String filepath,HashMap<Integer, ATMDocument> documentSet){
		File file=new File(filepath);
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while(true){
				String line=reader.readLine();
				if(line==null){
					break;
				}
				StringTokenizer tokenizer=new StringTokenizer(line,",");
				Vector<String> keywords=new Vector<String>();
				boolean isIndex=false;
				while(tokenizer.hasMoreTokens()){
					String token=tokenizer.nextToken().trim();
					if(isIndex&&NumberUtils.IsInteger(token.trim())){
						ATMDocument doc=documentSet.get(Integer.parseInt(token.trim()));
						if(doc!=null){
							doc.Subjects.addAll(keywords);
						}
					}
					else{
						int spaceIndex=token.lastIndexOf(' ');
						if(spaceIndex<0){
							keywords.add(token.trim().toLowerCase());
						}
						else{
							String lastSubToken=token.substring(spaceIndex).trim();
							if(NumberUtils.IsInteger(lastSubToken)){
								isIndex=true;
								keywords.add(token.substring(0,spaceIndex).trim().toLowerCase());
								ATMDocument doc=documentSet.get(Integer.parseInt(lastSubToken.trim()));
								if(doc!=null){
									doc.Subjects.addAll(keywords);
								}
							}
						}
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
	
	
	public Vocabulary LoadWords(){
		Vocabulary vvv=new Vocabulary();
		File file=new File("words.txt");
		StopwordsFilter filter=StopwordsFilter.GetInstance();
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while(true){
				String line=reader.readLine();
				if(line==null){
					break;
				}
				line=line.trim();
				if(IS_STEM_WORD){
					line=Stemmer.Stem(line);
				}
				
				if(!filter.IsStopword(line))
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
	private void LoadAuthorDoc(AuthorSet authorSet,HashMap<Integer,ATMDocument> docGloIndexMap){
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
					ATMDocument doc=docGloIndexMap.get(docIndex);
					if(doc!=null){
						Author author=authorSet.GetAuthor(authorIndex);
						if(author!=null){
							doc.Authors.add(author);
						}
						else{
							if(SHOW_DEBUG_INFO){
								System.err.println("Can't find Author :"+authorIndex);
							}
						}
					}
					else{
						if(SHOW_DEBUG_INFO){
							System.err.println("Can't find Doc :"+docIndex);
						}
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
	private void LoadGlobalTitles(HashMap<String, ATMDocument> documentSet){
		File file=new File("titles.txt");
		int index=0;
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while(true){
				String line=reader.readLine();
				if(line==null){
					break;
				}
				String title=line;
				ATMDocument doc=documentSet.get(title);
				if(doc!=null){
					doc.GlobalIndex=index;
				}
				else{
		//			System.err.println("Can't find doc:" +index);
				}
				index++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void LoadDocTities(String authorPath,HashMap<Integer, ATMDocument> documentSet){
		File file=new File(authorPath);
		try {
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while(true){
				String line=reader.readLine();
				if(line==null){
					break;
				}
				String title=line;
				reader.readLine();
				line=reader.readLine();
				int index=Integer.parseInt(line.trim());
				ATMDocument doc=documentSet.get(index);
				if(doc!=null){
					doc.Title=title;
				}
				reader.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private ATMDocument LoadDocument(File paper){
		ATMDocument result=null;
		try {
			
			BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(paper)));
			StringBuffer sb=new StringBuffer();
			while(true){
				String line=reader.readLine();
				if(line==null)
					break;
				sb.append(line);		
			}
			String content=sb.toString();
//			content=content.replace("- ", "");
			int temp1=content.toLowerCase().indexOf("abstract");
			int temp2=content.toLowerCase().lastIndexOf("references");
			if(temp1<0||temp2<0||temp2<=temp1){
				return null;
			}
//			String abs=content.substring(temp1+8,temp2).trim();
			
			result=new ATMDocument();
			result.Contents=content.substring(temp1+8,temp2).trim().replaceAll("- ", "");;
			result.Index=Integer.parseInt(paper.getName().substring(0,paper.getName().length()-4));
			
			return result;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
