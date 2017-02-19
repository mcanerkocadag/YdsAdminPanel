package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import models.UserModel;

@ManagedBean(name="dtSelectionView")
@SessionScoped
@ViewScoped
public class user {
	 private List<UserModel> users;
	 private UserModel selectedUser;
	 private UserModel selectedProfil;
	 private String secilenId=null;
	 private String secilenAd=null;
	 private UserModel regUser;
	 private UserModel editProfil;
	 private UserModel logUser;
	 private String mesaj;
	 @PostConstruct
	    public void init() {
		 
		 regUser=new UserModel();
		 logUser=new UserModel();

		 if(this.getSecilenAd().equals("")&&this.getSecilenId().equals(""))
		 {
			 users=webservice.webservice.listUsers();
		 }
		 else if(this.getSecilenAd().equals("")&&!this.getSecilenId().equals(""))
		 {
			
		 users.removeAll(this.getUsers());
		 List<UserModel> newUsers=new ArrayList();
		 newUsers=webservice.webservice.listUsers();
		 UserModel m;
		 for(int x=0;x<newUsers.size();x++)
		 {
			 if(this.getSecilenId().equals(newUsers.get(x).getIduser()))
			 {
				 m=new UserModel();
				 m.setIduser(newUsers.get(x).getIduser());
				 m.setEmail(newUsers.get(x).getEmail());
				 m.setPassword(newUsers.get(x).getPassword());
				 m.setUsername(newUsers.get(x).getUsername());
				 m.setStatus(newUsers.get(x).getStatus());
				 users.add(m);
			 }
		 }
		 
		 }
		  else if(!this.getSecilenAd().equals("")&&this.getSecilenId().equals(""))
		  {
			 users.removeAll(this.getUsers());
			 List<UserModel> newUsers=new ArrayList();
			 newUsers=webservice.webservice.listUsers();
			 UserModel m;
			 for(int x=0;x<newUsers.size();x++)
			 {
				 if(this.getSecilenAd().equals(newUsers.get(x).getUsername()))
				 {
					 m=new UserModel();
					 m.setIduser(newUsers.get(x).getIduser());
					 m.setEmail(newUsers.get(x).getEmail());
					 m.setPassword(newUsers.get(x).getPassword());
					 m.setUsername(newUsers.get(x).getUsername());
					 m.setStatus(newUsers.get(x).getStatus());
					 users.add(m);
				 }
			 }
			
		  }
		  
		  else
		  {
		  users.removeAll(this.getUsers());
			 List<UserModel> newUsers=new ArrayList();
			 newUsers=webservice.webservice.listUsers();
			 UserModel m;
			 for(int x=0;x<newUsers.size();x++)
			 {
				 if(this.getSecilenAd().equals(newUsers.get(x).getUsername())&&this.getSecilenId().equals(newUsers.get(x).getIduser()))
				 {
					 m=new UserModel();
					 m.setIduser(newUsers.get(x).getIduser());
					 m.setEmail(newUsers.get(x).getEmail());
					 m.setPassword(newUsers.get(x).getPassword());
					 m.setUsername(newUsers.get(x).getUsername());
					 m.setStatus(newUsers.get(x).getStatus());
					 users.add(m);
				 }
			 }
		  
		  
		  }
		 
		 
		 
		 
	    }
	 public String logout()
	 {
		 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("admin");
		 return "uyeGiris.xhtml?faces-redirect=true";	
	 }
	 
	 public String login()
	 {
		 users.removeAll(this.getUsers());
		 List<UserModel> newUsers=new ArrayList();
		 newUsers=webservice.webservice.listUsers();
		 UserModel m;
		 for(int x=0;x<newUsers.size();x++)
		 {
			 if(this.logUser.getUsername().equals(newUsers.get(x).getUsername())&& this.logUser.getPassword().equals(newUsers.get(x).getPassword())&&newUsers.get(x).getStatus().equals("admin"))
			 {
				 m=new UserModel();
				 m.setIduser(newUsers.get(x).getIduser());
				 m.setEmail(newUsers.get(x).getEmail());
				 m.setPassword(newUsers.get(x).getPassword());
				 m.setUsername(newUsers.get(x).getUsername());
				 m.setStatus(newUsers.get(x).getStatus());
				 users.add(m);
			 }
		 }
		
		 if(!users.isEmpty())
		 {
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin",logUser.getUsername());
			 return "admin.index.xhtml";
		 }
		 else 
		 {
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin",null);
			 message.addMessage("Uyarı", "Kullanıcı adı veya parola yanlış!!");
			 return ""; 
		 }
	 }
	 public UserModel profil()
	 {
		
		 List<UserModel> newUsers=new ArrayList();
		 newUsers=webservice.webservice.listUsers();
		 for(int x=0;x<newUsers.size();x++)
		 {
			 if( newUsers.get(x).getUsername().equals(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("admin")))
			 {
				 editProfil=new UserModel();
				 editProfil.setIduser(newUsers.get(x).getIduser());
				 editProfil.setEmail(newUsers.get(x).getEmail());
				 editProfil.setPassword(newUsers.get(x).getPassword());
				 editProfil.setUsername(newUsers.get(x).getUsername());
				 editProfil.setStatus(newUsers.get(x).getStatus());
				 
			 }
		 }
		 return this.getEditProfil();
	 }
	 
	 public void registerUser()
	 {
		 boolean r;
		 r=webservice.webservice.registerUser(regUser.getUsername(), regUser.getPassword(), regUser.getUsername());
		 if(r==false||regUser.getUsername()==""||regUser.getPassword()=="")
		 {
			 message.addMessage("Başarısız", "Bilgilerinizi kontrol edin.");
		 }
		 else
		 {
			 message.basarili("Başarılı", "Kayıt işlemi başarılı.");
		 }
		 
	 }
	 public String delete()
	 {
		 webservice.webservice.deleteUser(this.getSelectedUser().getIduser());
		 message.basarili("Başarılı", "Kullanıcı silindi.");
		 return "admin.kullaniciDS.jsf";
	 }
	 public String update()
	 {
		 boolean r;
		 r=webservice.webservice.updateUser(this.getSelectedUser().getIduser(), this.getSelectedUser().getUsername(), this.getSelectedUser().getPassword(),this.getSelectedUser().getEmail(), this.getSelectedUser().getStatus());
		 if(r==true)
		 {
			 message.basarili("Başarılı", "Güncelleme başarılı.");
			 return "admin.kullaniciDS.jsf";
			 
		 }
		 else 
		 {
			 message.addMessage("Başarısız", "Güncelleme başarısız!");
			 return "";
		 }
	 }
	 public String profilUpdate()
	 {
		 
		 boolean r;
		 r=webservice.webservice.updateUser(this.getSelectedUser().getIduser(), this.getSelectedUser().getUsername(), this.getSelectedUser().getPassword(),this.getSelectedUser().getEmail(), this.getSelectedUser().getStatus());
		 if(r==true)
		 {
			 
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("admin",this.getSelectedUser().getUsername());
			 message.basarili("Başarılı", "Güncelleme başarılı.");
			 return "admin.profil.jsf?faces-redirect=true";
			 
		 }
		 else 
		 {
			 message.addMessage("Başarısız", "Güncelleme başarısız!");
			 return "";
		 }
	 }
	public List<UserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}

	public UserModel getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserModel selectedUser) {
		this.selectedUser = selectedUser;
	}
	public UserModel getRegUser() {
		return regUser;
	}
	public void setRegUser(UserModel regUser) {
		this.regUser = regUser;
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

	public UserModel getLogUser() {
		return logUser;
	}

	public void setLogUser(UserModel logUser) {
		this.logUser = logUser;
	}
	public UserModel getEditProfil() {
		return editProfil;
	}
	public void setEditProfil(UserModel editProfil) {
		this.editProfil = editProfil;
	}
	public UserModel getSelectedProfil() {
		return selectedProfil;
	}
	public void setSelectedProfil(UserModel selectedProfil) {
		this.selectedProfil = selectedProfil;
	}
	public String getMesaj() {
		return mesaj;
	}
	public void setMesaj(String mesaj) {
		this.mesaj = mesaj;
	}
	 
}
