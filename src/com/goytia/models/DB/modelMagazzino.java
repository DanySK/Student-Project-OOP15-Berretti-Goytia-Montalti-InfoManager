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
	
	private void setFornitore(Integer IDFornitore){
		this.oggetto.setObjectValue("IDFornitore", IDFornitore);
	}
	
	private void setDescrizione(String descrizione){
		this.oggetto.setObjectValue("Descrizione", descrizione);
	}
	
	public Integer getID(){
		return this.oggetto.objectId();
	}
	
	public String getNome(){
		return (String) this.oggetto.getObject("Nome");
	}
	
	public int getQuantita(){
		return 0;
	}
	
	public Integer getFornitore(){
		return (Integer)this.oggetto.getObject("IDFornitore");
	}
	
	public String getDescrizione(){
		return (String)this.oggetto.getObject("Descrizione");
	}
	
	public static List<modelMagazzino> elenco(){
		MBQuery query = MBQuery.queryDaTabella("Magazzino");
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
	
	public static boolean nuovoProdotto(String nome, Integer IDFornitore, String descrizione) {
		modelMagazzino temp = new modelMagazzino(MBOggetto.oggettoDaTabella("Magazzino"));
		temp.setNome(nome);
		temp.setFornitore(IDFornitore);
		temp.setDescrizione(descrizione);
		return temp.oggetto.salva();
	}
	
	public static List<modelMagazzino> cercaProdotti(String nome){
		return modelMagazzino.elenco().stream()
				.filter(e-> e.getNome() == nome)
				.collect(Collectors.toList());
	}
	
	public static List<modelMagazzino> cercaProdotti(int quantitaMinima){
		return modelMagazzino.elenco().stream()
				.filter(e-> e.getQuantita() >= quantitaMinima)
				.collect(Collectors.toList());
	}
	
	/*public static List<modelMagazzino> cercaProdotti(String nomeFornitore){
		return modelMagazzino.elenco().stream()
				.filter(e-> e.getFornitore() == fornitore.getNome())
				.collect(Collectors.toList());
	}*/
	
}
