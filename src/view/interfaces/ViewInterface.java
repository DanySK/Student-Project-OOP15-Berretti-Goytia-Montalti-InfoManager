package view.interfaces;

public interface ViewInterface {
	/**
	 * Method that create viewClient
	 */
	void viewClienti();
	/**
	 * Method that create viewFornitori
	 */
	void viewFornitori();
	/**
	 * Method that create viewFatture
	 */
	void viewFatture();
	/**
	 * Method that create the Start view
	 */
	void viewStart();
	/**
	 * Method that create viewMenu
	 */
	void viewMenu();
	/**
	 * Method that create viewMagazzino
	 */
	void viewMagazzino();
	/**
	 * Method that create viewRegistiIva
	 */
	void viewRegistiIva();
	/**
	 * Method that create viewReportVendite
	 */
	void viewReportVendite();
	/**
	 * Method that create viewRiunioni
	 */
	void viewRiunioni();
	/**
	 * Method that create viewScontrini
	 */
	void viewScontrini();
	/**
	 * Method that create viewDialogCampoObbligatorio
	 */
	void viewDialogCampoObbligatorio();
	/**
	 * Method that create viewDialogCerca
	 */
	void viewDialogCerca();
	/**
	 * Method that create viewDialogNuovo
	 */
	void viewDialogNuovo();
	/**
	 * Method that create viewDialogRegistrati
	 */
	void viewDialogRegistrati();
	/**
	 * Method that create viewDialogWrongPass
	 */
	void viewDialogWrongPass();
	/**
	 * Method that create viewDialogWrongUser
	 */
	void viewDialogWrongUser();
	/**
	 * Method that set the Object ObserverInterface
	 * @param o
	 * 			Object ObserverInterface
	 */
	void setOggettoController(ObserverInterface o);
}
