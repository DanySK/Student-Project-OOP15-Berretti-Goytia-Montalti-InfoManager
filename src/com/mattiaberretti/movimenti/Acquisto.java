package com.mattiaberretti.movimenti;

import java.sql.Date;
import java.sql.SQLException;

public class Acquisto extends Movimento {

	protected Acquisto(Integer idMovimento, Integer quantita, String descrizione, Integer idProdotto, Date data,
			Double prezzo, Integer iVA, Integer idRicevuta) {
		super(idMovimento, quantita, descrizione, idProdotto, data, prezzo, iVA, idRicevuta);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getRicevuta() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void salva() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void elimina() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

}
