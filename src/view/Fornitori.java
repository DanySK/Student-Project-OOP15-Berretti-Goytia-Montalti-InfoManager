package view;


import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Fornitori extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7744994891479399079L;
	private BaseForCli jPanel = new BaseForCli();

	/**
	 * Create the frame.
	 */
	public Fornitori() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Fornitori.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.setTitle("Fornitori");
		this.setResizable(true);
		this.setBounds(100, 100, 789, 544);

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
