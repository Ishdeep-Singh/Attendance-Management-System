package beans;

public class Attendance {
	private String username;
	private String entryTime;
	private String exitTime;
	private int totalHours;
	private String currentDate;
	private String punchFlag;
	
	public String getPunchFlag() {
		return punchFlag;
	}
	public void setPunchFlag(String punchFlag) {
		this.punchFlag = punchFlag;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}
	public String getExitTime() {
		return exitTime;
	}
	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}
	public int getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	
	
	
}
