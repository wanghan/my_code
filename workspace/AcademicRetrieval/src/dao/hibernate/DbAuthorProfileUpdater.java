/**
 * 
 */
package dao.hibernate;

import java.io.File;
import java.util.Scanner;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.Session;

import tools.crawler.AuthorProfile;

/**
 * @author wanghan
 *
 */
public class DbAuthorProfileUpdater {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DbAuthorProfileUpdater.update();
	}

	
	private static String save_path="E:/author_profile/";
	private static String temp_path="E:/author_temp/";
	
	
	public static void update(){
		try {
			
			System.out.println("run");
			Scanner reader=new Scanner(new File("./aaa1.txt"));
			Session session =  HibernateUtil.getSessionFactory().getCurrentSession(); 
			session.beginTransaction();  
			int k=0;
			while(reader.hasNext()){
				String line=reader.nextLine();
				int index=line.indexOf("\t");
				String id=line.substring(0,index);
				String name=line.substring(index+1);
				String outPath=save_path+id+".xml";
				File file=new File(outPath);
				try {
					if(file.exists()){
						SAXReader saxreader=new SAXReader();
						saxreader.setEncoding("GBK"); 
						Document xmldoc=saxreader.read(file);
						Element root1=xmldoc.getRootElement();
						AuthorProfile profile=AuthorProfile.xmlToInstance(root1);
						
						DbAuthor t = (DbAuthor) session.get(DbAuthor.class, Long.parseLong(id));  
				        if(profile.getAddress()!=null){
				        	t.setAddress(profile.getAddress());
				        }
				        if(profile.getAffiliation()!=null){
				        	t.setAffiliation(profile.getAffiliation());
				        }
				        
				        if(profile.getHomepage()!=null){
				        	t.setHomepage(profile.getHomepage());
				        }
				        
				        if(profile.getInterest()!=null){
				        	t.setInterest(profile.getInterest());
				        }
				        
				        if(profile.getPosition()!=null){
				        	t.setPosition(profile.getPosition());
				        }
				        session.update(t);  
					}
					else{
						System.out.println(++k);
					}
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
					System.out.println(++k+" "+id);
				}
				
			}
			session.getTransaction().commit();  
			
			reader.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
