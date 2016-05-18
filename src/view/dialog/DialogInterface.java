package view.dialog;

import java.util.Map;
import view.interfaces.ObserverInterface;;

public interface DialogInterface {
	
	/**
	 * Metodo per catturare i dati dal JDialog
	 * @return Mappa contenente tutti i dati inseriti nelle JTextField del JDialog corrente, se l'operazione non va a buon fine 
	 * restituisce null
	 */
	public Map<String,String> getDataString(final ObserverInterface o);
}
