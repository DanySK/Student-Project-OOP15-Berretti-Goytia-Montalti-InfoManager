package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelClients{
	
	MBOggetto oggetto;
	
	public modelClients(){
		this.oggetto= MBOggetto.oggettoDaTabella("Clienti");
	}
	
	/***
	 * crea un nuovo oggetto tipo modelCliente
	 */
	private modelClients(MBOggetto temp){
		this.oggetto=temp;
	}
	
	public Integer getID(){
		return this.oggetto.objectId();
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
	
	public String getShopName(){
		return (String)this.oggetto.getObject("Negozio");
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
	
	private void setShopName(String negozio){
		this.oggetto.setObjectValue("Negozio", ctrlString(negozio));
	}
	
	private static String ctrlString(String str){
		return str != "" ? str : null;
	}
	
	/***
	 * elenco di tutti i clienti esisitenti
	 * @return
	 * una lista contenente tutti i clienti
	 */
	public static List<modelClients> clientsList(){
		MBQuery query = MBQuery.queryDaTabella("Clienti");
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
	 * ricerca di un/i clienti tramite uno o più paramentro
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
	public static List<modelClients> searchClients(String nome, String cognome, String mail, String telefono, String nomeNegozio){
		return modelClients.clientsList().stream()
							.filter(cliente -> cliente.getName().equalsIgnoreCase(nome) || cliente.getLastName().equalsIgnoreCase(cognome)
											    || cliente.getMail().equalsIgnoreCase(mail) || cliente.getShopName().equalsIgnoreCase(nomeNegozio)
											    || cliente.getPhone().equalsIgnoreCase(telefono))
							.collect(Collectors.toList());
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
		modelClients nuovo = new modelClients(MBOggetto.oggettoDaTabella("Clienti"));
		nuovo.setName(nome);
		nuovo.setLastName(cognome);
		nuovo.setMail(mail);
		nuovo.setPhone(telefono);
		nuovo.setShopName(negozio);
		return nuovo.oggetto.salva();
	}
	/***
	 * eliminazione del cliente corrente
	 * @return
	 * true o false a seconda del esito
	 */
	public boolean deleteClient(){
		return this.oggetto.elimina();
	}
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
	public boolean renameClient(String newNome, String newCognome, String newMail, String newTelefono, String newNegozio){
			if(newNome != "")this.setName(newNome);
			if(newCognome != "")this.setLastName(newCognome);
			if(newMail != "")this.setMail(newMail);
			if(newTelefono != "")this.setPhone(newTelefono);
			return this.oggetto.salva();
	}
	
}
