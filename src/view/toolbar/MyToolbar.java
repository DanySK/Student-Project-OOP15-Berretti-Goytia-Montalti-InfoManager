package view.toolbar;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import com.infoMng.controller.ObserverInterfaceImpl.saveResult;

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

/**
 * Classe che definisce Mytoolbar.
 * 
 * @author Alessandro
 *
 */
public class MyToolbar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 584196517276324848L;
	private JMenuItem mntmSalva = new JMenuItem("Salva");
	private JMenuItem mntmCerca = new JMenuItem("Cerca");
	private JMenuItem mntmIndietro = new JMenuItem("Indietro");
	private JMenuItem mntmNuovo = new JMenuItem("Nuovo");

	/**
	 * Costruttore della ToolBar.
	 * 
	 * @param o
	 *            Oggetto ObserverInterface
	 * @param frame
	 *            Oggetto InitializeFrame
	 */
	public MyToolbar(final ObserverInterface o, final InitializeFrame frame) {
		this.add(mntmNuovo);
		this.add(mntmSalva);
		this.add(mntmCerca);
		this.add(mntmIndietro);
		if (frame instanceof RegistriIvaGUI) {
			mntmNuovo.setEnabled(false);
		}
		if (frame instanceof MagazzinoGUI) {
			mntmNuovo.setEnabled(false);
		}
		this.mntmNuovo
				.setIcon(new ImageIcon(FattureGUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		this.mntmNuovo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				if (frame instanceof FattureGUI) {
					((FattureGUI) frame).resetCampi();
				}
				if (frame instanceof ClientiGUI) {
					o.setAttuale(frame);
					o.abilitaFrame(false);
					o.mostraDialogNuovo();
				}
				if (frame instanceof FornitoriGUI) {
					o.setAttuale(frame);
					o.abilitaFrame(false);
					o.mostraDialogNuovo();
				}
				if (frame instanceof ScontriniGUI) {
					((ScontriniGUI) frame).resetCampi();
				}
				if (frame instanceof RiunioniGUI) {
					((RiunioniGUI) frame).resetCampi();
				}
			}
		});
		this.mntmSalva.setIcon(
				new ImageIcon(FattureGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		if (frame.getClass().equals(MagazzinoGUI.class)) {
			mntmSalva.setEnabled(false);
		}
		if (frame.getClass().equals(ReportVenditeGUI.class)) {
			mntmSalva.setEnabled(false);
		}
		if (frame.getClass().equals(RegistriIvaGUI.class)) {
			mntmSalva.setEnabled(false);
		}
		this.mntmSalva.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				if (frame.getClass().equals(FattureGUI.class)) {

					try {
						switch (o.salvaFattura(((FattureGUI) frame).getTextfield())) {
						case success:
							JOptionPane.showMessageDialog(frame, saveResult.success.rawValue);
							break;
						case errorData:
							JOptionPane.showMessageDialog(frame, saveResult.errorData.rawValue);
							break;
						case errorSave:
							JOptionPane.showMessageDialog(frame, saveResult.errorSave.rawValue);
							break;
						default:
							throw new IllegalStateException();
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				if (frame.getClass().equals(ClientiGUI.class)) {
					if (o.salvaCliente(((ClientiGUI) frame).getTextfield())) {
						JOptionPane.showMessageDialog(frame, "Salvataggio eseguito con successo");
					} else {
						JOptionPane.showMessageDialog(frame, "Errore nel salvataggio dei dati");
					}

				}
				if (frame.getClass().equals(FornitoriGUI.class)) {
					o.salvaFornitore(((FornitoriGUI) frame).getTextfield());
					JOptionPane.showMessageDialog(frame, "Salvataggio eseguito con successo");
				}

				if (frame.getClass().equals(ScontriniGUI.class)) {
					o.salvaScontrini(((ScontriniGUI) frame).getTextField());
					JOptionPane.showMessageDialog(frame, "Salvataggio eseguito con successo");
				}
				if (frame.getClass().equals(RiunioniGUI.class)) {
					try {
						o.salvaRiunione(((RiunioniGUI) frame).getTextField());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(frame, "Salvataggio eseguito con successo");
				}
			}
		});
		this.mntmCerca
				.setIcon(new ImageIcon(FattureGUI.class.getResource("/view/icon/lente-di-ingrandimento_318-1787.jpg")));
		this.mntmCerca.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				o.setAttuale(frame);
				o.abilitaFrame(false);
				o.mostraDialogCerca();

			}
		});
		this.mntmIndietro.setIcon(
				new ImageIcon(MyToolbar.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		this.mntmIndietro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				o.setAttuale(frame);
				o.mostraMenu();
			}
		});
	}

}
