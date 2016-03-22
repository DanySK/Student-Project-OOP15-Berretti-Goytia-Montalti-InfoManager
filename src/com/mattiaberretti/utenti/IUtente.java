package com.mattiaberretti.utenti;

import java.sql.SQLException;
import java.util.HashMap;
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
	
	public static Optional<IUtente> registrati(String nome, String cognome, String username, String password) throws ClassNotFoundException, SQLException{
		IUtente ritorno = null;
		GestioneDB db = GestioneDB.generaControllore();
		db.connetti();
		Boolean ok = db.eseguiLettura(new String[]{"Username"}, "Utenti").stream()
				.filter(w -> w.get("Username").equals(username))
				.count() == 0;
		
		if(ok){
			Map<String, Object> valori = new HashMap<>();
			valori.put("Nome", nome);
			valori.put("Cognome", cognome);
			valori.put("Username", username);
			valori.put("Password", password);
			db.inserisciRecord("Utenti", valori);
			
			Integer idUtente = db.eseguiLettura(new String[]{"IDUtente"}, "Utenti").stream()
					.mapToInt(w -> (Integer)w.get("IDUtente"))
					.max().getAsInt();
			
			ritorno = new Utente(idUtente, nome, cognome, username, password);
			IUtente.utenteCorrente.setUtenteCorrente(ritorno);
			
		}
		
		db.disconnetti();
		
		return Optional.ofNullable(ritorno);
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