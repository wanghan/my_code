package sys;
/*
 * 视频一个结果集
 */
import java.io.File;
 
public class videoInfo {
	public String existID;  //eXistID
	public File[] keyFrame; //关键帧
	public String filename; //文件名
	public String getExistID() {
		return existID;
	}
   public videoInfo(String existID,File keyFrame[],String filename)
	{
		this.existID=existID;
		this.keyFrame=keyFrame;
		this.filename=filename;
	}
	public void setExistID(String existID) {
		this.existID = existID;
	}
	public File[] getKeyFrame() {
		return keyFrame;
	}
	public void setKeyFrame(File[] keyFrame) {
		this.keyFrame = keyFrame;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public videoInfo()
	{
		System.out.println("it is a constructor function!");
	}
}
