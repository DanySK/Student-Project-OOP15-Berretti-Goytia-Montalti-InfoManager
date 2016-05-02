package com.mattiaberretti.movimenti;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mattiaberretti.database.GestioneDB;

public class Acquisto extends Movimento {

	protected Acquisto(Integer idMovimento, Integer quantita, String descrizione, Integer idProdotto, Date data,
			Double prezzo, Integer iVA, Integer idRicevuta) {
		super(idMovimento, quantita, descrizione, idProdotto, data, prezzo, iVA, idRicevuta);
	}

	@Override
	public void getRicevuta() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void salva() throws SQLException, ClassNotFoundException {
		GestioneDB db = GestioneDB.generaControllore();
		db.connetti();
		Map<String, Object> valori = new HashMap<>();
		valori.put("Data", this.getData());
		valori.put("Prezzo", this.getPrezzo());
		valori.put("IVA", this.getIVA());
		valori.put("Descrizione", this.getDescrizione());
		valori.put("Quantita", this.getQuantita());
		
		db.aggiornaTabella("Movimenti", valori, "IDMovimento", new Object[]{this.getIdMovimento()});
		
		db.disconnetti();
	}

}
