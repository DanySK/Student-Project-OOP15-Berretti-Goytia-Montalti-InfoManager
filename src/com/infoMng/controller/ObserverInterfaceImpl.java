package com.infoMng.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.JFrame;

import com.goytia.models.DB.modelClienti;
import com.goytia.models.DB.modelFornitori;
import com.goytia.models.DB.modelMagazzino;

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
		this.view.viewClienti();

	}

	@Override
	public void mostraFornitori() {
		this.view.viewFornitori();

	}

	@Override
	public void mostraFatture() {
		this.view.viewFatture();

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
	public void salvaUtente(Map<String,String> dati) {
		 
	}

	@Override
	public boolean salvaCliente(Map<String,String> dati) {
	    String nome = dati.get("Nome");
	    String cognome = dati.get("Cognome");
	    String mail = dati.get("Email");
	    String telefono = dati.get("Telefono");
	    String nomeNegozio = ""; //in attesa del metodoto per il nome negozio
	    return modelClienti.nuovoCliente(nome, cognome, mail, telefono, nomeNegozio);
	}

	@Override
	public boolean salvaFornitore(Map<String, String> dati) {
		String nome = dati.get("Nome");
		String cognome = dati.get("Cognome");
		String telefono = dati.get("Telefono");
		String mail = dati.get("Email");
		return modelFornitori.nuovoFornitore(nome, cognome, mail, telefono);
	}

	@Override
	public void salvaFattura(Map<String, String> dati) {
		// TODO chiamata model per salvare fattura

	}

	@Override
	public void salvaRiunione(Map<String, String> dati) {
		// TODO chiamata model per salvare riunione

	}

	@Override
	public void abilitaFrame(boolean attiva) {
		if (attiva){
			this.attuale.setEnabled(attiva);
		} else {
			this.attuale.setEnabled(attiva);
		}
	}

	@Override
	public void salvaScontrini(Map<String, String> dati) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<modelMagazzino> ricercaProdotti(String nome) {
		// TODO Auto-generated method stub
		return null;
	}


}
