package com.mattiaberretti.clienti;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mattiaberretti.database.GestioneDB;
import com.mattiaberretti.utenti.IUtente;

public interface ICliente {

	static List<ICliente> elencoClienti() throws ClassNotFoundException, SQLException{
		GestioneDB db = GestioneDB.generaControllore();
		db.connetti();
		Integer idUtente = IUtente.utenteCorrente.getUtenteCorrente().get().getIDUtente();
		List<Map<String, Object>> valori = db.eseguiLettura(new String[]{"IDCliente", "Nome", "Cognome", "Mail", "Password", "IDUtente"}, "Clienti").stream()
				.filter(w -> w.get("IDUtente").equals(idUtente))
				.collect(Collectors.toList());
		
		db.disconnetti();
		List<ICliente> ritorno = new ArrayList<>();
		for(Map<String, Object> val : valori){
			ritorno.add(new Cliente((Integer)val.get("IDCliente"), 
					(String)val.get("Nome"),
					(String)val.get("Cognome"),
					(String)val.get("Mail"),
					(String)val.get("Telefono")));
		}
		
		return ritorno;
	}
	
	Integer getIDCliente();

	String getNome();

	String getCognome();

	String getEmail();

	String getTelefono();

	void setNome(String nome);

	void setCognome(String cognome);

	void setEmail(String email);

	void setTelefono(String telefono);

	void salva() throws ClassNotFoundException, SQLException;

	public class Builder
	{
		private String nome;
		private String cognome;
		private String mail;
		private String telefono;
		
		public Builder(String nome, String cognome){
			this.nome = nome;
			this.cognome = cognome;
			this.mail = "";
			this.telefono = "";
		}
		
		public Builder(){
			this("", "");
		}
		
		public ICliente.Builder setNome(String nome){
			this.nome = nome;
			return this;
		}
		
		public ICliente.Builder setCognome(String cognome){
			this.cognome = cognome;
			return this;
		}
		
		public ICliente.Builder setMail(String email){
			this.mail = email;
			return this;
		}
		
		public ICliente.Builder setTelefono(String telefono){
			this.telefono = telefono;
			return this;
		}
		
		private Boolean ok() throws ClassNotFoundException, SQLException{
			GestioneDB db = GestioneDB.generaControllore();
			db.connetti();
			Integer idUtente = IUtente.utenteCorrente.getUtenteCorrente().get().getIDUtente();
			Boolean ritorno = db.eseguiLettura(new String[]{"Nome", "Cognome", "IDUtente"}, "Clienti").stream()
					.filter(f -> f.get("IDUtente").equals(idUtente))
					.filter(n -> n.get("Nome").equals(this.nome))
					.filter(c -> c.get("Cognome").equals(this.cognome))
					.count() == 0;
			db.disconnetti();
			return ritorno;
		}
		
		public Optional<ICliente> build() throws ClassNotFoundException, SQLException, NoSuchElementException{
			if(!this.ok()){
				return Optional.empty();
			}
			GestioneDB db = GestioneDB.generaControllore();
			db.connetti();
			Integer idUtente = IUtente.utenteCorrente.getUtenteCorrente().get().getIDUtente();
			Map<String, Object> valori = new HashMap<>();
			valori.put("Nome", this.nome);
			valori.put("Cognome", this.cognome);
			valori.put("Mail", this.mail);
			valori.put("Telefono", this.telefono);
			valori.put("IDUtente", idUtente);
			db.inserisciRecord("Clienti", valori);
			
			Integer idCliente = db.eseguiLettura(new String[]{"IDCliente", "IDUtente"}, "Clienti").stream()
					.filter(w -> w.get("IDUtente").equals(idUtente))
					.mapToInt(i -> (Integer)i.get("IDCliente"))
					.max()
					.getAsInt();
			
			ICliente ritorno = new Cliente(idCliente, this.nome, this.cognome, this.mail, this.telefono);
			
			db.disconnetti();
			return Optional.of(ritorno);
		}
	}
	
}