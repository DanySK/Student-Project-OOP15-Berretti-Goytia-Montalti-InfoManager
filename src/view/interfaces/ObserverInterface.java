package view.interfaces;

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
	void abilitaFrame(boolean abilita);
	void salvaUtente(Map<String,String> dati);
	void salvaCliente(Map<String,String> dati);
	void salvaFornitore(Map<String, String> dati);
	void salvaFattura(Map<String, String> dati);
	void salvaRiunione(Map<String, String> dati);
	
	//Domandina veloce... Dobbiamo anche mettere un database esempio con gi� tutta la roba dentro per fare vedere che funziona,
	//quindi bisonga implementare la funzione carica? tipo void caricaDataBase?

}
