package AudrConsole;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import sys.SystemInstances;


public class UserDeleteTextTransporter {
	public String deleteText(String[] textId)
	{
		DeleteTextThread thread=null;
		try {
			Socket client = new Socket(SystemInstances.HQ_IP,
					SystemInstances.HQLuceneDeleteServPort);
            thread = new DeleteTextThread(textId, client);
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
    // for test
	public static void main(String[] args) {
		String textId[]=new String[3];
		textId[0]="text001";
		textId[1]="text002";
		textId[2]="text003";
		new UserDeleteTextTransporter().deleteText(null);
	}

}
//删除文本文件线程
class DeleteTextThread extends Thread {
	 
	private Socket client;
	private String textId[];
	public String resultMessage;

	public DeleteTextThread(String textId[], Socket socket) {
		// TODO Auto-generated constructor stub
		this.textId=textId;
		this.client = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			DataOutputStream dos = new DataOutputStream(client
					.getOutputStream());
			DataInputStream dis = new DataInputStream(client.getInputStream());
			// send text id
		     if(textId==null)
		    {
		    	dos.writeInt(0);
		    	dos.flush();
		    }
		    else
		    {    
		    	int len=textId.length;
		    	dos.writeInt(len);
			    dos.flush();
			    for(int i=0;i<len;i++)
			    {
			    	dos.writeUTF(textId[i]);
			    }
			    dos.flush();
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
