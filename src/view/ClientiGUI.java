package view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import view.interfaces.ObserverInterface;



public class ClientiGUI extends InitializeFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7113641769924370743L;
	private static final String TITOLO = "Clienti";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(830,568);
	
	

	/**
	 * Create the frame.
	 */
	public ClientiGUI(final ObserverInterface o) {
		super(TITOLO, LAYOUT, DIMFRAME);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(ClientiGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		
		
		
	}

}
