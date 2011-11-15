package AUDRwebJavaBeans;

public class ROI {
	private String startTime;
	 
	private String endTime;//Ω· ¯ ±º‰
	 
	private String Descripition;//√Ë ˆ
	 
	public ROI() {}
	

	public ROI(String startTime, String endTime, String descripition) {
		this.startTime = startTime;
		this.endTime = endTime;
		Descripition = descripition;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return Descripition;
	}

	public void setDescription(String descripition) {
		Descripition = descripition;
	}
}
