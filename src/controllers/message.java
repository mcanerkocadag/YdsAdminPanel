package controllers;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public class message {
	public static String name;
	private List<String> cevaplar;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
	}
	public List<Integer> soruNo()
	{
		
		List<Integer> no=new ArrayList();
		for(int x=1;x<81;x++)
		{
			no.add(x);
			
		}
		return no;
	}
	public void cevapDoldur(String a)
	{
		cevaplar.add(a);
		
	}
	
	public static void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	public static void basarili(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getCevaplar() {
		return cevaplar;
	}
	public void setCevaplar(List<String> cevaplar) {
		this.cevaplar = cevaplar;
	}
	
}
