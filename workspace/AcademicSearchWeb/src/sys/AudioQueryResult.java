package sys;

import java.io.Serializable;

public class AudioQueryResult implements Serializable {

	private String lowLevelID;

	//private String filename;

	//private String format;

	private String audname;

	private String singer;

	private String album;

	private String filesuf;

	public AudioQueryResult(String lowLevelID,String audname, String singer, String album,String filesuf) {
		this.lowLevelID=lowLevelID;
		this.audname = audname;
		this.singer = singer;
		this.album = album;
		this.filesuf=filesuf;
	}

	public AudioQueryResult() {

	}

	public void setAudNam(String audname) {
		this.audname = audname;
	}

	public String getAudNam() {
		return audname;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getSinger() {
		return singer;
	}
	public void setAlbum(String album) {
		this.album = album;
	}

	public String getAlbum() {
		return album;
	}
	public void setFileSuf(String filesuf) {
		this.filesuf = filesuf;
	}

	public String getFileSuf() {
		return filesuf;
	}
	
	public String getLowLevelID() {
		return lowLevelID;
	}

	public void setLowLevelID(String lowLevelID) {
		this.lowLevelID = lowLevelID;
	}

	
}
