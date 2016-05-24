package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelUtenti {

	MBOggetto oggetto;
	
	public modelUtenti(){
		this.oggetto= MBOggetto.oggettoDaTabella("Utenti");
	}
	
	private modelUtenti(MBOggetto temp){
		this.oggetto=temp;
	}
	
	public String getNome(){
		return (String)this.oggetto.getObject("Nome");
	}
	
	public String getCognome(){
		return (String)this.oggetto.getObject("Cognome");
	}
	
	public String getUsername(){
		return (String)this.oggetto.getObject("Username");
	}
	
	public String getPassword(){
		return (String)this.oggetto.getObject("Password");
	}
	public String getMail(){
		return (String)this.oggetto.getObject("Mail");
	}
	
	
	
	private void setNome(String nome){
		if(nome!= "")
		this.oggetto.setObjectValue("Nome", ctrlStringa(nome));
	}
	
	private void setCognome(String cognome){
		this.oggetto.setObjectValue("Cognome", ctrlStringa(cognome));
	}
	
	private void setMail(String mail){
		this.oggetto.setObjectValue("Mail", ctrlStringa(mail));
	}
	
	private void setUsername(String username){
		this.oggetto.setObjectValue("Username", ctrlStringa(username));
	}
	
	private void setPassword(String password){
		this.oggetto.setObjectValue("Password", ctrlStringa(password));
	}
	
	private String ctrlStringa(String str){
		return str != "" ? str : null;
	}
	
	
}
