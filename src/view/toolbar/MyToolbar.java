package view.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import view.BaseFatture;
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
	
	public MyToolbar(final ObserverInterface o){
		this.add(mntmNuovo);
		this.add(mntmSalva);
		this.add(mntmCerca);
		this.add(mntmIndietro);
		this.mntmNuovo.setIcon(new ImageIcon(BaseFatture.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		this.mntmNuovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				o.mostraDialogNuovo();//aggiungi booleano per controllare quale tipo di azione fare
				//Gli passo un intero e a seconda del numero passato al costruttore di mytoolbar creo una specifica toolbar
				//Chiedere a viroli come fare per gestire lo switch tra i dialog, nel senso che essendo la toolbar in più frame come fare a capire quale dialog attivare
			}
		});
		this.mntmSalva.setIcon(new ImageIcon(BaseFatture.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		this.mntmSalva.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		this.mntmCerca.setIcon(new ImageIcon(BaseFatture.class.getResource("/view/icon/lente-di-ingrandimento_318-1787.jpg")));
		this.mntmIndietro.setIcon(new ImageIcon(MyToolbar.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		this.mntmIndietro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				o.mostraMenu();
				
			}
		});
	}

}
