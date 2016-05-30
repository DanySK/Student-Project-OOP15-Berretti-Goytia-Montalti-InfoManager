package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelProviders{
	
	MBOggetto oggetto;
	
	private modelProviders(MBOggetto temp){
		this.oggetto=temp;
	}
	/***
	 * ottieni un nuovo record della tabella Fornitori
	 */
	public modelProviders(){
		this.oggetto = MBOggetto.oggettoDaTabella("Fornitori");
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
	
	private void setName(String nome){
		this.oggetto.setObjectValue("Nome", ctrlString(nome));
	}
	
	private void setLastName(String cognome){
		this.oggetto.setObjectValue("Cognome", ctrlString(cognome));
	}
	
	private void setMail(String mail){
		this.oggetto.setObjectValue("Mail", ctrlString(mail));
	}
	
	private void setPhone(String telf){
		this.oggetto.setObjectValue("Telefono", ctrlString(telf));
	}
	
	private static String ctrlString(String str){
		return str != "" ? str : null;
	}
	
	/***
	 * ottiene un eleno di tutti i fornitori
	 * @return
	 * una lista contenente tutti i fornitori
	 */
	public static List<modelProviders> providersList(){
		MBQuery query = MBQuery.queryDaTabella("Fornitori");
		try {
			return query.find().stream()
					.map(e -> new modelProviders(e))
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
	public static List<modelProviders> searchProviders(String nome, String cognome, String mail, String telefono){
		return modelProviders.providersList().stream()
							.filter(f -> f.getName().equalsIgnoreCase(nome) || f.getLastName().equalsIgnoreCase(cognome)
											    || f.getMail().equalsIgnoreCase(mail) || f.getPhone().equalsIgnoreCase(mail))
							.collect(Collectors.toList());
	}
	/***
	 * cerca dei fornitori attraverso i prodotti presente nel magazzino
	 * @param nomeProdotto
	 * @return
	 * una lista con tutti i fornitori che producono il prodotto specificato
	 */
	public static List<modelProviders> searchProviders(String nomeProdotto){
		
		List<Integer> listTemp = modelStore.elenco().stream()
				.filter(e-> e.getName().equalsIgnoreCase(nomeProdotto))
				.map(e -> e.getIDProvider())
				.collect(Collectors.toList());
		
		return modelProviders.providersList().stream()
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
	public static boolean newProvider(String nome, String cognome, String mail, String telefono){
		modelProviders nuovo = new modelProviders(MBOggetto.oggettoDaTabella("Fornitori"));
		nuovo.setName(nome);
		nuovo.setLastName(cognome);
		nuovo.setMail(mail);
		nuovo.setPhone(telefono);
		return nuovo.oggetto.salva();
	}
	/***
	 * eliminazione del fornitore corrente
	 * @return
	 * true o false a seconda del esito
	 */
	public boolean deleteProvider(){
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
	public boolean renameProvider(String newNome , String newCognome, String newMail, String newTelefono){
		if(newNome != "")this.setName(newNome);
		if(newCognome != "")this.setLastName(newCognome);
		if(newMail != "")this.setMail(newMail);
		if(newTelefono != "")this.setPhone(newTelefono);
		return this.oggetto.salva();
	}
}
