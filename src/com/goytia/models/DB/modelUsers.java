package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelUsers {

	MBOggetto oggetto;
	/***
	 * Ottengo un record nuovo dalla tabella Scontrini
	 */
	public modelUsers(){
		this.oggetto= MBOggetto.oggettoDaTabella("Utenti");
	}
	
	private modelUsers(MBOggetto temp){
		this.oggetto=temp;
	}
	
	public Integer getID(){
		return this.oggetto.objectId();
	}
	
	public String getName(){
		return (String)this.oggetto.getObject("Nome");
	}
	
	public String getLastName(){
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
	
	
	
	private void setName(String nome){
		if(nome!= "")
		this.oggetto.setObjectValue("Nome", ctrlString(nome));
	}
	
	private void setLastName(String cognome){
		this.oggetto.setObjectValue("Cognome", ctrlString(cognome));
	}
	
	private void setMail(String mail){
		this.oggetto.setObjectValue("Mail", ctrlString(mail));
	}
	
	private void setUsername(String username){
		this.oggetto.setObjectValue("Username", ctrlString(username));
	}
	
	private void setPassword(String password){
		this.oggetto.setObjectValue("Password", ctrlString(password));
	}
	
	private static String ctrlString(String str){
		return str != "" ? str : null;
	}
	/***
	 * metodo che ritorna un elenco di tutti gli utento
	 * @return
	 * una lista contenenti tutti gli utenti esistenti
	 */
	private static List<modelUsers> usersList(){
		MBQuery query = MBQuery.queryDaTabella("Utenti");
		try {
			return query.find().stream()
					.map(e -> new modelUsers(e))
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
	 * true se � stato creato l'utente altrimenti false
	 */
	public static boolean newUser(String nome, String cognome, String mail, String username, String password){
		modelUsers nuovo = new modelUsers(MBOggetto.oggettoDaTabella("Utenti"));
		nuovo.setName(nome);
		nuovo.setLastName(cognome);
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
	public static boolean usersLogin(String username, String password){
		return modelUsers.usersList().stream()
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
	public boolean changePassword(String nome, String cognome, String mail, String username, String password, String newPassword){
		//mi accerto che si tratti dello stesso cliente richiedendo i dati
		if(this.getName()== nome && this.getLastName()==cognome && this.getUsername()==username && this.getMail()==mail && this.getPassword()== password){
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
	 * true se � stata cancellata l'account altrimenti false
	 */
	public boolean deleteUser(String nome, String cognome, String mail, String username, String password){
		//mi accerto che si tratti dello stesso cliente richiedendo i dati
		if(this.getName()== nome && this.getLastName()==cognome && this.getUsername()==username && this.getMail()==mail && this.getPassword()== password)
			return this.oggetto.elimina();
		else 
			return false;
	}
	
	
}
