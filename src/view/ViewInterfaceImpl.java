package view;

import view.interfaces.ObserverInterface;
import view.interfaces.ViewInterface;

import view.dialog.*;

public class ViewInterfaceImpl implements ViewInterface{
	
	private ObserverInterface o;
	private ClientiGUI JClienti;
	
	
	/**
	 * Costruttore vuoto
	 */
    public ViewInterfaceImpl() {
	}
    
    public ObserverInterface getObserverInterface(){
    	return this.o;
    }

	@Override
	public void viewClienti() {
		this.JClienti = new ClientiGUI(o);
		this.JClienti.display(true);
		
	}

	@Override
	public void viewFornitori() {
		new FornitoriGUI(o).display(true);;

	}

	@Override
	public void viewFatture() {
		new FattureGUI(o).display(true);
	}

	@Override
	public void viewStart() {
		new LoginGUI(o).setStatus(true);
	}

	@Override
	public void viewMenu() {
		 new MenuGUI(o).display(true);
		
	}

	@Override
	public void viewMagazzino() {
		new MagazzinoGUI(o).display(true);
		
	}

	@Override
	public void viewRegistiIva() {
		new RegistriIvaGUI(o).display(true);
	
	}

	@Override
	public void viewReportVendite() {
		 new ReportVenditeGUI(o).display(true);
	}

	@Override
	public void viewRiunioni() {
		 new RiunioniGUI(o).display(true);

	}

	@Override
	public void viewScontrini() {
		 new ScontriniGUI(o).display(true);

	}

	@Override
	public void viewDialogCampoObbligatorio() {
		 new DialogCampoObbligatorio(o).setVisible(true);

	}

	@Override
	public void viewDialogCerca() {
		new DialogCerca(o).setVisible(true);

		
	}

	@Override
	public void viewDialogNuovo() {
			new DialogNuovo(o).setVisible(true);
		
	}

	@Override
	public void viewDialogRegistrati() {
		 new DialogRegistrati(o).setVisible(true);

		
	}

	@Override
	public void viewDialogWrongPass() {
		 new DialogWrongPass(o).setVisible(true);

		
	}

	@Override
	public void viewDialogWrongUser() {
		 new DialogWrongUser(o).setVisible(true);

		
	}

	@Override
	public void setOggettoController(ObserverInterface o) {
		this.o = o;	
	}

}
