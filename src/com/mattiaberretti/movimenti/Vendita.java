package com.mattiaberretti.movimenti;

import java.sql.Date;
import java.sql.SQLException;

public class Vendita extends Movimento {

	protected Vendita(Integer idMovimento, Integer quantita, String descrizione, Integer idProdotto, Date data,
			Double prezzo, Integer iVA, Integer idRicevuta) {
		super(idMovimento, quantita, descrizione, idProdotto, data, prezzo, iVA, idRicevuta);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salva() throws SQLException, ClassNotFoundException {
		
	}

	@Override
	public void elimina() throws SQLException, ClassNotFoundException {

	}

	@Override
	public void getRicevuta() throws SQLException, ClassNotFoundException {
		
	}
	
}
