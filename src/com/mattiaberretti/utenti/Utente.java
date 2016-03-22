package com.mattiaberretti.utenti;

class Utente implements IUtente {
	private Integer IDUtente;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	
	protected Utente(Integer IDUtente, String nome, String cognome, String username, String password){
		this.IDUtente = IDUtente;
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
		this.username = username;
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
	
	
}
