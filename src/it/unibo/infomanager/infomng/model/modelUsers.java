package it.unibo.infomanager.infomng.model;

import it.unibo.infomanager.infomng.controller.TableRow;

public class modelUsers implements modelUsersI {

	TableRow oggetto;
	/***
	 * Ottengo un record nuovo dalla tabella Scontrini
	 */
	public modelUsers(){
		this.oggetto= TableRow.oggettoDaTabella("Utenti");
	}
	
	protected modelUsers(TableRow temp){
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
	
	
	
	protected void setName(String nome){
		if(nome!= "")
		this.oggetto.setObjectValue("Nome", ctrlString(nome));
	}
	
	protected void setLastName(String cognome){
		this.oggetto.setObjectValue("Cognome", ctrlString(cognome));
	}
	
	protected void setMail(String mail){
		this.oggetto.setObjectValue("Mail", ctrlString(mail));
	}
	
	protected void setUsername(String username){
		this.oggetto.setObjectValue("Username", ctrlString(username));
	}
	
	protected void setPassword(String password){
		this.oggetto.setObjectValue("Password", ctrlString(password));
	}
	
	protected static String ctrlString(String str){
		return str != "" ? str : null;
	}
	
	public boolean changePassword(String nome, String cognome, String mail, String username, String password, String newPassword){
		//mi accerto che si tratti dello stesso cliente richiedendo i dati
		if(this.getName().equals(nome) && this.getLastName().equals(cognome) && this.getUsername().equals(username) && this.getMail().equals(mail) && this.getPassword().equals(password)){
			this.setPassword(newPassword);
			return this.oggetto.salva();
		}
		else 
			return false;
	}

	public boolean deleteUser(String nome, String cognome, String mail, String username, String password){
		//mi accerto che si tratti dello stesso cliente richiedendo i dati
		if(this.getName().equals(nome) && this.getLastName().equals(cognome) && this.getUsername().equals(username) && this.getMail().equals(mail) && this.getPassword().equals(password))
			return this.oggetto.elimina();
		else 
			return false;
	}
	
	
}
