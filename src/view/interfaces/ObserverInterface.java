package view.interfaces;

public interface ObserverInterface {
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
	void salvaUtente();
	void salvaCliente();
	void salvaFornitore();
	void salvaFattura();
	void salvaRiunione();
	
	//Domandina veloce... Dobbiamo anche mettere un database esempio con già tutta la roba dentro per fare vedere che funziona,
	//quindi bisonga implementare la funzione carica? tipo void caricaDataBase?

}
