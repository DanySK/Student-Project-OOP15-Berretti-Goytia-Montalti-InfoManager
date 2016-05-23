package com.infoMng.controller;

import java.awt.TextField;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.swing.JFrame;

import view.ClientiGUI;
import view.interfaces.ObserverInterface;
import view.interfaces.ViewInterface;

public class ObserverInterfaceImpl implements ObserverInterface {

	private ViewInterface view;
	private JFrame attuale;
	
	@Override
	public Optional<JFrame> getAttuale(){
		return Optional.ofNullable(this.attuale);
	}
	
	@Override
	public void setAttuale(JFrame attuale){
		this.attuale = attuale;
	}
	
	public ObserverInterfaceImpl(ViewInterface view) {
		this(view, null);
	}
	
	public ObserverInterfaceImpl(ViewInterface view, JFrame attuale) {
		this.view = view;
		this.view.setOggettoController(this);
		this.attuale = attuale;
	}
	
	@Override
	public void mostraClienti() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mostraFornitori() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mostraFatture() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Start() {
		//TODO: controllare se c'è l'utente loggato se no eseguo l'accesso
		this.view.viewStart();
	}

	@Override
	public void mostraMenu() {
		this.view.viewMenu();
		if(this.attuale != null){
			this.attuale.dispose();
		}
	}

	@Override
	public void mostraMagazzino() {
		this.view.viewMagazzino();
	}

	@Override
	public void mostraRegistiIva() {
		this.view.viewRegistiIva();
	}

	@Override
	public void mostraReportVendite() {
		this.view.viewReportVendite();

	}

	@Override
	public void mostraRiunioni() {
		this.view.viewRiunioni();

	}

	@Override
	public void mostraScontrini() {
		this.view.viewScontrini();

	}

	@Override
	public void mostraDialogCampoObbligatorio() {
		this.view.viewDialogCampoObbligatorio();
	}

	@Override
	public void mostraDialogCerca() {
		this.view.viewDialogCerca();
	}

	@Override
	public void mostraDialogNuovo() {
				this.view.viewDialogNuovo();

	}

	@Override
	public void mostraDialogRegistrati() {
		this.view.viewDialogRegistrati();
	}

	@Override
	public void mostraDialogWrongPass() {
		this.view.viewDialogWrongPass();
	}

	@Override
	public void mostraDialogWrongUser() {
		this.view.viewDialogWrongUser();
	}

	@Override
	public void salvaUtente() {
		 
	}

	@Override
	public void salvaCliente() {
		Map<String,String> mappa = new HashMap<>();
		mappa = new ClientiGUI(view.getObserverInterface()).getTextfield();
		//usa la mappa per dare i dati al model
	}

	@Override
	public void salvaFornitore(Map<String, TextField> dati) {
		// TODO Auto-generated method stub

	}

	@Override
	public void salvaFattura(Map<String, Object> dati) {
		// TODO Auto-generated method stub

	}

	@Override
	public void salvaRiunione(Map<String, Object> dati) {
		// TODO Auto-generated method stub

	}

	@Override
	public void abilitaFrame(boolean attiva) {
		if (attiva){
			this.attuale.setEnabled(attiva);
		} else {
			this.attuale.setEnabled(attiva);
		}
	}


}
