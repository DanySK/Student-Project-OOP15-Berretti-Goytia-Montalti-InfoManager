package com.goytia.models.DB;

import com.infoMng.controller.TableRow;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class modelSales implements modelSalesI {
	
	TableRow oggetto;
	//List<transactionsProducts> prodottiVenduti;
	
	protected modelSales(TableRow temp){
		this.oggetto = temp;
	}
	
	public modelSales(){
		this.oggetto = TableRow.oggettoDaTabella("Vendite");
	}
	
	protected void setNumberPaymentReceipt(int nRicevuta){
		this.oggetto.setObjectValue("nRicevuta", nRicevuta);
	}
	
	protected void setIVA(float iva){
		this.oggetto.setObjectValue("IVA", iva);
	}
	
	protected void setIDClient(Integer IDCliente){
		this.oggetto.setObjectValue("IDCliente", IDCliente);
	}
	
	protected void setDate(Date data){
		this.oggetto.setObjectValue("Data", data);
	}
	
	protected void setDiscount(float sconto){
		this.oggetto.setObjectValue("Sconto", sconto);
	}
	
	public Integer getID(){
		return this.oggetto.objectId();
	}
	
	public int getNumberPaymentReceipt(){
		return (int)this.oggetto.getObject("nRicevuta");
	}
	
	public Integer getIDClient(){
		return (Integer)this.oggetto.getObject("IDCliente");
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
	 * metodo per il slvataggio dei produtti venduti
	 * @param nRicevuta
	 * @param lista
	 * @return
	 * true o false a seconda dell'esito
	 */
	protected boolean builderProductsSale(int nRicevuta, List<transactionsProductsI> lista){
		
		return modelTransactionsI.transactionsProducts(nRicevuta, lista, true);
	}

	public boolean deleteSale(){

		if(modelTransactionsI.deleteTransactionsProducts(this.getNumberPaymentReceipt(), true))
			return this.oggetto.elimina();
		else 
			return false;
	}
	
	public List<transactionsProductsI> soldProducts(){
		return modelTransactionsI.transactionsList().stream()
				.filter( m -> m.getNumberPaymentRicevuta() == this.getNumberPaymentReceipt())
				.map( p -> {
					transactionsProducts prod = new transactionsProducts(p.getIDProduct(), Math.abs(p.getQuantity()), p.getPrice());
					return prod;
				})
				.collect(Collectors.toList());
	}
	
	public boolean renameSale(Integer newIDCliente, int newNRicevuta, float newIva, float newSconto, Date newData){
			if(!newIDCliente.equals(null))this.setNumberPaymentReceipt(newNRicevuta);
			if(newNRicevuta != -1)this.setNumberPaymentReceipt(newNRicevuta);
			if(newIva != -1)this.setIVA(newIva);
			if(newSconto != -1)this.setDiscount(newSconto);
			if(newData != null)this.setDate(newData);
			return this.oggetto.salva();
	}
	
	public double getTotalColleactions(){
		
		return this.soldProducts().stream()
				.mapToDouble(e -> e.getPrice() * e.getQuantity())
				.sum();
	}
}
