package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.TableRow;
import com.infoMng.controller.DataBaseSearch;

public interface modelProvidersI {
	
	/***
	 * ottiene ID del record corrente
	 * @return
	 * un integer contenente l'ID
	 */
	Integer getID();
	
	/***
	 * ottiene l'id del fornitore sottoforma di stringa
	 * @return
	 * una stringa contenente l'ID del fornitore
	 */
	String getIDFornitore();
	/***
	 * ottiene il nome del fornitore
	 * @return
	 * una string che contiene il nome
	 */
	String getName();
	/***
	 * ottiene il cognome de fornitore
	 * @return
	 * una stringa contenente il cognome
	 */
	String getLastName();
	/***
	 * ottiene la mail del fornitore
	 * @return
	 * una stringa contenente la mail
	 */
	String getMail();
	/***
	 * ottiene il recapito telefonico del fornitore
	 * @return
	 * una stringa contenente il recapito telfonico
	 */
	String getPhone();

	/***
	 * eliminazione del fornitore corrente
	 * @return
	 * true se e andato a buon fine
	 */
	boolean deleteProvider();

	/***
	 * modifica dei dati di un fornitore
	 * Si chiede di passare come "" i parametri che non si devono modificare
	 * @param newNome
	 * nuovo nome
	 * @param newCognome
	 * nuvo cognome
	 * @param newMail
	 * nuova mail
	 * @param newTelefono
	 * nuovo recapito telefonicoc
	 * @return
	 * true se e andato a buon fine
	 */
	boolean renameProvider(String newNome, String newCognome, String newMail, String newTelefono);
	
	
	/***
	 * ricerca di fornitore tramite uno o piu paramentri
	 * si chiede di passare come "" i parametri da non considerare
	 * @param nome
	 * nome del fornitre
	 * @param cognome
	 * cognome del fornitore
	 * @param mail
	 * mail del fornitore
	 * @param telefono
	 * recapito telefonico del fornitore
	 * @return
	 * una lista contenente tutti i fornitori, tramite i parametri forniti
	 */
	public static List<modelProvidersI> searchProviders(String nome, String cognome, String mail, String telefono){
		return modelProvidersI.providersList().stream()
							.filter(f -> f.getName().contains(nome) || f.getLastName().contains(cognome)
											    || f.getMail().contains(mail) || f.getPhone().contains(mail))
							.collect(Collectors.toList());
	}
	
	/***
	 * cerca dei fornitori attraverso i prodotti presente nel magazzino
	 * @param nomeProdotto
	 * noeme del prodotto
	 * @return
	 * una lista con tutti i fornitori che forniscono il prodotto specificato
	 */
	public static List<modelProvidersI> searchProvidersByProduct(String nomeProdotto){
		
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
	 * ottiene un elenco di tutti i fornitori
	 * @return
	 * una lista contenente tutti i fornitori
	 */
	public static List<modelProvidersI> providersList(){
		DataBaseSearch query = DataBaseSearch.queryDaTabella("Fornitori");
		try {
			return query.find().stream()
					.map(e -> new modelProviders(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			return new ArrayList<modelProvidersI>();
		}
	}
	
	/***
	 * creazione di un nuovo fornitore
	 * @param nome
	 * nome del fornitor
	 * @param cognome
	 * cognome del fornitore
	 * @param mail
	 * mail del fornitore
	 * @param telefono
	 * recapito telefonico del fornitore
	 * @return
	 * true se e andato a buon fine
	 */
	public static boolean newProvider(String nome, String cognome, String mail, String telefono){
		modelProviders nuovo = new modelProviders(TableRow.oggettoDaTabella("Fornitori"));
		nuovo.setName(nome);
		nuovo.setLastName(cognome);
		nuovo.setMail(mail);
		nuovo.setPhone(telefono);
		return nuovo.oggetto.salva();
	}

}