package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.TableRow;
import com.infoMng.controller.DataBaseSearch;

public interface modelClientsI {
	
	/***
	 * ottiene ID del record corrente
	 * @return
	 * un integer che rapparesenta il record
	 */
	Integer getID();
	
	/***
	 * ottiene il nome del cliente 
	 * @return
	 * una string con il nome del cliente
	 */
	String getName();
	
	/***
	 * ottiene il cognome del cliente
	 * @return
	 * una string con il cognome del cliente
	 */
	String getLastName();
	
	/***
	 * ottiene la mail del cliente
	 * @return
	 * una string con la mail del cliente
	 */
	String getMail();
	
	/***
	 * Ottiene il telefono del cliente
	 * @return
	 * una string con il telefono del cliente
	 */
	String getPhone();
	
	/***
	 * ottiene il nome del negozio a cui appartiene il cliente
	 * @return
	 * una stringa contenente il nome del negozio
	 */
	String getShopName();
	
	/***
	 * eliminazione del cliente corrente
	 * @return
	 * true se va a buon fine
	 */
	boolean deleteClient();

	/***
	 * modifica del cliente corrente
	 * Si chiede di passare come "" i parametri che non sono a modificare
	 * @param newNome
	 * nuovo nome 
	 * @param newCognome
	 * nuovo cognome 
	 * @param newMail
	 * nuova mail
	 * @param newTelefono
	 * nuovo recapivo telefonico
	 * @param newNegozio
	 * @return
	 * true se va buon fine
	 */
	boolean renameClient(String newNome, String newCognome, String newMail, String newTelefono, String newNegozio);

	/***
	 * ricerca di un/i clienti tramite uno o piu parametri
	 * passare come stringa vuota se non si vuole usare come parametro di ricerca
	 * @param nome
	 * nome del cliente
	 * @param cognome
	 * cognome del cliente
	 * @param mail
	 * mail del cliente
	 * @param telefono
	 * telefono del cliente
	 * @param nomeNegozio
	 * nome del negoizio a cui appartiene il cliente
	 * @return
	 * tutti i clienti trovati tramite i parametri forniti, si segue una lgica or!
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
			return new ArrayList<modelClientsI>();
		}
	}
	
	/***
	 * creazione di un nuovo cliente
	 * @param nome
	 * nome del cliente
	 * @param cognome
	 * cognome del cliente
	 * @param mail
	 * email del cliente
	 * @param telefono
	 * recapito telefonico del cliente
	 * @param negozio
	 * nome del negozio a cui appartiene il cliente
	 * @return
	 * true se e andato a buon fine il salvataggio
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