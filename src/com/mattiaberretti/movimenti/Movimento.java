package com.mattiaberretti.movimenti;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;

import com.mattiaberretti.database.GestioneDB;

abstract class Movimento implements IMovimento {
	private Integer idMovimento;
	private Integer quantita;
	private String descrizione;
	private Integer idProdotto;
	private Date data;
	private Double prezzo;
	private Integer IVA;
	private Integer idRicevuta;
	
	protected Movimento(Integer idMovimento, Integer quantita, String descrizione, Integer idProdotto, Date data,
			Double prezzo, Integer iVA, Integer idRicevuta) {
		super();
		this.idMovimento = idMovimento;
		this.quantita = quantita;
		this.descrizione = descrizione;
		this.idProdotto = idProdotto;
		this.data = data;
		this.prezzo = prezzo;
		IVA = iVA;
		this.idRicevuta = idRicevuta;
	}
	
	@Override
	public Integer getIdMovimento() {
		return idMovimento;
	}
	@Override
	public Integer getQuantita() {
		return quantita;
	}
	@Override
	public String getDescrizione() {
		return descrizione;
	}
	@Override
	public Integer getIdProdotto() {
		return idProdotto;
	}
	@Override
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	@Override
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	@Override
	public void setIdProdotto(Integer idProdotto) {
		this.idProdotto = idProdotto;
	}
	
	@Override
	public boolean isAcquisto(){
		return this.quantita > 0;
	}
	
	@Override
	public Date getData() {
		return data;
	}
	@Override
	public Double getPrezzo() {
		return prezzo;
	}
	@Override
	public Integer getIVA() {
		return IVA;
	}
	@Override
	public Integer getIdRicevuta() {
		return idRicevuta;
	}
	@Override
	public void setData(Date data) {
		this.data = data;
	}
	@Override
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	@Override
	public void setIVA(Integer iVA) {
		IVA = iVA;
	}
	@Override
	public void setIdRicevuta(Integer idRicevuta) {
		this.idRicevuta = idRicevuta;
	}
	
	//TODO: modificare il tipo per il ritorno di una ricevuta
	@Override
	public abstract void getRicevuta() throws SQLException, ClassNotFoundException;
	
	@Override
	public abstract void salva() throws SQLException, ClassNotFoundException;
	
	@Override
	public void elimina() throws SQLException, ClassNotFoundException{
		GestioneDB db = GestioneDB.generaControllore();
		db.connetti();
		db.eliminaRecord("Movimenti", Optional.of("IDMovimento"), Optional.of(this.idMovimento));
		db.disconnetti();
	}
}
