package view;


import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class Fatture extends InitializeFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3497349615019432641L;
	private static final String TITOLO = "Fatture";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(830,568);
	/**
	 * Create the frame.
	 */
	public Fatture() {
		super(TITOLO,LAYOUT,DIMFRAME);
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Fatture.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));

		
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
