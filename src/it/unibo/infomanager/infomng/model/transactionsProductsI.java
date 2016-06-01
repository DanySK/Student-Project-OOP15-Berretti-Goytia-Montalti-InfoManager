package it.unibo.infomanager.infomng.model;

public interface transactionsProductsI {

	@Deprecated
	Integer getIDProductInvolved();

	int getQuantity();

	double getPrice();
	
}