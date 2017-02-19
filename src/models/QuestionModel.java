package models;

public class QuestionModel {
	
	private String idquestion=null;
	private String question=null;
	
	private String content=null;
	
	private String a=null;
	
	private String b=null;
	
	private String c=null;
	
	private String d=null;
	
	private String e=null;
	
	private String answer;
	
	private String questionType;
	
	private String status;
	


	public QuestionModel(){
		idquestion="";
		question="";
		content="";
		a="";
		b="";
		c="";
		d="";
		e="";
		answer="";
		status="";
		questionType="";
	}
	
	public String getIdquestion() {
		if(idquestion==null)
		{
			idquestion="";
		}
		return idquestion;
	}

	public void setIdquestion(String idquestion) {
		this.idquestion = idquestion;
	}

	public String getQuestion() {
		if(question==null)
		{
			question="";
		}
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getContent() {
		if(content==null)
		{
			content="";
		}
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getA() {
		if(a==null)
		{
			a="";
		}
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		if(b==null)
		{
			b="";
		}
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		if(c==null)
		{
			c="";
		}
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		if(d==null)
		{
			d="";
		}
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getE() {
		if(e==null)
		{
			e="";
		}
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getAnswer() {
		if(answer==null)
		{
			answer="";
		}
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuestionType() {
		if(questionType==null)
		{
			questionType="";
		}
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getStatus() {
		if(status==null)
		{
			status="";
		}
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
