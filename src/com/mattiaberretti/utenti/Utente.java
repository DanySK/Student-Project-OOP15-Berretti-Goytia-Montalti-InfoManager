package com.mattiaberretti.utenti;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mattiaberretti.database.GestioneDB;

class Utente implements IUtente {
	private Integer IDUtente;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String nomeNegozio;
	private String mail;
	
	protected Utente(Integer IDUtente, String nome, String cognome, String username, String password, String mail, String nomeNegozio){
		this.IDUtente = IDUtente;
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
		this.username = username;
		this.mail = mail;
		this.nomeNegozio = nomeNegozio;
	}
	
	@Override
	public Integer getIDUtente(){
		return this.IDUtente;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public String getCognome() {
		return cognome;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNomeNegozio() {
		return nomeNegozio;
	}

	public String getMail() {
		return mail;
	}

	public void setNomeNegozio(String nomeNegozio) {
		this.nomeNegozio = nomeNegozio;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public void salva() throws ClassNotFoundException, SQLException{
		GestioneDB db = GestioneDB.generaControllore();
		db.connetti();
		Map<String, Object> valori = new HashMap<>();
		valori.put("Nome", this.nome);
		valori.put("Cognome", this.cognome);
		valori.put("Username", this.username);
		valori.put("Password", this.password);
		valori.put("Mail", this.mail);
		valori.put("NomeNegozio", this.nomeNegozio);
		
		db.aggiornaTabella("Utenti", valori, "IDUtente", new Object[]{this.IDUtente});
		db.disconnetti();
	}
	
	
}
