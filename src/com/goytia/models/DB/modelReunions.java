package com.goytia.models.DB;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelReunions {
	
	private DateFormat format= new SimpleDateFormat("HH:mm:ss");
	
	MBOggetto oggetto;

	private modelReunions(MBOggetto temp){
		this.oggetto = temp;
	}
	
	public modelReunions(){
		this.oggetto = MBOggetto.oggettoDaTabella("Riunioni");
	}
	
	private void setNomeRiunione(String nome){
		this.oggetto.setObjectValue("Riunione", ctrlString(nome));
	}
	
	private void setResponsible(String responsabile){
		this.oggetto.setObjectValue("Responsabile", ctrlString(responsabile));
	}
	
	private void  setReferenzeResponsabile(String referenze){
		this.oggetto.setObjectValue("Referenze", ctrlString(referenze));
	}
	private void setReunionDetails(String descrizione){
		this.oggetto.setObjectValue("Descrizione", ctrlString(descrizione));
	}
	
	private void setDate(Date dataRiunione){
		this.oggetto.setObjectValue("Data", dataRiunione);
	}
	
	private void setTime(String oraRiunione) {
		this.oggetto.setObjectValue("Ora", oraRiunione);
	}
	
	private static String ctrlString(String str){
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
	
	/***
	 * ritorna l'elenco con tutte le riunioni nel DB
	 * @return
	 * una lista contenente tutte le riunioni
	 */
	public static List<modelReunions> reunionsList(){
		MBQuery query = MBQuery.queryDaTabella("Riunioni");
		try {
			return query.find().stream()
					.map(e -> new modelReunions(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/***
	 * Creazione di una nuova riunione
	 * @param nomeRiunione
	 * @param nomeResponsabile
	 * @param referenze
	 * referenze del responsabile, come contattarlo per ulteriori informazione
	 * @param descrizione
	 * motivo della riunione
	 * @param dataEora
	 * data e ora della riunione
	 * @return
	 * true o false a seconda del esito
	 */
	public boolean newReunion(String nomeRiunione, String nomeResponsabile, String referenze, String descrizione, java.util.Date dataEora){
		modelReunions temp = new modelReunions(MBOggetto.oggettoDaTabella("Riunioni"));
		temp.setNomeRiunione(nomeRiunione);
		temp.setResponsible(nomeResponsabile);
		temp.setReferenzeResponsabile(referenze);
		temp.setReunionDetails(descrizione);
		temp.setDate(new java.sql.Date(dataEora.getTime()));
		temp.setTime(format.format(dataEora));
		return temp.oggetto.salva();
	}
	/***
	 * ritorna un elenco di tutte le riunione scadute o passate fino a quel momento
	 * @param dataCorrente
	 * @return
	 * una lista con tutte le reunioni realizzate fino a quel momento
	 */
	public List<modelReunions>pastReunionsList(Date dataCorrente){
		MBQuery query = MBQuery.queryDaTabella("Riunioni");
		try {
			return query.find().stream()
					.map(e -> new modelReunions(e))
					.filter(e -> e.getDate().before(dataCorrente))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/***
	 * ritorna un elenco di tutte le riunione da realizzare in quel giorno
	 * @param dataCorrente
	 * @return
	 * una lista con tutte le riunioone da effettuare
	 */
	public List<modelReunions>reunionsTodayList(Date dataCorrente){
		MBQuery query = MBQuery.queryDaTabella("Riunioni");
		try {
			return query.find().stream()
					.map(e -> new modelReunions(e))
					.filter(e -> e.getDate().equals(dataCorrente))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/***
	 * ritorn una lista con tutte le riunioni a venire
	 * @param dataCorrente
	 * @return
	 * una lista con tutte le riunioni 
	 */
	public List<modelReunions>futureReunionsList(Date dataCorrente){
		MBQuery query = MBQuery.queryDaTabella("Riunioni");
		try {
			return query.find().stream()
					.map(e -> new modelReunions(e))
					.filter(e -> e.getDate().after(dataCorrente))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/***
	 * se si intende modificare una riunione
	 * passare come "" e null per dataEora i dati da non modificare
	 * @param newNome
	 * @param newResponsabile
	 * @param newReferenze
	 * @param newDescrizione
	 * @param newDataEora
	 * @return
	 * true o false a seconda dell'esito
	 */
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
	/***
	 * elimna la riunione corrente
	 * @return
	 * true o false a seconda dell'esito
	 */
	public boolean deleteReunion(){
		return this.oggetto.elimina();
	}
}
