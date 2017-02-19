package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import models.StatisticsModel;
import webservice.webservice;

@ManagedBean(name="stSelectionView")
@SessionScoped
@ViewScoped
public class statistic {
	private List<StatisticsModel> statistics;
	private StatisticsModel model;
	private String secilenId=null;
	private StatisticsModel selectedStatistic;
	@PostConstruct
	public void init() {
		model=new StatisticsModel();
		 if(this.getSecilenId().equals(""))
		 {
			 statistics=webservice.adminlistStatistics();
		 }
		 else
		 {
			 statistics.removeAll(this.getStatistics());
			 List<StatisticsModel> newStatistics=new ArrayList();
			 newStatistics=webservice.adminlistStatistics();
			 StatisticsModel m;
			 for(int x=0;x<newStatistics.size();x++)
			 {
				 if(this.getSecilenId().equals(newStatistics.get(x).getIdstatistics()))
				 {
					 m=new StatisticsModel();
					 m.setIdstatistics(newStatistics.get(x).getIdstatistics());
					 m.setUsername(newStatistics.get(x).getUsername());
					 m.setExamname(newStatistics.get(x).getExamname());
					 m.setDogru(newStatistics.get(x).getDogru());
					 m.setStatus(newStatistics.get(x).getStatus());
					 
					 statistics.add(m);
				 }
			 }
		 }
	}
	public List<StatisticsModel> listStatistics(){
		return webservice.topStatistics();
	}
	
	public String deleteStatistic()
	{
		webservice.deleteStatistic(this.getSelectedStatistic().getIdstatistics());
		message.basarili("Başarılı", "İstatistik silindi.");
		 return "admin.istatistikLS.jsf";
	}
	public String resetStatistic()
	{
		webservice.resetStatistic();
		message.basarili("Başarılı", "Tüm istatistikler silindi.");
		 return "admin.istatistikLS.jsf";
	}
	public StatisticsModel getModel() {
		return model;
	}

	public void setModel(StatisticsModel model) {
		this.model = model;
	}
	
	public StatisticsModel getSelectedStatistic() {
		return selectedStatistic;
	}
	public void setSelectedStatistic(StatisticsModel selectedStatistic) {
		this.selectedStatistic = selectedStatistic;
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
	public List<StatisticsModel> getStatistics() {
		return statistics;
	}
	public void setStatistics(List<StatisticsModel> statistics) {
		this.statistics = statistics;
	}
	
	
}
