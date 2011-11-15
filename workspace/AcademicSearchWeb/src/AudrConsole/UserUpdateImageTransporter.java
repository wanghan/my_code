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


public class UserUpdateImageTransporter {

	public String updateImage(String imageId,String imagePath,Map keyValue)
	{
		UpdateImageSenderThread thread = null;
		try {
			Socket client = new Socket(SystemInstances.HQ_IP,
					SystemInstances.HQUserUpdateServPort);
            thread = new UpdateImageSenderThread(imageId,imagePath,keyValue,client);
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
		String imagePath="f:\\mingku.jpg";
		String imageId="imageId001";
		String key[]=new String[2];
		key[0]="0001k";
		key[1]="0002k";
		String values[]=new String[2];
		values[0]="bf";
		values[1]="sf";
		Map  keyValue=new HashMap();
		keyValue.put(key[0], values[0]);
		keyValue.put(key[1], values[1]);
		new UserUpdateImageTransporter().updateImage(imageId,imagePath, keyValue);
	}
}
//update image thread
class UpdateImageSenderThread extends Thread {
	private String imageId;
	private String imagePath; // 视频文件
	private Socket client;
	private Map  keyValue; //暂定..
	public String resultMessage;
    public UpdateImageSenderThread(String imageId,
			String imagePath, Map  keyValue, Socket socket) {
		// TODO Auto-generated constructor stub
		this.imageId=imageId;
		this.imagePath=imagePath;
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
			// send image id
		    dos.writeUTF(imageId);
			dos.flush();
			// transporter the image file
			
			if(imagePath.equals(""))
			{
				dos.writeUTF("nothing");
			}
			else
			{
		    File ifpath = new File(imagePath);// 获得文件路径
			String remoteOriFilepath = new FileTransporter().sendFile(
					SystemInstances.HQ_IP,
					SystemInstances.IMAGE_FILE_SERV_PORT, ifpath, //本机测试时使用VIDEO_FILE_SERV_PORT
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
				System.out.println("the key is "+key1);
				System.out.println("the value is "+value1);
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
