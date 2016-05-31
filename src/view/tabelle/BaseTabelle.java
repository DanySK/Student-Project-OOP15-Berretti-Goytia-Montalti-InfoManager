package view.tabelle;

import javax.swing.table.AbstractTableModel;

import com.goytia.models.DB.modelStoreI;

import view.interfaces.ObserverInterface;

/**
 * Classe che definisce il modello di JTable.
 * 
 * @author Alessandro
 *
 */
public class BaseTabelle extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6283359396361049566L;
	private ObserverInterface o;

	/**
	 * 
	 */

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return 3;
	}

	@Override
	public Object getValueAt(final int row, final int col) {
		if (row == 0) {
			if (col == 0) {
				return new String("Prodotto");
			}
			if (col == 1) {
				return new String("Quantita'");
			}
			if (col == 2) {
				return new String("Prezzo");
			}
		}
		
		return new String("");
	}
/**
 * Metodo per ottenere il nome della colonna.
 * @param col
 * 			numero colonna
 * @return
 * 			nome colonna
 */
	public String getColumnName(final int col) {
		return Integer.toString(col + 1);
	}

}
