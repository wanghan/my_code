package textCommon;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.IOException;  
import java.io.InputStreamReader;  
  
/**
 * ���ܣ�ʵ��pdf��ʽ�ļ�ת����swf��ʽ��Ƶ�ļ�
 * ʹ�÷�����ֱ�ӵ��÷�����convertPDF2SWF(String sourcePath,String destPath)
 * ������String���͵�Դ�ļ���Ŀ���ļ�
 * ����ֵ��int���͵�ת��״̬
 * 
 * ���ߣ��Ƽ���
 * ���ڣ�2010��12��29��17:14:22
 * */  
public class PdfToSwf {  
   
   public int convertPDF2SWF(String sourcePath, String destPath ) throws IOException {  
       // Ŀ��·������������Ŀ��·��  
       File dest = new File(destPath);  
       if (!dest.exists()) {  
           dest.createNewFile();  
       }  
 
       // Դ�ļ��������򷵻�  
       File source = new File(sourcePath);  
       if (!source.exists()) {  
           return 0;  
       }  
 
       // ������flash  
       String[] envp = new String[1];  
//       envp[0] = "PATH=C:\\Program Files\\SWFTools\\";  
//       String command = "pdf2swf -z -s flashversion=9  -t \"" + sourcePath  
//               + "\" -o \"" + destPath + "\"";  
       String command = "C:\\Program Files\\SWFTools\\pdf2swf -z -s flashversion=9  -t \"" + sourcePath  
       + "\" -o \"" + destPath + "\"";  
       
       Process pro = Runtime.getRuntime().exec(command);  
       BufferedReader bufferedReader = new BufferedReader(  
               new InputStreamReader(pro.getInputStream()));  
       while (bufferedReader.readLine() != null) {  
           String text = bufferedReader.readLine();  
           System.out.println(text);  
       }  
       try {  
           pro.waitFor();  
       } catch (InterruptedException e) {  
           // TODO Auto-generated catch block  
           e.printStackTrace();  
       } 
       
       // Ȼ�����ײ�����  
      command = "C:\\Program Files\\SWFTools\\swfcombine -z -X 540 -Y 450 \"C:/Program Files/SWFTools/swfs/rfxview.swf\" viewport=\""  
               + destPath + "\" -o \"" + destPath + "\"";  
       pro = Runtime.getRuntime().exec(command);  
       System.out.println(command);  
       bufferedReader = new BufferedReader(new InputStreamReader(pro  
               .getInputStream()));  
       while (bufferedReader.readLine() != null) {  
           String text = bufferedReader.readLine();  
           System.out.println(text);  
       }  
       try {  
           pro.waitFor();  
       } catch (InterruptedException e) {  
           // TODO Auto-generated catch block  
           e.printStackTrace();  
       }  
       return pro.exitValue();  
 
   }  
  
   public static void main(String[] args) {  
       String sourcePath = "E:\\test1229\\0200100000000957400.pdf";  
       String destPath = "E:\\test1229\\0200100000000957400.swf";  
       try {  
           System.out.println(new PdfToSwf().convertPDF2SWF(sourcePath,  
                   destPath));  
       } catch (IOException e) {  
          // TODO Auto-generated catch block  
          e.printStackTrace();  
      }  
  }  
}  