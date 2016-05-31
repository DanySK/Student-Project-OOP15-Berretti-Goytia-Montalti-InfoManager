package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public interface modelProvidersI {

	Integer getID();

	String getIDFornitore();

	String getName();

	String getLastName();

	String getMail();

	String getPhone();

	/***
	 * eliminazione del fornitore corrente
	 * @return
	 * true o false a seconda del esito
	 */
	boolean deleteProvider();

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
	boolean renameProvider(String newNome, String newCognome, String newMail, String newTelefono);
	
	
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
		return modelProvidersI.providersList().stream()
							.filter(f -> f.getName().contains(nome) || f.getLastName().contains(cognome)
											    || f.getMail().contains(mail) || f.getPhone().contains(mail))
							.collect(Collectors.toList());
	}
	
	/***
	 * cerca dei fornitori attraverso i prodotti presente nel magazzino
	 * @param nomeProdotto
	 * @return
	 * una lista con tutti i fornitori che producono il prodotto specificato
	 */
	public static List<modelProviders> searchProvidersByProduct(String nomeProdotto){
		
		List<Integer> listTemp = modelStoreI.productsList().stream()
				.filter(e-> e.getName().contains(nomeProdotto))
				.map(e -> e.getIDProvider())
				.collect(Collectors.toList());
		
		return modelProvidersI.providersList().stream()
				.filter(e ->{
					for(Integer a : listTemp){
						if(e.getID().equals(a))
							return true;
					}return false;	
				}).collect(Collectors.toList());
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

}