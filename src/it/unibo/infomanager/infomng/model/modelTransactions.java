package it.unibo.infomanager.infomng.model;

import it.unibo.infomanager.infomng.controller.TableRow;

public class modelTransactions implements modelTransactionsI{
	
	TableRow oggetto;
	
	protected modelTransactions(TableRow temp){
		this.oggetto = temp;
	}
	
	public modelTransactions(){
		this.oggetto = TableRow.oggettoDaTabella("Movimenti");
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
	
	public boolean deleteTransactions(){
		return this.oggetto.elimina();
	}

}
