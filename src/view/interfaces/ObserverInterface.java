package view.interfaces;

import java.awt.TextField;
import java.util.Map;
import java.util.Optional;

import javax.swing.JFrame;

public interface ObserverInterface {
	Optional<JFrame> getAttuale();
	void setAttuale(JFrame attuale);
	void mostraClienti();
	void mostraFornitori();
	void mostraFatture();
	public void Start();
	void mostraMenu();
	void mostraMagazzino();
	void mostraRegistiIva();
	void mostraReportVendite();
	void mostraRiunioni();
	void mostraScontrini();
	void mostraDialogCampoObbligatorio();
	void mostraDialogCerca();
	void mostraDialogNuovo();
	void mostraDialogRegistrati();
	void mostraDialogWrongPass();
	void mostraDialogWrongUser();
	void salvaUtente(Map<String, TextField> dati);
	void salvaCliente(Map<String, TextField> dati);
	void salvaFornitore(Map<String, TextField> dati);
	void salvaFattura(Map<String, Object> dati);
	void salvaRiunione(Map<String, Object> dati);
	
	//Domandina veloce... Dobbiamo anche mettere un database esempio con gi� tutta la roba dentro per fare vedere che funziona,
	//quindi bisonga implementare la funzione carica? tipo void caricaDataBase?

}
