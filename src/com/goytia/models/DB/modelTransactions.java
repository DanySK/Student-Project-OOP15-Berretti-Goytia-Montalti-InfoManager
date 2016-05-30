package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelTransactions {
	
	MBOggetto oggetto;
	
	private modelTransactions(MBOggetto temp){
		this.oggetto = temp;
	}
	
	public modelTransactions(){
		this.oggetto = MBOggetto.oggettoDaTabella("Movimenti");
	}
	
	public Integer getID(){
		return this.oggetto.objectId();
	}
	
	public Integer getIDProduct(){
		return (Integer) this.oggetto.getObject("IDProdotto");
	}
	
	public int getNumberPaymentRicevuta(){
		return (int)this.oggetto.getObject("nRicevuta");
	}
	
	public int getQuantity(){
		return (int)this.oggetto.getObject("Quantita");
	}
	
	public double getPrice(){
		return (double)this.oggetto.getObject("Prezzo");
	}
	/***
	 * elenco con tutti i movimenti fatti
	 * @return
	 * una lista che contiene tutti i movimenti. ACquisto e vendite si diferenzano per la quantita in ogni movimento
	 * vendita -> quantita negativa acquisto->quantita positiva
	 */
	public static List<modelTransactions> transactionsList(){
		
		MBQuery query = MBQuery.queryDaTabella("Movimenti");
		try {
			return query.find().stream()
					.map(e -> new modelTransactions(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/***
	 * nuovo movimento effettato
	 * @param nRicevuta
	 * @param lista
	 * lista tipo prodottoVenduto contenente tutti i prodotti coinvolti nel movimento
	 * @param ctrlVendita
	 * boolean che controlla se si tratta di una vendita(true) o acquisto(false)
	 * @return
	 * true o false a seconda dell'esito
	 */
	public static boolean transactionsProducts(int nRicevuta, List<transactionsProducts> lista, boolean ctrlVendita){
		
		boolean ctrl= true;
		for(transactionsProducts p : lista){
			modelTransactions temp = new modelTransactions(MBOggetto.oggettoDaTabella("Movimenti"));
			temp.oggetto.setObjectValue("nRicevuta", nRicevuta );
			temp.oggetto.setObjectValue("IDProdotto", p.getIDProductInvolved());
			if(ctrlVendita)
				temp.oggetto.setObjectValue("Quantita", -1 * p.getQuantity());
			else
				temp.oggetto.setObjectValue("Quantita", p.getQuantity());
			temp.oggetto.setObjectValue("Prezzo", p.getPrice());
			ctrl=temp.oggetto.salva();
		}
		return ctrl;
	}
	/***
	 * metodo per eliminaare tutti i prodotti di una vendita o aquisto sbagliti
	 * @param nRicevuta
	 * nRicevuta, neceessario per cancellare la vendita
	 * @param ctrlVendita
	 * ture se si tratta di una vendita, false se si tratta di un acquisto
	 * @return
	 * true o false a secoda del esito
	 */
	public static boolean deleteTransactionsProducts(int nRicevuta, boolean ctrlVendita){
		List<modelTransactions> temp;
		boolean ctrl = true;
		if(ctrlVendita){
			temp = modelTransactions.transactionsList().stream()
			.filter(e -> e.getNumberPaymentRicevuta()==nRicevuta)
			.filter(e -> e.getQuantity() < 0)
			.collect(Collectors.toList());
			
			for(modelTransactions a : temp){ ctrl = a.oggetto.elimina(); }
			return ctrl;
		}
		else
		{
			temp = modelTransactions.transactionsList().stream()
			.filter(e -> e.getNumberPaymentRicevuta()==nRicevuta)
			.filter(e -> e.getQuantity() > 0)
			.collect(Collectors.toList());
					
			for(modelTransactions a : temp){ ctrl = a.oggetto.elimina(); }
					return ctrl;
		}
	}
}
