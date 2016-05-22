package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelClienti {
	
	/***
	 * metodo che ottine un elenco con tutti i clienti
	 * @return
	 * un lista di tipo modelClienti contenente tutti i clienti
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

	MBOggetto oggetto;
	
	/***
	 * crea un nuovo oggetto tipo modelCliente
	 */
	private modelClienti(MBOggetto temp){
		this.oggetto=temp;
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
		if(nome!= "")
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
	
	private void setNegozio(String negoz){
		this.oggetto.setObjectValue("Negozio", ctrlStringa(negoz));
	}
	
	private String ctrlStringa(String str){
		return str != "" ? str : null;
	}
	
	/***
	 * ricera di clienti in base ai parametri, se non si ha conoscenza di tutti i parametri, si passi
	 * i parametri non conosciuti come stringa vuota ""
	 * @param nome cognome mail telefono nomeNegozio
	 * Stringhe che servono a filtare l'elenco e trovare il/i clienti
	 * @return
	 * un lista di contente il/i clienti trovati
	 */
	public static List<modelClienti> cercaClienti(String nome, String cognome, String mail, String telefono, String nomeNegozio){
		return modelClienti.elenco().stream()
							.filter(cliente -> cliente.getNome() == nome || cliente.getCognome() == cognome
											    || cliente.getMail() == mail || cliente.getNomeNegozio() == nomeNegozio
											    || cliente.getTelefono() == telefono)
							.collect(Collectors.toList());
	}
	
	/***
	 * crea un nuovo cliente
	 * @param nome cognome mail telefono negozio
	 * stringhe contenente i dati del clienti inclui il negozio(ovvero negozio a cui appartiene quest'ultimo)
	 * @return
	 * true se si e creato il nuovo cliente altriemente false
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
	 * eliminazione di un determinato cliente
	 * @param nome cognome mail telefono negozio
	 * tutti parametri sono indispensabile per poter eliminare un cliente, quindi si accerti prima
	 * di tutti i parametri neccessari
	 * @return
	 * true se è stato eliminato, altrimenti false
	 */
	public boolean eleminaCliente(String nome, String cognome, String mail, String telefono, String negozio){
		MBQuery query = MBQuery.queryDaTabella("Clienti");
		query.whereEqualTo("Nome", ctrlStringa(nome));
		query.whereEqualTo("Cognome", ctrlStringa(cognome));
		query.whereEqualTo("Mail", ctrlStringa(mail));
		query.whereEqualTo("Telefono", ctrlStringa(telefono));
		query.whereEqualTo("Negozio", ctrlStringa(negozio));
		try {
			return query.getFirst().elimina();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	/***
	 * eliminazione di un determinato cliente
	 * @param nome cognome mail telefono negozio
	 * dati correntei del cliente che si intende modificare i dati
	 * @param newNome newCognome newMail newTelefono newNegozio
	 * nuovi dati da modificare nel cliente. i dati a non modificare devono essere passati come stringa vuota ""
	 * @return
	 * true se è stato modificato, altrimenti false
	 */
	public boolean modificaCliente(String nome, String cognome, String mail, String telefono, String negozio,
									String newNome, String newCognome, String newMail, String newTelefono, String newNegozio){
	    @SuppressWarnings("static-access")
		List<modelClienti> _temp= this.elenco().stream()
						.filter(cliente -> cliente.getNome() == ctrlStringa(nome) && cliente.getCognome() == ctrlStringa(cognome)
											    && cliente.getMail() == ctrlStringa(mail) && cliente.getNomeNegozio() == ctrlStringa(negozio)
											    && cliente.getTelefono() == ctrlStringa(telefono))
						.collect(Collectors.toList());
	    if(_temp.size() == 1){
	    	_temp.get(0).setNome(newNome);
	    	_temp.get(0).setCognome(newCognome);
	    	_temp.get(0).setNegozio(newNegozio);
	    	_temp.get(0).setMail(newMail);
	    	_temp.get(0).setTelefono(newTelefono);
	    	return _temp.get(0).oggetto.salva();
	    }
	    else
	    	return false;
	}
}
