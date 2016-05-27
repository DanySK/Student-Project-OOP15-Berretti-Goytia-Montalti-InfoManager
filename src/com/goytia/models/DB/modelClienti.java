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
							.filter(cliente -> cliente.getNome().equalsIgnoreCase(nome) || cliente.getCognome().equalsIgnoreCase(cognome)
											    || cliente.getMail().equalsIgnoreCase(mail) || cliente.getNomeNegozio().equalsIgnoreCase(nomeNegozio)
											    || cliente.getTelefono().equalsIgnoreCase(telefono))
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

	public boolean eliminaCliente(Integer idCliente){
		this.oggetto.setObjectValue("objectId", idCliente);
		return this.oggetto.elimina();
	}
	
	public boolean modificaCliente(Integer idCliente, String newNome, String newCognome, String newMail, String newTelefono, String newNegozio){
			this.oggetto.setObjectValue("objectId", idCliente );
			if(newNome != "")this.setNome(newNome);
			if(newCognome != "")this.setCognome(newCognome);
			if(newMail != "")this.setMail(newMail);
			if(newTelefono != "")this.setTelefono(newTelefono);
			return this.oggetto.salva();
	}
}
