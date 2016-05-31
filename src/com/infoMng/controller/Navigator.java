package com.infoMng.controller;

import java.util.Collection;

interface Navigator<T> {
	
	static Navigator<?> creatNavigator(Collection<?> elementi){
		return new ListOfObjectImpl<>(elementi);
	}
	
	/**
	 * Obtein the next item
	 * @return
	 * the next item in the list if the actual item is the last return the last item
	 */
	T avanti();

	/**
	 * Obtein the previus item
	 * @return
	 * the previus item in the list if the actual item is the first return the first item
	 */
	T indietro();

	Boolean isPrimo();

	Boolean isUltimo();

}