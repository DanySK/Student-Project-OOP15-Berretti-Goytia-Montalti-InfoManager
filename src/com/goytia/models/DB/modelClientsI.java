package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.TableRow;
import com.infoMng.controller.DataBaseSearch;

public interface modelClientsI {

	Integer getID();

	String getName();

	String getLastName();

	String getMail();

	String getPhone();

	String getShopName();
	
	/***
	 * eliminazione del cliente corrente
	 * @return
	 * true o false a seconda del esito
	 */
	boolean deleteClient();

	/***
	 * modifica del cliente corrente
	 * Si chiede di passare come "" i parametri che non sono a modificare
	 * @param newNome
	 * @param newCognome
	 * @param newMail
	 * @param newTelefono
	 * @param newNegozio
	 * @return
	 * true o false a seconda del esisto\
	 */
	boolean renameClient(String newNome, String newCognome, String newMail, String newTelefono, String newNegozio);

	/***
	 * ricerca di un/i clienti tramite uno o pi� paramentro
	 * @param nome
	 * passarlo come "" se non si vuole filtrare con questo paramentro
	 * @param cognome
	 * passarlo come "" se non si vuole filtrare con questo paramentro
	 * @param mail
	 * passarlo come "" se non si vuole filtrare con questo paramentro
	 * @param telefono
	 * passarlo come "" se non si vuole filtrare con questo paramentro
	 * @param nomeNegozio
	 * passarlo come "" se non si vuole filtrare con questo paramentro
	 * @return
	 * tutti i clienti trovati tramite i parametri forniti, si segue una lgica or
	 */
	public static List<modelClientsI> searchClients(String nome, String cognome, String mail, String telefono, String nomeNegozio){
		return modelClientsI.clientsList().stream()
							.filter(cliente -> cliente.getName().contains(nome) || cliente.getLastName().contains(cognome)
											    || cliente.getMail().contains(mail) || cliente.getShopName().contains(nomeNegozio)
											    || cliente.getPhone().contains(telefono))
							.collect(Collectors.toList());
	}
	/***
	 * elenco di tutti i clienti esisitenti
	 * @return
	 * una lista contenente tutti i clienti
	 */
	public static List<modelClientsI> clientsList(){
		DataBaseSearch query = DataBaseSearch.queryDaTabella("Clienti");
		try {
			return query.find().stream()
					.map(e -> new modelClients(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/***
	 * creazione di un nuovo cliente
	 * @param nome
	 * @param cognome
	 * @param mail
	 * @param telefono
	 * @param negozio
	 * @return
	 * true o false a seconda del esito
	 */
	public static boolean newClient(String nome, String cognome, String mail, String telefono, String negozio){
		modelClients nuovo = new modelClients(TableRow.oggettoDaTabella("Clienti"));
		nuovo.setName(nome);
		nuovo.setLastName(cognome);
		nuovo.setMail(mail);
		nuovo.setPhone(telefono);
		nuovo.setShopName(negozio);
		return nuovo.oggetto.salva();
	}
}