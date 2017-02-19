package models;

public class ExamModel {
	
	private String idexam;
	private String name;
	private String questions;
	private String status;
	
	public ExamModel()
	{
		idexam="";
		name="";
		questions="";
		status="";
	}
	
	public String getIdexam() {
		return idexam;
	}
	public void setIdexam(String idexam) {
		this.idexam = idexam;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
