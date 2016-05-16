package com.mattiaberretti.fornitori;

import java.sql.SQLException;

class Fornitore implements IFornitore {
	private Integer idFornitore;
	private String nome;
	private String telefono;
	private String email;
	
	protected Fornitore(Integer idFornitore, String nome, String telefono, String email) {
		super();
		this.idFornitore = idFornitore;
		this.nome = nome;
		this.telefono = telefono;
		this.email = email;
	}
	
	@Override
	public Integer getIdFornitore() {
		return idFornitore;
	}
	@Override
	public String getNome() {
		return nome;
	}
	@Override
	public String getTelefono() {
		return telefono;
	}
	@Override
	public String getEmail() {
		return email;
	}
	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public void salva() throws SQLException, ClassNotFoundException{
		//TODO: creare modifica
	}
	
	@Override
	public void elimina() throws SQLException, ClassNotFoundException{
		//TODO: creare eliminazione
	}
	
}
