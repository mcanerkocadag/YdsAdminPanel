package models;

import java.util.Date;

public class ListExamModel {
	private String idexam;
	private String name;
	private String status;
	private String kelimeBilgisi;
	private String dilBilgisi;
	private String clozeTest;
	private String cumleTam;
	private String ingTur;
	private String turIng;
	private String okumaParca;
	private String diyalogTam;
	private String enYakin;
	private String paragrafTam;
	private String anlamBozan;
	private Date listDate;
	public ListExamModel()
	{
		idexam="";
		name="";
		status="";
		kelimeBilgisi="";
		dilBilgisi="";
		clozeTest="";
		cumleTam="";
		ingTur="";
		turIng="";
		okumaParca="";
		diyalogTam="";
		enYakin="";
		paragrafTam="";
		anlamBozan="";
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getKelimeBilgisi() {
		return kelimeBilgisi;
	}
	public void setKelimeBilgisi(String kelimeBilgisi) {
		this.kelimeBilgisi = kelimeBilgisi;
	}
	public String getDilBilgisi() {
		return dilBilgisi;
	}
	public void setDilBilgisi(String dilBilgisi) {
		this.dilBilgisi = dilBilgisi;
	}
	public String getClozeTest() {
		return clozeTest;
	}
	public void setClozeTest(String clozeTest) {
		this.clozeTest = clozeTest;
	}
	public String getCumleTam() {
		return cumleTam;
	}
	public void setCumleTam(String cumleTam) {
		this.cumleTam = cumleTam;
	}
	public String getIngTur() {
		return ingTur;
	}
	public void setIngTur(String ingTur) {
		this.ingTur = ingTur;
	}
	public String getTurIng() {
		return turIng;
	}
	public void setTurIng(String turIng) {
		this.turIng = turIng;
	}
	public String getOkumaParca() {
		return okumaParca;
	}
	public void setOkumaParca(String okumaParca) {
		this.okumaParca = okumaParca;
	}
	public String getDiyalogTam() {
		return diyalogTam;
	}
	public void setDiyalogTam(String diyalogTam) {
		this.diyalogTam = diyalogTam;
	}
	public String getEnYakin() {
		return enYakin;
	}
	public void setEnYakin(String enYakin) {
		this.enYakin = enYakin;
	}
	public String getParagrafTam() {
		return paragrafTam;
	}
	public void setParagrafTam(String paragrafTam) {
		this.paragrafTam = paragrafTam;
	}
	public String getAnlamBozan() {
		return anlamBozan;
	}
	public void setAnlamBozan(String anlamBozan) {
		this.anlamBozan = anlamBozan;
	}


	public Date getListDate() {
		return listDate;
	}


	public void setListDate(Date listDate) {
		this.listDate = listDate;
	}
	
	
}
