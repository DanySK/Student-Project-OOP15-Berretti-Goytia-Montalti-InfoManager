package it.unibo.infomanager.infomng.model;

import java.sql.Date;

import it.unibo.infomanager.infomng.controller.TableRow;

class modelReunions implements modelReunionsI{
	
	TableRow oggetto;

	protected modelReunions(TableRow temp){
		this.oggetto = temp;
	}
	
	public modelReunions(){
		this.oggetto = TableRow.oggettoDaTabella("Riunioni");
	}
	
	protected void setNomeRiunione(String nome){
		this.oggetto.setObjectValue("Riunione", ctrlString(nome));
	}
	
	protected void setResponsible(String responsabile){
		this.oggetto.setObjectValue("Responsabile", ctrlString(responsabile));
	}
	
	protected void  setReferenzeResponsabile(String referenze){
		this.oggetto.setObjectValue("Referenze", ctrlString(referenze));
	}
	protected void setReunionDetails(String descrizione){
		this.oggetto.setObjectValue("Descrizione", ctrlString(descrizione));
	}
	
	protected void setDate(Date dataRiunione){
		this.oggetto.setObjectValue("Data", dataRiunione);
	}
	
	protected void setTime(String oraRiunione) {
		this.oggetto.setObjectValue("Ora", oraRiunione);
	}
	
	protected static String ctrlString(String str){
		return str != "" ? str : null;
	}

	public Integer getID(){
		return this.oggetto.objectId();
	}
	
	public String getNameReunion(){
		return (String)this.oggetto.getObject("Riunione");
	}
	
	public String getNameResponsible(){
		return (String)this.oggetto.getObject("Responsabile");
	}
	
	public String getReferences(){
		return (String)this.oggetto.getObject("Referenze");
	}
	
	public Date getDate(){
		return (Date)this.oggetto.getObject("Data");
	}
	
	public String getTime(){
		return (String) this.oggetto.getObject("Ora");
	}
	
	public String getReunionDetails(){
		return  (String)this.oggetto.getObject("Descrizione");
	}

	public boolean renameReunion(String newNome, String newResponsabile, String newReferenze, String newDescrizione, java.util.Date newDataEora){
		if(newNome!="")this.setNomeRiunione(newNome);
		if(newResponsabile!="")this.setResponsible(newResponsabile);
		if(newReferenze!="")this.setReferenzeResponsabile(newReferenze);
		if(newDescrizione!="")this.setReunionDetails(newDescrizione);
		if(newDataEora!=null){
			this.setDate(new java.sql.Date(newDataEora.getTime()));
			this.setTime(format.format(newDataEora));
		}
		return this.oggetto.salva();
	}

	public boolean deleteReunion(){
		return this.oggetto.elimina();
	}
}
