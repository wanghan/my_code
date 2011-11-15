package textCommon;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.IOException;  
import java.io.InputStreamReader;  
  
/**
 * 功能：实现pdf格式文件转换成swf格式视频文件
 * 使用方法：直接调用方法：convertPDF2SWF(String sourcePath,String destPath)
 * 参数：String类型的源文件、目标文件
 * 返回值：int类型的转换状态
 * 
 * 作者：黄吉军
 * 日期：2010年12月29日17:14:22
 * */  
public class PdfToSwf {  
   
   public int convertPDF2SWF(String sourcePath, String destPath ) throws IOException {  
       // 目标路径不存在则建立目标路径  
       File dest = new File(destPath);  
       if (!dest.exists()) {  
           dest.createNewFile();  
       }  
 
       // 源文件不存在则返回  
       File source = new File(sourcePath);  
       if (!source.exists()) {  
           return 0;  
       }  
 
       // 先生成flash  
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
       
       // 然后在套播放器  
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