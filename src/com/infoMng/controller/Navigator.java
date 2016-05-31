package com.infoMng.controller;

import java.util.Collection;

interface Navigator<T> {

	public static Navigator<? super Object> navigatorFromObject(Collection<? super Object> oggetti){
		return new ListOfObjectImpl<>(oggetti);
	}
	
	T avanti();

	T indietro();

	Boolean isPrimo();

	Boolean isUltimo();

}