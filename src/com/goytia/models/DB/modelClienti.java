package com.goytia.models.DB;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.infoMng.controller.MBOggetto;
import com.infoMng.controller.MBQuery;

public class modelClienti {
	
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
	
	public modelClienti(MBOggetto temp){
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
	public void setNome(String nome){
		this.oggetto.setObjectValue("Nome", nome);
	}
	
	public void setCognome(String cognome){
		this.oggetto.setObjectValue("Cognome", cognome);
	}
	
	public void setMail(String mail){
		this.oggetto.setObjectValue("Mail", mail);
	}
	
	public void setTelefono(String telf){
		this.oggetto.setObjectValue("Telefono", telf);
	}
	
	public void setNegozio(String negoz){
		this.oggetto.setObjectValue("Negozio", negoz);
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
	
	public boolean eleminaCliente(String nome, String cognome, String mail, String telefono, String negozio){
		MBQuery query = MBQuery.queryDaTabella("Clienti");
		query.whereEqualTo("Nome", nome);
		query.whereEqualTo("Cognome", cognome);
		query.whereEqualTo("Mail", mail);
		query.whereEqualTo("Telefono", telefono);
		query.whereEqualTo("Negozio", negozio);
		try {
			return query.getFirst().elimina();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
