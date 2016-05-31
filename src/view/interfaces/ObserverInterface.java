package view.interfaces;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.JFrame;

import com.goytia.models.DB.modelClients;
import com.goytia.models.DB.modelProviders;
import com.goytia.models.DB.modelStore;
import com.infoMng.controller.ObserverInterfaceImpl.saveResult;

/**
 * Interfaccia che definisce ObserverInterface.
 * @author Alessandro
 *
 */
public interface ObserverInterface {
	/**
	 * Metodo per ottenere il JFrame attuale.
	 * 
	 * @return oggetto Optional(JFrame)
	 */
	Optional<JFrame> getAttuale();

	/**
	 * Metodo per settare il JFrame attuale.
	 * 
	 * @param attuale
	 *            Oggetto JFrame
	 */
	void setAttuale(JFrame attuale);

	/**
	 * metodo che mostra ClientiGUI.
	 */
	void mostraClienti();

	/**
	 * Metodo che mostra FornitoriGUI.
	 */
	void mostraFornitori();

	/**
	 * Metodo che mostra FattureGUI.
	 */
	void mostraFatture();

	/**
	 * Metodo che lancia l'applicazione.
	 */
	void start();

	/**
	 * Metodo che mostra MenuGUI.
	 */
	void mostraMenu();

	/**
	 * Metodo che mostra MagazzinoGUI.
	 */
	void mostraMagazzino();

	/**
	 * Metodo che mostra RegistriIvaGUI.
	 */
	void mostraRegistiIva();

	/**
	 * Metodo che mostra ReportVenditeGUI.
	 */
	void mostraReportVendite();

	/**
	 * Metodo che mostra RiunioniGUI.
	 */
	void mostraRiunioni();

	/**
	 * Metodo che mostra ScontriniGUI.
	 */
	void mostraScontrini();

	/**
	 * Metodo che mostra DialogCampoObbligatorio.
	 */
	void mostraDialogCampoObbligatorio();

	/**
	 * Metodo che mostra DiaslogCerca.
	 */
	void mostraDialogCerca();

	/**
	 * Metodo che mostra DialogNuovo.
	 */
	void mostraDialogNuovo();

	/**
	 * Metodo che mostra DialogRegistrati.
	 */
	void mostraDialogRegistrati();

	/**
	 * Metodo che mostra DialogWrongPass.
	 */
	void mostraDialogWrongPass();

	/**
	 * Metodo che mostra DialogWrongUser.
	 */
	void mostraDialogWrongUser();

	/**
	 * Metodo che setta l' attuale JFrame Visibile.
	 * 
	 * @param abilita
	 *            Variabile boolean, true se vuoi abilitare il JFrame attuale,
	 *            false se non vuoi abilitare il JFrame
	 */
	void abilitaFrame(boolean abilita);

	/**
	 * Metodo che salva l' User.
	 * 
	 * @param dati
	 *            Map (String,String)
	 * @return a boolean value than it is the result of registred
	 */
	boolean salvaUtente(Map<String, String> dati);

	/**
	 * Metodo che salva il Cliente.
	 * 
	 * @param dati
	 *            Map (String,String)
	 * @return Variabile boolean, true se l'operazione va a buon fine, false se non va buon fine
	 */
	boolean salvaCliente(Map<String, String> dati);

	/**
	 * Metodo che salva i fornitori.
	 * 
	 * @param dati
	 *            Map (String,String)
	 * @return Variabile boolean, true se l'operazione va a buon fine, false se non va buon fine
	 */
	boolean salvaFornitore(Map<String, String> dati);

	/**
	 * Metodo che salva la fattura.
	 * 
	 * @param map
	 *            Map (String,String)
	 * @return Enumerator than identifer if save had done or it was fail for incurrect data or if the operation have a error
	 * @throws ParseException
	 * 			da circondare con try catch
	 * 			the numeric data is not in correct format
	 */
	saveResult salvaFattura(Map<String, Object> map) throws ParseException;

	/**
	 * Metodo che salva le riunioni.
	 * 
	 * @param dati
	 *            Map (String,String)
	 * @return The result of saving of data
	 * @throws ParseException 
	 * 				Error while try parse DateString into Date
	 * 
	 */
	boolean salvaRiunione(Map<String, String> dati) throws ParseException;

	/**
	 * Metodo che salva gli scontrini.
	 * 
	 * @param dati
	 *            Map (String,String)
	 */
	void salvaScontrini(Map<String, Object> dati);
/**
 * Metodo per ricercare i prodotti da nome.
 * @param nome
 * 			nome prodotto da ricercare
 * @return
 * 			List< modelStore > :lista di prodotti 
 */	
	List<modelStore> ricercaProdotti(String nome);
/**Metodo per ottenere lunghezza lista prodotti.
 * @param lista
 * 		lista dei prodotti
 * @return
 * 		intero con lunghezza lista
 */
	 int elencoProdotti(List<modelStore> lista);
/**
 * Metodo per cercare un fornitore.
 * @param nome
 * 			nome del fornitore
 * @return 
 */
	List<modelProviders> cercaFornitori(String nome);
/**
 * Metodo per cercare un cliente.
 * @param nome
 * 			nome del cliente
 * @return 
 * 
 */
	List<modelClients> cercaClienti(String nome);
/**
 * Metodo per cercare una riunione.
 * @param data
 * 			data della riunione
 * @param nome
 * 			nome dell evento
 */
	void cercaRiunioni(String data, String nome);
/**
 * Metodo per cercare uno scontrino.
 * @param numero
 * 			numero dello scontrino
 * @param nome
 * 			nome dell prodotto
 */
	void cercaScontrini(String numero, String nome);
/**
 * Metodo di verifica di username e password.
 * @param user
 * 			username da verificare
 * @param pass
 * 			password da verificare
 * @return
 * 		Variabile boolean, true se l'operazione � eseguita con successo, falsa se non eseguita
 */
	boolean userLogin(String user, String pass);
	/**
	 * Metodo per cercare una fattura.
	 * @param numero
	 * 			numero della fattura
	 * @param nome
	 * 			nome del Cliente o Fornitore
	 */
	void cercaFatture(String numero, String nome);

}
