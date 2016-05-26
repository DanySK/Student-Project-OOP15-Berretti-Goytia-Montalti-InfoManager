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
	
	private void setNegozio(String negozio){
		this.oggetto.setObjectValue("Negozio", ctrlStringa(negozio));
	}
	
	private static String ctrlStringa(String str){
		return str != "" ? str : null;
	}
	
	private static modelClienti getSpecificObject(String nome, String cognome, String mail, String telefono, String negozio){
		MBQuery query = MBQuery.queryDaTabella("Clienti");
		query.whereEqualTo("Nome", ctrlStringa(nome));
		query.whereEqualTo("Cognome", ctrlStringa(cognome));
		query.whereEqualTo("Mail", ctrlStringa(mail));
		query.whereEqualTo("Telefono", ctrlStringa(telefono));
		query.whereEqualTo("Negozio", ctrlStringa(negozio));
		
		try {
			return new modelClienti(query.find().get(0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
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
	
	public static List<modelClienti> cercaClienti(String nome, String cognome, String mail, String telefono, String nomeNegozio){
		return modelClienti.elenco().stream()
							.filter(cliente -> cliente.getNome() == nome || cliente.getCognome() == cognome
											    || cliente.getMail() == mail || cliente.getNomeNegozio() == nomeNegozio
											    || cliente.getTelefono() == telefono)
							.collect(Collectors.toList());
	}
	
	public static boolean nuovoCliente(String nome, String cognome, String mail, String telefono, String negozio){
		modelClienti nuovo = new modelClienti(MBOggetto.oggettoDaTabella("Clienti"));
		nuovo.setNome(nome);
		nuovo.setCognome(cognome);
		nuovo.setMail(mail);
		nuovo.setTelefono(telefono);
		nuovo.setNegozio(negozio);
		return nuovo.oggetto.salva();
	}

	public boolean eliminaCliente(String nome, String cognome, String mail, String telefono, String negozio){
		//controllo dati in input
		modelClienti temp = modelClienti.getSpecificObject(nome, cognome, mail, telefono, negozio);
		if(temp != null)
			return temp.oggetto.elimina();
		else
			return false;
	}
	
	public boolean modificaCliente(String nome, String cognome, String mail, String telefono, String negozio,
									String newNome, String newCognome, String newMail, String newTelefono, String newNegozio){
		//controllo dell'esistenza del Cliente a modificare
		modelClienti _temp = modelClienti.getSpecificObject(nome, cognome, mail, telefono, negozio);

		if(_temp != null){
			_temp.setCognome(newCognome);
			_temp.setNome(newCognome);
			_temp.setMail(newMail);
			_temp.setNegozio(newNegozio);
			_temp.setTelefono(newTelefono);
			return _temp.oggetto.salva();
		}
		else
			return false;
	}
}
