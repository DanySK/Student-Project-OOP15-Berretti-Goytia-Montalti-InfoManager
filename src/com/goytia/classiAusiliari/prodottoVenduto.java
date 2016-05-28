package com.goytia.classiAusiliari;

public class prodottoVenduto {
	
	private Integer idProdotto;
	private int quantita;
	double prezzoUnitario;
	
	public prodottoVenduto(Integer idProdotto, int quantita, double prezzoUnitario){
		this.idProdotto = idProdotto;
		this.quantita = quantita;
		this.prezzoUnitario = prezzoUnitario;
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
