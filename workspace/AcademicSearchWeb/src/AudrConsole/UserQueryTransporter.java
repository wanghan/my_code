/**
 * 
 */
package AudrConsole;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import AudrConsole.FileTransporter;

import sys.RebuildQueryResult;
import sys.SystemInstances;

/**
 * @author wanghan
 * 
 */
public class UserQueryTransporter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url[] = {
				"http://www.google.cn/search?hl=zh-CN&client=aff-cs-maxthon&newwindow=1&q=java+%E5%BD%93%E5%89%8D%E8%BF%90%E8%A1%8C%E6%97%B6%E9%97%B4+%E6%AF%AB%E7%A7%92&aq=f&oq=",
				"http://www.xker.com/page/e2008/0424/52309.html",
				"http://www.patent-cn.com/2007jun/CN1975680.shtml" };

		String[] basicfeature = { "111.jpg", "222.jpg", "333.jpg" };
		File f1 = new File("./Chocolate Cake.bmp");
		File f2 = new File("./Water lilies.jpg");
		File f3 = new File("./Da Vinci.bmp");
		File f4 = new File("./Dragon.bmp");

		File files[] = { f1, f2, f3 };

		int j = 0;
		for (int i = 0; i < 5; ++i) {
			UserQueryTransporter tt = new UserQueryTransporter();
			RebuildQueryResult re = tt.QuerybylireID(url, "");
			System.out.println(new File(basicfeature[0]).exists());
			// String re=new UserTransporter().sendInsertInfo(files,
			// basicfeature, f4);

			// String re=new UserTransporter().sendInsertInfo(url, basicfeature,
			// f1);
			// System.out.println(re);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private Socket socket = null;
	private UserTransporter transporter = null;
	private long fullfileLength[] = null;
	private String fullFilePaths[] = null;
	private String fullFileNames[] = null;
	// private String userPath[]=null;//�û����ݹ�����Ŀ¼
	private String userPath = null;

	public UserQueryTransporter() {
		// TODO Auto-generated constructor stub
		try {
			transporter = new UserTransporter();
			socket = new Socket(sys.SystemInstances.HQ_IP,
					sys.SystemInstances.HQUserQueryServPort);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�Ҳ�������");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("IO���󣬿����ǵײ����δ����");
		}
	}

	public RebuildQueryResult Querybylire(File url, String type,
			String imageType, String userPath) {
		RebuildQueryResult re = transporter.QueryByAll(url, type, null, null,
				imageType, userPath, socket);
		
		if(re==null){
			return null;
		}
		
		this.fullfileLength = re.getFullFileLength();
		this.fullFileNames = re.getFullFileNames();

		this.fullFilePaths = new String[re.getFullFile().length];
		// �����û����ݹ�����Ŀ¼
		if (this.userPath == null)
			this.userPath = userPath;
		for (int i = 0; i < re.getFullFile().length; ++i) {
			this.fullFilePaths[i] = userPath
					+ re.getFullFile()[i].getAbsolutePath();
		}
		return re;
	}

	public RebuildQueryResult Querybyexist(String[] basicfeature,
			String[] semanticfeature, String imageType, String userPath) {
		RebuildQueryResult re = transporter.QueryByAll(null, null,
				basicfeature, semanticfeature, imageType, userPath, socket);
		
		if(re==null){
			return null;
		}
		
		this.fullfileLength = re.getFullFileLength();
		this.fullFileNames = re.getFullFileNames();
		this.fullFilePaths = new String[re.getFullFile().length];
		// �����û����ݹ�����Ŀ¼
		if (this.userPath == null)
			this.userPath = userPath;
		for (int i = 0; i < re.getFullFile().length; ++i) {
			this.fullFilePaths[i] = userPath
					+ re.getFullFile()[i].getAbsolutePath();
		}
		return re;
	}

	public RebuildQueryResult QueryByAll(File url, String type,
			String[] basicfeature, String[] semanticfeature, String imageType,
			String userPath) {
		RebuildQueryResult re = transporter.QueryByAll(url, type, basicfeature,
				semanticfeature, imageType, userPath, socket);
		
		if(re==null){
			return null;
		}
		
		this.fullfileLength = re.getFullFileLength();
		this.fullFileNames = re.getFullFileNames();
		this.fullFilePaths = new String[re.getFullFile().length];
		// �����û����ݹ�����Ŀ¼
		if (this.userPath == null)
			this.userPath = userPath;

		for (int i = 0; i < re.getFullFile().length; ++i) {
			this.fullFilePaths[i] = userPath
					+ re.getFullFile()[i].getAbsolutePath();
		}
		return re;
	}

	public String[] getFullFiles() {

		return transporter.getFullFiles(fullFilePaths, fullFileNames,
				fullfileLength, userPath, socket);
	}

	public RebuildQueryResult QuerybylireID(String[] lireID, String userPath) {
		return transporter.QuerybylireID(lireID, userPath, socket);
	}
}

class UserTransporter {

	public String[] getFullFiles(String[] filepaths, String[] filenames,
			long filelength[], String userPath, Socket socket) {
		GetFullFilesThread thread = null;
		try {
			thread = new GetFullFilesThread(filepaths, filenames, filelength,
					userPath, socket.getInputStream(), socket.getOutputStream());
			thread.start();
			thread.join();

		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thread.result;
	}

	public RebuildQueryResult QuerybylireID(String[] lireID, String userPath,
			Socket socket) {
		QueryByLireIDThread thread = null;
		try {
			thread = new QueryByLireIDThread(lireID, userPath,
					socket.getInputStream(), socket.getOutputStream());
			thread.start();
			thread.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thread.result;

	}

	public RebuildQueryResult QueryByAll(File url, String type,
			String[] basicfeature, String[] semanticfeature, String imageType,
			String userPath, Socket client) {

		QuerySenderThread thread = null;
		try {
			thread = new QuerySenderThread(url, type, basicfeature,
					semanticfeature, imageType, userPath,
					client.getInputStream(), client.getOutputStream());
			thread.start();
			thread.join();

		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(thread.result);
		return thread.result;
	}

	/**
	 * ���ԭͼ�Ŀͻ����߳�
	 * 
	 * @author wanghan
	 * 
	 */
	class GetFullFilesThread extends Thread {
		private String[] filepath;
		private InputStream in;
		private OutputStream out;
		private String result[];
		private long[] filelength;
		private String[] filenames;
		private String userPath;

		public GetFullFilesThread(String[] paths, String[] names,
				long filelength[], String userPath, InputStream in,
				OutputStream out) {
			// TODO Auto-generated constructor stub
			this.filepath = paths;
			this.in = in;
			this.out = out;
			this.filenames = names;
			this.filelength = filelength;
			this.userPath = userPath;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// send signal
			try {

				DataOutputStream dos = new DataOutputStream(out);
				dos.writeInt(1);
				// dos.writeInt(filepath.length);
				// for(int i=0;i<filepath.length;++i){
				// dos.writeUTF(filepath[i]);
				// dos.flush();
				// }

				DataInputStream dis = new DataInputStream(in);

				result = new String[filepath.length];
				String filedir = userPath
						+ new FileTransporter()
								.generateDirPath(SystemInstances.USER_FILE_CACHE_DIR);
				// String filedir=new
				// FileTransporter().generateDirPath(SystemInstances.USER_FILE_CACHE_DIR);
				filedir = filedir.replaceAll("/", "\\\\");
				System.out.println("userPath:" + userPath);
				System.out.println("filedir===" + filedir);
				for (int i = 0; i < filepath.length; ++i) {
					result[i] = filedir + filenames[i];
					long file_length = filelength[i];
					File ftemp = new File(result[i]);
					if (!ftemp.exists()) {
						ftemp.getParentFile().mkdirs();
					}
					FileOutputStream fos = new FileOutputStream(ftemp);
					byte[] buf = new byte[sys.SystemInstances.BUFFERSIZE];
					long remains = file_length;
					while (remains != 0) {
						if (remains <= sys.SystemInstances.BUFFERSIZE) {
							int readlen = dis.read(buf, 0, (int) remains);
							if (readlen == -1)
								continue;
							fos.write(buf, 0, readlen);
							fos.flush();
							remains -= readlen;
						} else {
							int readlen = dis.read(buf);
							if (readlen == -1)
								continue;
							fos.write(buf, 0, readlen);
							fos.flush();
							remains -= readlen;

						}
					}
					fos.close();
				}

				dis.close();
				dos.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			}
		}
	}

	class QueryByLireIDThread extends Thread {
		private String[] lireID;
		private String userPath;
		private InputStream in;
		private OutputStream out;
		private RebuildQueryResult result;

		public QueryByLireIDThread(String[] lireID, String userPath,
				InputStream in, OutputStream out) {
			// TODO Auto-generated constructor stub
			this.lireID = lireID;
			this.userPath = userPath;
			this.in = in;
			this.out = out;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// send signal
			try {

				DataOutputStream dos = new DataOutputStream(out);

				// send query type
				dos.writeInt(SystemInstances.QUERY_BY_LIREID);
				dos.writeInt(lireID.length);
				for (int i = 0; i < lireID.length; ++i) {
					dos.writeUTF(lireID[i]);
					dos.flush();
				}
				dos.writeUTF(userPath); // /�޸ĵĲ���..
				dos.flush();
				ObjectInputStream ois = new ObjectInputStream(in);
				DataInputStream dis = new DataInputStream(in);
				int flag = dis.readInt();
				if (flag != 0) {
					result = (RebuildQueryResult) ois.readObject();

					// receive small images
					int len3 = result.getImage().length;

					String filedir = userPath
							+ new FileTransporter()
									.generateDirPath(SystemInstances.USER_FILE_CACHE_DIR);
					for (int i = 0; i < len3; ++i) {
						String filepath = filedir
								+ result.getImage()[i].getName();
						long file_length = result.getSmallFileLength()[i];
						result.getImage()[i] = new File(filepath);
						if (!result.getImage()[i].getParentFile().exists()) {
							result.getImage()[i].getParentFile().mkdirs();
						}
						FileOutputStream fos = new FileOutputStream(
								result.getImage()[i]);
						byte[] buf = new byte[sys.SystemInstances.BUFFERSIZE];
						long remains = file_length;
						while (remains != 0) {
							if (remains <= sys.SystemInstances.BUFFERSIZE) {
								int readlen = dis.read(buf, 0, (int) remains);
								if (readlen == -1)
									continue;
								fos.write(buf, 0, readlen);
								fos.flush();
								remains -= readlen;
							} else {
								int readlen = dis.read(buf);
								if (readlen == -1)
									continue;
								fos.write(buf, 0, readlen);
								fos.flush();
								remains -= readlen;

							}
						}
						fos.close();
					}
					// receive full files.
					len3 = result.getFullFile().length;
					for (int i = 0; i < len3; ++i) {
						String filepath = filedir
								+ result.getFullFile()[i].getName();
						long file_length = result.getFullFileLength()[i];
						result.getFullFile()[i] = new File(filepath);
						if (!result.getFullFile()[i].getParentFile().exists()) {
							result.getFullFile()[i].getParentFile().mkdirs();
						}
						FileOutputStream fos = new FileOutputStream(
								result.getFullFile()[i]);
						byte[] buf = new byte[sys.SystemInstances.BUFFERSIZE];
						long remains = file_length;
						while (remains != 0) {
							if (remains <= sys.SystemInstances.BUFFERSIZE) {
								int readlen = dis.read(buf, 0, (int) remains);
								if (readlen == -1)
									continue;
								fos.write(buf, 0, readlen);
								fos.flush();
								remains -= readlen;
							} else {
								int readlen = dis.read(buf);
								if (readlen == -1)
									continue;
								fos.write(buf, 0, readlen);
								fos.flush();
								remains -= readlen;

							}
						}
						fos.close();
					}

					dis.close();
					dos.close();
				} else
					this.result = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			}
		}
	}

	/**
	 * thread used for sending the insert info the HQ
	 * 
	 * @author wanghan
	 * 
	 */
	class QuerySenderThread extends Thread {
		private File url;
		private String type;
		private String[] basicfeature;
		private String[] semanticfeature;
		private RebuildQueryResult result;
		private String imageType;
		private String userPath;
		private InputStream in;
		private OutputStream out;

		public QuerySenderThread(File url, String type, String[] basicfeature,
				String[] semanticfeature, String imageType, String userPath,
				InputStream in, OutputStream out) {
			// TODO Auto-generated constructor stub
			this.basicfeature = basicfeature;
			this.semanticfeature = semanticfeature;
			this.url = url;
			this.type = type;
			this.imageType = imageType;
			this.userPath = userPath;
			this.in = in;
			this.out = out;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				DataOutputStream dos = new DataOutputStream(out);
				// send query type
				dos.writeInt(sys.SystemInstances.QUERY_NORMAL);
				// Querybylire send 1 first
				if (url == null) {
					dos.writeInt(0);
					dos.flush();
				} else {
					dos.writeInt(1);
					dos.flush();
					String filepath = new FileTransporter().sendFile(
							sys.SystemInstances.HQ_IP,
							sys.SystemInstances.IMAGE_FILE_SERV_PORT, url,
							sys.SystemInstances.HQ_USER_FILE_CACHE_DIR);
					dos.writeUTF(filepath);
					dos.writeUTF(type);
				}

				if (basicfeature == null) {
					dos.writeInt(2);
					dos.flush();
				} else {
					dos.writeInt(3);
					dos.flush();
					// send length first
					int len1 = basicfeature.length;
					dos.writeInt(len1);
					dos.flush();
					for (int i = 0; i < len1; ++i) {
						dos.writeUTF(basicfeature[i]);
						dos.flush();
					}

					// send length first
					int len2 = semanticfeature.length;
					dos.writeInt(len2);
					dos.flush();
					for (int i = 0; i < len2; ++i) {
						dos.writeUTF(semanticfeature[i]);
						dos.flush();
					}
				}

				dos.writeUTF(imageType);
				dos.writeUTF(userPath);
				// transport query result to the users

				ObjectInputStream ois = new ObjectInputStream(in);
				DataInputStream dis = new DataInputStream(in);
				int flag = dis.readInt();
				if (flag != 0) {
					result = (RebuildQueryResult) ois.readObject();
					int len3 = result.getImage().length;

					String filedir = userPath
							+ new FileTransporter()
									.generateDirPath(SystemInstances.USER_FILE_CACHE_DIR);// ///�޸ĵ�Ŀ¼
					for (int i = 0; i < len3; ++i) {
						String filepath = filedir
								+ result.getImage()[i].getName();
						long file_length = result.getSmallFileLength()[i];
						result.getImage()[i] = new File(filepath);
						if (!result.getImage()[i].getParentFile().exists()) {
							result.getImage()[i].getParentFile().mkdirs();
						}
						FileOutputStream fos = new FileOutputStream(
								result.getImage()[i]);
						byte[] buf = new byte[sys.SystemInstances.BUFFERSIZE];
						long remains = file_length;
						while (remains != 0) {
							if (remains <= sys.SystemInstances.BUFFERSIZE) {
								int readlen = dis.read(buf, 0, (int) remains);
								if (readlen == -1)
									continue;
								fos.write(buf, 0, readlen);
								fos.flush();
								remains -= readlen;
							} else {
								int readlen = dis.read(buf);
								if (readlen == -1)
									continue;
								fos.write(buf, 0, readlen);
								fos.flush();
								remains -= readlen;

							}
						}
						fos.close();
					}
				}else
					this.result=null;
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
			}
		}
	}

}
