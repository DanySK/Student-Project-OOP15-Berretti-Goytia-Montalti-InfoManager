package com.infoMng.controller;

import java.util.Set;
import java.util.stream.Collectors;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

class Query implements MBQuery {
	private String nomeTabella;
	private Set<String> condizioni;
	
	public Query(String nomeTabella){
		this.nomeTabella = nomeTabella;
		this.condizioni = new HashSet<>();
	}
	
	public Query(String nomeTabella, String predicate){
		this(nomeTabella);
		this.condizioni.add(predicate);
	}
	
	@Override
	public void whereEqualTo(String key, Object value){
		this.condizioni.add(String.format("%s = '%s'", key, value.toString()));
	}
	
	@Override
	public void whereNotEqualTo(String key, Object value){
		this.condizioni.add(String.format("%s <> '%s'", key, value.toString()));
	}
	
	@Override
	public void whereKeyExists(String key){
		this.condizioni.add(String.format("%s is not null", key));
	}
	
	@Override
	public void whereKeyNotExists(String key){
		this.condizioni.add(String.format("%s is null", key));
	}
	
	private String generateSQL(){
		String sql = String.format("Select * from %s", this.nomeTabella);
		if(this.condizioni.size() > 0){
			sql = String.format("%s WHERE ", sql);
			String[] condizioni = this.condizioni.toArray(new String[this.condizioni.size()]);
			for(int i = 0; i < condizioni.length - 1; i++){
				sql = String.format("%s %s AND", sql, condizioni[i]);
			}
			sql = String.format("%s %s", sql, condizioni[condizioni.length - 1]);
		}
		return sql;
	}
	
	@Override
	public List<Oggetto> find() throws SQLException{
		DataBase db = new DataBase();
		db.connetti();
		List<Oggetto> ritorno = db.eseguiLettura(this.generateSQL()).stream()
				.map(d -> {
					return new Oggetto(this.nomeTabella, d);
				}).collect(Collectors.toList());
		db.disconnetti();
		return ritorno;
	}
	
	@Override
	public MBOggetto getFirst() throws SQLException{
		List<Oggetto> elenco = this.find();
		if(elenco.isEmpty()){
			return null;
		}
		else{
			return elenco.get(0);
		}
	}
}
