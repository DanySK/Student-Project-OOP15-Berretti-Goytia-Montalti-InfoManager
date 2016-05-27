package com.goytia.models.DB;

import com.infoMng.controller.MBOggetto;
import com.goytia.models.DB.*;
public class modelAcquisti {
	
	MBOggetto oggetto;
	
	private modelAcquisti(MBOggetto temp){
		this.oggetto = temp;
	}
	
	public modelAcquisti(){
		this.oggetto = MBOggetto.oggettoDaTabella("Acquisti");
	}
	
	private void setPrezzoAcquisto(double prezzoAcquisto){
		this.oggetto.setObjectValue("PrezzoAcquisto", prezzoAcquisto);
	}
	
	private void setIdPodotto(Integer id){
		this.oggetto.setObjectValue("IDProdotto", id);
	}
	
	private void setQuantita(int quantita){
		this.oggetto.setObjectValue("Quantita", quantita);
	}
	
	private static boolean newProdotto(String nome, Integer IDFornitore, String descrizione){
		return modelMagazzino.nuovoProdotto(nome, IDFornitore, descrizione);
	}
	
	private static boolean newFornitore(String nome, String cognome, String mail, String telefono){
		return modelFornitori.nuovoFornitore(nome, cognome, mail, telefono);
	}
	public Integer getID(){
		return this.oggetto.objectId();
	}
	
	public int getQuantita(){
		return (int)this.oggetto.getObject("Quantita");
	}
	
	public double getPrezzoAcquisto(){
		return (double)this.oggetto.getObject("PrezzoAcquisto");
	}
	
	public Integer getIdProdotto(){
		return (Integer)this.oggetto.getObject("IDProdotto");
	}
	
	public String getNomeProdotto(){
		return modelMagazzino.elenco().stream()
				.filter(p -> p.getID() == this.getIdProdotto())
				.map(a -> a.getNome())
				.findFirst()
				.toString();
	}
	
	public static boolean nuovoAcquistoProdttoEsistente(String nomeProdotto, int quantita, double prezzoAcquisto){
		
		modelAcquisti temp = new modelAcquisti(MBOggetto.oggettoDaTabella("Acquisti"));
		Integer idProd = modelMagazzino.elenco().stream()
				.filter(a -> a.getNome().equalsIgnoreCase(nomeProdotto))
				.map( i -> i.getID())
				.findFirst()
				.get();
		temp.setIdPodotto(idProd);
		temp.setPrezzoAcquisto(prezzoAcquisto);
		temp.setQuantita(quantita);
		return temp.oggetto.salva();
	}
	
	public static boolean newAcquistoProdottoNuovoFornitoreEsistente(String nomeProdotto, int quantita, double prezzoAcquisto, String descrizione, Integer IDFornitore){
		
		if(modelAcquisti.newProdotto(nomeProdotto, IDFornitore, descrizione))
		    return modelAcquisti.nuovoAcquistoProdttoEsistente(nomeProdotto, quantita, prezzoAcquisto);
		else
			return false;
	}
	
	public static boolean newAcquistoProdottoNuovoFornitoreNuovo(String nomeProdotto, int quantita, double prezzoAcquisto, String descrizione, String nomeFornitore, String cognomeFornitore, String mailFornitore, String telfFornitore ){
		
		if(modelAcquisti.newFornitore(nomeFornitore, cognomeFornitore, mailFornitore, telfFornitore)){
			Integer IDFornitore = modelFornitori.elenco().stream()
									.filter( f -> f.getNome().equalsIgnoreCase(nomeFornitore))
									.map(i -> i.getID())
									.findFirst()
									.get();
			if(modelAcquisti.newProdotto(nomeProdotto, IDFornitore, descrizione))
				return modelAcquisti.nuovoAcquistoProdttoEsistente(nomeProdotto, quantita, prezzoAcquisto);
			else
				return false;
		}
		else
			return false;
	}
	
}
