package com.goytia.models.DB;

import com.infoMng.controller.TableRow;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

class modelPurchases implements modelPurchasesI{
	
	TableRow oggetto;
	
	protected modelPurchases(TableRow temp){
		this.oggetto = temp;
	}
	
	public modelPurchases(){
		this.oggetto = TableRow.oggettoDaTabella("Acquisti");
	}
	
	protected void setNumberPaymentReceipt(int nRicevuta){
		this.oggetto.setObjectValue("nRicevuta", nRicevuta);
	}
	
	protected void setIVA(float iva){
		this.oggetto.setObjectValue("IVA", iva);
	}
	
	public Integer getID(){
		return this.oggetto.objectId();
	}
	
	protected void setProvider(Integer IDFornitore){
		this.oggetto.setObjectValue("IDFornitore", IDFornitore);
	}
	
	protected void setDate(Date data){
		this.oggetto.setObjectValue("Data", data);
	}
	
	protected void setDiscount(float sconto){
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
	
	//salvataggio degli elementi acquistati
	protected boolean builderProductsPurchases(int nRicevuta, List<transactionsProductsI> lista){
		return modelTransactionsI.transactionsProducts(nRicevuta, lista, false);
	}
	
	public List<transactionsProducts> purchasedProducts(){
		return modelTransactionsI.transactionsList().stream()
				.filter( m -> m.getNumberPaymentRicevuta() == this.getNumberPaymentReceipt())
				.map( p -> {
					return new transactionsProducts(p.getIDProduct(), Math.abs(p.getQuantity()), p.getPrice());
				})
				.collect(Collectors.toList());
				
	}
	
	/***
	 * eliminazione dell'accquisto corrente
	 * @return
	 * true o false a seconda del esito
	 */
	public boolean deletePurchase(){
		
		if(modelTransactionsI.deleteTransactionsProducts(this.getNumberPaymentReceipt(), false))
			return this.oggetto.elimina();
		else 
			return false;
	}
	
	/*
	 * calcolo del totale speso in un acquisto
	 */
	public Double getTotalSpent(){
		return purchasedProducts().stream()
		.mapToDouble(e -> e.getPrice() * e.getQuantity())
		.sum();
	}
}
