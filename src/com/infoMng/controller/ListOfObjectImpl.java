package com.infoMng.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;

import view.ClientiGUI;
import view.FornitoriGUI;

class ListOfObjectImpl<T> implements Navigator<T> {
	private Integer posizione;
	private List<T> oggetti;
	private JFrame view;
	
	protected ListOfObjectImpl(Collection<T> elementi, JFrame view){
		this.oggetti = new ArrayList<>();
		this.oggetti.addAll(elementi);
		this.posizione = -1;
		this.view = view;
		if(this.view.getClass().equals(ClientiGUI.class)){
			((ClientiGUI)this.view).fornitori = this;
		}
		else if(this.view.getClass().equals(FornitoriGUI.class)){
			((FornitoriGUI)this.view).fornitori = this;
		}
	}
	
	protected ListOfObjectImpl(Collection<T> elementi){
		this(elementi, null);
	}
	
	@Override
	public T avanti(){
		if(this.oggetti.isEmpty()){
			return null;
		}
		if(posizione < this.oggetti.size() - 1){
			this.posizione += 1;
		}
		
		T oggetto = this.oggetti.get(this.posizione);
		if(view != null){
			if(this.view.getClass().equals(ClientiGUI.class)){
				((ClientiGUI)this.view).setTextFields(oggetto);
			}
			else if(this.view.getClass().equals(FornitoriGUI.class)){
				((FornitoriGUI)this.view).setTextFields(oggetto);
			}
		}
		
		return oggetto;
	}
	
	@Override
	public T indietro(){
		if(this.oggetti.isEmpty()){
			return null;
		}
		if(this.posizione > 0){
			this.posizione -= 1;
		}
		T oggetto = this.oggetti.get(this.posizione);
		if(view != null){
			if(this.view.getClass().equals(ClientiGUI.class)){
				((ClientiGUI)this.view).setTextFields(oggetto);
			}
			else if(this.view.getClass().equals(FornitoriGUI.class)){
				((FornitoriGUI)this.view).setTextFields(oggetto);
			}
		}
		
		return oggetto;
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
