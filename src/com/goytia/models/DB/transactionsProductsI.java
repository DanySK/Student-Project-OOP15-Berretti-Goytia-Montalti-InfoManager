package com.goytia.models.DB;

public interface transactionsProductsI {

	@Deprecated
	Integer getIDProductInvolved();

	int getQuantity();

	double getPrice();
	
}