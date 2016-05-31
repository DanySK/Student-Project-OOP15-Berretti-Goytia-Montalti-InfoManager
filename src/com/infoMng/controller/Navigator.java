package com.infoMng.controller;

import java.util.Collection;

interface Navigator<T> {
	
	static Navigator<?> creatNavigator(Collection<?> elementi){
		return new ListOfObjectImpl<>(elementi);
	}
	
	T avanti();

	T indietro();

	Boolean isPrimo();

	Boolean isUltimo();

}