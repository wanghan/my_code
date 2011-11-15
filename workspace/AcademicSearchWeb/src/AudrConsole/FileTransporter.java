/**
 * 
 */
package AudrConsole;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import sys.SystemInstances;
/**
 * @author wanghan use to transport file(s)
 */
public class FileTransporter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * ����һ��·��Ψһ��Ŀ¼
	 * 
	 * @param base
	 *            ������Ŀ¼�ĸ�Ŀ¼
	 * @return ����Ŀ¼��·��
	 */
	public String generateDirPath(String base) {
		return base + "dir_" + String.valueOf(System.currentTimeMillis()) + "\\";
	}

	public String[] sendFiles(String serverIP, int serverPort, File file[],
			String tempDisPath) {
		String filepaths[] = new String[file.length];
		String filedir = generateDirPath(tempDisPath);
		for (int i = 0; i < file.length; ++i) {
			filepaths[i] = filedir + file[i].getName();
			Socket client;
			try {
				client = new Socket(serverIP, serverPort);
				FileSenderThread thread = new FileSenderThread(filepaths[i],
						file[i], client);
				thread.start();
				thread.join();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return filepaths;
	}

	/**
	 * �����ļ�����
	 * 
	 * @param serverIP
	 *            �����ļ��ķ�����IP
	 * @param serverPort
	 *            �����ļ��ķ���˿ں�
	 * @param file
	 *            ��������ļ�
	 * @param tempDisPath
	 *            ���汻�����ļ���Ŀ¼·��
	 * @return �����ļ��ڷ������˵ô洢·��
	 */

	public String sendFile(String serverIP, int serverPort, File file,
			String tempDisPath) {
		String filedir = generateDirPath(tempDisPath);
		String filepath = filedir + file.getName();

		Socket client;
		try {
			client = new Socket(serverIP, serverPort);
			FileSenderThread thread = new FileSenderThread(filepath, file,
					client);
			thread.start();
			thread.join();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return filepath;
	}
	//new ----chaojing
	public String sendFileNew(String serverIP, int serverPort, File file,
			String tempDisPath) {
		String filedir = tempDisPath+"/";// new
		String filepath = filedir + file.getName();
        Socket client;
		try {
			client = new Socket(serverIP, serverPort);
			FileSenderThread thread = new FileSenderThread(filepath, file,
					client);
			thread.start();
			thread.join();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return filepath;
	}
	//new ----chaojing
	public String[] sendFilesNew(String serverIP, int serverPort, File file[],
			String tempDisPath) {
		String filepaths[] = new String[file.length];
		String filedir = tempDisPath+"/";//new
		for (int i = 0; i < file.length; ++i) {
			filepaths[i] = filedir + file[i].getName();
			Socket client;
			try {
				client = new Socket(serverIP, serverPort);
				FileSenderThread thread = new FileSenderThread(filepaths[i],
						file[i], client);
				thread.start();
				thread.join();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return filepaths;
	}
	/**
	 * ����socket�����ļ����߳�
	 * 
	 * @author wanghan
	 * 
	 */
	class FileSenderThread extends Thread {
		private String filePath;
		private File file;
		private Socket client;

		public FileSenderThread(String filePath, File file, Socket socket) {
			// TODO Auto-generated constructor stub
			this.filePath = filePath;
			this.file = file;
			this.client = socket;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				DataOutputStream dos = new DataOutputStream(client
						.getOutputStream());
				DataInputStream dis = new DataInputStream(client
						.getInputStream());
				// send the path where the file be stored at the server
				dos.writeUTF(filePath);
				dos.writeLong(file.length());
				// send file stream
				int flag = 1;
				byte[] buf = new byte[SystemInstances.BUFFERSIZE];
				FileInputStream fis = new FileInputStream(file);
				while (flag != -1) {
					flag = fis.read(buf);
					if (flag != -1) {
						dos.write(buf, 0, flag);
						dos.flush();
					}

				}
				fis.close();
                dos.close();
				dis.close();
                client.close();

			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
