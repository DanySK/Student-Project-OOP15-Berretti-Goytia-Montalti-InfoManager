package view;

import view.interfaces.ObserverInterface;
import view.interfaces.ViewInterface;

import view.dialog.*;

public class ViewInterfaceImpl implements ViewInterface{
	
	private MenuGUI jFMenu;
	private FattureGUI jFFatture;
	private ClientiGUI jFClienti;
	private FornitoriGUI jFFornitori;
	private MagazzinoGUI jFMagazzino;
	private RegistriIvaGUI jFRegistiIva;
	private RiunioniGUI jFRiunioni;
	private ScontriniGUI jFScontrini;
	private LoginGUI jFLogin;
	private ReportVenditeGUI jFReportVendite;
	private ObserverInterface o;
	private DialogCampoObbligatorio jDCampoObb;
	private DialogCerca jDCerca;
	private DialogNuovo jDNuovo;
	private DialogRegistrati jDRegistrati;
	private DialogWrongPass jDWrongPass;
	private DialogWrongUser jDWrongUser;
	
	
	/**
	 * Costruttore vuoto
	 */
    public ViewInterfaceImpl() {
	}

	@Override
	public void viewClienti() {
		this.jFClienti = new ClientiGUI(o);
		this.jFClienti.display(true);
		
	}

	@Override
	public void viewFornitori() {
		this.jFFornitori = new FornitoriGUI(o);
		this.jFFornitori.display(true);
	}

	@Override
	public void viewFatture() {
		this.jFFatture = new FattureGUI(o);
		this.jFFatture.display(true);
	}

	@Override
	public void viewStart() {
		this.jFLogin = new LoginGUI(o);
		this.jFLogin.setStatus(true);
		
	}

	@Override
	public void viewMenu() {
		this.jFMenu = new MenuGUI(o);
		this.jFMenu.display(true);
	}

	@Override
	public void viewMagazzino() {
		this.jFMagazzino = new MagazzinoGUI(o);
		this.jFMagazzino.display(true);
	}

	@Override
	public void viewRegistiIva() {
		this.jFRegistiIva = new RegistriIvaGUI(o);
		this.jFRegistiIva.display(true);
	}

	@Override
	public void viewReportVendite() {
		this.jFReportVendite = new ReportVenditeGUI(o);
		this.jFReportVendite.display(true);
	}

	@Override
	public void viewRiunioni() {
		this.jFRiunioni = new RiunioniGUI(o);
		this.jFRiunioni.display(true);
	}

	@Override
	public void viewScontrini() {
		this.jFScontrini = new ScontriniGUI(o);
		this.jFScontrini.display(true);
	}

	@Override
	public void viewDialogCampoObbligatorio() {
		this.jDCampoObb = new DialogCampoObbligatorio(o);
		this.jDCampoObb.setVisible(true);
	}

	@Override
	public void viewDialogCerca() {
		this.jDCerca = new DialogCerca(o);
		this.jDCerca.setVisible(true);
		
	}

	@Override
	public void viewDialogNuovo() {
		this.jDNuovo = new DialogNuovo(o);
		this.jDNuovo.setVisible(true);
		
	}

	@Override
	public void viewDialogRegistrati() {
		this.jDRegistrati = new DialogRegistrati(o);
		this.jDRegistrati.setVisible(true);
		
	}

	@Override
	public void viewDialogWrongPass() {
		this.jDWrongPass = new DialogWrongPass(o);
		this.jDWrongPass.setVisible(true);
		
	}

	@Override
	public void viewDialogWrongUser() {
		this.jDWrongUser = new DialogWrongUser(o);
		this.jDWrongUser.setVisible(true);
		
	}

	@Override
	public void setOggettoController(ObserverInterface o) {
		this.o = o;	
	}

}
