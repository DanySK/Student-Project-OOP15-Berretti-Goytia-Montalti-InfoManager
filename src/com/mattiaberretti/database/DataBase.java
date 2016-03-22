package com.mattiaberretti.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class DataBase implements GestioneDB{
	private static String cartellaBase = System.getProperty("user.home") + File.separator;
	private Connection c;
	private Boolean connesso;
	
	
	protected DataBase(){
		this.c = null;
		this.connesso = false;
	}
	
	@Override
	public void connetti() throws SQLException, ClassNotFoundException{
		if(this.connesso){
			return;
		}
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:"+ DataBase.cartellaBase+ "info-mng.db");
		this.connesso = true;
	}
	
	@Override
	public void disconnetti() throws SQLException{
		if(this.connesso){
			c.close();
		}
	}

	@Override
	public List<Map<String, Object>> eseguiLettura(String[] campi, String tabella) throws SQLException{
		if(this.connesso == false){
			return null;
		}
		
		String sql = "SELECT ";
		for(int i = 0; i < campi.length - 1; i++){
			sql = String.format("%s %s,", sql, campi[i]);
		}
		if(campi.length > 0){
			sql = String.format("%s %s ", sql, campi[campi.length-1]);
		}
		
		sql = String.format("%s FROM %s", sql, tabella);
		
		Statement stmt = c.createStatement();
		
		ResultSet result = stmt.executeQuery(sql);
		
		List<Map<String, Object>> ritorno = new ArrayList<>();
		
		while(result.next()){
			Map<String, Object> riga = new HashMap<>();
			
			for(String campo : campi){
				riga.put(campo, result.getObject(campo));
			}
			ritorno.add(riga);
		}
		result.close();
		
		return ritorno;
	}

	public void creaTabella(GestioneDB.Colonna[] colonne, String nomeTabella) throws SQLException{
		if(this.connesso){
			String sql = "CREATE TABLE IF NOT EXISTS " + nomeTabella + "(";
			for(int i = 0; i < colonne.length - 1; i++){
				sql = String.format("%s %s,", sql, colonne[i].toString());
			}
			if(colonne.length > 0){
				sql = String.format("%s %s)", sql, colonne[colonne.length-1].toString());
			}
			Statement stmt = c.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		}
	}

	public void inserisciRecord(String nomeTabella, Map<String, Object> colonnaValore) throws SQLException{
		String sql = "INSERT INTO " + nomeTabella + "(";
		for(int i = 0; i < colonnaValore.keySet().size()-1; i++){
			sql = String.format("%s %s,", sql, colonnaValore.keySet().toArray()[i].toString());
		}
		if(colonnaValore.size() > 0){
			sql = String.format("%s %s)", sql, colonnaValore.keySet().toArray()[colonnaValore.size()-1].toString());
		}
		
		sql = sql + " VALUES (";
		for(int i = 0; i < colonnaValore.values().size()-1; i++){
			sql = String.format("%s '%s',", sql, colonnaValore.get(colonnaValore.keySet().toArray()[i]).toString());
		}
		if(colonnaValore.size() > 0){
			sql = String.format("%s '%s');", sql, colonnaValore.get(colonnaValore.keySet().toArray()[colonnaValore.size()-1]).toString() );
		}
		
		Statement stmt = this.c.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
	}
	
	public void aggiornaTabella(String nomeTabella, Map<String, Object> colonnaValore, String nomeChiave, Object[] valoriConfronto) throws SQLException{
		String sql = "UPDATE TABLE " + nomeTabella + "SET ";
		
		for (int i = 0; i < colonnaValore.size() - 1; i++){
			String chiave = colonnaValore.keySet().toArray()[i].toString();
			String valore = String.format("'%s'", colonnaValore.get(chiave).toString());
			
			sql = String.format("%s %s = '%s',", sql, chiave, valore);
		}
		if(colonnaValore.size() > 0){
			String chiave = colonnaValore.keySet().toArray()[colonnaValore.size()-1].toString();
			String valore = String.format("'%s'", colonnaValore.get(chiave).toString());
			
			sql = String.format("%s %s = '%s'", sql, chiave, valore);
		}
		
		sql = String.format("%s WHERE", sql);
		
		for (int i = 0; i < valoriConfronto.length; i++){
			sql = String.format("%s %s = '%s'", sql, nomeChiave, valoriConfronto[i].toString());
			if(i < valoriConfronto.length - 1){
				sql = String.format("%s OR", sql);
			}
		}
		
		Statement stmt = c.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
	}

	@Override
	public void eliminaRecord(String nomeTabella, Optional<String> nomeChiave, Optional<Object> valoreConfronto) throws SQLException {
		String sql = "DELETE FROM " + nomeTabella;
		if(nomeChiave.isPresent()){
			sql = String.format("%s WHERE %s = '%s'", sql, nomeChiave.get(), valoreConfronto.get().toString());
		}
		
		Statement stmt = c.createStatement();
		stmt.executeUpdate(sql);
	}
	
}
