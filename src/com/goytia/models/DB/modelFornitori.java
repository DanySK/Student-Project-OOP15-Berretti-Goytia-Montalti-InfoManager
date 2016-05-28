package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelFornitori{
	
	MBOggetto oggetto;
	
	private modelFornitori(MBOggetto temp){
		this.oggetto=temp;
	}
	/***
	 * ottieni un nuovo record della tabella Fornitori
	 */
	public modelFornitori(){
		this.oggetto = MBOggetto.oggettoDaTabella("Fornitori");
	}
	
	public Integer getID(){
		return this.oggetto.objectId();
	}
	
	public String getIDFornitore(){
		return String.format("%i", this.oggetto.objectId());
	}
	
	public String getNome(){
		return (String)this.oggetto.getObject("Nome");
	}
	
	public String getCognome(){
		return (String)this.oggetto.getObject("Cognome");
	}
	
	public String getMail(){
		return (String)this.oggetto.getObject("Mail");
	}
	
	public String getTelefono(){
		return (String)this.oggetto.getObject("Telefono");
	}
	
	private void setNome(String nome){
		this.oggetto.setObjectValue("Nome", ctrlStringa(nome));
	}
	
	private void setCognome(String cognome){
		this.oggetto.setObjectValue("Cognome", ctrlStringa(cognome));
	}
	
	private void setMail(String mail){
		this.oggetto.setObjectValue("Mail", ctrlStringa(mail));
	}
	
	private void setTelefono(String telf){
		this.oggetto.setObjectValue("Telefono", ctrlStringa(telf));
	}
	
	private static String ctrlStringa(String str){
		return str != "" ? str : null;
	}
	
	/***
	 * ottiene un eleno di tutti i fornitori
	 * @return
	 * una lista contenente tutti i fornitori
	 */
	public static List<modelFornitori> elenco(){
		MBQuery query = MBQuery.queryDaTabella("Fornitori");
		try {
			return query.find().stream()
					.map(e -> new modelFornitori(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/***
	 * ricerca di fornitore tramite uno o piu paramentri
	 * si chiede di passare come "" i parametri da non considerare
	 * @param nome
	 * @param cognome
	 * @param mail
	 * @param telefono
	 * @return
	 * una lista contenente tutti i fornitori, tramite i parametri forniti
	 */
	public static List<modelFornitori> cercaFornitori(String nome, String cognome, String mail, String telefono){
		return modelFornitori.elenco().stream()
							.filter(f -> f.getNome().equalsIgnoreCase(nome) || f.getCognome().equalsIgnoreCase(cognome)
											    || f.getMail().equalsIgnoreCase(mail) || f.getTelefono().equalsIgnoreCase(mail))
							.collect(Collectors.toList());
	}
	/***
	 * cerca dei fornitori attraverso i prodotti presente nel magazzino
	 * @param nomeProdotto
	 * @return
	 * una lista con tutti i fornitori che producono il prodotto specificato
	 */
	public static List<modelFornitori> cercaFornitori(String nomeProdotto){
		
		List<Integer> listTemp = modelMagazzino.elenco().stream()
				.filter(e-> e.getNome().equalsIgnoreCase(nomeProdotto))
				.map(e -> e.getFornitore())
				.collect(Collectors.toList());
		
		return modelFornitori.elenco().stream()
				.filter(e ->{
					for(Integer a : listTemp){
						if(e.getID().equals(a))
							return true;
					}return false;	
				}).collect(Collectors.toList());
	}
	/***
	 * creazione di un nuovo fornitore
	 * @param nome
	 * @param cognome
	 * @param mail
	 * @param telefono
	 * @return
	 * true o false a seonda del esito
	 */
	public static boolean nuovoFornitore(String nome, String cognome, String mail, String telefono){
		modelFornitori nuovo = new modelFornitori(MBOggetto.oggettoDaTabella("Fornitori"));
		nuovo.setNome(nome);
		nuovo.setCognome(cognome);
		nuovo.setMail(mail);
		nuovo.setTelefono(telefono);
		return nuovo.oggetto.salva();
	}
	/***
	 * eliminazione del fornitore corrente
	 * @return
	 * true o false a seconda del esito
	 */
	public boolean eliminaFornitore(){
		return this.oggetto.elimina();
	}
	/***
	 * modifica dei dati di un fornitore
	 * Si chiede di passare come "" i parametri che non si devono modificare
	 * @param newNome
	 * @param newCognome
	 * @param newMail
	 * @param newTelefono
	 * @return
	 * true o false a seconda del esito
	 */
	public boolean modificaFornitore(String newNome , String newCognome, String newMail, String newTelefono){
		if(newNome != "")this.setNome(newNome);
		if(newCognome != "")this.setCognome(newCognome);
		if(newMail != "")this.setMail(newMail);
		if(newTelefono != "")this.setTelefono(newTelefono);
		return this.oggetto.salva();
	}
}
