package com.goytia.models.DB;

import com.infoMng.controller.TableRow;

	class modelStore implements modelStoreI {
	
	TableRow oggetto;
	
	protected modelStore(TableRow temp){
		this.oggetto=temp;
	}
	
	protected modelStore(){
		this.oggetto = TableRow.oggettoDaTabella("Magazzino");
	}
	
	protected void setName(String nome){
		this.oggetto.setObjectValue("Nome", nome);
	}
	
	protected void setIDProvider(Integer IDFornitore){
		this.oggetto.setObjectValue("IDFornitore", IDFornitore);
	}
	
	protected void setProductDeatils(String descrizione){
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
		
		return 	modelTransactionsI.transactionsList().stream()
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

	
	public boolean deleteProduct(){
		return this.oggetto.elimina();
	}
	
}
