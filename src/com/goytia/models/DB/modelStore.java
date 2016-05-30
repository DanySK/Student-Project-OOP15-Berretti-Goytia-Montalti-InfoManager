package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelStore {
	
	MBOggetto oggetto;
	
	private modelStore(MBOggetto temp){
		this.oggetto=temp;
	}
	
	public modelStore(){
		this.oggetto = MBOggetto.oggettoDaTabella("Magazzino");
	}
	
	private void setName(String nome){
		this.oggetto.setObjectValue("Nome", nome);
	}
	
	private void setIDProvider(Integer IDFornitore){
		this.oggetto.setObjectValue("IDFornitore", IDFornitore);
	}
	
	private void setProductDeatils(String descrizione){
		this.oggetto.setObjectValue("Descrizione", descrizione);
	}
	
	public Integer getID(){
		return this.oggetto.objectId();
	}
	
	public String getName(){
		return (String) this.oggetto.getObject("Nome");
	}
	/***
	 * metodo che calola la quantita di un oggetto tramite un suo ID
	 * @param IDProdotto
	 * ID del prodotto da ottenere la quantita
	 * @return
	 * quantita del prodotto nel magazzzino
	 */
	public int getQuantity(Integer IDProdotto){
		
		return 	modelTransactions.transactionsList().stream()
				.filter(p -> p.getIDProduct().equals(IDProdotto))
				.mapToInt( m -> m.getQuantity())
				.sum();
	}
	
	public Integer getIDProvider(){
		return (Integer)this.oggetto.getObject("IDFornitore");
	}
	
	public String getProductDetails(){
		return (String)this.oggetto.getObject("Descrizione");
	}
	/***
	 * elenco di tutti i prodotti presenti nel magazzino
	 * @return
	 * una lista con tutti i prodotti
	 */
	public static List<modelStore> elenco(){
		MBQuery query = MBQuery.queryDaTabella("Magazzino");
		try {
			return query.find().stream()
					.map(e -> new modelStore(e))
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
	public static boolean newProduct(String nome, Integer IDFornitore, String descrizione) {
		modelStore temp = new modelStore(MBOggetto.oggettoDaTabella("Magazzino"));
		temp.setName(nome);
		temp.setIDProvider(IDFornitore);
		temp.setProductDeatils(descrizione);
		return temp.oggetto.salva();
	}
	/***
	 * cerca un prodotto tramine il nome
	 * @param nome
	 * nome del prodotto
	 * @return
	 * il/i prodotti trovati
	 */
	public static List<modelStore> serachProductsByName(String nome){
		return modelStore.elenco().stream()
				.filter(e-> e.getName().equalsIgnoreCase(nome))
				.collect(Collectors.toList());
	}
	/***
	 * ricerca dei prodotti con una quantita minima
	 * @param quantitaMinima
	 * @return
	 * una lista che contiene i prodotti trovati
	 */
	public static List<modelStore> searchProductsByQuantity(int quantitaMinima){
		return modelStore.elenco().stream()
				.filter(e-> e.getQuantity(e.getID()) >= quantitaMinima)
				.collect(Collectors.toList());
	}
	/***
	 * ricerca di prodotti di un determinato fornitore
	 * @param IDFornitore
	 * @return
	 * i prodotti trovati
	 */
	public static List<modelStore> serachProductsByProvider(Integer IDFornitore){
		return modelStore.elenco().stream()
				.filter(e-> e.getIDProvider().equals(IDFornitore))
				.collect(Collectors.toList());
	}
	
	public boolean deleteProduct(){
		return this.oggetto.elimina();
	}
}
