package view.tabelle;


import javax.swing.table.AbstractTableModel;



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
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return new Integer ((row+1)*(col+1));
	}
	
	 // ritorna il nome della colonna
	 public String getColumnName(int col) {
		 // e' il numero di colonna
		 return Integer.toString(col+1);
	 } 

}
