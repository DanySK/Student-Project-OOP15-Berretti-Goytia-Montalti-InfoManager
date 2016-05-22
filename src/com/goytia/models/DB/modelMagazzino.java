package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelMagazzino {
	
	MBOggetto oggetto;
	
	private modelMagazzino(MBOggetto temp){
		this.oggetto=temp;
	}
	
	public modelMagazzino(){
		this.oggetto = MBOggetto.oggettoDaTabella("Magazzino");
	}
	
	private void setNome(String nome){
		this.oggetto.setObjectValue("Nome", nome);
	}
	
	private void setQuantita(int quantita){
		this.oggetto.setObjectValue("Quantita", quantita);
	}
	
	private void setFornitore(modelFornitori fornitore){
		this.oggetto.setObjectValue("Fornitore", fornitore.oggetto.getObject("nome"));
	}
	
	private void setPrezzoAcquisto(double prezzo){
		this.oggetto.setObjectValue("PrezzoAcquisto", prezzo);
	}
	
	private void setPrezzoVendita(double prezzo){
		this.oggetto.setObjectValue("PrezzoVendita", prezzo);
	}
	
	private void setDescrizione(String descrizione){
		this.oggetto.setObjectValue("Descrizione", descrizione);
	}
	
	public String getNome(){
		return (String) this.oggetto.getObject("Nome");
	}
	
	public int getQuantita(){
		return (int)this.oggetto.getObject("Quantita");
	}
	
	public String getFornitore(){
		return (String)this.oggetto.getObject("Fornitore");
	}
	
	public Double getPrezzoAcquisto(){
		return (Double)this.oggetto.getObject("PrezzoAcquisto");
	}
	
	public Double getPrezzoVendita(){
		return (Double)this.oggetto.getObject("PrezzoVendita");
	}
	
	public String getDescrizione(){
		return (String)this.oggetto.getObject("Descrizione");
	}
	
	public static List<modelMagazzino> elenco(){
		MBQuery query = MBQuery.queryDaTabella("Clienti");
		try {
			return query.find().stream()
					.map(e -> new modelMagazzino(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean nuovoProdotto(String nome, int quantita, modelFornitori fornitore, double prezzoAcquisto, double prezzoVendita, String descrizione) {
		modelMagazzino temp = new modelMagazzino(MBOggetto.oggettoDaTabella("Magazzino"));
		temp.setNome(nome);
		temp.setQuantita(quantita);
		temp.setFornitore(fornitore);
		temp.setPrezzoAcquisto(prezzoAcquisto);
		temp.setPrezzoVendita(prezzoVendita);
		return temp.oggetto.salva();
	}
	/*
	public static List<modelMagazzino> cercaProdotto(String nome){
		
	}*/
}
