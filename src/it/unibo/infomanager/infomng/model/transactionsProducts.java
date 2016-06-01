package it.unibo.infomanager.infomng.model;

public class transactionsProducts implements transactionsProductsI {
	
	private Integer idProduct;
	private int quantity;
	private double price;
	
	public transactionsProducts(Integer idProdotto, int quantita, double prezzoUnitario){
		this.idProduct = idProdotto;
		this.quantity = quantita;
		this.price = prezzoUnitario;
	}
	
	public Integer getIDProductInvolved(){
		return this.idProduct;
	}
	
	public int getQuantity(){
		return this.quantity;
	}
	
	public double getPrice(){
		return this.price;
	}
	
}	
