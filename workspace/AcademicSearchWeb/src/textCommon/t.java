package textCommon;

import java.io.IOException;
import java.util.HashMap;

public class t {

	public t() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		 String command = "D:\\Work\\AUDRweb\\WebRoot\\sys\\pdfServerStart.bat";		//����Դ�ļ�ת��Ϊpdf�ļ�������
		 try {
			System.out.println(command);
			Process pro = Runtime.getRuntime().exec("cmd /c start "+command);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
