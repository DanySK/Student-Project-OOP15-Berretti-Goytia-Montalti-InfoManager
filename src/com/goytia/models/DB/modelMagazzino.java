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
	/***
	 * metodo che calola la quantita di un oggetto tramite un suo ID
	 * @param IDProdotto
	 * ID del prodotto da ottenere la quantita
	 * @return
	 * quantita del prodotto nel magazzzino
	 */
	public int getQuantita(Integer IDProdotto){
		
		return 	modelMovimenti.elenco().stream()
				.filter(p -> p.getIDProdotto().equals(IDProdotto))
				.mapToInt( m -> m.getQuantita())
				.sum();
	}
	
	public Integer getFornitore(){
		return (Integer)this.oggetto.getObject("IDFornitore");
	}
	
	public String getDescrizione(){
		return (String)this.oggetto.getObject("Descrizione");
	}
	/***
	 * elenco di tutti i prodotti presenti nel magazzino
	 * @return
	 * una lista con tutti i prodotti
	 */
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
	/***
	 * creazione di un nuovo prodotto
	 * @param nome
	 * nome del prodotto
	 * @param IDFornitore
	 * ID del fornitore del quale si è aquisito il prodotto
	 * @param descrizione
	 * descrizione del prodotto
	 * @return
	 */
	public static boolean nuovoProdotto(String nome, Integer IDFornitore, String descrizione) {
		modelMagazzino temp = new modelMagazzino(MBOggetto.oggettoDaTabella("Magazzino"));
		temp.setNome(nome);
		temp.setFornitore(IDFornitore);
		temp.setDescrizione(descrizione);
		return temp.oggetto.salva();
	}
	/***
	 * cerca un prodotto tramine il nome
	 * @param nome
	 * nome del prodotto
	 * @return
	 * il/i prodotti trovati
	 */
	public static List<modelMagazzino> cercaProdottiDalNome(String nome){
		return modelMagazzino.elenco().stream()
				.filter(e-> e.getNome().equalsIgnoreCase(nome))
				.collect(Collectors.toList());
	}
	/***
	 * ricerca dei prodotti con una quantita minima
	 * @param quantitaMinima
	 * @return
	 * una lista che contiene i prodotti trovati
	 */
	public static List<modelMagazzino> cercaProdottiDisponibili(int quantitaMinima){
		return modelMagazzino.elenco().stream()
				.filter(e-> e.getQuantita(e.getID()) >= quantitaMinima)
				.collect(Collectors.toList());
	}
	/***
	 * ricerca di prodotti di un determinato fornitore
	 * @param IDFornitore
	 * @return
	 * i prodotti trovati
	 */
	public static List<modelMagazzino> cercaProdottiDalFornitore(Integer IDFornitore){
		return modelMagazzino.elenco().stream()
				.filter(e-> e.getFornitore().equals(IDFornitore))
				.collect(Collectors.toList());
	}
	
	public boolean eliminaProdotto(){
		return this.oggetto.elimina();
	}
}
