package view.tabelle;



import javax.swing.table.AbstractTableModel;

import com.goytia.models.DB.modelStore;


/**
 * Class that define Model of JTable
 * @author Alessandro
 *
 */
public class BaseTabelle extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6283359396361049566L;

	/**
	 * 
	 */

	@Override
	public int getColumnCount() {
		
		return 3;
	}

	@Override
	public int getRowCount() {
		int i = 0;
		for (i=0;i<modelStore.elenco().size();i++){
		}
		return i;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		if(row == 0){
			if(col == 0){
				return new String("Prodotto");
			}
			if(col==1){
				return new String("Quantita'");
			}
			if (col == 2){
				return new String("Prezzo");
			}
		}
		return new String("");
	}
	
	 // ritorna il nome della colonna
	 public String getColumnName(int col) {
		 // e' il numero di colonna
		 return Integer.toString(col+1);
	 } 

}
