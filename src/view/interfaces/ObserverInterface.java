package view.interfaces;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.JFrame;

import com.goytia.models.DB.modelProviders;
import com.goytia.models.DB.modelStore;

public interface ObserverInterface {
	/**
	 * Method that get current JFrame
	 * @return
	 * 		   Object Optional(JFrame)
	 */
	Optional<JFrame> getAttuale();
	/**
	 * Method that set current JFrame
	 * @param attuale
	 * 			Object JFrame
	 */
	void setAttuale(JFrame attuale);
	/**
	 * Method that show ClientiGUI
	 */
	void mostraClienti();
	/**
	 * Method that show FornitoriGUI
	 */
	void mostraFornitori();
	/**
	 * Method that show FattureGUI
	 */
	void mostraFatture();
	/**
	 * Method that launch the application
	 */
	public void Start();
	/**
	 * Method that show MenuGUI
	 */
	void mostraMenu();
	/**
	 * Method that show MagazzinoGUI
	 */
	void mostraMagazzino();
	/**
	 * Method that show RegistriIvaGUI
	 */
	void mostraRegistiIva();
	/**
	 * Method that show ReportVenditeGUI
	 */
	void mostraReportVendite();
	/**
	 * Method that show RiunioniGUI
	 */
	void mostraRiunioni();
	/**
	 * Method that show ScontriniGUI
	 */
	void mostraScontrini();
	/**
	 * Method that show DialogCampoObbligatorio
	 */
	void mostraDialogCampoObbligatorio();
	/**
	 * Method that show DiaslogCerca
	 */
	void mostraDialogCerca();
	/**
	 * Method that show DialogNuovo
	 */
	void mostraDialogNuovo();
	/**
	 * Method that show DialogRegistrati
	 */
	void mostraDialogRegistrati();
	/**
	 * Method that show DialogWrongPass
	 */
	void mostraDialogWrongPass();
	/**
	 * Method that show DialogWrongUser
	 */
	void mostraDialogWrongUser();
	/**
	 * Method that set JFrame Visible
	 * @param abilita
	 * 			Variable boolean, true if you want to show current JFrame, false instead
	 */
	void abilitaFrame(boolean abilita);
	/**
	 * Method that save User
	 * @param dati
	 * 			Map (String,String)
	 */
	void salvaUtente(Map<String,String> dati);
	/**
	 * Method that save Client
	 * @param dati
	 * 			Map (String,String)
	 * @return
	 * 			Variable boolean, true if the operation goes well, false Instead
	 */
	boolean salvaCliente(Map<String,String> dati);
	/**
	 * Method that save supplier
	 * @param dati
	 * 			Map (String,String)
	 * @return
	 * 			Variable boolean, true if the operation goes well, false Instead
	 */
	boolean salvaFornitore(Map<String, String> dati);
	/**
	 * Method that save invoice
	 * @param map
	 * 			Map (String,String)
	 * @throws ParseException 
	 */
	void salvaFattura(Map<String, Object> map) throws ParseException;
	/**
	 * Method that save reunion
	 * @param dati
	 * 			Map (String,String)
	 */
	void salvaRiunione(Map<String, String> dati);
	/**
	 * Method that save receipt
	 * @param dati
	 * 			Map (String,String)
	 */
	void salvaScontrini(Map<String, Object> dati);
	
	List<modelStore> ricercaProdotti(String nome);
	
	List<modelStore> elencoProdotti();
}
