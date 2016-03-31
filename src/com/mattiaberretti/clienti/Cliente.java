package com.mattiaberretti.clienti;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mattiaberretti.database.GestioneDB;

class Cliente implements ICliente {
	private Integer idCliente;
	private String nome;
	private String cognome;
	private String email;
	private String telefono;
	
	protected Cliente(Integer idCliente, String nome, String cognome, String email, String telefono) {
		this.idCliente = idCliente;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.telefono = telefono;
	}

	@Override
	public Integer getIDCliente(){
		return this.idCliente;
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
	public String getEmail() {
		return email;
	}

	@Override
	public String getTelefono() {
		return telefono;
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
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public void salva() throws ClassNotFoundException, SQLException{
		GestioneDB db = GestioneDB.generaControllore();
		db.connetti();

		Map<String, Object> valori = new HashMap<>();
		valori.put("Nome", this.nome);
		valori.put("Cognome", this.cognome);
		valori.put("Mail", this.email);
		valori.put("Telefono", this.telefono);
		
		db.aggiornaTabella("Clienti", valori, "IDCliente", new Object[]{this.idCliente});
		
		db.disconnetti();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Cliente && ((Cliente)obj).getIDCliente().equals(this.getIDCliente());
	}
}
