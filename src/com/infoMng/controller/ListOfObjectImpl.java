package com.infoMng.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class ListOfObjectImpl<T> implements Navigator<T> {
	private Integer posizione;
	private List<T> oggetti;
	
	protected ListOfObjectImpl(Collection<T> elementi){
		this.oggetti = new ArrayList<>();
		this.oggetti.addAll(elementi);
		this.posizione = -1;
	}
	
	@Override
	public T avanti(){
		if(posizione < this.oggetti.size() - 1){
			this.posizione += 1;
		}
		return this.oggetti.get(this.posizione);
	}
	
	@Override
	public T indietro(){
		if(this.posizione > 0){
			this.posizione -= 1;
		}
		return this.oggetti.get(this.posizione);
	}
	
	@Override
	public Boolean isPrimo(){
		return this.posizione == 0;
	}
	
	@Override
	public Boolean isUltimo(){
		return this.posizione == (this.oggetti.size() - 1);
	}
}
