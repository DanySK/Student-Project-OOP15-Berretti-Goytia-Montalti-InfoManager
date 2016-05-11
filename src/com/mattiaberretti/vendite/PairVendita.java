package com.mattiaberretti.vendite;

class PairVendita {
	private Integer quantita;
	private Double prezzo;
	public PairVendita(Integer quantita, Double prezzo) {
		super();
		this.quantita = quantita;
		this.prezzo = prezzo;
	}
	public Integer getQuantita() {
		return quantita;
	}
	public Double getPrezzo() {
		return prezzo;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	
	
}
