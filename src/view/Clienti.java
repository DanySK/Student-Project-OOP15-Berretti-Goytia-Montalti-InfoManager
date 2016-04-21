package view;


import javax.swing.JFrame;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;



public class Clienti extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7113641769924370743L;
	private BaseForCli jPanel = new BaseForCli();
	

	/**
	 * Create the frame.
	 */
	public Clienti() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Clienti.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.setTitle("Clienti");
		this.setResizable(true);
		this.setBounds(100, 100, 846, 577);
		this.setContentPane(jPanel);
		this.jPanel.setEnabled(true);
		
		
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
