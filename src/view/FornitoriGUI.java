package view;

import java.awt.Toolkit;


import view.interfaces.ObserverInterface;
/**
 * Class that define  viewFornitori
 * @author Alessandro
 *
 */
public class FornitoriGUI extends ClientiGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7744994891479399079L;
	private static final String TITOLO ="Fornitori";
	

/**
 * Constructor for FornitoriGUI frame
 * @param o
 * 			Object ObserverInterface
 */
	public FornitoriGUI(final ObserverInterface o) {
		super(o);
		this.setTitle(TITOLO);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(FornitoriGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
	}

}
