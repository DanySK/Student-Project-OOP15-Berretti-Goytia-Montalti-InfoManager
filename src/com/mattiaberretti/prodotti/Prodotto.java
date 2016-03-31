package com.mattiaberretti.prodotti;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mattiaberretti.database.GestioneDB;

class Prodotto implements IProdotto {
	private Integer idProdotto;
	private String nome;
	private Double prezzoAcquisto;
	private Double prezzoVendita;
	
	protected Prodotto(Integer idProdotto, String nome, Double prezzoAcquisto, Double prezzoVendita) {
		this.idProdotto = idProdotto;
		this.nome = nome;
		this.prezzoAcquisto = prezzoAcquisto;
		this.prezzoVendita = prezzoVendita;
	}

	@Override
	public Integer getIDProdotto(){
		return this.idProdotto;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public Double getPrezzoAcquisto() {
		return prezzoAcquisto;
	}

	@Override
	public Double getPrezzoVendita() {
		return prezzoVendita;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public void setPrezzoAcquisto(Double prezzoAcquisto) {
		this.prezzoAcquisto = prezzoAcquisto;
	}

	@Override
	public void setPrezzoVendita(Double prezzoVendita) {
		this.prezzoVendita = prezzoVendita;
	}
	
	@Override
	public Integer quantita() throws ClassNotFoundException, SQLException{
		GestioneDB db = GestioneDB.generaControllore();
		db.connetti();
		Integer ritorno = db.eseguiLettura(new String[]{"IDProdotto", "Quantita"}, "Movimenti").stream()
				.filter(w -> w.get("IDProdotto").equals(this.idProdotto))
				.mapToInt(i -> (Integer)i.get("Quantita"))
				.sum();
		db.disconnetti();
		return ritorno;
	}
	
	@Override
	public void salva() throws ClassNotFoundException, SQLException{
		GestioneDB db = GestioneDB.generaControllore();
		db.connetti();
		Map<String, Object> valori = new HashMap<>();
		valori.put("Nome", this.nome);
		valori.put("PrezzoAcquisto", this.prezzoAcquisto);
		valori.put("PrezzoVendita", this.prezzoVendita);
		
		db.aggiornaTabella("Prodotti", valori, "IDProdotto", new Object[]{this.idProdotto});
		
		db.disconnetti();
	}

	@Override
	public List<IMovimentoMagazzino> elencoMovimenti() throws ClassNotFoundException, SQLException {
		GestioneDB db = GestioneDB.generaControllore();
		db.connetti();
		List<Map<String, Object>> valori = db.eseguiLettura(new String[]{"IDMovimento", "IDProdotto", "IDVendita", "IDAcquisto", "Quantita", "Data", "Prezzo", "IVA", "Descrizione"}, "Movimenti").stream()
				.filter(w -> w.get("IDProdotto").equals(this.idProdotto))
				.collect(Collectors.toList());
		db.disconnetti();
		
		List<IMovimentoMagazzino> ritorno = new ArrayList<>();
		for (Map<String, Object> val : valori) {
			ritorno.add(new MovimentoMagazzino((Integer)val.get("IDMovimento"),
					(Integer)val.get("Quantita"),
					(Integer)val.get("IDProdotto"),
					(Date)val.get("Data"),
					(Integer)val.get("IDVendita"),
					(Integer)val.get("IDAcquisto"),
					(String)val.get("Descrizione"),
					(Double)val.get("Prezzo"),
					(Integer)val.get("IVA")));
		}
		
		return ritorno;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Prodotto && ((Prodotto)obj).getIDProdotto().equals(this.getIDProdotto());
	}
}
