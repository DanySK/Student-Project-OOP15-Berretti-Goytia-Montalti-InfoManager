package com.goytia.models.DB;

import com.infoMng.controller.TableRow;

class modelClients implements modelClientsI{
	
	TableRow oggetto;
	
	protected modelClients(){
		this.oggetto= TableRow.oggettoDaTabella("Clienti");
	}
	
	/***
	 * crea un nuovo oggetto tipo modelCliente
	 */
	protected modelClients(TableRow temp){
		this.oggetto=temp;
	}
	
	public Integer getID(){
		return this.oggetto.objectId();
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
	
	public String getShopName(){
		return (String)this.oggetto.getObject("Negozio");
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
	
	protected void setShopName(String negozio){
		this.oggetto.setObjectValue("Negozio", ctrlString(negozio));
	}
	
	protected static String ctrlString(String str){
		return str != "" ? str : null;
	}

	
	public boolean deleteClient(){
		return this.oggetto.elimina();
	}
	
	public boolean renameClient(String newNome, String newCognome, String newMail, String newTelefono, String newNegozio){
			if(newNome != "")this.setName(newNome);
			if(newCognome != "")this.setLastName(newCognome);
			if(newMail != "")this.setMail(newMail);
			if(newTelefono != "")this.setPhone(newTelefono);
			return this.oggetto.salva();
	}
	
}
