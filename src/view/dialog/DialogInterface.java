package view.dialog;

import java.util.Map;
import view.interfaces.ObserverInterface;;

public interface DialogInterface {
	
	/**
	 * Method that capture data from JDialogs
	 * @param o
	 * 			Object ObserverInterface
	 * @return Map (String,String) that contains all the data insert on the current JTextField's JDialog, 
	 * if the operation don't goes well this return null 
	 * 
	 */
	public Map<String,String> getDataString(final ObserverInterface o);
}
