package AudrConsole;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import sys.SystemInstances;


public class UserUpdateTextTransporter {

	public String updateText(String textId,String lfFilePath,String originalFilePath,Map keyValue)
	{
		UpdateTextThread thread=null;
		try {
			Socket client = new Socket(SystemInstances.HQ_IP,
					SystemInstances.HQLuceneUpdateServPort);
			thread = new UpdateTextThread(textId,lfFilePath,originalFilePath,keyValue,client);
			thread.start();
			thread.join();// 等待该线程终止。
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thread.resultMessage;
	}
	public static void main(String[] args) {
		String textId="textId000";
		String textPath="f://shotInfo.txt";
		String lfPath="f://lf.txt";
		String key[]=new String[3];
		key[0]="11tk";
		key[1]="22tk";
		key[2]="33tk";
		String mes[]=new String[3];
		mes[0]="11";
		mes[1]="22";
		mes[2]="33";
		Map  keyValue=new HashMap();
		keyValue.put(key[0],mes[0]);
		keyValue.put(key[1],mes[1]);
		keyValue.put(key[2],mes[2]);
		new UserUpdateTextTransporter().updateText(textId,"","",null);
	 }
}
//update text thread
class UpdateTextThread extends Thread {
	private String textId;
	private String originalFilePath; // 视频文件
	private String lfFilePath; //底层文件
	private Socket client;
	private Map keyValue; //暂定..
	public String resultMessage;
    public UpdateTextThread(String textId,
			String lfFilePath,String originalFilePath, Map keyValue, Socket socket) {
		// TODO Auto-generated constructor stub
		this.textId=textId;
		this.lfFilePath=lfFilePath;
		this.originalFilePath=originalFilePath;
		this.keyValue=keyValue;
		this.client = socket;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			DataOutputStream dos = new DataOutputStream(client
					.getOutputStream());
			DataInputStream dis = new DataInputStream(client.getInputStream());
			 
			// send text id
		    dos.writeUTF(textId);
			dos.flush();
			// send lfFile ...
			File lfpath=new File(lfFilePath);
			if(lfFilePath.equals(""))
			{
				lfpath=null;
				dos.writeUTF("nothing");
				dos.flush();
			}
			else
			{
				String remotelfFilepath = new FileTransporter().sendFile(
						SystemInstances.HQ_IP,
						SystemInstances.TEXT_FILE_SERV_PORT, lfpath,
						SystemInstances.HQ_USER_FILE_CACHE_DIR);
				dos.writeUTF(remotelfFilepath);
				dos.flush();
			}
			// transporter the text file
			File tfpath = new File(originalFilePath);// 获得文件路径
			//  在次假定如果文件为空，关键帧和底层文件都为空
			if(originalFilePath.equals(""))
			{
				tfpath=null;
				dos.writeUTF("nothing");
			}
			else
			{
			String remoteOriFilepath = new FileTransporter().sendFile(
					SystemInstances.HQ_IP,
					SystemInstances.TEXT_FILE_SERV_PORT, tfpath,
					SystemInstances.HQ_USER_FILE_CACHE_DIR);
			dos.writeUTF(remoteOriFilepath);
			dos.flush();
			}
			
			// send map....
			if(keyValue==null)
			{
				keyValue=new HashMap();
				 
			}
			Iterator it=keyValue.keySet().iterator(); 
			int len=keyValue.keySet().size(); //key length
			dos.writeInt(len);
			dos.flush();
			while(it.hasNext()){
				Object key=(Object)it.next();
				String key1=key.toString();
				dos.writeUTF(key1);
				dos.flush();
				Object value=keyValue.get(key);
				String value1=value.toString();
				dos.writeUTF(value1);
				dos.flush();
				//System.out.println("the key is "+key1);
				//System.out.println("the value is "+value1);
			}
			// waiting and receive the result from HQ
			this.resultMessage = dis.readUTF();
			System.out.println("the resultMessage is " + resultMessage);
			// 关闭输入输出流和客户端
			dis.close();
			dos.close();
			client.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}