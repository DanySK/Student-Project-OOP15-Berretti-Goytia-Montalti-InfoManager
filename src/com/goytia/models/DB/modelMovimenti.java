package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelMovimenti {
	
	MBOggetto oggetto;
	
	private modelMovimenti(MBOggetto temp){
		this.oggetto = temp;
	}
	
	public modelMovimenti(){
		this.oggetto = MBOggetto.oggettoDaTabella("Movimenti");
	}
	
	public Integer getIDProdotto(){
		return (Integer) this.oggetto.getObject("IDProdotto");
	}
	
	public int getNricevuta(){
		return (int)this.oggetto.getObject("nRicevuta");
	}
	
	public int getQuantita(){
		return (int)this.oggetto.getObject("Quantita");
	}
	
	public double getPrezzo(){
		return (double)this.oggetto.getObject("Prezzo");
	}
	/***
	 * elenco con tutti i movimenti fatti
	 * @return
	 * una lista che contiene tutti i movimenti. ACquisto e vendite si diferenzano per la quantita in ogni movimento
	 * vendita -> quantita negativa acquisto->quantita positiva
	 */
	public static List<modelMovimenti> elenco(){
		
		MBQuery query = MBQuery.queryDaTabella("Clienti");
		try {
			return query.find().stream()
					.map(e -> new modelMovimenti(e))
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
	public static boolean prodottiNelMovimento(int nRicevuta, List<prodottoNelMovimento> lista, boolean ctrlVendita){
		
		boolean ctrl= true;
		for(prodottoNelMovimento p : lista){
			modelMovimenti temp = new modelMovimenti(MBOggetto.oggettoDaTabella("Movimenti"));
			temp.oggetto.setObjectValue("nRicevuta", nRicevuta );
			temp.oggetto.setObjectValue("IDProdotto", p.getIDProdottoV());
			if(ctrlVendita)
				temp.oggetto.setObjectValue("Quantita", -1 * p.getQuantita());
			else
				temp.oggetto.setObjectValue("Quantita", p.getQuantita());
			temp.oggetto.setObjectValue("Prezzo", p.getPrezzoUnitario());
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
	public static boolean elimnaProdottiDellMovimento(int nRicevuta, boolean ctrlVendita){
		List<modelMovimenti> temp;
		boolean ctrl = true;
		if(ctrlVendita){
			temp = modelMovimenti.elenco().stream()
			.filter(e -> e.getNricevuta()==nRicevuta)
			.filter(e -> e.getQuantita() < 0)
			.collect(Collectors.toList());
			
			for(modelMovimenti a : temp){ ctrl = a.oggetto.elimina(); }
			return ctrl;
		}
		else
		{
			temp = modelMovimenti.elenco().stream()
			.filter(e -> e.getNricevuta()==nRicevuta)
			.filter(e -> e.getQuantita() > 0)
			.collect(Collectors.toList());
					
			for(modelMovimenti a : temp){ ctrl = a.oggetto.elimina(); }
					return ctrl;
		}
	}
}
