package com.infoMng.controller;

import java.lang.instrument.IllegalClassFormatException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.Optional;

public class Oggetto{
	protected Map<String, Object> dati;
	protected String nomeTabella;
	protected Set<String> campi;
	
	public Oggetto(String nomeTabella){
		this.nomeTabella = nomeTabella;
		this.dati = new HashMap<>();
		DataBase db = new DataBase();
		try{
			db.connetti();
			this.campi = db.creaTabella(this);
			db.disconnetti();
		}
		catch(SQLException e){
			e.printStackTrace();
			this.campi = new HashSet<>();
			this.campi.add("objectId");
			this.campi.add("Creazione");
			this.campi.add("Modifica");
		}
	}
	
	protected Oggetto(String nomeTabella, Map<String, Object> valori){
		this.nomeTabella = nomeTabella;
		this.dati = valori;
		this.campi = valori.keySet();
	}

	public Integer objectId(){
		return (Integer)this.dati.get("objectId");
	}
	
	private Date ora(){
		java.util.Date tmp = new java.util.Date();
		return new Date(tmp.getTime());
	}
	
	
	private void setObjectId(Integer value){
		this.dati.put("objectId", value);
	}
	public Optional<Date> creazione(){
		return Optional.ofNullable((Date)this.dati.get("Creazione"));
	}
	private void setCreazione(){
		this.dati.put("Creazione", this.ora());
	}
	public Optional<Date> modifica(){
		return Optional.ofNullable((Date)this.dati.get("Modifica"));
	}
	private void setModifica(){
		this.dati.put("Modifica", this.ora());
	}
	public Object getObject(String key){
		return this.dati.get(key);
	}
	
	public void setObjectValue(String key, Object value){
		if(value != null){
			this.dati.put(key, value);
		}
		else{
			this.dati.remove(key);
		}
	}

	public boolean salva() throws IllegalClassFormatException{
		this.setModifica();
		boolean ritorno;
		DataBase db = new DataBase();
		if(this.objectId() == null){
			this.setCreazione();
			Integer nuovo = -1;
			try {
				db.connetti();
				nuovo = db.inserisciOggetto(this);
				if(nuovo == -1){
					ritorno = false;
				}
				else{
					ritorno = true;
					this.setObjectId(nuovo);
				}
				db.disconnetti();
				return ritorno;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		else{
			try {
				db.connetti();
				db.aggiornaRecord(this);
				db.disconnetti();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public boolean elimina(){
		if(this.objectId() == null){
			return false;
		}
		DataBase db = new DataBase();
		try {
			db.connetti();
			db.eliminaRecord(this);
			db.disconnetti();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
