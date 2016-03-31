package com.mattiaberretti.prodotti;

import java.sql.Date;
import java.sql.SQLException;

interface IMovimentoMagazzino {

	Integer getIdMovimento();

	Integer getQuantita();

	void setQuantita(Integer quantita);

	Integer getIdProdotto();

	Date getData();

	Integer getIdVendita();

	Integer getIdAcquisto();

	String getDescrizione();

	/***
	 * aggiorna i campi Quantita, IVA, Prezzo, Data all'interno del database 
	 * non è possibile modificare il numero della ricevuta di acquisto o vendita e neppure il prodotto
	 * @throws ClassNotFoundException
	 * impossibile trovare il jar di sqlite
	 * @throws SQLException
	 * errore durante l'aggiornamento
	 */
	void salva() throws ClassNotFoundException, SQLException;

	/***
	 * elimina il record dal database
	 * @throws ClassNotFoundException
	 * impossibile trovare il jar di sqlite
	 * @throws SQLException
	 * errore durante l'eliminazione
	 */
	void elimina() throws ClassNotFoundException, SQLException;

	Double getPrezzo();
	void setPrezzo(Double prezzo);
	
	Integer getIVA();
	void setIVA(Integer IVA);
}