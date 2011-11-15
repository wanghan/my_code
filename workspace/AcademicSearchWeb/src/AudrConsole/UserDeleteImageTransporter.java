package AudrConsole;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import sys.SystemInstances;


public class UserDeleteImageTransporter {

	public String deleteImage(String[] imageId)
	{
		DeleteImageThread thread=null;
		try {
			Socket client = new Socket(SystemInstances.HQ_IP,
					SystemInstances.HQUserDeleteServPort);
            thread = new DeleteImageThread(imageId, client);
			thread.start();
			thread.join();// �ȴ����߳���ֹ��
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
		String imageId[]=new String[3];
		imageId[0]="imageid001";
		imageId[1]="imageid002";
		imageId[2]="imageid003";
		new UserDeleteImageTransporter().deleteImage(null);
	}
}
//ɾ��ͼ���ļ��߳�
class DeleteImageThread extends Thread {
	 
	private Socket client;
	private String imageId[];
	public String resultMessage;

	public DeleteImageThread(String imageId[], Socket socket) {
		// TODO Auto-generated constructor stub
		this.imageId=imageId;
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
		     if(imageId==null)
		    {
		    	dos.writeInt(0);
		    	dos.flush();
		    }
		    else
		    {    
		    	int len=imageId.length;
		    	dos.writeInt(len);
			    dos.flush();
			    for(int i=0;i<len;i++)
			    {
			    	dos.writeUTF(imageId[i]);
			    }
			    dos.flush();
		    }
		    // waiting and receive the result from HQ
			this.resultMessage = dis.readUTF();
			System.out.println("the resultMessage is " + resultMessage);
			// �ر�����������Ϳͻ���
			dis.close();
			dos.close();
			client.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
