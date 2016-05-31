package com.goytia.models.DB;

import com.infoMng.controller.MBOggetto;

public class modelTransactions implements modelTransactionsI{
	
	MBOggetto oggetto;
	
	protected modelTransactions(MBOggetto temp){
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

}
