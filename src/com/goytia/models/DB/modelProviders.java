package com.goytia.models.DB;

import com.infoMng.controller.TableRow;

class modelProviders implements modelProvidersI{
	
	TableRow oggetto;
	
	protected modelProviders(TableRow temp){
		this.oggetto=temp;
	}
	/***
	 * ottieni un nuovo record della tabella Fornitori
	 */
	public modelProviders(){
		this.oggetto = TableRow.oggettoDaTabella("Fornitori");
	}
	
	public Integer getID(){
		return this.oggetto.objectId();
	}
	
	public String getIDFornitore(){
		return String.format("%i", this.oggetto.objectId());
	}
	
	public String getName(){
		return (String)this.oggetto.getObject("Nome");
	}
	
	public String getLastName(){
		return (String)this.oggetto.getObject("Cognome");
	}
	
	public String getMail(){
		return (String)this.oggetto.getObject("Mail");
	}
	
	public String getPhone(){
		return (String)this.oggetto.getObject("Telefono");
	}
	
	protected void setName(String nome){
		this.oggetto.setObjectValue("Nome", ctrlString(nome));
	}
	
	protected void setLastName(String cognome){
		this.oggetto.setObjectValue("Cognome", ctrlString(cognome));
	}
	
	protected void setMail(String mail){
		this.oggetto.setObjectValue("Mail", ctrlString(mail));
	}
	
	protected void setPhone(String telf){
		this.oggetto.setObjectValue("Telefono", ctrlString(telf));
	}
	
	protected static String ctrlString(String str){
		return str != "" ? str : null;
	}
	
	public boolean deleteProvider(){
		return this.oggetto.elimina();
	}

	public boolean renameProvider(String newNome , String newCognome, String newMail, String newTelefono){
		if(newNome != "")this.setName(newNome);
		if(newCognome != "")this.setLastName(newCognome);
		if(newMail != "")this.setMail(newMail);
		if(newTelefono != "")this.setPhone(newTelefono);
		return this.oggetto.salva();
	}
}