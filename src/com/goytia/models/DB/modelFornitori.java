package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelFornitori{
	
	MBOggetto oggetto;
	
	private modelFornitori(MBOggetto temp){
		this.oggetto=temp;
	}
	
	public modelFornitori(){
		this.oggetto = MBOggetto.oggettoDaTabella("Fornitori");
	}
	
	public Integer getID(){
		return this.oggetto.objectId();
	}
	
	public String getIDFornitore(){
		return String.format("%i", this.oggetto.objectId());
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
	
	private static String ctrlStringa(String str){
		return str != "" ? str : null;
	}
	
	private static modelFornitori getSpecificObject(String nome, String cognome, String mail, String telefono){
		MBQuery query = MBQuery.queryDaTabella("Clienti");
		query.whereEqualTo("Nome", ctrlStringa(nome));
		query.whereEqualTo("Cognome", ctrlStringa(cognome));
		query.whereEqualTo("Mail", ctrlStringa(mail));
		query.whereEqualTo("Telefono", ctrlStringa(telefono));	
		try {
			return new modelFornitori(query.find().get(0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<modelFornitori> elenco(){
		MBQuery query = MBQuery.queryDaTabella("Fornitori");
		try {
			return query.find().stream()
					.map(e -> new modelFornitori(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<modelFornitori> cercaFornitori(String nome, String cognome, String mail, String telefono){
		return modelFornitori.elenco().stream()
							.filter(f -> f.getNome() == ctrlStringa(nome) || f.getCognome() == ctrlStringa(cognome)
											    || f.getMail() == ctrlStringa(mail) || f.getTelefono() == ctrlStringa(telefono))
							.collect(Collectors.toList());
	}
	
	public static List<modelFornitori> cercaFornitori(String nomeProdotto){
		
		List<String> listTemp = modelMagazzino.elenco().stream()
				.filter(e-> e.getNome().equalsIgnoreCase(ctrlStringa(nomeProdotto)))
				.map(e -> e.getNome())
				.collect(Collectors.toList());
		
		return modelFornitori.elenco().stream()
				.filter(e ->{
					for(String a : listTemp){
						if(e.getNome() == a)
							return true;
					}return false;	
				}).collect(Collectors.toList());
	}
	public static boolean nuovoFornitore(String nome, String cognome, String mail, String telefono){
		modelFornitori nuovo = new modelFornitori(MBOggetto.oggettoDaTabella("Fornitori"));
		nuovo.setNome(nome);
		nuovo.setCognome(cognome);
		nuovo.setMail(mail);
		nuovo.setTelefono(telefono);
		return nuovo.oggetto.salva();
	}
	
	public boolean eliminaFornitore(String nome, String cognome, String mail, String telefono){
		//controllo l'esistenza del fornitore a eliminare
		modelFornitori temp = modelFornitori.getSpecificObject(nome, cognome, mail, telefono);
		if(temp != null){
			return temp.oggetto.elimina();
		}
		else 
			return false;
	}
	
	public boolean modificaFornitore(String nome , String cognome, String mail, String telefono, String newNome , String newCognome, String newMail, String newTelefono){
		//Conttrollo l'esistenza del fornitore a modificare
		modelFornitori temp = modelFornitori.getSpecificObject(nome, cognome, mail, telefono);
		if(temp != null){
			temp.setNome(newNome);
			temp.setCognome(newCognome);
			temp.setMail(newMail);
			temp.setTelefono(newTelefono);
			return temp.oggetto.salva();
		}
		else
			return false;
	}
}
