package view;

import java.awt.Toolkit;
import view.interfaces.ObserverInterface;

public class FornitoriGUI extends ClientiGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7744994891479399079L;
	private static final String TITOLO ="Fornitori";

	/**
	 * Create the frame.
	 */
	public FornitoriGUI(final ObserverInterface o) {
		super(o);
		this.setTitle(TITOLO);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(FornitoriGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		
	}

}
