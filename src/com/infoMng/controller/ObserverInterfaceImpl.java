package com.infoMng.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.JFrame;

import com.goytia.models.DB.modelClients;
import com.goytia.models.DB.modelProviders;
import com.goytia.models.DB.modelStore;

import view.interfaces.ObserverInterface;
import view.interfaces.ViewInterface;

public class ObserverInterfaceImpl implements ObserverInterface {

	private ViewInterface view;
	private JFrame attuale;

	@Override
	public Optional<JFrame> getAttuale() {
		return Optional.ofNullable(this.attuale);
	}

	@Override
	public void setAttuale(JFrame attuale) {
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
	public void start() {
		this.view.viewStart();
	}

	@Override
	public void mostraMenu() {
		this.view.viewMenu();
		if (this.attuale != null) {
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
	public void salvaUtente(Map<String, String> dati) {
		// TODO: Da fare
	}

	@Override
	public boolean salvaCliente(Map<String, String> dati) {
		String nome = dati.get("Nome");
		String cognome = dati.get("Cognome");
		String mail = dati.get("Email");
		String telefono = dati.get("Telefono");
		String nomeNegozio = ""; // in attesa del metodoto per il nome negozio
		return modelClients.newClient(nome, cognome, mail, telefono, nomeNegozio);
	}

	@Override
	public boolean salvaFornitore(Map<String, String> dati) {
		String nome = dati.get("Nome");
		String cognome = dati.get("Cognome");
		String telefono = dati.get("Telefono");
		String mail = dati.get("Email");
		return modelProviders.newProvider(nome, cognome, mail, telefono);
	}

	@Override
	public int salvaFattura(Map<String, Object> dati) throws ParseException, NumberFormatException {
		// TODO: fai enumerazione con i casi SUCCESSO o ERROREDATA o
		// ERRORESALVATAGGIO al posto del ritorno int perch� fa pi� figo
		Integer numeroFattura = Integer.parseInt((String) dati.get("NumeroOrdine"));
		String cliente = (String) dati.get("Fornitore/Cliente");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		Date data = new Date(dateFormatter.parse((String) dati.get("DataOrdine")).getTime());

		Date inizio = new Date(dateFormatter.parse((String) dati.get("Dal")).getTime());
		Date fine = new Date(dateFormatter.parse((String) dati.get("Al")).getTime());
		String banca = (String) dati.get("Banca");

		Double sconto = Double.parseDouble((String) dati.get("Sconto"));
		Integer iva = Integer.parseInt((String) dati.get("IVA"));

		String descrizione = String.format("Tipo ordine : %s, Banca %s, note %s", banca,
				(String) dati.get("Tipo ordine"), (String) dati.get("Note"));
		String nomeNegozio = (String) dati.get("Negozio");
		return 1;

	}

	@Override
	public void salvaRiunione(Map<String, String> dati) {
		// TODO chiamata model per salvare riunione

	}

	@Override
	public void abilitaFrame(boolean attiva) {
		if (attiva) {
			this.attuale.setEnabled(attiva);
		} else {
			this.attuale.setEnabled(attiva);
		}
	}

	@Override
	public void salvaScontrini(Map<String, Object> dati) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<modelStore> ricercaProdotti(String nome) {
		return modelStore.serachProductsByName(nome);
	}

	@Override
	public int elencoProdotti(List<modelStore> lista) {
		return lista.size();
	}

	@Override
	public void cercaFornitori(String nome) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cercaClienti(String nome) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cercaRiunioni(String data, String nome) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cercaScontrini(String numero, String nome) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean userLogin(String user, String pass) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void cercaFatture(String numero, String nome) {
		// TODO Auto-generated method stub
		
	}



}
