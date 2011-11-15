/**
 * 
 */
package AudrConsole;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import sys.RebuildQueryResult;
import sys.SystemInstances;

import AudrConsole.UserTransporter.QuerySenderThread;

/**
 * @author huanglei
 *
 */
public class DeicTransporter {
	private Socket socket=null;
	
	public DeicTransporter() {
		// TODO Auto-generated constructor stub
		try {
		
			socket=new Socket(sys.SystemInstances.DEIC_IP,sys.SystemInstances.DEICServPort);
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int userSendImageInfo(File url, String imageId,String filePath,String imageCategory,String imageKeywords,String webDir,String imageDescription){
		InfoSenderThread thread=null;
		try {		
			thread=new InfoSenderThread(url,imageId,filePath,imageCategory,imageKeywords,webDir,imageDescription,socket.getInputStream(),socket.getOutputStream());
			thread.start();
			thread.join();
			
			
		}catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return thread.succsessFlag;
	}
	class InfoSenderThread extends Thread{
		private File url;
	//	private String type;
	//	private String[] basicfeature;
	//	private String[] semanticfeature;
	//	private QueryResult result;
		private String imageId;
		private String filePath;
		private String imageCategory;
		private String imageKeywords;
		private String imageDescription;
		private InputStream in;
		private OutputStream out;
		private int succsessFlag=0;
		public InfoSenderThread(File url, String imageId,String filePath,String imageCategory,String imageKeywords,String webDir,String imageDescription,InputStream in,OutputStream out) {
			// TODO Auto-generated constructor stub
		//	this.basicfeature=basicfeature;
		//	this.semanticfeature=semanticfeature;
			this.url=url;
			this.imageId=imageId;
			this.imageCategory=imageCategory;
			this.filePath=filePath;
			this.imageKeywords=imageKeywords;
			this.imageDescription=imageDescription;
			this.in=in;
			this.out=out;
		}
		private String generateImageName(String filepath){
			 String[] ft=filePath.split("/");
			 return ft[ft.length-1];
		}
		@Override	
		public void run(){
			// TODO Auto-generated method stub
			try {
				DataOutputStream dos=new DataOutputStream(out);
				//send query type
			//	dos.writeInt(sys.SystemInstances.QUERY_NORMAL);
				//Querybylire send 1 first 
				if(url==null){
					dos.writeInt(0);
					dos.flush();
				}
				else{
					dos.writeInt(1);
					dos.flush();
					//String filepath=new FileTransporter().sendFile(sys.SystemInstances.DEIC_IP,sys.SystemInstances.FILE_SERV_PORT,url,sys.SystemInstances.HQ_USER_FILE_CACHE_DIR);
					//dos.writeUTF(filepath);
					String fileName=this.generateImageName(filePath);
					dos.writeUTF(fileName);
					//dos.writeUTF(type);
					dos.writeUTF(imageId);
					dos.writeUTF(imageCategory);
					dos.writeUTF(imageKeywords);
					dos.writeUTF(imageDescription);
					dos.writeLong(url.length());
					dos.flush();
					int flag=1;
					byte [] buf=new byte[sys.SystemInstances.BUFFERSIZE];
					FileInputStream fis=new FileInputStream(url);
					while(flag!=-1){
						flag=fis.read(buf);
						if(flag!=-1){
							dos.write(buf,0,flag);
							dos.flush();
						}
						
					}
					fis.close();
					
					dos.flush();
					this.succsessFlag=1;//图像发送完后成功标志，这样就能够保证在图像发给DEIC服务器后再进行页面跳转
				}
		
				
				dos.close();
			} 
			catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}

