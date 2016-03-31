package com.mattiaberretti.prodotti;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.mattiaberretti.database.GestioneDB;

class MovimentoMagazzino implements IMovimentoMagazzino {
	private Integer idMovimento;
	private Integer quantita;
	private Integer idProdotto;
	private Date data;
	private Integer idVendita;
	private Integer idAcquisto;
	private String descrizione;
	private Double prezzo;
	private Integer iva;
	
	protected MovimentoMagazzino(Integer idMovimento, Integer quantita, Integer idProdotto, Date data,
			Integer idVendita, Integer idAcquisto, String descrizione, Double prezzo, Integer IVA) {
		this.idMovimento = idMovimento;
		this.quantita = quantita;
		this.idProdotto = idProdotto;
		this.data = data;
		this.idVendita = idVendita;
		this.idAcquisto = idAcquisto;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.iva = IVA;
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
	public void setQuantita(Integer quantita){
		this.quantita = quantita;
	}

	@Override
	public Integer getIdProdotto() {
		return idProdotto;
	}

	@Override
	public Date getData() {
		return data;
	}

	@Override
	public Integer getIdVendita() {
		return idVendita;
	}

	@Override
	public Integer getIdAcquisto() {
		return idAcquisto;
	}

	@Override
	public String getDescrizione() {
		return descrizione;
	}
	
	
	@Override
	public Double getPrezzo() {
		return prezzo;
	}
	@Override
	public Integer getIVA() {
		return iva;
	}
	@Override
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	@Override
	public void setIVA(Integer iva) {
		this.iva = iva;
	}

	@Override
	public void salva() throws ClassNotFoundException, SQLException{
		GestioneDB db = GestioneDB.generaControllore();
		db.connetti();
		Map<String, Object> valori = new HashMap<>();
		valori.put("Prezzo", this.prezzo);
		valori.put("IVA", this.iva);
		valori.put("Quantita", this.quantita);
		valori.put("Descrizione", this.descrizione);
		valori.put("Data", this.data);
		db.aggiornaTabella("Movimenti", valori, "IDMovimento", new Object[]{this.idMovimento});
		db.disconnetti();
	}
	
	@Override
	public void elimina() throws ClassNotFoundException, SQLException{
		GestioneDB db = GestioneDB.generaControllore();
		db.connetti();
		db.eliminaRecord("Movimenti", Optional.of("IDMovimento"), Optional.of(this.idMovimento));
		db.disconnetti();
	}
	
}
