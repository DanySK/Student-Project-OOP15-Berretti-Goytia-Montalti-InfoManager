package view;


import javax.swing.JFrame;

import view.tabelle.PannelloTabelle;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;

public class Fatture extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3497349615019432641L;
	private PannelloTabelle jPanel = new PannelloTabelle();

	/**
	 * Create the frame.
	 */
	public Fatture() {
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Fatture.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.setTitle("Fatture");
		this.setResizable(true);
		this.setBounds(100, 100, 450, 300);
		this.setLayout(new BorderLayout(0,0));
		this.setContentPane(jPanel);
		
		
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
			
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				Menu jFMenu = new Menu();
				jFMenu.setVisible(true);
				dispose();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
