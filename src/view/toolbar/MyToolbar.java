package view.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import view.ClientiGUI;
import view.FattureGUI;
import view.FornitoriGUI;
import view.InitializeFrame;
import view.MagazzinoGUI;
import view.RegistriIvaGUI;
import view.ReportVenditeGUI;
import view.RiunioniGUI;
import view.ScontriniGUI;
import view.interfaces.ObserverInterface;

public class MyToolbar extends JToolBar {


	/**
	 * 
	 */
	private static final long serialVersionUID = 584196517276324848L;
	private JMenuItem mntmSalva = new JMenuItem("Salva");
	private JMenuItem mntmCerca = new JMenuItem("Cerca");
	private JMenuItem mntmIndietro = new JMenuItem("Indietro");
	private JMenuItem mntmNuovo = new JMenuItem("Nuovo");
	
	public MyToolbar(final ObserverInterface o, final InitializeFrame frame){
		this.add(mntmNuovo);
		this.add(mntmSalva);
		this.add(mntmCerca);
		this.add(mntmIndietro);
		if(frame.getClass().equals(ReportVenditeGUI.class)){
			mntmNuovo.setEnabled(false);
		}
		this.mntmNuovo.setIcon(new ImageIcon(FattureGUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		this.mntmNuovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(frame.getClass().equals(FattureGUI.class)){
					((FattureGUI) frame).resetCampi();
				}
				if(frame.getClass().equals(ClientiGUI.class)){
					o.setAttuale(frame);
					o.abilitaFrame(false);
					o.mostraDialogNuovo();
				}
				if(frame.getClass().equals(FornitoriGUI.class)){
					o.setAttuale(frame);
					o.abilitaFrame(false);
					o.mostraDialogNuovo();
				}
				if(frame.getClass().equals(MagazzinoGUI.class)){
					
				}
				if(frame.getClass().equals(RegistriIvaGUI.class)){
					
				}
				if(frame.getClass().equals(ScontriniGUI.class)){
					((ScontriniGUI) frame).resetCampi();
				}
				if(frame.getClass().equals(RiunioniGUI.class)){
					
				}
				
			}
		});
		this.mntmSalva.setIcon(new ImageIcon(FattureGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		if(frame.getClass().equals(MagazzinoGUI.class)){
			mntmSalva.setEnabled(false);
		}
		if(frame.getClass().equals(ReportVenditeGUI.class)){
			mntmSalva.setEnabled(false);
		}
		if(frame.getClass().equals(RegistriIvaGUI.class)){
			mntmSalva.setEnabled(false);
		}
		this.mntmSalva.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(frame.getClass().equals(FattureGUI.class)){
					o.salvaFattura(((FattureGUI) frame).getTextfield());
				}
				if(frame.getClass().equals(ClientiGUI.class)){
					o.salvaCliente(((ClientiGUI)frame).getTextfield());
				}
				if(frame.getClass().equals(FornitoriGUI.class)){
					o.salvaFornitore(((FornitoriGUI)frame).getTextfield());
				}

				if(frame.getClass().equals(ScontriniGUI.class)){
					((ScontriniGUI) frame).resetCampi();
				}
				if(frame.getClass().equals(RiunioniGUI.class)){
					o.salvaRiunione(((RiunioniGUI)frame).getTextField());
				}
			}
		});
		this.mntmCerca.setIcon(new ImageIcon(FattureGUI.class.getResource("/view/icon/lente-di-ingrandimento_318-1787.jpg")));
		this.mntmCerca.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				o.setAttuale(frame);
				o.abilitaFrame(false);
				o.mostraDialogCerca();
				
			}
		});
		this.mntmIndietro.setIcon(new ImageIcon(MyToolbar.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		this.mntmIndietro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				o.setAttuale(frame);
				o.mostraMenu();
			}
		});
	}
	
}
