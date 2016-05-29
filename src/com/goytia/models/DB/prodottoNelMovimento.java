package com.goytia.models.DB;

public class prodottoNelMovimento {
	
	private Integer idProdotto;
	private int quantita;
	double prezzoUnitario;
	
	public prodottoNelMovimento(Integer idProdotto, int quantita, double prezzoUnitario){
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
