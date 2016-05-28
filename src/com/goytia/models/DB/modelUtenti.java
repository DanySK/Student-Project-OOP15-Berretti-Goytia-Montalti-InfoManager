package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelUtenti {

	MBOggetto oggetto;
	/***
	 * Ottengo un record nuovo dalla tabella Scontrini
	 */
	public modelUtenti(){
		this.oggetto= MBOggetto.oggettoDaTabella("Utenti");
	}
	
	private modelUtenti(MBOggetto temp){
		this.oggetto=temp;
	}
	
	public Integer getID(){
		return this.oggetto.objectId();
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
	/***
	 * metodo che ritorna un elenco di tutti gli utento
	 * @return
	 * una lista contenenti tutti gli utenti esistenti
	 */
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
	/***
	 * creazione di un nuovo utente
	 * @param nome
	 * @param cognome
	 * @param mail
	 * @param username
	 * @param password
	 * @return
	 * true se è stato creato l'utente altrimenti false
	 */
	public boolean nuovoUtente(String nome, String cognome, String mail, String username, String password){
		modelUtenti nuovo = new modelUtenti(MBOggetto.oggettoDaTabella("Utenti"));
		nuovo.setNome(nome);
		nuovo.setCognome(cognome);
		nuovo.setMail(mail);
		nuovo.setUsername(username);
		nuovo.setPassword(password);
		return nuovo.oggetto.salva();
	}
	/***
	 * controllo dell'accesso dell'utente
	 * @param username
	 * @param password
	 * @return
	 * true se l'untente esiste, altrimenti False
	 */
	public boolean loginUtenti(String username, String password){
		return modelUtenti.elenco().stream()
				.filter(e -> e.getUsername() == username &&  e.getPassword() == password)
				.count() == 1;
	}
	/***
	 * metodo per cambiare la prpria pasword
	 * @param nome
	 * @param cognome
	 * @param mail
	 * @param username
	 * @param password
	 * @param newPassword
	 * @return
	 * true se la password e stata cambiata, altrimenti false.
	 */
	public boolean cambiaPassword(String nome, String cognome, String mail, String username, String password, String newPassword){
		//mi accerto che si tratti dello stesso cliente richiedendo i dati
		if(this.getNome()== nome && this.getCognome()==cognome && this.getUsername()==username && this.getMail()==mail && this.getPassword()== password){
			this.setPassword(newPassword);
			return this.oggetto.salva();
		}
		else 
			return false;
	}
	/***
	 * metodo per eliminare il propri account utente
	 * @param nome
	 * @param cognome
	 * @param mail
	 * @param username
	 * @param password
	 * @return
	 * true se è stata cancellata l'account altrimenti false
	 */
	public boolean eliminaUtente(String nome, String cognome, String mail, String username, String password){
		//mi accerto che si tratti dello stesso cliente richiedendo i dati
		if(this.getNome()== nome && this.getCognome()==cognome && this.getUsername()==username && this.getMail()==mail && this.getPassword()== password)
			return this.oggetto.elimina();
		else 
			return false;
	}
	
	
}
