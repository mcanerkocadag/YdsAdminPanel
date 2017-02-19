package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import models.QuestionModel;
import webservice.webservice;


@ManagedBean(name="qtSelectionView")
@SessionScoped
@ViewScoped
public class question {
	private List<QuestionModel> questions;
	 private QuestionModel selectedQuestion;
	 private QuestionModel model;
	 private QuestionModel regQuestion;
	 private String secilenId=null;
	 private String secilenType=null;
	 private List<String> typeList=new ArrayList();
	 @PostConstruct
	    public void init() {
		 
		 model=new QuestionModel();
		 regQuestion=new QuestionModel();
		 
		
			 if(this.getSecilenType().equals("")&&this.getSecilenId().equals(""))
			 {
				 questions=webservice.listQuestions();
			 }
			 else if(this.getSecilenType().equals("")&&!this.getSecilenId().equals(""))
			 {
				
			 questions.removeAll(this.getQuestions());
			 List<QuestionModel> newQuestions=new ArrayList();
			 newQuestions=webservice.listQuestions();
			 QuestionModel m;
			 for(int x=0;x<newQuestions.size();x++)
			 {
				 if(this.getSecilenId().equals(newQuestions.get(x).getIdquestion()))
				 {
					 m=new QuestionModel();
					 m.setIdquestion(newQuestions.get(x).getIdquestion());
					 m.setA(newQuestions.get(x).getA());
					 m.setAnswer(newQuestions.get(x).getAnswer());
					 m.setB(newQuestions.get(x).getB());
					 m.setC(newQuestions.get(x).getC());
					 m.setContent(newQuestions.get(x).getContent());
					 m.setD(newQuestions.get(x).getD());
					 m.setE(newQuestions.get(x).getE());
					 m.setQuestion(newQuestions.get(x).getQuestion());
					 m.setQuestionType(newQuestions.get(x).getQuestionType());
					 m.setStatus(newQuestions.get(x).getStatus());
					 questions.add(m);
				 }
			 }
			 
			 }
			  else if(!this.getSecilenType().equals("")&&this.getSecilenId().equals(""))
			  {
				 questions.removeAll(this.getQuestions());
				 List<QuestionModel> newQuestions=new ArrayList();
				 newQuestions=webservice.listQuestions();
				 QuestionModel m;
				 for(int x=0;x<newQuestions.size();x++)
				 {
					 if(this.getSecilenType().equals(newQuestions.get(x).getQuestionType()))
					 {
						 m=new QuestionModel();
						 m.setIdquestion(newQuestions.get(x).getIdquestion());
						 m.setA(newQuestions.get(x).getA());
						 m.setAnswer(newQuestions.get(x).getAnswer());
						 m.setB(newQuestions.get(x).getB());
						 m.setC(newQuestions.get(x).getC());
						 m.setContent(newQuestions.get(x).getContent());
						 m.setD(newQuestions.get(x).getD());
						 m.setE(newQuestions.get(x).getE());
						 m.setQuestion(newQuestions.get(x).getQuestion());
						 m.setQuestionType(newQuestions.get(x).getQuestionType());
						 m.setStatus(newQuestions.get(x).getStatus());
						 questions.add(m);
					 }
				 }
				
			  }
			  
			  else
			  {
			  questions.removeAll(this.getQuestions());
				 List<QuestionModel> newQuestions=new ArrayList();
				 newQuestions=webservice.listQuestions();
				 QuestionModel m;
				 for(int x=0;x<newQuestions.size();x++)
				 {
					 if(this.getSecilenType().equals(newQuestions.get(x).getQuestionType())&&this.getSecilenId().equals(newQuestions.get(x).getIdquestion()))
					 {
						 m=new QuestionModel();
						 m.setIdquestion(newQuestions.get(x).getIdquestion());
						 m.setA(newQuestions.get(x).getA());
						 m.setAnswer(newQuestions.get(x).getAnswer());
						 m.setB(newQuestions.get(x).getB());
						 m.setC(newQuestions.get(x).getC());
						 m.setContent(newQuestions.get(x).getContent());
						 m.setD(newQuestions.get(x).getD());
						 m.setE(newQuestions.get(x).getE());
						 m.setQuestion(newQuestions.get(x).getQuestion());
						 m.setQuestionType(newQuestions.get(x).getQuestionType());
						 m.setStatus(newQuestions.get(x).getStatus());
						 questions.add(m);
					 }
				 }
			  
			  
			  }
			 
			 
			 
			 }
		
	
	 public List<String> questionTypeList()
		{
			if(typeList.size()>0)
			{
				typeList.removeAll(getTypeList());
			}
			typeList.add("Kelime Bilgisi");
			typeList.add("Dil Bilgisi");
			typeList.add("Cloze Test");
			typeList.add("Cümle Tamamlama");
			typeList.add("İngilizce-Türkçe Çeviri");
			typeList.add("Türkçe-İngilizce Çeviri");
			typeList.add("Okuma Parçaları");
			typeList.add("Diyalog Tamamlama");
			typeList.add("Anlamca En Yakın Cümleyi Bulma");
			typeList.add("Paragraf Tamamlama");
			typeList.add("Anlam Bütünlüğünü Bozan Cümle");
			
			return typeList;
		}
	 
	
	 public String registerQuestion()
		{
			if(model.getQuestionType().equals("Kelime Bilgisi"))
			{
				model.setQuestionType("1");
			}
			else if(model.getQuestionType().equals("Dil Bilgisi"))
			{
				model.setQuestionType("2");
			}
			else if(model.getQuestionType().equals("Cloze Test"))
			{
				model.setQuestionType("3");
			}
			else if(model.getQuestionType().equals("Cümle Tamamlama"))
			{
				model.setQuestionType("4");
			}
			else if(model.getQuestionType().equals("İngilizce-Türkçe Çeviri"))
			{
				model.setQuestionType("5");
			}
			else if(model.getQuestionType().equals("Türkçe-İngilizce Çeviri"))
			{
				model.setQuestionType("6");
			}
			else if(model.getQuestionType().equals("Okuma Parçaları"))
			{
				model.setQuestionType("7");
			}
			else if(model.getQuestionType().equals("Diyalog Tamamlama"))
			{
				model.setQuestionType("8");
			}
			else if(model.getQuestionType().equals("Anlamca En Yakın Cümleyi Bulma"))
			{
				model.setQuestionType("9");
			}
			else if(model.getQuestionType().equals("Paragraf Tamamlama"))
			{
				model.setQuestionType("10");
			}
			else if(model.getQuestionType().equals("Anlam Bütünlüğünü Bozan Cümle"))
			{
				model.setQuestionType("11");
			}
			else 
				{
				model.setQuestionType(null);
				}
			
			
			if(model.getA().equals("")|| model.getAnswer().equals("")|| model.getB().equals("")||model.getC().equals("")||model.getContent().equals("")||model.getD().equals("")||model.getE().equals("")||model.getQuestion().equals("")||model.getQuestionType().equals(""))
			{
				message.addMessage("Başarısız", "Alanları boş bırakmayınız!!");
				
				return "";
				
			}
			else
			{
				webservice.registerQuestion(model.getA(), model.getAnswer(), model.getB(),model.getC(),model.getContent(),model.getD(),model.getE(),model.getQuestion(),model.getQuestionType());
				message.basarili("Başarılı","Soru eklendi.");
				model.setA(null);
				model.setAnswer(null);
				model.setB(null);
				model.setC(null);
				model.setContent(null);
				model.setD(null);
				model.setE(null);
				model.setQuestion(null);
				model.setQuestionType(null);
				model.setStatus(null);
				return "admin.soruEkle.jsf";
			}
			
		}
	 
	 
	 public String delete()
	 {
		 webservice.deleteQuestion(this.getSelectedQuestion().getIdquestion());
		 message.basarili("Başarılı", "Soru silindi.");
		 return "admin.soruLS.jsf";
	 }
	 public String updateQuestion()
		{
			if(selectedQuestion.getQuestionType().equals("Kelime Bilgisi"))
			{
				selectedQuestion.setQuestionType("1");
			}
			else if(selectedQuestion.getQuestionType().equals("Dil Bilgisi"))
			{
				selectedQuestion.setQuestionType("2");
			}
			else if(selectedQuestion.getQuestionType().equals("Cloze Test"))
			{
				selectedQuestion.setQuestionType("3");
			}
			else if(selectedQuestion.getQuestionType().equals("Cümle Tamamlama"))
			{
				selectedQuestion.setQuestionType("4");
			}
			else if(selectedQuestion.getQuestionType().equals("İngilizce-Türkçe Çeviri"))
			{
				selectedQuestion.setQuestionType("5");
			}
			else if(selectedQuestion.getQuestionType().equals("Türkçe-İngilizce Çeviri"))
			{
				selectedQuestion.setQuestionType("6");
			}
			else if(selectedQuestion.getQuestionType().equals("Okuma Par�alar�"))
			{
				selectedQuestion.setQuestionType("7");
			}
			else if(selectedQuestion.getQuestionType().equals("Diyalog Tamamlama"))
			{
				selectedQuestion.setQuestionType("8");
			}
			else if(selectedQuestion.getQuestionType().equals("Anlamca En Yakın Cümleyi Bulma"))
			{
				selectedQuestion.setQuestionType("9");
			}
			else if(selectedQuestion.getQuestionType().equals("Paragraf Tamamlama"))
			{
				selectedQuestion.setQuestionType("10");
			}
			else if(selectedQuestion.getQuestionType().equals("Anlam Bütünlüğünü Bozan Cümle"))
			{
				selectedQuestion.setQuestionType("11");
			}
			message.basarili("Başarılı", "Güncelleme başarılı.");
			webservice.updateQuestion(selectedQuestion.getIdquestion(), selectedQuestion.getA(), selectedQuestion.getAnswer(), selectedQuestion.getB(), selectedQuestion.getC(), selectedQuestion.getContent(), selectedQuestion.getD(), selectedQuestion.getE(), selectedQuestion.getQuestion(), selectedQuestion.getQuestionType(),selectedQuestion.getStatus());
			selectedQuestion.setA("");
			selectedQuestion.setAnswer("");
			selectedQuestion.setB("");
			selectedQuestion.setC("");
			selectedQuestion.setContent("");
			selectedQuestion.setD("");
			selectedQuestion.setE("");
			selectedQuestion.setQuestion("");
			selectedQuestion.setIdquestion("");
			selectedQuestion.setQuestionType("");
			selectedQuestion.setStatus("");
			this.setSecilenId("");
			return "admin.soruLS.jsf";
		}
	public List<QuestionModel> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionModel> questions) {
		this.questions = questions;
	}
	public QuestionModel getSelectedQuestion() {
		return selectedQuestion;
	}
	public void setSelectedQuestion(QuestionModel selectedQuestion) {
		this.selectedQuestion = selectedQuestion;
	}
	public QuestionModel getRegQuestion() {
		return regQuestion;
	}
	public void setRegQuestion(QuestionModel regQuestion) {
		this.regQuestion = regQuestion;
	}
	public List<String> getTypeList() {
		return typeList;
	}
	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
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

	public String getSecilenType() {
		if(secilenType==null)
		{
			secilenType="";
		}
		return secilenType;
	}

	public void setSecilenType(String secilenType) {
		this.secilenType = secilenType;
	}

	public QuestionModel getModel() {
		return model;
	}

	public void setModel(QuestionModel model) {
		this.model = model;
	}
	 
}
