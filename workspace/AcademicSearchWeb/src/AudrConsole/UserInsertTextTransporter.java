package AudrConsole;
/*
 * 文本插入链路
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class UserInsertTextTransporter {
	/**
	 * 
	 * @param url original File原始文件的路径
	 * @param basicfeature bf File 一个原始文件对应一个基本属性 String
	 * @param semanticfeature sf File所有的原始文件对应着一个文件的路径
	 * @param lfFilePath lf File 每个底层特征文件对应一个的路径
	 * @param textType
	 * @return
	 */
	public String receiveInsertInfo(String[] url, String[] basicfeature,
			String semanticfeaturepath, String[] lfFilePath, String textType) {
		InsertSenderThread thread = null;
		try {
			Socket client = new Socket(sys.SystemInstances.HQ_IP,
					sys.SystemInstances.HQLuceneInsertServPort);
			File sfFile = new File(semanticfeaturepath);// /File 类型
			String filepath = new FileTransporter().sendFile(      //传送语义文件                
					sys.SystemInstances.HQ_IP,
					sys.SystemInstances.TEXT_FILE_SERV_PORT, sfFile,
					sys.SystemInstances.HQ_USER_FILE_CACHE_DIR); 
			thread = new InsertSenderThread(url, basicfeature, filepath,
					lfFilePath, textType, client);
			thread.start();
			thread.join();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thread.resultMessage;
	}

	/*
	 * main function for testing
	 */
	public static void main(String args[]) {
		String url[] = new String[2];
		url[0] = "D:\\Data\\20\\2010031817075212689032722034.xml";
		url[1] = "D:\\Data\\20\\2010031817075212689032722185.xml";
		String basicfeatur[] = new String[2];
		basicfeatur[0] = "www";
		basicfeatur[1] = "mmm";
		String sfeature = "D:\\Data\\20\\2010031817075212689032721873.xml";
		String lfilepath[] = new String[2];
		lfilepath[0] = "D:\\Data\\20\\2010031817075212689032721401.xml";
		lfilepath[1] = "D:\\Data\\20\\2010031817075212689032721872.xml";
		new UserInsertTextTransporter().receiveInsertInfo(url, basicfeatur,
				sfeature, lfilepath, "");
	}

}

class InsertSenderThread extends Thread {
	private String[] url;
	private String[] basicfeature;
	private String semanticfeature;
	private Socket client;
	private String[] lfFilePath;
	private String textType;
	public String resultMessage;

	public InsertSenderThread(String[] url, String[] basicfeature,
			String filePath, String[] lfFilePath, String textType, Socket socket) {
		// TODO Auto-generated constructor stub
		this.basicfeature = basicfeature;
		this.semanticfeature = filePath;
		this.url = url;
		this.lfFilePath = lfFilePath;
		this.textType = textType;
		this.client = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			DataOutputStream dos = new DataOutputStream(client
					.getOutputStream());
			DataInputStream dis=new DataInputStream(client.getInputStream());
			// URL的类型可以商讨

			int len2 = basicfeature.length;
			dos.writeInt(len2);
			dos.flush();
			for (int k = 0; k < len2; ++k) {
				dos.writeUTF(basicfeature[k]);
				dos.flush();
			}
			//传送语义文件的路径,在上面的文件传送中,已经把语义文件传送过去了
			dos.writeUTF(semanticfeature);
			dos.flush();
			
			// /传输原始文件...
			
			File url2[] = new File[url.length];

			for (int i = 0; i < url2.length; i++) {
				url2[i] = new File(url[i]);
			}
			//传送所有的原始文本文件
			String remoteOriFilepaths[] = new FileTransporter().sendFiles(
					sys.SystemInstances.HQ_IP,
					sys.SystemInstances.TEXT_FILE_SERV_PORT, url2,
					sys.SystemInstances.HQ_USER_FILE_CACHE_DIR);
			
			int len1 = remoteOriFilepaths.length;
			dos.writeInt(len1);
			dos.flush();
			
			for (int i = 0; i < len1; ++i) {
				dos.writeUTF(remoteOriFilepaths[i]);
				dos.flush();
			}
			// 传输底层特征文件..
			File file1[] = new File[lfFilePath.length];
			for (int j = 0; j < file1.length; j++) {
				file1[j] = new File(lfFilePath[j]);
			}
			String filepaths1[] = new FileTransporter().sendFiles(
					sys.SystemInstances.HQ_IP,
					sys.SystemInstances.TEXT_FILE_SERV_PORT, file1,
					sys.SystemInstances.HQ_USER_FILE_CACHE_DIR);
			int len3 = filepaths1.length;
			dos.writeInt(len3);
			dos.flush();
			for (int j = 0; j < len3; j++) {
				dos.writeUTF(filepaths1[j]);
				dos.flush();
			}
			dos.writeUTF(textType);
			dos.flush();
			// waiting and receive the result

			
			this.resultMessage = dis.readUTF();
			System.out.println(resultMessage + "the resultMessage is "
					+ resultMessage);
			
			dis.close();
			dos.close();
			client.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
