package com.infoMng.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JFrame;

import com.goytia.models.DB.modelClients;
import com.goytia.models.DB.modelProviders;
import com.goytia.models.DB.modelReceipts;
import com.goytia.models.DB.modelReunions;
import com.goytia.models.DB.modelStore;
import com.goytia.models.DB.modelUsers;
import com.infoMng.model.IFattura;

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
	public boolean salvaUtente(Map<String, String> dati) {
		// ottengo i dati utente
		String username = dati.get("Username");
		String password = dati.get("Password");
		String mail = dati.get("Email");
		String indirizzo = dati.get("Indirizzo");
		String nomeNegozio = dati.get("Nome");
		
		return modelUsers.newUser(nomeNegozio, null, mail, username, password);
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
	public saveResult salvaFattura(Map<String, Object> dati) throws ParseException, NumberFormatException {
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
		
		
		IFattura.FatturaBuilder builder = new IFattura.FatturaBuilder();
		//imposto il numero della fattura
		builder.setNumeroOrdine(numeroFattura);
		//cerco il fornitore
		Optional<modelProviders> tmpFornitore = this.ottieniFornitoreDaNome(cliente);
		if(tmpFornitore.isPresent()){
			//il fornitore è stato trovato
			builder.setFornitore(tmpFornitore.get());
		}
		else{
			//il fornitore non è stato trovato
			//cerco il cliente
			Optional<modelClients> tmpCliente = this.ottieniClienteDaNome(cliente);
			if(tmpCliente.isPresent()){
				//il cliente è stato trovato
				builder.setCliente(tmpCliente.get());
			}
			else{
				//non è stato trovato ne cliente ne fornitore
				return saveResult.errorData;
			}
		}
		
		//imposto le date di richiesta e di consegna
		builder.setData(data);
		builder.getConsegna()[0] = inizio;
		builder.getConsegna()[1] = fine;
		
		builder.setBanca(banca);
		
		builder.setSconto(sconto);
		builder.setIVA(iva);
		builder.setNomeNegozio(nomeNegozio);
		builder.setNote(descrizione);
		
		//ho finito di impostare i campi della fattura ora aggiungo i prodotti
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> prodotti = (List<Map<String, Object>>) dati.get("Prodotti");
		prodotti.stream()
		.map(e ->{
			String nomeProdotto = (String) e.get("Nome");
			Integer quantita = Integer.parseInt((String) e.get("Quantita"));
			Double prezzo = Double.parseDouble((String) e.get("Prezzo"));
			//cerco il prodotto
			Optional<modelStore> tmpProdotto = this.ottengoProdottoDaNome(nomeProdotto);
			IFattura.prodottoFattura ritorno = null;
			if(tmpProdotto.isPresent()){
				ritorno = new IFattura.prodottoFattura();
				ritorno.prodotto = tmpProdotto.get();
				ritorno.prezzo = prezzo;
				ritorno.quantita = quantita;
			}
			else{
				System.out.println(String.format("Prodotto %s non trovato", nomeProdotto));
			}
			return Optional.ofNullable(ritorno);
		})
		.filter(e -> e.isPresent()).map(e -> e.get())
		.forEach(e -> builder.addProdotto(e));
		
		//ho aggiunto tutti i prodotti che sono riuscito a trovare
		IFattura nuova = builder.salva();
		if(nuova != null){
			return saveResult.success;
		}
		else{
			return saveResult.errorSave;
		}
	}
	
	private Optional<modelStore> ottengoProdottoDaNome(String nome){
		List<modelStore> tmp = modelStore.serachProductsByName(nome);
		if(tmp.size() > 0){
			return Optional.of(tmp.get(0));
		}
		else{
			return Optional.empty();
		}
	}
	
	private Optional<modelProviders> ottieniFornitoreDaNome(String nome){
		List<modelProviders> tmp = modelProviders.searchProviders(nome, null, null, null);
		if(tmp.size() > 0){
			return Optional.of(tmp.get(0));
		}
		else{
			return Optional.empty();
		}
	}
	
	private Optional<modelClients> ottieniClienteDaNome(String nome){
		List<modelClients> tmp = modelClients.searchClients(nome, null, null, null, null);
		if(tmp.size() > 0){
			return Optional.of(tmp.get(0));
		}
		else{
			return Optional.empty();
		}
	}

	@Override
	public boolean salvaRiunione(Map<String, String> dati) throws ParseException {
		String evento = dati.get("Evento");
		String giorno = dati.get("Giorno");
		String mese = dati.get("Mese");
		String anno = dati.get("Anno");
		String note = dati.get("Note");
		
		String nome = String.format("Riunione del %s/%s/%s", giorno, mese, anno);
		
		DateFormat formatterData = new SimpleDateFormat("dd-MM-yyyy");
		Date dataEora = new Date(formatterData.parse(String.format("%s-%s-%s", giorno, mese, anno)).getTime());
		return modelReunions.newReunion(nome, "", "", note, dataEora);
		
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
		//ottengo i dati dalla mappa
		//TODO : chiedere a monta quale è la view per lo scontrino
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
	public List<modelProviders> cercaFornitori(String nome) {
		return modelProviders.searchProviders(nome, null, null, null);
	}

	@Override
	public List<modelClients> cercaClienti(String nome) {
		return modelClients.searchClients(nome, null, null, null, null);
	}

	@Override
	public List<modelReunions> cercaRiunioni(String data, String nome) throws ParseException {
		// TODO Auto-generated method stub
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date giorno = new Date(format.parse(data).getTime());
		return modelReunions.reunionsList().stream().filter(r -> {
			return r.getNameReunion().equals(nome) && r.getDate().equals(giorno);
		}).collect(Collectors.toList());
	}

	@Override
	public modelReceipts cercaScontrini(String numero, String nome) throws NumberFormatException {
		Integer numeroScontrino = Integer.parseInt(numero);
		return modelReceipts.searchReceiptByNumber(numeroScontrino);
	}

	@Override
	public boolean userLogin(String user, String pass) {
		return modelUsers.usersLogin(user, pass);
	}

	@Override
	public Optional<IFattura> cercaFatture(String numero, String nome, String cognome) throws NumberFormatException {
		Integer invoiceNumber = Integer.parseInt(numero);
		try {
			return IFattura.searchIvoicesForNumber(invoiceNumber, nome, cognome);
		} catch (SQLException e) {
			return Optional.empty();
		}
	}


	public enum saveResult{
		success(""),
		errorData("Dati non corretti"),
		errorSave("Errore durante il salvataggio");
		
		public String rawValue;
		
		private saveResult(String rawValue){
			this.rawValue = rawValue;
		}
	}
}
