package AUDRwebJavaBeans;


import java.io.File;
import java.util.Vector;

public class ShotResult {
	private String videoShotId;
	private int beqSeq = 0;
	private int endSeq = 0;
	private Vector<File> keyFrameThumbnailFiles = new Vector<File>();    //¹Ø¼üÖ¡ËõÂÔÍ¼
	public String getVideoShotId() {
		return videoShotId;
	}
	public void setVideoShotId(String videoShotId) {
		this.videoShotId = videoShotId;
	}
	public int getBegSeq() {
		return beqSeq;
	}
	public void setBegSeq(int begSeq) {
		this.beqSeq = begSeq;
	}
	public int getEndSeq() {
		return endSeq;
	}
	public void setEndSeq(int endSeq) {
		this.endSeq = endSeq;
	}
	public Vector<File> getKeyFrameThumbnailFiles() {
		return keyFrameThumbnailFiles;
	}
	public void setKeyFrameThumbnailFiles(Vector<File> keyFrameThumbnailFiles) {
		this.keyFrameThumbnailFiles = keyFrameThumbnailFiles;
	}
	
	public void addKeyFrameThumbnail(File keyFrameThumbnailFile) {
		this.keyFrameThumbnailFiles.add(keyFrameThumbnailFile);
	}
	public int getKeyframeCount() {
		return this.keyFrameThumbnailFiles.size();
	}
	
}
