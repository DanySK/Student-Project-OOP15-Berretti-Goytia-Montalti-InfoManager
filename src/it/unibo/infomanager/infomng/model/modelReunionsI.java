package it.unibo.infomanager.infomng.model;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.infomanager.infomng.controller.DataBaseSearch;
import it.unibo.infomanager.infomng.controller.TableRow;

public interface modelReunionsI {
	//formato per estrarre l'ora in una data
	public static  DateFormat format= new SimpleDateFormat("HH:mm:ss");
	/***
	 * ottiene l'id del record
	 * @return
	 * un integer con l'iD
	 */
	Integer getID();
	/***
	 * ottiene il nome assegnato alla riunione
	 * @return
	 * una stringa con il nome
	 */
	String getNameReunion();
	/***
	 * ottiene il nome del responsabile della riunione
	 * @return
	 * una stringa contenente il nome del responsabile
	 */
	@Deprecated
	String getNameResponsible();
	/***
	 * ottiene le referenze per contattare il responsabile
	 * @return
	 * una string contenente i dati
	 */
	String getReferences();
	/***
	 * ottiene la data della riunione
	 * @return
	 * un object sql.date
	 */
	Date getDate();
	/***
	 * ottiene l'ora della riunione
	 * @return
	 * una string con l'ora della riunione
	 */
	String getTime();
	/***
	 * ottiene i detaggli, tra cui il motivo, luogo e altro della riunione
	 * @return
	 * una stringa contenente i dati
	 */
	String getReunionDetails();

	/***
	 * modifica di una eventuale riunione
	 * passare come "" e null per dataEora i dati da non modificare
	 * @param newNome
	 * nuovo nome da asseganre
	 * @param newResponsabile
	 * nuovo responsabile se c'� stato un cambiamento
	 * @param newReferenze
	 * nuove referenze del responsabile
	 * @param newDescrizione
	 * nuovi dettagli della riunione
	 * @param newDataEora
	 * la nuova data e ora della riunione sottoforma di object java.util.Date
	 * @return
	 * true o false a seconda dell'esito
	 */
	boolean renameReunion(String newNome, String newResponsabile, String newReferenze, String newDescrizione,
			java.util.Date newDataEora);

	/***
	 * elimna la riunione corrente
	 * @return
	 * true o false a seconda dell'esito
	 */
	boolean deleteReunion();
	
	/***
	 * ottiene una lista con tutte le riunione svolte fino a quel giorno(escluso)
	 * @param dataCorrente
	 * dataCorrente(odierna))
	 * @return
	 * una lista contenente tutte le riunioni filtrate
	 */
	public static List<modelReunionsI>pastReunionsList(Date dataCorrente){
		DataBaseSearch query = DataBaseSearch.queryDaTabella("Riunioni");
		try {
			return query.find().stream()
					.map(e -> new modelReunions(e))
					.filter(e -> e.getDate().before(dataCorrente))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			return new ArrayList<modelReunionsI>();
		}
	}
	
	/***
	 * ottiene una lista con tutte le riunioni per questo data
	 * @param dataCorrente
	 * Data del giorno corrente
	 * @return
	 * una lista con tutte le riunioni per oggi
	 */
	 public static List<modelReunionsI>reunionsTodayList(Date dataCorrente){
			DataBaseSearch query = DataBaseSearch.queryDaTabella("Riunioni");
			try {
				return query.find().stream()
						.map(e -> new modelReunions(e))
						.filter(e -> e.getDate().equals(dataCorrente))
						.collect(Collectors.toList());
			} catch (SQLException e) {
				return new ArrayList<modelReunionsI>();
			}
		}
	/***
	 * ottiene una lista con tutte le future riunioni
	 * @param dataCorrente
	 * data odierna
	 * @return
	 * una lista con tutte le riunioni filtrate
	 */
	 public static List<modelReunionsI>futureReunionsList(Date dataCorrente){
			DataBaseSearch query = DataBaseSearch.queryDaTabella("Riunioni");
			try {
				return query.find().stream()
						.map(e -> new modelReunions(e))
						.filter(e -> e.getDate().after(dataCorrente))
						.collect(Collectors.toList());
			} catch (SQLException e) {
				return new ArrayList<modelReunionsI>();
			}
		}
	
	/***
	 * ritorna l'elenco con tutte le riunioni nel DB
	 * @return
	 * una lista contenente tutte le riunioni
	 */
	public static List<modelReunionsI> reunionsList(){
		DataBaseSearch query = DataBaseSearch.queryDaTabella("Riunioni");
		try {
			return query.find().stream()
					.map(e -> new modelReunions(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			return new ArrayList<modelReunionsI>();
		}
	}
	/***
	 * salvataggio di una nuova riunione
	 * @param nomeRiunione
	 * nome da assegnare alla riunione
	 * @param nomeResponsabile
	 * nome del responsabile della riunione
	 * @param referenze
	 * referenze per contattare il responsabile della riunione
	 * @param descrizione
	 * dettagli della riunione tra cui luogo, motivo, ecc
	 * @param dataEora
	 * data e ora della riunione
	 * @return
	 * true se e andato a buon fine altrimenti false
	 */
	public static boolean newReunion(String nomeRiunione, String nomeResponsabile, String referenze, String descrizione, java.util.Date dataEora){
		modelReunions temp = new modelReunions(TableRow.oggettoDaTabella("Riunioni"));
		temp.setNomeRiunione(nomeRiunione);
		temp.setResponsible(nomeResponsabile);
		temp.setReferenzeResponsabile(referenze);
		temp.setReunionDetails(descrizione);
		temp.setDate(new java.sql.Date(dataEora.getTime()));
		temp.setTime(format.format(dataEora));
		return temp.oggetto.salva();
	}
}