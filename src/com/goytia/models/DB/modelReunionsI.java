package com.goytia.models.DB;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public interface modelReunionsI {

	static  DateFormat format= new SimpleDateFormat("HH:mm:ss");
	
	Integer getID();

	String getNameReunion();

	String getNameResponsible();

	String getReferences();

	Date getDate();

	String getTime();

	String getReunionDetails();

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
	boolean renameReunion(String newNome, String newResponsabile, String newReferenze, String newDescrizione,
			java.util.Date newDataEora);

	/***
	 * elimna la riunione corrente
	 * @return
	 * true o false a seconda dell'esito
	 */
	boolean deleteReunion();
	
	/* (non-Javadoc)
	 * @see com.goytia.models.DB.modelReunionsI#pastReunionsList(java.sql.Date)
	 */
	public static List<modelReunionsI>pastReunionsList(Date dataCorrente){
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
	
	/* (non-Javadoc)
	 * @see com.goytia.models.DB.modelReunionsI#reunionsTodayList(java.sql.Date)
	 */
	 public static List<modelReunionsI>reunionsTodayList(Date dataCorrente){
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
	/* (non-Javadoc)
	 * @see com.goytia.models.DB.modelReunionsI#futureReunionsList(java.sql.Date)
	 */
	 public static List<modelReunionsI>futureReunionsList(Date dataCorrente){
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
	 * ritorna l'elenco con tutte le riunioni nel DB
	 * @return
	 * una lista contenente tutte le riunioni
	 */
	public static List<modelReunionsI> reunionsList(){
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
	/* (non-Javadoc)
	 * @see com.goytia.models.DB.modelReunionsI#newReunion(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date)
	 */
	public static boolean newReunion(String nomeRiunione, String nomeResponsabile, String referenze, String descrizione, java.util.Date dataEora){
		modelReunions temp = new modelReunions(MBOggetto.oggettoDaTabella("Riunioni"));
		temp.setNomeRiunione(nomeRiunione);
		temp.setResponsible(nomeResponsabile);
		temp.setReferenzeResponsabile(referenze);
		temp.setReunionDetails(descrizione);
		temp.setDate(new java.sql.Date(dataEora.getTime()));
		temp.setTime(format.format(dataEora));
		return temp.oggetto.salva();
	}
}