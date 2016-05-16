package com.mattiaberretti.fornitori;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.mattiaberretti.database.GestioneDB;
import com.mattiaberretti.utenti.IUtente;

interface IFornitore {

	static List<IFornitore> elenco() throws ClassNotFoundException, SQLException{
		GestioneDB db = GestioneDB.generaControllore();
		db.connetti();
		Integer idutente = IUtente.utenteCorrente.getUtenteCorrente().get().getIDUtente();
		List<IFornitore> rit = db.eseguiLettura(new String[]{"IDFornitore", "Nome", "Telefono", "Mail", "IDUtente"}, "Fornitori").stream()
				.filter(w -> w.get("IDUtente").equals(idutente))
				.map(e -> {
					return new Fornitore((Integer)e.get("IDFornitore"), (String)e.get("Nome"), (String)e.get("Telefono"), (String)e.get("Mail"));
				})
				.collect(Collectors.toList());
		db.disconnetti();
		return rit;
	}
	
	Integer getIdFornitore();

	String getNome();

	String getTelefono();

	String getEmail();

	void setNome(String nome);

	void setTelefono(String telefono);

	void setEmail(String email);

	void salva() throws SQLException, ClassNotFoundException;

	void elimina() throws SQLException, ClassNotFoundException;

}