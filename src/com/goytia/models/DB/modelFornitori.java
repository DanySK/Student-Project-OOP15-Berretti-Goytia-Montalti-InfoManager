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
	
	private String ctrlStringa(String str){
		return str != "" ? str : null;
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
							.filter(f -> f.getNome() == nome || f.getCognome() == cognome
											    || f.getMail() == mail || f.getTelefono() == telefono)
							.collect(Collectors.toList());
	}
	
	public static boolean nuovoFornitore(String nome, String cognome, String mail, String telefono){
		modelFornitori nuovo = new modelFornitori(MBOggetto.oggettoDaTabella("Fornitori"));
		nuovo.setNome(nome);
		nuovo.setCognome(cognome);
		nuovo.setMail(mail);
		nuovo.setTelefono(telefono);
		return nuovo.oggetto.salva();
	}
	
	public boolean eleminaFornitore(String nome, String cognome, String mail, String telefono){
		MBQuery query = MBQuery.queryDaTabella("Clienti");
		query.whereEqualTo("Nome", ctrlStringa(nome));
		query.whereEqualTo("Cognome", ctrlStringa(cognome));
		query.whereEqualTo("Mail", ctrlStringa(mail));
		query.whereEqualTo("Telefono", ctrlStringa(telefono));
		try {
			return query.getFirst().elimina();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean modificaFornitore(String nome, String cognome, String mail, String telefono,
									String newNome, String newCognome, String newMail, String newTelefono){
		@SuppressWarnings("static-access")
		List<modelFornitori> _temp= this.elenco().stream()
								.filter(cliente -> cliente.getNome() == ctrlStringa(nome) && cliente.getCognome() == ctrlStringa(cognome)
													    && cliente.getMail() == ctrlStringa(mail) && cliente.getTelefono() == ctrlStringa(telefono))
								.collect(Collectors.toList());
			if(_temp.size() == 1){
			_temp.get(0).setNome(newNome);
			_temp.get(0).setCognome(newCognome);
			_temp.get(0).setMail(newMail);
			_temp.get(0).setTelefono(newTelefono);
			return _temp.get(0).oggetto.salva();
		}
		else
			return false;
	}
}
