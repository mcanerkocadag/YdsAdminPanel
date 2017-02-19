package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import models.ExamModel;
import models.ListExamModel;
import models.subjectModel;
import webservice.webservice;


@ManagedBean(name="etSelectionView")
@SessionScoped
@ViewScoped
public class exam {
	private ExamModel model;
	private subjectModel submodel;
	private Date date;
	private List<ListExamModel> exams;
	private List<ListExamModel> lastExams;
	 private String secilenId=null;
	 private String secilenAd=null;
	 private ListExamModel selectedExam;
	 @PostConstruct
	    public void init() {
		 
		 model=new ExamModel();
			submodel=new subjectModel();

		 if(this.getSecilenAd().equals("")&&this.getSecilenId().equals(""))
		 {
			 exams=webservice.listExam2();
		 }
		 else if(this.getSecilenAd().equals("")&&!this.getSecilenId().equals(""))
		 {
			
		 exams.removeAll(this.getExams());
		 List<ListExamModel> newExams=new ArrayList();
		 newExams=webservice.listExam2();
		 ListExamModel m;
		 for(int x=0;x<newExams.size();x++)
		 {
			 if(this.getSecilenId().equals(newExams.get(x).getIdexam()))
			 {
				 m=new ListExamModel();
				 m.setIdexam(newExams.get(x).getIdexam());
				 m.setAnlamBozan(newExams.get(x).getAnlamBozan());
				 m.setClozeTest(newExams.get(x).getClozeTest());
				 m.setCumleTam(newExams.get(x).getCumleTam());
				 m.setDilBilgisi(newExams.get(x).getDilBilgisi());
				 m.setDiyalogTam(newExams.get(x).getDiyalogTam());
				 m.setEnYakin(newExams.get(x).getEnYakin());
				 m.setIngTur(newExams.get(x).getIngTur());
				 m.setKelimeBilgisi(newExams.get(x).getKelimeBilgisi());
				 m.setListDate(newExams.get(x).getListDate());
				 m.setName(newExams.get(x).getName());
				 m.setOkumaParca(newExams.get(x).getOkumaParca());
				 m.setParagrafTam(newExams.get(x).getParagrafTam());
				 m.setTurIng(newExams.get(x).getTurIng());
				 m.setStatus(newExams.get(x).getStatus());
				 exams.add(m);
			 }
		 }
		 
		 }
		  else if(!this.getSecilenAd().equals("")&&this.getSecilenId().equals(""))
		  {
			 exams.removeAll(this.getExams());
			 List<ListExamModel> newExams=new ArrayList();
			 newExams=webservice.listExam2();
			 ListExamModel m;
			 for(int x=0;x<newExams.size();x++)
			 {
				 if(this.getSecilenAd().equals(newExams.get(x).getName()))
				 {
					 m=new ListExamModel();
					 m.setIdexam(newExams.get(x).getIdexam());
					 m.setAnlamBozan(newExams.get(x).getAnlamBozan());
					 m.setClozeTest(newExams.get(x).getClozeTest());
					 m.setCumleTam(newExams.get(x).getCumleTam());
					 m.setDilBilgisi(newExams.get(x).getDilBilgisi());
					 m.setDiyalogTam(newExams.get(x).getDiyalogTam());
					 m.setEnYakin(newExams.get(x).getEnYakin());
					 m.setIngTur(newExams.get(x).getIngTur());
					 m.setKelimeBilgisi(newExams.get(x).getKelimeBilgisi());
					 m.setListDate(newExams.get(x).getListDate());
					 m.setName(newExams.get(x).getName());
					 m.setOkumaParca(newExams.get(x).getOkumaParca());
					 m.setParagrafTam(newExams.get(x).getParagrafTam());
					 m.setTurIng(newExams.get(x).getTurIng());
					 m.setStatus(newExams.get(x).getStatus());
					 exams.add(m);
				 }
			 }
			
		  }
		  
		  else
		  {
		  exams.removeAll(this.getExams());
			 List<ListExamModel> newExams=new ArrayList();
			 newExams=webservice.listExam2();
			 ListExamModel m;
			 for(int x=0;x<newExams.size();x++)
			 {
				 if(this.getSecilenAd().equals(newExams.get(x).getName())&&this.getSecilenId().equals(newExams.get(x).getIdexam()))
				 {
					 m=new ListExamModel();
					 m.setIdexam(newExams.get(x).getIdexam());
					 m.setAnlamBozan(newExams.get(x).getAnlamBozan());
					 m.setClozeTest(newExams.get(x).getClozeTest());
					 m.setCumleTam(newExams.get(x).getCumleTam());
					 m.setDilBilgisi(newExams.get(x).getDilBilgisi());
					 m.setDiyalogTam(newExams.get(x).getDiyalogTam());
					 m.setEnYakin(newExams.get(x).getEnYakin());
					 m.setIngTur(newExams.get(x).getIngTur());
					 m.setKelimeBilgisi(newExams.get(x).getKelimeBilgisi());
					 m.setListDate(newExams.get(x).getListDate());
					 m.setName(newExams.get(x).getName());
					 m.setOkumaParca(newExams.get(x).getOkumaParca());
					 m.setParagrafTam(newExams.get(x).getParagrafTam());
					 m.setTurIng(newExams.get(x).getTurIng());
					 m.setStatus(newExams.get(x).getStatus());
					 exams.add(m);
				 }
			 }
		  
		  
		  }
		 
		 
	    }
	 
	 public String delete()
	 {
		 webservice.deleteExam(this.getSelectedExam().getIdexam());
		 message.basarili("Başarılı", "Sınav silindi.");
		 return "admin.sinavDS.jsf";
	 }
	 public String update()
	 {
		 boolean sonuc;
		 String questions=selectedExam.getKelimeBilgisi()+","+selectedExam.getDilBilgisi()+","+selectedExam.getClozeTest()+","+selectedExam.getCumleTam()+","+selectedExam.getIngTur()+","+selectedExam.getTurIng()+","+selectedExam.getOkumaParca()+","+selectedExam.getDiyalogTam()+","+selectedExam.getEnYakin()+","+selectedExam.getParagrafTam()+","+selectedExam.getAnlamBozan();
		 String status =selectedExam.getListDate().getYear()+1900+"-";
		 if(selectedExam.getListDate().getMonth()<9)
			{
				status+="0";
			}
			status+=selectedExam.getListDate().getMonth()+1+"-";
			if(selectedExam.getListDate().getDate()<10)
			{
				status+="0";
			}
			status+=selectedExam.getListDate().getDate();
			status+="T";
			if(selectedExam.getListDate().getHours()<10){
				status+="0";
			}
			status+=selectedExam.getListDate().getHours()+":";
			if(selectedExam.getListDate().getMinutes()<10){
				status+="0";
			}
			status+=selectedExam.getListDate().getMinutes();
			message.basarili("Başarılı", "Güncelleme başarılı.");
		 webservice.updateExam(this.getSelectedExam().getIdexam(),this.getSelectedExam().getName(),questions,status);
		 
			 return "admin.sinavDS.jsf";
		
	 }
	 
	 public String registerExam()
		{
			String questions=submodel.getKelimeBilgisi()+","+submodel.getDilBilgisi()+","+submodel.getClozeTest()+","+submodel.getCumleTam()+","+submodel.getIngTur()+","+submodel.getTurIng()+","+submodel.getOkumaParca()+","+submodel.getDiyalogTam()+","+submodel.getEnYakin()+","+submodel.getParagrafTam()+","+submodel.getAnlamBozan();
			
			String status ="";
			status+=date.getYear()+1900+"-";
			if(date.getMonth()<9)
			{
				status+="0";
			}
			status+=date.getMonth()+1+"-";
			if(date.getDate()<10)
			{
				status+="0";
			}
			status+=date.getDate();
			status+="T";
			if(date.getHours()<10){
				status+="0";
			}
			status+=date.getHours()+":";
			if(date.getMinutes()<10){
				status+="0";
			}
			status+=date.getMinutes();
			
			if(model.getName().equals("")||submodel.getAnlamBozan().equals("")||submodel.getClozeTest().equals("")||submodel.getCumleTam().equals("")||submodel.getDilBilgisi().equals("")||submodel.getDiyalogTam().equals("")||submodel.getEnYakin().equals("")||submodel.getIngTur().equals("")||submodel.getKelimeBilgisi().equals("")||submodel.getOkumaParca().equals("")||submodel.getParagrafTam().equals("")||submodel.getTurIng().equals("")||status.length()<1)
			{
				message.addMessage("Başarısız", "Alanları boş bırakmayınız!!");
				return "";
			}
			else 
			{
				webservice.registerExam(model.getName(), questions, status);
				message.basarili("Başarılı","Sınav eklendi.");
				model.setName("");
				submodel.setAnlamBozan("");
				submodel.setClozeTest("");
				submodel.setCumleTam("");
				submodel.setDilBilgisi("");
				submodel.setDiyalogTam("");
				submodel.setEnYakin("");
				submodel.setIngTur("");
				submodel.setKelimeBilgisi("");
				submodel.setOkumaParca("");
				submodel.setParagrafTam("");
				submodel.setTurIng("");
				model.setStatus("");
				this.date=null;
				return "admin.sinavEkle.jsf";
			}
			
		}
		
	 
	 
	public List<ListExamModel> getExams() {
		return exams;
	}

	public void setExams(List<ListExamModel> exams) {
		this.exams = exams;
	}

	public ListExamModel getSelectedExam() {
		return selectedExam;
	}

	public void setSelectedExam(ListExamModel selectedExam) {
		this.selectedExam = selectedExam;
	}


	public List<ListExamModel> getLastExams() {
		return lastExams;
	}

	public void setLastExams(List<ListExamModel> lastExams) {
		this.lastExams = lastExams;
	}

	public String getSecilenId() {
		if(secilenId==null)
		{
			secilenId="";
		}
		return secilenId;
	}

	public void setSecilenId(String secilenId) {
		this.secilenId = secilenId;
	}

	public String getSecilenAd() {
		if(secilenAd==null)
		{
			secilenAd="";
		}
		return secilenAd;
	}

	public void setSecilenAd(String secilenAd) {
		this.secilenAd = secilenAd;
	}

	public ExamModel getModel() {
		return model;
	}

	public void setModel(ExamModel model) {
		this.model = model;
	}

	public subjectModel getSubmodel() {
		return submodel;
	}

	public void setSubmodel(subjectModel submodel) {
		this.submodel = submodel;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	 
}
