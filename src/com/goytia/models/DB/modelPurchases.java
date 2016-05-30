package com.goytia.models.DB;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
public class modelPurchases {
	
	MBOggetto oggetto;
	
	private modelPurchases(MBOggetto temp){
		this.oggetto = temp;
	}
	
	public modelPurchases(){
		this.oggetto = MBOggetto.oggettoDaTabella("Acquisti");
	}
	
	private void setNumberPaymentReceipt(int nRicevuta){
		this.oggetto.setObjectValue("nRicevuta", nRicevuta);
	}
	
	private void setIVA(float iva){
		this.oggetto.setObjectValue("IVA", iva);
	}
	
	public Integer getID(){
		return this.oggetto.objectId();
	}
	
	private void setProvider(Integer IDFornitore){
		this.oggetto.setObjectValue("IDFornitore", IDFornitore);
	}
	
	private void setDate(Date data){
		this.oggetto.setObjectValue("Data", data);
	}
	
	private void setDiscount(float sconto){
		this.oggetto.setObjectValue("Sconto", sconto);
	}
	
	public int getNumberPaymentReceipt(){
		return (int)this.oggetto.getObject("nRicevuta");
	}
	
	public Integer getIDProvider(){
		return (Integer)this.oggetto.getObject("IDFornitore");
	}
	
	public float getIva(){
		return (float)this.oggetto.getObject("IVA");
	}
	
	public Date getDate(){
		return (Date)this.oggetto.getObject("Data");
	}
	
	public float getDiscount(){
		return (float)this.oggetto.getObject("Sconto");
	}
	
	/***
	 * elenco di tutti gli acquisti realizzati
	 * @return
	 * una lista contenente tutti gli acquisti fatti
	 */
	public static List<modelPurchases> purchasesList(){
		MBQuery query = MBQuery.queryDaTabella("Acquisti");
		try {
			return query.find().stream()
					.map(e -> new modelPurchases(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/***
	 * Aggiunta di un nuovo acquisto
	 * @param IDFornitore
	 * @param nRicevuta
	 * @param iva
	 * @param sconto
	 * @param data
	 * @param prodotti
	 * @return
	 * true se è stato aggiunt nel db altrimenti false
	 */
	public static boolean newPurchase(Integer IDFornitore, int nRicevuta, float iva, float sconto, Date data, List<transactionsProducts> prodotti){
		
		modelPurchases temp = new modelPurchases(MBOggetto.oggettoDaTabella("Acquisti"));
		temp.setProvider(IDFornitore);
		temp.setDiscount(sconto);
		temp.setDate(data);
		temp.setIVA(iva);
		temp.setNumberPaymentReceipt(nRicevuta);
		if(modelPurchases.builderProductsPurchases(nRicevuta, prodotti))
			return temp.oggetto.salva();
		else 
			return false;
	}
	//salvataggio degli elementi acquistati
	private static boolean builderProductsPurchases(int nRicevuta, List<transactionsProducts> lista){
		return modelTransactions.transactionsProducts(nRicevuta, lista, false);
	}
	
	public List<transactionsProducts> purchasedProducts(){
		return modelTransactions.transactionsList().stream()
				.filter( m -> m.getNumberPaymentRicevuta() == this.getNumberPaymentReceipt())
				.map( p -> {
					transactionsProducts prod = new transactionsProducts(p.getIDProduct(), Math.abs(p.getQuantity()), p.getPrice());
					return prod;
				})
				.collect(Collectors.toList());
				
	}
	
	/***
	 * eliminazione dell'accquisto corrente
	 * @return
	 * true o false a seconda del esito
	 */
	public boolean deletePurchase(){
		
		if(modelTransactions.deleteTransactionsProducts(this.getNumberPaymentReceipt(), false))
			return this.oggetto.elimina();
		else 
			return false;
	}
}
