package models;

public class StatisticsModel {
	
private String idstatistics;
	
	private String username;
	
	private String examname;
	
	private String dogru;
	
	private String status;
	
	public StatisticsModel(){
		idstatistics="";
		username="";
		examname="";
		dogru="";
		status="";
	}

	public String getIdstatistics() {
		return idstatistics;
	}

	public void setIdstatistics(String idstatistics) {
		this.idstatistics = idstatistics;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getExamname() {
		return examname;
	}

	public void setExamname(String examname) {
		this.examname = examname;
	}

	public String getDogru() {
		return dogru;
	}

	public void setDogru(String dogru) {
		this.dogru = dogru;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
