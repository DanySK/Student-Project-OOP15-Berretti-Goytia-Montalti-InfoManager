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
	
	private static String ctrlStringa(String str){
		return str != "" ? str : null;
	}
	
	private static List<modelUtenti> elenco(){
		MBQuery query = MBQuery.queryDaTabella("Utenti");
		try {
			return query.find().stream()
					.map(e -> new modelUtenti(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private static modelUtenti getSpecificObject(String nome, String cognome, String mail, String telefono, String negozio){
		MBQuery query = MBQuery.queryDaTabella("Clienti");
		query.whereEqualTo("Nome", ctrlStringa(nome));
		query.whereEqualTo("Cognome", ctrlStringa(cognome));
		query.whereEqualTo("Mail", ctrlStringa(mail));
		query.whereEqualTo("Telefono", ctrlStringa(telefono));
		query.whereEqualTo("Negozio", ctrlStringa(negozio));
		
		try {
			return new modelUtenti(query.find().get(0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public boolean nuovoUtente(String nome, String cognome, String mail, String username, String password){
		modelUtenti nuovo = new modelUtenti(MBOggetto.oggettoDaTabella("Utenti"));
		nuovo.setNome(nome);
		nuovo.setCognome(cognome);
		nuovo.setMail(mail);
		nuovo.setUsername(username);
		nuovo.setPassword(password);
		return nuovo.oggetto.salva();
	}
	
	public boolean loginUtenti(String username, String password){
		return modelUtenti.elenco().stream()
				.filter(e -> e.getUsername() == username &&  e.getPassword() == password)
				.count() == 1;
	}
	
	public boolean cambiaPassword(String nome, String cognome, String mail, String username, String password, String newPassword){
		//controllo esistenza dell'utente a cambiare la passoword
		modelUtenti temp = modelUtenti.getSpecificObject(nome, cognome, mail, username, password);
		if(temp != null){
			temp.setPassword(newPassword);
			return temp.oggetto.salva();
		}
		else 
			return false;
	}
	
	public boolean eliminaUtente(String nome, String cognome, String mail, String username, String password){
		//controllo esistenza dell'utente a eliminare
		modelUtenti temp = modelUtenti.getSpecificObject(nome, cognome, mail, username, password);
		if(temp != null)
			return temp.oggetto.elimina();
		else 
			return false;
	}
	
}
