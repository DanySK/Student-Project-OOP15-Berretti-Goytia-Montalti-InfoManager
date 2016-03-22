package com.mattiaberretti.utenti;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

import com.mattiaberretti.database.GestioneDB;

public interface IUtente {

	static CurrentUser utenteCorrente = new CurrentUser();
	
	static Optional<IUtente> accedi(String username, String password) throws SQLException, ClassNotFoundException{
		GestioneDB db = GestioneDB.generaControllore();
		db.connetti();
		Optional<Map<String, Object>> dati = db.eseguiLettura(new String[]{"IDUtente", "Nome", "Cognome", "Username", "Password"}, "Utenti").stream()
				.filter(e -> e.get("Username").equals(username))
				.filter(e -> e.get("Password").equals(password))
				.findFirst();
		db.disconnetti();
		if(dati.isPresent() == false){
			return Optional.empty();
		}
		
		IUtente ritorno = new Utente((Integer)dati.get().get("IDUtente"), 
				(String)dati.get().get("Nome"), 
				(String)dati.get().get("Cognome"),
				(String)dati.get().get("Username"),
				(String)dati.get().get("Password"));
		utenteCorrente.setUtenteCorrente(ritorno);
		return Optional.of(ritorno);
	}
	
	
	Integer getIDUtente();

	String getNome();

	String getCognome();

	String getUsername();

	String getPassword();

	void setNome(String nome);

	void setCognome(String cognome);

	void setUsername(String username);

	void setPassword(String password);

	public class CurrentUser{
		private Optional<IUtente> utente;
		
		public CurrentUser(){
			this.utente = Optional.empty();
		}
		
		public void setUtenteCorrente(IUtente nuovo){
			this.utente = Optional.ofNullable(nuovo);
		}
		
		public Optional<IUtente> getUtenteCorrente(){
			return this.utente;
		}
	}
}