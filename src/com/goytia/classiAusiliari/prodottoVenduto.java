package com.goytia.classiAusiliari;

public class prodottoVenduto {
	
	private Integer idProdotto;
	private int quantita;
	double prezzoUnitario;
	
	public prodottoVenduto(Integer idProdotto, int quantita, double prezzoUnitario, float IVA, float Sconto){
		this.idProdotto = idProdotto;
		this.quantita = quantita;
	}
	
	public Integer getIDProdottoV(){
		return this.idProdotto;
	}
	
	public int getQuantita(){
		return this.quantita;
	}
	
	public double getPrezzoUnitario(){
		return this.prezzoUnitario;
	}
	
}	
