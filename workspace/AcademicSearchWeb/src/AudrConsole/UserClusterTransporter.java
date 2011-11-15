package AudrConsole;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import sys.SystemInstances;

public class UserClusterTransporter {
	
	public static void main(String args[]) {
		long cluster_start_time = System.currentTimeMillis();
		String[] HBaseID = readHBaseID(new File("./HBase/HBaseID(1).txt"));
		Map<String,String[]> clusterResult = new UserClusterTransporter().clustering(1,HBaseID);
		printClusterResult(clusterResult);
		long takenTime = System.currentTimeMillis() - cluster_start_time; //所耗时间
		System.out.println("所耗时间："+takenTime);
	}
	
	/**
	 * User调用的聚类接口
	 * @param clusteringType ：聚类方式
	 * @param HBaseID ：待聚类图像的ID集合
	 * @return ： 聚类结果，<key,value>为<类名，该类所有图像的HBaseID集合>
	 */
	public Map<String,String[]> clustering(int clusteringType,String[] HBaseID){
		clusterThread thread=null;
		try {
			Socket client=new Socket(SystemInstances.HQ_IP,SystemInstances.HQUserClusterServPort);
			thread=new clusterThread(clusteringType,HBaseID,client);
			thread.start();
			thread.join();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return thread.clusterResult;
	}
	
	
	/**
	 * 聚类线程
	 * @author luojunwu
	 */
	class clusterThread extends Thread{
		private int clusteringType; //聚类方式
		private String[] HBaseID; //待聚类的图像数据ID
		private Socket client;
		public  Map<String,String[]> clusterResult; //聚类后的返回结果
		
		public clusterThread(int clusteringType,String[] HBaseID,Socket socket) {
			// TODO Auto-generated constructor stub
			this.clusteringType = clusteringType;
			this.HBaseID = HBaseID;
			this.client=socket;
			clusterResult = new HashMap<String,String[]>();
		}
		
		@Override
		public void run(){
			// TODO Auto-generated method stub
			try {
				//写clusteringType和HBaseID
				DataOutputStream dos=new DataOutputStream(client.getOutputStream());
				dos.writeInt(clusteringType);
				int len = HBaseID.length;
				dos.writeInt(len);
				for(int i=0;i<len;++i){
					dos.writeUTF(HBaseID[i]);
				}
				dos.flush();
				
				//等待总控端返回聚类结果，并读取
				DataInputStream dis=new DataInputStream(client.getInputStream());
				int numOfClass = dis.readInt(); //类别总数
				//依次读取各类的结果
				for(int i=0;i<numOfClass;i++) {
					String key = dis.readUTF(); //类名
					int numOfData = dis.readInt(); //该类数据总数
					String[] value = new String[numOfData]; //HBaseID集合
					for(int j=0;j<numOfData;j++) {
						value[j] = dis.readUTF();
					}
					clusterResult.put(key, value);
				}
				dos.close();
				dis.close();
				client.close();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public static String[] readHBaseID(File conf){
		try {
			if(!conf.exists()||conf.isDirectory())
		            throw new FileNotFoundException();
			 BufferedReader br = new BufferedReader(new FileReader(conf));
			 
			 int len = Integer.parseInt(br.readLine().trim());
			 String[] HBaseID =  new String[len];
			 
			 String temp = br.readLine();
			 for(int i=0;i<len;i++) {
				 HBaseID[i] = temp.trim();
				 temp=br.readLine(); 
			 }
			 return HBaseID;
		}
		catch(IOException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static void printClusterResult(Map<String,String[]> cr) {
		Iterator<String> iterator = cr.keySet().iterator();            
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			System.out.println(key+":");
			String[] value = cr.get(key);
			for(int i=0;i<value.length;i++)
				System.out.println(value[i]);
			System.out.println();
		}     
	}
}
