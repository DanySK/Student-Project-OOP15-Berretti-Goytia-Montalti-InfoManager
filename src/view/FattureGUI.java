package view;


import java.awt.Toolkit;
import view.interfaces.ObserverInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class FattureGUI extends InitializeFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3497349615019432641L;
	private BaseFatture panelFatture = new BaseFatture();
	private static final String TITOLO = "Fatture";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(830,568);
	/**
	 * Create the frame.
	 */
	public FattureGUI(final ObserverInterface o) {
		super(TITOLO,LAYOUT,DIMFRAME);
		this.add(panelFatture);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(FattureGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));

	}

}
