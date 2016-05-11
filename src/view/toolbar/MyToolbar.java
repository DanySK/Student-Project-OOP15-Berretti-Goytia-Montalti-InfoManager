package view.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import view.BaseFatture;
import view.dialog.DialogNuovo;

public class MyToolbar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 584196517276324848L;
	private JMenuItem mntmSalva = new JMenuItem("Salva");
	private JMenuItem mntmCerca = new JMenuItem("Cerca");
	private JMenuItem mntmAiuto = new JMenuItem("Aiuto");
	private JMenuItem mntmNuovo = new JMenuItem("Nuovo");
	
	public MyToolbar(){
		this.add(mntmNuovo);
		this.add(mntmSalva);
		this.add(mntmCerca);
		this.add(mntmAiuto);
		this.mntmNuovo.setIcon(new ImageIcon(BaseFatture.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		this.mntmNuovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DialogNuovo nuovoCli = new DialogNuovo();
				nuovoCli.setTitle("Nuovo Cliente o Fornitore");
				nuovoCli.setVisible(true);
			}
		});
		this.mntmSalva.setIcon(new ImageIcon(BaseFatture.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		this.mntmCerca.setIcon(new ImageIcon(BaseFatture.class.getResource("/view/icon/lente-di-ingrandimento_318-1787.jpg")));
		this.mntmAiuto.setIcon(new ImageIcon(BaseFatture.class.getResource("/view/icon/logotipo-informazioni-in-un-cerchio_318-9441.jpg")));
	}

}
