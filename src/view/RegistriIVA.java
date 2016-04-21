package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.tabelle.PannelloTabelle;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class RegistriIVA extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6419493734280735250L;
	private JPanel contentPane;
	private PannelloTabelle jPanel = new PannelloTabelle();


	/**
	 * Create the frame.
	 */
	public RegistriIVA() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(RegistriIVA.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.setTitle("Registri IVA");
		this.setResizable(true);
		this.setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.contentPane.add(jPanel);
		this.setContentPane(contentPane);
		
		
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
