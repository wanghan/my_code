/**
 * 
 */
package sys;

import java.io.File;
import java.io.Serializable;

/**
 * @author wanghan
 *
 */
public class RebuildQueryResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2283886750431451407L;
	private File image[];  //����ͼ
	private String lireID[];  //lire ID
	private long smallfileLength[];   //����ͼ����
	
	private long fullfileLength[];    //ԭͼ����
	private File fullFile[]=null;   //ԭͼ
	private String fullFileNames[]=null;   //ԭͼ����
	
	private String userPath[]=null; //�û�·��
	
	private String[] bf;
	private String[] sf;
	/**
	 * 
	 * @param image ����ͼ
	 * @param lireID
	 */
	public RebuildQueryResult(File[] image,String[] lireID) {
		// TODO Auto-generated constructor stub
		this.image=image;
		this.lireID=lireID;
		this.fullFile=null;
		this.fullfileLength=null;
		smallfileLength=new long[image.length];
		for(int i=0;i<image.length;++i){
			smallfileLength[i]=image[i].length();
		}
	}
	/**
	 * 
	 * @param image ����ͼ
	 * @param fullimage ԭͼ
	 * @param lireID ԭͼ·��
	 */
	public RebuildQueryResult(File[] image,File[] fullimage,String[] lireID){
		this.image=image;
		this.lireID=lireID;
		this.fullFile=fullimage;
		smallfileLength=new long[image.length];
		this.fullfileLength=new long[fullimage.length];
		this.fullFileNames=new String[fullimage.length];
		this.bf=null;
		this.sf=null;
		for(int i=0;i<image.length;++i){
			smallfileLength[i]=image[i].length();
		}
		for(int i=0;i<fullimage.length;++i){
			if(fullimage[i].exists()){
				fullfileLength[i]=fullimage[i].length();
				fullFileNames[i]=fullimage[i].getName();
			}
			else{
				fullfileLength[i]=0;
				fullFileNames[i]=SystemInstances.NULL_FILE_NAME;
			}
			
		}
	}
	/**
	 * Invoke by HQ
	 * @param image ����ͼ
	 * @param lireID
	 * @param filePath ԭͼ·��
	 */
	public RebuildQueryResult(File[] image,String[] lireID,String[] filePath,String bf[], String []sf) {
		// TODO Auto-generated constructor stub
		this.image=image;
		this.lireID=lireID;
		smallfileLength=new long[image.length];
		this.fullfileLength=new long[filePath.length];
		this.fullFileNames=new String[filePath.length];
		this.fullFile=new File[filePath.length];
		this.bf=bf;
		this.sf=sf;
		
		
		for(int i=0;i<image.length;++i){
			smallfileLength[i]=image[i].length();
		}
		for(int i=0;i<filePath.length;++i){
			File ftemp=new File(filePath[i]);
			if(ftemp.exists()){
				this.fullFile[i]=ftemp;
				fullfileLength[i]=ftemp.length();
				fullFileNames[i]=ftemp.getName();
			}
			else{
				this.fullFile[i]=null;
				fullfileLength[i]=0;
				fullFileNames[i]=SystemInstances.NULL_FILE_NAME;
			}
			
		}
	}
	public long[] getSmallFileLength() {
		return smallfileLength;
	}
	public File[] getImage() {
		return image;
	}
	public String[] getLireID() {
		return lireID;
	}
	
	public File[] getFullFile() {
		return fullFile;
	}
	public long[] getFullFileLength() {
		return fullfileLength;
	}
	public String[] getFullFileNames() {
		return fullFileNames;
	}
	public String[] getBf() {
		return bf;
	}
	public String[] getSf() {
		return sf;
	}
	public String[] getUserPath() {
		return userPath;
	}
	public void setUserPath(String[] userPath) {
		this.userPath = userPath;
	}
	
}
