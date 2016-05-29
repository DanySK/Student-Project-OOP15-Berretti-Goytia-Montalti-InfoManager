package com.goytia.models.DB;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelRiunioni {
	
	private DateFormat f= new SimpleDateFormat("HH:mm:ss");
	MBOggetto oggetto;

	private modelRiunioni(MBOggetto temp){
		this.oggetto = temp;
	}
	
	public modelRiunioni(){
		this.oggetto = MBOggetto.oggettoDaTabella("Riunioni");
	}
	
	private void setNomeRiunione(String nome){
		this.oggetto.setObjectValue("Riunione", ctrlStringa(nome));
	}
	
	private void setResponsabile(String responsabile){
		this.oggetto.setObjectValue("Responsabile", ctrlStringa(responsabile));
	}
	
	private void  setReferenzeResponsabile(String referenze){
		this.oggetto.setObjectValue("Referenze", ctrlStringa(referenze));
	}
	private void setDescrizione(String descrizione){
		this.oggetto.setObjectValue("Descrizione", ctrlStringa(descrizione));
	}
	
	private void setData(Date dataRiunione){
		this.oggetto.setObjectValue("Data", dataRiunione);
	}
	
	private void setOra(String oraRiunione) {
		this.oggetto.setObjectValue("Ora", oraRiunione);
	}
	
	private static String ctrlStringa(String str){
		return str != "" ? str : null;
	}

	public Integer getID(){
		return this.oggetto.objectId();
	}
	
	public String getNomeRiunione(){
		return (String)this.oggetto.getObject("Riunione");
	}
	
	public String getNomeResponsabile(){
		return (String)this.oggetto.getObject("Responsabile");
	}
	
	public String getReferenze(){
		return (String)this.oggetto.getObject("Referenze");
	}
	
	public Date getData(){
		return (Date)this.oggetto.getObject("Data");
	}
	
	public String getOra(){
		return (String) this.oggetto.getObject("Ora");
	}
	/***
	 * ritorna l'elenco con tutte le riunioni nel DB
	 * @return
	 * una lista contenente tutte le riunioni
	 */
	public static List<modelRiunioni> elenco(){
		MBQuery query = MBQuery.queryDaTabella("Riunioni");
		try {
			return query.find().stream()
					.map(e -> new modelRiunioni(e))
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
	public boolean nuovaRiunione(String nomeRiunione, String nomeResponsabile, String referenze, String descrizione, java.util.Date dataEora){
		modelRiunioni temp = new modelRiunioni(MBOggetto.oggettoDaTabella("Riunioni"));
		temp.setNomeRiunione(nomeRiunione);
		temp.setResponsabile(nomeResponsabile);
		temp.setReferenzeResponsabile(referenze);
		temp.setDescrizione(descrizione);
		temp.setData(new java.sql.Date(dataEora.getTime()));
		temp.setOra(f.format(dataEora));
		return temp.oggetto.salva();
	}
	/***
	 * ritorna un elenco di tutte le riunione scadute o passate fino a quel momento
	 * @param dataCorrente
	 * @return
	 * una lista con tutte le reunioni realizzate fino a quel momento
	 */
	public List<modelRiunioni>elencoRunioneScadute(Date dataCorrente){
		MBQuery query = MBQuery.queryDaTabella("Riunioni");
		try {
			return query.find().stream()
					.map(e -> new modelRiunioni(e))
					.filter(e -> e.getData().before(dataCorrente))
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
	public List<modelRiunioni>elencoRunioneOggi(Date dataCorrente){
		MBQuery query = MBQuery.queryDaTabella("Riunioni");
		try {
			return query.find().stream()
					.map(e -> new modelRiunioni(e))
					.filter(e -> e.getData().equals(dataCorrente))
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
	public List<modelRiunioni>elencoRunioneFuture(Date dataCorrente){
		MBQuery query = MBQuery.queryDaTabella("Riunioni");
		try {
			return query.find().stream()
					.map(e -> new modelRiunioni(e))
					.filter(e -> e.getData().after(dataCorrente))
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
	public boolean modificaRiunione(String newNome, String newResponsabile, String newReferenze, String newDescrizione, java.util.Date newDataEora){
		if(newNome!="")this.setNomeRiunione(newNome);
		if(newResponsabile!="")this.setResponsabile(newResponsabile);
		if(newReferenze!="")this.setReferenzeResponsabile(newReferenze);
		if(newDescrizione!="")this.setDescrizione(newDescrizione);
		if(newDataEora!=null){
			this.setData(new java.sql.Date(newDataEora.getTime()));
			this.setOra(f.format(newDataEora));
		}
		return this.oggetto.salva();
	}
	/***
	 * elimna la riunione corrente
	 * @return
	 * true o false a seconda dell'esito
	 */
	public boolean eliminaRiunione(){
		return this.oggetto.elimina();
	}
}
