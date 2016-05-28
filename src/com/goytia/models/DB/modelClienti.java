package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelClienti {
	
	MBOggetto oggetto;
	
	public modelClienti(){
		this.oggetto= MBOggetto.oggettoDaTabella("Clienti");
	}
	
	/***
	 * crea un nuovo oggetto tipo modelCliente
	 */
	private modelClienti(MBOggetto temp){
		this.oggetto=temp;
	}
	
	public Integer getID(){
		return this.oggetto.objectId();
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
	
	public String getNomeNegozio(){
		return (String)this.oggetto.getObject("Negozio");
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
	
	private void setNegozio(String negozio){
		this.oggetto.setObjectValue("Negozio", ctrlStringa(negozio));
	}
	
	private static String ctrlStringa(String str){
		return str != "" ? str : null;
	}
	/***
	 * elenco di tutti i clienti esisitenti
	 * @return
	 * una lista contenente tutti i clienti
	 */
	public static List<modelClienti> elenco(){
		MBQuery query = MBQuery.queryDaTabella("Clienti");
		try {
			return query.find().stream()
					.map(e -> new modelClienti(e))
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
	public static List<modelClienti> cercaClienti(String nome, String cognome, String mail, String telefono, String nomeNegozio){
		return modelClienti.elenco().stream()
							.filter(cliente -> cliente.getNome().equalsIgnoreCase(nome) || cliente.getCognome().equalsIgnoreCase(cognome)
											    || cliente.getMail().equalsIgnoreCase(mail) || cliente.getNomeNegozio().equalsIgnoreCase(nomeNegozio)
											    || cliente.getTelefono().equalsIgnoreCase(telefono))
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
	public static boolean nuovoCliente(String nome, String cognome, String mail, String telefono, String negozio){
		modelClienti nuovo = new modelClienti(MBOggetto.oggettoDaTabella("Clienti"));
		nuovo.setNome(nome);
		nuovo.setCognome(cognome);
		nuovo.setMail(mail);
		nuovo.setTelefono(telefono);
		nuovo.setNegozio(negozio);
		return nuovo.oggetto.salva();
	}
	/***
	 * eliminazione del cliente corrente
	 * @return
	 * true o false a seconda del esito
	 */
	public boolean eliminaCliente(){
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
	 * true o false a seconda del esisto
	 */
	public boolean modificaCliente(String newNome, String newCognome, String newMail, String newTelefono, String newNegozio){
			if(newNome != "")this.setNome(newNome);
			if(newCognome != "")this.setCognome(newCognome);
			if(newMail != "")this.setMail(newMail);
			if(newTelefono != "")this.setTelefono(newTelefono);
			return this.oggetto.salva();
	}
}
